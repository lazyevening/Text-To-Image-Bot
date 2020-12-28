import base.Constants;
import commands.ColorSetter;
import imagehandlers.ImageProcessor;
import imagehandlers.filters.BlackAndWhite;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlackAndWhiteTest {

    BufferedImage startImage;
    BufferedImage finishImage;
    Color color;

    BufferedImage startImage2;
    BufferedImage finishImage2;
    Color color2;

    @Before
    public void setup() throws IOException {
        File file = new File("src\\test\\testImages\\gray.jpg");
        startImage = ImageIO.read(file);
        finishImage = new BlackAndWhite().handleImage(ImageIO.read(file));
        color = new Color(finishImage.getRGB(10, 10));

        File file2 = new File("src\\test\\testImages\\lightGray.jpg");
        startImage2 = ImageIO.read(file2);
        finishImage2 = new BlackAndWhite().handleImage(ImageIO.read(file2));
        color2 = new Color(finishImage2.getRGB(10, 10));
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
    public void correctColor() {
        Assert.assertEquals(0, color.getBlue());
    }

    @Test
    public void correctColor2() {
        Assert.assertEquals(255, color2.getBlue());
    }
}

