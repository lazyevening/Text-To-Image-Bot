import base.Constants;
import commands.ColorSetter;
import imagehandlers.ImageProcessor;
import imagehandlers.filters.ColorNoise;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ColorNoiseTest {

    BufferedImage startImage;
    BufferedImage finishImage;
    @Before
    public void setup() throws IOException {
        File file = new File("src\\test\\testImages\\testGray.jpg");
        startImage = ImageIO.read(file);
        finishImage = new ColorNoise().handleImage(ImageIO.read(file));
    }
    @Test
    public void theSameWidth() {
        Assert.assertEquals(finishImage.getWidth(), startImage.getWidth());
    }

    @Test
    public void theSameHeight() {
        Assert.assertEquals(finishImage.getHeight(), startImage.getHeight());
    }

    @Test
    public void theSameColor() {
        Assert.assertEquals(finishImage.getColorModel(), startImage.getColorModel());
    }
}
