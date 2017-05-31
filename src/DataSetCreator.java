/**
 * Created by orrko_000 on 26/05/2017.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class DataSetCreator {

        static int imagesRead=0;

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


        private static void convertImageToArray(BufferedImage image,ArrayList inputList,ArrayList outputList,boolean light) {

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
                outputList.add(new float[]{5});
                else
                outputList.add(new float[]{1});

                tempList.clear();
            }

        public int getImageCounter() {
            return imagesRead;
        }
        public void start(ArrayList myTrainingInputs, ArrayList myTrainingOutputs){

                if (Utils.RED_DIR.isDirectory()) {
                    for (final File f : Utils.RED_DIR.listFiles(IMAGE_FILTER)) {
                        BufferedImage img = null;

                        try {
                            img = ImageIO.read(f);
                            convertImageToArray(img,myTrainingInputs,myTrainingOutputs,false);
                            System.out.println("Read Image: " + f.getName());
                            imagesRead++;

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
                        convertImageToArray(img,myTrainingInputs,myTrainingOutputs,true);
                        System.out.println("Read Image: " + f.getName());
                        imagesRead++;

                    }
                    catch (final IOException e) {
                        // handle errors here
                    }
                }
            }

        }
    }

