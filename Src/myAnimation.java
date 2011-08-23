import java.awt.Image;
import java.util.*;

public class myAnimation {//Handles animations //5:30 walkthrough
	
	long startingTime = System.currentTimeMillis();//current time(right now! :D)
	long cumulativeTime = startingTime;
	private ArrayList scenes;//object array of each image
	private int sceneindex;//the order of the scenes
	private long animationtime;//the current runtime
	private long totaltime;//the runtime of the entire animation
	private boolean running;
	
	//CONSTRUCTOR
	public myAnimation(){
		scenes = new ArrayList();//gives everything a new animation
		totaltime = 0;
		running=true;
		start();
	}
	//ADDING A SCENE
	public synchronized void addScene(Image i, long t){//only one can run at once
		totaltime += t;//sets the total runtime of all pics
		scenes.add(new AnimationScene(i,totaltime));//TIME TO PLAY
	}
	//RESETS EVERYTHING TO 0
	public synchronized void start(){//animation plays from beginning
		animationtime = 0;
		sceneindex = 0;
	}
	//CHANGING SCENE
	public synchronized void update(){//changes the frame
		long timepassed = System.currentTimeMillis() - cumulativeTime;//gets the time for this loop
		cumulativeTime += timepassed;
		if(running){
			if(scenes.size()>1){
				animationtime += timepassed;//updates the runtime
				if(animationtime>=totaltime){//starts the animation over again if the runtime is greater than the animation
					animationtime = 0;
					sceneindex = 0;
				}
				while(animationtime>getScene(sceneindex).endTime){
					sceneindex++;//when do we move to the next scene
				}
			}
		}
	}
	//gets the animations current scene and image
	public synchronized Image getImage(){
		if(scenes.size()==0){
			return null;
		}else{
				return getScene(sceneindex).pic;//gets current scene and pic
		}	
	}
	
	//GET SCENE
	private AnimationScene getScene(int x){
		return (AnimationScene)scenes.get(x);//returns the index object of the array
	}
	
	public void pause(){
		running=false;
	}
	public void play(){
		running=true;
	}
///////////////   PRIVATE INNER CLASS   //////////////////////
	private class AnimationScene{
		Image pic;
		long endTime;
		
		public AnimationScene(Image pic,Long endTime){//image and starttime
			this.pic = pic;
			this.endTime = endTime;
		}
	}
}
