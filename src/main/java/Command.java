import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.File;

public interface Command {
    void handle(String uid, String message, File file, Core core, FSM fsm);
}
