public class LLM {
    private NeuralNetwork neuralNetwork;

    public LLM(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    // Vectorize input string to double array
    private double[] vectorize(String input) {
        double[] vec = new double[10];
        for (int i = 0; i < input.length() && i < vec.length; i++) {
            vec[i] = (double) input.charAt(i) / 255.0;
        }
        return vec;
    }

    // Convert output vector to a readable string
    private String devectorize(double[] output) {
        StringBuilder sb = new StringBuilder();
        for (double v : output) {
            int c = (int) Math.round(v * 255.0);
            if (c > 31 && c < 127) { // printable ASCII
                sb.append((char) c);
            }
        }
        return sb.toString();
    }

    // Extract the first word from a string
    private String firstWord(String s) {
        String[] parts = s.trim().split("\\s+");
        return parts.length > 0 ? parts[0] : "";
    }

    // Train the network on (sentence so far) -> (next word)
    public void trainOnPair(String input, String expectedOutput) {
        double[] inputVec = vectorize(input);
        double[] targetVec = vectorize(firstWord(expectedOutput));
        // Train for more steps to make learning more extreme
        for (int i = 0; i < 5000; i++) {
            neuralNetwork.train(inputVec, targetVec);
        }
    }

    // Predict the next word given the sentence so far
    public String generateResponse(String userInput, String previousContext) {
        String combined = userInput.trim();
        double[] inputVec = vectorize(combined);
        double[] outputVec = neuralNetwork.predict(inputVec);
        String nextWord = devectorize(outputVec).trim();
        if (nextWord.isEmpty()) {
            nextWord = "(no prediction)";
        }
        return nextWord;
    }

    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(10, 10, 0.5); // was 0.01
        LLM llm = new LLM(neuralNetwork);

        // Example usage
        llm.trainOnPair("Hello", "world");
        String response = llm.generateResponse("Hello", "");
        System.out.println(response);
    }
}