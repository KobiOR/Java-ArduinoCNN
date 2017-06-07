import Utils.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Created by orrko_000 on 06/06/2017.
 */
public class TestResolution {
    public static void main(String[] args) throws Exception {
        int imageCounter=0;
        int width=0;
        int height=0;
        final FilenameFilter IMAGE_FILTER = new FilenameFilter()
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

        for (final File f : new File("C:\\Users\\orrko_000\\Desktop\\Dropbox\\Green").listFiles(IMAGE_FILTER)) {
            imageCounter++;
            BufferedImage img = null;
            try {
                img = ImageIO.read(f);
                width+=img.getWidth();
                height+=img.getHeight();

            }
            catch (final IOException e)
            {
e.printStackTrace();
            }


        }


        System.out.println("Images:"+imageCounter);
        System.out.println("Width AVG:"+width/imageCounter);
        System.out.println("Height AVG:"+height/imageCounter);


    }

    }


