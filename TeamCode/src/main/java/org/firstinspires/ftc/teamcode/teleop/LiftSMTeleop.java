package org.firstinspires.ftc.teamcode.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class LiftSMTeleop extends LinearOpMode{
    public enum STATE {
        A_1DPAD_Up,
    }

    public enum EVENT {
        LIFT_UP,
    }

    STATE currentState;
    EVENT currentEvent;

    public void transition(EVENT event) {
        switch (event) {
            case LIFT_UP:
                currentState = STATE.A_1DPAD_Up;
                break;
        }
    }

    public void update() {
        switch (currentState) {
            case A_1DPAD_Up:
                break;
        }
    }
    public void runOpMode(){
        waitForStart();

        while(opModeIsActive()) {
            if(gamepad1.a && gamepad1.dpad_up){
                transition(EVENT.LIFT_UP);
                telemetry.addData("A and dpad up is pressed, Lift Up","Lift Up");
                telemetry.update();
            }
        }
    }
}
