package org.firstinspires.ftc.teamcode.modules.statemachines;

import org.firstinspires.ftc.teamcode.modules.subsystems.ExampleSubsystem;
import org.firstinspires.ftc.teamcode.modules.subsystems.Lift;

/**
 * Class that outlines an example state machine.
 */
public class LiftSM implements StateMachine {


    /**
     * This is the subsystem object that this statemacine controls.
     */
    Lift lift;

    /**
     * The current state of this subsystem.
     */
    STATE currentState;


    /**
     * Enum declaring all the states of this state machine.
     */
    public enum STATE {
        LIFT_OPEN_UP,
        LIFT_GRABBED_TRUSS,
        LIFT_RETRACTED,
    }

    /**
     * Enum declaring all the events of this state machine.
     */
    public enum EVENT {
        IS_ENDGAME,
        ROBOT_UNDER_TRUSS,
        ROBOT_HANGING,
    }

    /**
     * The constructor function for this class.
     * Takes in the subsystem object that this state machine controls.
     * @param
     */
    public LiftSM(Lift lift) {
        this.lift = lift;
    }

    /**
     * Returns the current state of this state machine.
     * @return
     */
    public LiftSM.STATE getState() {
        return currentState;
    }


    /**
     * Transition the state into a different state given an event.
     * Run one time actions in here as well.
     * @param event
     */
    public void transition(LiftSM.EVENT event) {
        switch (event) {
            case IS_ENDGAME:
                // You can run one time events here.
                currentState = STATE.LIFT_OPEN_UP;
                break;
            case ROBOT_UNDER_TRUSS:

                currentState = STATE.LIFT_GRABBED_TRUSS;
                break;
            case ROBOT_HANGING:

                currentState = STATE.LIFT_RETRACTED;
                break;
        }
    }

    /**
     * Updates the state every single loop. Run repetitive actions here.
     * You can define transitions to other states internally here as well.
     */
    public void update() {
        switch (currentState) {
            case LIFT_OPEN_UP:

                break;
            case LIFT_GRABBED_TRUSS:
                break;
            case LIFT_RETRACTED:
                break;
        }
    }

}