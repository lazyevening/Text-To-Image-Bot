package imagehandlers.filters;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AddColorNoise implements Filter {
    @Override
    public void handleImage(BufferedImage image) {
        Random random = new Random();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int r = random.nextInt(180);
                int g = random.nextInt(180);
                int b = random.nextInt(180);

                Color color = new Color(image.getRGB(x, y));

                int red = color.getRed() + r;
                int green = color.getGreen() + g;
                int blue = color.getBlue() + b;

                image.setRGB(x, y, new Color (Math.round(red / 2), Math.round(green/ 2), Math.round(blue / 2)).getRGB());
            }
        }
    }
}
