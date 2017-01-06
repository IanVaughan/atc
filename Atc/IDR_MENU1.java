//------------------------------------------------------------------------------
// IDR_MENU1.java:
//		Implementation for menu creation class IDR_MENU1
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
//      import IDR_MENU1;
//
// 2) Create an instance of this class in your Applet's 'main' member, and call
//    CreateMenu() through this object:
//
//      IDR_MENU1 menu = new IDR_MENU1 (frame);
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

public class IDR_MENU1
{
	Frame   m_Frame        = null;
	boolean m_fInitialized = false;

	// MenuBar definitions
	//--------------------------------------------------------------------------
	MenuBar mb;

	// Menu and Menu item definitions
	//--------------------------------------------------------------------------
	Menu m1;	// File
	Menu m2;	// Views
	MenuItem ID_VIEWS_ZOOMIN;	// Zoom in
	MenuItem ID_VIEWS_ZOOMOUT;	// Zoom out
	MenuItem ID_VIEWS_CENTERMAP;	// Center map
	MenuItem m6;	// Separator
	MenuItem ID_VIEWS_ZOOMBOX;	// Zoom box

	// Constructor
	//--------------------------------------------------------------------------
	public IDR_MENU1 (Frame frame)
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
		m1 = new Menu("File");
		mb.add(m1);
		m2 = new Menu("Views");
		mb.add(m2);
			ID_VIEWS_ZOOMIN = new MenuItem("Zoom in");
			m2.add(ID_VIEWS_ZOOMIN);
			ID_VIEWS_ZOOMOUT = new MenuItem("Zoom out");
			m2.add(ID_VIEWS_ZOOMOUT);
			ID_VIEWS_CENTERMAP = new MenuItem("Center map");
			m2.add(ID_VIEWS_CENTERMAP);
			m6 = new MenuItem("-");
			m2.add(m6);
			ID_VIEWS_ZOOMBOX = new MenuItem("Zoom box");
			m2.add(ID_VIEWS_ZOOMBOX);

		m_fInitialized = true;
		return true;
	}
}
