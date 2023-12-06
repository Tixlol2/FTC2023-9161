package org.firstinspires.ftc.teamcode.Main;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class hardware {
    OpMode opMode;

    public DcMotor frontLeft, frontRight, backRight, backLeft, arm1, arm2;
    public DcMotor[] Drive;

    public void init_robot(OpMode opMode) {
        this.opMode = opMode;
        init_hardware();
    }


    public void init_hardware() {




        frontLeft = opMode.hardwareMap.dcMotor.get("FLM");
        frontRight = opMode.hardwareMap.dcMotor.get("FRM");
        backLeft = opMode.hardwareMap.dcMotor.get("BLM");
        backRight = opMode.hardwareMap.dcMotor.get("BRM");

        //Remove if error, I am testing to see if motor's work better with float or brake ZP
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);



        Drive = new DcMotor[]{frontLeft, backLeft, frontRight, backRight};
        for (DcMotor motor: Drive){
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        opMode.telemetry.update();
    }


}