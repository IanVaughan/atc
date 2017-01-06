//------------------------------------------------------------------------------
// Popup1.java:
//		Implementation for menu creation class Popup1
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
//      import Popup1;
//
// 2) Create an instance of this class in your Applet's 'main' member, and call
//    CreateMenu() through this object:
//
//      Popup1 menu = new Popup1 (frame);
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

public class Popup1
{
	Frame   m_Frame        = null;
	boolean m_fInitialized = false;

	// MenuBar definitions
	//--------------------------------------------------------------------------
	MenuBar mb;

	// Menu and Menu item definitions
	//--------------------------------------------------------------------------
	Menu ID_PAUSE;	// &Pause
	Menu m2;	// Separator
	Menu m3;	// Plane History
	MenuItem ID_PLANEHISTORY_6;	// &6
	MenuItem ID_PLANEHISTORY_10;	// &10
	MenuItem m6;	// Separator
	MenuItem ID_PLANEHISTORY_OTHER;	// &Other...
	Menu ID_COLORS;	// &Colors...

	// Constructor
	//--------------------------------------------------------------------------
	public Popup1 (Frame frame)
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
		ID_PAUSE = new Menu("&Pause");
		mb.add(ID_PAUSE);
		m2 = new Menu("-");
		mb.add(m2);
		m3 = new Menu("Plane History");
		mb.add(m3);
			ID_PLANEHISTORY_6 = new MenuItem("&6");
			m3.add(ID_PLANEHISTORY_6);
			ID_PLANEHISTORY_10 = new MenuItem("&10");
			m3.add(ID_PLANEHISTORY_10);
			m6 = new MenuItem("-");
			m3.add(m6);
			ID_PLANEHISTORY_OTHER = new MenuItem("&Other...");
			m3.add(ID_PLANEHISTORY_OTHER);
		ID_COLORS = new Menu("&Colors...");
		mb.add(ID_COLORS);

		m_fInitialized = true;
		return true;
	}
}
