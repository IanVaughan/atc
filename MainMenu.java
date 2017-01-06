//------------------------------------------------------------------------------
// MainMenu.java:
//		Implementation for menu creation class MainMenu
//
// WARNING: Do not modify this file.  This file is recreated each time its
//          associated .rct/.res file is sent through the Java Resource Wizard!
//
// This class is use to create a menu on a Frame object.  For addtional
// information on using Java Resource Wizard generated classes, refer to the
// Visual J++ 1.1 documention.
//
// 1) Import this class in the .java file for the Frame that will use it:
//
//      import MainMenu;
//
// 2) Create an instance of this class in your Applet's 'main' member, and call
//    CreateMenu() through this object:
//
//      MainMenu menu = new MainMenu (frame);
//      menu.CreateMenu();
//
// 3) To process events generated from user action on the menu, implement
//    the 'handleEvent' member for your Frame:
//
//      public boolean handleEvent (Event evt)
//      {
//
//      }
//
//------------------------------------------------------------------------------
import java.awt.*;

public class MainMenu
{
	Frame   m_Frame        = null;
	boolean m_fInitialized = false;

	// MenuBar definitions
	//--------------------------------------------------------------------------
	MenuBar mb;

	// Menu and Menu item definitions
	//--------------------------------------------------------------------------
	Menu m1;	// &File
	MenuItem ID_FILE_RESTART;	// &Restart
	MenuItem ID_FILE_NEW;	// &New
	MenuItem m4;	// Separator
	MenuItem ID_FILE_EXIT;	// &Exit
	Menu m6;	// Display
	MenuItem ID_VIEW_CLOCK;	// &Clock
	MenuItem ID_VIEW_FPS;	// &Flight Progress Slips
	MenuItem ID_VIEW_COMMANDDIALOG;	// Command &Dialog Box
	MenuItem ID_VIEW_RADAR;	// &Radar
	Menu m11;	// Simulation
	MenuItem ID_MENUITEM40038;	// Start
	MenuItem ID_SIMULATION_PAUSE;	// Pause
	MenuItem m14;	// Separator
	MenuItem ID_MENUITEM40039;	// Speed Up
	MenuItem ID_MENUITEM40040;	// Speed Down
	MenuItem ID_MENUITEM40041;	// Normal
	MenuItem m18;	// Separator
	MenuItem ID_MENUITEM40042;	// Remove Aircraft
	Menu m20;	// Options
	Menu m21;	// Plane History
	MenuItem ID_OPTIONS_PLANEHISTORY_6;	// 6
	MenuItem ID_OPTIONS_PLANEHISTORY_10;	// 10
	MenuItem m24;	// Separator
	MenuItem ID_OPTIONS_PLANEHISTORY_OTHER;	// Other...
	Menu m26;	// Predict Lines
	MenuItem ID_MENUITEM40044;	// Off
	MenuItem m28;	// Separator
	MenuItem ID_MENUITEM40045;	// 5 Min
	MenuItem ID_MENUITEM40046;	// 10 Min
	MenuItem m31;	// Separator
	MenuItem ID_MENUITEM40047;	// Other...
	MenuItem ID_MENUITEM40043;	// Radius Rings
	MenuItem cen_rings;	// Center Rings???
	Menu m35;	// &Help
	MenuItem ID_MENUITEM40048;	// Contents
	MenuItem ID_MENUITEM40049;	// Commands
	MenuItem ID_MENUITEM40050;	// How to use Help
	MenuItem m39;	// Separator
	MenuItem ID_HELP_ABOUT;	// &About...

	// Constructor
	//--------------------------------------------------------------------------
	public MainMenu (Frame frame)
	{
		m_Frame = frame;
	}

	// Initialization.
	//--------------------------------------------------------------------------
	public boolean CreateMenu()
	{
		// Can only init controls once
		//----------------------------------------------------------------------
		if (m_fInitialized || m_Frame == null)
			return false;

		// Create menubar and attach to the frame
		//----------------------------------------------------------------------
		mb = new MenuBar();
		m_Frame.setMenuBar(mb);

		// Create menu and menu items and assign to menubar
		//----------------------------------------------------------------------
		m1 = new Menu("&File");
		mb.add(m1);
			ID_FILE_RESTART = new MenuItem("&Restart");
			m1.add(ID_FILE_RESTART);
			ID_FILE_NEW = new MenuItem("&New");
			m1.add(ID_FILE_NEW);
			m4 = new MenuItem("-");
			m1.add(m4);
			ID_FILE_EXIT = new MenuItem("&Exit");
			m1.add(ID_FILE_EXIT);
		m6 = new Menu("Display");
		mb.add(m6);
			ID_VIEW_CLOCK = new MenuItem("&Clock");
			m6.add(ID_VIEW_CLOCK);
			ID_VIEW_FPS = new MenuItem("&Flight Progress Slips");
			m6.add(ID_VIEW_FPS);
			ID_VIEW_COMMANDDIALOG = new MenuItem("Command &Dialog Box");
			m6.add(ID_VIEW_COMMANDDIALOG);
			ID_VIEW_RADAR = new MenuItem("&Radar");
			m6.add(ID_VIEW_RADAR);
		m11 = new Menu("Simulation");
		mb.add(m11);
			ID_MENUITEM40038 = new MenuItem("Start");
			m11.add(ID_MENUITEM40038);
			ID_SIMULATION_PAUSE = new MenuItem("Pause");
			m11.add(ID_SIMULATION_PAUSE);
			m14 = new MenuItem("-");
			m11.add(m14);
			ID_MENUITEM40039 = new MenuItem("Speed Up");
			m11.add(ID_MENUITEM40039);
			ID_MENUITEM40040 = new MenuItem("Speed Down");
			m11.add(ID_MENUITEM40040);
			ID_MENUITEM40041 = new MenuItem("Normal");
			m11.add(ID_MENUITEM40041);
			m18 = new MenuItem("-");
			m11.add(m18);
			ID_MENUITEM40042 = new MenuItem("Remove Aircraft");
			m11.add(ID_MENUITEM40042);
		m20 = new Menu("Options");
		mb.add(m20);
			m21 = new Menu("Plane History");
			m20.add(m21);
				ID_OPTIONS_PLANEHISTORY_6 = new MenuItem("6");
				m21.add(ID_OPTIONS_PLANEHISTORY_6);
				ID_OPTIONS_PLANEHISTORY_10 = new MenuItem("10");
				m21.add(ID_OPTIONS_PLANEHISTORY_10);
				m24 = new MenuItem("-");
				m21.add(m24);
				ID_OPTIONS_PLANEHISTORY_OTHER = new MenuItem("Other...");
				m21.add(ID_OPTIONS_PLANEHISTORY_OTHER);
			m26 = new Menu("Predict Lines");
			m20.add(m26);
				ID_MENUITEM40044 = new MenuItem("Off");
				m26.add(ID_MENUITEM40044);
				m28 = new MenuItem("-");
				m26.add(m28);
				ID_MENUITEM40045 = new MenuItem("5 Min");
				m26.add(ID_MENUITEM40045);
				ID_MENUITEM40046 = new MenuItem("10 Min");
				m26.add(ID_MENUITEM40046);
				m31 = new MenuItem("-");
				m26.add(m31);
				ID_MENUITEM40047 = new MenuItem("Other...");
				m26.add(ID_MENUITEM40047);
			ID_MENUITEM40043 = new MenuItem("Radius Rings");
			m20.add(ID_MENUITEM40043);
			cen_rings = new MenuItem("Center Rings???");
			m20.add(cen_rings);
		m35 = new Menu("&Help");
		mb.add(m35);
			ID_MENUITEM40048 = new MenuItem("Contents");
			m35.add(ID_MENUITEM40048);
			ID_MENUITEM40049 = new MenuItem("Commands");
			m35.add(ID_MENUITEM40049);
			ID_MENUITEM40050 = new MenuItem("How to use Help");
			m35.add(ID_MENUITEM40050);
			m39 = new MenuItem("-");
			m35.add(m39);
			ID_HELP_ABOUT = new MenuItem("&About...");
			m35.add(ID_HELP_ABOUT);

		m_fInitialized = true;
		return true;
	}
}
