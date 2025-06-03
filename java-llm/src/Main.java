import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(10, 10, 0.01);
        ContextStorage contextStorage = new ContextStorage();
        LLM llm = new LLM(neuralNetwork);
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

            // Ask user for expected output and train
            System.out.print("Expected LLM output (or leave blank to skip training): ");
            String expectedOutput = scanner.nextLine();
            if (!expectedOutput.trim().isEmpty()) {
                llm.trainOnPair(userInput + " " + previousContext, expectedOutput);
                System.out.println("Model trained on your correction.");
            }
        }
        scanner.close();
    }
}