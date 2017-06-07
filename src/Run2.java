//Hello world

import Utils.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import Utils.DataSetCreator;
public class Run2 {

    static DataSetCreator dataCreator = new DataSetCreator();
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

    public static void main(String[] args) throws Exception {
        start();

    }
    public static void start() {
         final File dir = new File("Dataset");

        while (true) {
       System.out.print(".");
       if (dir.isDirectory()) {
           for (final File f : dir.listFiles((IMAGE_FILTER) )) {
               BufferedImage img = null;

               try {
                   img = ImageIO.read(f);
                   checkImage(img);

               } catch (final Exception e) {
                   e.printStackTrace();

               }
           }
       }


   }

    }

    public static void checkImage(BufferedImage image){
        int redCounter=0;
        int greenCounter=0;
        ArrayList<Float> tempList=new ArrayList<Float>();
        final int width = image.getWidth();
        final int height = image.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = image.getRGB(i, j);
                float red = (pixel >> 16) & 0xff;//RED
                float green = (pixel >> 8) & 0xff;//GREEN
                float blue = (pixel) & 0xff;//BLUE

                if (red>200 && green<30 && blue<30)
                    redCounter++;
                if (green>200 && red<30 && blue<30)
                    greenCounter++;

            }
        }

        System.out.println("Red:"+redCounter);
        System.out.println("Green:"+greenCounter);

    }

}




