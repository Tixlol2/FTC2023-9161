package org.firstinspires.ftc.teamcode.auto.util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class hardware {
    OpMode opMode;

    public DcMotor frontLeft, frontRight, backRight, backLeft;
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

        Drive = new DcMotor[]{frontLeft, backLeft, frontRight, backRight};
        for (DcMotor motor: Drive){
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        opMode.telemetry.update();
    }

}