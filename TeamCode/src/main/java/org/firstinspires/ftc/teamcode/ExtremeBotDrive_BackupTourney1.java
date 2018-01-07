package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static android.R.attr.left;
import static android.R.attr.right;

/**
 * Created by femukund on 10/29/2017.
 */
@TeleOp
public class ExtremeBotDrive_BackupTourney1 extends LinearOpMode
{
    Robot robot = new Robot();

    double leftMotorTgtPower = 0;
    double rightMotorTgtPower = 0;

    double liftMotorTgtPower = .1;

    double leftClawPosition = 0.5;
    double rightClawPosition = 0.5;
    static double MAX_LEFT_CLAW_OPEN_POS = 0.2;
    static double MAX_LEFT_CLAW_CLOSE_POS = 0.6;
    static double MAX_RIGHT_CLAW_OPEN_POS = 0.8;
    static double MAX_RIGHT_CLAW_CLOSE_POS = 0.4;

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
        }
    }

    public void drive()
    {
        driveWithTwoJoysticks2();
        operateLift();
        operateClaw();
    }

    public void operateLift()
    {
        liftMotorTgtPower = 0;

        if (gamepad2.dpad_up)
        {
            liftMotorTgtPower = -0.5;
            telemetry.addData("Lift Motor UPPPPPPPPPPPPPPP", "UP");
        }
        if (gamepad2.dpad_down)
        {
            liftMotorTgtPower = 0.5;
            telemetry.addData("Lift Motor DOWNNNNNNNN", "DOWN");
        }
        if (robot.liftTouchSensor.isPressed() && gamepad2.dpad_up)
        {
            liftMotorTgtPower = 0;
            telemetry.addData("Lift Motor TOUCHSENSOR IS PRESSED", "TOUCH");
        }
        robot.liftMotor.setPower(liftMotorTgtPower);
        telemetry.addData("Lift Motor Power", robot.liftMotor.getPower());
        telemetry.update();
    }

    public void operateClaw()
    {
        // If A button is pressed, open the claw (release the glyph)
        if (gamepad2.a)
        {
            leftClawPosition = MAX_LEFT_CLAW_OPEN_POS;
            rightClawPosition = MAX_RIGHT_CLAW_OPEN_POS;
            telemetry.addData("Claw Left Position A", robot.clawLeftServo.getPosition());
        }

        // If B button is pressed, close the claw (hold the glyph)
        if (gamepad2.b)
        {
            leftClawPosition = MAX_LEFT_CLAW_CLOSE_POS;
            rightClawPosition = MAX_RIGHT_CLAW_CLOSE_POS;
            telemetry.addData("Claw Left Position B", robot.clawLeftServo.getPosition());
        }

        // If Y button is pressed, middle position
        if (gamepad2.y)
        {
            leftClawPosition = 0.5;
            rightClawPosition = 0.5;
            telemetry.addData("Claw Left Position Y", robot.clawLeftServo.getPosition());
        }

        // If left trigger is pressed, keep closing the left Claw. If left stick button is pressed, keep opening the left claw
        if (gamepad2.left_trigger > 0)
        {
            leftClawPosition = leftClawPosition - 0.02;
        }
        if (gamepad2.left_bumper)
        {
            leftClawPosition = leftClawPosition + 0.02;
        }
        if (leftClawPosition < 0.2)
        {
            leftClawPosition = 0.2;
        }
        if (leftClawPosition > 0.8)
        {
            leftClawPosition = 0.8;
        }

        // If right trigger is pressed, keep closing the right Claw. If left stick button is pressed, keep opening the right claw
        if (gamepad2.right_trigger > 0)
        {
            rightClawPosition = rightClawPosition + 0.02;
        }
        if (gamepad2.right_bumper)
        {
            rightClawPosition = rightClawPosition - 0.02;
        }
        if (rightClawPosition < 0.2)
        {
            rightClawPosition = 0.2;
        }
        if (rightClawPosition > 0.8)
        {
            rightClawPosition = 0.8;
        }

        // Update claw servo positions
        robot.clawLeftServo.setPosition(leftClawPosition);
        robot.clawRightServo.setPosition(rightClawPosition);

        telemetry.addData("Claw Left Position", robot.clawLeftServo.getPosition());
        telemetry.addData("Claw Right Position", robot.clawRightServo.getPosition());
        telemetry.update();
    }

    public void operateClaw2()
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
        leftMotorTgtPower = -gamepad1.right_stick_x + gamepad1.left_stick_y;
        rightMotorTgtPower = -gamepad1.right_stick_x - gamepad1.left_stick_y;
        telemetry.addData("XXXXXXXXXXXXXXXXXX", gamepad1.right_stick_x);
        telemetry.addData("YYYYYYYYYYYYYYYYYY", gamepad1.right_stick_y);

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