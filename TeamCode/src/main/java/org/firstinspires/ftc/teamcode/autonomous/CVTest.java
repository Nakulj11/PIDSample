package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.component.Webcam;
import org.openftc.apriltag.AprilTagDetection;


public class CVTest extends LinearOpMode {

    Webcam.Location location = null;
    AprilTagDetection tagOfInterest = null;
    static final double FEET_PER_METER = 3.28084;


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // put this code in robot class later
        Webcam webcam = new Webcam();
        webcam.init(hardwareMap);


        while (!isStarted() && !isStopRequested()) {
            webcam.scanForTags();
            location = webcam.getLocation();
            tagOfInterest = webcam.getTagOfInterest();


            if (location != null && tagOfInterest != null){
                telemetry.addData("Location", location);
                telemetry.addData("Tag ID", tagOfInterest.id);
                telemetry.addLine("\n\nLocation data:");
                tagToTelemetry(tagOfInterest);
                telemetry.update();
            }
        }
            webcam.stopStreaming();




    }










    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }

}
