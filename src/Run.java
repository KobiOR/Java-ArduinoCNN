//Hello world
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Run {

    static DataSetCreator dataCreator = new DataSetCreator();
    static NeuralNetwork NN;

    public static void main(String[] args) throws IOException {
        ArrayList myTrainingInputs = new ArrayList();
        ArrayList myTrainingOutputs = new ArrayList();

        if (Utils.LEARNING_STATE) {
            dataCreator.start(myTrainingInputs, myTrainingOutputs);
            NN = new NeuralNetwork();
            NN.addLayer(Utils.CNN_DIMENSION, Utils.CNN_DIMENSION);
            NN.addLayer(Utils.CNN_DIMENSION, Utils.CNN_DIMENSION * 3);
            NN.addLayer(Utils.CNN_DIMENSION*3, Utils.CNN_DIMENSION );
            NN.addLayer(Utils.CNN_DIMENSION, 100 );
            NN.addLayer(100 , 2);


            System.out.println("Finish create the Neural Network");

        } else {
            WriteWeightToFile writer = new WriteWeightToFile();
            NN = writer.readCNN(Utils.WEIGHTS_DIRECTORY);


        }
        System.out.println("Begin Training");
        NN.autoTrainNetwork(myTrainingInputs, myTrainingOutputs, Utils.LEARNING_RATE, Utils.CYCLE_LIMIT);
        System.out.println("Train end; " + dataCreator.getImageCounter() + " Images had transformed");

        //TEST
        testImage(ImageIO.read(new File("black.png")));
        testImage(ImageIO.read(new File("black2.png")));
        testImage(ImageIO.read(new File("black3.png")));
        testImage(ImageIO.read(new File("black4.png")));
        testImage(ImageIO.read(new File("black.png")));

       // testImage(ImageIO.read(new File("white.bmp")));


        try {

            WriteWeightToFile writer = new WriteWeightToFile();
            writer.writeCNN(NN);
        } catch (Exception e) {

            System.out.print(e.toString());
        }
    }
    public static float testImage(BufferedImage image){

        BufferedImage img = dataCreator.getScaledImage(image);
        NN.processInputsToOutputs(DataSetCreator.convertImageToArray(img));
        System.out.println(" OUTPUT=" +  NN.getOutputs()[0] );

        return NN.getOutputs()[0];
    }
}




