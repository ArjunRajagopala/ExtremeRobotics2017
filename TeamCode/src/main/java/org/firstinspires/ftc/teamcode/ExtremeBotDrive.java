package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static android.R.attr.left;
import static android.R.attr.right;

/**
 * Created by femukund on 10/29/2017.
 */
@TeleOp
public class ExtremeBotDrive extends LinearOpMode
{
    Robot robot = new Robot();
    double leftMotorTgtPower = 0;
    double rightMotorTgtPower = 0;
    double liftMotorTgtPower = .1;

    @Override
    public void runOpMode ()
    {
        telemetry.addData("Status", "Initialized");
        robot.init(hardwareMap, this);
        telemetry.update();

        // Wait for game to start (driver presses PLAY
        waitForStart();

        // run until driver presses STOP
        while (opModeIsActive())
        {
            drive();
            //driveWithTwoJoysticks2();
            //robot.testColorSensor(gamepad1.x);
        }
        robot.setBackgroundWhite();
    }


    public void drive()
    {
        driveWithTwoJoysticks2();
        lift();
        operateClaw();
    }

    public void lift()
    {
        liftMotorTgtPower = 0;

        if (gamepad2.dpad_up == true)
        {
            liftMotorTgtPower = 0.5;
        }
        if (gamepad2.dpad_down == true)
        {
            liftMotorTgtPower = -0.5;
        }
        robot.liftMotor.setPower(liftMotorTgtPower);
        telemetry.addData("Lift Motor Power", liftMotorTgtPower);
        telemetry.addData("Lift Motor Power", robot.liftMotor.getPower());
        telemetry.update();
    }

    public void operateClaw()
    {
        double clawLeftPosition = 0;
        clawLeftPosition = gamepad2.right_trigger;
        robot.clawLeftServo.setPosition(clawLeftPosition);
        double clawRightPosition = 1.0 - clawLeftPosition;
        robot.clawRightServo.setPosition(clawRightPosition);
        telemetry.addData("Claw Left Position", clawLeftPosition);
        telemetry.addData("Claw Right Position", clawRightPosition);
        telemetry.addData("Claw Left Position", robot.clawLeftServo.getPosition());
        telemetry.addData("Claw Right Position", robot.clawRightServo.getPosition());
        telemetry.update();
    }

    public void driveWithTwoJoysticks2 ()
    {
        double max;
        // Run wheels in POV mode (note: The joystick goes negative when pushed forwards, so negate it)
        // In this mode the Left stick moves the robot fwd and back, the Right stick turns left and right
        leftMotorTgtPower = -gamepad1.left_stick_x + gamepad1.right_stick_y;
        rightMotorTgtPower = -gamepad1.left_stick_x - gamepad1.right_stick_y;

        // Normalize the values so neither exceed +/- 1.0
        max = Math.max(Math.abs(leftMotorTgtPower), Math.abs(rightMotorTgtPower));
        if (max > 1.0)
        {
            leftMotorTgtPower /= max;
            rightMotorTgtPower /= max;
        }

        robot.leftMotor.setPower(leftMotorTgtPower);
        robot.rightMotor.setPower(rightMotorTgtPower);

        // Displaying information on the Driver Station
        telemetry.addData("Left Target Power", leftMotorTgtPower);
        telemetry.addData("Right Target Power", rightMotorTgtPower);
        telemetry.addData("Left Motor Power", robot.leftMotor.getPower());
        telemetry.addData("Right Motor Power", robot.rightMotor.getPower());
        telemetry.addData("Status", "Running");
        telemetry.update();
    }

    // ExtremeBotDrive with one joy stick.
    public void driveWithOneJoystick ()
    {
        leftMotorTgtPower  = -gamepad1.right_stick_y + gamepad1.right_stick_x;
        rightMotorTgtPower = -gamepad1.right_stick_y - gamepad1.right_stick_x;
        double max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0) {
            leftMotorTgtPower /= max;
            rightMotorTgtPower /= max;
        }
        robot.leftMotor.setPower(leftMotorTgtPower);
        robot.rightMotor.setPower(rightMotorTgtPower);
        telemetry.addData("Left Target Power", leftMotorTgtPower);
        telemetry.addData("Right Target Power", rightMotorTgtPower);
        telemetry.addData("Left Motor Power", robot.leftMotor.getPower());
        telemetry.addData("Right Motor Power", robot.rightMotor.getPower());
        telemetry.addData("Status", "Running");
        telemetry.update();
    }

    public void driveWithTwoJoysticks ()
    {
        leftMotorTgtPower = -this.gamepad1.left_stick_x;
        rightMotorTgtPower = -this.gamepad1.right_stick_x;
        robot.leftMotor.setPower(leftMotorTgtPower);
        robot.rightMotor.setPower(rightMotorTgtPower);
        telemetry.addData("Left Target Power", leftMotorTgtPower);
        telemetry.addData("Right Target Power", rightMotorTgtPower);
        telemetry.addData("Left Motor Power", robot.leftMotor.getPower());
        telemetry.addData("Right Motor Power", robot.rightMotor.getPower());
        telemetry.addData("Status", "Running");
        telemetry.update();
    }

}