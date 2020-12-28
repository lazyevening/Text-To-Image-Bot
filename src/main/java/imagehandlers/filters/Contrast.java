package imagehandlers.filters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Contrast implements Filter {
    @Override
    public void handleImage(BufferedImage image) {
        int k = 20; //увеличение контрастности на 20%
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));
                int red = (color.getRed() * 100 - 128 * k) / (100 - k);
                int green = (color.getGreen() * 100 - 128 * k) / (100 - k);
                int blue = (color.getBlue() * 100 - 128 * k) / (100 - k);

                if (red > 255) red = 255;
                else if (red < 0) red = 0;

                if (green > 255) green = 255;
                else if (green < 0) green = 0;

                if (blue > 255) blue = 255;
                else if (blue < 0) blue = 0;

                image.setRGB(x, y, new Color (red, green, blue).getRGB());
            }
        }
    }
}
