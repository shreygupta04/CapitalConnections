import java.io.*;
import java.util.*;

public class RandomPersonGenerator{
   public static void main(String[] args) throws Exception{
      char[] companies = new char[20];
      for(int i = 0; i < companies.length; i++){
         companies[i] = (char)(i+65);
      }
      
      for(int i = 2; i < 100; i++){
         String fileName = "data" + i + ".txt";
         File f = new File(fileName);
         System.setOut(new PrintStream(f));
         int totalCompanies = (int)(Math.random() * 30) + 10;
         
         for(int a = 0; a < totalCompanies; a++){
            int company = (int)(Math.random() * 20);
            double total = (int)(Math.random() * 99800)/100.0 + 2;
            System.out.println(company); 
            System.out.println("$" + total);   
         }
         }
   }
}