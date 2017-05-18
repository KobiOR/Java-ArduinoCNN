import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by orrko_000 on 13/05/2017.
 */
public class NeuralNetwork {

        ArrayList<Layer> layers=new ArrayList<Layer>();
        float[] arrayOfInputs={};
        float[] arrayOfOutputs={};
        float learningRate;
        float networkError;
        float trainingError;
        int retrainChances=0;
        final Random rand = new Random();

        NeuralNetwork(){
    /* the default learning rate of a neural network is set to 0.1, which can changed by the setLearningRate(lR) function. */
            learningRate=0.1f;
        }



        /* Function to add a Layer to the Neural Network */
        void addLayer(int numConnections, int numNeurons){
            layers.add(new Layer(numConnections,numNeurons));
        }



        /* Function to return the number of layers in the neural network */
        int getLayerCount(){
            return layers.size();
        }



        /* Function to set the learningRate of the Neural Network */
        void setLearningRate(float tempLearningRate){
            learningRate=tempLearningRate;
        }



        /* Function to set the inputs of the neural network */
        void setInputs(float[] tempInputs){
            arrayOfInputs=new float[tempInputs.length];
            for (int i = 0; i <tempInputs.length ; i++) {
                this.arrayOfInputs[i]=tempInputs[i];
            }
        }



        /* Function to set the inputs of a specified layer */
        void setLayerInputs(float[] tempInputs, int layerIndex){
            if(layerIndex>getLayerCount()-1){
                System.out.println("NN Error: setLayerInputs: layerIndex=" + layerIndex + " exceeded limits= " + (getLayerCount()-1));
            } else {
                layers.get(layerIndex).setInputs(tempInputs);
            }
        }



        /* Function to set the outputs of the neural network */
        void setOutputs(float[] tempOutputs){
            arrayOfOutputs=tempOutputs;
        }



        /* Function to return the outputs of the Neural Network */
        float[] getOutputs(){
            return arrayOfOutputs;
        }



        /* Function to process the Neural Network's input values and convert them to an output pattern using ALL layers in the network */
        void processInputsToOutputs(float[] tempInputs){
            setInputs(tempInputs);

    /* Check to make sure that the number of NeuralNetwork inputs matches the Neuron Connection Count in the first layer. */
            if(getLayerCount()>0){
                if(arrayOfInputs.length!=layers.get(0).neurons.get(0).getConnectionCount())
                {
                    System.out.println("NN Error: processInputsToOutputs: The number of inputs do NOT match the NN");
                    System.exit(0);
                } else {
        /* The number of inputs are fine : continue */
                    for(int i=0; i<getLayerCount(); i++){

          /*Set the INPUTs for each layer: The first layer gets it's input data from the NN, whereas the 2nd and subsequent layers get their input data from the previous layer's actual output. */
                        if(i==0){
                            setLayerInputs(arrayOfInputs,i);
                        } else {
                            setLayerInputs(layers.get(i-1).actualOUTPUTs, i);
                        }

          /* Now that the layer has had it's input values set, it can now process this data, and convert them into an output using the layer's neurons. The outputs will be used as inputs in the next layer (if available). */
                        layers.get(i).processInputsToOutputs();
                    }
        /* Once all the data has filtered through to the end of network, we can grab the actualOUTPUTs of the LAST layer
           These values become or will be set to the NN output values (arrayOfOutputs), through the setOutputs function call. */
                    setOutputs(layers.get(getLayerCount()-1).actualOUTPUTs);
                }
            }else{
                System.out.println("Error: There are no layers in this Neural Network");
                System.exit(0);
            }
        }




        /* Function to train the entire network using an array. */
        void trainNetwork(float[] inputData, float[] expectedOutputData){
    /* Populate the ENTIRE network by processing the inputData. */
            processInputsToOutputs(inputData);

    /* train each layer - from back to front (back propagation) */
            for(int i=getLayerCount()-1; i>-1; i--){
                if(i==getLayerCount()-1){
                    layers.get(i).setDeltaError(expectedOutputData);
                    layers.get(i).trainLayer(learningRate);
                    networkError=layers.get(i).getLayerError();
                } else {
        /* Calculate the expected value for each neuron in this layer (eg. HIDDEN LAYER) */
                    for(int j=0; j<layers.get(i).getNeuronCount(); j++){
          /* Reset the delta error of this neuron to zero. */
                        layers.get(i).neurons.get(j).deltaError=0;
          /* The delta error of a hidden layer neuron is equal to the SUM of [the PRODUCT of the connection.weight and error of the neurons in the next layer(eg OUTPUT Layer)]. */
          /* Connection#1 of each neuron in the output layer connect with Neuron#1 in the hidden layer */
                        for(int k=0; k<layers.get(i+1).getNeuronCount(); k++){
                            layers.get(i).neurons.get(j).deltaError += (layers.get(i+1).neurons.get(k).connections.get(j).weight * layers.get(i+1).neurons.get(k).deltaError);
                        }
          /* Now that we have the sum of Errors x weights attached to this neuron. We must multiply it by the derivative of the activation function. */
                        layers.get(i).neurons.get(j).deltaError *= (layers.get(i).neurons.get(j).neuronOutputValue * (1-layers.get(i).neurons.get(j).neuronOutputValue));
                    }
        /* Now that you have all the necessary fields populated, you can now Train this hidden layer and then clear the Expected outputs, ready for the next round. */
                    layers.get(i).trainLayer(learningRate);
                    layers.get(i).clearExpectedOUTPUT();
                }
            }
        }





        /* Function to train the entire network, using an array of input and expected data within an ArrayList */
        void trainingCycle(ArrayList trainingInputData, ArrayList trainingExpectedData, Boolean trainRandomly){
            int dataIndex;

      /* re-initialise the training Error with every cycle */
            trainingError=0;

      /* Cycle through the training data either randomly or sequentially */
            for(int i=0; i<trainingInputData.size(); i++){
                if(trainRandomly){
                    dataIndex= (rand.nextInt(trainingInputData.size()));
                } else {
                    dataIndex=i;
                }

                trainNetwork((float[]) trainingInputData.get(dataIndex),(float[]) trainingExpectedData.get(dataIndex));

        /* Use the networkError variable which is calculated at the end of each individual training session to calculate the entire trainingError. */
                trainingError+=abs(networkError);
            }
        }





        /* Function to train the network until the Error is below a specific threshold */
        void autoTrainNetwork(ArrayList trainingInputData, ArrayList trainingExpectedData, float trainingErrorTarget, int cycleLimit){
            trainingError=9999;
            int trainingCounter=0;


    /* cycle through the training data until the trainingError gets below trainingErrorTarget (eg. 0.0005) or the training cycles have exceeded the cycleLimit

variable (eg. 10000). */
            while(trainingError>trainingErrorTarget && trainingCounter<cycleLimit){

      /* re-initialise the training Error with every cycle */
                trainingError=0;

      /* Cycle through the training data randomly */
                trainingCycle(trainingInputData, trainingExpectedData, true);

      /* increment the training counter to prevent endless loop */
                trainingCounter++;
            }

    /* Due to the random nature in which this neural network is trained. There may be occasions when the training error may drop below the threshold
       To check if this is the case, we will go through one more cycle (but sequentially this time), and check the trainingError for that cycle
       If the training error is still below the trainingErrorTarget, then we will end the training session.
       If the training error is above the trainingErrorTarget, we will continue to train. It will do this check a  Maximum of 9 times. */
            if(trainingCounter<cycleLimit){
                trainingCycle(trainingInputData, trainingExpectedData, false);
                trainingCounter++;

                if(trainingError>trainingErrorTarget){
                    if (retrainChances<10){
                        retrainChances++;
                        autoTrainNetwork(trainingInputData, trainingExpectedData,trainingErrorTarget, cycleLimit);
                    }
                }

            } else {
                System.out.println("CycleLimit has been reached. Has been retrained " + retrainChances + " times.  Error is = " + trainingError);
            }
        }
    }
