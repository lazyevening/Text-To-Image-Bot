public enum State {
    START(Constants.START_MSG),
    ENTRY_POINT(Constants.ENTRY_POINT_MSG),
    LISTEN(Constants.BOT_WAITING_COMMANDS),
    WAIT_IMAGE(Constants.GET_IMAGE_MSG),
    WAIT_TEXT(Constants.GET_TEXT_MSG),
    READY_TO_GET(Constants.READY_TO_GET_MSG),
    HELP(Constants.HELP_MSG),
    WAIT_POSITION(Constants.POSITION_MSG),
    WAIT_COLOR(Constants.COLOR_MSG),
    WAIT_RGB(Constants.RGB_MSG);

    private final String state;

    State(String state) {
        this.state = state;
    }

    public String getStateMessage(){
        return state;
    }

}