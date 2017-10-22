package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by femukund on 10/11/2017.
 */

@TeleOp(name = "TeleOp Tutorial", group = "Tutorials")
public class TeleOpTutorial extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {

        waitForStart();
        while (opModeIsActive())
        {
            idle();
        }
    }
}
