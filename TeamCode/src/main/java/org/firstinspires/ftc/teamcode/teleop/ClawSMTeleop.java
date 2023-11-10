package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class ClawSMTeleop extends LinearOpMode{
    public enum STATE {
        CLAW_OPEN,
        CLAW_CLOSE

    }

    public enum EVENT {
        DRIVER_PRESSED_2TL,
        DRIVER_PRESSED_2TR
    }

    STATE currentState;
    EVENT currentEvent;

    public void transition(EVENT event) {
        switch (event) {
            case DRIVER_PRESSED_2TL:
                currentState = STATE.CLAW_OPEN;
                break;

            case DRIVER_PRESSED_2TR:
                currentState = STATE.CLAW_CLOSE;
                break;
        }
    }

    public void update() {
        switch (currentState) {
            case CLAW_OPEN:
                break;
            case CLAW_CLOSE:
                break;
        }
    }
    public void runOpMode(){
            waitForStart();

            while(opModeIsActive()) {
                if(gamepad2.left_trigger > 0){
                    transition(EVENT.DRIVER_PRESSED_2TL);
                    telemetry.addData("Left Trigger Pressed, Claw Open","Claw Open");
                    telemetry.update();
                }
                else if(gamepad2.right_trigger > 0){
                    transition(EVENT.DRIVER_PRESSED_2TR);
                    telemetry.addData("Right Trigger Pressed, Claw Closed","Claw Closed");
                    telemetry.update();
                }
            }
    }
}