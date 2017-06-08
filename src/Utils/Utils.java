package Utils;

import java.io.File;

/**
 * Created by orrko_000 on 27/05/2017.
 */
public final class Utils {
    public static float BEST_TRAIN_ERROR=9999;
    public  static int BEST_TRAIN_ERROR_CYCLE=0;
    public    static int imagesRead=0;
    public  static final  int WIDTH=15;
    public  static final  int HEIGHT=35;
    static final private int DIMENSION=3;
    public static final int CNN_DIMENSION=WIDTH*HEIGHT*DIMENSION;
    public  static final float GREEN_CLASSIFICATION=1.0f;
    public  static final float RED_CLASSIFICATION=0.0f;
    public  static boolean WRITE_TO_FILE_STATE=false;
    public static boolean LEARNING_STATE=true;
    public  static boolean RANDOMISE_BIAS=true;
    public  static int CYCLE_LIMIT=20;
    public  static float LEARNING_RATE=0.01f;
    public static String IMAGES_FILE_NAME="images.bin";
    static final File GREEN_DIR = new File("G:\\Dataset\\Green");
    static final File RED_DIR= new File("G:\\Dataset\\Red");
    public static final String[] EXTENSIONS = new String[]{"gif", "png", "bmp","jpeg","jpg","JPG","PNG" };


}
