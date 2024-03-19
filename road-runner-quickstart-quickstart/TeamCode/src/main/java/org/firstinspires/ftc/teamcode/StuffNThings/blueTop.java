//package org.firstinspires.ftc.teamcode.StuffNThings;
//
//
//import static java.lang.Thread.sleep;
//
//import android.util.Size;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//
//import org.firstinspires.ftc.teamcode.StuffNThings.opencv;
//
//import org.firstinspires.ftc.vision.VisionPortal;
//import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
//import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
//
//import java.util.List;
//
//
//@Autonomous(name = "Blue Top", group = "Auto")
//public class blueTop extends LinearOpMode {
//
//
//    opencv c = new opencv();
//    VisionPortal portal;
//    AprilTagProcessor aprilTag;
//    RedPropThreshold redPropThreshold;
//
//    BluePropThreshold bluePropThreshold;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        //Init Phase
//        r.init_robot(this);
//
//
//        //Start Cam Build
//        portal = new VisionPortal.Builder()
//                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
//                .setCameraResolution(new Size(640, 480))
//                .setCamera(BuiltinCameraDirection.BACK)
//                .addProcessor(redPropThreshold)
//                .build();
//
//        //AprilTag Builder
//        aprilTag = new AprilTagProcessor.Builder()
//                .setDrawAxes(true)
//                .setDrawCubeProjection(true)
//                .setDrawTagOutline(true)
//                .setDrawTagID(true)
//                .build();
//
//
//
//        // Timer stuff
//        int maxTime = 30;
//
//        //camera stuff
//
//        int backSlot = 0;
//        boolean blue = true;
//        try {
//            if (redPropThreshold.outStr != "null" || bluePropThreshold.outStr != "null") {
//                if (blue) {
//                    switch (bluePropThreshold.outStr) {
//                        case ("left"):
//                            if (blue) {
//                                backSlot = 1;
//                            } else {
//                                backSlot = 4;
//                            }
//                            break;
//                        case ("middle"):
//                            if (blue) {
//                                backSlot = 2;
//                            } else {
//                                backSlot = 5;
//                            }
//                            break;
//                        case ("right"):
//                            if (blue) {
//                                backSlot = 3;
//                            } else {
//                                backSlot = 6;
//                            }
//                            break;
//                    }
//                } else if (!blue) {
//                    switch (redPropThreshold.outStr) {
//                        case ("left"):
//                            if (blue) {
//                                backSlot = 1;
//                            } else {
//                                backSlot = 4;
//                            }
//                            break;
//                        case ("middle"):
//                            if (blue) {
//                                backSlot = 2;
//                            } else {
//                                backSlot = 5;
//                            }
//                            break;
//                        case ("right"):
//                            if (blue) {
//                                backSlot = 3;
//                            } else {
//                                backSlot = 6;
//                            }
//                            break;
//                    }
//                }
//
//            }
//        } catch (Exception E) {
//            telemetry.addData("NUll!", 0);
//        }
//
//        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
//        telemetry.addData("# AprilTags Detected", currentDetections.size());
//        try {
//            telemetry.addData("Red??", redPropThreshold.outStr);
//            telemetry.addData("Blue??", bluePropThreshold.outStr);
//        } catch (Exception E) {
//        }
//        telemetry.update();
//
//        waitForStart(); //End of Init Phase
//
//
//
//
//
//        //Start timer
//        ElapsedTime Time = new ElapsedTime();
//        Time.reset();
//        //move toward rand line
//        if (backSlot == 1 || backSlot == 4){
//
//            r.goInches(14, BACKWARD, .8);
//            r.goInches(11, RIGHT, .5);
//
//        } else if (backSlot == 2 || backSlot == 5){
//
//            r.goInches(25, BACKWARD, .8);
//
//        } else if (backSlot == 3 || backSlot == 6){
//
//            r.goInches(14, BACKWARD, .8);
//            r.goInches(11, LEFT, .5);
//
//        }
//
//
//        //Once at line open intake (drop 1st pixel)
//            //use camera to detect line?
//            //else just use presets
//            //HAVE TO TURN 180 DEG
//
//            r.tweet.setPosition(1);
//            sleep(1000);
//            r.tweet.setPosition(0);
//
//        //Turn to backdrop
//
//        //switch(randInt) {
//        // line up with randint apriltag
//            //based on randint go to a preset position until can see apriltag,
//            // then align using apriltag instead of encoders
//        if (backSlot == 1 || backSlot == 4){
//
//            r.goInches(45, BACKWARD, .8);
//            r.goInches(5, RIGHT, .5);
//
//        } else if (backSlot == 2 || backSlot == 5){
//        //45 close 95 far
//            r.goInches(45, BACKWARD, .8);
//
//        } else if (backSlot == 3 || backSlot == 6){
//
//            r.goInches(45, BACKWARD, .8);
//            r.goInches(5, LEFT, .5);
//
//        }
//        /* commented until we are able to align with april tags
//        if (!aprilTag.getDetections().isEmpty()){
//            for (AprilTagDetection detection : aprilTag.getDetections()){
//                if (detection.id == 1 || detection.id == 4){
//
//                }
//            }
//
//         */
//
//
//
//        }
//
//        //extend claw into backdrop, use touch sensor to stop at backdrop
//            //give target and then slowly extend until touch
//
//
//        ///////////////////
//        // CYCLES //////
//        ///////////////////
//
//        //while time above x {
//        //      cycle
//        //}
//        //while (Time.milliseconds() <= time) {
//        //}
//        //park
//
//        //BASICS
//        /*
//        if (blue) {
//            r.goInches(1, RIGHT, .9);
//
//        } else {
//            r.goInches(1, RIGHT, .9);
//
//        }
//        r.goInches(25, FORWARD, .9);
//        sleep(5000);
//
//         */
//
//
//
//
//    }
