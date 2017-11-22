package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


/**
 * Created by femukund on 11/5/2017.
 */
@Autonomous(name="ExtremeBotAuto")
public class ExtremeBotAuto extends LinearOpMode
{
    Robot robot = new Robot();
    @Override
    public void runOpMode()
    {
        double drivePower = .25;

        robot.init(hardwareMap, this);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        telemetry.addData("Say", "All systems are go!");
        //GO!!!
        // Lower jewel servo
         //robot.lowerJewelServo();
         telemetry.addData("Say", "Servo is lowered.");
         telemetry.update();

        // Sense the color of the jewel
        boolean isJewelRed = robot.isJewelRed();
        telemetry.addData("Is Jewel Red:", isJewelRed);

        // Knock off jewel
        /*
        if (isJewelRed = true)
        {
            robot.DriveForward(.1, 500);
        }
        else
        {
            robot.DriveBackwards(.1, 500);
        }
*/
        // Raise jewel servo
        //robot.raiseJewelServo();
        telemetry.addData("Say", "Servo is raised.");
        telemetry.update();
        // robot.Drive(.5, -.5, 1000);
        // robot.Brake(500);
        // robot.Drive(-.5, .5, 1000);
        // robot.Brake(1000);


        telemetry.addData("Say", "I am done.");
        telemetry.update();
    }
}
