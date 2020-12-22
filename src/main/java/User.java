import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class User {
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
