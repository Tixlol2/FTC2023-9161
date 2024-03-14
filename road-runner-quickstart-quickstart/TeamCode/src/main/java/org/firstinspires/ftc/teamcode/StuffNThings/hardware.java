package org.firstinspires.ftc.teamcode.StuffNThings;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;

public class hardware {

    opencv opencv = new opencv();
    OpMode opMode;

    public DcMotor frontLeft, frontRight, backRight, backLeft, outMain, inLeft, inRight, hang;

    public DcMotor[] driveLeft, driveRight;


    public BNO055IMU imu;


    public DcMotor[] Drive;
    public Servo tweet, outTakeS, pixelPusher, clawServo;

    public DcMotor[] all;


  

    public void init_robot(OpMode opMode) {
        this.opMode = opMode;
        init_hardware();
    }

    public void init_robot_noDrive(OpMode opMode) {
        this.opMode = opMode;
        init_hardware_noDrive();
    }


    public void init_hardware() {

        //Wheel Motor Def.

        frontLeft = opMode.hardwareMap.dcMotor.get("FLM");
        frontRight = opMode.hardwareMap.dcMotor.get("FRM");
        backLeft = opMode.hardwareMap.dcMotor.get("BLM");
        backRight = opMode.hardwareMap.dcMotor.get("BRM");


        //Gyro def.
        imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();//.Parameters(new RevHubOrientationOnRobot(
                parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);


        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //Claw Def.
        inLeft = opMode.hardwareMap.dcMotor.get("INL");
        inRight = opMode.hardwareMap.dcMotor.get("INR");
        clawServo = opMode.hardwareMap.servo.get("CLW");

        //Airplane Launcher Servo Def.
        tweet = opMode.hardwareMap.servo.get("TWT");

        //Hanger
        hang = opMode.hardwareMap.dcMotor.get("HNG");

        //Outtake
        outMain = opMode.hardwareMap.dcMotor.get("OUT");
        outTakeS = opMode.hardwareMap.servo.get("OUTS");

        //Pusher Def.
        pixelPusher = opMode.hardwareMap.servo.get("CM");




        driveLeft = new DcMotor[]{frontLeft, backLeft};
        driveRight = new DcMotor[]{frontRight, backRight};

        Drive = new DcMotor[]{frontLeft, backLeft, frontRight, backRight};
        all = new DcMotor[]{outMain, inLeft, inRight, hang};

        for (DcMotor motor : Drive){

            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        }

    }

    public void init_hardware_noDrive() {




        //Gyro def.
        //imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");

        //BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();//.Parameters(new RevHubOrientationOnRobot(
        //parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        //imu.initialize(parameters);


        //backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //Claw Def.
        inLeft = opMode.hardwareMap.dcMotor.get("INL");
        inRight = opMode.hardwareMap.dcMotor.get("INR");
        clawServo = opMode.hardwareMap.servo.get("CLW");

        //Airplane Launcher Servo Def.
        tweet = opMode.hardwareMap.servo.get("TWT");

        //Hanger
        hang = opMode.hardwareMap.dcMotor.get("HNG");

        //Outtake
        outMain = opMode.hardwareMap.dcMotor.get("OUT");
        outTakeS = opMode.hardwareMap.servo.get("OUTS");

        //Pusher Def.
        pixelPusher = opMode.hardwareMap.servo.get("CM");


        all = new DcMotor[]{outMain, inLeft, inRight, hang};
/*
        driveLeft = new DcMotor[]{frontLeft, backLeft};
        driveRight = new DcMotor[]{frontRight, backRight};

        Drive = new DcMotor[]{frontLeft, backLeft, frontRight, backRight};


        for (DcMotor motor : Drive){

            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
*/


    }
}

