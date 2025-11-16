import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {


   /*  older project for problemsolving
   public static int[] runningSum(int[] nums) {


        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    public static int maximumWealth(int[][] accounts) {

        int currnt = 0;
        for (int i = 0; i < accounts.length; i++) {
            int person = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                person += accounts[i][j];
            }
            if (currnt < person) {
                currnt = person;
            }
        }
        return currnt;
    }
*/
  /* int[][] arr = {{1, 2, 3}, {4, 5, 6}};   useage
        int max = maximumWealth(arr);
        System.out.println(max);

       int[] newArr= runningSum(arr);
        for (int i : newArr){
            System.out.println(i);
        }
    }*/

    public static void main(String[] args) {

      String filePath= "words.txt";
      ArrayList<String> words =new ArrayList<>();

      try(BufferedReader reader= new BufferedReader(new FileReader(filePath))) {
          String line;
          while ((line = reader.readLine())!=null){
              words.add(line.trim());
          }
      }
      catch (FileNotFoundException e){
          System.out.println("Could not find file");
      }
      catch (IOException e){
          System.out.println("something went wrong");
      }
        Random random = new Random();

      String word = words.get(random.nextInt(words.size()));
        Scanner scanner = new Scanner(System.in);
        ArrayList<Character>wordState = new ArrayList<>();
        int wrongGuesses = 0 ;
        for (int i =0;i <word.length();i++)
        {
            wordState.add('_');
        }
       while (wrongGuesses<6){
           System.out.print(getHangman(wrongGuesses));
           System.out.print("Word: ");
           for (char c :wordState){
               System.out.print(c+" ");
           }
           System.out.println();

           System.out.print("guess a letter: ");
           char guess = scanner.next().toLowerCase().charAt(0);
           if (word.indexOf(guess)>=0){
               System.out.println("Correct guess!");
               for (int i = 0;i<word.length();i++){
                   if (word.charAt(i)==guess){
                       wordState.set (i,guess);
                   }
               }
               if (!wordState.contains('_')){
                   System.out.println(getHangman(wrongGuesses));
                   System.out.println("you win");
                   System.out.println("the word was:  "+word);
                   break;
               }
           }else {
               wrongGuesses++;
               System.out.println("Wrong guess!");
           }
       }
       if (wrongGuesses>=6){
           System.out.print(getHangman(wrongGuesses));
           System.out.println("Game over");
           System.out.println("The word was : " +word);
       }

        scanner.close();
    }

    static String getHangman(int wrongGuesses ){
     return switch (wrongGuesses){
         case 0 -> """
                 
                 
                 
                 """;
         case 1 -> """
                    o
                 
                 
                   """;
         case 2 -> """
                    o
                    |
                 
                   """;
         case 3 -> """
                    o
                   /|
                 
                   """;
         case 4 -> """
                    o
                   /|\\
                 
                   """;
         case 5 -> """
                    o
                   /|\\
                   /
                   """;
         case 6 -> """
                    o
                   /|\\
                   / \\
                   """;
         default -> "";
     };
    }
}