import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class Penguins {

	public Image graphic;
	public int npcX;
	public int npcY;
	public Rectangle hitBox;
	private int changeX;
	private int changeY;
	public int health;
	private long fromTime;
	private long timePassed;
	private int moveSpeed;
	private Line2D W;
	private Line2D N;
	private Line2D S;
	private Line2D E;
	private int frame;
	private Image animation[];

	public Penguins(int x, int y, Image[] array){
		Random generator = new Random();
		npcX=x;
		npcY=y;
		animation= array;
		graphic = new ImageIcon(getClass().getResource("PenguinS.png")).getImage();
		hitBox= new Rectangle(npcX,npcY,graphic.getWidth(null),graphic.getHeight(null));
		frame=0;
		health=100;
		fromTime = System.currentTimeMillis();
		timePassed=0;
		moveSpeed = generator.nextInt(61)+20;
	}

	public void update(int x, int y,int pX, int pY){
		if(npcX>pX){
			changeX=-1;
		}else if(npcX<pX){
			changeX=1;
		}else{
			changeX=0;
		}
		if(npcY>pY){
			changeY=-1;
		}else if(npcY<pY){
			changeY=1;
		}else{
			changeY=0;
		}
		timePassed=System.currentTimeMillis()-fromTime;
		if(timePassed>moveSpeed){
			frame++;
			if(frame>3){
				frame=0;
			}
			graphic=animation[frame];
			npcX+=changeX;
			npcY+=changeY;
			fromTime=System.currentTimeMillis();
		}

		//currentY+=2;
		hitBox.setLocation(npcX+x,npcY+y);

	}
	public boolean isDead(){
		if(health<=0){
			return true;
		}else{
			return false;
		}

	}
	public void detectEntities(Objects R2){
		Point STLO = new Point((int)R2.bounds.getX(),(int)R2.bounds.getY());//typecast
		 Point STRO = new Point((int)R2.bounds.getX()+(int)R2.bounds.getWidth(),(int)R2.bounds.getY());//typecast
		 Point SBLO = new Point((int)R2.bounds.getX(),(int)R2.bounds.getY()+(int)R2.bounds.getHeight());//typecast
		 Point SBRO = new Point((int)R2.bounds.getX()+(int)R2.bounds.getWidth(),(int)R2.bounds.getY()+(int)R2.bounds.getHeight());//typecast
		 //Point STLI = new Point((int)R2.getX()+1,(int)R2.getY()+1);//typecast
		 //Point STRI = new Point((int)R2.getX()+(int)R2.getWidth()-1,(int)R2.getY()+1);//typecast
		 //Point SBLI = new Point((int)R2.getX()+1,(int)R2.getY()+(int)R2.getHeight()-1);//typecast
		 //Point SBRI = new Point((int)R2.getX()+(int)R2.getWidth()-1,(int)R2.getY()+(int)R2.getHeight()-1);//typecast
		 W = new Line2D.Float(STLO,SBLO);
		 N = new Line2D.Float(STLO,STRO);//TODO
		 S = new Line2D.Float(SBLO,SBRO);
		 E = new Line2D.Float(STRO,SBRO);//TODO
		 
		 if(R2.health!=1337){
			 if(hitBox.intersectsLine(N)){
				 npcY-=1;
				 R2.health-=2;
			 }else if(hitBox.intersectsLine(S)){
				 npcY+=1;
				 R2.health-=2;
			 }else if(hitBox.intersectsLine(E)){
				 npcX+=1;
				 R2.health-=2;
			 }else if(hitBox.intersectsLine(W)){
				 npcX-=1;
				 R2.health-=2;
			 }		 
		 }else{
			 if(hitBox.intersectsLine(N)){
				 npcY-=1;
				 npcX+=1;
			 }else if(hitBox.intersectsLine(S)){
				 npcY+=1;
				 npcX-=1;
			 }else if(hitBox.intersectsLine(E)){
				 npcX+=1;
				 npcY-=1;
			 }else if(hitBox.intersectsLine(W)){
				 npcX-=1;
				 npcY+=1;
			 }
			 
		 }
		 if(R2.isLethal&&hitBox.intersects(R2.bounds)){
			 health-=20;
		 }
	}
}

