//------------------------------------------------------------------------------
// OPEN.java:
//		Implementation for container control initialization class OPEN
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
//      import OPEN;
//
// 2) Create an instance of this class in your Applet's 'init' member, and call
//    CreateControls() through this object:
//
//      OPEN ctrls = new OPEN (this);
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

public class OPEN
{
	Container    m_Parent       = null;
	boolean      m_fInitialized = false;
	DialogLayout m_Layout;

	// Control definitions
	//--------------------------------------------------------------------------
	Button        IDOPEN;
	Button        IDCANCEL;
	Choice        ICD_LOOKIN;
	List          IDC_LIST1;
	Label         IDC_STATIC1;
	Scrollbar     IDC_SCROLLBAR2;
	Label         IDC_STATIC2;
	TextField     ICD_FILENAME;
	Label         IDC_STATIC3;
	TextField     IDC_FILETYPE;


	// Constructor
	//--------------------------------------------------------------------------
	public OPEN (Container parent)
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
		m_Layout = new DialogLayout(m_Parent, 259, 159);
		m_Parent.setLayout(m_Layout);
		m_Parent.addNotify();

		Dimension size   = m_Layout.getDialogSize();
		Insets    insets = m_Parent.insets();
		
		m_Parent.resize(insets.left + size.width  + insets.right,
                        insets.top  + size.height + insets.bottom);

		// Control creation
		//----------------------------------------------------------------------
		IDOPEN = new Button ("Open");
		m_Parent.add(IDOPEN);
		m_Layout.setShape(IDOPEN, 202, 121, 50, 14);

		IDCANCEL = new Button ("Cancel");
		m_Parent.add(IDCANCEL);
		m_Layout.setShape(IDCANCEL, 202, 138, 50, 14);

		ICD_LOOKIN = new Choice ();
		m_Parent.add(ICD_LOOKIN);
		m_Layout.setShape(ICD_LOOKIN, 39, 7, 129, 15);

		IDC_LIST1 = new List (1, false);
		m_Parent.add(IDC_LIST1);
		m_Layout.setShape(IDC_LIST1, 7, 23, 245, 81);

		IDC_STATIC1 = new Label ("Look in", Label.LEFT);
		m_Parent.add(IDC_STATIC1);
		m_Layout.setShape(IDC_STATIC1, 7, 7, 27, 14);

		IDC_SCROLLBAR2 = new Scrollbar (Scrollbar.HORIZONTAL, 0, 1, 0, 99);
		m_Parent.add(IDC_SCROLLBAR2);
		m_Layout.setShape(IDC_SCROLLBAR2, 7, 103, 243, 8);

		IDC_STATIC2 = new Label ("File Name :", Label.LEFT);
		m_Parent.add(IDC_STATIC2);
		m_Layout.setShape(IDC_STATIC2, 7, 124, 38, 10);

		ICD_FILENAME = new TextField ("");
		m_Parent.add(ICD_FILENAME);
		m_Layout.setShape(ICD_FILENAME, 53, 123, 139, 12);

		IDC_STATIC3 = new Label ("Files of type :", Label.LEFT);
		m_Parent.add(IDC_STATIC3);
		m_Layout.setShape(IDC_STATIC3, 7, 137, 45, 8);

		IDC_FILETYPE = new TextField ("");
		m_Parent.add(IDC_FILETYPE);
		m_Layout.setShape(IDC_FILETYPE, 53, 138, 138, 12);

		m_fInitialized = true;
		return true;
	}
}
