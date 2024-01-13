package org.firstinspires.ftc.teamcode.Main.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class hardware {

    opencv opencv = new opencv();
    OpMode opMode;

    public DcMotor frontLeft, frontRight, backRight, backLeft, outMain, inLeft, inRight, hang;

    public DcMotor[] driveLeft, driveRight;



    public DcMotor[] Drive;
    public Servo tweet;

    public CRServo clawServo;
  

    public void init_robot(OpMode opMode) {
        this.opMode = opMode;
        init_hardware();
    }


    public void init_hardware() {

        //Wheel Motor Def.

        frontLeft = opMode.hardwareMap.dcMotor.get("FLM");
        frontRight = opMode.hardwareMap.dcMotor.get("FRM");
        backLeft = opMode.hardwareMap.dcMotor.get("BLM");
        backRight = opMode.hardwareMap.dcMotor.get("BRM");


        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //Claw Motor Def.
        outMain = opMode.hardwareMap.dcMotor.get("OUT");
        inLeft = opMode.hardwareMap.dcMotor.get("INL");
        clawServo = opMode.hardwareMap.crservo.get("CLW");
        inRight = opMode.hardwareMap.dcMotor.get("INR");

        //Servo Def.
        tweet = opMode.hardwareMap.servo.get("TWT");

        //Hanger
        //hang = opMode.hardwareMap.dcMotor.get("HNG");

        //Drone Launch Def.
        //droneLauncher = opMode.hardwareMap.dcMotor.get("DLM");

        //droneLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        driveLeft = new DcMotor[]{frontLeft, backLeft};
        driveRight = new DcMotor[]{frontRight, backRight};

        Drive = new DcMotor[]{frontLeft, backLeft, frontRight, backRight};

        for (DcMotor motor : Drive){

            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            opMode.telemetry.addData("Motor Init for Drive", Drive);
            opMode.telemetry.update();

        }

        opMode.telemetry.update();

    }
}

