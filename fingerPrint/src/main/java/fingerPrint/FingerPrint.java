package main.java.fingerPrint;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;

import javax.imageio.ImageIO;

class FingerPrint {
    public static void main(String[] args) throws IOException{
        String resPath = binarizeImage("fingerPrint/src/main/java/fingerPrint/img/101_1.tif");
        skeletonization(resPath);
    }

    public static String skeletonization(String filePath){

        return null;
    }

    public static String binarizeImage(String pathToImage) throws IOException{
        File file = new File(pathToImage);
        int [][]greyPicture = greyGrad(file);
        greyPicture = bradleyThreshold(greyPicture);
        writeGrayImage(greyPicture, "result.png");
        return "result.png";
    }

    public static int[][] convertFileToMatrix(File file) throws IOException{
        BufferedImage iBufferedImage = ImageIO.read(file);
        int [][] image = new int[iBufferedImage.getHeight()][iBufferedImage.getWidth()];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                image[i][j] = iBufferedImage.getRGB(i, j);
            }
        }
        return image;
    }

    public static int[][] bradleyThreshold(int[][] src) {
        final int width = src.length;
        final int height = src[0].length;
        final int S = width / 12;
        int s2 = S / 2;
        final float t = 0.05f;
        long[] integralImage = new long[width * height];
        long sum = 0;
        int count = 0;
        int index;
        int x1, y1, x2, y2;

        // Вычисление интегрального изображения
        for (int i = 0; i < width; i++)

 {
            sum = 0;
            for (int j = 0; j < height; j++) {
                index = j * width + i;
                sum += src[i][j];
                if (i == 0)
                    integralImage[index] = sum;
                else
                    integralImage[index] = integralImage[index - 1] + sum;
            }
        }

        // Нахождение границ для локальных областей и бинаризация
        int[][] binaryImage = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                index = j * width + i;

                x1 = i - s2;
                x2 = i + s2;
                y1 = j - s2;
                y2 = j + s2;

                if (x1 < 0)
                    x1 = 0;
                if (x2 >= width)
                    x2 = width - 1;
                if (y1 < 0)
                    y1 = 0;
                if (y2 >= height)
                    y2 = height - 1;

                count = (x2 - x1) * (y2 - y1);

                sum = integralImage[y2 * width + x2] - integralImage[y1 * width + x2] -
                        integralImage[y2 * width + x1] + integralImage[y1 * width + x1];
                if (src[i][j] * count < sum * (1.0f - t))
                    binaryImage[i][j] = 0;
                else
                    binaryImage[i][j] = 255;
            }
        }

        return binaryImage;
    }

    public static int[][] greyGrad(File image) throws IOException{
        BufferedImage beforeGreyImage = ImageIO.read(image);
        int [][] greyImage = new int[beforeGreyImage.getHeight()][beforeGreyImage.getWidth()];
        int [] pixel = new int[3];
        for (int i = 0; i < greyImage.length; i++) {
            for (int j = 0; j < greyImage[0].length; j++) {
                beforeGreyImage.getRaster().getPixel(i, j, pixel);
                greyImage[i][j] = (pixel[0] + pixel[1] + pixel[2]) / 3;
            }
        }
        return greyImage;
    }

    public static void writeGrayImage(int[][] grayPixels, String outputPath) throws IOException{
        int width = grayPixels[0].length;
        int height = grayPixels.length;

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        // Устанавливаем значения пикселей в изображении
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int grayValue = grayPixels[x][y]; // Получаем значение оттенка серого
                int rgb = (grayValue << 16) | (grayValue << 8) | grayValue; // Преобразуем в формат RGB
                grayImage.setRGB(x, y, rgb);
            }
        }

        // Сохраняем изображение
        try {
            File outputImageFile = new File(outputPath);
            ImageIO.write(grayImage, "png", outputImageFile);
            System.out.println("Gray image saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}