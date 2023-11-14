package org.firstinspires.ftc.teamcode.modules.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.RobotParts.Arm;
import org.firstinspires.ftc.teamcode.modules.drive.SampleMecanumDrive;

/**
 * This is a simple teleop routine for testing localization. Drive the robot around like a normal
 * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
 * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
 * exercise is to ascertain whether the localizer has been configured properly (note: the pure
 * encoder localizer heading may be significantly off if the track width has not been tuned).
 */

@TeleOp(group = "drive")
public class LocalizationTest extends LinearOpMode {


    DcMotorEx armMotor;
    Servo linkMotor;
    Servo clawMotor;
    Servo droneLauncher;
    Arm myArm;

    Telemetry mytelemetry;

    @Override
    public void runOpMode() throws InterruptedException {
        myArm = new Arm(hardwareMap, telemetry);
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        linkMotor = hardwareMap.get(Servo.class, "linkMotor");
        clawMotor = hardwareMap.get(Servo.class, "clawMotor");
        droneLauncher = hardwareMap.get(Servo.class, "droneMotor");
        mytelemetry = telemetry;

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            gamepad1.left_stick_x, //imperfect strafing fix, must be tuned for new drivetrain
                            gamepad1.right_stick_x
                    )
            );

            drive.update();

            Pose2d poseEstimate = drive.getPoseEstimate();
//            telemetry.addData("x", poseEstimate.getX());
//            telemetry.addData("y", poseEstimate.getY());
//            telemetry.addData("heading", poseEstimate.getHeading());
//            telemetry.update();


            if (gamepad2.dpad_up) {
                myArm.moveArmForward(-0.2);
                mytelemetry.addData("Current position of ARM is ",armMotor.getCurrentPosition() );
                mytelemetry.update();
                // Use myArm instance to call the method
            } else if (gamepad2.dpad_down) {
                myArm.moveArmBackward(0.2); // Use myArm instance to call the method
                mytelemetry.addData("Current position of ARM is ",armMotor.getCurrentPosition() );
                mytelemetry.update();
            }
            else if (gamepad2.a){
                myArm.moveArmForward(0);
            }
//            telemetry.addData("Power is %f", armMotor.getPower());
//            telemetry.update();

            if (gamepad2.right_bumper) {
                myArm.moveLinkPickUp(); // Use myArm instance to call the method
            } else if (gamepad2.left_bumper) {
                myArm.moveLinkDrop(); // Use myArm instance to call the method
            }

            if (gamepad2.dpad_left){
                myArm.clawOpen();
            } else if (gamepad2.dpad_right) {
                myArm.clawClose();

            }

            if (gamepad2.b && gamepad2.y){
                myArm.launch();
            }

            if (gamepad2.x){
                mytelemetry.addData("The pos is ", armMotor.getCurrentPosition());
                mytelemetry.update();
            }

        }
    }
}
