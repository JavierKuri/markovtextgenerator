import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;


public class MarkovMatrix {
    public File input_file;
    public int k_value;
    public Map<String, Map<String, Float>> matrix = new HashMap<>();

    //Constructor
    public MarkovMatrix(File input_file, int k_value) {
        this.input_file = input_file;
        this.k_value = k_value;
        this.matrix = generateProbMatrix(input_file, k_value);
    }

    public Map<String, Map<String, Float>> generateProbMatrix(File input_file, int k_value) {

        //Creating the k_grams
        Map<String, Map<String, Float>> probmatrix = new HashMap<>();
        String text = "";
        try {
            text = Files.readString(input_file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] tokens = text.split(" ");
        String[] k_grams = new String[tokens.length-k_value+1];
        for(int i=0;i<tokens.length-k_value+1;i++) {
            k_grams[i] = create_k_gram(tokens, i);
        }

        //Filling the matrix with frecuencies of state transitions between kgrams
        for(int i=0;i<k_grams.length-1;i++) {
            String current_kgram = k_grams[i];
            String next_kgram = k_grams[i+1];
            Map<String, Float> innerMap = probmatrix.getOrDefault(current_kgram, new HashMap<>());
            float currentCount = innerMap.getOrDefault(next_kgram, 0.0f);
            innerMap.put(next_kgram, currentCount + 1.0f);
            probmatrix.put(current_kgram, innerMap);
        }

        //Turn frecuencies to probabilities
        for (Map.Entry<String, Map<String, Float>> entry : probmatrix.entrySet()) {
            Map<String, Float> innerMap = entry.getValue();
            float totalCount = 0.0f;
            for (float count : innerMap.values()) {
                totalCount += count;
            }
            for (Map.Entry<String, Float> innerEntry : innerMap.entrySet()) {
                String next_kgram = innerEntry.getKey();
                float count = innerEntry.getValue();
                float probability = count / totalCount;
                innerMap.put(next_kgram, probability);
            }
        }
        return probmatrix;
    }

    //Helper function for creating the kgrams to be added to the array
    private String create_k_gram(String[] tokens, int tokens_index) {
        String value = "";
        for(int i=0;i<k_value;i++) {
            value += tokens[i + tokens_index] + " ";
        }
        return value.trim();
    }
}