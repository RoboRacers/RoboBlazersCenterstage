package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class LauncherSMTeleop extends LinearOpMode{
    public enum STATE {
        DRIVER_PRESSED_A_AND_DPAD_UP,
    }

    public enum EVENT {
        DRONE_LAUNCHED,
    }

    STATE currentState;
    EVENT currentEvent;

    public void transition(EVENT event) {
        switch (event) {
            case DRONE_LAUNCHED:
                currentState = STATE.DRIVER_PRESSED_A_AND_DPAD_UP;
                break;
        }
    }

    public void update() {
        switch (currentState) {
            case DRIVER_PRESSED_A_AND_DPAD_UP:
                break;
        }
    }
    public void runOpMode(){
        waitForStart();

        while(opModeIsActive()) {
            if(gamepad2.a && gamepad2.dpad_up){
                transition(EVENT.DRONE_LAUNCHED);
                telemetry.addData("A and dpad up is pressed, Drone Launched","Drone Launched");
                telemetry.update();
            }
        }
    }
}