var canvas = document.querySelector('canvas');

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

var c = canvas.getContext('2d');

// strings can be 'String' or "String"
// random is Math.random() between 0 and 1.

//rectangle
// c.fillStyle = "rgba(0, 255, 255, 0.2)";
// c.fillRect(100,100,100,100);
// c.fillStyle = "green";
// c.fillRect(300,300,100,100);
//console.log(canvas);

// line 
// c.beginPath();
// c.moveTo(50, 300);
// c.lineTo(300, 100);
// c.lineTo(400, 300);
// c.strokeStyle = "red";
// c.stroke();

//arc and circles
// c.beginPath();
// c.arc(250, 250, 50, 0, Math.PI * 2, false);
// c.strokeStyle = "blue";
// c.stroke();


function Circle(x, y ,r, dx, dy){

 this.x = x;
 this.y = y;
 this.r = r;
 this.dx = dx;
 this.dy = dy;

 this.draw = function(){

    c.beginPath();
    c.arc(this.x, this.y, this.r, 0, Math.PI * 2, false);
    c.fillStyle = "rgba(255, 255, 255, .2)";
    c.fill();

 }

 this.update = function(){

    if((this.x+this.r) > innerWidth){
    this.dx = this.dx*(-1);
    }else if((this.y+this.r) > innerHeight-100){
     this.dy = this.dy*(-1);
    }else if((this.x-this.r) < 0){
     this.dx = this.dx*(-1);
    }else if((this.y-this.r) < 0){
     this.dy = this.dy*(-1);
    }
   
    this.x += this.dx;
    this.y += this.dy;

  }
}

 var circArray = [];

for(var i = 0; i < 250; i++){

     var r = Math.random()*50;
     var x = Math.random() * (innerWidth - r*2) + r;
     var y = Math.random() * ((innerHeight-100) - r*2) + r;
     var dx = (Math.random() - .5)*5;
     var dy = (Math.random() - .5)*5;

    circArray.push(new Circle(x, y, r, dx, dy));

}

 console.log(circArray);

 // var circ = new Circle(200, 200, 50, 3, 3);

function animate(){
    requestAnimationFrame(animate);

    c.clearRect(0, 0, innerWidth, innerHeight);
  
    for(var i = 0; i < circArray.length; i++){
     circArray[i].draw();
     circArray[i].update();   
    }

    c.fillStyle = "rgba(210, 180, 140, .75)";
    c.fillRect(0,innerHeight-100, innerWidth, innerHeight-100);
  
   // circ.draw();
   // circ.update();
  
}

animate();