import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProcessor {
    public static BufferedImage textToImage(BufferedImage image, String text){
        System.out.println(image.getHeight() + " " + image.getHeight());
        Font font = new Font("Arial", Font.BOLD, (int)(image.getHeight() + image.getHeight()) / 35);
        Graphics g = image.getGraphics();
        FontMetrics metrics = g.getFontMetrics(font);
        int positionX = (image.getWidth() - metrics.stringWidth(text)) / 2;
        int positionY = (image.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(text, positionX, positionY);
        return image;
    }
}
