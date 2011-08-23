import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class resourceBlock extends Objects {

	public resourceBlock(int x, int y, int Type){
		blockX=x;
		blockY=y;
		if(Type==1){
			image = new ImageIcon(getClass().getResource("treeTrunk.png")).getImage();
			health=1000;
			matsReturn = 4;
			material="Wood";
		}else if(Type==2){
			image = new ImageIcon(getClass().getResource("rockTest.png")).getImage();
			health=1000;
			matsReturn = 4;
			material="Stone";
		}
		
		bounds = new Rectangle(blockX, blockY, image.getWidth(null), image.getHeight(null));
		isLethal=false;
		
	}
	

}
