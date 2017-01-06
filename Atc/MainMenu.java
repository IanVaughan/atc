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
	MenuItem m3;	// Separator
	MenuItem ID_FILE_EXIT;	// &Exit
	Menu m5;	// Display
	MenuItem ID_VIEW_CLOCK;	// &Clock
	MenuItem ID_VIEW_FPS;	// &Flight Progress Strips
	MenuItem ID_VIEW_COMMANDDIALOG;	// Command Control &Box
	MenuItem ID_VIEW_RADAR;	// &Radar Window
	MenuItem ID_VIEW_ZOOM;	// &Zoom Control
	Menu m11;	// Options
	MenuItem ID_OPTIONS_PREDICTLINES;	// &Predict Lines
	MenuItem ID_MENUITEM40043;	// &Radius Rings
	MenuItem cen_rings;	// &Centre Rings

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
			m3 = new MenuItem("-");
			m1.add(m3);
			ID_FILE_EXIT = new MenuItem("&Exit");
			m1.add(ID_FILE_EXIT);
		m5 = new Menu("Display");
		mb.add(m5);
			ID_VIEW_CLOCK = new MenuItem("&Clock");
			m5.add(ID_VIEW_CLOCK);
			ID_VIEW_FPS = new MenuItem("&Flight Progress Strips");
			m5.add(ID_VIEW_FPS);
			ID_VIEW_COMMANDDIALOG = new MenuItem("Command Control &Box");
			m5.add(ID_VIEW_COMMANDDIALOG);
			ID_VIEW_RADAR = new MenuItem("&Radar Window");
			m5.add(ID_VIEW_RADAR);
			ID_VIEW_ZOOM = new MenuItem("&Zoom Control");
			m5.add(ID_VIEW_ZOOM);
		m11 = new Menu("Options");
		mb.add(m11);
			ID_OPTIONS_PREDICTLINES = new MenuItem("&Predict Lines");
			m11.add(ID_OPTIONS_PREDICTLINES);
			ID_MENUITEM40043 = new MenuItem("&Radius Rings");
			m11.add(ID_MENUITEM40043);
			cen_rings = new MenuItem("&Centre Rings");
			m11.add(cen_rings);

		m_fInitialized = true;
		return true;
	}
}
