package org.firstinspires.ftc.teamcode.StuffNThings;

import static android.os.SystemClock.sleep;



import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.StuffNThings.vision;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;


@TeleOp(name = "Drive", group = "TeleOp")

public class drive extends OpMode {


    hardware r = new hardware();
    vision v = new vision();

    @Override
    public void init() {
        r.init_robot(this);
    }

    @Override
    public void loop() {


        double deflator;

        deflator = gamepad1.left_bumper ? 0.9 : gamepad1.right_bumper ? 0.5 : 0.7;

        double deflator2;

        deflator2 = gamepad2.left_bumper ? 0.9 : gamepad2.right_bumper ? 0.5 : 0.7;


        double y = gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double angle = 0;

        ///////////////////
        // MECANUM DRIVE //
        //////////////////

        double angle2 = r.imu.getAngularOrientation(AxesReference.INTRINSIC,AxesOrder.ZYX,AngleUnit.RADIANS).firstAngle;

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
        double v1 = velocity * Math.cos(angle + (Math.PI / 4) + angle2-(Math.PI/4));
        double v2 = velocity * Math.sin(angle + (Math.PI / 4) + angle2-(Math.PI/4));
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


        //set limiters for pixel System

        if (gamepad2.right_trigger > 0) {
            r.inLeft.setPower(gamepad2.right_trigger * 0.7 * deflator);
            r.inRight.setPower(-gamepad2.right_trigger * 0.7 * deflator);
        }
        if (gamepad2.left_trigger > 0){
            r.inLeft.setPower(-gamepad2.left_trigger * 0.7 * deflator);
            r.inRight.setPower(gamepad2.left_trigger * 0.7 * deflator);
        }




        r.outMain.setPower(gamepad2.left_stick_y * 0.7 * deflator2);



        r.inLeft.setPower(-(gamepad2.right_stick_y * 0.7 * deflator2));
        r.inRight.setPower((gamepad2.right_stick_y * 0.7 * deflator2));

        if (gamepad2.a) {
            r.tweet.setPosition(Math.abs(r.tweet.getPosition() - 1));
        }
        //opens and closes claw
        if (gamepad2.x) {
            r.clawServo.setPosition(Math.abs(r.clawServo.getPosition() - 1));
        }

        //Hang Mechanism
        if (gamepad2.dpad_up){
            r.hang.setPower(.9);
        } else if (gamepad2.dpad_down){
            r.hang.setPower(-.9);
        } else r.hang.setPower(0);

        //Outtake Servo Toggle
        if (gamepad2.y) {
            r.outTakeS.setPosition(Math.abs(r.outTakeS.getPosition() - 1));
        }

        // Push telemetry to the Driver Station.
        telemetry.update();










    }
}
