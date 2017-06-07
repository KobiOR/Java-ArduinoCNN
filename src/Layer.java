import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by orrko_000 on 13/05/2017.
 */
public class Layer  implements Serializable {
    private static final long serialVersionUID = 3L;

    ArrayList<Neuron> neurons=new ArrayList<Neuron>();
        float[] layerINPUTs;
        float[] actualOUTPUTs;int actualOUTPUTsSize=0;
        float expectedOUTPUTs;
        float layerError;
        float learningRate;
        int numberConnections;

        Layer(int numberConnections, int numberNeurons){
          this.numberConnections=numberConnections;
    /* Add all the neurons and actualOUTPUTs to the layer */
            for(int i=0; i<numberNeurons; i++){
                Neuron tempNeuron = new Neuron(numberConnections);
                addNeuron(tempNeuron);
                addActualOUTPUT();
            }
        }
        void addNeuron(Neuron xNeuron){
            neurons.add(xNeuron);
        }
        int getNeuronCount(){
            return neurons.size();
        }
        void addActualOUTPUT(){
            float [] temp=new float[this.actualOUTPUTsSize+1];
            for (int i = 0; i <this.actualOUTPUTsSize ; i++)
                temp[i]=this.actualOUTPUTs[i];

            temp[this.actualOUTPUTsSize]=0.0f;
            this.actualOUTPUTsSize++;
            this.actualOUTPUTs=temp;

        }
        void setExpectedOUTPUTs(float tempExpectedOUTPUTs){
            expectedOUTPUTs=tempExpectedOUTPUTs;
        }
        void clearExpectedOUTPUT(){
            expectedOUTPUTs=0;
        }
        void setLearningRate(float tempLearningRate){
            learningRate=tempLearningRate;
        }
        void setInputs(float[] tempInputs){
            this.layerINPUTs=new float[tempInputs.length];
            for (int i = 0; i <tempInputs.length ; i++) {
                this.layerINPUTs[i]=tempInputs[i];
            }
        }
        void processInputsToOutputs(){
    /* neuronCount is used a couple of times in this function. */
            int neuronCount = getNeuronCount();

    /* Check to make sure that there are neurons in this layer to process the inputs */
            if(neuronCount>0) {
      /* Check to make sure that the number of inputs matches the number of Neuron Connections. */
                if(layerINPUTs.length!=neurons.get(0).getConnectionCount()){
                    System.out.println("Error in Layer: processInputsToOutputs: The number of inputs do NOT match the number of Neuron connections in this layer");
                    System.exit(0);
                } else {
        /* The number of inputs are fine : continue
           Calculate the actualOUTPUT of each neuron in this layer,
           based on their layerINPUTs (which were previously calculated).
           Add the value to the layer's actualOUTPUTs array. */
                    for(int i=0; i<neuronCount;i++){
                        actualOUTPUTs[i]=neurons.get(i).getNeuronOutput(layerINPUTs);
                    }
                }
            }else{
                System.out.println("Error in Layer: processInputsToOutputs: There are no Neurons in this layer");
                System.exit(0);
            }
        }
        float getLayerError(){
            return layerError;
        }
        void setLayerError(float tempLayerError){
            layerError=tempLayerError;
        }
        void increaseLayerErrorBy(float tempLayerError){
            layerError+=tempLayerError;
        }
        void setDeltaError(float expectedOutputData){
            setExpectedOUTPUTs(expectedOutputData);
            int neuronCount = getNeuronCount();
    /* Reset the layer error to 0 before cycling through each neuron */
            setLayerError(0);
            for(int i=0; i<neuronCount;i++){
                neurons.get(i).deltaError = actualOUTPUTs[i]*(1-actualOUTPUTs[i])*(expectedOUTPUTs-actualOUTPUTs[i]);

       /* Increase the layer Error by the absolute difference between the calculated value (actualOUTPUT) and the expected value (expectedOUTPUT). */
                increaseLayerErrorBy(abs(expectedOUTPUTs-actualOUTPUTs[i]));
            }
        }
        void trainLayer(float tempLearningRate){
            setLearningRate(tempLearningRate);

            int neuronCount = getNeuronCount();

            for(int i=0; i<neuronCount;i++){
      /* update the bias for neuron[i] */
                neurons.get(i).bias += (learningRate * 1 * neurons.get(i).deltaError);

      /* update the weight of each connection for this neuron[i] */
                for(int j=0; j<neurons.get(i).getConnectionCount(); j++){
                    neurons.get(i).connections.get(j).weight += (learningRate * neurons.get(i).connections.get(j).connEntry * neurons.get(i).deltaError);
                }
            }
        }
    }

