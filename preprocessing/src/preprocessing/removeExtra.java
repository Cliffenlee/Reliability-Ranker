package preprocessing;
import java.io.*;
import java.util.*;

public class removeExtra {
    public static void main(String[] args){
        String path = "./negativeRootWords.txt";
        String filepath = "./negativeRootWordsFinal.txt";
        String line = "";

        HashSet<String> found = new HashSet<String>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            
           
            while((line = br.readLine()) != null){
                if(!found.contains(line)){
                    found.add(line);
                    saveRecord(line, filepath);
                }
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void saveRecord(String line, String filepath){
        try{
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw= new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(line);
            pw.flush();
            pw.close();
        }catch(Exception e){
        }
    }
}
