package Algorithm;

import java.io.*;
public class HeapSort
{
    /**
     * To sort based on seller's reliability rating
     * @param arr SellerwithRating
     */
    public void sort(SellerwithRating arr[])
    {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>0; i--)
        {
            // Move current root to end
            SellerwithRating temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    /**
     * Heapify subtree with root at index i
     * @param arr all SellerwithRating
     * @param n size of heap
     * @param i index in arr[]
     */
    void heapify(SellerwithRating arr[], int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l].getRating() > arr[largest].getRating())
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r].getRating() > arr[largest].getRating())
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            SellerwithRating swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    /**
     * Print sorted array of SellerwithRating
     * @param arr all SellerwithRating
     */
    public void printArray(SellerwithRating arr[]) {
        String filepath = "CSV/result/shopeeResults4.csv";
        int n = arr.length;
        for (int i = 0; i < n; ++i) {

            saveRecord(arr[i].getSeller().getUsername(), arr[i].getRating(), filepath);
        }
        System.out.println();
    }

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
