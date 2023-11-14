package org.firstinspires.ftc.teamcode.modules.RobotParts;

import org.firstinspires.ftc.teamcode.modules.RobotParts.Arm;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ArmClassTest extends LinearOpMode {

    DcMotorEx armMotor;
    Servo linkMotor;
    Servo clawMotor;

    Servo droneLauncher;

    Arm myArm;

    @Override
    public void runOpMode() throws InterruptedException {
        myArm = new Arm(hardwareMap, telemetry);
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        linkMotor = hardwareMap.get(Servo.class, "linkMotor");
        clawMotor = hardwareMap.get(Servo.class, "clawMotor");
        droneLauncher = hardwareMap.get(Servo.class, "droneMotor");
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                myArm.moveArmForward(-0.2); // Use myArm instance to call the method
            } else if (gamepad1.dpad_down) {
                myArm.moveArmBackward(0.2); // Use myArm instance to call the method
            }
            else if (gamepad1.a){
                myArm.moveArmForward(0);
            }
            telemetry.addData("Power is %f", armMotor.getPower());
            telemetry.update();

            if (gamepad1.right_bumper) {
                myArm.moveLinkPickUp(); // Use myArm instance to call the method
            } else if (gamepad1.left_bumper) {
                myArm.moveLinkDrop(); // Use myArm instance to call the method
            }

            if (gamepad1.dpad_left){
                myArm.clawOpen();
            } else if (gamepad1.dpad_right) {
                myArm.clawClose();

            }
        }
    }
}
