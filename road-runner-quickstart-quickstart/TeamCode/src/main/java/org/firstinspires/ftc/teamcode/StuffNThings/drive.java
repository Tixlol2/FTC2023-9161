package org.firstinspires.ftc.teamcode.StuffNThings;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// Drake was here :)
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;


//import org.firstinspires.ftc.teamcode.code.util.BackupHardware;


@TeleOp(name="Backup Drive", group = "TeleOp")
public class drive extends OpMode {
    hardware r = new hardware();



    @Override
    public void init() {
        r.init_robot(this);
        r.ClawWristOne.setPower(0);
        //r.ClawWristTwo.setPower(0);

    }

    @Override
    public void loop() {
        double deflator = 0.7;
        //deflator = gamepad1.left_bumper && gamepad1.right_bumper ? 0.1 : gamepad1.left_bumper ? 0.4 : 0.7;
        //r.slideSpool.scaleRange(0.0, 1.0);
//        r.Larm.scaleRange(0.0, 1.0);


        //legacy code that runs our mecanum drive wheels

        //this first section creates the variables that will be used later

        //first we must translate the rectangular values of the joystick into polar coordinates;

        //- <3 Evan


        double y = gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double angle = 0;
        boolean extended;

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
//        double strafe = gamepad1.right_stick_x;

        //equations taking the polar coordinates and turing them into motor powers
        double v1 = velocity * Math.cos(angle + (Math.PI / 4));
        double v2 = velocity * Math.sin(angle + (Math.PI / 4));
        double power1 = v1 + rotation;
        double power2 = v2 + rotation;
        double power3 = v2 - rotation;
        double power4 = v1 - rotation;


        r.TopLeft.setPower((power1) * deflator);
        r.TopRight.setPower(power2 * deflator);
        r.BottomLeft.setPower(-(power3) * deflator);
        r.BottomRight.setPower(-(power4) * deflator);
        telemetry.addLine(String.valueOf(r.TopLeft.getCurrentPosition()));
        telemetry.addLine(String.valueOf(r.TopRight.getCurrentPosition()));
        telemetry.addLine(String.valueOf(r.BottomLeft.getCurrentPosition()));
        telemetry.addLine(String.valueOf(r.BottomRight.getCurrentPosition()));
//        double launcher_degrees = 0;

//        double coaxialAngleTime = (41126.4 / (360 / angle)) / 360;
        double ClawAngle = (0);
        double ClawAngle1 = (0);

        if (gamepad1.a) {
            ClawAngle -= 1;
            r.ClawFingerOne.setPosition(ClawAngle);
        } else {
            r.ClawFingerOne.setPosition(1);
        }
        if (gamepad1.x) {
            ClawAngle1 += 1;
            r.ClawFingerTwo.setPosition(ClawAngle1);
        } else {
            r.ClawFingerTwo.setPosition(0);
        }

//        double clawrist = gamepad1.right_trigger - gamepad1.left_trigger;
        //r.ClawWristTwo.setPower(clawrist);
        r.ClawWristOne.setPower(gamepad1.left_trigger - gamepad1.right_trigger);
        r.ClawWristTwo.setPower(Math.abs((gamepad1.left_trigger + gamepad1.right_trigger)-1));


//        if (gamepad1.right_trigger == 1) {
//            r.ClawWristOne.setPower(gamepad1.right_trigger - gamepad1.left_trigger );
//            r.ClawWristTwo.setPower(0.7);
//        } else if (gamepad1.left_trigger == 1) {
//            r.ClawWristOne.setPower(0.7);
//            r.ClawWristTwo.setPower(0.7);
//        } else {
//            r.ClawWristOne.setPower(0);
//            r.ClawWristTwo.setPower(0);
//        }



//        if (gamepad1.right_stick_x >= 0.8) {
//            r.Lift.setPower(0.8);
//        } else if (gamepad1.right_stick_x <= -0.8) {
//            r.Lift.setPower(-0.8);
//        } else {
//            r.Lift.setPower(0);
//        }

//        if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0) {
//            if (gamepad1.left_stick_y >= 0.8) {
//                r.TopRight.setPower(0.7);
//                r.BottomRight.setPower(0.7);
//                r.TopLeft.setPower(-0.7);
//                r.BottomLeft.setPower(-0.7);
//            } else if (gamepad1.left_stick_y <= -0.8) {
//                r.TopRight.setPower(-0.7);
//                r.BottomRight.setPower(-0.7);
//                r.TopLeft.setPower(0.7);
//                r.BottomLeft.setPower(0.7);
//            } else if (gamepad1.left_stick_x <= -0.8) {
//                r.TopRight.setPower(0.9);
//                r.BottomRight.setPower(-0.9);
//                r.TopLeft.setPower(0.9);
//                r.BottomLeft.setPower(-0.9);
//            } else if (gamepad1.left_stick_x >= 0.8) {
//                r.TopRight.setPower(-0.9);
//                r.BottomRight.setPower(0.9);
//                r.TopLeft.setPower(-0.9);
//                r.BottomLeft.setPower(0.9);
//            } else if (gamepad1.left_stick_x >= 0.8 && gamepad1.left_stick_y <= -0.8) {
//                r.TopLeft.setPower(0.7);
//                r.BottomRight.setPower(0.7);
//            } else if (gamepad1.left_stick_x >= -0.8 && gamepad1.left_stick_y <= -0.8) {
//                r.BottomLeft.setPower(0.7);
//                r.TopRight.setPower(0.7);
//            } else if (gamepad1.left_stick_x >= -0.8 && gamepad1.left_stick_y <= 0.8) {
//                r.TopLeft.setPower(-0.7);
//                r.BottomRight.setPower(-0.7);
//            } else if (gamepad1.left_stick_x >= 0.8 && gamepad1.left_stick_y <= 0.8) {
//                r.BottomLeft.setPower(-0.7);
//                r.TopRight.setPower(-0.7);
//            } else {
//                r.TopRight.setPower(0);
//                r.TopLeft.setPower(0);
//                r.BottomRight.setPower(0);
//                r.BottomLeft.setPower(0);
//            }
//            if (gamepad1.left_stick_y != 0 || gamepad1.left_stick_x != 0) {
//                if (gamepad1.right_stick_x >= 0.8) {
//                    r.TopLeft.setPower(-0.7);
//                    r.BottomLeft.setPower(-0.7);
//                    r.TopRight.setPower(0.7);
//                    r.BottomRight.setPower(0.7);
//                } else if (gamepad1.right_stick_x <= -0.8) {
//                    r.TopLeft.setPower(0.7);
//                    r.BottomLeft.setPower(0.7);
//                    r.TopRight.setPower(-0.7);
//                    r.BottomRight.setPower(-0.7);
//                } else {
//                    r.TopRight.setPower(0);
//                    r.TopLeft.setPower(0);
//                    r.BottomRight.setPower(0);
//                    r.BottomLeft.setPower(0);
//                }
//
                if (gamepad2.x) {
                    telemetry.addData("X", gamepad2.x);
                    r.AirplaneLauncher.setPower(-1);
                } else if (gamepad2.y) {
                    r.AirplaneLauncher.setPower(1);
                }else{
                    r.AirplaneLauncher.setPower(0);
                }
//                //PEACE!
//

//            if (gamepad1.right_bumper) {
//
//                r.clawWrist.setPower(0.2);
//
//            }
//            else if (gamepad1.left_bumper) {
//                r.clawWrist.setPower(-0.2);
//            }else{

//                r.clawWrist.setPower(0);


        if (gamepad2.right_bumper) {
            r.PullupBarRotator.setPower(0.9);
        }
        if (gamepad2.left_bumper) {
            r.PullupBarRotator.setPower(-0.9);
        } else {
            r.PullupBarRotator.setPower(0);
        }

//        if (gamepad1.dpad_up){
//
//            deflator = 0.9;u
//
//        } else if (gamepad1.dpad_down) {
//            deflator = 0.5;
//        } else if (gamepad1.dpad_left || gamepad1.dpad_right) {
//            deflator = 0.7;
//        }


//
//
        if (gamepad2.right_trigger == 1) {
            r.PullupBarExtender.setPower(1);
        }
        if (gamepad2.left_trigger == 1) {
            r.PullupBarExtender.setPower(-1);
        } else {
            r.PullupBarExtender.setPower(0);
        }
//        if (Math.abs(gamepad2.left_stick_y) > 0.3 || Math.abs(gamepad2.left_stick_x) > 0.3) {


        telemetry.addData("X", gamepad2.right_stick_x);
        telemetry.update();

        if (gamepad1.left_bumper) {
            r.RightSlide.setPower(-0.5);
            r.LeftSlide.setPower(0.5);
        } else if (gamepad1.right_bumper) {
            r.RightSlide.setPower(0.5);
            r.LeftSlide.setPower(-0.5);
        } else {
            r.RightSlide.setPower(0);
            r.LeftSlide.setPower(0);
        }
//        if (gamepad2.right_bumper) {
//            r.RightLifter.setPower(-0.5);
//            r.LeftLifter.setPower(-0.5);
//        } else if (gamepad2.left_bumper) {
//            r.RightLifter.setPower(-0.5);
//            r.LeftLifter.setPower(-0.5);
//        } else {
//            r.RightLifter.setPower(0);
//            r.LeftLifter.setPower(0);
//        }
    }
}


