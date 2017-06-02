import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.exp;

/**
 * Created by orrko_000 on 13/05/2017.
 */
public class Neuron  implements Serializable {
    private static final long serialVersionUID = 4L;

        ArrayList<Connection> connections=new ArrayList<Connection>();
        float bias;
        float neuronInputValue;
        float neuronOutputValue;
        float deltaError;
        final Random rand = new Random();

        Neuron(int numOfConnections){
            randomiseBias();
            for(int i=0; i<numOfConnections; i++){
                Connection conn = new Connection();
                addConnection(conn);
            }
        }
        void addConnection(Connection conn){
            connections.add(conn);
        }
        int getConnectionCount(){
            return connections.size();
        }
        void setBias(float tempBias){
            bias = tempBias;
        }
        void randomiseBias(){
            //TODO check this !!
            setBias(rand.nextFloat()%1);
        }
        float getNeuronOutput(float[] connEntryValues){
            if(connEntryValues.length!=getConnectionCount()){
                System.out.println("Neuron Error: getNeuronOutput() : Wrong number of connEntryValues");
                System.exit(0);
            }

            neuronInputValue=0;

    /* First SUM all of the weighted connection values (connExit) attached to this neuron. This becomes the neuronInputValue. */
            for(int i=0; i<getConnectionCount(); i++){
                neuronInputValue+=connections.get(i).calcConnExit(connEntryValues[i]);
            }

            //Add the bias to the Neuron's inputValue
            neuronInputValue+=bias;

    /* Send the inputValue through the activation function to produce the Neuron's outputValue */
            neuronOutputValue=Activation(neuronInputValue);

            //Return the outputValue
            return neuronOutputValue;
        }
        float Activation(float x){
            float activatedValue = (float) (1 / (1 + exp(-1 * x)));
            return activatedValue;
        }

    }


