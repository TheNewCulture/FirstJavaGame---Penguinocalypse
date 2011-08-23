import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Buildings extends Objects {
	
	public Image innerImage;
	public Rectangle inside;
	public Rectangle door;
	
	public Buildings(int x, int y, int type){
		blockX=x;
		blockY=y;
		if(type==0){
			image = new ImageIcon(getClass().getResource("houseTest.png")).getImage();
			innerImage = new ImageIcon(getClass().getResource("roomTest.png")).getImage();
			material="Wood";
			health=1337;
			matsReturn = 0;
		}else if(type==1){
			image = new ImageIcon(getClass().getResource("Error.png")).getImage();
			material="Stone";
			health=1337;
			matsReturn = 0;
		}
		bounds = new Rectangle(blockX, blockY, image.getWidth(null), image.getHeight(null));
		inside = new Rectangle(blockX+5, blockY+5, innerImage.getWidth(null)-5, innerImage.getHeight(null)-5);
		door = new Rectangle(blockX+32, blockY+62, 17, 20);

	}
	public void update(int x, int y){
		bounds.setLocation(blockX+x,blockY+y);
		inside.setLocation(blockX+5+x,blockY+5+y);
		door.setLocation(blockX+32+x,blockY+62+y);
	}
}
