package imagehandlers.filters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AddCubes implements Filter {
    @Override
    public void handleImage(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed() + y % 20 * 2;
                int green = color.getGreen();
                int blue = color.getBlue() + x % 20 * 3;

                if (red > 255) red = 255;
                if (blue > 255) blue = 255;

                image.setRGB(x, y, new Color (red, green, blue).getRGB());
            }
        }
    }
}
