import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(10, 10, 0.5);
        neuralNetwork.loadWeights("../data/weights.bin"); // Load weights if available

        ContextStorage contextStorage = new ContextStorage();
        LLM llm = new LLM(neuralNetwork);

        // Train from user_context.txt on start
        llm.trainFromContextFile("../data/user_context.txt");

        Scanner scanner = new Scanner(System.in);

        
        System.out.println("Type 'exit' to quit.");
        while (true) {
            String previousContext = contextStorage.loadContext();

            System.out.print("You: ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")) break;

            contextStorage.saveContext(userInput);

            String response = llm.generateResponse(userInput, previousContext);
            System.out.println("LLM: " + response);

            System.out.print("Expected LLM output (or leave blank to skip training): ");
            String expectedOutput = scanner.nextLine();
            if (!expectedOutput.trim().isEmpty()) {
                llm.trainOnPair(userInput + " " + previousContext, expectedOutput);
                System.out.println("Model trained on your correction.");
            }
        }
        neuralNetwork.saveWeights("../data/weights.bin"); // Save weights on exit
        scanner.close();
    }
}