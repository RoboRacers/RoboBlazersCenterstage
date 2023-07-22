package org.firstinspires.ftc.teamcode.teleop;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.Localizer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotCore;
import org.firstinspires.ftc.teamcode.modules.gaeldrive.localization.MonteCarloLocalizer;
import org.firstinspires.ftc.teamcode.modules.gaeldrive.localization.MonteCarloLocalizerTest;

import java.util.List;

@TeleOp(name = "Base Template Teleop", group = "Test")
public class TemplateTeleop extends LinearOpMode {

    double driveSensitivity = .5;
    double turnSensitivity = .75;

    FtcDashboard dashboard;

    @Override
    public void runOpMode() throws InterruptedException {

        RobotCore robot = new RobotCore(hardwareMap);

        long loop;
        long loopTime = 0;


        while (opModeInInit()) {

        }

        while (!isStopRequested()) {

            robot.drive.setWeightedDrivePower(new Pose2d(-gamepad1.left_stick_y*driveSensitivity,-gamepad1.left_stick_x*driveSensitivity, -gamepad1.right_stick_x*turnSensitivity));
            robot.drive.update();

            loop = System.nanoTime();
            telemetry.addData("hz", 1000000000/(loop-loopTime));
            telemetry.update();
            loopTime = loop;

        }
    }
}
