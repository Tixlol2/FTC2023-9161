package org.firstinspires.ftc.teamcode.Main;



import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class HardAuto extends hardware {

    @Override
    public void init_robot(OpMode opMode) {
        super.init_robot(opMode);
    }

    //enums for directions, you can use switch and then case FORWARD : . . . etc.
    public enum direction {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT
    }

    public void moveIn(double inches, direction dir, double power) {
        inches *= 62;
        int in = (int) Math.round(inches);

        switch (dir) {
            case FORWARD:
                frontLeft.setTargetPosition(in);
                frontRight.setTargetPosition(-in);
                backRight.setTargetPosition(in);
                backLeft.setTargetPosition(-in);
                break;
            case BACKWARD:
                frontLeft.setTargetPosition(-in);
                frontRight.setTargetPosition(in);
                backRight.setTargetPosition(-in);
                backLeft.setTargetPosition(in);
                break;
            case LEFT:
                frontLeft.setTargetPosition(in);
                frontRight.setTargetPosition(in);
                backRight.setTargetPosition(in);
                backLeft.setTargetPosition(in);
                break;
            case RIGHT:
                frontLeft.setTargetPosition(-in);
                frontRight.setTargetPosition(-in);
                backRight.setTargetPosition(-in);
                backLeft.setTargetPosition(-in);
                break;
        }
    }
}