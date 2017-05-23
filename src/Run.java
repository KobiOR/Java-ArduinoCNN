import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Run {
    public static void main(String[] args) throws IOException {

        File f=new File("C:\\Users\\orrko_000\\Desktop\\a.jpg");
        BufferedImage hugeImage = ImageIO.read(f);
        float[][] a=convertTo2DWithoutUsingGetRGB(hugeImage);


        float[] ClassificationA = {1};
        float[] ClassificationB = {0};

        ArrayList myTrainingInputs = new ArrayList();
        ArrayList myTrainingOutputs = new ArrayList();

        for (float[] arr:a) {
            myTrainingInputs.add(arr);
            float[] temp=new float[1];
            temp[0]=new Random().nextFloat()%2;

            myTrainingOutputs.add(temp);
        }


        NeuralNetwork NN = new NeuralNetwork();
        NN.addLayer(10, 10);

        System.out.println("Begin Training");
        NN.autoTrainNetwork(myTrainingInputs, myTrainingOutputs, 0.0001f, 500000);

    }  private static float[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        float[][] result = new float[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += ((pixels[pixel] & 0xf) << 24); // alpha
                argb += ( pixels[pixel + 1] & 0xf); // blue
                argb += (( pixels[pixel + 2] & 0xf) << 8); // green
                argb += (( pixels[pixel + 3] & 0xf) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += (pixels[pixel] & 0xff); // blue
                argb += (( pixels[pixel + 1] & 0xff) << 8); // green
                argb += ((pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }




}
