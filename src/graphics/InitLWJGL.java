package graphics;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;
import de.lessvoid.nifty.renderer.lwjgl.input.LwjglInputSystem;
import graphics.gui.InitNifty;
import java.awt.Font;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import de.lessvoid.nifty.Nifty;

/**
 * The class "InitLWJGL" initializes lwjgl. It builds and manipulates all the
 * needed OpenGL context.
 * 
 * @author void
 */
public class InitLWJGL {
	// ==========================================================================
	// === Private Fields
	// ==========================================================================

	/** The screen width. */
	private static final int SCREEN_WIDTH = 1024;
	/** The screen height. */
	private static final int SCREEN_HEIGHT = 768;
	/** The LwjglInputSystem. */
	private static LwjglInputSystem inputSystem;

	/** The InitLWJGL instance. */
	private static InitLWJGL instance;

	/** Holds all asynchron OGL Tasks in need of the context. */
	private final List<Runnable> glQueue = Collections
			.synchronizedList(new LinkedList<Runnable>());

	/** TODO */
	private static final int MAX_ZOOM = 10;
	/** TODO */
	private static int currentZoom = 0;

	private static UnicodeFont fontTitle;
	private static UnicodeFont fontContent;

	private static boolean showNews;
	private static boolean showStud;
	private static boolean info;

	public static String content = "";
	public static String title = "";

	// ==========================================================================
	// === Methods & Constructor
	// ==========================================================================

	public static String getContent() {
		return content;
	}

	public void setContent(String content) {
		InitLWJGL.content = content;
	}

	public static String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		InitLWJGL.title = title;
	}
	
	public static void setNewsTxtOn (){
		showNews = true;
	}
	
	public static void setNewsTxtOff (){
		showNews = false;
	}
	
	public static void setStudTxtOn (){
		showStud = true;
	}
	
	public static void setStudTxtOff (){
		showStud = false;
	}

	public static void setInfoTxtOn (){
		info = true;
	}
	
	public static void setInfoTxtOff (){
		info = false;
	}
	
	/**
	 * The hidden constructor of the singleton.
	 */
	private InitLWJGL() {
		try {
			Display.setDisplayMode(new DisplayMode(1024, 768));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		Font awtFontTitle = new Font("Arial", Font.BOLD, 16);
		Font awtFontContent = new Font("Arial", Font.PLAIN, 12);
		fontTitle = new UnicodeFont(awtFontTitle);
		fontTitle.getEffects().add(new ColorEffect(java.awt.Color.white));
		fontTitle.addAsciiGlyphs();
		fontContent = new UnicodeFont(awtFontContent);
		fontContent.getEffects().add(new ColorEffect(java.awt.Color.white));
		fontContent.addAsciiGlyphs();
		try {
			fontTitle.loadGlyphs();
			fontContent.loadGlyphs();
		} catch (SlickException ex) {

		}

		initOGL();
		initInput();
	}

	/**
	 * Static method to retrieve the one and only reference to the InitLWJGL.
	 * 
	 * @return the reference of the InitLWJGL.
	 */
	public static InitLWJGL getInstance() {
		if (instance != null) {
			return instance;
		}
		return instance = new InitLWJGL();
	}

	/**
	 * The LWJGL inputSystem.
	 */
	private static void initInput() {
		try {
			inputSystem = new LwjglInputSystem();
			inputSystem.startup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the OpenGL context.
	 */
	public static void initOGL() {
		glViewport(0, 0, Display.getDisplayMode().getWidth(), Display
				.getDisplayMode().getHeight());

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClear(GL_COLOR_BUFFER_BIT);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display
				.getDisplayMode().getHeight(), 0, -9999, 9999);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_CULL_FACE);

		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glAlphaFunc(GL11.GL_NOTEQUAL, 0);

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

	}

	/**
	 * Gets the LWJGL inputSystem.
	 * 
	 * @return inputSystem
	 */
	public static LwjglInputSystem getInputSystem() {
		return inputSystem;
	}

	/**
	 * Destroys EVERYTHING for the glory of Satan!
	 */
	public static void destroy() {
		inputSystem.shutdown();
		Display.destroy();
		System.exit(0);
	}

	/**
	 * The renderloop.
	 * 
	 * @param nifty
	 *            The nifty-gui instance.
	 */
	public void renderLoop(final Nifty nifty) {
		boolean done = false;
		while (!Display.isCloseRequested() && !done) {
			Display.update();
			processGLQueue();

			if (nifty.update()) {
				done = true;
			}

			addToGLQueue(new Runnable() {
				public void run() {
					nifty.render(true);
				}
			});

			if (showNews) {

				drawS(fontTitle, 340, 130, title);
				drawS(fontContent, 340, 130, content);

			}
			
			if (showStud) {

				drawS(fontTitle, 260, 230, title);
				drawS(fontContent, 260, 250, content);

			}
			
			if (info) {

				drawS(fontTitle, 400, 230, title);
				drawS(fontContent, 400, 250, content);

			}

			int error = GL11.glGetError();
			if (error != GL11.GL_NO_ERROR) {
				String glerrmsg = GLU.gluErrorString(error);
				System.out.println("OpenGL Error: (" + error + ") " + glerrmsg);
			}
		}
	}

	// ==========================================================================
	// === Async OGL Tasks
	// ==========================================================================

	/**
	 * Adds a asynchron Runnable task to the glQueue.
	 * 
	 * @param glTask
	 *            The task.
	 */
	public void addToGLQueue(Runnable glTask) {
		glQueue.add(glTask);
	}

	/**
	 * Iterates through glQueue and runs every task.
	 */
	private void processGLQueue() {
		synchronized (glQueue) {
			while (glQueue.isEmpty() == false) {
				glQueue.remove(0).run();
			}
		}
	}

	/**
	 * Rotates the display content in dependence to an angle.
	 * 
	 * @param angle
	 *            The angle.
	 */
	public void rotateContent(final double angle) {
		addToGLQueue(new Runnable() {
			public void run() {
				GL11.glTranslatef(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
				GL11.glRotated(angle, 0.0f, 0.0f, -1.0f);
				GL11.glTranslatef(-SCREEN_WIDTH / 2, -SCREEN_HEIGHT / 2, 0);
			}
		});
	}

	private void drawS(UnicodeFont font, float x, float y, String text) {
		if (info){
			font.drawString(x, y, text, Color.black);
		}
		else {
			font.drawString(x, y, text, Color.white);
		}
		
	}

	/** TODO */
	public void gotoScreen(final String id) {
		addToGLQueue(new Runnable() {
			public void run() {
				InitNifty.getNifty().gotoScreen(id);
			}
		});
	}

	/**
	 * Scales the display content in dependence to an factor.
	 * 
	 * @param scale
	 *            The factor.
	 */
	public void scaleContent(final double xPos, final double yPos,
			final boolean scale) {
		addToGLQueue(new Runnable() {
			public void run() {
				if (scale) {
					if (currentZoom < MAX_ZOOM) {
						GL11.glTranslated(xPos, yPos, 0);
						GL11.glScaled(1 / 0.9, 1 / 0.9, 1);
						GL11.glTranslated(-xPos, -yPos, 0);

						currentZoom += 1;
					}

				}
				if (!scale) {
					if (currentZoom > 0) {
						GL11.glTranslated(xPos, yPos, 0);
						GL11.glScaled(1 * 0.9, 1 * 0.9, 1);
						GL11.glTranslated(-xPos, -yPos, 0);

						currentZoom -= 1;
					}
				}
				System.out.println("x=" + xPos + "\n" + "y=" + yPos);
			}
		});
	}

	/** TODO */
	public void resetView(final double xPos, final double yPos) {
		addToGLQueue(new Runnable() {
			public void run() {
				while (currentZoom > 0) {
					GL11.glTranslated(xPos, yPos, 0);
					GL11.glScaled(1 * 0.9, 1 * 0.9, 1);
					GL11.glTranslated(-xPos, -yPos, 0);

					currentZoom -= 1;
				}
			}
		});

	}

}
