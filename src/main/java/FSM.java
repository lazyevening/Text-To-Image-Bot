import java.util.ArrayList;

public class FSM {
    private final TransitionTable transitionTable = new TransitionTable();
    private final ArrayList<String> commands = new ArrayList<>();
    private State currentState;

    public FSM(){
        initCommands();
        initTransitions();
        currentState = State.ENTRY_POINT;
    }

    private void initCommands(){
        commands.add(Constants.ADD_TEXT_COMMAND);
        commands.add(Constants.CREATORS_COMMAND);
        commands.add(Constants.START_COMMAND);
    }

    private void initTransitions(){
        transitionTable.addTransition(new Transition(State.ENTRY_POINT, Constants.START_COMMAND, State.START));
        transitionTable.addTransition(new Transition(State.START, null, State.LISTEN));

        transitionTable.addTransition(new Transition(State.LISTEN, Constants.ADD_TEXT_COMMAND, State.WAIT_IMAGE));
        transitionTable.addTransition(new Transition(State.WAIT_IMAGE, null, State.WAIT_TEXT));

    }

    public void setState(State state){
        currentState = state;
    }

    public void update(){ update(""); }

    public void update(String line){
        if(!commands.contains(line)){
            line = null;
        }

        State end = transitionTable.getEndState(currentState, line);

        if(end != null) {
            currentState = end;
        }
    }

    public State getCurrentState(){
        return currentState;
    }

    public boolean isState(State state){
        return currentState.equals(state);
    }
}
