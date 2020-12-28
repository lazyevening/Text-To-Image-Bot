import base.Constants;
import commands.ColorSetter;
import imagehandlers.ImageProcessor;
import imagehandlers.filters.Gray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GrayTest {

    BufferedImage startImage;
    BufferedImage finishImage;
    @Before
    public void setup() throws IOException {
        File file = new File("src\\test\\testImages\\testGray.jpg");
        startImage = ImageIO.read(file);
        finishImage = new Gray().handleImage(ImageIO.read(file));
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
        int finishX = finishImage.getWidth() / 2;
        int finishY = finishImage.getHeight() / 2;
        int startX = startImage.getWidth() / 2;
        int startY = startImage.getHeight() / 2;
        Assert.assertEquals(finishImage.getRGB(finishX, finishY), startImage.getRGB(startX, startY));
    }
    @Test
    public void noColored() throws IOException {
        finishImage = new Gray().handleImage(ImageIO.read(new File("src\\test\\testImages\\testColored.jpg")));
        boolean greyIsEverywhere = true;
        for (var x = 0; x < finishImage.getWidth(); x++)
            for (var y = 0; y < finishImage.getHeight(); y++){
                int argb = finishImage.getRGB(x, y);
                int red = (argb >> 16) & 0xff;
                int green = (argb >>  8) & 0xff;
                int blue = (argb ) & 0xff;
                if (red != green || red != blue)
                    greyIsEverywhere = false;
            }
        Assert.assertTrue(greyIsEverywhere);
    }
}
