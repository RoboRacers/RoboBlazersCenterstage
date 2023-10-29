package org.firstinspires.ftc.teamcode.autonomous.SM_Test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Launcher extends LinearOpMode{
    DcMotorEx launcher;
    boolean press = false;

    public void runOpMode(){
        launcher = hardwareMap.get(DcMotorEx.class, "launcher");
        while(opModeIsActive()) {
            if (gamepad1.a && press == false) {
                launcher.setVelocity(100);
                boolean press = true;
                telemetry.addData("Launching", "Shreesh");
            }
            if (gamepad1.a && press == true) {
                launcher.setVelocity(0);
                boolean press = false;
                telemetry.addData("Stopped", "Shreesh");
            }
            telemetry.update();
        }
    }
}