package org.firstinspires.ftc.teamcode.auto.util.threads;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.auto.util.HardAuto;

public class ArmVertical extends Thread{
    HardAuto r;
    int pos;
    OpMode opMode;
    boolean set;
    int in;
    double power;

    int counter;
    @Override
    public void run(){


        super.run();
        //Move whatever
        vertIn(this.in, this.set, this.power);

    }
    public void moveVert(int in, boolean set, double power){
        this.in = in;
        this.set = set;
        this.power = power;
        start();
    }
    private void vertIn(int in, boolean set, double power) {

        
        in *= 82;
        in = !set ? r.armPulley.getCurrentPosition() + in : in;




        r.armPulley.setTargetPosition(in);

        r.armPulley.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.armPulley.setPower(0.9);
        while (r.armPulley.getTargetPosition() != r.armPulley.getCurrentPosition()) {

        }


        r.armPulley.setPower(0);
        r.armPulley.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public ArmVertical(OpMode opMode, HardAuto r){
        this.opMode = opMode;
        this.r = r;
    }

    public void move(int pos){
        this.pos = pos;
        start();
    }

}
