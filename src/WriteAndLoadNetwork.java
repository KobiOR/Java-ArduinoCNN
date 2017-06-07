/**
 * Created by orrko_000 on 02/06/2017.
 */

import java.io.*;
class WriteAndLoadNetwork {

    public void save(NeuralNetwork NN) throws Exception {

        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        try {
            String name="CNN"+Utils.BEST_TRAIN_ERROR+".ser";
            fout = new FileOutputStream(name);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(NN);
            System.out.println("Neural Network Saved");
        }
        catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }
    public NeuralNetwork load(){
        try {
            FileInputStream fin = new FileInputStream("CNN.ser");
            ObjectInputStream ois = new ObjectInputStream(fin);
            NeuralNetwork N1 = (NeuralNetwork) ois.readObject();
            return N1;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;


    }
    public void serializeAddressJDK7(NeuralNetwork NN) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream("NN2.ser"))) {

            oos.writeObject(NN);
            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
