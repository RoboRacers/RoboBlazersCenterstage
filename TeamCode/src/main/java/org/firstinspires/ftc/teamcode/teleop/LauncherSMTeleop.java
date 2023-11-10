package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class LauncherSMTeleop extends LinearOpMode{
    public enum STATE {
        DRONE_LAUNCHED,
    }

    public enum EVENT {
        DRIVER_PRESSED_A_AND_DPAD_UP,
    }

    STATE currentState;
    EVENT currentEvent;

    public void transition(EVENT event) {
        switch (event) {
            case DRIVER_PRESSED_A_AND_DPAD_UP:
                currentState = STATE.DRONE_LAUNCHED;
                break;
        }
    }

    public void update() {
        switch (currentState) {
            case DRONE_LAUNCHED:
                break;
        }
    }
    public void runOpMode(){
        waitForStart();

        while(opModeIsActive()) {
            if(gamepad2.a && gamepad2.dpad_up){
                transition(EVENT.DRIVER_PRESSED_A_AND_DPAD_UP);
                telemetry.addData("A and dpad up is pressed, Drone Launched","Drone Launched");
                telemetry.update();
            }
        }
    }
}