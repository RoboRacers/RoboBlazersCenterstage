package org.firstinspires.ftc.teamcode.modules.drive.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.util.NanoClock;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@TeleOp(name = "Arm Test", group = "Arm")
public class ArmTest extends LinearOpMode {

    DcMotorEx armMotor;


    @Override
    public void runOpMode() throws InterruptedException {
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.dpad_up){
                moveArmUp(-0.2);
            } else if (gamepad1.dpad_down) {
                moveArmDown(0.2);
            }
            telemetry.addData("Power is %f", armMotor.getPower());
            telemetry.update();
        }
    }

    public void moveArmUp(double power){
        if (power > 0){
            telemetry.addData("Power is WRONG", power);
            return;
        } else if (power <= 0) {
            armMotor.setPower(power);
        }
        telemetry.update();
    }

    public void moveArmDown(double power){
        if (power < 0){
            telemetry.addData("Power is WRONG", power);
            return;
        } else if (power >= 0) {
            armMotor.setPower(power);
        }
        telemetry.update();
    }

}
