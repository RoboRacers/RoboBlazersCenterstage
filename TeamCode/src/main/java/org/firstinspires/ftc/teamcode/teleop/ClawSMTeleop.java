package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class ClawSMTeleop extends LinearOpMode{
    public enum STATE {
        DRIVER_PRESSED_2TL,
        DRIVER_PRESSED_2TR
    }

    public enum EVENT {
        CLAW_OPEN,
        CLAW_CLOSE
    }

    STATE currentState;
    EVENT currentEvent;

    public void transition(EVENT event) {
        switch (event) {
            case CLAW_OPEN:
                currentState = STATE.DRIVER_PRESSED_2TL;
                break;

            case CLAW_CLOSE:
                currentState = STATE.DRIVER_PRESSED_2TR;
                break;
        }
    }

    public void update() {
        switch (currentState) {
            case DRIVER_PRESSED_2TL:
                break;
            case DRIVER_PRESSED_2TR:
                break;
        }
    }
    public void runOpMode(){
            waitForStart();

            while(opModeIsActive()) {
                if(gamepad2.left_trigger > 0){
                    transition(EVENT.CLAW_OPEN);
                    telemetry.addData("Left Trigger Pressed, Claw Open","Claw Open");
                    telemetry.update();
                }
                else if(gamepad2.right_trigger > 0){
                    transition(EVENT.CLAW_CLOSE);
                    telemetry.addData("Right Trigger Pressed, Claw Closed","Claw Closed");
                    telemetry.update();
                }
            }
    }
}