import java.io.File;

public class SetColor implements Command {
    @Override
    public void handle(String uid, String message, File file, Core core, FSM fsm) {
        if (fsm.getCurrentState().equals(State.READY_TO_GET)){
            core.setUserColor(uid, message);
            if (core.getUserTextColor(uid) != null)
                core.putTextToPhoto(uid);
            else
                fsm.setState(State.WAIT_RGB);
        }
    }
}