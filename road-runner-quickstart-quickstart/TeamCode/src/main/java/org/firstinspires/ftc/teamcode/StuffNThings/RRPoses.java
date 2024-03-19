package org.firstinspires.ftc.teamcode.StuffNThings;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

public class RRPoses {

    private int randInt;
    public RRPoses() {randInt = 0;}


    public RRPoses(int randInt) {
        this.randInt = randInt;
    }

    /////////////////////
    //Starting Poses////
    ///////////////////
    final public Pose2d redTop = new Pose2d(12,-60, Math.toRadians(-90));
    final public Pose2d redBot = new Pose2d(-36,-60, Math.toRadians(-90));
    final public Pose2d blueTop = new Pose2d(12,60, Math.toRadians(90));
    final public Pose2d blueBot = new Pose2d(-36,60, Math.toRadians(90));

    ////////////////////
    //Scoring Poses////
    //////////////////
    final public Pose2d redDrop =
            randInt == 0 ? new Pose2d(48, -29, 0) :
                    randInt == 1 ? new Pose2d( 48, -36, 0) :
                            new Pose2d(48,-43,0);
    final public Pose2d blueDrop =
            randInt == 0 ? new Pose2d(48, 43, 0) :
                    randInt == 1 ? new Pose2d( 48, 36, 0) :
                            new Pose2d(48,29,0);


    /////////////////
    //spike poses///
    ///////////////
    final public Pose2d redTopSpike =
            randInt == 0 ? new Pose2d(14, -24, Math.toRadians(180)) :
                    randInt == 1 ? new Pose2d( 26, -21, Math.toRadians(180)) :
                            new Pose2d(38,-24,Math.toRadians(180));
    final public Pose2d blueTopSpike =
            randInt == 0 ? new Pose2d(38,24,Math.toRadians(0)) :
                    randInt == 1 ? new Pose2d( 26, 21, Math.toRadians(0)) :
                            new Pose2d(14, 24, Math.toRadians(0));

    final public Pose2d redBotSpike =
            randInt == 0 ? new Pose2d(-34, -31, Math.toRadians(0)) :
                    randInt == 1 ? new Pose2d( -33, -38, Math.toRadians(-90)) :
                            new Pose2d(-10,-31,Math.toRadians(0));
    final public Pose2d blueBotSpike =
            randInt == 0 ? new Pose2d(-10,31,Math.toRadians(0)) :
                    randInt == 1 ? new Pose2d( -33, 38, Math.toRadians(90)) :
                            new Pose2d(-34, 31, Math.toRadians(0));


    //parks
    final public Pose2d redTopPark = new Pose2d(48,-12, Math.toRadians(0));
    final public Pose2d redBotPark = new Pose2d(48,-60, Math.toRadians(0));
    final public Pose2d blueTopPark = new Pose2d(48,60, Math.toRadians(0));
    final public Pose2d blueBotPark = new Pose2d(48,12, Math.toRadians(0));
    ////////////////////////
    //Sequences////////////
    //////////////////////


}


