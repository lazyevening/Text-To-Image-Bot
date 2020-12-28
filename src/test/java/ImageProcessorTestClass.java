import base.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import imagehandlers.ImageProcessor;

import java.awt.*;

public class ImageProcessorTestClass {

    @Before
    public void setup() {
    }

    @Test
    public void CenterX() {
        int actual = ImageProcessor.defX(20, Constants.POSITION_CENTER, 2);
        Assert.assertEquals(9, actual, 1e-5);
    }

    @Test
    public void LeftX() {
        int actual = ImageProcessor.defX(20, Constants.POSITION_LEFT, 2);
        Assert.assertEquals(0, actual, 1e-5);
    }

    @Test
    public void RightX() {
        int actual = ImageProcessor.defX(20, Constants.POSITION_RIGHT, 2);
        Assert.assertEquals(18, actual, 1e-5);
    }

    @Test
    public void CenterY() {
        int actual = ImageProcessor.defY(20, 2, Constants.POSITION_CENTER, 0);
        Assert.assertEquals(9, actual, 1e-5);
    }

    @Test
    public void BottomY() {
        int actual = ImageProcessor.defY(20, 2, Constants.POSITION_BOTTOM, 0);
        Assert.assertEquals(18, actual, 1e-5);
    }

    @Test
    public void TopY() {
        int actual = ImageProcessor.defY(20, 2, Constants.POSITION_TOP, 0);
        Assert.assertEquals(0, actual, 1e-5);
    }

    @Test
    public void RightRBG() {
        Assert.assertEquals(new Color(255, 100, 10), ImageProcessor.getColor("255 100 10", true));
    }
}
