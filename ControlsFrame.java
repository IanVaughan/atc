//-----------------------------------------------------------------------------------------
//
//		ControlsFrame.java
//
//-----------------------------------------------------------------------------------------
import java.awt.*;
//import CommandDialog;

//-----------------------------------------------------------------------------------------
// ControlsFrame class
//-----------------------------------------------------------------------------------------
class ControlsFrame extends Dialog
{
//	CommandDialog ctrls = null ;
//	PlaneData pd = null; // used to ref the plane were going to change stuff about

	IDC_CONTROL_BOTH ctrls = null ;

	int fl;
	int fh;
	String plane_name ;

	Sky m_sky = null ;

	int plane_num ;
	boolean selected = false ;
//	boolean right_turn ;

	//-----------------------------------------------------------------------------------------
	// Constructor
	//-----------------------------------------------------------------------------------------
	public ControlsFrame(Frame parent)
	{
		super(parent, "Command Dialog Box", false);

		show();
		hide();

//		ctrls = new CommandDialog (this);
//	    ctrls.CreateControls();

		ctrls = new IDC_CONTROL_BOTH (this);
		ctrls.CreateControls();

		move(830,550);

		setResizable(false);

		ctrls.IDC_RIGHT.setState(true);


		for (int i=0; i<72; i++)
		{
			String convert = convert.valueOf(i*5) ;
			if (i<2)
			{
				ctrls.IDC_TURN_LIST.addItem("00"+convert);
			}
			else if (i<20)
			{
				ctrls.IDC_TURN_LIST.addItem("0"+convert);
			}
			else
			{
				ctrls.IDC_TURN_LIST.addItem(convert);
			}
			
		}

		for (int i=45; i>9; i--)
		{
			String convert = convert.valueOf(i*10) ;
			ctrls.IDC_FL_LIST.addItem(convert);
		}
	
	} 

	//-----------------------------------------------------------------------------------------
	// Methods
	//-----------------------------------------------------------------------------------------


	// getData
	//-----------------------------------------------------------------------------------------
	public void getData(PlaneDirection planedir_in, int plane_num)
	{
		selected = true ;
		this.plane_num = plane_num ;

		fh = planedir_in.current_heading ;
		fl = (int)planedir_in.current_fl ;
		plane_name = planedir_in.plane_name ;

		ctrls.IDC_TURN_LIST.select(0);
		ctrls.IDC_FL_LIST.select(0);
		
		// set list box to current fl/heading
		ctrls.IDC_TURN_LIST.makeVisible((fh/5)+4);
		ctrls.IDC_TURN_LIST.select(fh/5);
		
		ctrls.IDC_FL_LIST.makeVisible(45-(fl/10)+4);
		ctrls.IDC_FL_LIST.select(45-(fl/10));
	}

	//554:pl#4 (6647)
	// handleEvent
	//-----------------------------------------------------------------------------------------
	public boolean handleEvent(Event e) 
	{
		//6647
		if (ctrls != null) {

			if (e.target instanceof Button)	{
				if (selected) {

//					if(e.target == ctrls.IDC_SEND_FH)
//					{
						// flight heading
						String stage1 = ctrls.IDC_TURN_LIST.getSelectedItem();
						Integer convert = convert.valueOf(stage1) ;
						fh = convert.intValue();

						//m_sky.updatePlaneFH(plane_num, fh, IDC_RIGHT.getState());

//						System.out.println("ControlsFrame:handleEvent.IDC_SEND_FH");
//					}

//					else if (e.target == ctrls.IDC_SEND_FL)
//					{
						// flight level
						String stage2 = ctrls.IDC_FL_LIST.getItem(ctrls.IDC_FL_LIST.getSelectedIndex());
						Integer converts = converts.valueOf(stage2) ;
						fl = converts.intValue();
				
//						m_sky.updatePlaneFL(plane_num, fl, ctrls.IDC_EXPERTITE_FL.getState());

//						System.out.println("ControlsFrame:handleEvent.IDC_SEND_FL");
//					}
				}
			return true;
			}
			
		}

		if (e.id == Event.WINDOW_DESTROY) {
			hide();
			dispose();
		}

		return super.handleEvent(e);        
	}  

	//cl:1132,dl:3
	// setPointer
	//-----------------------------------------------------------------------------------------
	public void setPointer(Sky sky)
	{
		m_sky = sky ;
	}

	public void noneSelected()
	{
		selected = false ;
	}

}//end ControlsFrame