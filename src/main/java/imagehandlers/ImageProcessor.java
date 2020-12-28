package imagehandlers;

import base.Constants;
import org.checkerframework.checker.units.qual.C;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageProcessor {
    private static final HashMap<String, Color> colors = new HashMap<>();

    private static void initColors(){
        colors.put(Constants.COLORS[0], Color.BLACK);
        colors.put(Constants.COLORS[1], Color.WHITE);
        colors.put(Constants.COLORS[2], new Color(101, 35, 35));

        colors.put(Constants.COLORS[3], Color.RED);
        colors.put(Constants.COLORS[4], Color.GREEN);
        colors.put(Constants.COLORS[5], Color.BLUE);

        colors.put(Constants.COLORS[6], Color.YELLOW);
        colors.put(Constants.COLORS[7], new Color(255,165,0));
        colors.put(Constants.COLORS[8], Color.MAGENTA);
    }

    public static void textToImage(BufferedImage image, String text, String position, String color, boolean isRGB){
        initColors();
        System.out.println(image.getHeight() + " " + image.getHeight());
        Font font = new Font("Arial", Font.BOLD, (image.getWidth() + image.getHeight()) / 33);
        Graphics g = image.getGraphics();
        FontMetrics metrics = g.getFontMetrics(font);
        int positionX = defX(image.getWidth(), position.split(" ")[1], metrics.stringWidth(text));
        int positionY = defY(image.getHeight(), metrics.getHeight(),position.split(" ")[0], metrics.getAscent());
        g.setFont(font);
        g.setColor(getColor(color, isRGB));
        g.drawString(text, positionX, positionY);
    }

    public static Color getColor(String color, boolean isRGB) {
        if (isRGB)
            return new Color(Integer.parseInt(color.split(" ")[0]),
                    Integer.parseInt(color.split(" ")[1]),
                    Integer.parseInt(color.split(" ")[2]));
        else if (color != null)
            return colors.get(color);
        return Color.GRAY;
    }


    public static int defX(int width, String position, int textLength){
        switch (position){
            case Constants.POSITION_LEFT:
                return 0;
            case Constants.POSITION_RIGHT:
                return width - textLength;
            default:
                return (width - textLength) / 2;
        }
    }

    public static int defY(int imageHeight, int metricsHeight, String position, int metricsAscent){
        switch (position){
            case Constants.POSITION_TOP:
                return metricsAscent;
            case Constants.POSITION_BOTTOM:
                  return (imageHeight - metricsHeight) + metricsAscent;
            default:
                return (imageHeight - metricsHeight) / 2 + metricsAscent;
        }
    }
}
