import java.io.*;

/**
 * Use for testing ReviewAnalysis and preprocessing for the Double Negative Experiments
 * This is not part of the Algorithm program.
 * The output created from ReviewAnalysis will be place onto one of the csv column for
 * analysis.
 */
public class TestMain {
    
    public static void main(String[] args){
        String path = "C:\\Users\\Jun An\\Documents\\GitHub\\DSA\\Stemmer\\editedoutput.csv";
        String filepath = "C:\\Users\\Jun An\\Documents\\GitHub\\DSA\\review_analyse\\output3.csv";

        ReviewAnalysis reviewAnalysis = new ReviewAnalysis();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            
            br.readLine();
           
            //write into csv on the number of double negatives and statements from ReviewAnalysis
            String line3 = "";
            while((line3 = br.readLine()) != null){
                //2. split according to column
                String[] array = line3.split(",");
                String reviews = array[13];
                
                int count = reviewAnalysis.countDoubleNegatives(reviews);
                String username = array[0];
                saveRecord(username, count, filepath);
            }
              
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }


    }
    /**
     * Method for writing the output on csv file.
     */
    public static void saveRecord(String username, double reliabilityRating, String filepath){
        try{
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw= new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(username +","+reliabilityRating);
            pw.flush();
            pw.close();
        }catch(Exception e){
        }
    }
}
