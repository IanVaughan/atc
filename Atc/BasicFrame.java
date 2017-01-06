import java.awt.*;

class BasicFrame extends Frame
{
	public BasicFrame(String str)
	{
		super (str);
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
