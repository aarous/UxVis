package graphics.gui;


import java.awt.*;

public class UxVisSplash
extends Window
{
 private Image        m_imageSplash = null;
 private MediaTracker m_mediaTracker = null;

 public UxVisSplash(String strImagePath, Dimension dimImageSize)
 {
     super(new Frame());
     setLocation((getToolkit().getScreenSize().width - dimImageSize.width) / 2, 
                 (getToolkit().getScreenSize().height - dimImageSize.height) / 2);
     setSize(dimImageSize);

     if(strImagePath!=null && dimImageSize!=null)
     {
         m_imageSplash = getToolkit().getImage(strImagePath);
         m_mediaTracker = new MediaTracker(this);
         m_mediaTracker.addImage(m_imageSplash, 0);

         try
         {
             m_mediaTracker.waitForAll();
         }
         catch(InterruptedException e)
         {
         }
     }
 }

 public void paint(Graphics g)
 {
     g.drawImage(m_imageSplash, 0, 0, getSize().width, getSize().height, this);
 }

 public void start()
 {
     setVisible(true);
 }

 public void destroy()
 {
     setVisible(false);
     dispose();
 }
}