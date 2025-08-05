import java.io.File;
public class markovgenerator {
    public static void main(String[] args) {
        
        //Conditionals for checking validity of the passed arguments
        if(args.length != 2) {
            System.out.println("Usage: java markovgenerator.java path_to_file k_value");
            return;   
        }
        File file = new File(args[0]);
        if(!file.exists()) {
            System.out.println("File does not exist");
            return;
        }
        try {
            int k_value = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid k value ");
            return;
        }

        
    }
}
