import java.io.File;

/**
 * Created by orrko_000 on 27/05/2017.
 */
public final class Utils {
    static float BEST_TRAIN_ERROR=9999;
    static int BEST_TRAIN_ERROR_CYCLE=0;
    static int imagesRead=0;
    static final  int WIDTH=15;
    static final  int HEIGHT=35;
    static final private int DIMENSION=3;
    static final int CNN_DIMENSION=WIDTH*HEIGHT*DIMENSION;
    static final float GREEN_CLASSIFICATION=1.0f;
    static final float RED_CLASSIFICATION=0.0f;
    static boolean WRITE_TO_FILE_STATE=false;
    static boolean LEARNING_STATE=true;
    static boolean RANDOMISE_BIAS=true;
    static int CYCLE_LIMIT=20;
    static float LEARNING_RATE=0.01f;
    static String IMAGES_FILE_NAME="images.bin";
//    static final File GREEN_DIR = new File("/home/colman/P/Dataset/Green");
//    static final File RED_DIR= new File("/home/colman/P/Dataset/Red");
    static final File GREEN_DIR = new File("Dataset\\Green");
    static final File RED_DIR= new File("Dataset\\Red");
    static final String[] EXTENSIONS = new String[]{"gif", "png", "bmp","jpeg","jpg","JPG" };


}
//
//    The settings start the Java virtual machine with a minimum heap memory of 128M, a max heap size of 512M and a max perm gen size of 512M.
//        You will need 1 GB of memory if the perm gen memory is filled up.
//
//        -Xms128m -Xmx512m -XX:MaxPermSize=512m
//http://www.jcuda.org/tutorial/TutorialIndex.html
