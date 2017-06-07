import NeuralNetwork.NeuralNetwork;
import Utils.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by orrko_000 on 06/06/2017.
 */
public class TestNetwork {
    public static void main(String[] args) throws Exception {

        DataSetCreator dataCreator = new DataSetCreator();
        WriteAndLoadNetwork WO=new WriteAndLoadNetwork();
        NeuralNetwork NN = WO.load();


        BufferedImage img = dataCreator.getScaledImage( ImageIO.read(new File("GREEN.JPG")));
        NN.processInputsToOutputs(DataSetCreator.convertImageToArray(img));
        System.out.println(" OUTPUT=" +  NN.getOutputs()[0]+":"+ NN.getOutputs()[1] );




    }


    }

