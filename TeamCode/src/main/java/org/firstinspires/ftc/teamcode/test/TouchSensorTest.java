package org.firstinspires.ftc.teamcode.test;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.RobotCore;

@TeleOp(name = "Touch Sensor test", group = "new")
public class TouchSensorTest extends LinearOpMode {


    RevTouchSensor touchSensor;
    static boolean touchIsPressed;

    @Override
    public void runOpMode() throws InterruptedException {

        touchSensor = hardwareMap.get(RevTouchSensor.class, "touch1");

        while (opModeInInit()) {

        }

        while (!isStopRequested()) {

            touchIsPressed = touchSensor.isPressed();

            telemetry.addData("Touch sensor pressed", touchIsPressed);
            telemetry.update();
        }
    }
}
