import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;


public class Character {
	long startingTime = System.currentTimeMillis();//current time(right now! :D)
	long cumulativeTime = startingTime;
	protected boolean moving;
	public long timePassed;
	protected int face=1;
	protected boolean canMoveW = true;
	protected boolean canMoveN = true;
	protected boolean canMoveS = true;
	protected boolean canMoveE = true;
	public boolean notOnDoor = true;
	protected int chary;
	protected int charx;
	protected int vN=0;
	protected int vE=0;
	protected int vS=0;
	protected int vW=0;
	protected myAnimation[] aniArray;
	protected Image[] idleArray;
	public Line2D W;
	public Line2D S;
	public Line2D E;
	public Line2D N;
	public Line2D NI;
	public Line2D WI;
	public Line2D EI;
	public Line2D SI;
	
	public myAnimation[] getAnimation(){
		return aniArray;
	}
	
	public void update(){
		
	}
	
	public void addAnimation(myAnimation[] array){
		this.aniArray = array;
	}
	public void addIdle(Image[] array){
		this.idleArray = array;
	}
	
	public boolean canMove(char dir){
		if(dir=='w'){
			return canMoveW;
		}
		if(dir=='s'){
			return canMoveS;
		}
		if(dir=='e'){
			return canMoveE;
		}
		if(dir=='n'){
			return canMoveN;
		}
		else{
			return true;
		}
	}
	public void moveN(){
		if(canMoveN){
			vN=1;
		}
		face=4;
	}
	public void moveS(){
		if(canMoveS){
			vS=1;
		}
		face=2;
	}
	public void moveE(){
		if(canMoveE){
			vE=1;
		}
		face=1;
	}
	public void moveW(){
		if(canMoveW){
			vW=1;
		}
		face=3;
	}
	public void stopMoving(int key){
		if(key==KeyEvent.VK_W){
			vN=0;
		}
		if(key==KeyEvent.VK_D){		
			vE=0;
		}
		if(key==KeyEvent.VK_S){
			vS=0;
		}
		if(key==KeyEvent.VK_A){
			vW=0;
		}
	}
	public int getCharx(){
		return charx;
	}
	public int getFacing() {
		// TODO Auto-generated method stub
		return face;
	}
	public int getChary(){
		return chary;
	}
	public myAnimation getImage(){
		return aniArray[face];
	}
	public Image getIdle(){
		return idleArray[face];
	}
	

}

