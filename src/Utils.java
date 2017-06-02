import java.io.File;

/**
 * Created by orrko_000 on 27/05/2017.
 */
public final class Utils {
    static int imagesRead=0;
    static private int WIDTH=10;
    static private int HEIGHT=10;
    static private int  DIMENSION=3;
    static int CNN_DIMENSION=WIDTH*HEIGHT*DIMENSION;
     static final float []GREEN_CLASSIFICATION={1};
     static final float[] RED_CLASSIFICATION={0};

    public static final String GREEN_LIGHT_DIRECTORY="1";
    public static final String RED_LIGHT_DIRECTORY="2";
    static final String WEIGHTS_DIRECTORY = "weights.text";
    static boolean LEARNING_STATE=true;
    static int CYCLE_LIMIT=100;
    static float LEARNING_RATE=0.01f;
    static final File GREEN_DIR = new File("Dataset\\1");
    static final File RED_DIR= new File("Dataset\\2");
    static final String[] EXTENSIONS = new String[]{"gif", "png", "bmp","jpeg","jpg" };


}
