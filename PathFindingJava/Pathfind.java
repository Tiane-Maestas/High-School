import java.lang.Double;
import java.util.ArrayList;

public class Pathfind
{
   
    int unit;
    
    public ArrayList<Nodes> path;

    public Pathfind(int unit){
        
        this.unit = unit;
        
        path = new ArrayList<>();
  
    }
    
    public void start(Nodes[][] nodesArray, int startX, int startY, int endX, int endY, boolean startBlock, boolean endBlock){     
        
        if(startBlock && endBlock){
           
            aStar(nodesArray, startX, startY, endX, endY); 
            
        }
        
    }
    
    public void aStar(Nodes[][] nodesArray, int currentX, int currentY, int endX, int endY){
        
        if(currentX != endX || currentY != endY){
            
            
            for(int i = 0; i < path.size(); i++){
                
               nodesArray[pathX(i)][pathY(i)].update(false);
                
            }
        
        Nodes[] potentialNodes = new Nodes[8];
        
        int costUp, costDown, costLeft, costRight, costURight, costULeft, costDRight, costDLeft;
        
        costRight = 1000000;costLeft = 1000000;costUp = 1000000;costDown = 1000000;
        costURight = 1000000;costDRight = 1000000;costULeft = 1000000;costDLeft = 1000000;
        
        if(currentX+1 < nodesArray.length){
           if(nodesArray[currentX+1][currentY].getState()){
            
           potentialNodes[0] = new Nodes(currentX+1, currentY); 
           
           Double d = new Double(""+Math.sqrt(Math.pow((currentX+1)-endX,2)+Math.pow((currentY)-endY,2)));
            
           costRight = d.intValue();
          }
        }
        
        if(currentX+1 < nodesArray.length && currentY+1 < nodesArray[currentX].length){
          
           if(nodesArray[currentX+1][currentY+1].getState()){ 
           potentialNodes[1] = new Nodes(currentX+1, currentY+1); 
           
           Double d = new Double(""+Math.sqrt(Math.pow((currentX+1)-endX,2)+Math.pow((currentY+1)-endY,2)));
            
           costDRight = d.intValue(); 
           
          }
        }
        
        if(currentY+1 < nodesArray[currentX].length){
           
           if(nodesArray[currentX][currentY+1].getState()){  
           potentialNodes[2] = new Nodes(currentX, currentY+1); 
           
           Double d = new Double(""+Math.sqrt(Math.pow((currentX)-endX,2)+Math.pow((currentY+1)-endY,2)));
            
           costDown = d.intValue();
           
          }
        }
          
        if(currentX-1 >= 0 && currentY+1 < nodesArray[currentX].length){
            
            if(nodesArray[currentX-1][currentY+1].getState()){ 
            potentialNodes[3] = new Nodes(currentX-1, currentY+1); 
           
            Double d = new Double(""+Math.sqrt(Math.pow((currentX-1)-endX,2)+Math.pow((currentY+1)-endY,2)));
            
           costDLeft = d.intValue();

          }
        }
        
        if(currentX-1 >= 0){
            
            if(nodesArray[currentX-1][currentY].getState()){ 
           potentialNodes[4] = new Nodes(currentX-1, currentY); 
           
           Double d = new Double(""+Math.sqrt(Math.pow((currentX-1)-endX,2)+Math.pow((currentY)-endY,2)));
            
           costLeft = d.intValue();
 
          }
        }
        
        if(currentX-1 >= 0 && currentY-1 >= 0){
            
            if(nodesArray[currentX-1][currentY-1].getState()){ 
           potentialNodes[5] = new Nodes(currentX-1, currentY-1); 
                      
           Double d = new Double(""+Math.sqrt(Math.pow((currentX-1)-endX,2)+Math.pow((currentY-1)-endY,2)));
            
           costULeft = d.intValue();

          }
        }
        
        if(currentY-1 >= 0){
            
            if(nodesArray[currentX][currentY-1].getState()){ 
            potentialNodes[6] = new Nodes(currentX, currentY-1); 
           
            Double d = new Double(""+Math.sqrt(Math.pow((currentX)-endX,2)+Math.pow((currentY-1)-endY,2)));
            
           costUp = d.intValue();

          }
        }
        
        if(currentX+1 < nodesArray.length && currentY-1 >= 0){
            
            if(nodesArray[currentX+1][currentY-1].getState()){ 
            potentialNodes[7] = new Nodes(currentX+1, currentY-1); 
           
            Double d = new Double(""+Math.sqrt(Math.pow((currentX+1)-endX,2)+Math.pow((currentY-1)-endY,2)));
            
           costURight = d.intValue();

          }
        }
        
        String move = compareCosts(costUp, costDown, costLeft, costRight, costURight, costULeft, costDRight, costDLeft);  
        
        try{
        
        if(move.equals("RIGHT")){
            
            try{            
            path.add(new Nodes(potentialNodes[0].getPosX(), potentialNodes[0].getPosY()));
            aStar(nodesArray, potentialNodes[0].getPosX(), potentialNodes[0].getPosY(), endX, endY);
            }catch(NullPointerException e){
             System.err.println("No Found Path...");   
            }
            
        }
        
        if(move.equals("DOWNRIGHT")){
            
            try{
             path.add(new Nodes(potentialNodes[1].getPosX(), potentialNodes[1].getPosY()));
             aStar(nodesArray, potentialNodes[1].getPosX(), potentialNodes[1].getPosY(), endX, endY);
            }catch(NullPointerException e){
             System.err.println("No Found Path...");   
            }
            
        }
        
        if(move.equals("DOWN")){
            
            try{
             path.add(new Nodes(potentialNodes[2].getPosX(), potentialNodes[2].getPosY()));
             aStar(nodesArray, potentialNodes[2].getPosX(), potentialNodes[2].getPosY(), endX, endY);
            }catch(NullPointerException e){
             System.err.println("No Found Path...");   
            }

        }
        
        if(move.equals("DOWNLEFT")){
            
            try{
             path.add(new Nodes(potentialNodes[3].getPosX(), potentialNodes[3].getPosY()));
             aStar(nodesArray, potentialNodes[3].getPosX(), potentialNodes[3].getPosY(), endX, endY);
            }catch(NullPointerException e){
             System.err.println("No Found Path...");   
            }
            
        }
        
        if(move.equals("LEFT")){
            
            try{
             path.add(new Nodes(potentialNodes[4].getPosX(), potentialNodes[4].getPosY()));
            aStar(nodesArray, potentialNodes[4].getPosX(), potentialNodes[4].getPosY(), endX, endY);
           }catch(NullPointerException e){
             System.err.println("No Found Path...");   
            }

        }
        
        if(move.equals("UPLEFT")){
            
            try{
             path.add(new Nodes(potentialNodes[5].getPosX(), potentialNodes[5].getPosY()));
             aStar(nodesArray, potentialNodes[5].getPosX(), potentialNodes[5].getPosY(), endX, endY);
            }catch(NullPointerException e){
             System.err.println("No Found Path...");   
            }
            
        }
        
        if(move.equals("UP")){
            
            try{
             path.add(new Nodes(potentialNodes[6].getPosX(), potentialNodes[6].getPosY()));
             aStar(nodesArray, potentialNodes[6].getPosX(), potentialNodes[6].getPosY(), endX, endY);
            }catch(NullPointerException e){
             System.err.println("No Found Path...");   
            }
           
        }
        
        if(move.equals("UPRIGHT")){
            
            try{
             path.add(new Nodes(potentialNodes[7].getPosX(), potentialNodes[7].getPosY()));
             aStar(nodesArray, potentialNodes[7].getPosX(), potentialNodes[7].getPosY(), endX, endY);
            }catch(NullPointerException e){
             System.err.println("No Found Path...");   
            }
            
        }
        
        }catch(StackOverflowError e){
            System.err.println("No Found Path...");
        }
        
      }
      
    }
    
    public static String compareCosts(int costUp, int costDown, int costLeft, int costRight, int costURight, int costULeft, int costDRight, int costDLeft){
        
        
        int minValue = Math.min(Math.min(Math.min(costUp,costDown),Math.min(costLeft,costRight)),Math.min(Math.min(costURight,costULeft),Math.min(costDRight,costDLeft)));
        
        if(minValue == costUp){
           return "UP"; 
        }else if(minValue == costDown){
           return "DOWN"; 
        }else if(minValue == costLeft){
           return "LEFT"; 
        }else if(minValue == costRight){
           return "RIGHT"; 
        }else if(minValue == costURight){
           return "UPRIGHT"; 
        }else if(minValue == costULeft){
           return "UPLEFT"; 
        }else if(minValue == costDRight){
           return "DOWNRIGHT"; 
        }else{
           return "DOWNLEFT"; 
        }    
        
        
    }
    
    public int pathX(int i){        
        return path.get(i).getPosX();
    }
    
    public int pathY(int i){        
        return path.get(i).getPosY();
    }
    
    public int length(){
        return path.size();
    }
    
}
