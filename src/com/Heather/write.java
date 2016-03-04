package com.Heather;

        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.io.IOException;

public class write {

    public static void main(String[] args) throws IOException {
        String filelines=" ";
        writefile(filelines);
    }
    public static String writefile(String filelines) throws IOException {
        String filename="recyclingreport-mainstreet.txt";
        FileWriter writer = new FileWriter(filename);
        BufferedWriter bufWriter = new BufferedWriter(writer);

        for (int i=0;i<1;i++) {
            bufWriter.write(i + "\n");

        }
        bufWriter.close();
        return filename;
    }
}
