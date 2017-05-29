
import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.util.ArrayList;

public class Run {

    static int WIDTH=10;
    static int HEIGHT=10;
    static int DIMENSION=3;
    static int CNN_DIMENSION=WIDTH*HEIGHT*DIMENSION;
    static DataSetCreator dataCreator=new DataSetCreator();

    public static void main(String[] args) throws IOException {
        ArrayList myTrainingInputs = new ArrayList();
        ArrayList myTrainingOutputs = new ArrayList();
        NeuralNetwork NN;

        if (Utils.LEARNING_STATE) {
            dataCreator.start(myTrainingInputs, myTrainingOutputs);
            NN = new NeuralNetwork();
            NN.addLayer(300, 300);
          //  NN.addLayer(300, 900);
         //   NN.addLayer(900, 1800);
            NN.addLayer(300, 2);
        }
        else {
            WriteWeightToFile writer=new WriteWeightToFile();
            NN= writer.readCNN(Utils.WEIGHTS_DIRECTORY);





        }
        System.out.println("Begin Training");
        NN.autoTrainNetwork(myTrainingInputs, myTrainingOutputs, Utils.LEARNING_RATE, Utils.CYCLE_LIMIT);
        System.out.println("Train end; "+dataCreator.getImageCounter()+" Images had transformed");

        //TEST
//        NN.processInputsToOutputs((float[]) myTrainingInputs.get(0));
//        float[] myOutputDataA1=NN.getOutputs();
//        System.out.println("INPUT = Green; OUTPUT=" + myOutputDataA1[0]);

        NN.processInputsToOutputs((float[]) myTrainingInputs.get(1));
        float[] myOutputDataA2=NN.getOutputs();
        System.out.println(" INPUT = RED; OUTPUT=" + myOutputDataA2[0]);

        WriteWeightToFile writer = new WriteWeightToFile();
        writer.writeCNN(NN);
    }

    }




