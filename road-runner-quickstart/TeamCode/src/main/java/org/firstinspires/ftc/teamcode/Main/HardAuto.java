package org.firstinspires.ftc.teamcode.Main;


import com.qualcomm.robotcore.hardware.DcMotor;

public class HardAuto extends hardware {

    final double motorTickCount = 28;

    final double gearBoxMulti = 15;

    final double updatedMotorTickCount = motorTickCount * gearBoxMulti;

    final double radius = 48;
    final double circumference = 2 * (Math.PI) * radius;

    final double ticksPerInch = updatedMotorTickCount / circumference;

    ////////////////////
    // Object Classes //
    ////////////////////

    public static class pidController {

        double p;
        double i;
        double d;

        //memory variables
        float integrator, differentiator, preErr, preMeasurement;

        //Output limits
        float limMax = 0.9f,limMin = 0.9f; //Don't change, this is set to the motors' max input value

        //sample time
        float T;

        //controller output
        double out;

        
        pidController(float p, float i, float d) {
            this.p = p;
            this.i = i;
            this.d = d;
        }

        public double pidUpdate(float setpoint, float measurement) {

            //calculating error
            float error = setpoint - measurement;

            //proportional
            float proportional = (float) (p*error);

            //integral
            integrator += 0.5 * i * T * (error+preErr);

            //integral limits
            float limMaxInt,limMinInt;
            //computing the integral limits
            if (limMax > proportional) {
                limMaxInt = limMax - proportional;
            } else limMaxInt = 0;

            if (limMin < proportional) { 
                limMinInt = limMin - proportional;
            } else limMinInt = 0;
            //clamping integral
            if (integrator > limMaxInt) integrator = limMaxInt;
            else if (integrator < limMinInt) integrator = limMinInt;

            //derivative
            differentiator = (float) (-(2 * d * (measurement - preMeasurement)) + (-1*T*differentiator) / T);

            //output with applied limits
            out = (double) ((proportional + differentiator + integrator)/1000); //I put 1000 for now to convert ticks into a value that is applicable to the double that is required by the motors

            if (out > limMax) out = limMax;
            else if (out < limMin) out = limMin;

            //store error and measurement for use in next iteration
            preErr = error;
            preMeasurement = measurement;

            //Return controller output
            return out;
        }

    }

    public static class motors {
        double motorInTicks;
        double gearMulti;
        double motorTicks;
        double outRadius;
        double tickInches;

        DcMotor[] motorList;

        final int tickRange = 100;

        motors(double motorInticks, double gearMulti, double motorTicks, double outRadius, DcMotor[] motorList) {
            this.motorInTicks = motorInticks;
            this.gearMulti = gearMulti;
            this.motorTicks = motorTicks;
            this.outRadius = outRadius;
            this.tickInches = (motorInticks * gearMulti) / (outRadius*2*Math.PI);

            this.motorList = motorList;

        }

        public void runTo(int ticks, double power) {
                for (DcMotor motor : motorList) {
                    motor.setPower(power);
                }

                for (DcMotor motor : motorList) { 
                    if (Math.abs(motor.getTargetPosition() - motor.getCurrentPosition()) < tickRange) motor.setPower(0);
                }

            }
        
    }
    
    public class driveMotors extends motors {

        driveMotors(double motorInticks, double gearMulti, double motorTicks, double outRadius, DcMotor[] motorList) {
            super(motorInticks, gearMulti, motorTicks, outRadius, motorList);
        }

        public void goInches(double inches, direction dir, double power) {

        int totalTicks = (int) (inches * tickInches);
        

        switch (dir) {
            case FORWARD:
                for (DcMotor motor: motorList) {
                    motor.setTargetPosition(motor.getCurrentPosition() + totalTicks);
                }
                break;
                
            case BACKWARD:
                for (DcMotor motor: motorList) {
                    motor.setTargetPosition(motor.getCurrentPosition() - totalTicks);
                }
                break;

            case LEFT:
                for (DcMotor motor: motorList) {
                    if (motor == frontLeft || motor == backLeft) motor.setTargetPosition(motor.getCurrentPosition() - totalTicks);
                    else motor.setTargetPosition(motor.getCurrentPosition() + totalTicks);
                }
                break;

            case RIGHT:
                for (DcMotor motor: motorList) {
                    if (motor == frontLeft || motor == backLeft) motor.setTargetPosition(motor.getCurrentPosition() + totalTicks);
                    else motor.setTargetPosition(motor.getCurrentPosition() - totalTicks);
                }
                break;
        }

        for (DcMotor motor : motorList){
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(power);

            opMode.telemetry.addData("Power FORWARD", Drive);
            opMode.telemetry.addData("Current", motor.getCurrentPosition());
            opMode.telemetry.addData("Target", motor.getTargetPosition());

        }

        for (DcMotor motor : motorList) { 
            if (Math.abs(motor.getTargetPosition() - motor.getCurrentPosition()) < tickRange) motor.setPower(0);
        }

        opMode.telemetry.update();
        }

    }
    
    /////////////////////
    // Drive Functions //
    /////////////////////

    //enums for directions, you can use switch and then case FORWARD : . . . etc.
    public enum direction {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT
    }


    public void goInches(double inches, direction dir, double power) {

        int totalTicks = (int) (inches * ticksPerInch);
        

        switch (dir) {
            case FORWARD:
                for (DcMotor motor: Drive) {
                    motor.setTargetPosition(motor.getCurrentPosition() + totalTicks);
                }
                break;
                
            case BACKWARD:
                for (DcMotor motor: Drive) {
                    motor.setTargetPosition(motor.getCurrentPosition() - totalTicks);
                }
                break;

            case LEFT:
                for (DcMotor motor: Drive) {
                    if (motor == frontLeft || motor == backLeft){motor.setTargetPosition(motor.getCurrentPosition() - totalTicks)};
                    else motor.setTargetPosition(motor.getCurrentPosition() + totalTicks);
                }
                break;

            case RIGHT:
                for (DcMotor motor: Drive) {
                    if (motor == frontLeft || motor == backLeft){
                        motor.setTargetPosition(motor.getCurrentPosition() + totalTicks);
                    } else {
                   
                        motor.setTargetPosition(motor.getCurrentPosition() - totalTicks);
                    }
                }

                break;
            }

        for (DcMotor motor : Drive){
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(power);

            opMode.telemetry.addData("Power FORWARD", Drive);
            opMode.telemetry.addData("Current", motor.getCurrentPosition());
            opMode.telemetry.addData("Target", motor.getTargetPosition());

        }

        if ( totalTicks - 100 < frontLeft.getCurrentPosition() || frontLeft.getCurrentPosition() < totalTicks + 100 ) {
            for (DcMotor motor : Drive){
                motor.setPower(0);
            }
        }

        opMode.telemetry.update();

    }
}
