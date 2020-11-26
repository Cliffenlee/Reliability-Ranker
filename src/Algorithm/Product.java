package Algorithm;

import java.util.*;

public class Product {
    public String CarousellProtection;
    public String ProductDescription;
    
    /**
     * Constructor for Product
     * @param CarousellProtection whether the product has Carousell Protection or not
     * @param ProductDescription product description of a particular product listing
     */
    public Product(String CarousellProtection, String ProductDescription) {
        this.CarousellProtection = CarousellProtection;
        this.ProductDescription = ProductDescription;
    }

    /**
     * Compute the individual rating for the Product Description field, based on whether there are keywords present in the product description that indicate preference for negotiation outside of Carousell
     * @return individual rating for Product Description field
     */
    public double getProductDescriptionRating() {
        Map<String, Double> target = new TreeMap<>(String.CASE_INSENSITIVE_ORDER); 
        target.put("Whatsapp", -1.0);
        target.put("call", -1.0);
        target.put("contact", -1.0);
        target.put("contactable", -1.0);
        target.put("sms", -1.0);
        double rating = 5;

        String[] split = ProductDescription.split(" ");
        if(split != null){
            for (int i = 0; i < split.length; i++) {
                if (rating >= 0 && rating <= 5) {
                    if (target.get(split[i]) != null) {
                        rating += target.get(split[i]);
                    }
                }
            }
        }
        
        return rating;
    }

    /**
     * Compute individual rating for Carousell Protection field, based on whether the product is under Carousell Protection or not
     * @return individual rating for Carousell Protection field
     */
    public double getCarousellProtectionRating() {
        double rating = 0;
        if (CarousellProtection.equals("yes")) {
            rating = 5;
        } else {
            rating = 2.5; // to experiment
        }
        return rating;
    }

    /**
     * Compute the overall reliability rating for a product listing
     * @return reliability rating of a product
     */ 
    public double getProductReliabilityRating() {
        // value of rank is higher for those attributes with higher weight
        double CarousellProtectionRank = 2;
        double ProductDescriptionRank = 1;
        double total = CarousellProtectionRank  + ProductDescriptionRank;
        double CarousellProtectionRating = this.getCarousellProtectionRating();
        double ProductDescriptionRating = this.getProductDescriptionRating();
        double CarousellProtectionWeight = CarousellProtectionRank / total;
        double ProductDescriptionWeight = ProductDescriptionRank / total; 
        return CarousellProtectionRating * CarousellProtectionWeight + ProductDescriptionRating * ProductDescriptionWeight;
    }
}
