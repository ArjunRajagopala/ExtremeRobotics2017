package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by femukund on 10/22/2017.
 */
//@TeleOp(name="MyFirstJavaOpMode: Linear OpMode", group="Linear Opmode")
public class MyFirstJavaOpModeTest extends LinearOpMode
{
            Robot robot = new Robot();
            @Override
            public void runOpMode ()
            {
                robot.init(hardwareMap, this);
                telemetry.addData("Status", "Initialized");
                telemetry.update();

                // Wait for game to start (driver presses PLAY
                waitForStart();

                // run until driver presses STOP
                double tgtPower = 0;
                while (opModeIsActive())
                {
                    tgtPower = -this.gamepad2.left_stick_y;
                    robot.leftMotor.setPower(tgtPower);
                    telemetry.addData("Target Power", tgtPower);
                    telemetry.addData("Motor Power", robot.leftMotor.getPower());
                    telemetry.addData("Status", "Running");
                    telemetry.update();

            telemetry.addData("Status", "Waiting for Servo action");
            telemetry.update();
            if ( gamepad1.y)
            {
                robot.jewelServo.setPosition(0);
            }
            if ( gamepad1.x || gamepad1.b )
            {
                robot.jewelServo.setPosition(0.5);
            }
            if(gamepad1.a )
            {
                robot.jewelServo.setPosition(1);
            }
            telemetry.addData("Servo Position", robot.jewelServo.getPosition() );
        }
    }
}