package org.firstinspires.ftc.teamcode.StuffNThings.Auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.StuffNThings.RRPoses;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

@Autonomous(name = "blueBot",  group = "Auto")

public class blueBot extends LinearOpMode {

    public double cX = 0;
    public double cY = 0;

    public int randInt = 0;

    public OpenCvCamera controlHubCam;  // Use OpenCvCamera class from FTC SDK
    private static final int CAMERA_WIDTH = 800; // width  of wanted camera resolution
    private static final int CAMERA_HEIGHT = 448; // height of wanted camera resolution


    @Override
    public void runOpMode() throws InterruptedException{

        SampleMecanumDrive mecDrive = new SampleMecanumDrive(hardwareMap);






        initOpenCV(true);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        FtcDashboard.getInstance().startCameraStream(controlHubCam, 30);



        while (!isStopRequested() && !isStarted()) {
            telemetry.addData("Coordinate", "(" + (int) cX + ", " + (int) cY + ")");
            if (cX < 400) {telemetry.addLine("Left"); randInt = 0;}
            else if (cX < 800) {telemetry.addLine("Middle"); randInt = 1;}
            else telemetry.addLine("Right"); randInt = 2;
            telemetry.update();

            // The OpenCV pipeline automatically processes frames and handles detection
        }

        RRPoses r = new RRPoses(randInt);




        TrajectorySequence traj1 = mecDrive.trajectorySequenceBuilder(r.redTop)
                .lineToLinearHeading(r.redTopSpike)

                .build();

        // Release resources
        controlHubCam.stopStreaming();

        mecDrive.setPoseEstimate(r.redBot);
        mecDrive.followTrajectorySequence(traj1);


    }




































    public void initOpenCV(boolean blue) {

        // Create an instance of the camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//2131230820
        //telemetry.addData("Thing0", cameraMonitorViewId);
        //telemetry.update();

        // Use OpenCvCameraFactory class from FTC SDK to create camera instance
        controlHubCam = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        controlHubCam.setPipeline(new YellowBlobDetectionPipeline(blue));

        controlHubCam.openCameraDevice();
        controlHubCam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPSIDE_DOWN);
    }

    class YellowBlobDetectionPipeline extends OpenCvPipeline {

        boolean blue = false;

        public YellowBlobDetectionPipeline(boolean blue) {
            this.blue = blue;
        }


        @Override
        public Mat processFrame(Mat input) {

            // Preprocess the frame to detect yellow regions
            Mat yellowMask = preprocessFrame(input);

            // Find contours of the detected yellow regions
            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(yellowMask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

            // Find the largest yellow contour (blob)
            MatOfPoint largestContour = findLargestContour(contours);

            if (largestContour != null) {
                // Draw a red outline around the largest detected object
                Imgproc.drawContours(input, contours, contours.indexOf(largestContour), new Scalar(255, 0, 0), 2);

                // Calculate the centroid of the largest contour
                Moments moments = Imgproc.moments(largestContour);
                cX = moments.get_m10() / moments.get_m00();
                cY = moments.get_m01() / moments.get_m00();

            }

            return input;
        }

        private Mat preprocessFrame(Mat frame) {
            Mat hsvFrame = new Mat();
            Imgproc.cvtColor(frame, hsvFrame, Imgproc.COLOR_BGR2HSV);
            //RED


            Scalar lowerYellow = new Scalar(100, 100, 100);
            Scalar upperYellow = new Scalar(180, 255, 255);

            ///* BLUE
            if (blue) {
                lowerYellow = new Scalar(0, 70, 200);
                upperYellow = new Scalar(90, 255, 255);
            }
            //*/

            Mat yellowMask = new Mat();
            Core.inRange(hsvFrame, lowerYellow, upperYellow, yellowMask);

            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
            Imgproc.morphologyEx(yellowMask, yellowMask, Imgproc.MORPH_OPEN, kernel);
            Imgproc.morphologyEx(yellowMask, yellowMask, Imgproc.MORPH_CLOSE, kernel);

            return yellowMask;
        }

        private MatOfPoint findLargestContour(List<MatOfPoint> contours) {
            double maxArea = 0;
            MatOfPoint largestContour = null;

            for (MatOfPoint contour : contours) {
                double area = Imgproc.contourArea(contour);
                if (area > maxArea) {
                    maxArea = area;
                    largestContour = contour;
                }
            }

            return largestContour;
        }

    }
}