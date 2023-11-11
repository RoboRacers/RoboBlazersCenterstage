package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotCore;
import org.firstinspires.ftc.teamcode.modules.trajectorysequence.TrajectorySequence;

// Localization is doesn't show drift, follower if it does

@Config
@Autonomous(name = "Template Autoop", group = "16481-Power-Play")
public class ExampleAutoop extends LinearOpMode {

    boolean finished = false;

    @Override
    public void runOpMode() {

        RobotCore robot = new RobotCore(hardwareMap);

        TrajectorySequence traj2 = robot.drive.trajectorySequenceBuilder(new Pose2d(12.75, -58.81, Math.toRadians(86.99)))
                .splineTo(new Vector2d(16.44, -40.18), Math.toRadians(168.93))
                .splineTo(new Vector2d(-36.13, -38.77), Math.toRadians(88.26))
                .splineTo(new Vector2d(-30.68, -11.87), Math.toRadians(-0.90))
                .splineTo(new Vector2d(43.87, -33.85), Math.toRadians(8.13))
                .build();



        while(!isStopRequested() && !opModeIsActive()) {

        }

        waitForStart();

        if (isStopRequested()) return;

        robot.drive.followTrajectorySequence(traj2);


    }

}
