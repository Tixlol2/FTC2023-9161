package org.firstinspires.ftc.teamcode.StuffNThings;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Config
@Autonomous(name = "Playground", group = "Auto")
public class Playground extends LinearOpMode {

    public static int x1 = 20;
    public static int y1 = 20;
    public static int ang1 = 90;

    public static int x = 20;
    public static int y = 20;
    public static int ang = 90;
    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive mecDrive = new SampleMecanumDrive(hardwareMap);

        Trajectory traj1 = mecDrive.trajectoryBuilder(new Pose2d(12.5,-60, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(50,-50 + 0*8,Math.toRadians(0)))
                .build();

        Trajectory traj2 = mecDrive.trajectoryBuilder(traj1.end())
                .lineToConstantHeading(new Vector2d(0*11.5 + 15, -16))
                .build();


        /*
        Trajectory traj1 = mecDrive.trajectoryBuilder(new Pose2d(-35,-60, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-40, -45, Math.toRadians(-90)))

                .build();

        Trajectory traj2 = mecDrive.trajectoryBuilder(traj1.end())
                .lineToLinearHeading(new Pose2d(-35,-30, Math.toRadians(0)))
                .build();

        Trajectory traj3 = mecDrive.trajectoryBuilder(traj2.end())
                .lineToLinearHeading(new Pose2d(50,-35 + 0*8,Math.toRadians(0)))
                .build();
        */
        Trajectory traj4 = mecDrive.trajectoryBuilder(traj2.end())
                .lineToLinearHeading(new Pose2d(36,-55, Math.toRadians(0)))
                .build();

        Trajectory traj5 = mecDrive.trajectoryBuilder(traj4.end())
                .lineToConstantHeading(new Vector2d(60,-60))
                .build();






        waitForStart();

        mecDrive.setPoseEstimate(new Pose2d(12.5,-60, Math.toRadians(-90)));
        mecDrive.followTrajectory(traj1);
        mecDrive.followTrajectory(traj2);


        //mecDrive.followTrajectory(traj3);
        mecDrive.followTrajectory(traj4);
        mecDrive.followTrajectory(traj5);




    }
}
