//package org.firstinspires.ftc.teamcode.StuffNThings.Auto;
//
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//import org.firstinspires.ftc.teamcode.StuffNThings.RRPoses;
//import org.firstinspires.ftc.teamcode.StuffNThings.hardware;
//import org.firstinspires.ftc.teamcode.StuffNThings.opencvTest;
//import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
//
//@Autonomous(name = "blueBot", group = "Auto")
//public class blueBot extends LinearOpMode {
//
//    RRPoses poses = new RRPoses(0);
//
//    hardware r = new hardware();
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        SampleMecanumDrive mecDrive = new SampleMecanumDrive(hardwareMap);
//
//        r.init_robot(this);
//        for (DcMotor motor: r.all) {
//            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            motor.setTargetPosition(0);
//            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        }
//
//        r.clawServo.setPosition(.8);
//        r.outTakeS.setPosition(1);
//
//        opencvTest opencv = new opencvTest();
//
//        opencv.initOpenCV(false);
//        FtcDashboard dashboard = FtcDashboard.getInstance();
//        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
//        FtcDashboard.getInstance().startCameraStream(opencv.controlHubCam, 30);
//
//
//        while (!isStopRequested() && !isStarted()) {
//            telemetry.addData("Coordinate", "(" + (int) opencv.cX + ", " + (int) opencv.cY + ")");
//            if (opencv.cX < 220) {
//                telemetry.addLine("Left");
//                opencv.randInt = 0;
//            } else if (opencv.cX < 440) {
//                telemetry.addLine("Middle");
//                opencv.randInt = 1;
//            } else telemetry.addLine("Right");
//            opencv.randInt = 2;
//            telemetry.update();
//        }
//
//
//        RRPoses poses  = new RRPoses(opencv.randInt);
//
//        TrajectorySequence traj1 = mecDrive.trajectorySequenceBuilder(poses.blueBot)
//                .setReversed(true)
//                .lineToLinearHeading(poses.blueBotSpike)
//
//                .addDisplacementMarker(() -> {
//                    r.inLeft.setTargetPosition(70);
//                    r.inRight.setTargetPosition(-70);
//
//                    while (r.inLeft.getCurrentPosition() < r.inLeft.getTargetPosition()) {
//                        r.inLeft.setPower(.5);
//                        r.inRight.setPower(.5);
//                    }
//                    r.inLeft.setPower(.0);
//                    r.inRight.setPower(.0);
//                    r.clawServo.setPosition(.6);
//                    sleep(2000);
//
//                    r.inLeft.setTargetPosition(0);
//                    r.inRight.setTargetPosition(0);
//
//                    while (r.inLeft.getCurrentPosition() > r.inLeft.getTargetPosition()) {
//                        r.inLeft.setPower(.5);
//                        r.inRight.setPower(.5);
//                    }
//                    r.inLeft.setPower(.0);
//                    r.inRight.setPower(.0);
//                    r.clawServo.setPosition(.8);
//
//                })
//
//                .setReversed(false)
//                .turn(Math.toRadians(-90))
//                .lineToLinearHeading(poses.blueDrop)
//                .addDisplacementMarker(() -> {
//                r.outMain.setTargetPosition(1000);
//
//                while (r.outMain.getCurrentPosition() < r.outMain.getTargetPosition()+50) {
//                    r.outMain.setPower(.5);
//                }
//                r.outMain.setPower(.0);
//                r.outTakeS.setPosition(0);
//
//                r.outMain.setTargetPosition(0);
//
//                while (r.outMain.getCurrentPosition() > r.outMain.getTargetPosition()+50) {
//                    r.outMain.setPower(.5);
//                }
//                r.outMain.setPower(.0);
//
//                r.outTakeS.setPosition(1);
//
//                })
//
//                .setReversed(true)
//                .splineToLinearHeading(poses.blueBotPark, 0)
//
//                .build();
//
//
//
//
//        waitForStart();
//
//        mecDrive.setPoseEstimate(poses.blueBot);
//        mecDrive.followTrajectorySequence(traj1);
//
//
//
//
//    }
//}
