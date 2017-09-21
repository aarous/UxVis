package graphics.gui;
//Testprogramm für SplashScreen-Klassen

import java.awt.Dimension;

public class ShowSplash
{
 private UxVisSplash    m_splashScreen;
 private final String    m_strImagePath = "res/img/loadO.png";
 private final Dimension m_dimImageSize = new Dimension(800, 600);

 public ShowSplash()
 {
     m_splashScreen = new UxVisSplash(m_strImagePath, m_dimImageSize);
 }
 
 
 public void splashOn() {
	 m_splashScreen.start(); 
 }
 
 
 public void splashOff(){
	 try  
     {  
       Thread.sleep(20000);  
     }  
     catch (InterruptedException e) {e.printStackTrace();}  
	 
	 m_splashScreen.destroy();
 }
}