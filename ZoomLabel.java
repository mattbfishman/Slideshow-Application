import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/*Matt Fishman
 * Partner Matt Cucuzza
 * this class is class overrides the JLabel class to add the zoom feature
 */


public class ZoomLabel extends JLabel{
	
	//instance variables
	private Image img;
	private double scale;
	
	//constructor
	public ZoomLabel(Image img){
		this.img = new ImageIcon(img).getImage();
		scale = 1.0;
	}
	
	//setter
	public void setScale(double scale){
		this.scale = scale;
		repaint();
	}
	
	//setter
	public void setImage(Image img){
		this.img = new ImageIcon(img).getImage();
		scale = 1.0;	
		repaint();
	}
	
	//get the dimension of the scaled image
	public Dimension getPreferredSize(){
		return new Dimension((int)(img.getWidth(null)*scale),(int)(img.getHeight(null)*scale));
	}
	
	//paints the scaled image
	public void paintComponent(Graphics g){
		int w = (int)(img.getWidth(null)*scale);
		int h = (int)(img.getHeight(null)*scale);
		//padding needed to display the image in the center
		int x = (getWidth() - w) /2;
		int y = (getHeight() - h) / 2;
		g.drawImage(img,x,y,w,h,null);
	}
	
}
