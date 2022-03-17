public class BodyPart
{
   
    int x, y;
    
    public BodyPart(int x, int y)
    {
      this.x = x;
      this.y = y;
    }
    
    public int X(){
        return this.x;
    }
    
    public int Y(){
        return this.y;
    }
    
    public void setX(int input){
        this.x = input;
    }
    
     public void setY(int input){
        this.y = input;
    }

}
