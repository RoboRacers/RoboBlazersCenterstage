package org.firstinspires.ftc.teamcode.autonomous;

public class liftSM {

    public enum STATE {
        LIFT_OPEN_UP,
        LIFT_GRABBED_TRUSS,
        LIFT_RETRACTED,

    }

    public enum EVENT {
        IS_ENDGAME,
        ROBOT_UNDER_TRUSS,
        ROBOT_HANGING,
    }


    liftSM.STATE currentState;

    public liftSM.STATE getState() {
        return currentState;
    }

    public void transition(EVENT event) {
        switch (event) {
            case IS_ENDGAME:
                currentState = liftSM.STATE.LIFT_OPEN_UP;
                break;
            case ROBOT_UNDER_TRUSS:
                currentState = liftSM.STATE.LIFT_GRABBED_TRUSS;
                break;
            case ROBOT_HANGING:
                currentState = liftSM.STATE.LIFT_RETRACTED;
                break;
        }
    }
}
