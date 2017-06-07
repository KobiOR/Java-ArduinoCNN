/**
 * Created by orrko_000 on 26/05/2017.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.imageio.ImageIO;


public class DataSetCreator {
    static PrintWriter writer=null;
    static BufferedReader reader =null;
    static int imageCouner=0;

        static final FilenameFilter IMAGE_FILTER = new FilenameFilter()
        {
            @Override
            public boolean accept(final File dir, final String name)
            {
                for (final String ext : Utils.EXTENSIONS) {
                    if (name.endsWith("." + ext)) {
                        return (true);
                    }
                }
                return (false);
            }
        };
    public static void convertImageToArray(BufferedImage image,ArrayList inputList,ArrayList outputList,boolean light){

            ArrayList<Float> tempList=new ArrayList<Float>();
            final int width = image.getWidth();
            final int height = image.getHeight();

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int pixel = image.getRGB(i, j);
                    float red = (pixel >> 16) & 0xff;//RED
                    float green = (pixel >> 8) & 0xff;//GREEN
                    float blue = (pixel) & 0xff;//BLUE
                   if(!Utils.WRITE_TO_FILE_STATE) {
                       tempList.add(red);
                       tempList.add(green);
                       tempList.add(blue);
                   }
                }
            }

                float[] arr=new float[tempList.size()];
                for (int j = 0; j <tempList.size() ; j++)
                    arr[j]=tempList.get(j);

                inputList.add(arr);

                if (light)
                {
                     outputList.add(Utils.GREEN_CLASSIFICATION);
                    if (Utils.WRITE_TO_FILE_STATE)
                        writeImagesToFIle(arr,1);
                }
                else {
                    outputList.add(Utils.RED_CLASSIFICATION);
                    if (Utils.WRITE_TO_FILE_STATE)
                        writeImagesToFIle(arr,0);
                }
        tempList.clear();

    }
    public int getImageCounter() {
            return Utils.imagesRead;
        }
    public void start(ArrayList myTrainingInputs, ArrayList myTrainingOutputs){

                if (Utils.RED_DIR.isDirectory()) {
                    for (final File f : Utils.RED_DIR.listFiles(IMAGE_FILTER))
                    {
                        BufferedImage img = null;

                        try {
                            img = ImageIO.read(f);
                            img = this.getScaledImage(img);
                            convertImageToArray(img,myTrainingInputs,myTrainingOutputs,false);
                            System.out.println("Read Image Number "+ Utils.imagesRead +": "+ f.getName());
                            Utils.imagesRead++;

                        } catch (final IOException e) {
                            // handle errors here
                        }
                    }
                }
            if (Utils.GREEN_DIR.isDirectory()) {
                for (final File f : Utils.GREEN_DIR.listFiles(IMAGE_FILTER)) {
                    BufferedImage img = null;

                    try {
                        img = ImageIO.read(f);
                        img = this.getScaledImage(img);

                        convertImageToArray(img,myTrainingInputs,myTrainingOutputs,true);
                        System.out.println("Read Image Number "+ Utils.imagesRead +": "+ f.getName());
                        Utils.imagesRead++;

                    }
                    catch (final IOException e) {
                        // handle errors here
                    }
                }
            }
        randomiseData( myTrainingInputs,  myTrainingOutputs);

    }
    public BufferedImage getScaledImage(Image srcImg){
        BufferedImage resizedImg = new BufferedImage(Utils.WIDTH, Utils.HEIGHT , Transparency.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, Utils.WIDTH, Utils.HEIGHT, null);
        g2.dispose();
        return resizedImg;
    }
    public static float[] convertImageToArray(BufferedImage image){
        ArrayList<Float> tempList=new ArrayList<Float>();
        final int width = image.getWidth();
        final int height = image.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = image.getRGB(i, j);
                float red = (pixel >> 16) & 0xff;//RED
                float green = (pixel >> 8) & 0xff;//GREEN
                float blue = (pixel) & 0xff;//BLUE
                tempList.add(red);
                tempList.add(green);
                tempList.add(blue);
            }
        }
        float[] arr=new float[tempList.size()];
        for (int j = 0; j <tempList.size() ; j++) {
            arr[j]=tempList.get(j);
        }
        return arr;
    }
    public void randomiseData(ArrayList myTrainingInputs, ArrayList myTrainingOutputs){

        long seed = System.nanoTime();
        Collections.shuffle(myTrainingInputs, new Random(seed));
        Collections.shuffle(myTrainingOutputs, new Random(seed));


    }
    public static void writeImagesToFIle(float[] arr,float classification){
        try{
            if(writer==null)
            {        writer = new PrintWriter(Utils.IMAGES_FILE_NAME, "UTF-8");
                     writer.println(classification);
                     writer.println(arr);
            }
            else{
                writer.println(classification);
                writer.println(arr);
            }
    //        writer.close();
        } catch (IOException e) {
            // do something
        }



    }
    public void trainNetworkWithImage(NeuralNetwork NN, BufferedImage image, float classification){

        float [] arr=convertImageToArray(image);
        NN.trainNetwork(arr,classification);



    }
}

