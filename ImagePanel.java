/*Matt Fishman
 * Partner Matt Cucuzza
 * this class handles the images and buttons for the Java Applet
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class ImagePanel extends JPanel {
	
	//instance variables
	private ZoomLabel pictureLabel; 
	private JButton play,pause,forward,previous,zoomIn,zoomOut;
	final ArrayList<ImageIcon> pictureArray = new ArrayList<ImageIcon>();
	private int counter = 0;
	private int DELAY = 1000;
	private boolean stopped = true, paused = false; 
	private double scale = 1.0;
	
	//constructor
	public ImagePanel(){
		super();
		
		setLayout(new BorderLayout());
		
		//reads in the pictures and puts them in an array
		for(int i = 1; i <=7; i++){
			ImageIcon picture = createImageIcon("Pic" + i + ".jpg", "picture" + "i");
			pictureArray.add(picture);
		}
		//sets the default picture
		ZoomLabel pictureLabel = new ZoomLabel(pictureArray.get(0).getImage());
		this.add(pictureLabel);
	
		//makes the grid and buttonPanel
		JPanel buttonPanel = new JPanel();
		GridLayout buttonGrid = new GridLayout(3,3);
		buttonGrid.setVgap(5);
		buttonGrid.setHgap(5);
		buttonPanel.setLayout(buttonGrid);
		
		//makes the buttons
		play = new JButton("Play");
		pause = new JButton("Pause");
		pause.setEnabled(false);
		forward = new JButton("Forward");
		previous = new JButton("Previous");
		zoomIn = new JButton("Zoom In");
		zoomOut = new JButton("Zoom Out");
		
		//adds the buttons
		buttonPanel.add(play, SwingConstants.CENTER);
		buttonPanel.add(pause, SwingConstants.CENTER);
		buttonPanel.add(forward, SwingConstants.CENTER);
		buttonPanel.add(previous, SwingConstants.CENTER);
		buttonPanel.add(zoomIn, SwingConstants.CENTER);
		buttonPanel.add(zoomOut, SwingConstants.CENTER);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
	
		//time that will set the photo to the next when activated
		Timer t = new Timer(DELAY, new ActionListener(){
			public void actionPerformed(ActionEvent event){
				counter++;
				if(counter > 6){
					counter = 0;
				}
				pictureLabel.setImage(pictureArray.get(counter).getImage());
				repaint();
			}
		});
		
		//plays through the slideshow and stops the slideshow
		play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(stopped == true){
					t.start();
					play.setText("Stop");
					stopped = false;
					pause.setEnabled(true);
					paused = false;
				}
				else{
					t.stop();
					play.setText("Play");
					stopped = true;
					pause.setEnabled(false);
					pause.setText("Pause");
				}
				scale = 1.0;
			}
		});
		
		//pauses and resumes the slideshow
		pause.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(paused == true){
					t.start();
					pause.setText("Resume");
					paused = false;
				}
				else{
					t.stop();
					pause.setText("Pause");
					paused = true;
				}
				scale = 1.0;
			}
		});
		
		//changes the photo to the next one in the array
		forward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				t.stop();
				counter++;
				if(counter > 6){
					counter = 0;
				}
				pictureLabel.setImage(pictureArray.get(counter).getImage());
				scale = 1.0;
				repaint();
			}
		});
		
		//changes the photo to the previous one in the array
		previous.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				t.stop();
				counter--;
				if(counter < 0){
					counter = 6;
				}
				pictureLabel.setImage(pictureArray.get(counter).getImage());
				scale = 1.0;
				repaint();
			}
		});
		
		zoomIn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				t.stop();
				play.setText("Play");
				stopped = true;
				pause.setEnabled(false);
				pause.setText("Pause");
				scale +=0.1;
				pictureLabel.setScale(scale);
				repaint();			
				}
		});
		
		zoomOut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				t.stop();
				play.setText("Play");
				stopped = true;
				pause.setEnabled(false);
				pause.setText("Pause");
				scale -=0.1;
				//sets the scale so it can't go past 0
				if(scale < 0){
					scale = 0.0;
				}
				pictureLabel.setScale(scale);
				repaint();
			}
		});
	
	}//end constructor
	
	

	private static ImageIcon createImageIcon(String path, String desc) {
	       java.net.URL imgURL = ImagePanel.class.getResource(path);
	       if (imgURL != null) {
	           return new ImageIcon(imgURL,desc);
	       } else {
	           System.err.println("Couldn't find file: " + path);
	           return null;
	       }
	   }
	 
}