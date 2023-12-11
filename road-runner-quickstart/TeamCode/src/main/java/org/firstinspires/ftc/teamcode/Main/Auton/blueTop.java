package org.firstinspires.ftc.teamcode.Main;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Main.HardAuto;


@Autonomous(name = "blueTop", group = "Auto")
public class blueTop extends LinearOpMode {

    HardAuto r = new HardAuto();


    //We have to add an init robot probably
    double motorTickCount = 28;

    double gearBoxMulti = 15;

    double updatedMotorTickCount = motorTickCount * gearBoxMulti;

    double radius = 48;

    double circumference = 2 * (Math.PI) * radius;

    double ticksPerInch = updatedMotorTickCount / circumference;


    @Override
    public void runOpMode() throws InterruptedException {

        //Init Phase
        r.init_robot(this);
        waitForStart(); //End of Init Phase

        r.goInches(4, HardAuto.direction.FORWARD, .2);
        telemetry.addData("Completed Forward", r.frontLeft.getCurrentPosition());
        sleep(20000);
        r.goInches(4, HardAuto.direction.BACKWARD, .2);



    }
}