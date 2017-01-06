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
	ZoomFrame zoomframe = null ;

	// Constructor
	//-----------------------------------------------------------------------------------------
	public AtcMain(String situation_fileName, Dimension screen_size) //Dimension screen_size
	{
		super("Atc");
		System.out.print("\nLoading...");

		options = new AtcOptions();
		
		setTitle("CATC - Air Traffic Control Simulator");

		// create menu
		MainMenu menu = new MainMenu(this);  	
		menu.CreateMenu();
		System.out.print("\n debug info... \nmenu - ok \nmain screen");

		resize(screen_size.width, screen_size.height-60);  
		reshape(-4,-4,1160,843);
		System.out.print(" - ok \nradar");
		
		// open all windows that should be open
	
		// radar
		radar = new Radar(this); // controls is the ref for the controls box we made
		System.out.print(" - ok \ncontrols");
		
		// controls box, which changes plane heading/flight level
		controls = new ControlsFrame(this); // a ref to radar so we can call medthods
		System.out.print(" - ok\nclock");
		
		// the clock with start/pause/stop buttons on
		clock = new ClockFrame(this); // a ref to radar, so we can call its methods
		System.out.print(" - ok \nfps");
		
		// flight progress slips
		fps = new FpsFrame(this);			// fps is a 'child' of "this" frame	
		System.out.print(" - ok \nsky");

		//Sky class  cn227
		sky = new Sky(situation_fileName);
		System.out.print(" - ok \nzoom box");

		zoomframe = new ZoomFrame(this);
		System.out.print(" - ok \n Setting pointers \ncontrols");


		//cl:113,dl:3
		controls.setPointer(sky);			System.out.print(" - ok \nclock");
		clock.setPointer(sky);				System.out.print(" - ok \nfps");
		
		fps.setPointer(sky);				System.out.print(" - ok \nzoombox");
		zoomframe.setPointer(radar);
		radar.setPointer(sky, controls,zoomframe);	System.out.print(" - ok \n Showing \nmain");

		show();  System.out.print(" - ok \nradar");	// main menu	

		radar.show();		System.out.print(" - ok \ncontrols");
		controls.show();	System.out.print(" - ok \nfps");
		fps.show();			System.out.print(" - ok \nzoombox");
		zoomframe.show();	System.out.print(" - ok \nclock");
		clock.show();		System.out.print(" - ok \n All OK!");

		System.out.println("\n\nThank you.\n\n");
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
			if(o.equals("Command Control &Box"))
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
					
			if(o.equals("&Flight Progress Strips"))
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
			
			if(o.equals("&Radar Window"))
			{
				if(radar.isVisible())
				{
					radar.hide();
					
				}
				else
				{
					radar.show();
				}
			}

			if(o.equals("&Zoom Control"))
			{
				if(zoomframe.isVisible())				{
					zoomframe.hide();
				}
				else{
					zoomframe.show();
				}
			}

		


			if(o.equals("&Radius Rings"))
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

			if(o.equals("&Centre Rings"))
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

			if(o.equals("&Predict Lines"))
			{
				if (radar.predict)
				{
					radar.predict = false ;
				}
				else
				{
					radar.predict = true ;
				}

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
				if (radar != null)
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
			Rectangle main_size = new Rectangle(this.bounds());
			Rectangle radar_size = new Rectangle(radar.bounds());
			Rectangle clock_size = new Rectangle(clock.bounds());
			Rectangle controls_size = new Rectangle(controls.bounds());
			Rectangle fps_size = new Rectangle(fps.bounds());
			Rectangle zoom_size = new Rectangle(zoomframe.bounds());

			System.out.println("Main     :"+main_size);
			System.out.println("Radar    :"+radar_size);
			System.out.println("Clock    :"+clock_size);
			System.out.println("Controls :"+controls_size);
			System.out.println("Fps      :"+fps_size);
			System.out.println("Zoom     :"+zoom_size);

		//	try {
		//	System.in.read();
		//	}
		//	catch(java.io.IOException ioe)			{}

			// user has quit, so kill app
			ExitProgram exiting = new ExitProgram(this);

			return true;
		}
		return super.handleEvent(e);		
	}
}