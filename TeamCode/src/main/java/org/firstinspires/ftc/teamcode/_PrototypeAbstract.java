package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by femukund on 10/22/2017.
 */

public abstract class _PrototypeAbstract extends LinearOpMode
{
    _PrototypeHardwareMap robot = new _PrototypeHardwareMap();
    public void WaitMillis (long millis){
        try
        {
            Thread.sleep(millis);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
public void TurnTicks(double power, int distance, DcMotor motor){
    motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
}}