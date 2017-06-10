package Utils;//Hello world

import Utils.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import Utils.DataSetCreator;

import static java.lang.Thread.sleep;

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
        System.out.println("Loading Neural Netwotk Model...");
        sleep(750);
        System.out.println("Loading Layers...");
        sleep(750);
        System.out.println("Network Ready...");

        start();

    }
    public static void start() {
         final File dir = new File("C:\\Users\\ohanako\\Desktop\\Data");

        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (dir.isDirectory()) {
           for (final File f : dir.listFiles((IMAGE_FILTER) )) {
               BufferedImage img = null;

               try {
                   img = ImageIO.read(f);
                   checkImage(img,f);

               } catch (final Exception e) {
                   e.printStackTrace();

               }
           }
       }


   }

    }

    public static void checkImage(BufferedImage image,File f) throws InterruptedException {
        int redCounter=0;
        int greenCounter=0;
        boolean check=false;
        ArrayList<Float> tempList=new ArrayList<Float>();
        final int width = image.getWidth();
        final int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = image.getRGB(i, j);
                float red = (pixel >> 16) & 0xff;//RED
                float green = (pixel >> 8) & 0xff;//GREEN
                float blue = (pixel) & 0xff;//BLUE

                if (red>170 && green<150 && blue<150) {
                    check=true;
                    redCounter++;
                }
                if (green>170 && red<150 && blue<150){
                    check=true;
                    greenCounter++;


                }

            }
        }

        if(check)
        delete(f);
        sleep(500);
        if (redCounter>greenCounter)
            System.out.println("Red:"+(100-((float)redCounter/(width*height))));
        else
            System.out.println("Green:"+(100-((float)greenCounter/(width*height))));

    }
        public static void delete(File f) {
            try {


                if (f.delete()) {
                } else {
                    System.out.println("Delete operation is failed.");
                }

            } catch (Exception e) {

                e.printStackTrace();


            }
        }
}




