package org.firstinspires.ftc.teamcode.modules.RobotParts;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Arm Test", group = "Arm")
public class Arm extends LinearOpMode {

    DcMotorEx armMotor;
    Servo linkMotor;

    Servo clawMotor;


    @Override
    public void runOpMode() throws InterruptedException {

        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        linkMotor = hardwareMap.get(Servo.class,"linkMotor");
        clawMotor = hardwareMap.get(Servo.class,"clawMotor");


        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.dpad_up){
                moveArmForward(-0.2);
            } else if (gamepad1.dpad_down) {
                moveArmBackward(0.2);
            }
            telemetry.addData("Power is %f", armMotor.getPower());
            telemetry.update();

            if (gamepad1.left_bumper){
                clawOpen();
            } else if (gamepad1.right_bumper) {
                clawClose();
            }

        }
    }

    public void moveArmForward(double power){
        if (power > 0){
            telemetry.addData("Power is WRONG", power);
            return;
        } else if (power <= 0) {
            armMotor.setPower(power);
        }
        telemetry.update();
    }

    public void moveArmBackward(double power){
        if (power < 0){
            telemetry.addData("Power is WRONG", power);
            return;
        } else if (power >= 0) {
            armMotor.setPower(power);
        }
        telemetry.update();
    }

    public void moveLinkPickUp(){
        linkMotor.setPosition(0);
    }
    public void moveLinkDrop(){
        linkMotor.setPosition(1);
    }
    public void clawOpen(){
        clawMotor.setPosition(0.75);
    }
    public void clawClose(){
        clawMotor.setPosition(0.25);
    }

}
