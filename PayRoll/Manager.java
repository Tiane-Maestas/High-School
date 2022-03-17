public class Manager extends Employee
{
    
    public Manager(String name)
    {
        this.Name = name;
        this.type = "Manager";
    }
    
    public double calculatePay(int daysWorked){
        
        double payment = 1000;
        
        if(daysWorked > 5){
            payment += 500;
        }
        
        return payment;
    }
   
    // they get a salary and a bonus
    
}
