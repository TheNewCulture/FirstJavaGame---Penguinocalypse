import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Missiles {

	public Image graphic;
	private int startX;
	private int startY;
	public int currentX;
	public int currentY;
	public Rectangle hitBox;
	private int direction;
	private int changeX;
	private int changeY;
	public boolean hasHit=false;

	public Missiles(int x, int y, int Type, int facing){
		startX=x;
		startY=y;
		currentX=startX;
		currentY=startY;
		direction=facing;
		
		if(Type==0){
			if(direction==1){
				graphic = new ImageIcon(getClass().getResource("Arrow.png")).getImage();
				changeX=2;
			}else if(direction==2){
				graphic = new ImageIcon(getClass().getResource("ArrowS.png")).getImage();
				changeY=2;
			}else if(direction==3){
				graphic = new ImageIcon(getClass().getResource("ArrowW.png")).getImage();
				changeX=-2;
			}else{
				graphic = new ImageIcon(getClass().getResource("ArrowN.png")).getImage();
				changeY=-2;
			}
		}else{
			graphic = new ImageIcon(getClass().getResource("Arrow.png")).getImage();
		}
		hitBox= new Rectangle(startX,startY,graphic.getWidth(null),graphic.getHeight(null));
	}
	public void update(int x, int y){
		currentX+=changeX;
		currentY+=changeY;
		//currentY+=2;
		hitBox.setLocation(currentX+x,currentY+y);
		
	}
	public boolean isDead(){
		int traversedX=currentX-startX;
		int traversedY=currentY-startY;
		if(traversedX+traversedY>100||hasHit==true){
			return true;
		}else{
			return false;
		}
	}
}
