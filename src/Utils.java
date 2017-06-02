import java.io.File;

/**
 * Created by orrko_000 on 27/05/2017.
 */
public final class Utils {
    static int imagesRead=0;
    static final  int WIDTH=3;
    static final  int HEIGHT=3;
    static final private int  DIMENSION=3;
    static final int CNN_DIMENSION=WIDTH*HEIGHT*DIMENSION;
    static final float []GREEN_CLASSIFICATION={1.0f};
    static final float[] RED_CLASSIFICATION={0.1f};

    static final String WEIGHTS_DIRECTORY = "weights.text";
    static boolean LEARNING_STATE=true;
    static int CYCLE_LIMIT=100;
    static float LEARNING_RATE=0.00001f;
    static final File GREEN_DIR = new File("Dataset\\1");
    static final File RED_DIR= new File("Dataset\\2");
    static final String[] EXTENSIONS = new String[]{"gif", "png", "bmp","jpeg","jpg" };


}
