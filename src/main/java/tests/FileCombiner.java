package tests;

import java.io.*;

public class FileCombiner {
    public static void main(String[] args) throws IOException {
        FileOutputStream os = new FileOutputStream(args[2]);
        FileInputStream is1 = new FileInputStream(args[0]);
        FileInputStream is2 = new FileInputStream(args[1]);
        int bytes = 0;
        while (bytes != -1) {
            if ((bytes = is1.read()) != -1) {
                os.write(bytes);
            } else if ((bytes = is2.read()) != -1) {
                os.write(bytes);
            }
        }
        is1.close();
        is2.close();
        os.close();
    }
}


