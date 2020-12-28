package imagehandlers.filters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Surprise implements Filter {
    @Override
    public void handleImage(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));
                int red = (color.getRed() + x) % 255;
                int green = (color.getGreen() + y) % 250;
                int blue = color.getBlue() + x % 10 * 6;

                if (blue > 255) blue = 255;

                image.setRGB(x, y, new Color (red, green, blue).getRGB());
            }
        }
    }
}
