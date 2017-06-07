import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by orrko_000 on 13/05/2017.
 */
public class NeuralNetwork  implements Serializable {
    private static final long serialVersionUID = 1L;


    ArrayList<Layer> layers=new ArrayList<Layer>();
        float[] arrayOfInputs={};
        float[] arrayOfOutputs={};
        float learningRate;
        float networkError;
        float trainingError;
        int retrainChances=0;
        final Random rand = new Random();

        NeuralNetwork(){
            learningRate= Utils.LEARNING_RATE;
        }
        void addLayer(int numConnections, int numNeurons){
            layers.add(new Layer(numConnections,numNeurons));
        }
        int getLayerCount(){
            return layers.size();
        }
        void setLearningRate(float tempLearningRate){
            learningRate=tempLearningRate;
        }
        void setInputs(float[] tempInputs){
            arrayOfInputs=new float[tempInputs.length];
            for (int i = 0; i <tempInputs.length ; i++) {
                this.arrayOfInputs[i]=tempInputs[i];
            }
        }
        void setLayerInputs(float[] tempInputs, int layerIndex){
            if(layerIndex>getLayerCount()-1){
                System.out.println("NN Error: setLayerInputs: layerIndex=" + layerIndex + " exceeded limits= " + (getLayerCount()-1));
            } else {
                layers.get(layerIndex).setInputs(tempInputs);
            }
        }
        void setOutputs(float[] tempOutputs){
            arrayOfOutputs=tempOutputs;
        }
        float[] getOutputs(){
            return arrayOfOutputs;
        }
        void processInputsToOutputs(float[] tempInputs){
            setInputs(tempInputs);

            if(getLayerCount()>0){
                if(arrayOfInputs.length!=layers.get(0).neurons.get(0).getConnectionCount())
                {
                    System.out.println("NN Error: processInputsToOutputs: The number of inputs do NOT match the NN");
                    System.exit(0);
                } else {
                    for(int i=0; i<getLayerCount(); i++){

                        if(i==0){
                            setLayerInputs(arrayOfInputs,i);
                        } else {
                            setLayerInputs(layers.get(i-1).actualOUTPUTs, i);
                        }

                        layers.get(i).processInputsToOutputs();
                    }

                    setOutputs(layers.get(getLayerCount()-1).actualOUTPUTs);
                }
            }else{
                System.out.println("Error: There are no layers in this Neural Network");
                System.exit(0);
            }
        }
        void trainNetwork(float[] inputData, float expectedOutputData){
            processInputsToOutputs(inputData);
            for(int i=getLayerCount()-1; i>-1; i--){
                if(i==getLayerCount()-1){
                    layers.get(i).setDeltaError(expectedOutputData);
                    layers.get(i).trainLayer(learningRate);
                    networkError=layers.get(i).getLayerError();
                } else {
                    for(int j=0; j<layers.get(i).getNeuronCount(); j++){
                        layers.get(i).neurons.get(j).deltaError=0;
                        for(int k=0; k<layers.get(i+1).getNeuronCount(); k++){
                            layers.get(i).neurons.get(j).deltaError += (layers.get(i+1).neurons.get(k).connections.get(j).weight * layers.get(i+1).neurons.get(k).deltaError);
                        }
                        layers.get(i).neurons.get(j).deltaError *= (layers.get(i).neurons.get(j).neuronOutputValue * (1-layers.get(i).neurons.get(j).neuronOutputValue));
                    }
                    layers.get(i).trainLayer(learningRate);
                    layers.get(i).clearExpectedOUTPUT();
                }
            }
        }
        void trainingCycle(ArrayList trainingInputData, ArrayList trainingExpectedData, Boolean trainRandomly){
            int dataIndex;

            trainingError=0;

            for(int i=0; i<trainingInputData.size(); i++){
                if(trainRandomly){
                    dataIndex= (rand.nextInt(trainingInputData.size()));
                } else {
                    dataIndex=i;
                }

                trainNetwork((float[]) trainingInputData.get(dataIndex), (Float) trainingExpectedData.get(dataIndex));

                trainingError+=abs(networkError);

            }
        }
        void autoTrainNetwork(ArrayList trainingInputData, ArrayList trainingExpectedData, float trainingErrorTarget, int cycleLimit){
            trainingError=9999;
            int trainingCounter=1;
            while(trainingError>trainingErrorTarget && trainingCounter<cycleLimit){

                System.out.println("Cycle number : "+trainingCounter);
                System.out.println("Train error : "+trainingError);
                if (Utils.BEST_TRAIN_ERROR>trainingError) {
                    Utils.BEST_TRAIN_ERROR = trainingError;
                    Utils.BEST_TRAIN_ERROR_CYCLE=trainingCounter;
                }

                trainingError=0;
                trainingCycle(trainingInputData, trainingExpectedData, true);
                trainingCounter++;

            }

            if(trainingCounter<cycleLimit){
                System.out.println("Cycle number : "+trainingCounter );
                System.out.println("Train error : "+trainingError);
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
