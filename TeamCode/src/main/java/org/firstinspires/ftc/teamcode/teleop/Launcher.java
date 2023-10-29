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

        waitForStart();
        while(opModeIsActive()) {
                launcher.setPower(0.1);
                telemetry.addData("Launching", "Shreesh");
                telemetry.update();
        }
    }
}