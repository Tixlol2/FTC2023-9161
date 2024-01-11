package org.firstinspires.ftc.teamcode.Main.Auton.Blue;

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

    @Override
    public void runOpMode() throws InterruptedException {

        //Init Phase
        r.init_robot(this);


        // Timer stuff
        int maxTime = 30;

        //camera stuff
        String check_rand_location = "LEFT";
        int randInt = 0;
        boolean red = true;

        switch(check_rand_location) {
            case ("LEFT"):
                if (red) {
                    randInt = 1;
                } else {
                    randInt = 4;
                }
                break;
            case ("MIDDLE"):
                if (red) {
                    randInt = 2;
                } else {
                    randInt = 5;
                }
                break;
            case ("RIGHT"):
                if (red) {
                    randInt = 3;
                } else {
                    randInt = 6;
                }
                break;
        }

        waitForStart(); //End of Init Phase

        //Start timer

        //move toward rand line

        //Once at line open intake (drop 1st pixel)

        //Turn to backdrop

        //switch(randInt) {
        // line up with randint apriltag

        //extend claw into backdrop, use touch sensor to stop at backdrop

        ///////////////////
        // CYCLES //////
        ///////////////////

        //while time above x {
        //      cycle
        //}

        //park








    }
}