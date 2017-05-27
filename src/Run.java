import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Run {
    static int WIDTH=10;
    static int HEIGHT=10;
    static int DIMENSION=3;
    static int CNN_DIMENSION=WIDTH*HEIGHT*DIMENSION;
    static DataSetCreator dataCreator=new DataSetCreator();

    public static void main(String[] args) throws IOException {
        ArrayList myTrainingInputs = new ArrayList();
        ArrayList myTrainingOutputs = new ArrayList();

        dataCreator.start(myTrainingInputs,myTrainingOutputs);

        NeuralNetwork NN = new NeuralNetwork();
        NN.addLayer(300, 300);
        NN.addLayer(300, 900);
        NN.addLayer(900, 2);

        System.out.println("Begin Training");
        NN.autoTrainNetwork(myTrainingInputs, myTrainingOutputs, 0.01f, 1000);
        System.out.println("Train end; "+dataCreator.getImageCounter()+" Images had transformed");

        //TEST
        NN.processInputsToOutputs((float[]) myTrainingInputs.get(0));
        float[] myOutputDataA1=NN.getOutputs();
        System.out.println("Feed Forward:  INPUT = Green; OUTPUT=" + myOutputDataA1[0]);

        NN.processInputsToOutputs((float[]) myTrainingInputs.get(1));
        float[] myOutputDataA2=NN.getOutputs();
        System.out.println("Feed Forward:  INPUT = RED; OUTPUT=" + myOutputDataA2[0]);
    }

    }




