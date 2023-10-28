package org.firstinspires.ftc.teamcode.modules.statemachines;

import org.firstinspires.ftc.teamcode.autonomous.SM_Test.clawSM;
import org.firstinspires.ftc.teamcode.modules.subsystems.Claw;
import org.firstinspires.ftc.teamcode.modules.subsystems.Deposit;

public class ClawSM implements StateMachine {

    Claw claw;

    STATE currentState;

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

    public ClawSM(Claw claw) {
        this.claw = claw;
    }

    public ClawSM.STATE getState() {
        return currentState;
    }

    public void transition(ClawSM.EVENT event) {
        switch (event) {
            case GAME_START:
                currentState = ClawSM.STATE.HAS_START_PY_PIXELS;
                break;
            case DETECTED_TEAM_PROP:
                currentState = ClawSM.STATE.DROPPED_PURPLE_PIXEL;
                break;
            case IDENTIFIED_APRIL_TAG:
                currentState = ClawSM.STATE.DROPPED_YELLOW_PIXEL;
                break;
            case REACHED_STARTER_STACK:
                currentState = ClawSM.STATE.HAS_WHITE_PIXELS;
                break;
            case REACHED_BACKDROP:
                currentState = ClawSM.STATE.DROPPED_WHITE_PIXEL;
                break;
        }
    }

    public void update() {
        switch (currentState) {

        }
    }

}