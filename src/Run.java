//Hello world
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Run  {

    static DataSetCreator dataCreator = new DataSetCreator();
    static NeuralNetwork NN=new NeuralNetwork();
    static WriteObject WO=new WriteObject();
  //  static Server server=new Server();

    public static void main(String[] args) throws Exception {
        ArrayList myTrainingInputs = new ArrayList();
        ArrayList myTrainingOutputs = new ArrayList();

        if (Utils.LEARNING_STATE) {
            System.out.print("Processing Data...");
            dataCreator.start(myTrainingInputs, myTrainingOutputs);
            System.out.println("Done!");

            System.out.print("Creating Layers...");
            NN.addLayer(Utils.CNN_DIMENSION, Utils.CNN_DIMENSION);
            NN.addLayer(Utils.CNN_DIMENSION, Utils.CNN_DIMENSION * 3);
            NN.addLayer(Utils.CNN_DIMENSION*3, Utils.CNN_DIMENSION );
            NN.addLayer(Utils.CNN_DIMENSION, 100 );
            NN.addLayer(100 , 2);
            System.out.println("Done!");

        }
        else if (!Utils.LEARNING_STATE) {
            WriteObject WO=new WriteObject();
            NN = WO.load();
        }

        System.out.print("Training The Network...");
        NN.autoTrainNetwork(myTrainingInputs, myTrainingOutputs, Utils.LEARNING_RATE, Utils.CYCLE_LIMIT);
        System.out.println("Train end; " + dataCreator.getImageCounter() + " Images had transformed");

        System.out.print("Testing The Network...");
        testImage(ImageIO.read(new File("RED.jpg")));
        System.out.println("Done!");

        System.out.print("Saved The CNN Model....");
        WO.save(NN);
        System.out.println("Done!");



    }

    public static float testImage(BufferedImage image){

        BufferedImage img = dataCreator.getScaledImage(image);
        NN.processInputsToOutputs(DataSetCreator.convertImageToArray(img));
        System.out.println(" OUTPUT=" +  NN.getOutputs()[0] );

        return NN.getOutputs()[0];
    }
}




