package graphics.gui;

import java.awt.Font;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import rssprocessor.RSSReader;
import content.rss.Feed;
import content.rss.ReadHSHLRSS;
import content.studi.TexteInhalt;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.*;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.SizeValue;
import graphics.*;

/**
 * ScreenController for the UxVis start screen.
 * 
 * @author void
 */
public class InitScreen implements ScreenController {
	// ==========================================================================
	// === Private Fields.
	// ==========================================================================

	ReadHSHLRSS rss = new ReadHSHLRSS();

	/** The nifty-gui instance. */
	private static Nifty nifty;
	/** The InitScreen instance. */
	private static InitScreen instance;
	/** The InitLWJGL instance. */
	private static final InitLWJGL lwjgl = InitLWJGL.getInstance();

	/** Holds the current background image. */
	private static NiftyImage niftyBgImg;
	/** Holds the current menu image. */
	private static NiftyImage niftyMenueImg;

	private static final String BR = System.getProperty("line.separator");

	/** TODO */
	private static boolean menuIsVisible = false;
	/** TODO */
	private static boolean infoIsVisible = false;
	private static UnicodeFont fontTitle;
	private static UnicodeFont fontContent;

	// Alle Texte für VIDEO============================
	String ueberschrift = "";
	String neuerText = "";

	// String[] messagesNews = ReadHSHLRSS.getNewsFeed().getMessages();

	public void setNewsTxt1() {

		// ueberschrift = ReadHSHLRSS.getNewsFeed().;

		ueberschrift = "Gefällt mir: Die HSHL-NRW-Postkartenstars";

		neuerText = BR
				+ BR
				+ "News vom 18.06.2013"
				+ BR
				+ BR
				+ "Herzlichen Glückwunsch an Erhan Aydin Yavuz, Sarina Winkler, Michael Sudhaus und Gernot Kaiser zu den besten Postkartensprüchen, die von den Besucherinnen und Besuchern unseres facebok-Profils ausgewählt wurden! Aus insgesamt knapp 50 Einreichungen haben es die folgenden Sprüche unter die Top 5 geschafft.";

		showButNews();
		hideButPresse();
		hideButBlog();
		// updateTextLabel(neuerText, "infoText");
		lwjgl.setTitle(parseString(ueberschrift, 30));
		lwjgl.setContent(parseString(neuerText, 60));
	}

	public void setNewsTxt2() {
		ueberschrift = "Gastvortrag zum Technologiemarketing der Firma GIRA";
		neuerText = BR
				+ BR
				+ BR
				+ "News vom 18.06.2013"
				+ BR
				+ BR
				+ "Lichtschalter und Steckdosen – für diese Produkte ist die Firma GIRA Giersiepen GmbH & Co. KG aus Radevormwald im Oberbergischen Kreis weithin bekannt. Auf den ersten Blick keine Produkte mit hohem emotionalen Potential. Im Gastvortrag von Roland Seifert, Leiter Technologiemanagement bei GIRA, erfuhren die die Viertsemester im Studiengang „Technisches Management und Marketing“ heute jedoch, dass es bei dem Unternehmen längst nicht mehr nur um das simple „Licht an, Licht aus“ geht.";
		showButNews();
		hideButPresse();
		hideButBlog();
		// updateTextLabel(neuerText, "infoText");
		lwjgl.setTitle(parseString(ueberschrift, 30));
		lwjgl.setContent(parseString(neuerText, 60));
	}

	public void setPresseTxt() {
		ueberschrift = "1. und 4. Platz: Förderpreise Unternehmensverband Westfalen-Mitte gehen an HSHL-Studierende ";

		neuerText = BR
				+ BR
				+ BR
				+ "Pressemitteilung vom 13.06.2013"
				+ BR
				+ BR
				+ "Anna Günter und Tobias Lodenkemper erhielten zwei der insgesamt fünf Förderpreise, die gestern bei der diesjährigen Mitgliederversammlung des Unternehmensverbandes Westfalen-Mitte an Absolventinnen und Absolventen verliehen wurden. Die ehemalige Studentin der Mechatronik am Standort Lippstadt bekam den ersten, mit 2.500 Euro dotierten Preis für ihre hervorragende Bachelorarbeit mit besonderem Praxisbezug. Tobias Lodenkemper, Absolvent der Energietechnik und Ressourcenoptimierung am Standort Hamm, wurde mit dem vierten Förderpreis und 1.000 Euro für seine Abschlussarbeit ausgezeichnet. Mit den diesjährigen Absolventinnen und Absolventen kam die Hochschule Hamm-Lippstadt erstmalig für die Förderpreise des Verbandes in Frage.";

		showButPresse();
		hideButNews();
		hideButBlog();
		// updateTextLabel(neuerText, "infoText");
		lwjgl.setTitle(parseString(ueberschrift, 30));
		lwjgl.setContent(parseString(neuerText, 60));
	}

	public void setBlogTxt() {
		ueberschrift = "Eine Exkursion bei der Firma BHTC";
		neuerText = BR
				+ BR
				+ "Beitrag von: Bastian Höschen und Jan-Hendrik Kaiser, viertes Semester Wirtschaftsingenieurwesen am 14.06.2013 "
				+ BR
				+ BR
				+ "Nach bereits spannenden Exkursionen zu den Unternehmen Hella und Rothe Erde besuchte der Schwerpunkt: „Technischer Einkauf“ des Studiengangs Wirtschaftsingenieurwesen Anfang Mai das Unternehmen BHTC in Lippstadt. Die Abkürzung „BHTC“ steht für ein Jointventure der Firmen „Hella“ und „Behr“. Aus beiden Firmen und dem Hauptgeschäft entstand der Name Behr Hella Thermocontrol.";
		showButBlog();
		hideButNews();
		hideButPresse();
		// updateTextLabel(neuerText, "infoText");
		lwjgl.setTitle(parseString(ueberschrift, 30));
		lwjgl.setContent(parseString(neuerText, 60));
	}
	
	public void setCVDTxt1() {
		ueberschrift = "Hochauflösend, mehrdimensional und täuschend echt";
		neuerText = BR
				+ BR
				+ "Mal eben die neue Wohnungseinrichtung oder das neue Traumauto am Rechner konfigurieren oder sich mit Hilfe von Apps auf dem Mobiltelefon durchs Leben navigieren: digitale Anwendungen gehören heute zum Alltag selbstverständlich dazu. So spielerisch leicht diese auch wirken mögen, so komplex sind die mathematischen und physikalischen Hard- und Softwareprozesse im Hintergrund. Ob bei der Simulation von bewegten Objekten im Raum wie etwa bei der Entwicklung von Fahrassistenzprogrammen, bei der dreidimensionalen, realitätsnahen Darstellung von Gebäuden und Landschaften, bei der Abbildung des menschlichen Körpers durch bildgebende Verfahren wie der Magnetresonanztomographie oder beim Entwerfen von Phantasiewelten in Filmen und Spielen, neben der Technologie werden für die Visualisierung Menschen mit kreativen Fähigkeiten benötigt. Sie entwickeln aus digitalen Datensätzen Anwendungen, die auch höchsten ästhetischen Ansprüchen ihrer Kundinnen und Kunden gerecht werden."
				+ BR
				+ "Der Studiengang 'Computervisualistik und Design' verknüpft ingenieurwissenschaftliches Fachwissen, vor allem aus dem Bereich der Informatik, mit Gestaltungskompetenzen auf den Gebieten von Industrie-, Interface-, 3D- und Motiondesign. Ingenieurinnen und Ingenieure dieser Fachrichtung finden Tätigkeitsfelder in der Industrie beispielsweise in der kostengünstigen Erprobung industrieller Prototypen, in der Medizintechnik, in der Simulation von Bewegungen und Strömungsverhalten im Sport oder in der Fahrzeugtechnik, in der Postproduktion von Kino- und Fernsehfilmen sowie in der Entwicklung von Computerspielen und beim Entwerfen von Raumausstattungen, Gebäuden oder Lichtinszenierungen.";
		// updateTextLabel(neuerText, "infoText");
		lwjgl.setTitle(parseString(ueberschrift, 30));
		lwjgl.setContent(parseString(neuerText, 90));
	}
	
	public void setCVDTxt2() {
		ueberschrift = "Der Studiengang";
		neuerText = BR
				+ BR
				+ "Die Hochschule Hamm-Lippstadt bietet 'Computervisualistik und Design' als Bachelorstudiengang mit einer Regelstudienzeit von sieben Semestern und dem Abschluss 'Bachelor of Science' an."
				+ BR + BR
				+ "Studiengangsleiterin: Prof. Susanne Lengyel"
				+ BR + BR
				+ "Der Studiengang 'Computervisualistik und Design' wird an der Hochschule Hamm-Lippstadt auf dem Campus Lippstadt am Standort Lüningstraße gelehrt. Dort befinden sich auch der 3D-Showroom und das Labor für Rapid Prototyping, beides praxisnahe Einrichtungen, die regelmäßig während des Studiums zum Einsatz kommen."
				+ BR + BR
				+ "Darüber hinaus gibt es am Standort Südstraße gegenüber des CARTEC verschiedene weitere Labore, die für Forschung und Lehre genutzt werden. Hier steht zum Beispiel das Rasterelektronenmikroskop."
				+ BR + BR
				+ "Beratungsangebote für Studieninteressierte gibt es im Campus Office in der Lüningstraße.";
		// updateTextLabel(neuerText, "infoText");
		lwjgl.setTitle(parseString(ueberschrift, 30));
		lwjgl.setContent(parseString(neuerText, 60));
	}

	public String parseString(String str, int x) {
		StringBuilder sb = new StringBuilder(str);

		int i = 0;
		while ((i = sb.indexOf(" ", i + x)) != -1) {
			sb.replace(i, i + 1, System.getProperty("line.separator"));
		}

		str = sb.toString();
		return str;
	}

	// ==========================================================================
	// === Methods & Constructor
	// ==========================================================================

	/** TODO */
	private InitScreen() {
		createBgImage("res/img/hintergrund.png");
	}

	/**
	 * Static method to retrieve the one and only reference to the InitScreen.
	 * 
	 * @return the reference of the InitScreen.
	 */
	public static InitScreen getInstance() {
		if (instance != null) {
			return instance;
		}
		return instance = new InitScreen();
	}

	/**
	 * Updates an Textlabel of an nifty element.
	 * 
	 * @param text
	 *            The new text.
	 * @param id
	 *            The id of the nifty element.
	 */
	public void updateTextLabel(String text, String id) {
		Element element = getNifty().getCurrentScreen().findElementByName(id);
		element.getRenderer(TextRenderer.class).setText(text);
	}

	/**
	 * Shows/ hides the touchable Buttons in the news-screen.
	 */
	public void showButNews() {
		Element activeButton = nifty.getCurrentScreen().findElementByName(
				"selNews");
		activeButton.show();
		Element deactButton = nifty.getCurrentScreen().findElementByName(
				"butNews");
		deactButton.setVisible(false);
	}

	public void hideButNews() {
		Element activeButton = nifty.getCurrentScreen().findElementByName(
				"selNews");
		activeButton.hide();
		Element deactButton = nifty.getCurrentScreen().findElementByName(
				"butNews");
		deactButton.setVisible(true);
	}

	public void showButPresse() {
		Element activeButton = nifty.getCurrentScreen().findElementByName(
				"selPresse");
		activeButton.show();
		Element deactButton = nifty.getCurrentScreen().findElementByName(
				"butPresse");
		deactButton.setVisible(false);
	}

	public void hideButPresse() {
		Element activeButton = nifty.getCurrentScreen().findElementByName(
				"selPresse");
		activeButton.hide();
		Element deactButton = nifty.getCurrentScreen().findElementByName(
				"butPresse");
		deactButton.setVisible(true);
	}

	public void showButBlog() {
		Element activeButton = nifty.getCurrentScreen().findElementByName(
				"selBlog");
		activeButton.show();
		Element deactButton = nifty.getCurrentScreen().findElementByName(
				"butBlog");
		deactButton.setVisible(false);
	}

	public void hideButBlog() {
		Element activeButton = nifty.getCurrentScreen().findElementByName(
				"selBlog");
		activeButton.hide();
		Element deactButton = nifty.getCurrentScreen().findElementByName(
				"butBlog");
		deactButton.setVisible(true);
	}
	
	/**
	 * Switches screens in the "Studieren" area.
	 */
	
	public void goToStudieren() {
		lwjgl.setContent("");
		lwjgl.setTitle("");
		lwjgl.gotoScreen("stud");
	}
	
	public void goToFachrichtungen1() {
		lwjgl.setContent("");
		lwjgl.setTitle("");
		lwjgl.gotoScreen("fachrichtungen1");
	}
	
	public void goToFachrichtungen2() {
		lwjgl.setContent("");
		lwjgl.setTitle("");
		lwjgl.gotoScreen("fachrichtungen2");
	}
	
	public void goToFachrichtungen3() {
		lwjgl.setContent("");
		lwjgl.setTitle("");
		lwjgl.gotoScreen("fachrichtungen3");
	}
	
	public void goToCvd1() {
		lwjgl.gotoScreen("visualistik1");
		setCVDTxt1();
	}

	/**
	 * Shows the menu.
	 */
	public void menueShow() {
		if (!menuIsVisible) {
			Element menueShow = nifty.getCurrentScreen().findElementByName(
					"menuePanel");
			menueShow.show();
			menuIsVisible = true;
		}
	}

	/**
	 * Hides the menu.
	 */
	public void menuHide() {
		if (menuIsVisible) {
			Element menuHide = nifty.getCurrentScreen().findElementByName(
					"menuePanel");
			menuHide.hide();
		}
		menuIsVisible = false;
	}

	/**
	 * Shows the infopanel.
	 */
	public void infoShow() {
		if (!infoIsVisible) {
			//Feed text = ReadHSHLRSS.getNewsFeed();
			String screenID = nifty.getCurrentScreen().getScreenId();
			//System.out.println(screenID);
			if (screenID.equals("visualistik1")){
				setCVDTxt2();
			}
			InitLWJGL.setNewsTxtOff();
			InitLWJGL.setStudTxtOff();
			InitLWJGL.setInfoTxtOn();
			Element infoShow = nifty.getCurrentScreen().findElementByName(
					"infoPanel");
			infoShow.show();
			// System.out.println("Newsfeed Titel: " + text.getTitle());
			infoIsVisible = true;
		}
	}

	/**
	 * Hides the infopanel.
	 */
	public void infoHide() {
		if (infoIsVisible) {
			String screenID = nifty.getCurrentScreen().getScreenId();
			if (screenID.equals("visualistik1") ){
				setCVDTxt1();
			}
			InitLWJGL.setNewsTxtOff();
			InitLWJGL.setStudTxtOn();
			InitLWJGL.setInfoTxtOff();
			Element infoHide = nifty.getCurrentScreen().findElementByName(
					"infoPanel");
			infoHide.hide();
		}
		infoIsVisible = false;
	}

	// /**
	// * Shows the map.
	// */
	// public void mapShow() {
	// Element mapShow = nifty.getCurrentScreen().findElementByName(
	// "karte");
	// mapShow.show();
	// }
	//
	// /**
	// * Hides the map.
	// */
	// public void mapHide() {
	// Element mapHide = nifty.getCurrentScreen().findElementByName(
	// "karte");
	// mapHide.hide();
	// }

	/**
	 * Changes the background image of an nifty element in dependence of an
	 * angle.
	 * 
	 * @param angleArea
	 *            The angle.
	 */
	public void changeBackground(int angleArea) {
		Element backgroundImg = nifty.getCurrentScreen().findElementByName(
				"deko");
		backgroundImg.getRenderer(ImageRenderer.class).setImage(
				getNewBackgroundImg(angleArea));
	}

	public void changeMenueImg(int area) {
		Element menueImg = nifty.getCurrentScreen().findElementByName(
				"menuePanel");
		menueImg.getRenderer(ImageRenderer.class)
				.setImage(getNewMenueImg(area));
	}

	/**
	 * Returns an Image in dependence on the angle of the menu coin.
	 * 
	 * @param angleArea
	 *            The angle.
	 * @return The image.
	 */
	public NiftyImage getNewBackgroundImg(int angleArea) {
		System.out.println(angleArea);
		switch (angleArea) {
		case 0:
			return createBgImage("res/img/DekoNewsV2.png");
		case 1:
			return createBgImage("res/img/DekoStudierenV2.png");
		case 2:
			return createBgImage("res/img/DekoMapV2.png");
		case 3:
			return createBgImage("res/img/screensaverVG.png");
		default:
			return createBgImage("res/img/screensaverVG.png");
		}
	}

	/** TODO */
	public NiftyImage getNewMenueImg(int area) {
		switch (area) {
		case 0:
			return createMenueImage("res/img/MenueNewsV1s.png");
		case 1:
			return createMenueImage("res/img/MenueStudierenV1s.png");
		case 2:
			return createMenueImage("res/img/MenueMapV1s.png");
		case 3:
			return createMenueImage("res/img/leer.png");
		default:
			return createMenueImage("res/img/leer.png");
		}
	}

	/**
	 * Creates an NiftyImage with a path.
	 * 
	 * @param path
	 */
	public NiftyImage createBgImage(final String path) {
		lwjgl.addToGLQueue(new Runnable() {
			public void run() {
				niftyBgImg = InitNifty.getNifty().getRenderEngine()
						.createImage(path, false);
			}
		});
		return niftyBgImg;
	}

	/** TODO */
	public NiftyImage createMenueImage(final String path) {
		lwjgl.addToGLQueue(new Runnable() {
			public void run() {
				niftyMenueImg = InitNifty.getNifty().getRenderEngine()
						.createImage(path, false);
			}
		});
		return niftyMenueImg;
	}

	/** TODO */
	public void movePanel(int x, int y) {
		// nifty.getCurrentScreen().findElementByName("TestCoin")
		// .setConstraintX(new SizeValue(x + "px"));
		// nifty.getCurrentScreen().findElementByName("TestCoin")
		// .setConstraintY(new SizeValue(y + "px"));
		// nifty.getCurrentScreen().findElementByName("TestCoin").getParent()
		// .layoutElements();
	}

	/**
	 * Gets the nifty-gui instance.
	 * 
	 * @return The nifty-gui instance.
	 */
	public Nifty getNifty() {
		return nifty;
	}

	// ==========================================================================
	// === Screencontroller
	// ==========================================================================

	/*
	 * (non-Javadoc) {@link}
	 */
	@Override
	public void bind(final Nifty newNifty, final Screen newScreen) {
		InitScreen.nifty = newNifty;

	}

	/*
	 * (non-Javadoc) {@link}
	 */
	@Override
	public void onStartScreen() {
	}

	/*
	 * (non-Javadoc) {@link}
	 */
	@Override
	public void onEndScreen() {
	}
}