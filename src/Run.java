import java.util.ArrayList;


public class Run {
    public static void main(String[] args) {
        float[] ClassificationA = {1};
        float[] ClassificationB = {0};

        ArrayList myTrainingInputs = new ArrayList();
        ArrayList myTrainingOutputs = new ArrayList();

        float[] imageA = {0, 1,0};
        float[] imageB = {1, 0,0};
        float[] imageC = {0, 1,0};
        float[] imageD = {1, 1,1};

        myTrainingInputs.add(imageA);
        myTrainingInputs.add(imageB);
        myTrainingInputs.add(imageC);
        myTrainingInputs.add(imageD);
        myTrainingOutputs.add(ClassificationB);
        myTrainingOutputs.add(ClassificationB);
        myTrainingOutputs.add(ClassificationB);
        myTrainingOutputs.add(ClassificationA);


        NeuralNetwork NN = new NeuralNetwork();
        NN.addLayer(3, 3);
        NN.addLayer(3, 3);
        NN.addLayer(3, 3);
        NN.addLayer(3, 3);
        NN.addLayer(3, 2);

        System.out.println("Begin Training");
        NN.autoTrainNetwork(myTrainingInputs, myTrainingOutputs, 0.0001f, 500000);





    }


}
