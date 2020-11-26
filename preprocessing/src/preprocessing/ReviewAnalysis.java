package preprocessing;
import java.util.*;
import java.util.regex.Pattern;


/**
 * This class is used in the Experiment: Detection of Double Negatives in review textbody and its Overall Scoring.
 * The results from the experiment shows no changes in overall scoring.
 * Since the weightage of negative words was not changed significantly to make an effect.
 * This class is used as in preprocessing step, results were used to deduct the negative word count in the csv,
 * the adjusted count is then placed on a new column in the csv file (column name: correctedNegWordCount).
 */
public class ReviewAnalysis {
    
    /**
     * Use for storing all the double negatives
    */
    private List<String> sentences = new ArrayList<>();
    
    /** 
     * Constructor for review analysis.
     * Double negatives and other key words are added into the list
     * after instantiate the object.
    */
    
    public ReviewAnalysis(){
        sentences.add("not bad");
        sentences.add("not a bad seller");
        sentences.add("not a scammer");
        sentences.add("not scammer");
        sentences.add("i do not know this person");
        sentences.add("no bad");
        sentences.add("not unpleasant");
        sentences.add("no unpleasant");
        sentences.add("no scratches");
        sentences.add("hassle free");
        sentences.add("fuss free");
        sentences.add("no damage");
        sentences.add("not damage");
        sentences.add("not slow");
        sentences.add("not expensive");
        sentences.add("not spoiled");
        sentences.add("no spoilages");
        sentences.add("no spoilage");
        sentences.add("no spoil");
        sentences.add("no cracks");
        sentences.add("no crack");
        sentences.add("fast fuss");
        sentences.add("no defects");
        sentences.add("no defect");
        sentences.add("a steal");
        sentences.add("steal");
        sentences.add("very cheap");
        sentences.add("too cheap");
    }

   

    /**
     * Add more sentences , double negatives or words
     * into the list.
     * @param str
     */
    public void addMoreSentence(String str){
        sentences.add(str);
    }

    /**
     * Check if there are double negative, or if it matches other statements
     * that are counted into negative word count in review when negative words are
     * used in a positive seller's review body.
     * @param review
     * @return whether the statement or double negatives is in the review body
     */
    public boolean doubleNegative(String review){
        String noSpaceReview = review.replaceAll("\\s+", "").toLowerCase();

        for(String str : sentences){
            str = str.replaceAll("\\s+", "");
            if(noSpaceReview.contains(str)){
                return true;
            }
        }
        return false;
    }

    /**
     * Overrided method of doubleNegatives with username and review as input.
     * Check if its double negative, or if it matches other statements
     * that are counted into negative word count when negative words are
     * used in a positive seller's review body.
     * @param username
     * @param review
     * @return the username of seller, and whether the statement or double negatives is in the review body.
     */
    public String doubleNegative(String username, String review){
        String noSpaceReview = review.replaceAll("\\s+", "").toLowerCase();

        for(String str : sentences){
            str = str.replaceAll("\\s+", "");
            if(noSpaceReview.contains(str)){
                return username + ": " + "true";
            }
        }
        return username + ": " + "false";
    }

    
    /**
     * Count number of statements in the seller's review textbody
     *  that matches the statements and double negatives 
     * in the ReviewAnalysis list.
     * @param review
     * @return the number of double negatives and matched statements in the review body
     */
    public int countDoubleNegatives(String review){
        //remove all spaces
        String noSpaceReview = review.replaceAll("\\s+", "").toLowerCase();
        int totalCount = 0;

        for(String sentence : sentences){
            //remove all spaces for easy comparison
            String noSpace = sentence.replaceAll("\\s+", "");

            int count = (noSpaceReview.split(Pattern.quote(noSpace), -1).length) - 1;
            totalCount += count;
            
        }

        return totalCount;
    }
}
