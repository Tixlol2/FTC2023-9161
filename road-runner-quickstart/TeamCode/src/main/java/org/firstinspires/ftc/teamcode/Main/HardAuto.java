package org.firstinspires.ftc.teamcode.Main;


import com.qualcomm.robotcore.hardware.DcMotor;

public class HardAuto extends hardware {


    double motorTickCount = 28;

    double gearBoxMulti = 15;

    double updatedMotorTickCount = motorTickCount * gearBoxMulti;

    double radius = 48;
    double circumference = 2 * (Math.PI) * radius;

    double ticksPerInch = updatedMotorTickCount / circumference;


    //enums for directions, you can use switch and then case FORWARD : . . . etc.
    public enum direction {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT
    }

    public void moveInEncoder(double inches, direction dir, double power) {


        inches *= 62;
        int in = (int) Math.round(inches);

        switch (dir) {
            case FORWARD:
                frontLeft.setTargetPosition(-in);
                frontRight.setTargetPosition(-in);
                backRight.setTargetPosition(in);
                backLeft.setTargetPosition(in);
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

        for (DcMotor motor : Drive) {
            motor.setPower(power);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        }

    }

    public void moveIn(double time, direction dir, double power) {


        switch (dir) {
            case FORWARD:
                frontLeft.setPower(power);
                frontRight.setPower(-power);
                backRight.setPower(-power);
                backLeft.setPower(power);
                break;
            case BACKWARD:
                frontLeft.setPower(-power);
                frontRight.setPower(power);
                backRight.setPower(power);
                backLeft.setPower(-power);
                break;
            case LEFT:
                frontLeft.setPower(power);
                frontRight.setPower(power);
                backRight.setPower(power);
                backLeft.setPower(power);
                break;
            case RIGHT:
                frontLeft.setPower(power);
                frontRight.setPower(power);
                backRight.setPower(power);
                backLeft.setPower(power);
                break;


        }
    }

    public void goInches(double inches, direction dir, double power) {

        int totalInches = (int) (inches * ticksPerInch);



        switch (dir) {
            case FORWARD:

                for (DcMotor motor : Drive){

                    motor.setTargetPosition(totalInches);
                    if (motor == backLeft || motor == frontLeft){motor.setTargetPosition(-totalInches);}
                    else {motor.setTargetPosition(totalInches);}
                    motor.setPower(power);
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    opMode.telemetry.addData("Power FORWARD", Drive);
                    opMode.telemetry.addData("Current", motor.getCurrentPosition());
                    opMode.telemetry.addData("Target", motor.getTargetPosition());
                }
                while (frontLeft.isBusy()){
                    opMode.telemetry.addData("FLM Current", frontLeft.getCurrentPosition());
                    //Do Nothing
                }
                break;
            case BACKWARD:
                for (DcMotor motor : Drive){
                    motor.setTargetPosition(totalInches);
                    if (motor == backLeft || motor == frontLeft){motor.setTargetPosition(totalInches);}
                    else {motor.setTargetPosition(-totalInches);}
                    motor.setPower(power);
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    opMode.telemetry.addData("Power FORWARD", Drive);
                    opMode.telemetry.addData("Current", motor.getCurrentPosition());
                    opMode.telemetry.addData("Target", motor.getTargetPosition());
                }
                while (frontLeft.isBusy()){
                    //Do Nothing
                }
                break;
            case LEFT:

                break;
            case RIGHT:
                break;


        }
        opMode.telemetry.update();

    }
}