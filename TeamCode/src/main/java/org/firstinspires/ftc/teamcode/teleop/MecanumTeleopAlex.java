package org.firstinspires.ftc.teamcode.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MechTest", group = "16481-Power-Play")
public class MecanumTeleopAlex extends LinearOpMode {


    DcMotor RFMotor;
    DcMotor RBMotor;
    DcMotor LFMotor;
    DcMotor LBMotor;

    public void DriveTrainMove () {
        double vertMove;
        double horiMove;
        double pivot;
        vertMove = gamepad1.left_stick_y;
        horiMove = gamepad1.left_stick_x;
        pivot = gamepad1.right_stick_x;

        RFMotor.setPower(pivot + (-vertMove + horiMove));
        RBMotor.setPower(pivot + (-vertMove - horiMove));
        LFMotor.setPower(pivot + (-vertMove - horiMove));
        LBMotor.setPower(pivot + (-vertMove + horiMove));
    }


    @Override
    public void runOpMode() throws InterruptedException {

        RFMotor = hardwareMap.get(DcMotor.class,"Fr");
        RBMotor = hardwareMap.get(DcMotor.class,"Br");
        LFMotor = hardwareMap.get(DcMotor.class,"Fl");
        LBMotor = hardwareMap.get(DcMotor.class,"Bl");

        while (opModeInInit()) {

        }

        while (!isStopRequested()) {

        }
    }


}
