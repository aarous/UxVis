package content.studi;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.*;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.SizeValue;
import graphics.*;
import graphics.gui.InitScreen;

public class TexteInhalt {
	// ==========================================================================
	// === Private Fields.
	// ==========================================================================

	/** The nifty-gui instance. */
	private static Nifty nifty;
	String neuerText ="Ich hoffe es klappt";
	
	
	public static Nifty getNifty() {
		return nifty;
	}
	
	public static void updateTextLabel(String text, String id) {
		Element element = getNifty().getCurrentScreen().findElementByName(id);
		element.getRenderer(TextRenderer.class).setText(text);
	}
}
