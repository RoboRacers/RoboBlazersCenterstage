package org.firstinspires.ftc.teamcode.teleop;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.RobotCore;

@TeleOp(name = "Claw Test", group = "16481-Centerstage")
public class ClawTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Servo claw;
        claw = hardwareMap.get(Servo.class, "claw");
        TouchSensor touch;
        touch = hardwareMap.touchSensor.get("touch_sensor");
        boolean ifPressed= true;


        boolean isOpen = false;


        while (opModeInInit()) {
        }

        while (!isStopRequested()) {


            telemetry.addLine("the servo is at pos"+claw.getPosition());


            if (touch.isPressed() == false && ifPressed == true) {
                telemetry.addLine("Ishaan sold, button is pressed");

                if (claw.getPosition() >=0.5){
                    claw.setPosition(0);

                }

                else if (claw.getPosition() <=0.1){
                    claw.setPosition(0.6);

                }
            }
            telemetry.update();
            ifPressed = touch.isPressed();
        }
    }
}
