package eventsystem;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Enumeration;
import java.util.Hashtable;

import de.lessvoid.nifty.Nifty;
import TUIO.*;
import graphics.InitLWJGL;
import graphics.gui.InitScreen;

/**
 * The class "React" implements the TUIO listener and handles all Reactable
 * related events.
 * 
 * @author void
 */
public class React implements TuioListener {
	// ==========================================================================
	// === Private Fields
	// ==========================================================================

	/** The InitScreen instance. */
	private static InitScreen startscreen = InitScreen.getInstance();
	/** The InitLWJGL instance. */
	private static final InitLWJGL lwjgl = InitLWJGL.getInstance();

	/** The Screen width. */
	private static int SCREEN_WIDTH = 1024;
	/** The Screen height. */
	private static int SCREEN_HEIGHT = 768;
	/** The screen x-axis origin. */
	private static int SCREEN_X_ORIGIN = SCREEN_WIDTH / 2;
	/** The Screen y-axis origin. */
	private static int SCREEN_Y_ORIGIN = SCREEN_HEIGHT / 2;

	/** Holds the Cursors */
	private Hashtable<Long, TuioCursor> cursors = new Hashtable<Long, TuioCursor>();
	/** The current distance between two cursors */
	private double currentDistance;
	/** The previous distance between two cursors */
	private double previousDistance;
	/** The zoom Position */
	private boolean zoomPos = false;
	/** The difference between the x coordinates of two cursors */
	private double dx;
	/** The difference between the y coordinates of two cursors */
	private double dy;
	/** The x-axis origin of the zoom position */
	private double xPos;
	/** The y-axis origin of the zoom position */
	private double yPos;

	/** Holds the x_axis center of the menu */
	private double menuXCenter;
	/** Holds the y_axis center of the menu */
	private double menuYCenter;
	/** Holds the menu radius. */
	private double menuRadius;

	private int coinPosX;
	private int coinPosY;

	private Robot robot;

	/** Holds the x_axis center of the info point */
	private double infoXCenter;
	/** Holds the y_axis center of the info point */
	private double infoYCenter;
	/** Holds the info point radius. */
	private double infoRadius;

	/** The view angle */
	private double viewAngle;
	/** The previous view angle */
	private double previousViewAngle;

	/** TODO */
	private int mainMenuAngleArea;
	/** TODO */
	private float mainMenuStartAngle;
	/** TODO */
	private float mainMenuCurrentAngle;

	// ==========================================================================
	// === Methods & Constructor
	// ==========================================================================

	/**
	 * Constructor.
	 */
	public React() {
		createMenuContainer(512, 720, 70);
		createInfoContainer(SCREEN_X_ORIGIN, SCREEN_Y_ORIGIN, 350);

		try {
			robot = new Robot();
		} catch (AWTException ex) {
		}
	}

	/**
	 * Defines the attributes of the circle container.
	 * 
	 * @param x
	 *            x_axis center of the menu.
	 * @param y
	 *            y_axis center of the menu.
	 * @param r
	 *            radius of the menu.
	 */
	public void createMenuContainer(double x, double y, double r) {
		menuXCenter = x;
		menuYCenter = y;
		menuRadius = r;
	}

	/**
	 * Defines the attributes of the circle container.
	 * 
	 * @param x
	 *            x_axis center of the info point.
	 * @param y
	 *            y_axis center of the info point.
	 * @param r
	 *            radius of the info point.
	 */
	public void createInfoContainer(double x, double y, double r) {
		infoXCenter = x;
		infoYCenter = y;
		infoRadius = r;
	}

	/**
	 * Rotates the interface in relation to a TUIO object.
	 * 
	 * @param arg0
	 *            The TuioObject.
	 */
	public void rotateView(TuioObject arg0) {
		previousViewAngle = viewAngle;
		double x = arg0.getScreenX(SCREEN_WIDTH) - SCREEN_X_ORIGIN;
		double y = arg0.getScreenY(SCREEN_HEIGHT) - SCREEN_Y_ORIGIN;
		viewAngle = Math.atan2(x, y) / Math.PI * 180 + 360;
		double dViewAngle = viewAngle - previousViewAngle;
		lwjgl.rotateContent(dViewAngle);

		double s = Math.sin(Math.toRadians(dViewAngle));
		double c = Math.cos(Math.toRadians(dViewAngle));
		double mx = menuXCenter - SCREEN_X_ORIGIN;
		double my = menuYCenter - SCREEN_Y_ORIGIN;
		double ix = infoXCenter - SCREEN_X_ORIGIN;
		double iy = infoYCenter - SCREEN_Y_ORIGIN;
		infoXCenter = ix * c + iy * s + SCREEN_X_ORIGIN;
		infoYCenter = -ix * s + iy * c + SCREEN_Y_ORIGIN;
		menuXCenter = mx * c + my * s + SCREEN_X_ORIGIN;
		menuYCenter = -mx * s + my * c + SCREEN_Y_ORIGIN;
	}

	/**
	 * Checks if the coordinates of an Circle are within a defined circle.
	 * 
	 * @param xPosition
	 *            x_axis coordinate of the object.
	 * @param yPosition
	 *            y_axis coordinate of the object.
	 * 
	 * @param x
	 *            x_axis coordinate of the circle container.
	 * @param y
	 *            y_axis coordinate of the circle container.
	 * @param r
	 *            radius x of the circle container.
	 * 
	 * @return {@code true} The Circle is within the circle. {@code false} The
	 *         Circle is not within the circle.
	 */
	public boolean isObjInCircleContainer(double xPosition, double yPosition,
			double x, double y, double r) {
		double xCenter = x;
		double yCenter = y;
		double radius = r;

		if (Math.pow(xPosition - xCenter, 2) + Math.pow(yPosition - yCenter, 2) <= Math
				.pow(radius, 2)) {
			return true;
		} else
			return false;
	}

	/**
	 * Checks if the coordinates of an object are within the defined menu
	 * container.
	 * 
	 * @param xPosition
	 *            x_axis coordinate of the object.
	 * @param yPosition
	 *            y_axis coordinate of the object.
	 * @return {@code true} The Object is within the menu container.
	 *         {@code false} The Object is not within the menu container.
	 */
	public boolean isInBorderCircle(double xPosition, double yPosition) {
		if (isObjInCircleContainer(xPosition, yPosition, SCREEN_X_ORIGIN,
				SCREEN_Y_ORIGIN, SCREEN_Y_ORIGIN)
				&& Math.pow(xPosition - SCREEN_X_ORIGIN, 2)
						+ Math.pow(yPosition - SCREEN_Y_ORIGIN, 2) >= Math.pow(
						(SCREEN_Y_ORIGIN * 0.85), 2)) {
			return true;
		} else
			return false;
	}

	/**
	 * Pinch to Zoom
	 */
	public void pinchToZoom() {
		Enumeration<TuioCursor> curEnum = cursors.elements();
		TuioCursor c1 = curEnum.nextElement();
		TuioCursor c2 = curEnum.nextElement();

		previousDistance = currentDistance;

		if (c1.getScreenX(SCREEN_WIDTH) > c2.getScreenX(SCREEN_WIDTH)) {
			dx = c1.getScreenX(SCREEN_WIDTH) - c2.getScreenX(SCREEN_WIDTH);
		} else {
			dx = c2.getScreenX(SCREEN_WIDTH) - c1.getScreenX(SCREEN_WIDTH);
		}
		if (c1.getScreenY(SCREEN_HEIGHT) > c2.getScreenY(SCREEN_HEIGHT)) {
			dy = c1.getScreenY(SCREEN_HEIGHT) - c2.getScreenY(SCREEN_HEIGHT);
		} else {
			dy = c2.getScreenY(SCREEN_HEIGHT) - c1.getScreenY(SCREEN_HEIGHT);
		}

		if (!zoomPos) {
			xPos = (c1.getScreenX(SCREEN_WIDTH) + c2.getScreenX(SCREEN_WIDTH)) / 2;
			yPos = (c1.getScreenY(SCREEN_HEIGHT) + c2.getScreenY(SCREEN_HEIGHT)) / 2;
			zoomPos = true;
		}

		currentDistance = Math.pow(dx + dy, 2);

		if (currentDistance > previousDistance) {
			lwjgl.scaleContent(xPos, yPos, true);
			System.out.println("distance gained");

		} else if (currentDistance < previousDistance) {
			lwjgl.scaleContent(xPos, yPos, false);
			System.out.println("distance shortened");
		}
	}

	/**
	 * Select a Menu in dependence of an angle.
	 * 
	 * @param arg0
	 *            The angle.
	 */
	public void selectMenu(TuioObject arg0) {
		mainMenuCurrentAngle = arg0.getAngleDegrees();
		if (mainMenuCurrentAngle >= mainMenuStartAngle + 180
				&& mainMenuCurrentAngle < mainMenuStartAngle + 300) {
			lwjgl.setContent("");
			lwjgl.setTitle("");
			InitLWJGL.setNewsTxtOff();
			InitLWJGL.setStudTxtOn();
			lwjgl.gotoScreen("stud");
		} else if (mainMenuCurrentAngle >= mainMenuStartAngle + 60
				&& mainMenuCurrentAngle < mainMenuStartAngle + 180) {
			lwjgl.setContent("");
			lwjgl.setTitle("");
			// lwjgl.gotoScreen("map");
		} else {

			String ueberschrift = "Gefällt mir: Die HSHL-NRW-Postkartenstars";
			String BR = System.getProperty("line.separator");
			String neuerText = BR
					+ BR
					+ "News vom 18.06.2013"
					+ BR
					+ BR
					+ "Herzlichen Glückwunsch an Erhan Aydin Yavuz, Sarina Winkler, Michael Sudhaus und Gernot Kaiser zu den besten Postkartensprüchen, die von den Besucherinnen und Besuchern unseres facebok-Profils ausgewählt wurden! Aus insgesamt knapp 50 Einreichungen haben es die folgenden Sprüche unter die Top 5 geschafft.";
			InitLWJGL.setNewsTxtOn();
			InitLWJGL.setStudTxtOff();
			lwjgl.setTitle(startscreen.parseString(ueberschrift, 30));
			lwjgl.setContent(startscreen.parseString(neuerText, 60));
			lwjgl.gotoScreen("news");
		}
	}

	/** TODO */
	public boolean menucoinHasMoved(TuioObject arg0) {
		if (arg0.getScreenX(SCREEN_WIDTH) != coinPosX
				|| arg0.getScreenY(SCREEN_HEIGHT) != coinPosY) {
			coinPosX = arg0.getScreenX(SCREEN_WIDTH);
			coinPosY = arg0.getScreenY(SCREEN_HEIGHT);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc) {@link}
	 */
	@Override
	public void refresh(TuioTime arg0) {
	}

	// ==========================================================================
	// === TUIO Cursor
	// ==========================================================================

	/*
	 * (non-Javadoc) {@link}
	 */
	@Override
	public void addTuioCursor(TuioCursor arg0) {

		if (!cursors.containsKey(arg0.getSessionID())) {
			cursors.put(arg0.getSessionID(), arg0);

			robot.mouseMove(arg0.getScreenX(SCREEN_WIDTH),
					arg0.getScreenY(SCREEN_HEIGHT));
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);

		}
		fingerScroll(arg0.getScreenX(SCREEN_WIDTH),
				arg0.getScreenY(SCREEN_WIDTH));

	}

	/*
	 * (non-Javadoc) {@link}
	 */
	@Override
	public void removeTuioCursor(TuioCursor arg0) {
		System.out.println(arg0.getScreenX(SCREEN_WIDTH) + " / "
				+ arg0.getScreenY(SCREEN_HEIGHT));

		final int seconds = 2;
		final long start = System.currentTimeMillis();
		boolean count = true;
		while (count) {
			if (System.currentTimeMillis() - start >= seconds * 1000) {
				cursors.remove(arg0.getSessionID());
				count = false;
			}
		}

		if (cursors.size() != 2) {
			zoomPos = false;
			lwjgl.resetView(xPos, yPos);
		}
	}

	/*
	 * (non-Javadoc) {@link}
	 */
	@Override
	public void updateTuioCursor(TuioCursor arg0) {
		if (cursors.size() == 2) {
			pinchToZoom();
			fingerScroll(arg0.getScreenX(SCREEN_WIDTH),
					arg0.getScreenY(SCREEN_WIDTH));
		}
	}

	private void fingerScroll(int cursorX, int cursorY) {
		System.out.println("started");
		// Check if Cursor is in info panel, start panel: 358,76, size: 563*573
		if ((cursorX >= 358 && cursorX <= 921)
				&& (cursorY >= 76 && cursorY <= 649)) {
			System.out.println("ist im panel bereich");
			// code zum scrollen
		}

	}

	// ==========================================================================
	// === TUIO Object
	// ==========================================================================

	/*
	 * (non-Javadoc) {@link}
	 */
	@Override
	public void addTuioObject(TuioObject arg0) {
		int symbol = arg0.getSymbolID();
		if (symbol == 4) {
			createInfoContainer(315, 620, 40);
			selectMenu(arg0);
			coinPosX = arg0.getScreenX(SCREEN_WIDTH);
			coinPosY = arg0.getScreenY(SCREEN_HEIGHT);
			mainMenuStartAngle = arg0.getAngleDegrees();
		}
	}

	/*
	 * (non-Javadoc) {@link}
	 */
	@Override
	public void removeTuioObject(TuioObject arg0) {
		int symbol = arg0.getSymbolID();

		final int seconds = 2;
		final long start = System.currentTimeMillis();
		boolean count = true;
		while (count) {
			if (System.currentTimeMillis() - start >= seconds * 1000) {
				if (symbol == 4) {
					createInfoContainer(SCREEN_X_ORIGIN, SCREEN_Y_ORIGIN, 700);
					lwjgl.gotoScreen("saver");
				}
				count = false;
			}
		}

	}

	/*
	 * (non-Javadoc) {@link}
	 */
	@Override
	public void updateTuioObject(TuioObject arg0) {
		// startscreen.movePanel((int) menuXCenter, (int) menuYCenter);

		if (arg0.getSymbolID() == 4) {

			if (isInBorderCircle(arg0.getScreenX(SCREEN_WIDTH),
					arg0.getScreenY(SCREEN_HEIGHT))) {
				rotateView(arg0);
			}

			if (isObjInCircleContainer(arg0.getScreenX(SCREEN_WIDTH),
					arg0.getScreenY(SCREEN_HEIGHT), menuXCenter, menuYCenter,
					menuRadius)) {
				startscreen.menueShow();
				selectMenu(arg0);
			} else {
				startscreen.menuHide();
			}
		}

		if (arg0.getSymbolID() == 5) {
			if (isObjInCircleContainer(arg0.getScreenX(SCREEN_WIDTH),
					arg0.getScreenY(SCREEN_HEIGHT), infoXCenter, infoYCenter,
					infoRadius)) {
				startscreen.infoShow();
			} else {
				startscreen.infoHide();
			}
		}

		if (mainMenuAngleArea != 0) {
			startscreen.infoHide();
		}
	}
}
