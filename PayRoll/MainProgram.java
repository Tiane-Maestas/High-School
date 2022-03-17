import java.util.ArrayList;
import java.util.Scanner;
public class MainProgram
{
   public static void main(String args[]){
       
       ArrayList<Employee> employees = new ArrayList<>();
       
       employees.add(new Manager("Kaitlyn"));
       employees.add(new Salaried("Isai"));
       employees.add(new Hourly("Keller"));
       employees.add(new Manager("Kaitlyn"));
       employees.add(new Salaried("Isai"));
       employees.add(new Hourly("Keller"));
       
       Scanner input = new Scanner(System.in); 
       
       for(int i = 0; i < employees.size(); i++){
           
           if(employees.get(i).type.equals("Manager")){
               
               System.out.println("How many days did " + employees.get(i).Name + " work?");
               
               int days = input.nextInt();
               
               System.out.println(employees.get(i).Name + ": " + employees.get(i).calculatePay(days));
               
            }else if(employees.get(i).type.equals("Salaried")){
               
                System.out.println(employees.get(i).Name + ": " + employees.get(i).calculatePay(1));                    
                
            }else{
               
               System.out.println("How many hours did " + employees.get(i).Name + " work?");
               
               int hours = input.nextInt();
               
               System.out.println(employees.get(i).Name + ": " + employees.get(i).calculatePay(hours));
                                
            }
           
        }
               
    }
}
