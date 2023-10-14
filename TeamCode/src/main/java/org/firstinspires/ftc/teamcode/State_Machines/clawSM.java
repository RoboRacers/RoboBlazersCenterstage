package org.firstinspires.ftc.teamcode.State_Machines;

public class clawSM {

    public enum STATE {
        HAS_START_PY_PIXELS,
        DROPPED_PURPLE_PIXEL,
        DROPPED_YELLOW_PIXEL,
        HAS_WHITE_PIXELS,
        DROPPED_WHITE_PIXEL,
    }

    public enum EVENT {
        GAME_START,
        DETECTED_TEAM_PROP,
        IDENTIFIED_APRIL_TAG,
        REACHED_STARTER_STACK,
        REACHED_BACKDROP,
    }

    STATE currentState;

    public STATE getState() {
        return currentState;
    }

    public void transition(EVENT event) {
        switch (event) {
            case GAME_START:
                currentState = STATE.HAS_START_PY_PIXELS;
                break;
            case DETECTED_TEAM_PROP:
                currentState = STATE.DROPPED_PURPLE_PIXEL;
                break;
            case IDENTIFIED_APRIL_TAG:
                currentState = STATE.DROPPED_YELLOW_PIXEL;
                break;
            case REACHED_STARTER_STACK:
                currentState = STATE.HAS_WHITE_PIXELS;
                break;
            case REACHED_BACKDROP:
                currentState = STATE.DROPPED_WHITE_PIXEL;
                break;
        }
    }

    public void update() {
        switch (currentState) {

        }
    }
}
