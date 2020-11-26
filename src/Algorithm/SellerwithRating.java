package Algorithm;

public class SellerwithRating {
    private Seller seller;
    private double rating;

    /**
     * Constructor for SellerwithRating
     * @param seller Seller object
     */
    public SellerwithRating(Seller seller) {
        this.seller = seller;
        this.rating = seller.getOverallSellerRating();
    }

    /**
     * Getter method for Seller 
     * @return seller
     */
    public Seller getSeller() {
        return seller;
    }

    /**
     * Getter method for rating (Seller's overall reliability rating)
     * @return rating
     */
    public double getRating() {
        return rating;
    }

}