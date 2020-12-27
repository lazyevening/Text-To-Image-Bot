package imagehandlers.filters;

import java.awt.image.BufferedImage;

public interface Filter {
    void handleImage(BufferedImage image);
}
