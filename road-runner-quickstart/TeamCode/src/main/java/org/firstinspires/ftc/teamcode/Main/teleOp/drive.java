package org.firstinspires.ftc.teamcode.Main.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Main.hardware;

@TeleOp(name = "Drive", group = "TeleOp")

public class drive extends OpMode {

    hardware r = new hardware();

    @Override
    public void init() {
        r.init_robot(this);
    }

    @Override
    public void loop() {

        double deflator;
        
        deflator = gamepad1.left_bumper ? 0.9 : gamepad1.right_bumper ? 0.5 : 0.7;

        double y = gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double angle = 0;

        ///////////////////
        // MECANUM DRIVE //
        //////////////////
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
        //equations taking the polar coordinates and turning them into motor powers
        double v1 = velocity * Math.cos(angle + (Math.PI / 4));
        double v2 = velocity * Math.sin(angle + (Math.PI / 4));
        double power1 = v1 + rotation;
        double power2 = v2 + rotation;
        double power3 = v2 - rotation;
        double power4 = v1 - rotation;

        r.frontLeft.setPower((power1) * deflator);
        r.frontRight.setPower(-power2 * deflator);
        r.backLeft.setPower(-(power3) * deflator);
        r.backRight.setPower(power4 * deflator);
        //////////////////////////
        // END OF MECANUM DRIVE //
        //////////////////////////

        //REV HD Motor outputs 28 ticks per rotation before gearbox.
        // will wheel motors be put through gear ratio? or just conveyer


        r.inMain.setPower(gamepad2.right_stick_y);
        r.outMain.setPower(gamepad2.left_stick_y);
    }
}
