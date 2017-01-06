//------------------------------------------------------------------------------
// IDC_CONTROL_BOTH.java:
//		Implementation for container control initialization class IDC_CONTROL_BOTH
//
// WARNING: Do not modify this file.  This file is recreated each time its
//          associated .rct/.res file is sent through the Java Resource Wizard!
//
// This class can be use to create controls within any container, however, the
// following describes how to use this class with an Applet.  For addtional
// information on using Java Resource Wizard generated classes, refer to the
// Visual J++ 1.1 documention.
//
// 1) Import this class in the .java file for the Applet that will use it:
//
//      import IDC_CONTROL_BOTH;
//
// 2) Create an instance of this class in your Applet's 'init' member, and call
//    CreateControls() through this object:
//
//      IDC_CONTROL_BOTH ctrls = new IDC_CONTROL_BOTH (this);
//      ctrls.CreateControls();
//
// 3) To process events generated from user action on these controls, implement
//    the 'handleEvent' member for your applet:
//
//      public boolean handleEvent (Event evt)
//      {
//
//      }
//
//------------------------------------------------------------------------------
import java.awt.*;
import DialogLayout;

public class IDC_CONTROL_BOTH
{
	Container    m_Parent       = null;
	boolean      m_fInitialized = false;
	DialogLayout m_Layout;

	// Control definitions
	//--------------------------------------------------------------------------
	Checkbox      IDC_EXPERTITE_FL;
	List          IDC_FL_LIST;
	Button        IDC_SEND_FL;
	CheckboxGroup group1;
	Checkbox      IDC_RIGHT;
	Checkbox      IDC_LEFT;
	List          IDC_TURN_LIST;
	Button        IDC_SEND_FH;


	// Constructor
	//--------------------------------------------------------------------------
	public IDC_CONTROL_BOTH (Container parent)
	{
		m_Parent = parent;
	}

	// Initialization.
	//--------------------------------------------------------------------------
	public boolean CreateControls()
	{
		// Can only init controls once
		//----------------------------------------------------------------------
		if (m_fInitialized || m_Parent == null)
			return false;

		// Parent must be a derivation of the Container class
		//----------------------------------------------------------------------
		if (!(m_Parent instanceof Container))
			return false;

		// Since there is no way to know if a given font is supported from
		// platform to platform, we only change the size of the font, and not
		// type face name.  And, we only change the font if the dialog resource
		// specified a font.
		//----------------------------------------------------------------------
		Font OldFnt = m_Parent.getFont();
		
		if (OldFnt != null)
		{
			Font NewFnt = new Font(OldFnt.getName(), OldFnt.getStyle(), 8);

			m_Parent.setFont(NewFnt);
		}

		// All position and sizes are in Dialog Units, so, we use the
		// DialogLayout manager.
		//----------------------------------------------------------------------
		m_Layout = new DialogLayout(m_Parent, 127, 153);
		m_Parent.setLayout(m_Layout);
		m_Parent.addNotify();

		Dimension size   = m_Layout.getDialogSize();
		Insets    insets = m_Parent.insets();
		
		m_Parent.resize(insets.left + size.width  + insets.right,
                        insets.top  + size.height + insets.bottom);

		// Control creation
		//----------------------------------------------------------------------
		IDC_EXPERTITE_FL = new Checkbox ("Expertite");
		m_Parent.add(IDC_EXPERTITE_FL);
		m_Layout.setShape(IDC_EXPERTITE_FL, 78, 96, 42, 10);

		IDC_FL_LIST = new List (1, false);
		m_Parent.add(IDC_FL_LIST);
		m_Layout.setShape(IDC_FL_LIST, 78, 7, 42, 81);

		IDC_SEND_FL = new Button ("Send FL");
		m_Parent.add(IDC_SEND_FL);
		m_Layout.setShape(IDC_SEND_FL, 75, 123, 45, 23);

		group1 = new CheckboxGroup ();
		IDC_RIGHT = new Checkbox ("Right", group1, false);
		m_Parent.add(IDC_RIGHT);
		m_Layout.setShape(IDC_RIGHT, 26, 105, 31, 8);

		IDC_LEFT = new Checkbox ("Left", group1, false);
		m_Parent.add(IDC_LEFT);
		m_Layout.setShape(IDC_LEFT, 7, 93, 21, 10);

		IDC_TURN_LIST = new List (1, false);
		m_Parent.add(IDC_TURN_LIST);
		m_Layout.setShape(IDC_TURN_LIST, 7, 7, 46, 81);

		IDC_SEND_FH = new Button ("Send FH");
		m_Parent.add(IDC_SEND_FH);
		m_Layout.setShape(IDC_SEND_FH, 7, 123, 47, 23);

		m_fInitialized = true;
		return true;
	}
}
