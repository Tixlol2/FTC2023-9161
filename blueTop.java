package org.firstinspires.ftc.teamcode.Main;

import static java.lang.Thread.sleep;

import android.graphics.Camera;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Main.HardAuto;
import org.firstinspires.ftc.teamcode.Main.opencv;
import org.firstinspires.ftc.teamcode.Main.thing;
import org.firstinspires.ftc.vision.VisionPortal;


@Autonomous(name = "blueTop", group = "Auto")
public class blueTop extends LinearOpMode {

    HardAuto r = new HardAuto();
    VisionPortal portal;
    RedPropThreshold redPropThreshold;

    @Override
    public void runOpMode() throws InterruptedException {

        //Init Phase
        r.init_robot(this);


        //Start Cam Testing

        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .setCamera(BuiltinCameraDirection.BACK)
                .build();


        // Timer stuff
        int maxTime = 30;

        //camera stuff
        int check_rand_location = r.checkRand();
        int backSlot = 0;
        boolean blue = true;

        switch(check_rand_location) {
            case (1):
                if (blue) {
                    backSlot = 1;
                } else {
                    backSlot = 4;
                }
                break;
            case (2):
                if (blue) {
                    backSlot = 2;
                } else {
                    backSlot = 5;
                }
                break;
            case (3):
                if (blue) {
                    backSlot = 3;
                } else {
                    backSlot = 6;
                }
                break;
        }

        waitForStart(); //End of Init Phase

        telemetry.addData("Prop Position", redPropThreshold.getPropPosition());
        telemetry.update();

        //Start timer
        ElapsedTime Time = new ElapsedTime();
        Time.reset();
        //move toward rand line

        //Once at line open intake (drop 1st pixel)
            //use camera to detect line?
            //else just use presets

        //Turn to backdrop

        //switch(randInt) {
        // line up with randint apriltag
            //based on randint go to a preset position until can see apriltag,
            // then align using apriltag instead of encoders

        //extend claw into backdrop, use touch sensor to stop at backdrop
            //give target and then slowly extend until touch


        ///////////////////
        // CYCLES //////
        ///////////////////

        //while time above x {
        //      cycle
        //}
        //while (Time.milliseconds() <= time) {
        //}
        //park








    }
}