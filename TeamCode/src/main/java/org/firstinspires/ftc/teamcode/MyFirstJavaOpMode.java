package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by femukund on 10/22/2017.
 */
@TeleOp
public class MyFirstJavaOpMode extends _PrototypeAbstract
{
    @Override
    public void runOpMode ()
    {
        robot.init(hardwareMap)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for game to start (driver presses PLAY
        waitForStart();

        // run until driver presses STOP
        double tgtPower = 0;
        while (opModeIsActive())
        {
            tgtPower = -this.gamepad2.left_stick_y;
            robot.motorTest.setPower(tgtPower);
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Motor Power", robot.motorTest.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
