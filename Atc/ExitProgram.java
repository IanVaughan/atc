//
//
// Exit
//
//

import java.awt.*;

class ExitProgram extends Dialog
{ 
	private Exit ctrls = null ;

	public ExitProgram(Frame parent)
	{
		super(parent, "Aircraft Performance Sim", true);
		ctrls = new Exit (this);
	    ctrls.CreateControls();			
		move(500,400);
		setResizable(false);
		show();
	}
	
	public boolean action(Event evt, Object o)
	{	
		if (evt.target == ctrls.IDOK)
		{
			System.exit(0);
		}
		else if (evt.target == ctrls.IDCANCEL)
		{
			dispose();
		}
	 	return true;
	}
}

