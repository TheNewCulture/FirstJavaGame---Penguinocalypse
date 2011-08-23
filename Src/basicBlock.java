import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class basicBlock extends Objects {

	
	
	public basicBlock(int x, int y){
		blockX=x;
		blockY=y;
		image = new ImageIcon(getClass().getResource("woodBlock.png")).getImage();
		bounds = new Rectangle(blockX, blockY, image.getWidth(null), image.getHeight(null));
		health=100;
		matsReturn = 1;
		material="Wood";
		}
	
	public basicBlock(int x, int y, int type, char facing){
		blockX=x;
		blockY=y;
		if(type==0){
			if(facing=='w'||facing=='e'){
				image = new ImageIcon(getClass().getResource("woodBlockE.png")).getImage();
			}else if(facing=='n'){
				image = new ImageIcon(getClass().getResource("woodBlockN.png")).getImage();
			}else if(facing=='s'){
				image = new ImageIcon(getClass().getResource("woodBlockN.png")).getImage();
			}
			material="Wood";
			health=100;
			matsReturn = 1;
		}else if(type==1){
			if(facing=='w'||facing=='e'){
				image = new ImageIcon(getClass().getResource("stoneBlockN.png")).getImage();
			}else if(facing=='n'){
				image = new ImageIcon(getClass().getResource("stoneBlockN.png")).getImage();
			}else if(facing=='s'){
				image = new ImageIcon(getClass().getResource("stoneBlockN.png")).getImage();
			}
			material="Stone";
			health=1000;
			matsReturn = 0;
		}
		bounds = new Rectangle(blockX, blockY, image.getWidth(null), image.getHeight(null));
		isLethal=false;

	}
}
