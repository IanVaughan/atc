import java.awt.*;

class BasicDialog extends Dialog
{
	public BasicDialog(Frame parent, String str)
	{
		super (parent, str,true);
	}

	public boolean handleEvent(Event evt)
	{
		switch (evt.id)
		{
			// Application shutdown (e.g. user chooses Close from the system menu).
			//------------------------------------------------------------------
			case Event.WINDOW_DESTROY:
				// TODO: Place additional clean up code here
				hide();
				dispose();
				return true;

			default:
				return super.handleEvent(evt);
		}			 
	}
}
