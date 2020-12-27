package commands;

import base.Core;
import statemachine.FSM;

import java.io.File;

public class SetPosition implements Command {
    @Override
    public void handle(String uid, String message, File file, Core core, FSM fsm) {
        core.setUserTextPosition(uid, message);
    }
}
