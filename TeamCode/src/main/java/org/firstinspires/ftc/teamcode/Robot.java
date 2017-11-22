package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by femukund on 10/22/2017.
 */

public class Robot
{
    //    DcMotor motorTest;
    Servo jewelServo;
    Servo clawLeftServo;
    Servo clawRightServo;
    ColorSensor colorSensor;
    HardwareMap hwMap;
    DcMotor rightMotor;
    DcMotor leftMotor;
    DcMotor liftMotor;
    LinearOpMode opMode;

    // hsvValues is an array that will hold the hue, saturation, and value information.
    float hsvValues[] = {0F,0F,0F};

    // values is a reference to the hsvValues array.
    final float values[] = hsvValues;

    // get a reference to the RelativeLayout so we can change the background
    // color of the Robot Controller app to match the hue detected by the RGB sensor.
    int relativeLayoutId;
    View relativeLayout = null;

    // bPrevState and bCurrState represent the previous and current state of the button.
    boolean bPrevState = false;
    boolean bCurrState = false;

    // bLedOn represents the state of the LED.
    boolean bLedOn = true;


    public void init(HardwareMap ProtohwMap, LinearOpMode linearOpMode)
    {
        hwMap = ProtohwMap;
        opMode = linearOpMode;
        jewelServo = hwMap.servo.get("jewelServo");
        clawLeftServo = hwMap.servo.get("clawLeftServo");
        clawRightServo = hwMap.servo.get("clawRightServo");
        colorSensor = hwMap.get(ColorSensor.class, "colorSensor");
        rightMotor = hwMap.dcMotor.get("rightMotor");
        leftMotor = hwMap.dcMotor.get("leftMotor");
        liftMotor = hwMap.dcMotor.get("liftMotor");

        relativeLayoutId = hwMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hwMap.appContext.getPackageName());
        relativeLayout = ((Activity) hwMap.appContext).findViewById(relativeLayoutId);
    }

    public void knockOffJewel(RobotEnum.AllianceColor allianceColor)
    {
        opMode.telemetry.addData("Status", "Sensing Color");


        if (allianceColor == RobotEnum.AllianceColor.BLUE)
        {

        }
        else
        {
        }
    }

    public boolean isJewelRed()
    {
        boolean isJewelRed = true;
        colorSensor.enableLed(true);
        int redColor = colorSensor.red();
        int blueColor = colorSensor.blue();
        opMode.telemetry.addData("Red Color Value:", colorSensor.red());
        opMode.telemetry.addData("Blue Color Value:", colorSensor.blue());
        opMode.telemetry.addData("Blue Color Value:", colorSensor.green());
        opMode.telemetry.update();
        if (redColor > blueColor)
            {
            isJewelRed = true;
        }
        else
        {
            isJewelRed = false;
        }

        WaitMillis(2000);
        colorSensor.enableLed(false);
        return isJewelRed;
    }

    // Lower Jewel Servo
    public void lowerJewelServo()
    {
        jewelServo.setPosition(1);
        opMode.telemetry.addData("Servo Position 1:", jewelServo.getPosition());
        opMode.telemetry.update();
        WaitMillis(4000);
        //GO!!!
        jewelServo.setPosition(.2);
        opMode.telemetry.addData("Servo Position 2:", jewelServo.getPosition());
        opMode.telemetry.update();
        WaitMillis(4000);
    }

    // Raise Jewel Servo
    public void raiseJewelServo()
    {
        jewelServo.setPosition(1);
        opMode.telemetry.addData("Servo Position 3:", jewelServo.getPosition());
        opMode.telemetry.update();
        WaitMillis(4000);
    }

    public void WaitMillis (long millis)
    {
        try{
            Thread.sleep(millis);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void Drive(double left, double right, long millis)
    {
        leftMotor.setPower(left);
        rightMotor.setPower(right);
        WaitMillis(millis);
    }

    public void DriveForward(double power, long millis)
    {
        leftMotor.setPower(power);
        rightMotor.setPower(-power);
        WaitMillis(millis);
    }

    public void DriveBackwards(double power, long millis)
    {
        leftMotor.setPower(-power);
        rightMotor.setPower(power);
        WaitMillis(millis);
    }

    public void Brake(long millis)
    {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        WaitMillis(millis);
    }

    public void testColorSensor(boolean gamepad1XStatus)
    {
        // check the status of the x button on either gamepad.
        bCurrState = gamepad1XStatus;

        // check for button state transitions.
        if (bCurrState && (bCurrState != bPrevState))
        {

            // button is transitioning to a pressed state. So Toggle LED
            bLedOn = !bLedOn;
            colorSensor.enableLed(bLedOn);
        }

        // update previous state variable.
        bPrevState = bCurrState;

        // convert the RGB values to HSV values.
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

        // send the info back to driver station using telemetry function.
        opMode.telemetry.addData("LED", bLedOn ? "On" : "Off");
        opMode.telemetry.addData("Clear", colorSensor.alpha());
        opMode.telemetry.addData("Red  ", colorSensor.red());
        opMode.telemetry.addData("Green", colorSensor.green());
        opMode.telemetry.addData("Blue ", colorSensor.blue());
        opMode.telemetry.addData("Hue", hsvValues[0]);

        // change the background color to match the color detected by the RGB sensor.
        // pass a reference to the hue, saturation, and value array as an argument
        // to the HSVToColor method.
        relativeLayout.post(new Runnable()
        {
            public void run()
            {
                relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
            }
        });

        opMode.telemetry.update();
    }

    public void setBackgroundWhite()
    {
        // Set the panel back to the default color
        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.WHITE);
            }
        });
    }
}
