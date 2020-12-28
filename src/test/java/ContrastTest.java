import base.Constants;
import commands.ColorSetter;
import imagehandlers.ImageProcessor;
import imagehandlers.filters.Contrast;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ContrastTest {

    BufferedImage startImage;
    BufferedImage finishImage;
    @Before
    public void setup() throws IOException {
        File file = new File("src\\test\\testImages\\gray.jpg");
        startImage = ImageIO.read(file);
        finishImage = new Contrast().handleImage(ImageIO.read(file));
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
    
    @Test
    public void notTheSameColor() {
        int finishX = finishImage.getWidth() / 2;
        int finishY = finishImage.getHeight() / 2;
        int startX = startImage.getWidth() / 2;
        int startY = startImage.getHeight() / 2;
        Assert.assertNotEquals(finishImage.getRGB(finishX, finishY), startImage.getRGB(startX, startY));
    }
}
