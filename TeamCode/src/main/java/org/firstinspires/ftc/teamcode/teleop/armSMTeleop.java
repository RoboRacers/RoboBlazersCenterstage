package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.SM_Test.armSM;

public class armSMTeleop extends LinearOpMode {
    public enum STATE {
        ARM_FORWARD,
        ARM_BACKWARD,
        ARM_LINK1_FORWARD,
        ARM_LINK1_BACK
    }

    public enum EVENT {
        TWO_LJ_DOWN,
        TWO_LJ_UP,
        TWO_RJ_UP,
        TWO_RJ_DOWN
    }

    STATE currentState;
    EVENT currentEvent;

    public armSMTeleop.STATE getState() {
        return currentState;
    }

    public void transition(EVENT event) {
        switch (event) {
            case TWO_LJ_DOWN:
                currentState = armSMTeleop.STATE.ARM_FORWARD;
                break;
            case TWO_LJ_UP:
                currentState = armSMTeleop.STATE.ARM_BACKWARD;
                break;
            case TWO_RJ_UP:
                currentState = armSMTeleop.STATE.ARM_LINK1_FORWARD;
                break;
            case TWO_RJ_DOWN:
                currentState = armSMTeleop.STATE.ARM_LINK1_BACK;
                break;
        }
    }

    public void update(){
        switch (currentState) {
            case ARM_FORWARD:
                break;
            case ARM_BACKWARD:
                break;
            case ARM_LINK1_FORWARD:
                break;
            case ARM_LINK1_BACK:
                break;
        }
    }

    public void runOpMode() {
        waitForStart();

        while(opModeIsActive()) {
            if(gamepad2.left_stick_y < 0 ){
                transition(armSMTeleop.EVENT.TWO_LJ_DOWN);
                telemetry.addData("Left Trigger Pressed, Claw Open","Claw Open");
                telemetry.update();
            }
            else if(gamepad2.left_stick_y > 0){
                transition(armSMTeleop.EVENT.TWO_LJ_UP);
                telemetry.addData("Right Trigger Pressed, Claw Closed","Claw Closed");
                telemetry.update();
            }
            else if(gamepad2.right_trigger > 0){
                transition(armSMTeleop.EVENT.TWO_RJ_UP);
                telemetry.addData("Right Trigger Pressed, Claw Closed","Claw Closed");
                telemetry.update();
            }
            else if(gamepad2.right_trigger > 0){
                transition(armSMTeleop.EVENT.TWO_RJ_DOWN);
                telemetry.addData("Right Trigger Pressed, Claw Closed","Claw Closed");
                telemetry.update();
            }
        }
    }
}
