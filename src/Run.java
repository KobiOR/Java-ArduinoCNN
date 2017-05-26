import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Run {
    static int WIDTH=10;
    static int HEIGHT=10;
    static int DIMENSION=3;
    static int CNN_DIMENSION=WIDTH*HEIGHT*DIMENSION;
    static DataSetCreator dataCreator=new DataSetCreator();

    public static void main(String[] args) throws IOException {
        ArrayList myTrainingInputs = new ArrayList();
        ArrayList myTrainingOutputs = new ArrayList();

        dataCreator.start(myTrainingInputs,myTrainingOutputs);

        File f=new File("C:\\Users\\orrko_000\\Desktop\\a.jpg");
     //   BufferedImage hugeImage = ImageIO.read(f);
       //convertImageToArray(hugeImage,myTrainingInputs,myTrainingOutputs);



        NeuralNetwork NN = new NeuralNetwork();
        NN.addLayer(30, 30);
//        NN.addLayer(10, 100);
//        NN.addLayer(100, 2);

        // NN.addLayer(2, 30);
       // NN.addLayer(2, 2);

        System.out.println("Begin Training");
        NN.autoTrainNetwork(myTrainingInputs, myTrainingOutputs, 0.0001f, 500000);

    }
    private static void convertImageToArray(BufferedImage image,ArrayList inputList,ArrayList outputList) {

        ArrayList<Float> tempList=new ArrayList<Float>();
        final int width = image.getWidth();
        final int height = image.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = image.getRGB(j, i);
                float red = (pixel >> 16) & 0xff;//RED
                float green = (pixel >> 8) & 0xff;//GREEN
                float blue = (pixel) & 0xff;//BLUE
                tempList.add(red);
                tempList.add(green);
                tempList.add(blue);
            }
                float[] arr=new float[tempList.size()];
            for (int j = 0; j <tempList.size() ; j++) {
               arr[i]=tempList.get(i);
            }
             inputList.add(arr);
            outputList.add(new float[]{0});
            tempList.clear();
            }


        }

    }




