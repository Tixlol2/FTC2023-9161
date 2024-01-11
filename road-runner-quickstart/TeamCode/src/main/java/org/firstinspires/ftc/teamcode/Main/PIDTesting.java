package org.firstinspires.ftc.teamcode.Main;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.FtcDashboard;


@Config
@TeleOp
public class PIDTesting extends OpMode{

    public float target = 0;

    hardware r = new hardware();
    HardAuto b = new HardAuto();

    FtcDashboard dash = FtcDashboard.getInstance();
    private HardAuto.pidController controller;
    //private PIDController controller;

    public static float p = 0, i = 0, d = 0;
    //public static double f = 0;

    private final double ticks_in_degree = 1;// don't really know yet
    //private final double ticks_in_degree = 4096 / 360; //Axon Odometry pods measure 4096 ticks per revolution

    private DcMotorEx frontLeft;
    @Override
    public void init() {
        controller = new HardAuto.pidController(p,i,d);
        telemetry = new MultipleTelemetry(telemetry, dash.getTelemetry());

            frontLeft = hardwareMap.get(DcMotorEx.class, "FLM");
    }

    @Override
    public void loop() {
        //controller.setPID(p,i,d);
        int motorPos = frontLeft.getCurrentPosition();
        double pid = controller.pidUpdate(motorPos,target);//controller.calculate(motorPos, target);
        //double ff = Math.cos(Math.toRadian(target/ticks_in_degree)) * f;

        double power = pid;//+ ff;

        frontLeft.setPower(power);

        telemetry.addData("pos", motorPos);
        telemetry.addData("target", target);
        telemetry.update();



    }
}
