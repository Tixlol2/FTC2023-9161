package org.firstinspires.ftc.teamcode.Main;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.auto.util.HardAuto;
import org.firstinspires.ftc.teamcode.auto.util.threads.ArmVertical;

@TeleOp(name="Drive", group = "TeleOp")

public class drive extends OpMode {
    //class imports

    HardAuto r = new HardAuto();
    ArmVertical a;
    //variable presets
    double deflator = 0.7;
    boolean grab = false;
    int grabNum = 0;
    boolean grabwait = true;

    // init call
    @Override
    public void init() {
        r.init_robot(this);
        a = new ArmVertical(this, r);
    }

    //drive loop
    @Override
    public void loop() {

        double deflator;
        double armDeflator;
        deflator = gamepad1.left_bumper && gamepad1.right_bumper ? 0.9 : gamepad1.left_bumper ? 0.4 : 0.7;
        armDeflator = gamepad2.left_bumper && gamepad2.right_bumper ? 0.9 : gamepad2.left_bumper ? 0.4 : 0.7;
        {


            //legacy code that runs our mecanum drive wheels

            //this first section creates the variables that will be used later

            //first we must translate the rectangular values of the joystick into polar coordinates;
            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double angle = 0;
            // math, dunno
            if (y > 0 && x > 0)//quadrant 1
                angle = Math.atan(y / x);
            else {
                double angle1 = Math.toRadians(180) + Math.atan(y / x);
                if (y > 0 && x < 0)//quadrant 2
                    angle = angle1;
                else if (y < 0 && x < 0)//quadrant 3
                    angle = angle1;
                else if (y < 0 && x > 0)//quadrant 4
                    angle = Math.toRadians(360) + Math.atan(y / x);
            }

            if (y == 0 && x > 1)
                angle = 0;
            if (y > 0 && x == 0)
                angle = Math.PI / 2;
            if (y == 0 && x < 0)
                angle = Math.PI;
            if (y < 0 && x == 0)
                angle = 3 * Math.PI / 2;

            double velocity = Math.sqrt(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2));
            double rotation = gamepad1.right_stick_x;
            //double rotation = gamepad1.dpad_left ? 1 : gamepad1.dpad_right ? -1 : 0;
            //equations taking the polar coordinates and turing them into motor powers

            double v1 = velocity * Math.cos(angle + (Math.PI / 4));
            double v2 = velocity * Math.sin(angle + (Math.PI / 4));
            double power1 = v1 + rotation;
            double power2 = v2 + rotation;
            double power3 = v2 - rotation;
            double power4 = v1 - rotation;

            r.frontLeft.setPower(power1 * deflator);
            r.frontRight.setPower(power2 * deflator);
            r.backLeft.setPower(power3 * deflator);
            r.backRight.setPower(power4 * deflator);


            if (!a.isAlive()) {
                r.armPulley.setPower(-gamepad2.left_stick_y * armDeflator);
            }


        }

        if (gamepad2.a) {
            if (grabwait) {
                grab = !grab;
                grabNum = grab ? 1 : 0;
                grabwait = false;

            }
        } else {
            grabwait = true;
        }


        r.PiGrabber.setPosition(grabNum);



    }
}
