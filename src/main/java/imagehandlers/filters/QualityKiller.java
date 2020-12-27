package imagehandlers.filters;

import imagehandlers.filters.Filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class QualityKiller implements Filter {
    @Override
    public void handleImage(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                if (red % 2 == 0) red += 50;
                else red -= 40;

                if (green % 2 == 0) green += 40;
                else green -= 40;

                if (blue % 2 == 0) blue += 50;
                else blue -= 40;

                if (red > 255) red = 255;
                if (red < 0) red = 0;

                if (green > 255) green = 255;
                if (green < 0) green = 0;

                if (blue > 255) blue = 255;
                if (blue < 0) blue = 0;

                image.setRGB(x, y, new Color (red, green, blue).getRGB());
            }
        }
    }
}
