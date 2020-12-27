package base;

import statemachine.State;

import java.io.File;

public class User {
    boolean isRGB;
    String textColor;
    String textPosition;
    String text;
    File file;
    State state;

    User(){
        this.state = State.ENTRY_POINT;
    }
    public State getFsmState() {
        return state;
    }

    public void setFsmState(State fsmState) {
        this.state = fsmState;
    }
}
