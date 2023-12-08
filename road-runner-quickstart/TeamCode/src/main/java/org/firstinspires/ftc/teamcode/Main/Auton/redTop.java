package org.firstinspires.ftc.teamcode.Main;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Main.HardAuto;


@Autonomous(name = "redTop", group = "Auto")
public class redTop extends LinearOpMode {

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

        r.moveIn(.25, HardAuto.direction.RIGHT, .2);
        sleep(25);
        r.moveIn(20, HardAuto.direction.FORWARD, .5);


    }




}