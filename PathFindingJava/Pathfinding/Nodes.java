public class Nodes
{
    
    private boolean state;
    private int posX, posY;
    
    public Nodes(boolean state, int posX, int posY){
        
        this.state = state;
        this.posX = posX;
        this.posY = posY;
        
    }
    
    public Nodes(int posX, int posY){
        
        this.posX = posX;
        this.posY = posY;
        
    }
    
    public void update(boolean state){
        
        this.state = state;
        
    }
    
    public boolean getState(){
        return state;
    }
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    
}


