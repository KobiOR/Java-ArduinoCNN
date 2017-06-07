//Hello world

import NeuralNetwork.NeuralNetwork;
import Utils.Utils;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import Utils.DataSetCreator;
import Utils.*;
public class Run  {

    static DataSetCreator dataCreator = new DataSetCreator();
    static NeuralNetwork NN=new NeuralNetwork();
    static WriteAndLoadNetwork WO=new WriteAndLoadNetwork();
  //  static Server server=new Server();

    public static void main(String[] args) throws Exception {
        ArrayList myTrainingInputs = new ArrayList();
        ArrayList myTrainingOutputs = new ArrayList();


        if (Utils.LEARNING_STATE) {
            System.out.print("Processing Data...");
           // dataCreator.readImagesFromFile();
            dataCreator.start(myTrainingInputs, myTrainingOutputs);
            System.out.println("Done!");

            System.out.print("Creating Layers...");
            NN.addLayer(Utils.CNN_DIMENSION, Utils.CNN_DIMENSION);
            NN.addLayer(Utils.CNN_DIMENSION, Utils.CNN_DIMENSION * 3);
            NN.addLayer(Utils.CNN_DIMENSION*3  , 2);


            System.out.print("Training The Network...");
            NN.autoTrainNetwork(myTrainingInputs, myTrainingOutputs, 0.0001f, Utils.CYCLE_LIMIT);
            System.out.println("Train end; " + dataCreator.getImageCounter() + " Images had transformed");
            System.out.println("Minimum:"+Utils.BEST_TRAIN_ERROR+" At Cycle Number: "+Utils.BEST_TRAIN_ERROR_CYCLE);
            System.out.print("Saved The CNN Model....");
            WO.save(NN);
            System.out.println("Done!");
        }
        else {
            WriteAndLoadNetwork WO=new WriteAndLoadNetwork();
            NN = WO.load();
        }
//
//        System.out.print("Testing The Network...");
//        testImage(ImageIO.read(new File("RED.jpg")));
//        System.out.println("Done!");

        BufferedImage img = dataCreator.getScaledImage( ImageIO.read(new File("GREEN.JPG")));
        NN.processInputsToOutputs(DataSetCreator.convertImageToArray(img));
        System.out.println(" OUTPUT=" +  NN.getOutputs()[0]+":"+ NN.getOutputs()[1] );

         img = dataCreator.getScaledImage( ImageIO.read(new File("RED.JPG")));
        NN.processInputsToOutputs(DataSetCreator.convertImageToArray(img));
        System.out.println(" OUTPUT=" +  NN.getOutputs()[0]+":"+ NN.getOutputs()[1] );
    }

    public static void testImage(BufferedImage image){

        BufferedImage img = null;
        try {
            img = dataCreator.getScaledImage( ImageIO.read(new File("GREEN.JPG")));
            NN.processInputsToOutputs(DataSetCreator.convertImageToArray(img));
            System.out.println(" OUTPUT=" +  NN.getOutputs()[0]+":"+ NN.getOutputs()[1] );

            img = dataCreator.getScaledImage( ImageIO.read(new File("RED.JPG")));
            NN.processInputsToOutputs(DataSetCreator.convertImageToArray(img));
            System.out.println(" OUTPUT=" +  NN.getOutputs()[0]+":"+ NN.getOutputs()[1] );
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}




