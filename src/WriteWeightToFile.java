import java.io.*;
import java.util.ArrayList;

/**
 * Created by orrko_000 on 26/05/2017.
 */
public class WriteWeightToFile {

    public void writeCNN(NeuralNetwork CNN) throws IOException {
        ArrayList<Layer> layers = CNN.layers;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Utils.WEIGHTS_DIRECTORY))) {

            bw.write(String.valueOf(layers.size()));
            for (int i = 0; i < layers.size(); i++) {
                bw.write(String.valueOf(layers.get(i).neurons.size()));
                for (int j = 0; j < layers.get(i).neurons.size(); j++) {
                    bw.write(String.valueOf(layers.get(i).neurons.get(j).connections.size()));
                    for (int k = 0; k < layers.get(i).neurons.get(j).connections.size(); k++) {
                        bw.write(String.valueOf(layers.get(i).neurons.get(j).bias));
                        bw.write(String.valueOf(layers.get(i).neurons.get(j).connections.get(k).connEntry));
                        bw.write(String.valueOf(layers.get(i).neurons.get(j).connections.get(k).connExit));
                        bw.write(String.valueOf(layers.get(i).neurons.get(j).connections.get(k).weight));

                    }
                }

            }//WRITE NUMBER OF  LAYERS
            bw.write(String.valueOf(layers.size()));//WRITE NUMBER OF  LAYERS

            // no need to close it.
            //bw.close();

            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        }


    }

    public NeuralNetwork readCNN(String file) {
        NeuralNetwork NN = new NeuralNetwork();
        NN.layers = new ArrayList<Layer>();
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(Utils.WEIGHTS_DIRECTORY));

            char  []temp=new char[4];
            int numberOfLayers=br.read(temp);
            for (int i = 0; i <numberOfLayers ; i++) {
                int numberOfNeurons=br.read(temp);
                NN.layers.add(new Layer(numberOfNeurons,numberOfNeurons));
                for (int j = 0; j <numberOfNeurons ; j++) {

                }

            }

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
        return NN;


    }
}