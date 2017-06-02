/**
 * Created by orrko_000 on 26/05/2017.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class DataSetCreator {


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


    public static void convertImageToArray(BufferedImage image,ArrayList inputList,ArrayList outputList,boolean light)    {

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
            }
                float[] arr=new float[tempList.size()];
                for (int j = 0; j <tempList.size() ; j++) {
                    arr[j]=tempList.get(j);
                }
                inputList.add(arr);
                if (light)
                outputList.add(Utils.GREEN_CLASSIFICATION);
                else
                outputList.add(Utils.RED_CLASSIFICATION);
                tempList.clear();
            }
    public int getImageCounter() {
            return Utils.imagesRead;
        }
    public void start(ArrayList myTrainingInputs, ArrayList myTrainingOutputs){

                if (Utils.RED_DIR.isDirectory()) {
                    for (final File f : Utils.RED_DIR.listFiles(IMAGE_FILTER)) {
                        BufferedImage img = null;

                        try {
                            img = ImageIO.read(f);
                            img = this.getScaledImage(img);
                            convertImageToArray(img,myTrainingInputs,myTrainingOutputs,false);
                            System.out.println("Read Image: " + f.getName());
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
                        System.out.println("Read Image: " + f.getName());
                        Utils.imagesRead++;

                    }
                    catch (final IOException e) {
                        // handle errors here
                    }
                }
            }

        }
    public BufferedImage getScaledImage(Image srcImg){
        BufferedImage resizedImg = new BufferedImage(Utils.WIDTH,Utils.HEIGHT , Transparency.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, Utils.WIDTH, Utils.HEIGHT, null);
        g2.dispose();
        return resizedImg;
    }
}

