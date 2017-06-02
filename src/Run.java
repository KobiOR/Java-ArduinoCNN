
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
            NN.addLayer(Utils.CNN_DIMENSION, Utils.CNN_DIMENSION * 2);
            NN.addLayer(Utils.CNN_DIMENSION * 2, 2);
            System.out.println("Finish create the Neural Network");

        } else {
            WriteWeightToFile writer = new WriteWeightToFile();
            NN = writer.readCNN(Utils.WEIGHTS_DIRECTORY);


        }
        System.out.println("Begin Training");
        NN.autoTrainNetwork(myTrainingInputs, myTrainingOutputs, Utils.LEARNING_RATE, Utils.CYCLE_LIMIT);
        System.out.println("Train end; " + dataCreator.getImageCounter() + " Images had transformed");

        //TEST
        testImage(ImageIO.read(new File("C:\\Users\\orrko_000\\Desktop\\black.png")),1.0f,NN,true);
        testImage(ImageIO.read(new File("C:\\Users\\orrko_000\\Desktop\\white.png")),1.0f,NN,false);


        try {

            WriteWeightToFile writer = new WriteWeightToFile();
            writer.writeCNN(NN);
        } catch (Exception e) {

            System.out.print(e.toString());
        }
    }
    public static float testImage(BufferedImage image,float classification,NeuralNetwork NN,boolean light){

        ArrayList testList = new ArrayList();
        ArrayList testListClassification = new ArrayList();
        testListClassification.add(classification);
        BufferedImage img = dataCreator.getScaledImage(image);
        dataCreator.convertImageToArray(img, testList, testListClassification, true);
        NN.processInputsToOutputs((float[]) testList.get(0));
        System.out.println(" OUTPUT=" +  NN.getOutputs());
        return 0;
    }
}




