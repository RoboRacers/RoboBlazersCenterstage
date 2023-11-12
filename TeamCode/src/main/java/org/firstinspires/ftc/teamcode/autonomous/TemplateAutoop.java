package org.firstinspires.ftc.teamcode.autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.modules.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.modules.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RobotCore;
import org.firstinspires.ftc.teamcode.modules.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

// Localization is doesn't show drift, follower if it does

@Config
@Autonomous(name = "Robotblazers path test", group = "23692")
public class TemplateAutoop extends LinearOpMode {

    boolean finished = false;
    public SampleMecanumDrive drive;
    @Override
    public void runOpMode() {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

TrajectoryVelocityConstraint slowCont = new MinVelocityConstraint(Arrays.asList(
        new TranslationalVelocityConstraint(0.2),
        new AngularVelocityConstraint(1)));

        // RobotCore robot = new RobotCore(hardwareMap);
        TrajectorySequence traj2 = drive.trajectorySequenceBuilder(
                new Pose2d(29.80, 40.70, Math.toRadians(-86.21)))
                .lineTo( new Vector2d(34.80, 40.70),
                //.setVelConstraint(slowCont)
                //SampleMecanumDrive.getVelocityConstraint(5, DriveConstants.MAX_ANG_VEL,DriveConstants.TRACK_WIDTH),
                SampleMecanumDrive.getVelocityConstraint(5, DriveConstants.MAX_ANG_VEL,DriveConstants.TRACK_WIDTH),
                SampleMecanumDrive.getAccelerationConstraint(5))
                .build();

        while(!isStopRequested() && !opModeIsActive()) {

        }
        waitForStart();
        if (isStopRequested()) return;
        drive.followTrajectorySequence(traj2);
    }

}
