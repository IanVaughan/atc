//------------------------------------------------------------------------------
// CommandDialog.java:
//		Implementation for container control initialization class CommandDialog
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
//      import CommandDialog;
//
// 2) Create an instance of this class in your Applet's 'init' member, and call
//    CreateControls() through this object:
//
//      CommandDialog ctrls = new CommandDialog (this);
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

public class CommandDialog
{
	Container    m_Parent       = null;
	boolean      m_fInitialized = false;
	DialogLayout m_Layout;

	// Control definitions
	//--------------------------------------------------------------------------
	Button        IDC_SEND;
	Checkbox      IDC_EXPERTITE_FH;
	CheckboxGroup group1;
	Checkbox      IDC_RIGHT;
	Checkbox      IDC_LEFT;
	Button        Turn;
	Button        IDC_BUTTON2;
	List          IDC_FL_LIST;
	List          IDC_TURN_LIST;
	Label         IDC_STATIC1;


	// Constructor
	//--------------------------------------------------------------------------
	public CommandDialog (Container parent)
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
		m_Layout = new DialogLayout(m_Parent, 220, 179);
		m_Parent.setLayout(m_Layout);
		m_Parent.addNotify();

		Dimension size   = m_Layout.getDialogSize();
		Insets    insets = m_Parent.insets();
		
		m_Parent.resize(insets.left + size.width  + insets.right,
                        insets.top  + size.height + insets.bottom);

		// Control creation
		//----------------------------------------------------------------------
		IDC_SEND = new Button ("Send");
		m_Parent.add(IDC_SEND);
		m_Layout.setShape(IDC_SEND, 24, 106, 61, 54);

		IDC_EXPERTITE_FH = new Checkbox ("Expertite");
		m_Parent.add(IDC_EXPERTITE_FH);
		m_Layout.setShape(IDC_EXPERTITE_FH, 170, 78, 43, 10);

		group1 = new CheckboxGroup ();
		IDC_RIGHT = new Checkbox ("Right", group1, false);
		m_Parent.add(IDC_RIGHT);
		m_Layout.setShape(IDC_RIGHT, 137, 154, 31, 18);

		IDC_LEFT = new Checkbox ("", group1, false);
		m_Parent.add(IDC_LEFT);
		m_Layout.setShape(IDC_LEFT, 126, 154, 8, 18);

		Turn = new Button ("Direction");
		m_Parent.add(Turn);
		m_Layout.setShape(Turn, 7, 6, 96, 34);

		IDC_BUTTON2 = new Button ("Flight Level");
		m_Parent.add(IDC_BUTTON2);
		m_Layout.setShape(IDC_BUTTON2, 7, 58, 95, 34);

		IDC_FL_LIST = new List (1, false);
		m_Parent.add(IDC_FL_LIST);
		m_Layout.setShape(IDC_FL_LIST, 177, 6, 26, 68);

		IDC_TURN_LIST = new List (1, false);
		m_Parent.add(IDC_TURN_LIST);
		m_Layout.setShape(IDC_TURN_LIST, 122, 67, 31, 81);

		IDC_STATIC1 = new Label ("Left", Label.LEFT);
		m_Parent.add(IDC_STATIC1);
		m_Layout.setShape(IDC_STATIC1, 114, 158, 12, 8);

		m_fInitialized = true;
		return true;
	}
}
