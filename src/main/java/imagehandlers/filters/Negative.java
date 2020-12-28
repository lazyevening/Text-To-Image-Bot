package imagehandlers.filters;

import imagehandlers.filters.Filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Negative implements Filter {
    @Override
    public void handleImage(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                image.setRGB(x, y, new Color (255 - red, 255 - green, 255 - blue).getRGB());
            }
        }
    }
}
