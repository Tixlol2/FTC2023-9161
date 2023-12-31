package org.firstinspires.ftc.teamcode.Main;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class hardware {
    OpMode opMode;

    public DcMotor frontLeft, frontRight, backRight, backLeft, clawLeft, clawRight, droneLauncher;

    public DcMotor[] driveLeft, driveRight;



    public DcMotor[] Drive;
    public Servo servo;
  

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

        //reversing left side motors
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //Claw Motor Def.
        clawLeft = opMode.hardwareMap.dcMotor.get("CL");
        clawRight = opMode.hardwareMap.dcMotor.get("CR");
        //clawAngle = opMode.hardwareMap.dcMotor.get("CLA");

        //Servo Def.
        servo = opMode.hardwareMap.servo.get("CLW");

        //Drone Launch Def.
        droneLauncher = opMode.hardwareMap.dcMotor.get("DLM");

        droneLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        driveLeft = new DcMotor[]{frontLeft, backLeft};
        driveRight = new DcMotor[]{frontRight, backRight};

        Drive = new DcMotor[]{frontLeft, backLeft, frontRight, backRight};

        for (DcMotor motor : Drive){
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            opMode.telemetry.addData("Motor Init for Drive", motor);
            opMode.telemetry.update();

        }

        opMode.telemetry.update();
    }
}

