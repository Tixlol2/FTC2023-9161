package org.firstinspires.ftc.teamcode.Main;



import com.qualcomm.robotcore.hardware.DcMotor;

public class HardAuto extends hardware {

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