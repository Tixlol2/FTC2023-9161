package org.firstinspires.ftc.teamcode.Main;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class hardware {
    OpMode opMode;

    public DcMotor frontLeft, frontRight, backRight, backLeft, arm1, arm2;
    public DcMotor[] Drive;
  

    public void init_robot(OpMode opMode) {
        this.opMode = opMode;
        init_hardware();
    }


    public void init_hardware() {

        frontLeft = opMode.hardwareMap.dcMotor.get("FLM");
        frontRight = opMode.hardwareMap.dcMotor.get("FRM");
        backLeft = opMode.hardwareMap.dcMotor.get("BLM");
        backRight = opMode.hardwareMap.dcMotor.get("BRM");

        Drive = new DcMotor[]{frontLeft, backLeft, frontRight, backRight};
        for (DcMotor motor: Drive){
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        opMode.telemetry.update();
    }

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
}