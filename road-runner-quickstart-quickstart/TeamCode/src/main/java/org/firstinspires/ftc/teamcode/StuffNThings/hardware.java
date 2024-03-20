package org.firstinspires.ftc.teamcode.StuffNThings;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;

public class hardware {
    OpMode opMode;
    public DcMotor TopLeft, TopRight,BottomLeft ,BottomRight,PullupBarRotator, LeftSlide, RightSlide,inRotator,Scooper, SpinnyThing, ArmElbow, PullupBarExtender, AirplaneMotor; //AirPlanemotor;
    public DcMotor[] drive;
    public Servo claw, Rarm, Larm, getSlideSpool, ClawFingerOne,ClawFingerTwo;
    public Servo InTakeStar,slideSpool, ArmExtender, PixelClampThumbLpitchOne, LpitchTwo , RpitchOne, RpitchTwo;
    public CRServo  r,ClawWristOne,ClawWristTwo, AirplaneLauncher,RightLifter, LeftLifter;
    public ModernRoboticsI2cColorSensor ArmColorSensor;




    public void init_robot(OpMode opMode){

        this.opMode = opMode;

        init_hardware();

    }

    public boolean getTolerance(double val1, double val2, double tolerance){
        return (val1+tolerance >val2) && (val1-tolerance < val2);
    }
    public void init_hardware(){
////////////////////////////////////////////////////////////////////////BTW Casper Slide dude was here 2
//        ArmColorSensor = (ModernRoboticsI2cColorSensor) opMode.hardwareMap.colorSensor.get("ACS");

       TopLeft = opMode.hardwareMap.dcMotor.get("TLM");
        TopRight = opMode.hardwareMap.dcMotor.get("TRM");
        BottomLeft = opMode.hardwareMap.dcMotor.get("BLM");
        BottomRight = opMode.hardwareMap.dcMotor.get("BRM");


//        Lift = opMode.hardwareMap.dcMotor.get("LFT");
        RightSlide = opMode.hardwareMap.dcMotor.get("RSL");
        LeftSlide = opMode.hardwareMap.dcMotor.get("LSL");
//        AirplaneMotor = opMode.hardwareMap.dcMotor.get("ALM");

////////////////////////////////////////////////////////////////////////PEACE!

//          Scooper = opMode.hardwareMap.dcMotor.get("SOM");
//          slideSpool = opMode.hardwareMap.servo.get("SPL");
          PullupBarExtender = opMode.hardwareMap.dcMotor.get("PBE");
////          ArmExtender = opMode.hardwareMap.servo.get("AAE");
          PullupBarRotator = opMode.hardwareMap.dcMotor.get("PBR");
        AirplaneLauncher = opMode.hardwareMap.crservo.get("ALR");
        ClawWristOne = opMode.hardwareMap.crservo.get("CWO");
        ClawFingerOne = opMode.hardwareMap.servo.get("CFO");
        ClawWristTwo = opMode.hardwareMap.crservo.get("CWT");
        ClawFingerTwo = opMode.hardwareMap.servo.get("CFT");
        //RightLifter = opMode.hardwareMap.crservo.get("RLF");
        //LeftLifter = opMode.hardwareMap.crservo.get("LFL");
//        PixelClampThumb = opMode.hardwareMap.servo.get("PCT");







        TopLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        BottomRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BottomLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        TopRight.setDirection(DcMotorSimple.Direction.REVERSE);





       drive = new DcMotor[]{TopLeft, TopRight, BottomLeft, TopRight};
//        for (DcMotor motor: drive){
//            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        }
    }
}

