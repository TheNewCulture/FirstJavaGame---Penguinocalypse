import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

import javax.swing.*;

import java.applet.*;

public class RunApplet extends JApplet implements Runnable {
	
	final static int WIDTH  = 200;
	final static int HEIGHT = 180;
	public String message = "";
	AudioClip ac;
	private Image image;
	private Image ghostImage;
	private Image statusBar;
	private Image idleArray[]= new Image[5];
	private myAnimation aniObjectIdle;
	private myAnimation aniObject;
	private myAnimation aniObjectN;
	private myAnimation aniObjectW;
	private myAnimation aniObjectS;
	private boolean Ghosting=false;
	private int wood=5;
	private int stone=1;
	private int resource=1;
	private boolean drawConsole=false;
	public myAnimation aniArray[] = new myAnimation[5];
	private ArrayList<Objects> entities;
	private ArrayList<Buildings> buildings;
	private ArrayList<Missiles> playerAttack;
	private ArrayList<Penguins> npcArray;
	private Image penguinAnimation[] = new Image[4];
	Graphics bufferGraphics;
	Image offScreen;
	Dimension dim;
	Thread runner = null;
	userInput inputobj;
	Player player = new Player(90,80);
	Rectangle r2 = new Rectangle(20,20,20,20);
	Random randometer = new Random();
	
	public void init(){//initialisation method
		
		//ac = getAudioClip(getDocumentBase(),"Town.wav");
		//ac.loop();   //play continuously
		dim = getSize();
		setBackground(Color.BLACK);
		
		offScreen = createImage(dim.width,dim.height);
		bufferGraphics = offScreen.getGraphics();
		inputobj = new userInput();
		addMouseMotionListener(inputobj);
		addMouseListener(inputobj);
		addKeyListener(inputobj);
		statusBar = new ImageIcon(getClass().getResource("Heart.png")).getImage();
		loadPics();
		loadEntities();
		playerAttack = new ArrayList();
	}
	
	public void start() {//when user enters applet

	    if ( runner == null ) {

	        runner = new Thread( this );
	        runner.start();
	    }
	}
	
	public void stop() {//when user exits applet

	    //ac.stop();
	    runner = null;
	}
	
	public void run(){
		while ( runner != null ) {

	        repaint();

	        try {

	            Thread.sleep(20);

	        } catch ( InterruptedException e ) {}
	            // do nothing
		}
	}

	public void paint(Graphics g) {
		//int numDoors=0;
		int rX=player.getRelativeX();
		int rY=player.getRelativeY();
		boolean notInBuilding=true;
		bufferGraphics.clearRect(0,0,dim.width,dim.width);
		bufferGraphics.setColor(Color.WHITE);
		r2.setLocation(20+rX, 20+rY);
		
		
		//Iterator<Objects>  iterator1 = entities.iterator();//size is the last index, therefore it starts at the end
		//while(iterator1.hasNext()){
		//iterator1.next().update(rX, rY);
		//bufferGraphics.drawImage(iterator1.next().image, iterator1.next().blockX+rX, iterator1.next().blockX+rY, null);
		//}
		for (int x =0;x<buildings.size();x++) {
            buildings.get(x).update(rX, rY);
            player.onDoor(player.r, buildings.get(x).door);
            player.collideOn(player.r, buildings.get(x));
            if(player.inHouse(player.r, buildings.get(x).inside)){
            	notInBuilding=false;
            }
            //if(notInBuilding){
            	//bufferGraphics.drawImage(buildings.get(x).image, buildings.get(x).blockX+rX,buildings.get(x).blockY+rY, null);
            //}else{
            	//bufferGraphics.drawImage(buildings.get(x).innerImage, buildings.get(x).blockX+rX,buildings.get(x).blockY+rY, null);
            //}
           
           
            
            //bufferGraphics.fillRect(entities.get(x).bounds.x, entities.get(x).bounds.y, entities.get(x).bounds.width, entities.get(x).bounds.height);
        }
		
		
		
		bufferGraphics.setFont(new Font("Arial",Font.PLAIN,20));
		//String m1 = new Boolean(player.canMove('w')).toString();
		bufferGraphics.drawString(message, 0, 170);
		//bufferGraphics.fillRect(r2.x, r2.y, r2.width, r2.height);
		//player.collideOn(player.r, r2);
		//***Image log1 = new ImageIcon(getClass().getResource("logTest.png")).getImage();
		//***bufferGraphics.drawImage(log1, (int)r2.getX(), (int)r2.getY(), null);
		//bufferGraphics.drawLine((int)player.N.getX1(), (int)player.N.getY1(), (int)player.N.getX2(), (int)player.N.getY2());
		//bufferGraphics.drawLine((int)player.E.getX1(), (int)player.E.getY1(), (int)player.E.getX2(), (int)player.E.getY2());
		//bufferGraphics.drawLine((int)player.S.getX1(), (int)player.S.getY1(), (int)player.S.getX2(), (int)player.S.getY2());
		//bufferGraphics.drawLine((int)player.W.getX1(), (int)player.W.getY1(), (int)player.W.getX2(), (int)player.W.getY2());
		//bufferGraphics.setColor(Color.RED);
		//bufferGraphics.drawLine((int)player.NI.getX1(), (int)player.NI.getY1(), (int)player.NI.getX2(), (int)player.NI.getY2());
		//bufferGraphics.drawLine((int)player.EI.getX1(), (int)player.EI.getY1(), (int)player.EI.getX2(), (int)player.EI.getY2());
		//bufferGraphics.drawLine((int)player.SI.getX1(), (int)player.SI.getY1(), (int)player.SI.getX2(), (int)player.SI.getY2());
		//bufferGraphics.drawLine((int)player.WI.getX1(), (int)player.WI.getY1(), (int)player.WI.getX2(), (int)player.WI.getY2());
		if(notInBuilding){
		Image thebg = new ImageIcon(getClass().getResource("grassbackingdev.png")).getImage();
		bufferGraphics.drawImage(thebg,0+rX,0+rY,null);
		}
		
		player.update();
		int setX=Math.abs(player.relativeX);
		int setY=Math.abs(player.relativeY);
		if(player.relativeX>0){
			setX=-setX;
		}
		if(player.relativeY>0){
			setY=-setY;
		}
		
		if(notInBuilding){
			for (int x =0;x<entities.size();x++) {
				entities.get(x).update(rX, rY);
				bufferGraphics.drawImage(entities.get(x).image, entities.get(x).blockX+rX, entities.get(x).blockY+rY, null);
				player.collideOn(player.r, entities.get(x));
				if(entities.get(x).health<=0){
					if(entities.get(x).material=="Wood")
						wood+=entities.get(x).matsReturn;
					if(entities.get(x).material=="Stone")
						stone+=entities.get(x).matsReturn;
					entities.remove(x);
				}
				//bufferGraphics.fillRect(entities.get(x).bounds.x, entities.get(x).bounds.y, entities.get(x).bounds.width, entities.get(x).bounds.height);
			}
		}
		for (int x =0;x<buildings.size();x++) {
			if(notInBuilding){
            	bufferGraphics.drawImage(buildings.get(x).image, buildings.get(x).blockX+rX,buildings.get(x).blockY+rY, null);
            }else{
            	bufferGraphics.drawImage(buildings.get(x).innerImage, buildings.get(x).blockX+rX,buildings.get(x).blockY+rY, null);
            }
		}
		if(notInBuilding){
			for (int x =0;x<npcArray.size();x++) {
				
				for(int e=0;e<entities.size();e++){
					npcArray.get(x).detectEntities(entities.get(e));
					if(entities.get(e).health<=0){
						entities.remove(e);
					}
				}
				npcArray.get(x).update(rX, rY,setX+92,setY+82);
				bufferGraphics.drawImage(npcArray.get(x).graphic, npcArray.get(x).npcX+rX, npcArray.get(x).npcY+rY, null);
				if(npcArray.get(x).isDead()){
					npcArray.remove(x);
				}else if(npcArray.get(x).hitBox.intersects(player.r)){
					player.health-=25;
					npcArray.remove(x);
				}
			}
		}
		for (int x =0;x<playerAttack.size();x++) {
			playerAttack.get(x).update(rX, rY);
			bufferGraphics.drawImage(playerAttack.get(x).graphic, playerAttack.get(x).currentX+rX, playerAttack.get(x).currentY+rY, null);
			for (int y =0;y<npcArray.size();y++) {
				if(playerAttack.get(x).hitBox.intersects(npcArray.get(y).hitBox)){
					npcArray.get(y).health-=25;
					playerAttack.get(x).hasHit=true;
				}
			}
			if(playerAttack.get(x).isDead()){
				playerAttack.remove(x);
			}
		}
		
		
		player.notOnDoor=true;
		if(player.moving){
			bufferGraphics.drawImage(player.getImage().getImage(), player.getCharx(),player.getChary(), null);//TODO
		}else{
			bufferGraphics.drawImage(player.getIdle(), player.getCharx(),player.getChary(), null);
		}
		if(Ghosting){
			if(player.face==1){
				bufferGraphics.drawImage(player.getResource(resource),104,87,null);//EAST
			}
			if(player.face==2){
				bufferGraphics.drawImage(player.getResource(resource),94,106,null);//SOUTH*94,106
			}
			if(player.face==3){
				bufferGraphics.drawImage(player.getResource(resource),84,87,null);//WEST84,87
			}
			if(player.face==4){
				bufferGraphics.drawImage(player.getResource(resource),94,75,null);//NORTH94,75
			}
		}
		if(player.health<=0){
			bufferGraphics.drawString("You are dead", 10, 90);
			stop();
		}
		bufferGraphics.setFont(new Font("Arial",Font.PLAIN,9));
		bufferGraphics.setColor(new Color(160,82,45));
		bufferGraphics.fillRect(0, 2, 8, 8);
		bufferGraphics.setColor(new Color(105,105,105));
		bufferGraphics.fillRect(0, 12, 8, 8);
		bufferGraphics.drawImage(statusBar,0,22,null);
		bufferGraphics.setColor(Color.WHITE);
		bufferGraphics.drawString(String.format("%d", wood), 18, 10);
		bufferGraphics.drawString(String.format("%d", stone), 18, 20);
		bufferGraphics.drawString(String.format("%d", player.health), 18, 30);
		//bufferGraphics.fillRect((int)player.r.getX(), (int)player.r.getY(), (int)player.r.getWidth(),(int)player.r.getHeight());
		//bufferGraphics.fillRect(player.r.x, player.r.x, player.r.width, player.r.height);
		g.drawImage(offScreen, 0, 0, this);
		//if(drawConsole){
			//message = String.format("wood: %d", wood);
		//}


	}
	public void update(Graphics g) {
		
		paint(g);

	}

	public void loadPics(){
		ghostImage = new ImageIcon(getClass().getResource("Ghosting.png")).getImage();
		//Image button2Icon = new ImageIcon(getClass().getResource("ani1.png")).getImage();
		Image aniIdleS = new ImageIcon(getClass().getResource("TimS3.png")).getImage();
		Image aniIdleN = new ImageIcon(getClass().getResource("TimN5.png")).getImage();
		Image aniIdleE = new ImageIcon(getClass().getResource("TimE4.png")).getImage();
		Image aniIdleW = new ImageIcon(getClass().getResource("TimW3.png")).getImage();
		idleArray[1] = aniIdleE;
		idleArray[2] = aniIdleS;
		idleArray[3] = aniIdleW;
		idleArray[4] = aniIdleN;
		Image anie1 = new ImageIcon(getClass().getResource("TimE1.png")).getImage();
		Image anie2 = new ImageIcon(getClass().getResource("TimE2.png")).getImage();
		Image anie3 = new ImageIcon(getClass().getResource("TimE3.png")).getImage();
		Image anie4 = new ImageIcon(getClass().getResource("TimE4.png")).getImage();
		Image anie5 = new ImageIcon(getClass().getResource("TimE5.png")).getImage();
		Image anie6 = new ImageIcon(getClass().getResource("TimE6.png")).getImage();
		aniObject = new myAnimation();
		aniObject.addScene(anie1, 250);//250 millisecond play time
		aniObject.addScene(anie2, 250);//250 millisecond play time
		aniObject.addScene(anie3, 250);//250 millisecond play time
		aniObject.addScene(anie4, 250);//250 millisecond play time
		aniObject.addScene(anie5, 250);//250 millisecond play time
		aniObject.addScene(anie6, 250);//250 millisecond play time
		aniArray[1] = aniObject;
		Image anis1 = new ImageIcon(getClass().getResource("TimS1.png")).getImage();
		Image anis2 = new ImageIcon(getClass().getResource("TimS2.png")).getImage();
		Image anis3 = new ImageIcon(getClass().getResource("TimS3.png")).getImage();
		Image anis4 = new ImageIcon(getClass().getResource("TimS4.png")).getImage();
		Image anis5 = new ImageIcon(getClass().getResource("TimS5.png")).getImage();
		Image anis6 = new ImageIcon(getClass().getResource("TimS6.png")).getImage();
		Image anis7 = new ImageIcon(getClass().getResource("TimS7.png")).getImage();
		Image anis8 = new ImageIcon(getClass().getResource("TimS8.png")).getImage();
		aniObjectS = new myAnimation();
		aniObjectS.addScene(anis1, 250);//250 millisecond play time
		aniObjectS.addScene(anis2, 250);//250 millisecond play time
		aniObjectS.addScene(anis3, 250);//250 millisecond play time
		aniObjectS.addScene(anis4, 250);//250 millisecond play time
		aniObjectS.addScene(anis5, 250);//250 millisecond play time
		aniObjectS.addScene(anis6, 250);//250 millisecond play time
		aniObjectS.addScene(anis7, 250);//250 millisecond play time
		aniObjectS.addScene(anis8, 250);
		aniArray[2] = aniObjectS;
		Image aniw1 = new ImageIcon(getClass().getResource("TimW1.png")).getImage();
		Image aniw2 = new ImageIcon(getClass().getResource("TimW2.png")).getImage();
		Image aniw3 = new ImageIcon(getClass().getResource("TimW3.png")).getImage();
		Image aniw4 = new ImageIcon(getClass().getResource("TimW4.png")).getImage();
		Image aniw5 = new ImageIcon(getClass().getResource("TimW5.png")).getImage();
		Image aniw6 = new ImageIcon(getClass().getResource("TimW6.png")).getImage();
		aniObjectW = new myAnimation();
		aniObjectW.addScene(aniw1, 250);//250 millisecond play time
		aniObjectW.addScene(aniw2, 250);//250 millisecond play time
		aniObjectW.addScene(aniw3, 250);//250 millisecond play time
		aniObjectW.addScene(aniw4, 250);//250 millisecond play time
		aniObjectW.addScene(aniw5, 250);//250 millisecond play time
		aniObjectW.addScene(aniw6, 250);//250 millisecond play time
		aniArray[3] = aniObjectW;
		Image anin1 = new ImageIcon(getClass().getResource("TimN1.png")).getImage();
		Image anin2 = new ImageIcon(getClass().getResource("TimN2.png")).getImage();
		Image anin3 = new ImageIcon(getClass().getResource("TimN3.png")).getImage();
		Image anin4 = new ImageIcon(getClass().getResource("TimN4.png")).getImage();
		Image anin5 = new ImageIcon(getClass().getResource("TimN5.png")).getImage();
		Image anin6 = new ImageIcon(getClass().getResource("TimN6.png")).getImage();
		Image anin7 = new ImageIcon(getClass().getResource("TimN7.png")).getImage();
		Image anin8 = new ImageIcon(getClass().getResource("TimN8.png")).getImage();
		aniObjectN= new myAnimation();
		aniObjectN.addScene(anin1, 250);//250 millisecond play time
		aniObjectN.addScene(anin2, 250);//250 millisecond play time
		aniObjectN.addScene(anin3, 250);//250 millisecond play time
		aniObjectN.addScene(anin4, 250);//250 millisecond play time
		aniObjectN.addScene(anin5, 250);//250 millisecond play time
		aniObjectN.addScene(anin6, 250);//250 millisecond play time
		aniObjectN.addScene(anin7, 250);//250 millisecond play time
		aniObjectN.addScene(anin8, 250);//250 millisecond play time
		aniArray[4] = aniObjectN;
		penguinAnimation[0]=new ImageIcon(getClass().getResource("PenguinS.png")).getImage();
		penguinAnimation[1]=new ImageIcon(getClass().getResource("PenguinS2.png")).getImage();
		penguinAnimation[2]=new ImageIcon(getClass().getResource("PenguinS.png")).getImage();
		penguinAnimation[3]=new ImageIcon(getClass().getResource("PenguinS3.png")).getImage();
		player.addAnimation(aniArray);
		player.addIdle(idleArray);
	}
	
	public void loadEntities(){
		entities = new ArrayList();
		buildings = new ArrayList();
		npcArray = new ArrayList();
		entities.add(new resourceBlock(200,200,1));
		entities.add(new resourceBlock(100,200,1));
		entities.add(new resourceBlock(150,200,1));
		entities.add(new resourceBlock(200,100,1));
		entities.add(new resourceBlock(200,150,1));
		entities.add(new resourceBlock(125,100,1));
		entities.add(new resourceBlock(150,150,1));
		entities.add(new resourceBlock(107,200,1));
		entities.add(new resourceBlock(5,30,2));
		entities.add(new resourceBlock(45,75,2));
		entities.add(new resourceBlock(8,50,1));
		buildings.add(new Buildings(300,36,0));
		npcArray.add(new Penguins(300,300,penguinAnimation));
		npcArray.add(new Penguins(200,300,penguinAnimation));
		npcArray.add(new Penguins(200,200,penguinAnimation));
		npcArray.add(new Penguins(250,300,penguinAnimation));
		npcArray.add(new Penguins(250,250,penguinAnimation));
		//buildings.add(new Buildings(0,0,0));
	}
	
	public int getResource(int Type){
		if(Type==0)
			return wood;
		if(Type==1)
			return stone;
		else
			return wood;
	}
	public void takeResource(int Type){
		if(Type==0)
			wood-=1;
		if(Type==1)
			stone-=1;
		else
			wood-=1;
	}
	
	public class userInput implements KeyListener, MouseMotionListener,MouseListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int index  = e.getKeyCode();

			switch (index){
				case KeyEvent.VK_ESCAPE:
					stop();
					break;
				case KeyEvent.VK_TAB:
					stop();
					break;
				case KeyEvent.VK_W:
					player.moving=true;
					player.moveN();
					break;
				case KeyEvent.VK_A:
					player.moving=true;
					player.moveW();
					break;
				case KeyEvent.VK_S:
					player.moving=true;
					player.moveS();
					break;
				case KeyEvent.VK_D:
					player.moving=true;
					player.moveE();
					break;
				case KeyEvent.VK_E:
					player.hasTool=true;
					break;
				case KeyEvent.VK_Q:
					if(getResource(resource)>0){
						int setX=Math.abs(player.relativeX);
						int setY=Math.abs(player.relativeY);
						if(player.relativeX>0){
							setX=-setX;
						}
						if(player.relativeY>0){
							setY=-setY;
						}
						if(resource<=1){
							if(player.face==1){
								entities.add(new basicBlock(104+setX,87+setY,resource,'e'));//EAST
							}
							if(player.face==2){
								entities.add(new basicBlock(94+setX,106+setY,resource,'s'));//SOUTH*
							}
							if(player.face==3){
								entities.add(new basicBlock(84+setX,87+setY,resource,'w'));//WEST
							}
							if(player.face==4){
								entities.add(new basicBlock(94+setX,75+setY,resource,'n'));//NORTH
							}
						}else{
							if(player.face==1){
								entities.add(new attackBlock(104+setX,87+setY,resource));//EAST
							}
							if(player.face==2){
								entities.add(new attackBlock(94+setX,106+setY,resource));//SOUTH*
							}
							if(player.face==3){
								entities.add(new attackBlock(84+setX,87+setY,resource));//WEST
							}
							if(player.face==4){
								entities.add(new attackBlock(94+setX,75+setY,resource));//NORTH
							}
						}
						//if(drawConsole){
							//message = "Block added";
						//}
						takeResource(resource);
					}
					break;
				case KeyEvent.VK_R:
					if(Ghosting==false){
						Ghosting=true;
					}else{
						Ghosting=false;
					}
					break;
				case KeyEvent.VK_BACK_QUOTE:
					if(drawConsole){
						drawConsole=false;
					}else{
						drawConsole=true;
					}
					break;
				case KeyEvent.VK_1:
					resource=0;
					break;
				case KeyEvent.VK_2:
					resource=1;
					break;
				case KeyEvent.VK_3:
					resource=2;
					break;
				case KeyEvent.VK_SPACE:
					
					int setX=Math.abs(player.relativeX);
					int setY=Math.abs(player.relativeY);
					if(player.relativeX>0){
						setX=-setX;
					}
					if(player.relativeY>0){
						setY=-setY;
					}
					playerAttack.add(new Missiles(98+setX,85+setY,0,player.face));
					break;
				case KeyEvent.VK_ENTER:
					int i = randometer.nextInt(101);
					int i2 = randometer.nextInt(101);
					npcArray.add(new Penguins(250+i,250+i2,penguinAnimation));
					break;
				default:
					//String.format("pressed %s", KeyEvent.getKeyText(index));
			}
		//message = String.format("pressed %s", KeyEvent.getKeyText(index));
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {

			int index  = e.getKeyCode();
			player.stopMoving(index);
			player.isMoving();
			e.consume();
			player.hasTool=false;
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			int index  = e.getKeyCode();

			switch (index){
			
			}
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			//if(drawConsole==false){
				//message = "W A S D";
			//}
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	
}

/*Iterator :--- Iterator takes the place of Enumeration in the Java collections framework. One can traverse through the collection with the
 *help of iterator in forward direction only and Iterators allow the caller to remove elements from the underlying
 *collection during the iteration with well-defined semantics

ListIterator:--An iterator for lists that allows one to traverse the list in either direction.modify the list during iteration,
 and obtain the iterator's current position in the list. A ListIterator has no current element. its cursor position always lies
 between the element that would be returned by a call to previous() and the element that would be returned by a call to next().
 In a list of length n, there are n+1 valid index values, from 0 to n, inclusive. 
*/
