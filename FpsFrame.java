//-----------------------------------------------------------------------------------------
//
//		FpsFrame.java
//
//-----------------------------------------------------------------------------------------
import java.awt.*;

//-----------------------------------------------------------------------------------------
//	FpsFrame class
//-----------------------------------------------------------------------------------------
class FpsFrame extends Dialog
{
	Sky m_sky ;

	//-----------------------------------------------------------------------------------------
	//	Constructor
	//-----------------------------------------------------------------------------------------
	public FpsFrame(Frame parent)
	{
		super(parent, "Flight Progress Slips", false);

		reshape(10,770,650,200);
		show();	
		hide();
		setResizable(false);		
	}

	//-----------------------------------------------------------------------------------------
	// Methods
	//-----------------------------------------------------------------------------------------

	//cl:1132,dl:3
	// setPointer
	//-----------------------------------------------------------------------------------------
	public void setPointer(Sky sky)
	{
		m_sky = sky ;
	}

	// paint
	//-----------------------------------------------------------------------------------------
	public void paint(Graphics g)
	{
		if(m_sky != null) {
		// draw fps
		Image fps1 = Toolkit.getDefaultToolkit().getImage("Images/fps_gif.gif");
		g.drawImage(fps1,5,30,this);
		g.drawImage(fps1,5,115,this);

		Font f;
		
		int y = 0 ;

		f = new Font("Helvetica",Font.PLAIN,20);
		g.setFont(f);
		g.drawString(m_sky.m_plane[0].asd.callsign,		250,70+y);	// 6
		
		f = new Font("Helvetica",Font.PLAIN,15);
		g.setFont(f);
		g.drawString(Integer.toString(m_sky.m_plane[0].acd.cruise_speed),		345,100+y);			// 9
//		g.drawString("??",								15, 100+y);			// 2
		g.drawString(m_sky.m_plane[0].acd.ICAO,			230,100+y);		  	// 7
		g.drawString(m_sky.m_plane[0].asd.route1,		388,100+y);			// 10
		g.drawString(m_sky.m_plane[0].asd.route2,		440,100+y);			// 11
		g.drawString(m_sky.m_plane[0].asd.route3,		480,100+y);			// 12

		f = new Font("Helvetica",Font.PLAIN,12);
		g.setFont(f);
//		g.drawString("????",							20,  50+y);			// 1
		g.drawString(m_sky.m_plane[0].asd.fin_loc,		65,  50+y);			// 3
		g.drawString(Integer.toString(m_sky.m_plane[0].asd.start_fl),			115, 70+y);			// 4
		g.drawString(m_sky.m_plane[0].asd.opperator,	235, 50+y);			// 5
//		g.drawString("????",							290, 85+y);			// 8
//		g.drawString("??",								550, 43+y);			// 13
		g.drawString(m_sky.m_plane[0].asd.start_loc,	550, 55+y);			// 14
		g.drawString(Integer.toString(m_sky.m_plane[0].asd.start_delay),		600, 55+y);			// 15
	
	
		y = 85 ;

		f = new Font("Helvetica",Font.PLAIN,20);
		g.setFont(f);
		g.drawString(m_sky.m_plane[1].asd.callsign,		250,70+y);	// 6
		
		f = new Font("Helvetica",Font.PLAIN,15);
		g.setFont(f);
		g.drawString(Integer.toString(m_sky.m_plane[1].acd.cruise_speed),		345,100+y);			// 9
	//	g.drawString("??",								15, 100+y);			// 2
		g.drawString(m_sky.m_plane[1].acd.ICAO,			230,100+y);		  	// 7
		g.drawString(m_sky.m_plane[1].asd.route1,		388,100+y);			// 10
		g.drawString(m_sky.m_plane[1].asd.route2,		440,100+y);			// 11
		g.drawString(m_sky.m_plane[1].asd.route3,		480,100+y);			// 12

		f = new Font("Helvetica",Font.PLAIN,12);
		g.setFont(f);
	//	g.drawString("????",							20,  50+y);			// 1
		g.drawString(m_sky.m_plane[1].asd.fin_loc,		65,  50+y);			// 3
		g.drawString(Integer.toString(m_sky.m_plane[1].asd.start_fl),			115, 70+y);			// 4
		g.drawString(m_sky.m_plane[1].asd.opperator,	235, 50+y);			// 5
	//	g.drawString("????",							290, 85+y);			// 8
		g.drawString("??",								550, 43+y);			// 13
	//	g.drawString(m_sky.m_plane[1].asd.start_loc,	550, 55+y);			// 14
		g.drawString(Integer.toString(m_sky.m_plane[1].asd.start_delay),		600, 55+y);			// 15
	
	

//		Image cross = Toolkit.getDefaultToolkit().getImage("Images/ian cross1.gif");
//		g.drawImage(cross,105,60,this);
	
		}
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