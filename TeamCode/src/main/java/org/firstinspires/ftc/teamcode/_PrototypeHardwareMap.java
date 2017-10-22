package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by femukund on 10/22/2017.
 */

public class _PrototypeHardwareMap{
    DcMotor motorTest;
    Servo testServo;

    HardwareMap hwMap;
    public void init(HardwareMap ProtohwMap){
        hwMap = ProtohwMap;
        motorTest = hwMap.dcMotor.get("motorTest");
        testServo = hwMap.servo.get("testServo");
    }
}
