public class Salaried extends Employee
{

    public Salaried(String name)
    {
       this.Name = name;
        this.type = "Salaried";
    }
    
    public double calculatePay(int filler){
        
        double payment = 1000;
     
        return payment;
        
    }
    
    //get paid their slaray no matter how many hours they work
    
}
