package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by femukund on 11/5/2017.
 */

@Autonomous(name="RedMiddleExtremeBotAuto")
public class RedMiddleExtremeBotAuto extends LinearOpMode
{
    Robot robot = new Robot();

    @Override
    public void runOpMode()
    {
        double drivePower = .4;

        robot.init(hardwareMap, this);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        telemetry.addData("Say", "All systems are go!");
        //GO!!!

        // Hold glyph
        robot.openClaw();
        robot.jewelServo.setPosition(0.85);

        waitForStart();
        robot.holdGlyph();
        robot.raiseLift();

        // Lower jewel servo
        robot.lowerJewelServo();
        telemetry.addData("Say", "Servo is lowered.");
        telemetry.update();

        // Sense the color of the jewel
        boolean isJewelRed = robot.isJewelRed();
        telemetry.addData("Is Jewel Red:", isJewelRed);

        // Knock off jewel
        long driveBackTime = 1400;
        if (isJewelRed)
        {
            robot.DriveForward(drivePower, 205);
            robot.Brake(500);
        } else
        {
            robot.DriveBackwards(drivePower, 205);
            robot.Brake(500);
        }

        // Raise jewel servo
        robot.raiseJewelServo();
        telemetry.addData("Say", "Servo is raised.");
        telemetry.update();

        // Drive to Cryptobox
        robot.DriveForward(drivePower, 600);
        robot.Brake(500);
        robot.TankLeft(0.8, 1800);
        robot.Brake(500);
        robot.TankRight(0.8, 600);
        robot.lowerLift();
        robot.openClaw();
        robot.DriveForward(drivePower, 500);
        robot.DriveBackwards(drivePower, 300);

        // Place the glyph
        robot.lowerLift();
        robot.openClaw();
        robot.DriveForward(drivePower, 500);
        robot.DriveBackwards(drivePower, 300);

        // Park in the triangle

        telemetry.addData("Say", "I am done.");
        telemetry.update();
    }
}
