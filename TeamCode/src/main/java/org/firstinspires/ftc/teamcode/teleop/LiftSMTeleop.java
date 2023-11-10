package org.firstinspires.ftc.teamcode.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class LiftSMTeleop extends LinearOpMode{
    public enum STATE {
        LIFT_UP,
    }

    public enum EVENT {
        A_1DPAD_Up,
    }

    STATE currentState;
    EVENT currentEvent;

    public void transition(EVENT event) {
        switch (event) {
            case A_1DPAD_Up:
                currentState = STATE.LIFT_UP;
                break;
        }
    }

    public void update() {
        switch (currentState) {
            case LIFT_UP:
                break;
        }
    }
    public void runOpMode(){
        waitForStart();

        while(opModeIsActive()) {
            if(gamepad1.a && gamepad1.dpad_up){
                transition(EVENT.A_1DPAD_Up);
                telemetry.addData("A and dpad up is pressed, Lift Up","Lift Up");
                telemetry.update();
            }
        }
    }
}
