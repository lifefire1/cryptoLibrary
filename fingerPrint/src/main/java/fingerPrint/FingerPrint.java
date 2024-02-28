package fingerPrint;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

/**
 * skilet
 */
class skiletTmp {

    int [][] currentImage;
    boolean status;    
}

class FingerPrint {
    public static void main(String[] args) throws IOException{
        int [][] binaryImage = binarizeImage("fingerPrint/src/main/java/fingerPrint/img/101_1.tif");
        killNoize(binaryImage);
        writeGrayImage(binaryImage, "killNoize.png");
        binaryImage = skeletonize(binaryImage);

        // [0] - count endPoint
        // [1] - count branchPoint
        System.out.println(Arrays.toString(countSpecialPoint(binaryImage)));
    }

    public static void killNoize(int[][] image) {
        // Проходим по всем пикселям, кроме краев
        for (int i = 1; i < image.length - 1; i++) {
            for (int j = 1; j < image[0].length - 1; j++) {
                // Подсчитываем количество черных соседей текущего пикселя
                int blackNeighbors = countBlackNeighbors(image, i, j);
                // Если количество черных соседей меньше семи, текущий пиксель становится белым
                if (blackNeighbors < 3) {
                    image[i][j] = Color.WHITE.getRGB(); // 255 - это белый цвет
                }
            }
        }
    }
    
    // Метод для подсчета количества черных соседей у заданного пикселя
    public static int countBlackNeighbors(int[][] image, int row, int col) {
        int blackCount = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                // Если текущий пиксель - черный и это не центральный пиксель, увеличиваем счетчик
                if (image[i][j] == Color.BLACK.getRGB() && (i != row || j != col)) {
                    blackCount++;
                }
            }
        }
        return blackCount;
    }

    public static int[] countSpecialPoint(int[][] image) {
        int[] answer = new int[2];
        int firstCount = 0;
        int secondCount = 0;
    
        // Проходим по всем пикселям, кроме краев
        for (int i = 1; i < image.length - 1; i++) {
            for (int j = 1; j < image[0].length - 1; j++) {
                // Проверяем окрестность текущего пикселя 3x3
                int countBlack = 0;
                for (int m = i - 1; m <= i + 1; m++) {
                    for (int n = j - 1; n <= j + 1; n++) {
                        if (image[m][n] == Color.BLACK.getRGB()) { // Если текущий пиксель черный
                            countBlack++;
                        }
                    }
                }
                // Если только один черный пиксель в окрестности, увеличиваем firstCount
                if (countBlack == 1) {
                    firstCount++;
                }
                // Если три черных пикселя в окрестности, увеличиваем secondCount
                else if (countBlack == 3) {
                    secondCount++;
                }
            }
        }
    
        // Записываем результаты в массив ответа
        answer[0] = firstCount;
        answer[1] = secondCount;
    
        return answer;
    }

    public static int [][] skeletonize(int [][] image) throws IOException{
        int [][] structPattern = {
            {Color.WHITE.getRGB(),                  0,                   0},
            {Color.BLACK.getRGB(),Color.BLACK.getRGB(),                  0},
            {Color.WHITE.getRGB(),Color.BLACK.getRGB(),Color.WHITE.getRGB()},
        };
        int iter = 0;
        while (corrosive(image, structPattern)) {
            iter++;
        }
        // System.out.println("first corrosive = " + iter);

        iter = 0;

        int [][] structPattern2 = {
            {0,                     Color.BLACK.getRGB(),   0},
            {Color.BLACK.getRGB(),  Color.BLACK.getRGB(),   Color.WHITE.getRGB()},
            {0,                     Color.WHITE.getRGB(),   Color.WHITE.getRGB()},
        };

        while (corrosive(image, structPattern2)) {
            iter++;
        }

        // System.out.println("second corrosive = " + iter);

        iter = 0;

        int [][] structPattern3 = {
            {0,                     Color.BLACK.getRGB(),   0},
            {Color.WHITE.getRGB(),  Color.BLACK.getRGB(),   Color.BLACK.getRGB()},
            {Color.WHITE.getRGB(),  Color.WHITE.getRGB(),   0},
        };
        while (corrosive(image, structPattern3)) {
            iter++;
        }

        // System.out.println("third corrosive = " + iter);

        iter = 0;

        int [][] structPattern4 = {
            {Color.WHITE.getRGB(),  Color.WHITE.getRGB(),   0},
            {Color.WHITE.getRGB(),  Color.BLACK.getRGB(),   Color.BLACK.getRGB()},
            {0,                     Color.BLACK.getRGB(),   0},
        };

        while (corrosive(image, structPattern4)) {
            iter++;
        }


        // System.out.println("four corrosive = " + iter);

        iter = 0;


        int [][] structPattern5 = {
            {Color.WHITE.getRGB(),  Color.WHITE.getRGB(),   Color.WHITE.getRGB()},
            {Color.BLACK.getRGB(),  Color.BLACK.getRGB(),   Color.BLACK.getRGB()},
            {0,                     Color.BLACK.getRGB(),   0},
        };

        while (corrosive(image, structPattern5)) {
            iter++;
        }

        // System.out.println("five corrosive = " + iter);

        iter = 0;

        int [][] structPattern6 = {
            {0,                     Color.BLACK.getRGB(),   Color.WHITE.getRGB()},
            {Color.BLACK.getRGB(),  Color.BLACK.getRGB(),   Color.WHITE.getRGB()},
            {0,                     Color.BLACK.getRGB(),   Color.WHITE.getRGB()},
        };

        while (corrosive(image, structPattern6)) {
            iter++;
        }

        // System.out.println("six corrosive = " + iter);

        iter = 0;

        int [][] structPattern7 = {
            {Color.WHITE.getRGB(),  Color.BLACK.getRGB(),   0},
            {Color.WHITE.getRGB(),  Color.BLACK.getRGB(),   Color.BLACK.getRGB()},
            {Color.WHITE.getRGB(),  Color.BLACK.getRGB(),   0},
        };

        while (corrosive(image, structPattern7)) {
            iter++;
        }

        // System.out.println("seven corrosive = " + iter);

        iter = 0;

        int [][] structPattern8 = {
            {0                   ,  Color.BLACK.getRGB(),   0},
            {Color.BLACK.getRGB(),  Color.BLACK.getRGB(),   Color.BLACK.getRGB()},
            {Color.WHITE.getRGB(),  Color.WHITE.getRGB(),   Color.WHITE.getRGB()},
        };

        while (corrosive(image, structPattern8)) {
            iter++;
        }

        // System.out.println("eight corrosive = " + iter);

        int [][] structPattern9 = {
            {Color.WHITE.getRGB(),  Color.WHITE.getRGB(),   Color.WHITE.getRGB()},
            {Color.WHITE.getRGB(),  Color.BLACK.getRGB(),   Color.WHITE.getRGB()},
            {Color.WHITE.getRGB(),  Color.WHITE.getRGB(),   Color.WHITE.getRGB()},
        };

        while (corrosive(image, structPattern9)) {
            iter++;
        }

        // System.out.println("nine corrosive = " + iter);

        iter = 0;

        int [][] structPattern10 = {
            {Color.WHITE.getRGB(),  Color.WHITE.getRGB(),   Color.WHITE.getRGB()},
            {Color.WHITE.getRGB(),  Color.BLACK.getRGB(),   Color.WHITE.getRGB()},
            {Color.BLACK.getRGB(),  Color.BLACK.getRGB(),   Color.BLACK.getRGB()},
        };

        while (corrosive(image, structPattern10)) {
            iter++;
        }

        // System.out.println("ten corrosive = " + iter);
        


        iter = 0;

        int [][] structPattern11 = {
            {Color.WHITE.getRGB(),  Color.WHITE.getRGB(),   Color.WHITE.getRGB()},
            {Color.WHITE.getRGB(),  Color.BLACK.getRGB(),   Color.WHITE.getRGB()},
            {Color.BLACK.getRGB(),  Color.BLACK.getRGB(),   Color.WHITE.getRGB()},
        };

        while (corrosive(image, structPattern11)) {
            iter++;
        }

        // System.out.println("eleven corrosive = " + iter);


        iter = 0;

        int [][] structPattern12 = {
            {Color.WHITE.getRGB(),  Color.WHITE.getRGB(),   Color.WHITE.getRGB()},
            {Color.WHITE.getRGB(),  Color.BLACK.getRGB(),   Color.WHITE.getRGB()},
            {Color.WHITE.getRGB(),  Color.BLACK.getRGB(),   Color.BLACK.getRGB()},
        };

        while (corrosive(image, structPattern12)) {
            iter++;
        }

        // System.out.println("twelve corrosive = " + iter);

        writeGrayImage(image, "skeleton.png");
        return image;
    }

    public static boolean corrosive(int [][] image, int [][] structPattern){
        int [][] skillet = new int[image.length][image[0].length];
        boolean status = false;
        for(int i = 1; i < image.length - 2; i++){
            for(int j = 1; j < image[0].length - 2; j++){
                if(isHit(i,j, image, structPattern)){
                    image[i][j] = Color.WHITE.getRGB();
                    status = true;
                }
            }
        }
        skiletTmp tmp = new skiletTmp();
        tmp.currentImage = skillet;
        tmp.status = status;
        return status;
    }

    public static boolean isHit(int i, int j, int[][] image, int[][] structPattern) {
        int[][] tmp = new int[structPattern.length][structPattern[0].length];
    
        for (int k = 0; k < structPattern.length; k++) {
            for (int l = 0; l < structPattern[0].length; l++) {
                int imageRow = i + k - 1;
                int imageCol = j + l - 1;
                if (imageRow >= 0 && imageRow < image.length && imageCol >= 0 && imageCol < image[0].length) {
                    tmp[k][l] = image[imageRow][imageCol];
                } else {
                    // Здесь вы можете установить значение по умолчанию, если выходите за пределы изображения
                    tmp[k][l] = 0;
                }
            }
        }
    
        // Сравниваем tmp с structPattern
        for (int k = 0; k < structPattern.length; k++) {
            for (int l = 0; l < structPattern[0].length; l++) {
                // Если элемент в structPattern равен 1, то элемент в tmp должен быть также 1
                if(structPattern[k][l] == 0){
                    continue;
                }
                else if (structPattern[k][l] == Color.BLACK.getRGB() && tmp[k][l] != Color.BLACK.getRGB()) {
                    return false; // Не совпадает, возвращаем false
                }
                else if(structPattern[k][l] == Color.WHITE.getRGB() && tmp[k][l] != Color.WHITE.getRGB()){
                    return false;
                }
            }
        }
    
        // Если все элементы structPattern равны 1 и соответствующие элементы в tmp также равны 1, возвращаем true
        return true;
    }

    public static int [][] binarizeImage(String pathToImage) throws IOException{
        File file = new File(pathToImage);
        int [][]greyPicture = greyGrad(file);
        greyPicture = bradleyThreshold(greyPicture);
        writeGrayImage(greyPicture, "result.png");
        return greyPicture;
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
                    binaryImage[i][j] = Color.BLACK.getRGB();
                else
                    binaryImage[i][j] = Color.WHITE.getRGB();
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