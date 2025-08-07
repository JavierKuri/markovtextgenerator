import java.io.File;
public class markovgenerator {
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
        int word_count;
        try {
            word_count = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid word count value");
            return;
        }
        MarkovMatrix markovMatrix = new MarkovMatrix(file, k_value);
    }
}
