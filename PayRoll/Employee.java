public class Employee
{   
    
    
    public String Name = "unknown";
    public String type = "Employee";
        
    public Employee()
    {
        
    }
    
    public double calculatePay(int hoursWorked){
        
        double payment;
        
        if(hoursWorked > 40){
            payment = 40*15;
            payment += (hoursWorked-40)*22.5;
        }else{
            payment = hoursWorked*15;
        }
        
        return payment;
    }
    
}
