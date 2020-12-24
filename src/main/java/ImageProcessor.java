import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageProcessor {
    private static final HashMap<String, Color> colors = new HashMap<>();

    private static void initColors(){
        colors.put(Constants.COLORS[0], Color.BLACK);
        colors.put(Constants.COLORS[1], Color.WHITE);
        colors.put(Constants.COLORS[2], Color.GRAY);

        colors.put(Constants.COLORS[3], Color.RED);
        colors.put(Constants.COLORS[4], Color.GREEN);
        colors.put(Constants.COLORS[5], Color.BLUE);

        colors.put(Constants.COLORS[6], Color.YELLOW);
        colors.put(Constants.COLORS[7], Color.PINK);
        colors.put(Constants.COLORS[8], Color.MAGENTA);
    }

    public static void textToImage(BufferedImage image, String text, String position, String color, boolean isRGB){
        initColors();
        System.out.println(image.getHeight() + " " + image.getHeight());
        Font font = new Font("Arial", Font.BOLD, (image.getWidth() + image.getHeight()) / 33);
        Graphics g = image.getGraphics();
        FontMetrics metrics = g.getFontMetrics(font);
        int positionX = defX(image, position, metrics.stringWidth(text));
        int positionY = defY(image, position, metrics);
        g.setFont(font);
        g.setColor(getColor(color, isRGB));
        g.drawString(text, positionX, positionY);
    }

    private static Color getColor(String color, boolean isRGB) {
        if (isRGB)
            return new Color(Integer.parseInt(color.split(" ")[0]),
                    Integer.parseInt(color.split(" ")[1]),
                    Integer.parseInt(color.split(" ")[2]));
        else if (color != null)
            return colors.get(color);
        return Color.GRAY;
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
