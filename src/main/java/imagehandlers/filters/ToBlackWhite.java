package imagehandlers.filters;

import imagehandlers.filters.Filter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ToBlackWhite implements Filter {
    @Override
    public void handleImage(BufferedImage image) {
        double separator = 255 / 0.8 / 2 * 3;
        for (int x = 0; x < image.getWidth(); x++)
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));
                int total = color.getRed() + color.getGreen() + color.getBlue();
                if (total > separator){
                    image.setRGB(x, y, 0xffffff);
                } else {
                    image.setRGB(x, y, 0x0);
                }
            }
    }
}
