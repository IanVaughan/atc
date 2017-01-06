//-----------------------------------------------------------------------------------------
//
//		ClockFrame.java
//
//-----------------------------------------------------------------------------------------

//import java.awt.event.*;
import java.awt.*;
import Clock;
//import RunningClock;

//-----------------------------------------------------------------------------------------
// ClockFrame class
//-----------------------------------------------------------------------------------------
class ClockFrame extends Dialog
{
	Clock clock = null;
	Sky m_sky = null ;

	// Constructor
	//-----------------------------------------------------------------------------------------
	public ClockFrame(Frame parent)
	{
		super(parent, "Clock", false);

		// create dialog and add it to our frame
		clock = new Clock (this);
        clock.CreateControls();

		move(830,60);
		setResizable(false);
	}

	//-----------------------------------------------------------------------------------------
	// Methods
	//-----------------------------------------------------------------------------------------

	// action
	//----------------------------------------------------------------------------------------- 
	public boolean action(Event e, Object o)
	{	
		Object target = e.target;
		// if this is our button
		if (target instanceof Button)
		{
			Button button = (Button)target;	// get the button pressed

			String buttonLabel = button.getLabel(); 
			
			// start threads
			if (buttonLabel.compareTo("Start") ==0)
			{		
				clock.IDC_START.setLabel("Stop"); // change button text

				m_sky.startPlanes();
			}

			// restart sim
			else if (buttonLabel.compareTo("Stop") ==0)
			{
				clock.IDC_START.setLabel("Start");

				m_sky.pausePlanes();

				// need to have some ending stuff here!!!
			}

			// pause threads
			else if (buttonLabel.compareTo("Pause") ==0)
			{
				clock.IDC_PAUSE.setLabel("Paused");

				m_sky.pausePlanes();
			}

			// ? unpause:continue  threads
			else if (buttonLabel.compareTo("Paused") ==0)
			{
				clock.IDC_PAUSE.setLabel("Pause");

				m_sky.startPlanes();
			}
			
			return true;
		}
		return false;
	}

	//cl:1137,dl:3
	// setPointer
	//-----------------------------------------------------------------------------------------
	public void setPointer(Sky sky)
	{
		m_sky = sky ;
	}

	// handleEvent
	//-----------------------------------------------------------------------------------------
	public boolean handleEvent(Event e)
	{
		if (e.id == Event.WINDOW_DESTROY)
		{
			// we dont want to kill the app alltogether now do we!!
			hide();
			dispose();
		}
		return super.handleEvent(e);
	}
}