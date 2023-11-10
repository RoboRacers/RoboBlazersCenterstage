package org.firstinspires.ftc.teamcode.modules.RobotParts;

import org.firstinspires.ftc.teamcode.modules.RobotParts.Arm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Arm Test", group = "Arm")
public class ArmClassTest extends LinearOpMode {

    DcMotorEx armMotor;
    Servo linkMotor;
    Servo clawMotor;

    Arm myArm;

    @Override
    public void runOpMode() throws InterruptedException {

        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        linkMotor = hardwareMap.get(Servo.class, "linkMotor");
        clawMotor = hardwareMap.get(Servo.class, "clawMotor");

        myArm = new Arm(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                myArm.moveArmForward(-0.2); // Use myArm instance to call the method
            } else if (gamepad1.dpad_down) {
                myArm.moveArmBackward(0.2); // Use myArm instance to call the method
            }
            telemetry.addData("Power is %f", armMotor.getPower());
            telemetry.update();

            if (gamepad1.left_bumper) {
                myArm.clawOpen(); // Use myArm instance to call the method
            } else if (gamepad1.right_bumper) {
                myArm.clawClose(); // Use myArm instance to call the method
            }
        }
    }
}
