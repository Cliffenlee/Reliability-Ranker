package Algorithm;
import java.util.*;

public class Application {
    // Main method to run the application
    // Sorted list of sellers with their corresponding reliability ratings will be printed out
    public static void main(String[] args){
        EntityConstructor constructor = new EntityConstructor();
        HashMap<String, Seller> map = new HashMap<>();

        map = constructor.createEntityList();
        List<Seller> list = new ArrayList<>(map.values());
        System.out.println(list.size());
        SellerwithRating[] arr = new SellerwithRating[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Seller seller = list.get(i);
            arr[i] = new SellerwithRating(seller);
        }
        HeapSort heapsort = new HeapSort();
        heapsort.sort(arr);
        heapsort.printArray(arr);
    }
}
