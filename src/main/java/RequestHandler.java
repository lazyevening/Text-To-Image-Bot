import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;

public class RequestHandler {
    private final Core core;
    private final FSM fsm = new FSM();

    public RequestHandler(){
        core = new Core();
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

    public String handle(String uid, String message, File file){
        updateFSMState(uid);
        boolean isWaitImage = fsm.isState(State.WAIT_IMAGE);
        boolean isWaitText = fsm.isState(State.WAIT_TEXT);
        boolean isWaitPosition = fsm.isState(State.WAIT_POSITION);
        boolean isWaitColor = fsm.isState(State.WAIT_COLOR);
        boolean isWaitRGB = fsm.isState(State.WAIT_RGB);
        boolean isStart = fsm.isState(State.START);
        boolean isHelp = fsm.isState(State.HELP);
        if (isWaitImage && file == null) return Constants.GET_IMAGE_MSG;
        if (message.equals("/fsmstate")) return fsm.getCurrentState().toString();

        if (isStart || isHelp) fsm.update();
        fsm.update(message);

        if (isWaitImage){
            core.setImage(uid, file);
        } else if (isWaitText){
            core.setUserText(uid, message);
        }else if (isWaitPosition) {
            core.setUserTextPosition(uid, message);
        }else if (isWaitColor || isWaitRGB) {
            if (fsm.getCurrentState().equals(State.READY_TO_GET)){
                core.setUserColor(uid, message);
                if (core.getUserTextColor(uid) != null)
                    core.putTextToPhoto(uid);
                else
                    fsm.setState(State.WAIT_RGB);
            }
        }
        String res = fsm.getCurrentState().getStateMessage();
        core.setUserFSMState(uid, fsm.getCurrentState());

        return res;
    }

    public File getPhoto(String user_id) {
        return core.getUserPhoto(user_id);
    }

    public ReplyKeyboard handleKeyboard(String uid) {
        if(core.getUserFSMState(uid).equals(State.WAIT_POSITION))
            return Bot.getKeyboard(Constants.POSITIONS);
        if(core.getUserFSMState(uid).equals(State.WAIT_COLOR))
            return Bot.getKeyboard(Constants.COLORS);
        return null;
    }
}
