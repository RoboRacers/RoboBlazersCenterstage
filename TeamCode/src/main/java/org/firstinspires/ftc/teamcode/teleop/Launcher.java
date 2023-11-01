package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="launcher", group="SM OpMode")
public class Launcher extends LinearOpMode{
    DcMotorEx launcher;
    ///Servo push;
    boolean press = false;

    public void runOpMode(){
        launcher = hardwareMap.get(DcMotorEx.class, "launcher");
        //push = hardwareMap.get(Servo.class, "push");

        boolean previousa = false;

        waitForStart();
        while(opModeIsActive()) {
            if(gamepad1.a && press == false) {
                launcher.setPower(0.1);
                telemetry.addData("Launching", "Shreesh");
                telemetry.update();
                press = true;
                previousa = true;
            }
            else if(gamepad1.a && press == true && previousa == true){
                launcher.setPower(0);
                telemetry.addData("Stopping Launch", "Shreesh");
                telemetry.update();
                press = false;
            }

            previousa = gamepad1.a;

        }
    }
}