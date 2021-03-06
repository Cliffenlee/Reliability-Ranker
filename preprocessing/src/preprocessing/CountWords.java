package preprocessing;
import java.util.*;
import java.io.*;

class CountWords {

    private static HashSet<String> negativeWords = new HashSet<>();

    // Takies in a csv file generated by OctoParse and extracts the number of words, negative words, and double negatives in the review for each user
    public static void main(String[] args) {
        String path = "./data/original/shopee.csv";
        String filepath = "./data/result/shopeeReview.csv";
        String line = "";

        negativeWords = retrieveNegativeRoot();
        ReviewAnalysis reviewAnalysis = new ReviewAnalysis();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));

            reader.readLine();
            saveRecord("title", "numOfWords", "numOfNegativeWords", "numOfDoubleNegatives", filepath);
            while((line = reader.readLine()) != null){
                // split according to column
                String[] values = line.split(",");
                String username = values[0];
                String reviews = values[13];
                reviews += values[12];
                int doubleNegative = reviewAnalysis.countDoubleNegatives(reviews);
                int num = countWordsInReview(reviews);
                int negativeNum = countNegativeWords(reviews);
                saveRecord(String.valueOf(username),String.valueOf(num),String.valueOf(negativeNum),String.valueOf(doubleNegative),filepath);
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Counts total number of words in review
    private static int countWordsInReview(String review) {
        String[] words = review.split(" ");
        return words.length == 1 ? 0 : words.length;
    }

    // Counts number of negative words in reviews
    private static int countNegativeWords(String review) {
        Iterator<String> iter = negativeWords.iterator();
        int count = 0;
        while (iter.hasNext()) {
            if (review.contains(iter.next())) {
                count ++;
            }
        }

        return count;
    }

    //retrieve all negative root words and store them in a hashset
    private static HashSet<String> retrieveNegativeRoot () {

        HashSet<String> result = new HashSet<String>();
        String path = "./data/words/negativeRootWordsFinal.txt";
        String line = "";

        try{
            BufferedReader br = new BufferedReader(new FileReader(path));

            while((line = br.readLine()) != null){
                result.add(line);
                }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return result;
    }

    // Pipes input to a csv file
    public static void saveRecord(String username, String numWords, String numNegative, String doubleNegative, String filepath){
        try{
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw= new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(username +","+numWords+","+numNegative+","+doubleNegative);
            pw.flush();
            pw.close();
        }catch(Exception e){
        }
    }
}
