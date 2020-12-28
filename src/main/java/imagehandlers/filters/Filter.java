package imagehandlers.filters;

import java.awt.image.BufferedImage;

public interface Filter {
    BufferedImage handleImage(BufferedImage image);
}
