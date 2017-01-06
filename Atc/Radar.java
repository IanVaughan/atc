//========================================================================================
//	
//	Radar.java
//
//	implements the radar screen
//	
//========================================================================================  

import java.awt.*;
import Plane;

// Radar class
//-----------------------------------------------------------------------------------------
class Radar extends Dialog implements Runnable
{
	private Thread m_thread = null;
	private Dimension m_dimCursorLoc = null; // used to hold cursor pos

	private ControlsFrame m_controls = null;
	private Sky m_sky = null ;
	private ZoomFrame zoombox = null ;

	private int num_planes  ;	//replace with link list!!
	private int num_trail ;

	private FloatPoint[] plane_cord = new FloatPoint[10] ;
	private Point[] plane_pos = new Point[10] ;
	private FloatPoint[][] history = new FloatPoint[10][]; 
	private PlaneDirection[] plane_dir = new PlaneDirection[10] ;

	private int selected_plane_num = -1;
	private boolean ok2paint = false ;
	
	Map m_map = null;
	private Rectangle window = new Rectangle();
	private Rectangle scale = new Rectangle();
	private Rectangle zoom = new Rectangle();

	boolean radius = false ;
	boolean predict = false ;
	boolean cen_rings = false ;


	//-----------------------------------------------------------------------------------------
	// Contstructor
	//-----------------------------------------------------------------------------------------
	public Radar(Frame parent)
	{
		super(parent, "Radar - Tyne", false); // Allow Tyne Sector to be replaced by other mapp 
		setBackground(Color.black);
		reshape(6,56,762,560);

		if (m_thread==null)	{
			m_thread = new Thread(this);
		}

		num_trail = 10 ;
		num_planes = 10 ;

		// create 2nd dimension of array
		for (int i=0; i<num_planes; i++)
		{
			plane_pos[i] = new Point(0,0);

			history[i] = new FloatPoint[num_trail] ;
			
			for (int a=0; a<num_trail; a++) 
			{
				history[i][a] = new FloatPoint();	
			}
		}
		
		
		
		window = bounds() ;
		scale = bounds();
		zoom = bounds();

		zoom.width = 120;
		zoom.height = 120 ;

		m_map = new Map(window,zoom);
	}

	//-----------------------------------------------------------------------------------------
	// public methods
	//-----------------------------------------------------------------------------------------

	// run
	//-----------------------------------------------------------------------------------------
	public void run()
	{
		while(true) 
		{
			if(!ok2paint) 
			{
				for (int pnum=0; pnum< num_planes; pnum++) 
				{
					plane_cord[pnum] = m_sky.getPlanePos(pnum);
					history[pnum][0] = plane_cord[pnum] ;

					plane_dir[pnum] = m_sky.getPlaneDir(pnum);


					int r_heading ;
					if(plane_dir[pnum].current_heading>180) {
						r_heading = plane_dir[pnum].current_heading - 180 ;
					}
					else
					{
						r_heading = plane_dir[pnum].current_heading + 180 ;
					}

					for(int num=0; num< num_trail-1; num++) 
					{
						float newx = (float)Math.sin(deg(r_heading)) * ((float)plane_dir[pnum].current_speed/60/60*6);
						float newy = (float)-Math.cos(deg(r_heading)) * ((float)plane_dir[pnum].current_speed/60/60*6);
	
						history[pnum][num+1].latitude = history[pnum][num].latitude + newx;
						history[pnum][num+1].longitude = history[pnum][num].longitude + newy;								
					}
				}
			ok2paint = true ;						
			}

			try 
			{
			
				//	repaint(plotPlane(plane_cord[i]).x-50,
				//			plotPlane(plane_cord[i]).y-50,
				//			150,
				//			100);
				
	
				// draws new planes pos
				repaint();
				

				// wait for radar sweep
				Thread.sleep(6000);
			}
			catch(InterruptedException e)
			{
				System.out.println(e);
			}

			for (int i=0; i< num_planes; i++) 
			{
					plane_cord[i] = m_sky.getPlanePos(i);
					history[i][0] = plane_cord[i] ;

					plane_dir[i] = m_sky.getPlaneDir(i);

					for (int aa=num_trail-2; aa>-1; aa--){
						history[i][aa+1] = history[i][aa] ;
					}
			}	
		}
	}


	// paint
	//-----------------------------------------------------------------------------------------
	public void paint(Graphics g) //synchronized 
	{
		scale = bounds();

		// keeps box square
		if(scale.width < scale.height)
		{
			window.height = scale.width ;
			window.width = scale.width ;
		}
		if(scale.height < scale.width)
		{
			window.width = scale.height ;
			window.height = scale.height ;
		}
		
		g.setColor(Color.green);	

		m_map.plotPoints(g);

		if (!m_thread.isAlive()) { m_thread.start(); }

		if (ok2paint) { 
		
		m_map.updateSizes(window,scale,zoom);

		for (int i=0; i< num_planes; i++) 
		{
			plane_pos[i] = plotPlane(plane_cord[i]);
			
			int x = plane_pos[i].x ;
			int y = plane_pos[i].y ;

			g.setColor(Color.green);

		if (m_sky.isPlaneVisable(i))
		{

			if (selected_plane_num == i) {
				g.drawRect(x-7,y-7,14,14);
			}
			
			// plane info
			g.drawString(m_sky.getPlaneDetails(i)[0],  x-4,  y-23); // plane name
			g.drawString(m_sky.getPlaneDetails(i)[1],  x-4,  y-10);			

			// star
			g.drawLine(x-4,	y-4,	x+4,	y+4);
			g.drawLine(x+4,	y-4,	x-4,	y+4);
			g.drawLine(x-4,	y,		x+4,	y);
			g.drawLine(x,	y-4,	x,		y+4);

			// history
			for (int aa=0; aa<num_trail; aa++){
				Point his = plotPlane( history[i][aa] );

				g.drawLine(his.x, his.y, his.x, his.y);
			}

			// upate history
			
	
			if(radius) {
				g.drawOval(x-(int)scale(5).longitude,
							y-(int)scale(5).latitude,
							(int)scale(10).longitude,
							(int)scale(10).latitude);
			}
	
			if(predict) {
				Point xy = new Point(0,0);
				int time = (m_sky.getPlaneSpeed(i)/60)*5 ;
				xy = plotDist(time,plane_dir[i].current_heading);
				g.drawLine(x,y,x+xy.x,y+xy.y);
			}

			if(cen_rings) {
				for(int j =0; j<20; j++)
				{
					g.drawOval((int)(m_map.cen.x - scale(j*5).latitude),
						(int)(m_map.cen.y - scale(j*5).longitude),
						(int)scale(j*10).latitude,
						(int)scale(j*10).longitude
					);
				}
			}
		}

		}
		}
	}



	// mouseDown
	//-----------------------------------------------------------------------------------------
	public boolean mouseDown(Event  evt, int  x, int  y) //synchronized 
	{
		if (evt.clickCount==2) // left click twice = plane deselect
		{
			selected_plane_num = -1 ;

			repaint();
			m_controls.noneSelected();
		}
		else if ((evt.clickCount==1) && (!evt.metaDown())) // left click once = plane select
		{
			m_dimCursorLoc = new Dimension(x,y);

	    	// loop all planes to see if mouseDown was on any of them
			for (int i=0; i< num_planes; i++)
			{
				int ppx = plane_pos[i].x;
				int ppy = plane_pos[i].y;

				// find out if mouse click was on or near a plane
				if (x > ppx -10 && x < ppx +10 &&
					y > ppy -10 && y < ppy +10)
				{
	
					m_controls.getData(plane_dir[i], i);
					
					selected_plane_num = i ;

					repaint();
				}
			}
		}
		else if ((evt.clickCount==1) && (evt.metaDown())) // right click once = popup
		{
			//popup.move(x,y);
		//	popup.show(this, x,y);
		}

	 	return true;
	}


	// setPointer
	//-----------------------------------------------------------------------------------------
	public void setPointer(Sky sky, ControlsFrame controls, ZoomFrame zbox)
	{
		m_sky = sky ;
		m_controls = controls;
		zoombox = zbox ;

		num_planes = m_sky.getnumPlanes();
	}

	// handleEvent
	//-----------------------------------------------------------------------------------------
	public boolean handleEvent(Event e)	{
		
		if (e.id == Event.WINDOW_DESTROY) {
			hide();
			dispose();
		}

		if (e.id == Event.WINDOW_ICONIFY) {
			System.out.println("Iconify");
		}
		if (e.id == Event.WINDOW_DEICONIFY) {
			System.out.println("DeIconify");
		}
		return super.handleEvent(e);
	}

	public void haveNewZoomSizes(int zoom_fac)
	{
		zoom.width = zoom_fac ;
		zoom.height = zoom_fac ;
		repaint();
	}

	//-----------------------------------------------------------------------------------------
	// private methods
	//-----------------------------------------------------------------------------------------

	// scale
	//-----------------------------------------------------------------------------------------
	private FloatPoint scale(float in_val)
	{
		FloatPoint temp = new FloatPoint();

		temp.latitude = (in_val * window.width)/zoom.width ;
		temp.longitude = (in_val * window.height)/zoom.height ;

		return temp ;
	}

	// plotPlane
	//-----------------------------------------------------------------------------------------
	private Point plotPlane(FloatPoint in)
	{
		FloatPoint temp = new FloatPoint();

		temp.latitude  = window.width/2 ;		
		temp.latitude += scale(60).latitude ;		
		temp.latitude += scale(in.latitude).latitude ;

		temp.longitude  = window.height/2 ;	
		temp.longitude += scale(60).longitude ;		
		temp.longitude += scale(in.longitude).longitude ;	

		Point return_temp = new Point(0,0);

		return_temp.x = (int) temp.latitude ;
		return_temp.y = (int) temp.longitude ;

		return return_temp ;
	}

	private Point plotDist(int miles, int bearing)
	{
		double dx = Math.sin(deg(bearing)) * (int)scale(miles).longitude;
		double dy = -Math.cos(deg(bearing)) * (int)scale(miles).latitude;
				
		Point temp = new Point((int)dx,(int)dy);

		return temp ;
	}

	private double deg(int rad) { return rad * Math.PI / 180;} // converts radions into degrees
}