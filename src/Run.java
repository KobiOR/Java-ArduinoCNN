
import javax.imageio.ImageIO;
import javax.rmi.CORBA.Util;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Run {

    static DataSetCreator dataCreator = new DataSetCreator();

    public static void main(String[] args) throws IOException {
        ArrayList myTrainingInputs = new ArrayList();
        ArrayList myTrainingOutputs = new ArrayList();
        NeuralNetwork NN;

        if (Utils.LEARNING_STATE) {
            dataCreator.start(myTrainingInputs, myTrainingOutputs);
            NN = new NeuralNetwork();
            NN.addLayer(Utils.CNN_DIMENSION, Utils.CNN_DIMENSION);
            NN.addLayer(Utils.CNN_DIMENSION, Utils.CNN_DIMENSION * 3);
            NN.addLayer(Utils.CNN_DIMENSION * 3, 2);
            System.out.println("Finish create the Neural Network");

        } else {
            WriteWeightToFile writer = new WriteWeightToFile();
            NN = writer.readCNN(Utils.WEIGHTS_DIRECTORY);


        }
        System.out.println("Begin Training");
        NN.autoTrainNetwork(myTrainingInputs, myTrainingOutputs, Utils.LEARNING_RATE, Utils.CYCLE_LIMIT);
        System.out.println("Train end; " + dataCreator.getImageCounter() + " Images had transformed");

        //TEST

        ArrayList testList = new ArrayList();
        ArrayList testListClassification = new ArrayList();
        testListClassification.add((float) 1.0f);
        BufferedImage img = null;
        BufferedImage image = ImageIO.read(new File("C:\\Users\\orrko_000\\Desktop\\black.png"));
        img = dataCreator.getScaledImage(image);
        dataCreator.convertImageToArray(img, testList, testListClassification, true);
        NN.processInputsToOutputs((float[]) myTrainingInputs.get(1));
        float[] myOutputDataA2 = NN.getOutputs();
        System.out.println(" INPUT = RED; OUTPUT=" + myOutputDataA2[0]);

        try {

            WriteWeightToFile writer = new WriteWeightToFile();
            writer.writeCNN(NN);
        } catch (Exception e) {

            System.out.print(e.toString());
        }
    }
}




