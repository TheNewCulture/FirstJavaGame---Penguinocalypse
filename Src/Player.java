import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;



public class Player extends Character {
	
	public int relativeX=0;
	public int relativeY=0;
	private int outsideY=0;
	private int outsideX=0;
	public boolean hasTool = false;
	public int health = 100;
	
	public Rectangle r = new Rectangle(0,0,7,3);
	
	public int getRelativeX(){
		return relativeX;
	}
	
	public int getRelativeY(){
		return relativeY;
	}

	public Player(int x, int y){
		this.charx = x;
		this.chary = y;
	}
	
	public void update(){
		r.setLocation(charx+9, chary+21);
		if(aniArray!=null){
			aniArray[face].update();
		}
		if(vN>0&&canMoveN){
			if(relativeY<=100&&relativeY>=-446){
				relativeY+=1;
			}else{
				chary-=vN;
				outsideY+=1;
				if(outsideY==0){
					relativeY+=1;
				}
			}
		}
		if(vE>0&&canMoveE){
			if(relativeX>=-494&&relativeX<=100){
				relativeX-=1;
			}else{
				charx+=vE;
				outsideX+=1;
				if(outsideX==0){
					relativeX-=1;
				}
			}
		}
		if(vS>0&&canMoveS){
			if(relativeY>=-446&&relativeY<=100){
				relativeY-=1;	
			}else{
				chary+=vS;
				outsideY-=1;
				if(outsideY==0){
					relativeY-=1;
				}
			}
		}
		if(vW>0&&canMoveW){
			if(relativeX<=100&&relativeX>=-494){
				relativeX+=1;
				}else{
				charx-=vW;
				outsideX-=1;
				if(outsideX==0){
					relativeX+=1;
				}
			}
		}
		
	}
	public void isMoving(){
		if(vN+vE+vW+vS==0){
			moving=false;
		}
	}
	public void collideOn(Rectangle R1, Objects R2){
		 //Rectangle r = new Rectangle(0,0,300,180);
		 //Point FTL = new Point((int)R1.getX(),(int)R1.getY());//typecast
		 //Point FTR = new Point((int)R1.getX()+(int)R1.getWidth(),(int)R1.getY());//typecast
		 //Point FBL = new Point((int)R1.getX(),(int)R1.getY()+(int)R1.getHeight());//typecast
		 //Point FBR = new Point((int)R1.getX()+(int)R1.getWidth(),(int)R1.getY()+(int)R1.getHeight());//typecast
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
		 //WI = new Line2D.Float(STLI,SBLI);
		 //NI = new Line2D.Float(STLI,STRI);//TODO
		 //SI = new Line2D.Float(SBLI,SBRI);
		 //EI = new Line2D.Float(STRI,SBRI);//TODO
		 
		 if(notOnDoor){

			 if(R1.intersectsLine(W)){
				 relativeX+=1;
				 if(hasTool&&R2.health!=1337){
					 R2.health-=25;
				 }
			 //}else if(R1.intersectsLine(WI)){
				 //canMoveW=false;
			 }else{
				 //canMoveE=true;
				 //canMoveW=true;
			 }
			 if(R1.intersectsLine(S)){
				relativeY-=1;
				if(hasTool&&R2.health!=1337){
					 R2.health-=25;
				 }
			 //}else if(R1.intersectsLine(WI)){
				 //canMoveS=false;
			 }else{
				 //canMoveN=true;
				 //canMoveS=true;
			 }
			 if(R1.intersectsLine(N)){
				 relativeY+=1;
				 if(hasTool&&R2.health!=1337){
					 R2.health-=25;
				 }
			 //}else if(R1.intersectsLine(WI)){
				 //canMoveN=false;
			 }else{
				 //canMoveS=true;
				 //canMoveN=true;
			 }
			 if(R1.intersectsLine(E)){
				 relativeX-=1;
				 if(hasTool&&R2.health!=1337){
					 R2.health-=25;
				 }
			 //}else if(R1.intersectsLine(WI)){
				 //canMoveE=false;
			 }else{
				 //canMoveW=true;
				 //canMoveE=true;
			 } 
		 }
	}
	public void onDoor(Rectangle R1, Rectangle R2){
		if(R1.intersects(R2)){
			notOnDoor=false;
			//return 1;
		}else{
			//return 0;
		}
	}
	public boolean inHouse(Rectangle R1, Rectangle R2){
		if(R1.intersects(R2)){
			return true;
		}else{
			return false;
		}
	}
	public Image getResource(int Type){
		if(Type==0){
			if(face==1||face==3){
				return new ImageIcon(getClass().getResource("woodBlockE.png")).getImage();
			}
			if(face==2){
				return new ImageIcon(getClass().getResource("woodBlock.png")).getImage();
			}
			if(face==4){
				return new ImageIcon(getClass().getResource("woodBlockN.png")).getImage();
			}else{
				return new ImageIcon(getClass().getResource("Error.png")).getImage();
			}

		}if(Type==1){
			return new ImageIcon(getClass().getResource("stoneBlockN.png")).getImage();
			}else{
				return new ImageIcon(getClass().getResource("Error.png")).getImage();
			}

	}

}