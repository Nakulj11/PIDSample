/** This is the code used for the field-centric driving tutorial
 This is by no means a perfect code
 There are a number of improvements that can be made
 So, feel free to add onto this and make it better
 */

package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.core.Moby;
import org.firstinspires.ftc.teamcode.library.DriverOrientedControl;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;

/**
 * feel free to change the name or group of your class to better fit your robot
 */
public class TeleOpParent extends LinearOpMode {

    /**
     * make sure to change these motors to your team's preference and configuration
     */


    Orientation angles;
    Acceleration gravity;

    DriverOrientedControl drive;

    @Override
    public void runOpMode() throws InterruptedException {

        /**
         * you can change the variable names to make more sense
         */



        composeTelemetry();

        waitForStart();

        drive = new DriverOrientedControl();


        while (opModeIsActive()) {
            drive.run(gamepad2, 0.6);
        }

    }

    void composeTelemetry() {
        telemetry.addAction(new Runnable() {
            @Override
            public void run() {
                angles = Moby.imu.getImu().getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                gravity = Moby.imu.getImu().getGravity();
            }
        });
    }


}