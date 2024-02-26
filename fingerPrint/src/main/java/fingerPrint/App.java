// package fingerPrint;

// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.IOException;
// import javax.imageio.ImageIO;
// import java.awt.image.PixelGrabber;
// import java.awt.Color;
// import java.awt.Graphics;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.IOException;

// public class App {

//     // a = 0.3
//     // b = 0.59
//     // c = 0.11

//     // tiff +
//     // a = 0.7
//     // b = 0.89
//     // c = 0.5

//     // tiff + (1)
//     // a = 0.75
//     // b = 0.89
//     // c = 0.55

//     // tiff + (2)
//     // a = 0.73
//     // b = 0.89
//     // c = 0.53

//     public static int[][] binary(BufferedImage img, double a, double b, double c) {
//         int[][] bImg = new int[img.getWidth()][img.getHeight()];

//         for (int i = 0; i < img.getWidth(); i++) {
//             for (int j = 0; j < img.getHeight(); j++) {
//                 int[] t = new int[3];
//                 img.getRaster().getPixel(i, j, t);

//                 double p = t[0] * a + t[1] * b + t[2] * c;
//                 if (p > 128) {
//                     bImg[i][j] = 1;
//                 } else {
//                     bImg[i][j] = 0;
//                 }
//             }
//         }

//         return bImg;
//     }

//     public static BufferedImage createBinaryImage(int[][] binaryPixels) {
//         int width = binaryPixels.length;
//         int height = binaryPixels[0].length;

//         BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//         Graphics g = binaryImage.getGraphics();

//         for (int x = 0; x < width; x++) {
//             for (int y = 0; y < height; y++) {
//                 if (binaryPixels[x][y] == 1) {
//                     g.setColor(Color.WHITE);
//                 } else {
//                     g.setColor(Color.BLACK);
//                 }
//                 g.fillRect(x, y, 1, 1);
//             }
//         }

//         g.dispose();

//         return binaryImage;
//     }

//     public static void saveImage(BufferedImage image, String outputPath) {
//         File outputFile = new File(outputPath);
//         try {
//             ImageIO.write(image, "png", outputFile);
//             System.out.println("Binary image saved successfully.");
//         } catch (IOException e) {
//             System.out.println("Error saving binary image: " + e.getMessage());
//         }
//     }

//     public static int[][][] createImageArray(String[] imagePaths) {
//         // Получаем размеры первого изображения
//         int width = 0;
//         int height = 0;
//         try {
//             File firstImageFile = new File(imagePaths[0]);
//             BufferedImage firstImage = ImageIO.read(firstImageFile);
//             width = firstImage.getWidth();
//             height = firstImage.getHeight();
//         } catch (Exception e) {
//             e.printStackTrace();
//             return null;
//         }

//         // Создаем трехмерный массив
//         int[][][] imageArray = new int[height][width][imagePaths.length];

//         // Заполняем массив значениями пикселей из каждого изображения
//         for (int k = 0; k < imagePaths.length; k++) {
//             try {
//                 File imageFile = new File(imagePaths[k]);
//                 BufferedImage image = ImageIO.read(imageFile);
//                 for (int i = 0; i < height; i++) {
//                     for (int j = 0; j < width; j++) {
//                         int rgb = image.getRGB(j, i);
//                         imageArray[i][j][k] = rgb;
//                     }
//                 }
//             } catch (Exception e) {
//                 e.printStackTrace();
//                 return null;
//             }
//         }

//         return imageArray;
//     }

//     public static int[][] combineImage(int[][][] imageArray){
//         int [][] resImage = new int[imageArray.length][imageArray[0].length];
        
//         for(int i = 0; i < imageArray.length;i++){
//             for (int j = 0; j < imageArray[0].length; j++) {
//                 int countBlack = 0;
//                 int countWhite = 0;
//                 for (int k = 0; k < imageArray[0][0].length; k++) {
//                     if(imageArray[i][j][k] == Color.WHITE.getRGB()){
//                         countWhite++;
//                     }
//                     else{
//                         countBlack++;
//                     }
//                 }
//                 // if(i < imageArray.length/3 &&  j < imageArray.length/3){
//                 //     resImage[j][i] = countBlack > 4? 0:1;
//                 // }
//                 // else if(i < imageArray.length/3 &&  j < (imageArray.length * 2)/3){
//                 //     resImage[j][i] = countBlack > 5? 0:1;
//                 // }
//                 // else if(i < imageArray.length/3 &&  j < imageArray.length){
//                 //     resImage[j][i] = countBlack > 5? 0:1;
//                 // }
//                 // else if(i < (imageArray.length * 2)/3 &&  j < imageArray.length/3){
//                 //     resImage[j][i] = countBlack > 9? 0:1;
//                 // }
//                 // else if(i < (imageArray.length * 2)/3 &&  j < (imageArray.length * 2)/3){
//                 //     resImage[j][i] = countBlack > 13? 0:1;
//                 // }
//                 // else if(i < (imageArray.length * 2)/3 &&  j < imageArray.length){
//                 //     resImage[j][i] = countBlack > 11? 0:1;
//                 // }
//                 // else if(i < imageArray.length &&  j < imageArray.length/3){
//                 //     resImage[j][i] = countBlack > 7? 0:1;
//                 // }
//                 // else if(i < imageArray.length &&  j < (imageArray.length * 2)/3){
//                 //     resImage[j][i] = countBlack > 7? 0:1;
//                 // }
//                 // else if(i < imageArray.length &&  j < imageArray.length){
//                 //     resImage[j][i] = countBlack > 6? 0:1;
//                 // }

//                 if(i > (imageArray.length + 0.0) / 1.8 && j > (imageArray[0].length + 0.0) / 1.8){
//                     resImage[j][i] = countBlack > (imageArray[0][0].length/2) ? 0:1;
//                 }
//                 else{
//                     resImage[j][i] = countBlack >(imageArray[0][0].length / 4.457878) ? 0:1;
//                 }
//             }
//         }
        
//         return resImage;
//     }

//     public static void main(String[] args) throws IOException {
//         // Пример использования
//         BufferedImage img = ImageIO.read(new File("fingerPrint/src/main/java/fingerPrint/img/101_2.tif"));
//         int quantityRound = 10;
//         String [] maStrings = new String[quantityRound];
//         double a = 0.75;
//         double b = 0.89;
//         double c = 0.6;

//         double stepA = (1.15 - a) / (quantityRound + 0.0);
//         double stepB = (1.15 - b) / (quantityRound + 0.0);
//         double stepC = (1.15 - c) / (quantityRound + 0.0);
//         for (int i = 0; i < quantityRound; i++) {
//             int[][] arrayImage = binary(img,a,b,c);
//             BufferedImage binaryImage = createBinaryImage(arrayImage);
//             String path = "fingerPrint/src/main/java/fingerPrint/tmpBinary/binary_image_"+ i + ".png";
//             saveImage(binaryImage, path);
//             a += stepA;
//             b += stepB;
//             c += stepC;
//             maStrings[i] = path;
//         }

//         int [][][] massArray = createImageArray(maStrings);
//         int [][] imageRes = combineImage(massArray);
//         BufferedImage res = createBinaryImage(imageRes);
//         saveImage(res, "result.png");
//     }
// }


