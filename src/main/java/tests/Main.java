package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("file1.txt");
        Scanner sc = new Scanner(f);
        String str;
        int n = 0;
        while (sc.hasNextLine()){
            str = sc.nextLine();
            if (str.length()>n){
                n = str.length();
            }
        }
        System.out.println(n);
    }
}
