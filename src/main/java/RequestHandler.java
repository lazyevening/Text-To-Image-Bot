import java.io.File;

public class RequestHandler {
    private final Core core;
    private final FSM fsm = new FSM();

    public RequestHandler(){
        core = new Core();
    }

    public RequestHandler(String path){
        core = new Core(path);
    }

    private void updateFSMState(String uid) {
        State userState = core.getUserFSMState(uid);
        if (userState == null) {
            core.createUser(uid);
            fsm.setState(State.ENTRY_POINT);
        } else {
            fsm.setState(userState);
        }
    }

    public String handle(String uid, String chatId, String message, File file){
        updateFSMState(uid);
        boolean isWaitImage = fsm.isState(State.WAIT_IMAGE);
        boolean isWaitText = fsm.isState(State.WAIT_TEXT);
        boolean isStart = fsm.isState(State.START);
        boolean isHelp = fsm.isState(State.HELP);
        if (isWaitImage && file == null) return Constants.GET_IMAGE_MSG;
        if (message.equals("/fsmstate")) return fsm.getCurrentState().toString();

        if (isStart || isHelp) fsm.update();
        fsm.update(message);

        if (isWaitImage){
            core.setImage(uid, file);
        } else if (isWaitText){
            core.putTextToPhoto(uid, message);
        }

        String res = fsm.getCurrentState().getStateMessage();
        core.setUserFSMState(uid, fsm.getCurrentState());

        return res;
    }

    public File getPhoto(String user_id) {
        return core.getUserPhoto(user_id);
    }
}
