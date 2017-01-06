import java.awt.* ;

class ZoomFrame extends Dialog
{
	Radar m_radar = null ;
	ZoomBox zbox = null;

	public ZoomFrame(Frame parent)
	{
		super(parent, "Zoom Box", false);

		zbox = new ZoomBox(this);
		zbox.CreateControls();

		move(830,300);
		setResizable(false);

		zbox.ZOOM_SCROLL.setValues(120,10,0,400); // start at current heading
//		zbox.CEN_SCROLL_X.setValues(0,1,50,100);
//		zbox.CEN_SCROLL_Y.setValues(0,1,50,100);

		zbox.ZOOM_SCROLL.setPageIncrement(1);
//		zbox.CEN_SCROLL_X.setPageIncrement(1);
//		zbox.CEN_SCROLL_Y.setPageIncrement(1);

//		zbox.LOCK_CEN.setState(true);

		zbox.ZOOM_EDIT.setText("0");
	}

	public boolean handleEvent(Event e)
	{	
		String ts = null ;
		if(e.target == zbox.ZOOM_SCROLL)
		{
			// zoom factor
			int zoom_fac = zbox.ZOOM_SCROLL.getValue() ;
			ts = String.valueOf(zoom_fac);
			zbox.ZOOM_EDIT.setText(ts);
			
			m_radar.haveNewZoomSizes(zoom_fac +1);
		}

/*		if(e.target == zbox.CEN_SCROLL_Y)
		{
			System.out.println("Scrolling...");
		}
		
		if(e.target == zbox.CEN_SCROLL_X)
		{
		}
*/		
		if (e.id == Event.WINDOW_DESTROY)
		{
			// we dont want to kill the app alltogether now do we!!
			hide();
			dispose();
		}
	
		return true;
	}
	
	public void setPointer(Radar radar)
	{
		m_radar = radar ;
	}
}