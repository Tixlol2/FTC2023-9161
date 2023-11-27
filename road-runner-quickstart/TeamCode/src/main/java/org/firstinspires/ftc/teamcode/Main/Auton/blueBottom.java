package org.firstinspires.ftc.teamcode.Main;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name = "blueBottom", group = "Auto")
public class blueBottom extends LinearOpMode {

    HardAuto r = new HardAuto();
    DcMotor frontLeft, frontRight, backLeft, backRight;


    @Override
    public void runOpMode() throws InterruptedException {

        frontLeft = hardwareMap.get(DcMotor.class, "FLM");
        frontRight = hardwareMap.get(DcMotor.class, "FRM");
        backLeft = hardwareMap.get(DcMotor.class, "BLM");
        backRight = hardwareMap.get(DcMotor.class, "BRM");


        /*
        double diameter = 0;

        double circumference = (diameter * Math.PI);

        double ticksPerInch = (28/circumference);

        */

        //i need to go 33 inches


        //total distance = speed * time
        //results in 500
        //500 what???
        r.moveIn(.25, HardAuto.direction.RIGHT, .5);
        sleep(25);
        r.moveIn(35, HardAuto.direction.FORWARD, .5);

    }


    public void run(double power, int time){

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        sleep(time);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);


    }

}