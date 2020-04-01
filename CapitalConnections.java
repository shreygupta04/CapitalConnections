import java.io.*;
import java.util.*;

public class CapitalConnections
{
   public static void main(String[] args) throws Exception
   {
      List<Map<String, Double>> listOfMaps = new ArrayList<Map<String, Double>>();
      for(int i = 0; i < 98; i++){
         String fileName = "data" + (i+2) + ".txt";
         listOfMaps.add(individualTierScores(new File(fileName)));
      }
      double[] companyTotals = new double[20];
      for(int i = 0; i < listOfMaps.size(); i++){
         for(String str: listOfMaps.get(i).keySet())
            companyTotals[Integer.valueOf(str)] += listOfMaps.get(i).get(str) / 100.0;
      }
      HashMap<String, Double> finalMap = new HashMap<String, Double>();
      for(int i = 0; i  < companyTotals.length; i++) {
         finalMap.put(Integer.toString(i), companyTotals[i]);
      }
      List<Map.Entry<String, Double>> list = new ArrayList<>(finalMap.entrySet());
      list.sort(Map.Entry.comparingByValue());
      for(Map.Entry<String, Double> entry: list) {
      }
      
      String top1 = list.get(list.size() - 1).getKey();
      String top2 = list.get(list.size() - 2).getKey();
      String top3 = list.get(list.size() - 3).getKey();
      System.out.println("Most beneficial company to partner with: " + "Company " + top1 + "\n" + "Second most beneficial company to partner with: " + "Company " + top2 + "\n" + "Third most beneficial company to partner with: "  + "Company " + top3);
   }
   public static TreeMap<String, Double> individualTierScores(File f){ 
      try{
      Scanner infile = new Scanner(f);
      int numitems = 0;
      while(infile.hasNext())
      {
         infile.next();
         numitems++;
      }
      infile.close();
      Scanner s = new Scanner(f);
      String[] companyData = new String[numitems];
      ArrayList<String> company = new ArrayList<String>();
      ArrayList<Double> amount = new ArrayList<Double>();
      for(int i = 0; i < numitems; i++) {
         companyData[i] = s.next();
         if (companyData[i].contains("$")) {
            companyData[i] = companyData[i].replace("$", "");
         }
         if(i%2 == 0) {
            company.add(companyData[i]);
         }
         else {
            amount.add(Double.valueOf(companyData[i]));
         }
      }
      
      TreeMap<String, Double> companyAndAmount = new TreeMap<String, Double>();
      for(int i = 0; i < company.size(); i++) {
         if(!companyAndAmount.containsKey(company.get(i)))
             companyAndAmount.put(company.get(i), 0.0);
              
         companyAndAmount.put(company.get(i), companyAndAmount.get(company.get(i)) + amount.get(i));
      }
      
      TreeMap<String, Integer> companyAndFrequency = new TreeMap<String, Integer>();
      for(int i = 0; i < company.size(); i++){
         if(!companyAndFrequency.containsKey(company.get(i)))
            companyAndFrequency.put(company.get(i), 0);
          
          companyAndFrequency.put(company.get(i), companyAndFrequency.get(company.get(i)) + 1);
      }
      
      TreeMap<String, Double> companyAndTierScore = new TreeMap<String, Double>();
      for(String str: companyAndAmount.keySet()){
         double tierScore = 0.0;
         tierScore = (0.7 * companyAndAmount.get(str)) + (0.93 * companyAndFrequency.get(str));
         tierScore = ((int)(tierScore * 100) + 0.0)/(100.0);
         companyAndTierScore.put(str, tierScore);
      } 
      
      return companyAndTierScore;
      }
      catch(Exception E){
         return null;
      }
    
   }
   
   
}
