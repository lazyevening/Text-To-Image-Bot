package commands;

import base.Constants;
import base.Core;
import base.User;
import imagehandlers.ImageProcessor;
import imagehandlers.filters.*;
import statemachine.FSM;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SetFilter implements Command {
    public static final HashMap<String, Filter> filters = new HashMap<>();

    private void initFilters() {
        filters.put(Constants.TO_GRAY_FILTER, new ToGray());
        filters.put(Constants.TO_BLACK_WHITE, new ToBlackWhite());
        filters.put(Constants.TO_NEGATIVE, new ToNegative());
        filters.put(Constants.ADD_CONTRAST, new AddContrast());

        filters.put(Constants.ADD_COLOR_NOISE, new AddColorNoise());
        filters.put(Constants.ADD_CUBES, new AddCubes());
        filters.put(Constants.ADD_SMOOTH, new AddSmooth());
        filters.put(Constants.QUALITY_KILLER, new QualityKiller());



    }

    @Override
    public void handle(String uid, String message, File file, Core core, FSM fsm) {
        initFilters();
        if (filters.containsKey(message)) {
            Filter filter = filters.get(message);
            try {
                BufferedImage image = ImageIO.read(core.getUserPhoto(uid));
                filter.handleImage(image);
                ImageIO.write(image, "jpg", new File(core.getUserPhoto(uid).toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
