public class ContextStorage {
    private static final String FILE_PATH = "data/user_context.txt";

    public void saveContext(String userInput) {
        try {
            FileUtils.writeToFile(FILE_PATH, userInput + System.lineSeparator(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String retrieveContext() {
        try {
            return FileUtils.readFromFile(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}