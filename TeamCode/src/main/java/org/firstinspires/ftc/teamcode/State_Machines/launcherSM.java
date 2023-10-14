package org.firstinspires.ftc.teamcode.State_Machines;

public class launcherSM {

    public enum STATE {
        DRONE_LAUNCHER_LOADED,
        DRONE_LAUNCHED,
    }

    public enum EVENT {
        GAME_START,
        DRONE_LAUNCH_BUTTON_PRESSED,
    }

    STATE currentState;

    public STATE getState() {
        return currentState;
    }

    public void transition(EVENT event) {
        switch (event) {
            case GAME_START:
                currentState = STATE.DRONE_LAUNCHER_LOADED;
                break;
            case DRONE_LAUNCH_BUTTON_PRESSED:
                currentState = STATE.DRONE_LAUNCHED;
                break;
        }
    }
}
