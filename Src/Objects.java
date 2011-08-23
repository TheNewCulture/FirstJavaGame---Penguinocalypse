import java.awt.Image;
import java.awt.Rectangle;


public class Objects {
	
	public int blockX;
	public int blockY;
	public Image image;
	public int health;
	public Rectangle bounds;
	public int matsReturn;
	public String material;
	protected boolean isLethal;
	
	public void update(int x, int y){
		bounds.setLocation(blockX+x,blockY+y);
	}
	/*public static boolean notOnDoor=true;
	public static boolean canMoveN;
	public static boolean canMoveE;
	public static boolean canMoveS;
	public static boolean canMoveW;
	public Line2D W;
	public Line2D S;
	public Line2D E;
	public Line2D N;
	
	public void collideOn(Rectangle R1, Rectangle R2){
		 //Rectangle r = new Rectangle(0,0,300,180);
		 //Point FTL = new Point((int)R1.getX(),(int)R1.getY());//typecast
		 //Point FTR = new Point((int)R1.getX()+(int)R1.getWidth(),(int)R1.getY());//typecast
		 //Point FBL = new Point((int)R1.getX(),(int)R1.getY()+(int)R1.getHeight());//typecast
		 //Point FBR = new Point((int)R1.getX()+(int)R1.getWidth(),(int)R1.getY()+(int)R1.getHeight());//typecast
		 Point STLO = new Point((int)R2.getX(),(int)R2.getY());//typecast
		 Point STRO = new Point((int)R2.getX()+(int)R2.getWidth(),(int)R2.getY());//typecast
		 Point SBLO = new Point((int)R2.getX(),(int)R2.getY()+(int)R2.getHeight());//typecast
		 Point SBRO = new Point((int)R2.getX()+(int)R2.getWidth(),(int)R2.getY()+(int)R2.getHeight());//typecast
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
				 canMoveE=false;
			 //}else if(R1.intersectsLine(WI)){
				 //canMoveW=false;
			 }else{
				 canMoveE=true;
				 //canMoveW=true;
			 }
			 if(R1.intersectsLine(S)){
				 canMoveN=false;
			 //}else if(R1.intersectsLine(WI)){
				 //canMoveS=false;
			 }else{
				 canMoveN=true;
				 //canMoveS=true;
			 }
			 if(R1.intersectsLine(N)){
				 canMoveS=false;
			 //}else if(R1.intersectsLine(WI)){
				 //canMoveN=false;
			 }else{
				 canMoveS=true;
				 //canMoveN=true;
			 }
			 if(R1.intersectsLine(E)){
				 canMoveW=false;
			 //}else if(R1.intersectsLine(WI)){
				 //canMoveE=false;
			 }else{
				 canMoveW=true;
				 //canMoveE=true;
			 } 
		 }
	}*/
}
