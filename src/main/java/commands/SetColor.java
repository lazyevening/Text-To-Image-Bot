package commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import base.Core;
import imagehandlers.ImageProcessor;
import statemachine.FSM;
import statemachine.State;

import javax.imageio.ImageIO;

public class SetColor implements Command {
    @Override
    public void handle(String uid, String message, File file, Core core, FSM fsm) {
        if (fsm.getCurrentState().equals(State.WAIT_FILTER)){
            core.setUserColor(uid, message);
            if (core.getUserTextColor(uid) != null)
                putTextToPhoto(uid, core);
            else
                fsm.setState(State.WAIT_RGB);
        }
    }

    public void putTextToPhoto(String user_id, Core core) {
        try {
            BufferedImage image = ImageIO.read(core.getUserPhoto(user_id));
            ImageProcessor.textToImage(
                    image,
                    core.getUserText(user_id),
                    core.getUserTextPosition(user_id),
                    core.getUserTextColor(user_id),
                    core.getUserIsRgb(user_id));
            ImageIO.write(image, "jpg", new File(core.getUserPhoto(user_id).toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
