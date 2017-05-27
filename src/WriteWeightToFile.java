import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by orrko_000 on 26/05/2017.
 */
public class WriteWeightToFile {
    private static final String FILE_NAME = "C:\\Users\\orrko_000\\Desktop\\weights.text";

    public void writeCNN(NeuralNetwork CNN) throws IOException {
        ArrayList<Layer> layers=CNN.layers;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            bw.write(String.valueOf(layers.size()));
            for (int i = 0; i <layers.size();  i++) {
                bw.write(String.valueOf(layers.get(i).neurons.size()));
                for (int j = 0; j <layers.get(i).neurons.size() ; j++) {
                    bw.write(String.valueOf(layers.get(i).neurons.get(j).bias));
                    for (int k = 0; k <layers.get(i).neurons.get(j).connections.size(); k++) {
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


}
