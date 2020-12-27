package commands;

import base.Core;
import statemachine.FSM;

import java.io.File;

public class SetImage implements Command {
    @Override
    public void handle(String uid, String message, File file, Core core, FSM fsm) {
        core.setImage(uid, file);
    }
}
