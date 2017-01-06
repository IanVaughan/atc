//------------------------------------------------------------------------------
// IDD_CONTROLS_FL.java:
//		Implementation for container control initialization class IDD_CONTROLS_FL
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
//      import IDD_CONTROLS_FL;
//
// 2) Create an instance of this class in your Applet's 'init' member, and call
//    CreateControls() through this object:
//
//      IDD_CONTROLS_FL ctrls = new IDD_CONTROLS_FL (this);
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

public class IDD_CONTROLS_FL
{
	Container    m_Parent       = null;
	boolean      m_fInitialized = false;
	DialogLayout m_Layout;

	// Control definitions
	//--------------------------------------------------------------------------
	Checkbox      IDC_EXPERTITE_FH;
	List          IDC_FL_LIST;
	Button        IDC_SEND_FL;


	// Constructor
	//--------------------------------------------------------------------------
	public IDD_CONTROLS_FL (Container parent)
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
		m_Layout = new DialogLayout(m_Parent, 57, 125);
		m_Parent.setLayout(m_Layout);
		m_Parent.addNotify();

		Dimension size   = m_Layout.getDialogSize();
		Insets    insets = m_Parent.insets();
		
		m_Parent.resize(insets.left + size.width  + insets.right,
                        insets.top  + size.height + insets.bottom);

		// Control creation
		//----------------------------------------------------------------------
		IDC_EXPERTITE_FH = new Checkbox ("Expertite");
		m_Parent.add(IDC_EXPERTITE_FH);
		m_Layout.setShape(IDC_EXPERTITE_FH, 7, 78, 42, 10);

		IDC_FL_LIST = new List (1, false);
		m_Parent.add(IDC_FL_LIST);
		m_Layout.setShape(IDC_FL_LIST, 7, 7, 42, 68);

		IDC_SEND_FL = new Button ("Send");
		m_Parent.add(IDC_SEND_FL);
		m_Layout.setShape(IDC_SEND_FL, 7, 95, 42, 23);

		m_fInitialized = true;
		return true;
	}
}
