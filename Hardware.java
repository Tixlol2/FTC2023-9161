package org.firstinspires.ftc.teamcode.auto.util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;





public class Hardware {
    // OpMode, OpMode . . . OpMode
    OpMode opMode;
    //finding and naming motors and servos
    // wheel motors
    public DcMotor frontLeft, frontRight, backRight, backLeft;
    public DcMotor[] Drive;
    //arm stuff
    public DcMotor armPulley;
    public Servo PiGrabber;

    //public DigitalChannel redLED;
    //public DigitalChannel greenLED;
    // init code
    public void init_robot(OpMode opMode){
        this.opMode = opMode;
        init_hardware();
    }
    // actual init code
    public void init_hardware() {
        //actually finding the motors in the configuration
        frontLeft = opMode.hardwareMap.dcMotor.get("FLM");
        frontRight = opMode.hardwareMap.dcMotor.get("FRM");
        backLeft = opMode.hardwareMap.dcMotor.get("BLM");
        backRight = opMode.hardwareMap.dcMotor.get("BRM");
        //redLED = opMode.hardwareMap.get(DigitalChannel.class, "red");
        //greenLED = opMode.hardwareMap.get(DigitalChannel.class, "green");
        //greenLED.setMode(DigitalChannel.Mode.OUTPUT);
        //redLED.setMode(DigitalChannel.Mode.OUTPUT);
        Drive = new DcMotor[]{frontLeft, backLeft, frontRight, backRight};
        for (DcMotor motor: Drive){
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        // testing to see if arm stuff is there
        try {
            armPulley = opMode.hardwareMap.dcMotor.get("AP");
        } catch (Exception e){
            opMode.telemetry.addLine("Pulley Motor Not Found");
        }
        try {
            PiGrabber = opMode.hardwareMap.servo.get("grab");


        }catch (Exception e){
            opMode.telemetry.addLine("Claw Servo Not Found");
        }


        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);


        opMode.telemetry.update();

    }

    //simple wait function
    public void waiter(int time){
        ElapsedTime Time = new ElapsedTime();
        Time.reset();
        while (Time.milliseconds() <= time){
        //nothing needs to go here, hence the name "waiter"
        }
    }

    public void inwaiter(int time, boolean var) {
        ElapsedTime Time = new ElapsedTime();
        Time.reset();
        if (Time.milliseconds() <= time){
            var = true;
        } else {
            var = false;
        }
    }

    //automated motor:motor set mode
    public void setDriverMotorMode(DcMotor.RunMode mode) {
        for (DcMotor motor: Drive){motor.setMode(mode);}
    }
}