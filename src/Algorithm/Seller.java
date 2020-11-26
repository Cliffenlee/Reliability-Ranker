package Algorithm;
import java.util.*;

public class Seller {
    private String username;
    private List<Product> listProducts;
    private double rating;
    private String verified;
    private String joinedDate;
    private int numFollowers;
    private int numWordsInReviews;
    private int numNegativeWordsInReviews;
    private String numReviews;

    /**
     * Constructor for Seller
     * @param listProducts list of all product listings under a seller
     * @param rating average rating of seller based on all reviews
     * @param verified methods by which the seller is verified by
     * @param joinedDate duration the seller has been on Carousell
     * @param numFollowers total number of followers the seller has
     * @param numWordsInReviews total number of words in the seller's reviews
     * @param numNegativeWordsInReviews total number of negative keywords in the seller's reviews
     */
    public Seller(String username, List<Product> listProducts, double rating, String verified, String joinedDate,
                  int numFollowers, int numWordsInReviews, int numNegativeWordsInReviews, String numReviews) {
        this.username = username;
        this.listProducts = listProducts;
        this.rating = rating;
        this.verified = verified;
        this.joinedDate = joinedDate;
        this.numFollowers = numFollowers;
        this.numWordsInReviews = numWordsInReviews;
        this.numNegativeWordsInReviews = numNegativeWordsInReviews;
        this.numReviews = numReviews;
    }

    /**
     * Compute the individual rating for the verfied field
     * @return individual rating for verified
     */
    private double getVerifiedRating() {
        double rating = 0;
        if (verified.contains("facebook")) {
            rating += 1;
        }

        if (verified.contains("email")) {
            rating += 1;
        }

        if (verified.contains("mobile")) {
            rating += 3;
        }

        if (verified.contains("identity")) {
            rating = 5;
        }
        return rating;
    }

    /**
     * Compute the individual rating for the joinedDate field
     * @return individual rating for joinedDate
     */
    private double getJoinedDateRating() {
        double rating = 0;
        // to experiment with ratings
        if (joinedDate.contains("y")) {
            rating = 5;
        } else if (joinedDate.contains("m")) {
            int indexOfm = joinedDate.indexOf('m');
            char numMonths = (char)(joinedDate.charAt(indexOfm - 1));
            if (numMonths > 12) {
                rating = 4;
            } else if (numMonths > 6) {
                rating = 3;
            } else if (numMonths > 3) {
                rating = 2;
            } else if (numMonths >= 1) {
                rating = 1;
            }
        }
        return rating;
    }

    /**
     * Compute the individual rating for the numFollowers field
     * @return individual rating for numFollowers
     */
    private double getNumFollowersRating() {
        double rating = 0;
        if (numFollowers > 2000) {
            rating = 5;
        } else if (numFollowers > 1000) {
            rating = 4;
        } else if (numFollowers > 500) {
            rating = 3;
        } else if (numFollowers > 100) {
            rating = 2;
        } else if (numFollowers > 30) {
            rating = 1;
        }
        return rating;
    }

    /**
     * Compute the individual rating for the reviews field based on the proportion of non-negative keywords in the seller's reviews
     * @return individual rating for reviews field
     */
    private double getReviewsRating() {
        if (numWordsInReviews != 0) {
            double proportionOfNonNegativeKeywords = 1 - numNegativeWordsInReviews / numWordsInReviews;
            double rating = proportionOfNonNegativeKeywords * 6;
            return rating;
        }
        return 0.0;
    }

    /**
     * Compute the average rating for the seller's products based on each of the product's individual reliability rating
     * @return average rating for seller's products
     */
    public double getProductsRating() {
        double rating = 0;
        double sum = 0;
        for (int i = 0; i < listProducts.size(); i++) {
            double indivRating = listProducts.get(i).getProductReliabilityRating();
            sum += indivRating;
        }
        rating = sum / listProducts.size();
        return rating;
    }

    public double getFinalRating() {
        double rating = 0;
        double originalRating = rating;
        if (originalRating > 4.8) {
            rating = 5;
        } else if (originalRating > 4.6){
            rating = 4;
        } else if (originalRating > 4) {
            rating = 3;
        } else if (originalRating > 3) {
            rating = 2;
        } else {
            rating = 1;
        }

        return rating;
    }

    public double getNumReviewsRating() {
        double rating = 0;
        String numReviews = this.numReviews;
        Double finalNumReviews;
        if (numReviews.contains("k") || numReviews.contains("K")) {
            finalNumReviews = Double.parseDouble(numReviews.substring(0,numReviews.length()-1))*1000;
        } else {
            finalNumReviews = Double.parseDouble(numReviews);
        }
 
        if (finalNumReviews > 1000) {
            rating = 5;
        } else if (finalNumReviews > 500) {
            rating = 4;
        } else if (finalNumReviews > 200) {
            rating = 3;
        } else if (finalNumReviews > 50) {
            rating = 2;
        } else if (finalNumReviews > 5) {
            rating = 1;
        }
        return rating;
    }

    /**
     * Compute the overall rating for a seller based on the attribute values and attribute weights
     * @return seller's overall reliability rating
     */
    public double getOverallSellerRating() {
        // assign rankings to each attribute
        int numFollowersRank = 7;
        int overallReviewsRank = 6;
        int numReviewsRank = 5;
        int ratingRank = 4;
        int listProductsRank = 3;
        int verifiedRank = 2;
        int joinedDateRank = 1;

        double total = numFollowersRank + overallReviewsRank + ratingRank + listProductsRank + verifiedRank + joinedDateRank + numReviewsRank;

        double numFollowersWeight = numFollowersRank / total;
        double overallReviewsWeight = overallReviewsRank / total;
        double ratingWeight = ratingRank / total;
        double listProductsWeight = listProductsRank / total;
        double verifiedWeight = verifiedRank / total;
        double joinedDateWeight = joinedDateRank / total;
        double numReviewsWeight = numReviewsRank / total;

        double numFollowersRating = getNumFollowersRating();
        // System.out.println("numFollowers:" + getNumFollowersRating());

        // Takes into consideration the cases where there is high review rating but few reviews and low review rating but large number of reviews
        double overallReviewsRating = getReviewsRating();
        // System.out.println("Reviews:" + getReviewsRating());

        double ratingRating = getFinalRating();
        // System.out.println("Rating:" + rating);

        double listProductsRating = getProductsRating();
        // System.out.println("listProducts:" + getProductsRating());

        double verifiedRating = getVerifiedRating();
        // System.out.println("verified:" + getVerifiedRating());

        double joinedDateDating = getJoinedDateRating();
        // System.out.println("joinedDate:" + getJoinedDateRating());

        double numReviewsRating = getNumReviewsRating();

        double rating = numFollowersRating * numFollowersWeight + overallReviewsRating * overallReviewsWeight
                        + ratingRating * ratingWeight + listProductsRating * listProductsWeight + verifiedRating * verifiedWeight
                        + joinedDateDating * joinedDateWeight + numReviewsRating * numReviewsWeight;

        return rating;
    }

    /**
     * Getter method for username
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    public List<Product> getProductList(){
        return this.listProducts;
    }

    /**
     * Update seller's product listings
     * @param product product to add into seller's product listings
     */
    public void updateProductList(Product product){
        List<Product> list = new ArrayList<>();
        list = getProductList();
        list.add(product);
        this.listProducts = list;
    }

    /**
     * Getter method for seller's joined date
     * @return joinedDate
     */
    public String getJoinedDate(){
        return this.joinedDate;
    }

    /**
     * Getter method for seller's rating
     * @return rating
     */
    public double getRating(){
        return this.rating;
    }

    /**
     * Getter method for seller's number of followers
     * @return numFollowers
     */
    public int getNumFollowers(){
        return this.numFollowers;
    }

    /**
     * Getter method for number of words in collated list of reviews
     * @return numWordsInReviews
     */
    public int getNumWordsInReviews(){
        return this.numWordsInReviews;
    }

    /**
     * Getter method for number of negative words in collated list of reviews
     * @return numNegativeWordsInReviews
     */
    public int getNumNegativeWordsInReviews(){
        return this.numNegativeWordsInReviews;
    }
}
