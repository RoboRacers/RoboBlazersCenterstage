package org.firstinspires.ftc.teamcode.autonomous.SM_Test;

public class clawSM {

    public enum STATE {
        HAS_START_PY_PIXELS,
        DROPPING_PURPLE_PIXEL,
        DROPPING_YELLOW_PIXEL,
        HAS_WHITE_PIXELS,
        DROPPING_WHITE_PIXEL,
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
                currentState = clawSM.STATE.HAS_START_PY_PIXELS;
                break;
            case DETECTED_TEAM_PROP:
                currentState = clawSM.STATE.DROPPING_PURPLE_PIXEL;
                break;
            case IDENTIFIED_APRIL_TAG:
                currentState = clawSM.STATE.DROPPING_YELLOW_PIXEL;
                break;
            case REACHED_STARTER_STACK:
                currentState = clawSM.STATE.HAS_WHITE_PIXELS;
                break;
            case REACHED_BACKDROP:
                currentState = clawSM.STATE.DROPPING_WHITE_PIXEL;
                break;
        }
    }

    public void update() {
        switch (currentState) {
            case HAS_START_PY_PIXELS:

                break;
            case DROPPING_PURPLE_PIXEL:

                break;
            case DROPPING_YELLOW_PIXEL:

                break;
            case HAS_WHITE_PIXELS:

                break;
            case DROPPING_WHITE_PIXEL:
                
                break;
        }
    }
}
