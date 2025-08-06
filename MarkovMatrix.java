import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MarkovMatrix {
    public File input_file;
    public int k_value;
    public Map<String, Map<String, Float>> matrix = new HashMap<>();

    public MarkovMatrix(File input_file, int k_value) {
        this.input_file = input_file;
        this.k_value = k_value;
        this.matrix = generateProbMatrix(input_file, k_value);
    }

    public Map<String, Map<String, Float>> generateProbMatrix(File input_file, int k_value) {
        Map<String, Map<String, Float>> probmatrix = new HashMap<>();
        return probmatrix;
    }
}