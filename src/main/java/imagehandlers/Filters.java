package imagehandlers;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.util.Random;

public class Filters {
    public static BufferedImage toGray(BufferedImage image) {
        ColorSpace cs = ColorSpace.getInstance(
                ColorSpace.CS_GRAY);
        BufferedImageOp op = new ColorConvertOp(cs, null);
        return op.filter(image, null);
    }

    public static BufferedImage toBlackWhite(BufferedImage image) {
        // Создаем новое пустое изображение, такого же размера
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        //0.8 коэф. яркости
        double separator = 255 / 0.8 / 2 * 3;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                // Получаем цвет текущего пикселя
                Color color = new Color(image.getRGB(x, y));

                // Суммируем каналы этого цвета
                int total = color.getRed() + color.getGreen() + color.getBlue();

                if (total > separator){
                    result.setRGB(x, y, 0xffffff);
                } else {
                    result.setRGB(x, y, 0x0);
                }
            }
        }
        return result;
    }

    public static BufferedImage toNegative(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                image.setRGB(x, y, new Color (255 - red, 255 - green, 255 - blue).getRGB());
            }
        }
        return image;
    }

    public static BufferedImage addContr(BufferedImage image) {
        int k = 20; //увеличение контрастности на 20%
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));
                int red = (color.getRed() * 100 - 128 * k) / (100 - k);
                int green = (color.getGreen() * 100 - 128 * k) / (100 - k);
                int blue = (color.getBlue() * 100 - 128 * k) / (100 - k);

                if (red > 255) red = 255;
                if (red < 0) red = 0;

                if (green > 255) green = 255;
                if (green < 0) green = 0;

                if (blue > 255) blue = 255;
                if (blue < 0) blue = 0;

                image.setRGB(x, y, new Color (red, green, blue).getRGB());
            }
        }
        return image;
    }

    public static BufferedImage addColorNoise(BufferedImage image) {
        Random random = new Random();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int r = random.nextInt(180);
                int g = random.nextInt(180);
                int b = random.nextInt(180);

                Color color = new Color(image.getRGB(x, y));

                int red = color.getRed() + r;
                int green = color.getGreen() + g;
                int blue = color.getBlue() + b;

                image.setRGB(x, y, new Color (Math.round(red / 2), Math.round(green/ 2), Math.round(blue / 2)).getRGB());
            }
        }
        return image;
    }

    public static BufferedImage addCubes(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed() + y % 20 * 2;
                int green = color.getGreen();
                int blue = color.getBlue() + x % 20 * 3;

                if (red > 255) red = 255;
                if (blue > 255) blue = 255;

                image.setRGB(x, y, new Color (red, green, blue).getRGB());
            }
        }
        return image;
    }

    public static BufferedImage addSmth(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));
                int red = (color.getRed() + x) % 255;
                int green = (color.getGreen() + y) % 250;
                int blue = color.getBlue() + x % 10 * 6;

                if (blue > 255) blue = 255;

                image.setRGB(x, y, new Color (red, green, blue).getRGB());
            }
        }
        return image;
    }

    public static BufferedImage qualityKiller(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                if (red % 2 == 0) red += 50;
                else red -= 40;

                if (green % 2 == 0) green += 40;
                else green -= 40;

                if (blue % 2 == 0) blue += 50;
                else blue -= 40;

                if (red > 255) red = 255;
                if (red < 0) red = 0;

                if (green > 255) green = 255;
                if (green < 0) green = 0;

                if (blue > 255) blue = 255;
                if (blue < 0) blue = 0;

                image.setRGB(x, y, new Color (red, green, blue).getRGB());
            }
        }
        return image;
    }

}
