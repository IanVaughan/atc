//
//
// PopupFrame
//
//
import Popup1;
import java.awt.*;

class PopupFrame //extends Dialog
{
	PopupMenu popup ;
	MenuItem m1, m2, m3 ;

	public PopupFrame(Frame parent) 
	{
	//	super(parent, true); // t/f modal
	//	show();
	//	hide();

		popup = new PopupMenu("Edit");

		m1 = new MenuItem("This");
		m2 = new MenuItem("is");
		m3 = new MenuItem("bollocks");
		
		popup.add(m1);
		popup.add(m2);
		popup.add(m3);

		add(popup);

//		menu.CreateMenu();

//		reshape(0,0,50,100);
//		setResizable(false);
	}	


	public boolean handleEvent (Event e)
	{
		if (e.id == Event.WINDOW_DESTROY) {
			hide();
			dispose();
		}
		return super.handleEvent(e);
	}
}

