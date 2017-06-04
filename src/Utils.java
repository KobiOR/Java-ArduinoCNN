import java.io.File;

/**
 * Created by orrko_000 on 27/05/2017.
 */
public final class Utils {
    static int imagesRead=0;
    static final  int WIDTH=30;
    static final  int HEIGHT=30;
    static final private int  DIMENSION=3;
    static final int CNN_DIMENSION=WIDTH*HEIGHT*DIMENSION;
    static final float []GREEN_CLASSIFICATION={1.0f};
    static final float[] RED_CLASSIFICATION={0.1f};
    static boolean WRITE_TO_FILE_STATE=true;

    static boolean LEARNING_STATE=true;
    static boolean RANDOMISE_BIAS=false;
    static int CYCLE_LIMIT=30;
    static float LEARNING_RATE=0.0001f;

    static final File GREEN_DIR = new File("Dataset\\1");
    static final File RED_DIR= new File("Dataset\\2");
    static final String[] EXTENSIONS = new String[]{"gif", "png", "bmp","jpeg","jpg" };


}
