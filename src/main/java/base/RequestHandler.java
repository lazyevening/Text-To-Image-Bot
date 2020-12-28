package base;

import commands.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import statemachine.FSM;
import statemachine.State;
import telegram.Bot;

import java.io.File;
import java.util.EnumMap;

public class RequestHandler {
    private final Core core;
    private final FSM fsm = new FSM();
    private final EnumMap<State, Command> stateToCommand = new EnumMap<>(State.class);
    public RequestHandler(){
        core = new Core();
    }

    private void initStateToCommandMap(){
        stateToCommand.put(State.WAIT_IMAGE, new ImageSetter());
        stateToCommand.put(State.WAIT_IMAGE_FILTER, new ImageSetter());
        stateToCommand.put(State.WAIT_TEXT, new TextSetter());
        stateToCommand.put(State.WAIT_POSITION, new PositionSetter());
        stateToCommand.put(State.WAIT_COLOR, new ColorSetter());
        stateToCommand.put(State.WAIT_RGB, new ColorSetter());
        stateToCommand.put(State.WAIT_FILTER, new FilterSetter());

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
        initStateToCommandMap();
        updateFSMState(uid);
        State prevState = fsm.getCurrentState();
        if (fsm.isState(State.WAIT_IMAGE) && file == null)
            return Constants.GET_IMAGE_TEXT_MSG;
        if (fsm.isState(State.WAIT_IMAGE_FILTER) && file == null)
            return Constants.GET_IMAGE_FILTER_MSG;
        ifVoidState(prevState);
        fsm.update(message);
        if (stateToCommand.containsKey(prevState))
            stateToCommand.get(prevState).handle(uid, message, file, core, fsm);
        String res = fsm.getCurrentState().getStateMessage();
        core.setUserFSMState(uid, fsm.getCurrentState());
        return res;
    }

    private void ifVoidState(State state){
        if (state.equals(State.START) || state.equals(State.HELP))
            fsm.update();
    }

    public File getPhoto(String user_id) {
        return core.getUserPhoto(user_id);
    }

    public ReplyKeyboard handleKeyboard(String uid) {
        if(core.getUserFSMState(uid).equals(State.WAIT_POSITION))
            return Bot.getKeyboard(Constants.POSITIONS);
        if(core.getUserFSMState(uid).equals(State.WAIT_COLOR))
            return Bot.getKeyboard(Constants.COLORS);
        if(core.getUserFSMState(uid).equals(State.WAIT_FILTER))
            return Bot.getKeyboard(Constants.FILTERS);
        return Bot.getKeyboard(null);
    }
}
