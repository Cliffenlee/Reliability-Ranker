package Algorithm;
import java.io.*;
import java.util.*;


public class EntityConstructor {
    private HashMap<String, Seller> map;

    /**
     * Constructor for EntityConstructor
     */
    public EntityConstructor() {
        this.map = new HashMap<>();
    }

    /**
     * Read data from csv file and create HashMap consisting of usernames and corresponding seller objects
     * @return HashMap with usernames and corresponding sellers
     */
    public HashMap<String, Seller> createEntityList(){
        //get info from csv
        String path = "CSV/original/shopee.csv";
        String line = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));

            reader.readLine();
            while((line = reader.readLine()) != null){
                // split according to column
                String[] values = line.split(",");
                addToMap(values);
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return this.map;
    }

    /**
     * Set parameters for seller objects and product objects and add usernames with corresponding seller objects to map
     * @param array attribute values for seller and product objects
     */
    public void addToMap(String[] array){
        String username = array[0];
        if(!this.map.containsKey(username)){

            double rating = Double.parseDouble(array[2]);

            int numFollowers = Integer.parseInt(array[5]);
            int wordCount = Integer.parseInt(array[8]);
            int negWordCount = Integer.parseInt(array[9]);
            String numReviews = array[13];
            Seller seller = new Seller(array[0], new ArrayList<>(),
                                                            rating,
                                                            array[4],
                                                            array[3],
                                                            numFollowers,
                                                            wordCount,
                                                            negWordCount,
                                                            numReviews);

            Product product = new Product(array[1], array[6]);
            seller.updateProductList(product);
            this.map.put(username, seller);
        }
        else{
            Product product = new Product(array[1], array[6]);
            Seller seller = map.get(username);
            seller.updateProductList(product);
            this.map.put(username, seller);
        }
    }

    public static void saveRecord(String username, String reviews, String filepath){
        try{
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw= new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(username +""+reviews);
            pw.flush();
            pw.close();
        }catch(Exception e){
        }
    }

    /**
     * Returns HashMap of usernames with corresponding sellers
     * @return HashMap consisting of usernames and corresponding sellers
     */
    public HashMap<String, Seller> getListOfSellers(){
        return this.map;
    }
}
