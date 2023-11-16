package org.firstinspires.ftc.teamcode.auto.util;

import com.qualcomm.robotcore.hardware.DcMotor;

public class HardAuto extends Hardware{
    //variable presets
    int counter;
//Auton
    //This start function moves forward and scores a cone on the d3 junction and then goes to the e3 tile, and faces other alliance
    public  void Start1(boolean side) {
        //side variable: true = left, false = right
        //move Backward off the wall
        PiGrabber.setPosition(1);
        waiter(500);
        VertIn(3,0.5);
        moveIn(5.5,direction.BACKWARD,0.9);
        //move over to get away from junction
        if(! side) {
            moveIn(4.5, direction.LEFT, 0.9);
        } else {
            moveIn(4.5, direction.RIGHT, 0.9);
        }
        //Move to e3/b3 tile
        moveIn(47, direction.BACKWARD, 0.9);
        //Turn 135 degrees to the right/left
        if (! side) {
            turnDeg(140, direction.RIGHT, 0.9);
        } else {
            turnDeg(140, direction.LEFT, 0.9);
        }
        //move forward to line up with junction
        moveIn(8, direction.FORWARD, 0.9);
        //score cone on junction
        coneGrab(34.5, false);

        //Move backward to middle of tile
        armPulley.setTargetPosition(0);
        frontLeft.setTargetPosition(-496);
        frontRight.setTargetPosition(496);
        backRight.setTargetPosition(-496);
        backLeft.setTargetPosition(496);
        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriverMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        armPulley.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        for (DcMotor motor : Drive) {
            motor.setPower(0.4);
        }
        armPulley.setPower(0.9);

        while (backLeft.getCurrentPosition() != backLeft.getTargetPosition()) {}

        for (DcMotor motor : Drive) {
            motor.setPower(0);
        }
        armPulley.setPower(0);

        //Turn to face other alliance
        if (! side) {
            armPulley.setTargetPosition(0);
            frontLeft.setTargetPosition(225);
            frontRight.setTargetPosition(225);
            backRight.setTargetPosition(-225);
            backLeft.setTargetPosition(-225);
            setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setDriverMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            armPulley.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            for (DcMotor motor : Drive) {
                motor.setPower(0.4);
            }
            armPulley.setPower(0.9);

            while (armPulley.getCurrentPosition() != armPulley.getTargetPosition()) {}

            for (DcMotor motor : Drive) {
                motor.setPower(0.0);
            }
            armPulley.setPower(0.0);

            moveIn(5, direction.RIGHT, 0.9);
        } else {
            turnDeg(65, direction.LEFT, 0.9);
            armPulley.setTargetPosition(0);
            frontLeft.setTargetPosition(-225);
            frontRight.setTargetPosition(-225);
            backRight.setTargetPosition(225);
            backLeft.setTargetPosition(225);
            setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setDriverMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            armPulley.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            for (DcMotor motor : Drive) {
                motor.setPower(0.4);
            }
            armPulley.setPower(0.9);

            while (armPulley.getCurrentPosition() != armPulley.getTargetPosition()) {}

            for (DcMotor motor : Drive) {
                motor.setPower(0.0);
            }
            armPulley.setPower(0.0);
            moveIn(5, direction.LEFT, 0.9);
        }

    }
    //This function should start from either the b3 or e3 tile facing away from the driver
    public void scoreConed3() {
        turnDeg(45,direction.LEFT,0.9);
        moveIn(8, direction.FORWARD, 0.9);
        coneGrab(33.5, false);
        moveIn(8, direction.BACKWARD, 0.9);
        turnDeg(45, direction.RIGHT, 0.9);
    }
    public void getConef3(int coneTowerNum) {
        turnDeg(135, direction.RIGHT, 0.9);
        moveIn(24, direction.FORWARD, 0.9);
        coneGrab(15,true);
        moveIn(24,direction.BACKWARD,0.9);
        turnDeg(135, direction.LEFT, 0.9);
    }

    public void scoreConeb3() {
        turnDeg(45,direction.RIGHT,0.9);
        moveIn(8, direction.FORWARD, 0.9);
        coneGrab(3.5, false);
        moveIn(8, direction.BACKWARD, 0.9);
        turnDeg(45, direction.LEFT, 0.9);
    }
    public void getConea3(int coneTowerNum) {
        turnDeg(135, direction.LEFT, 0.9);
        moveIn(24, direction.FORWARD, 0.9);
        coneGrab(15,true);
        moveIn(24,direction.BACKWARD,0.9);
        turnDeg(135, direction.RIGHT, 0.9);
    }
//robot automation
    //moves a motor until it reaches its position
    public void motorRunToPos(DcMotor motor, double power, int pos) {
        motor.setTargetPosition(pos);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(power);
        while (motor.getTargetPosition() != motor.getCurrentPosition()) {}
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(0);
    }
    //grabs or ungrabs from a specific height
    public void coneGrab(double height, boolean grab) {
        int in = (int) Math.round(height) ;

        VertIn(in, 0.9);
        moveIn(4,direction.FORWARD,0.9);
        PiGrabber.setPosition(0);
        waiter(500);
        VertIn(in - 1, 0.9);
        waiter(500);
        if (grab) {
            VertIn(0, 0.9);
            waiter(1000);
            PiGrabber.setPosition(1);
            waiter(500);
            VertIn(in + 5, 0.9);
        } else {
            PiGrabber.setPosition(0);
        }
        waiter(1000);
        moveIn(4,direction.BACKWARD,0.9);
    }
//robot movement
    //robot horizontal movement
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

        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriverMotorMode(DcMotor.RunMode.RUN_TO_POSITION);


        frontRight.setPower(power);
        backRight.setPower(power);
        frontLeft.setPower(power);
        backLeft.setPower(power);


        while (backLeft.getCurrentPosition() != backLeft.getTargetPosition()) {}

        setDriverMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        for (DcMotor motor : Drive) {
            motor.setPower(0);
        }
    }
    //robot horizontal turn
    public void turnDeg(int deg, direction dir, double power) {
        //Implement this once you can test your robot to find how quickly it turns
        //deg *= number of ticks
        deg *= 5;
        switch (dir) {
            case LEFT:
                frontLeft.setTargetPosition(-deg);
                frontRight.setTargetPosition(-deg);
                backRight.setTargetPosition(deg);
                backLeft.setTargetPosition(deg);
                break;
            case RIGHT:
                frontLeft.setTargetPosition(deg);
                frontRight.setTargetPosition(deg);
                backRight.setTargetPosition(-deg);
                backLeft.setTargetPosition(-deg);
                break;
        }
        setDriverMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setDriverMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        for (DcMotor motor : Drive) {
            motor.setPower(power);
        }
        while (backLeft.getTargetPosition() != backLeft.getCurrentPosition()) {}
        setDriverMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        for (DcMotor motor : Drive) {
            motor.setPower(0);
        }
    }
    //robot arm movement
    public void VertIn(int in,  double power) {
        in *= 30;
        motorRunToPos(armPulley, power, in);
// Jacob Was Here
        //2
        }
    // moves the robot in a given direction for a certain amount of time
 public void moveDir(int time, direction dir, double power) {
        switch (dir) {
            case FORWARD:
                frontLeft.setPower(power);
                frontRight.setPower(-power);
                backRight.setPower(power);
                backLeft.setPower(-power);
                break;
            case BACKWARD:
                frontLeft.setPower(-power);
                frontRight.setPower(power);
                backRight.setPower(-power);
                backLeft.setPower(power);
                break;
            case LEFT:
                frontLeft.setPower(-power);
                frontRight.setPower(-power);
                backRight.setPower(-power);
                backLeft.setPower(-power);
                break;
            case RIGHT:
                frontLeft.setPower(power);
                frontRight.setPower(power);
                backRight.setPower(power);
                backLeft.setPower(power);
                break;
        }

        if (backLeft.getMode() != DcMotor.RunMode.RUN_WITHOUT_ENCODER) {
            setDriverMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }


        waiter(time);



    }

    //direction variables
    public enum direction {
    //if you're wondering why this is happening, ask Evan, it just randomly happened when I added turnDeg
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT
    }

}
