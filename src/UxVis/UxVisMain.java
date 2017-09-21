package UxVis;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import rssprocessor.RSSReader;

import content.rss.ReadHSHLRSS;

import de.lessvoid.nifty.Nifty;

import eventsystem.React;
import TUIO.*;
import graphics.InitLWJGL;
import graphics.gui.InitNifty;
import graphics.gui.ShowSplash;
import graphics.gui.UxVisSplash;

/**
 * Main class of UxVis.
 * 
 * Initializes the TUIO, LWJGL and nifty-gui context.
 * 
 * @author void
 * @version 0.1
 */
public class UxVisMain {
	
	final static ShowSplash splash = new ShowSplash();

	// ==========================================================================
	// === Methods & Constructor
	// ==========================================================================
	/**
	 * Main invocation method.
	 * 
	 * @param args
	 *            The arguments.
	 */
	public void load(){
		
		Thread l = new Thread(){
			public void run(){
				React listener = new React();
				TuioClient client = new TuioClient();
				client.addTuioListener(listener);
				client.connect();
				System.out.println("Client Connected: " + client.isConnected());
		
				Logger.getLogger("").setLevel(Level.WARNING);
				
				InitNifty.getInstance();
				//ReadHSHLRSS feed = new ReadHSHLRSS();
				
				System.out.println("KLAPPR");
			}
		};
		l.start();
		splash.splashOn();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){  
		      public void run(){  
		    	new UxVisMain().load();
		      }  
		    });
		
		splash.splashOff();
	}
}
