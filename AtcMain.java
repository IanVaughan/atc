/* -----------------------------------------------------------------------------------------

  
	AtcMain.java


  ---------------------------------------------------------------------------------------- */

import java.awt.*;
import MainMenu;
import Radar;
import ClockFrame;
import ControlsFrame;
import FpsFrame;
//227
import Sky;

//-----------------------------------------------------------------------------------------
// AtcMain class
//-----------------------------------------------------------------------------------------
class AtcMain extends Frame
{
	FpsFrame fps = null;
	ClockFrame clock = null;
	ControlsFrame controls = null;
	Radar radar = null;
	Sky sky = null ;
	AtcOptions options = null ;

	// Constructor
	//-----------------------------------------------------------------------------------------
	public AtcMain(String situation_fileName, Dimension screen_size) //Dimension screen_size
	{
		super("Atc");
		setBackground(Color.gray);
		System.out.print("\nLoading.");
		System.out.print(".");

//		Frame openframe = new Open(); //so slow the programs loaded before its fin loading image

		options = new AtcOptions();
		
		setTitle("CATC - Air Traffic Control Simulator");
		System.out.print(".");

		// create menu
		MainMenu menu = new MainMenu(this);  	
		menu.CreateMenu();
		System.out.print(".");

		resize(screen_size.width, screen_size.height-30);  
		reshape(-4,-4,1288,1004);
		System.out.print(".");
		
		// open all windows that should be open
	
		// radar
		radar = new Radar(this); // controls is the ref for the controls box we made
		System.out.print(".");
		
		// controls box, which changes plane heading/flight level
		controls = new ControlsFrame(this); // a ref to radar so we can call medthods
		System.out.print(".");
		
		// the clock with start/pause/stop buttons on
		clock = new ClockFrame(this); // a ref to radar, so we can call its methods
		System.out.print(".");
		
		// flight progress slips
		fps = new FpsFrame(this);			// fps is a 'child' of "this" frame	
		System.out.print(".");

		//Sky class  cn227
		sky = new Sky(situation_fileName);
		System.out.print(".");

		ZoomFrame zoomframe = new ZoomFrame(this);
		System.out.print(".");


		//cl:113,dl:3
		controls.setPointer(sky);			System.out.print(".");
		clock.setPointer(sky);				System.out.print(".");
		
		fps.setPointer(sky);				System.out.print(".");
//		sky.setPointer(m_map);  			System.out.print(".");
		zoomframe.setPointer(radar);
		radar.setPointer(sky, controls,zoomframe);	System.out.print(".");

		show();  System.out.print(".");	// main menu	

		radar.show();		System.out.print(".");
		controls.show();	System.out.print(".");
		fps.show();			System.out.print(".");
		zoomframe.show();	System.out.print(".");
		clock.show();		System.out.print(".\n");

		System.out.println("\n\nThank you.\n\n");

//		openframe.dispose();
	}

	//-----------------------------------------------------------------------------------------
	// Methods
	//-----------------------------------------------------------------------------------------

	// action
	//-----------------------------------------------------------------------------------------
	public boolean action(Event e, Object o)
	{
		if (radar==null) {
			System.out.println("radar = null");
		}

		if(e.target instanceof MenuItem)
		{
			// view/display menu
			// ------------------------------------------------------------------------------------
			if(o.equals("Command &Dialog Box"))
			{
				if (controls.isVisible())
				{
					controls.hide();
				}
				else 
				{
					controls.show();
				}
			}
			
			if(o.equals("&Clock"))
			{
				if (clock.isVisible())
				{
					clock.hide();
				}
				else
				{
					clock.show();
				}
			}
					
			if(o.equals("&Flight Progress Slips"))
			{
				if(fps.isVisible())
				{
					fps.hide();
				}
				else
				{
					fps.show();
				}
			}
			
			if(o.equals("&Radar"))
			{
				if(radar.isVisible())
				{
					radar.dispose();
					System.out.println("menu->isVisible");
				}
				else
				{
					radar = new Radar(this);
					System.out.println("menu->new radar");
				}
			}

			if(o.equals("Radius Rings"))
			{
				if (radar.radius)
				{
					radar.radius = false ;
				}
				else
				{
					radar.radius = true ;
				}

				radar.repaint();
			}

			if(o.equals("Center Rings???"))
			{
				if (radar.cen_rings)
				{
					radar.cen_rings = false ;
				}
				else
				{
					radar.cen_rings = true ;
				}

				radar.repaint();
			}

			if(o.equals("5 Min"))
			{
				radar.predict = true ;
				radar.repaint();
			}

			if(o.equals("Off"))
			{
				radar.predict = false ;
				radar.repaint();
			}

			// file menu
			// ---------
			if(o.equals("&Exit")){
				// display exit dialog box
				ExitProgram exiting = new ExitProgram(this);
			}

			if(o.equals("&Restart"))
			{
				if (radar!=null)
				{	
					radar.dispose();	
				}
				
				radar = new Radar(this);
			}
		}
		return true;
	}

	// handleEvent
	//-----------------------------------------------------------------------------------------
	public boolean handleEvent(Event e)
	{
		if (e.id == Event.WINDOW_DESTROY)
		{
			// user has quit, so kill app
				ExitProgram exiting = new ExitProgram(this);
			return true;
		}
		return super.handleEvent(e);

		
	}
}