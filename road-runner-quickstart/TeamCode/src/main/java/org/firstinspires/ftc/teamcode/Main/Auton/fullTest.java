package org.firstinspires.ftc.teamcode.Main;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "fullTest", group = "Auto")
public class fullTest extends LinearOpMode {

    HardAuto r = new HardAuto();



    DcMotor frontLeft, frontRight, backLeft, backRight;

    OpMode opMode;

    @Override
    public void runOpMode() throws InterruptedException {


        r.init_robot(this);



        r.moveIn(2, HardAuto.direction.FORWARD, .5);
        r.moveIn(2, HardAuto.direction.BACKWARD, .5);
        r.moveIn(2, HardAuto.direction.LEFT, .5);
        r.moveIn(2, HardAuto.direction.RIGHT, .5);



    }
}
