package org.firstinspires.ftc.teamcode.autonomous;

public class LiftStateMachine {
    private enum state{
        DRIVING_TOWARDS_A_SPIKE_MARKER,
        ROBOT_REACHED_THE_SPIKE_MARKER,
        DRIVING_TOWARDS_THE_BACK_BACKDROP,
        REACHED_THE_BACKDROP,
        DRIVING_TOWARDS_THE_STACK_ONE,
        REACHED_STACK_ONE,
        DRIVING_TOWARDS_STACK_TWO,
        REACHED_STACK_TWO,
        DRIVE_TOWARDS_BACKDROP,
        LOOP,
    }

    private enum event{
        DETECTED_THE_TEAM_PROP,
        LOCATION_XYZ,
        DROPPED_PURPLE_PIXEL,
        LOCATION,
        DROP_YELLOW_PIXEL,
        LOCATION_STACK_ONE,
        PIXEL_IS_IN_CLAW,
        LOCATION_STACK_TWO,
        TWO_PIXELS_IN_CLAW,
        LOOP,
    }
    public void transition(AutoMith.event) {
        switch (event) {
            case AutoMith.event.DETECTED_THE_TEAM_PROP:
                currentState = AutoMith.state.DRIVING_TOWARDS_A_SPIKE_MARKER;
                break;
            case AutoMith.event.LOCATION_XYZ:
                currentState = AutoMith.state.ROBOT_REACHED_THE_SPIKE_MARKER;
                break;
            case AutoMith.event.DROPPED_PURPLE_PIXEL:
                currentState = AutoMith.state.DRIVING_TOWARDS_THE_BACK_BACKDROP;
                break;
            case AutoMith.event.LOCATION:
                currentState = AutoMith.state.REACHED_THE_BACKDROP;
                break;
            case AutoMith.event.DROP_YELLOW_PIXEL:
                currentState = AutoMith.state.DRIVING_TOWARDS_THE_STACK_ONE;
                break;
            case AutoMith.event.LOCATION_STACK_ONE:
                currentState = AutoMith.state.REACHED_STACK_ONE;
                break;
            case AutoMith.event.PIXEL_IS_IN_CLAW:
                currentState = AutoMith.state.DRIVING_TOWARDS_STACK_TWO;
                break;
            case AutoMith.event.LOCATION_STACK_TWO:
                currentState = AutoMith.state.REACHED_STACK_TWO;
                break;
            case AutoMith.event.TWO_PIXELS_IN_CLAW:
                currentState = AutoMith.state.DRIVE_TOWARDS_BACKDROP;
                break;
            case AutoMith.event.LOOP:
                currentState = AutoMith.state.LOOP;
                break;
        }
        public void action(AutoMith.state) {
                switch (state) {
                    case AutoMith.state.DRIVING_TOWARDS_A_SPIKE_MARKER:
                        //We must put the drive function to drive toward the spike marker with the team prop
                        break;
                    case AutoMith.state.ROBOT_REACHED_THE_SPIKE_MARKER:
                        //We must put the drive function that tells us when we reach the spike marker

                        break;
                    case AutoMith.state.DRIVING_TOWARDS_THE_BACK_BACKDROP:
                        //We must put the drive function that drives toward the back drop
                        break;

                    case AutoMith.state.REACHED_THE_BACKDROP:
                        //We must put the drive function that tells us when we reach the back drop
                        break;
                    case AutoMith.state.DRIVING_TOWARDS_THE_STACK_ONE:
                        //We must put the drive function to go to stack 1
                        break;
                    case AutoMith.state.REACHED_STACK_ONE:
                        //We must put the drive function to tell us that we reached stack 1
                        break;

                    case AutoMith.state.DRIVING_TOWARDS_STACK_TWO:
                        //We must put the drive function to drive to stack 2
                        break;
                    case AutoMith.state.REACHED_STACK_TWO:
                        //We must put the drive function to tell us once we reach stack 2
                        break;
                    case AutoMith.state.DRIVE_TOWARDS_BACKDROP:
                        //We must put the drive function to drive to the backdrop
                        break;

                    case AutoMith.state.LOOP:
                        //We must put the drive function to loop the code for the stacks
                        break;
                }
        }
}
