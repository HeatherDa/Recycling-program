package com.Heather;
import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        //Recycling program.  Identify house with most recycling and house with least.  Allow for duplicates.
        int total = 0;
        int count = 0;
        int max = 0;
        int maxhouse = 0;
        int min = 100;
        int minhouse = 0;
        HashMap<Integer, Integer> minhash = new HashMap<>();
        HashMap<Integer, Integer> maxhash = new HashMap<>();
        HashMap<Integer, Integer>housecrates=new HashMap<>();
        try ( FileReader reader = new FileReader("recyclingreport-mainstreet.txt");
              BufferedReader bufReader = new BufferedReader(reader)) {
            housecrates = readfile("recyclingreport-mainstreet.txt");
        }catch(FileNotFoundException ioe) {
            System.out.println("File cannot be found.");
        }

        for (int housenum:housecrates.keySet()) {
            if (housecrates.get(housenum) > max) {
                max = housecrates.get(housenum);
                maxhouse = housenum;
            }
            if (housecrates.get(housenum) < min) {
                min = housecrates.get(housenum);
                minhouse = housenum;
            }
        }
        maxhash.put(maxhouse, max);
        minhash.put(minhouse, min);
        for (int temp:housecrates.keySet()) {
            if ((temp != maxhouse) && (housecrates.get(temp) == max)) {
                maxhash.put(temp, max);
            }
        }
        for (int temp2:housecrates.keySet()) {
            if ((temp2 != minhouse) && (housecrates.get(temp2) == min)) {
                minhash.put(temp2, min);
            }
        }
        //make strings for text file
        String[] filelines = new String[(housecrates.size()*2+4)]; //if all houses recycled the same number of containers, you would need this many positions in the array
        String heading="\nRecycling statistics for this street:\n";
        filelines[count]=heading;
        count++;
        for (int hnum : housecrates.keySet()) {
            String sentence = "House " + hnum + " recycled " + housecrates.get(hnum) + " crates.";
            filelines[count] = sentence;
            total=total+housecrates.get(hnum);
            count++;
        }
        String sentencetotal = "\nTotal crates recycled = " + total;
        filelines[count]=sentencetotal;
        count++;
        String sentence3="\nHouses that recycled the most: ";
        filelines[count]=sentence3;
        count++;
        for (int mhouse:maxhash.keySet()){
            String sentence2 = "House " + mhouse + " recycled " + maxhash.get(mhouse) + " crates.";
            filelines[count] = sentence2;
            count++;
        }
        String sentence4="\nHouses that recycled the least:";
        filelines[count]=sentence4;
        count++;
        for (int lhouse:minhash.keySet()){
            String sentence5 = "House " + lhouse + " recycled " + minhash.get(lhouse) + " crates.";
            filelines[count] = sentence5;
            count++;
        }
        writefile(filelines);
    }


    public static String writefile(String[] filelines) throws IOException {
        String filename="recyclingreport-mainstreet stats.txt";
        FileWriter writer = new FileWriter(filename);
        BufferedWriter bufWriter = new BufferedWriter(writer);

        for (String line:filelines) {
            if (line != null) {
                bufWriter.write(line + "\n");
            }
        }
        bufWriter.close();
        return filename;
    }

    public static HashMap<Integer, Integer> readfile(String filename) throws IOException {

        FileReader reader = new FileReader(filename);
        BufferedReader bufReader = new BufferedReader(reader);
        String line = bufReader.readLine();
        HashMap<Integer, Integer> crates = new HashMap<>();
        while (line != null) {
            String[] linetext = line.split(" ");
            crates.put(Integer.parseInt(linetext[1]), Integer.parseInt(linetext[3]));
            line = bufReader.readLine();

        }

        bufReader.close();   //This closes the inner FileReader too
        return crates;
    }

}
