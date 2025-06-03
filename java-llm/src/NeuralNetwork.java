public class NeuralNetwork {
    private double[][] weights;
    private double learningRate;

    public NeuralNetwork(int inputSize, int outputSize, double learningRate) {
        this.weights = new double[inputSize][outputSize];
        this.learningRate = learningRate;
        initializeWeights();
    }

    private void initializeWeights() {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                weights[i][j] = Math.random() * 0.01; // Small random values
            }
        }
    }

    public double[] predict(double[] input) {
        double[] output = new double[weights[0].length];
        for (int j = 0; j < weights[0].length; j++) {
            for (int i = 0; i < input.length; i++) {
                output[j] += input[i] * weights[i][j];
            }
        }
        return output;
    }

    public void train(double[] input, double[] target) {
        double[] output = predict(input);
        double[] error = new double[target.length];

        for (int i = 0; i < target.length; i++) {
            error[i] = target[i] - output[i];
        }

        for (int j = 0; j < weights[0].length; j++) {
            for (int i = 0; i < input.length; i++) {
                weights[i][j] += learningRate * error[j] * input[i];
            }
        }
    }

    public double[][] getWeights() {
        return weights;
    }

    public void setWeights(double[][] weights) {
        this.weights = weights;
    }
}