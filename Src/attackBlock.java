import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class attackBlock extends Objects {
	public attackBlock(int x, int y, int type){
		blockX=x;
		blockY=y;
		if(type==2){		
			image = new ImageIcon(getClass().getResource("Caltrop.png")).getImage();		
			material="Wood";
			health=100;
			matsReturn = 0;
		}else if(type==1){
			image = new ImageIcon(getClass().getResource("stoneBlockN.png")).getImage();
			material="Stone";
			health=1000;
			matsReturn = 0;
		}
		bounds = new Rectangle(blockX, blockY, image.getWidth(null), image.getHeight(null));
		isLethal=true;

	}
}
