import java.io.*;

public class ContextStorage {
    private static final String FILE_PATH = "../data/user_context.txt";

    public void saveContext(String input) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            fw.write(input + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadContext() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(" ");
            }
        } catch (IOException e) {
            // Ignore if file doesn't exist yet
        }
        return sb.toString().trim();
    }
}