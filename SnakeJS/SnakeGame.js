var canvas = document.querySelector('canvas');

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

var c = canvas.getContext('2d');


function mouse(){
    this.x = 0;
    this.y = 0;    
};

window.addEventListener('mousemove', function(e){

    mouse.y = e.y;
    mouse.x = e.x;
    //console.log(e);

});

var running = true;

 var unit = 25;

 var Snake = [];

 var xSpeed = 0;
 var ySpeed = 0;

 var appleX, appleY;

 function Bodypart(x, y){

    this.x = x;
    this.y = y;   
  
 }

 function createNewBodyPart(x, y){
    Snake.push(new Bodypart(x, y));
 }

 //intitialize
createNewBodyPart(innerWidth/2,innerHeight/2);
updateApple();

 function update(){

  movement();
  updateSnake();
  checkSnake();

 }

 function draw(){
   
    drawSnake();
    drawApple();
   
 }

 function movement(){

    if(mouse.x > Snake[0].x+unit){
        xSpeed = unit;
      }else if(mouse.x < Snake[0].x){
        xSpeed = -unit;  
      }else{
        xSpeed = 0;
      }
      
    if(mouse.y > Snake[0].y+unit){
        ySpeed = unit;
      }else if(mouse.y < Snake[0].y){
        ySpeed = -unit;  
      }else{
        ySpeed = 0; 
      }

      Snake[0].x += xSpeed/2;
      Snake[0].y += ySpeed/2;
   
   }

   function updateSnake(){

    for(i = Snake.length-1; i > 0; i--){
        
        Snake[i].x = Snake[i-1].x;
        Snake[i].y = Snake[i-1].y;
        
   }

   if(Snake[0].x >= appleX && Snake[0].y >= appleY && Snake[0].x <= appleX+unit && Snake[0].y <= appleY+unit){
     createNewBodyPart(Snake[Snake.length-1].x+unit, Snake[Snake.length-1].y);
     updateApple();
     }
  
    if(Snake[0].x+unit <= appleX && Snake[0].y+unit <= appleY && Snake[0].x+unit >= appleX+unit && Snake[0].y+unit >= appleY+unit){
     createNewBodyPart(Snake[Snake.length-1].x+unit, Snake[Snake.length-1].y);
     updateApple();
     }
  
    if(Snake[0].x+unit/2 >= appleX && Snake[0].y+unit/2 >= appleY && Snake[0].x+unit/2 <= appleX+unit && Snake[0].y+unit/2 <= appleY+unit){
     createNewBodyPart(Snake[Snake.length-1].x+unit, Snake[Snake.length-1].y);
     updateApple();
     }

   }

   function checkSnake(){

    for(i = Snake.length-1; i > 2; i--){
                
        if(Snake[0].x == Snake[i].x && Snake[0].y == Snake[i].y){
          running = false;
        }
              
     }

   }

   function drawSnake(){

    c.fillStyle = "rgba(0, 255, 0)";c.fillStyle = "rgba(0, 255, 0)";

    for(i = 0; i < Snake.length; i++){
    c.fillRect(Snake[i].x,Snake[i].y,unit,unit);
    }

   }

   function updateApple(){

    appleX = Math.random()*(innerWidth-2*unit);
    appleY = Math.random()*(innerHeight-2*unit);
    if(Snake.length>1){
    console.log(Snake.length-2);
    }

   }

   function drawApple(){

    c.fillStyle = "red";
    c.fillRect(appleX,appleY,unit,unit);

   }

 
 var count = 0;

function animate(){
    if(running){
    requestAnimationFrame(animate);
    }

    c.clearRect(0, 0, innerWidth, innerHeight);
  
 if(count > 3){
    update();
    count = 0;
  }

  draw();

  count++;

}

animate();