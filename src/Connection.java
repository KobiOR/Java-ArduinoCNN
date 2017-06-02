import java.util.Random;

/**
 * Created by orrko_000 on 13/05/2017.
 */
public class Connection {
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
    //Function to randomise the weight of this connection
    void randomiseWeight(){
        //TODO Check this!
        setWeight(rand.nextFloat()%2-1);
    }
    //Function to calculate and store the output of this Connection
    float calcConnExit(float tempInput){
        connEntry = tempInput;
        connExit = connEntry * weight;
        return connExit;
    }
}
