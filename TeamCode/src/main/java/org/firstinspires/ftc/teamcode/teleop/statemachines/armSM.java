package org.firstinspires.ftc.teamcode.teleop.statemachines;

public class armSM {

    public enum STATE {
        FOLDED_IN,
        REACHED_OUT,
    }

    public enum EVENT {
        GAME_START,
        DETECTED_PIXEL_TO_GRAB,
        DETECTED_BACKDROP_DROP_PIXEL,
    }

    STATE currentState;

    public STATE getState() {
        return currentState;
    }

    public void transition(EVENT event) {
        switch (event) {
            case GAME_START:
                currentState = STATE.FOLDED_IN;
                break;
            case DETECTED_PIXEL_TO_GRAB:
                currentState = STATE.REACHED_OUT;
                break;
            case DETECTED_BACKDROP_DROP_PIXEL:
                currentState = STATE.REACHED_OUT;
                break;
        }
    }

    public void update(){
        switch (currentState) {
            case REACHED_OUT:

                break;
        }
    }
}