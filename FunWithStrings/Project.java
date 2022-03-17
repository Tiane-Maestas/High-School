import java.util.Scanner;  
import java.util.Stack; 

public class Project
{
    public static void main(String[] args){
        
        Scanner in = new Scanner(System.in);
        
        String upperCase = "";
        char[] second;
        int lastI = 0;
        char[] underScore;
        int vowels = 0;
        int[] vowelsPositions;
        Stack<Integer> vowelsPosition = new Stack<>();
        
        String input = in.nextLine();
        underScore = new char[input.length()];
        second = new char[input.length()];
        
        for(int i = 0; i < input.length(); i++){
            
           if(input.charAt(i) == 'a' || input.charAt(i) == 'e' ||input.charAt(i) == 'i' || 
           input.charAt(i) == 'o' ||input.charAt(i) == 'u' || input.charAt(i) == 'A' || 
           input.charAt(i) == 'E' ||input.charAt(i) == 'I' || 
           input.charAt(i) == 'O' ||input.charAt(i) == 'U'){
               vowels++;
               vowelsPosition.push(i);
               underScore[i] = '_';
            } 
            else{
               underScore[i] = input.charAt(i);
            }
            
            if(i != lastI && input.charAt(i) != ' '){
                second[i] = input.charAt(i);
                lastI = i+1;
            }
            
            if(Character.isUpperCase(input.charAt(i))){
                upperCase = upperCase + input.charAt(i);
            }
 
        }
        
        vowelsPositions = new int[vowels];
        
        System.out.print("Upper Case: ");
        System.out.println(upperCase);
        System.out.print("Every Second: ");
        System.out.println(second);
        System.out.print("Underscore Version: ");
        System.out.println(underScore);
        System.out.print("Number of Vowels: ");
        System.out.println(vowels);
        
        for(int i = vowels - 1; i >= 0; i--){
         vowelsPositions[i] = vowelsPosition.pop();
        }
        
        System.out.println( "Vowel:  Vowels Position:");
        
        for(int i = 0; i < vowelsPositions.length; i++){

         System.out.printf("%5d%18d", i, vowelsPositions[i]);
         System.out.println();
         
        }

    }
}
