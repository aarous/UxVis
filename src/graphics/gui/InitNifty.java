package graphics.gui;

import graphics.*;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.renderer.lwjgl.render.LwjglRenderDevice;
import de.lessvoid.nifty.sound.openal.OpenALSoundDevice;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;

/**
 * The class "InitNifty" creates the LWJGL instance and initializes and binds
 * all nifty-gui context.
 * 
 * @author void
 */
public class InitNifty {
	// ==========================================================================
	// === Private Fields
	// ==========================================================================

	/** The nifty-gui instance. */
	private static Nifty nifty;

	/** The InitNifty instance. */
	private static InitNifty instance;

	/** The InitScreen instance. */
	private static InitScreen startScreen;

	// ==========================================================================
	// === Methods & Constructor
	// ==========================================================================

	/**
	 * The hidden constructor of the singleton.
	 */
	private InitNifty() {
		InitLWJGL lwjgl = InitLWJGL.getInstance();

		nifty = new Nifty(new LwjglRenderDevice(), new OpenALSoundDevice(),
				InitLWJGL.getInputSystem(), new AccurateTimeProvider());

		startScreen = InitScreen.getInstance();
		nifty.registerScreenController(startScreen);
		nifty.fromXml("res/saver.xml", "saver");
		nifty.addXml("res/start.xml");
		nifty.addXml("res/studi.xml");
		nifty.addXml("res/gaenge.xml");
		//nifty.addXml("res/map.xml");

		lwjgl.renderLoop(nifty);
		InitLWJGL.destroy();
	}

	/**
	 * Static method to retrieve the one and only reference to the InitNifty.
	 * 
	 * @return the reference of the InitNifty.
	 */
	public static InitNifty getInstance() {
		if (instance != null) {
			return instance;
		}
		return instance = new InitNifty();
	}

	/** TODO */
	public static Nifty getNifty() {
		return nifty;
	}

}
