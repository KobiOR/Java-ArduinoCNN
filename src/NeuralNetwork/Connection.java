package NeuralNetwork;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by orrko_000 on 13/05/2017.
 */
public class Connection implements Serializable {
    private static final long serialVersionUID = 2L;

    float connEntry;
    float weight;
    float connExit;
    final Random rand = new Random();

    Connection(){
        randomiseWeight();
    }
    Connection(float tempWeight){
        setWeight(tempWeight);
    }
    void setWeight(float tempWeight){
        weight=tempWeight;
    }
    void randomiseWeight(){
        //TODO Check this!
         setWeight(rand.nextFloat()%2-1);

    }
    float calcConnExit(float tempInput){
        connEntry = tempInput;
        connExit = connEntry * weight;
        return connExit;
    }
}
