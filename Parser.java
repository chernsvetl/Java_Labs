import java.lang.*;
import java.io.*;
import java.util.*;


public class Parser {
    public static void main (String[] args) throws FileNotFoundException {
        Reader reader = null;


        HashMap <String, Integer> hashMap = new HashMap<>();

        int NumberOfWords = 0;

        try {

            reader = new InputStreamReader(new FileInputStream("input.txt"));

            StringBuilder word = new StringBuilder();
            int symbolNum = reader.read();
            while (symbolNum != -1) {
                char currentSymb = (char) symbolNum;
                if (Character.isLetterOrDigit(currentSymb)) {
                    word.append(currentSymb);
                } else {
                    if (word.length() != 0) {
                        String string = word.toString();


                        int currentCountInMap;
                        if(hashMap.containsKey(string)){

                            currentCountInMap = hashMap.get(string)+1;
                            hashMap.put(string, currentCountInMap);
                        }
                        else {
                            hashMap.put(string, 1);
                        }

                        word.setLength(0);
                        NumberOfWords++;

                    }

                }

                symbolNum = reader.read();
            }


            if (word.length() != 0) {
                String string = word.toString();
                int currentCountInMap;
                if (hashMap.containsKey(string)) {

                    currentCountInMap = hashMap.get(string) + 1;
                    hashMap.put(string, currentCountInMap);
                } else {
                    hashMap.put(string, 1);
                }
                NumberOfWords++;
            }



        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }

        List <Map.Entry<String, Integer>> list = new LinkedList<>(hashMap.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> first, Map.Entry<String, Integer> second) {
                return (second.getValue()).compareTo(first.getValue());
            }
        });

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("output.txt"));
            for (Map.Entry<String,Integer> word : list) {
                writer.write(word.getKey() + ' ' + word.getValue() + ' '  + ((double) (word.getValue()) /  NumberOfWords *100) + '\n');

            }
        }
        
        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }


        finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
