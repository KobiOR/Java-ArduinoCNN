import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by orrko_000 on 13/05/2017.
 */
public class Layer {


        ArrayList<Neuron> neurons=new ArrayList<Neuron>();
        float[] layerINPUTs;int layerINPUTsSize=0;
        float[] actualOUTPUTs;int actualOUTPUTsSize=0;
        float[] expectedOUTPUTs;int expectedOUTPUTsSize=0;
        float layerError;
        float learningRate;


        /* This is the default constructor for the Layer */
        Layer(int numberConnections, int numberNeurons){
    /* Add all the neurons and actualOUTPUTs to the layer */
            for(int i=0; i<numberNeurons; i++){
                Neuron tempNeuron = new Neuron(numberConnections);
                addNeuron(tempNeuron);
                addActualOUTPUT();
            }
        }


        /* Function to add an input or output Neuron to this Layer */
        void addNeuron(Neuron xNeuron){
            neurons.add(xNeuron);
        }


        /* Function to get the number of neurons in this layer */
        int getNeuronCount(){
            return neurons.size();
        }


        /* Function to increment the size of the actualOUTPUTs array by one. */
        void addActualOUTPUT(){
            float [] temp=new float[this.actualOUTPUTsSize+1];
            for (int i = 0; i <this.actualOUTPUTsSize ; i++)
                temp[i]=this.actualOUTPUTs[i];

            temp[this.actualOUTPUTsSize]=0.0f;
            this.actualOUTPUTsSize++;
            this.actualOUTPUTs=temp;

        }


        /* Function to set the ENTIRE expectedOUTPUTs array in one go. */
        void setExpectedOUTPUTs(float[] tempExpectedOUTPUTs){
            expectedOUTPUTs=tempExpectedOUTPUTs;
        }


        /* Function to clear ALL values from the expectedOUTPUTs array */
        void clearExpectedOUTPUT(){
            expectedOUTPUTs=new float[]{};
        }


        /* Function to set the learning rate of the layer */
        void setLearningRate(float tempLearningRate){
            learningRate=tempLearningRate;
        }


        /* Function to set the inputs of this layer */
        void setInputs(float[] tempInputs){
            this.layerINPUTs=new float[tempInputs.length];
            for (int i = 0; i <tempInputs.length ; i++) {
                this.layerINPUTs[i]=tempInputs[i];
            }
        }



        /* Function to convert ALL the Neuron input values into Neuron output values in this layer, through a special activation function. */
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


        /* Function to get the error of this layer */
        float getLayerError(){
            return layerError;
        }


        /* Function to set the error of this layer */
        void setLayerError(float tempLayerError){
            layerError=tempLayerError;
        }


        /* Function to increase the layerError by a certain amount */
        void increaseLayerErrorBy(float tempLayerError){
            layerError+=tempLayerError;
        }


        /* Function to calculate and set the deltaError of each neuron in the layer */
        void setDeltaError(float[] expectedOutputData){
            setExpectedOUTPUTs(expectedOutputData);
            int neuronCount = getNeuronCount();
    /* Reset the layer error to 0 before cycling through each neuron */
            setLayerError(0);
            for(int i=0; i<neuronCount;i++){
                neurons.get(i).deltaError = actualOUTPUTs[i]*(1-actualOUTPUTs[i])*(expectedOUTPUTs[0]-actualOUTPUTs[i]);

       /* Increase the layer Error by the absolute difference between the calculated value (actualOUTPUT) and the expected value (expectedOUTPUT). */
                increaseLayerErrorBy(abs(expectedOUTPUTs[0]-actualOUTPUTs[i]));
            }
        }


        /* Function to train the layer : which uses a training set to adjust the connection weights and biases of the neurons in this layer */
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

