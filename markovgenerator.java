import java.io.File;
import java.util.*;
public class markovgenerator {

    //Method for the actual generation of the random text
    public static void randomWalk(Map<String, Map<String, Float>> matrix, int token_Count) {
        Random random = new Random();
        List<String> keys = new ArrayList<>(matrix.keySet());
        String currentKey = keys.get(random.nextInt(keys.size()));
        for (int i = 0; i < token_Count; i++) {
            if (i == 0) {
                System.out.print(currentKey + " ");
            } else {
                String[] words = currentKey.split(" ");
                System.out.print(words[words.length - 1] + " ");
            }
            Map<String, Float> innerMap = matrix.getOrDefault(currentKey, Collections.emptyMap());
            double rand = random.nextDouble();
            double cumulative = 0.0;
            String nextKey = null;
            for (Map.Entry<String, Float> entry : innerMap.entrySet()) {
                cumulative += entry.getValue();
                if (rand <= cumulative) {
                    nextKey = entry.getKey();
                    break;
                }
            }
            if (nextKey == null) {
                currentKey = keys.get(random.nextInt(keys.size()));
            } else {
                currentKey = nextKey;
            }
        }
    }

    public static void main(String[] args) {
        
        //Conditionals for checking validity of the passed arguments
        if(args.length != 3) {
            System.out.println("Usage: java markovgenerator.java path_to_file k_value word_count");
            return;   
        }
        File file = new File(args[0]);
        if(!file.exists()) {
            System.out.println("File does not exist");
            return;
        }
        int k_value;
        try {
            k_value = Integer.parseInt(args[1]);
            if(k_value < 1) {
                System.out.println("k value must be greater than 0");
            return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid k value");
            return;
        }
        int token_count;
        try {
            token_count = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid word count value");
            return;
        }
        MarkovMatrix markovMatrix = new MarkovMatrix(file, k_value);

        //Generating the random text with the probability matrix
        randomWalk(markovMatrix.matrix, token_count);
    }
}
