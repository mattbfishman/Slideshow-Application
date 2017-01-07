/*Matt Fishman
 * Partner Matt Cucuzza
 * this class is the main driver for the Java Applet
 */

import java.awt.BorderLayout;

import javax.swing.JApplet;


public class PictureApplet extends JApplet{
	public void init(){
	ImagePanel panel = new ImagePanel();
	this.add(panel, BorderLayout.CENTER);
	this.setSize(300,300);
	this.setVisible(true);
	}
}
