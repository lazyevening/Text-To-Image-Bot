package imagehandlers.filters;

import imagehandlers.filters.Filter;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;

public class ToGray implements Filter {
    @Override
    public void handleImage(BufferedImage image) {
        ColorSpace cs = ColorSpace.getInstance(
                ColorSpace.CS_GRAY);
        BufferedImageOp op = new ColorConvertOp(cs, null);
        image = op.filter(image, null);
    }
}
