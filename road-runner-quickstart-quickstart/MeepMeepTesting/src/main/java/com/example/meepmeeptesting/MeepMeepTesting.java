package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Random;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        Pose2d redTop = new Pose2d(12.5,-60, Math.toRadians(-90));
        Pose2d redBot = new Pose2d(-35,-60, Math.toRadians(-90));
        Pose2d blueTop = new Pose2d(12.5,60, Math.toRadians(90));
        Pose2d blueBot = new Pose2d(-35,60, Math.toRadians(90));

        Random random = new Random();
        int randInt = random.nextInt(3);
        final Pose2d spikePose = randInt == 0 ? new Pose2d(-40, -45, Math.toRadians(-90)) : randInt == 2 ? new Pose2d(-40,-35,Math.toRadians(180)) : new Pose2d( -33, -40, Math.toRadians(-90));
        MeepRRPoses poses = new MeepRRPoses(2);



        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50.46434527240892, 50.46434527240892, Math.toRadians(143.45000439028763), Math.toRadians(143.45000439028763), 11.92)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(poses.blueTop)
                                .setReversed(true)
                                .splineToLinearHeading(poses.blueTopSpike, Math.toRadians(0))


                                .setReversed(false)
                                .lineToLinearHeading(poses.blueDrop)



                                .setReversed(true)
                                .splineToLinearHeading(poses.blueTopPark, 0)


                                /*
                                //BlueTop
                                .lineToLinearHeading(new Pose2d(50,42 - random.nextInt(3)*8,Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(random.nextInt(3)*11.5 + 18, 26))

                                /*
                                //RedBot
                                .lineToLinearHeading(spikePose)
                                .lineToLinearHeading(new Pose2d(-35,-35, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(50,-50 + randInt*8,Math.toRadians(0)))
                                 */


                                /*
                                // RedTop
                                .lineToLinearHeading(new Pose2d(50,-50 + random.nextInt(3)*8,Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(random.nextInt(3)*11.5 + 15, -16))
                                */

                                /*
                                //Red Parking
                                .lineToLinearHeading(new Pose2d(36,-55, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(60,-60))


                                //BlueParking
                                .lineToLinearHeading(new Pose2d(36,55, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(60,60))
                                */
                                //.lineToLinearHeading(new Pose2d(60,-60,Math.toRadians(180)))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}