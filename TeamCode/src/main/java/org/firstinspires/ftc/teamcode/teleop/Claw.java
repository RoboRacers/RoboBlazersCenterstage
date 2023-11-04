package org.firstinspires.ftc.teamcode.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="claw", group="SM OpMode")
public class Claw extends LinearOpMode {
    Servo claw_servo;
    public void runOpMode(){

        waitForStart();

        while (opModeIsActive()) {

            boolean previousb = false;
            boolean press = false;

            if(press == false && gamepad1.b){


            }

        }
    }
}
