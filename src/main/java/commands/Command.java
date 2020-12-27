package commands;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.File;
import base.Core;
import statemachine.FSM;

public interface Command {
    void handle(String uid, String message, File file, Core core, FSM fsm);
}
