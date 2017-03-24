import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.lang.annotation.Native;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class Test {

	public static void main(String args[]){
		//creating GUI
		JFrame f= new JFrame();
		f.setLocation(100,100);
		f.setSize(1000,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		//using canvas class to display
		Canvas c=new Canvas();
		c.setBackground(Color.black);
		JPanel p=new JPanel();
		p.setLayout(new BorderLayout());
		//video take all jpanle surface
		p.add(c);
		f.add(p);
		//read vlcj and its native libraraies
		//************load native libraraies of vlc from where vlc is installed
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"C:/Program Files/VideoLAN/VLC");
		com.sun.jna.Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		//intionalize media player
		
	MediaPlayerFactory mpf= new MediaPlayerFactory();
	//control user actions
	EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(f)); 
	emp.setVideoSurface(mpf.newVideoSurface(c));
	//for full screen
	//emp.toggleFullScreen();
	//hide the cursor
	emp.setEnableMouseInputHandling(false);
	//disable the keyboard
	emp.setEnableKeyInputHandling(false);
	
	String file ="Learn.avi";
	//preparing file to read
	emp.prepareMedia(file);
	//read the file
	emp.play();
	
		
		
		
		
		
		
		
	}
}
