import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProcessor {
    public static void textToImage(BufferedImage image, String text, String position){
        System.out.println(image.getHeight() + " " + image.getHeight());
        Font font = new Font("Arial", Font.BOLD, (int)(image.getHeight() + image.getHeight()) / 35);
        Graphics g = image.getGraphics();
        FontMetrics metrics = g.getFontMetrics(font);
        int positionX = defX(image, position, metrics.stringWidth(text));
        int positionY = defY(image, position, metrics);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(text, positionX, positionY);
    }

    private static int defX(BufferedImage image, String position, int textLength){
        position = position.split(" ")[1];
        switch (position){
            case Constants.POSITION_LEFT:
                return 0;
            case Constants.POSITION_RIGHT:
                return image.getWidth() - textLength;
            default:
                return (image.getWidth() - textLength) / 2;
        }
    }

    private static int defY(BufferedImage image, String position, FontMetrics metrics){
        position = position.split(" ")[0];
        switch (position){
            case Constants.POSITION_TOP:
                return metrics.getAscent();
            case Constants.POSITION_BOTTOM:
                  return (image.getHeight() - metrics.getHeight()) + metrics.getAscent();
            default:
                return (image.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
        }
    }
}
