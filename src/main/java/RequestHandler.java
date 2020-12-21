public class RequestHandler {
    private Core core;
    private FSM fsm = new FSM();

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

    public String handle(String uid, String chatId, String input, ISender sender){
        updateFSMState(uid);

        boolean isAdd = fsm.isState(State.WAIT_IMAGE);
        boolean isStart = fsm.isState(State.START);
        if (input.equals("/fsmstate"))
            return fsm.getCurrentState().toString();
        State prevState = fsm.getCurrentState();
        if (isStart){
            fsm.update();
        }
        fsm.update(input);

        String res = fsm.getCurrentState().getStateMessage();
        core.setUserFSMState(uid, fsm.getCurrentState());

        return res;
    }
}
