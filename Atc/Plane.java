//-----------------------------------------------------------------------------------------
//
//		Plane.java
//
//-----------------------------------------------------------------------------------------

import java.awt.*;

//-----------------------------------------------------------------------------------------
// Plane class
//-----------------------------------------------------------------------------------------
class Plane implements Runnable 
{
	private static int plane_num ;
	private Thread m_thread = null;

	AircraftData acd = null ; // actual plane data
	PlaneCord current_plane_cord = null ;
	PlaneDirection plane_dir = null ;
	AircraftSituationData asd;
	
	boolean right_turn;

	int number ;
	int second_counter =0 ;

	//-----------------------------------------------------------------------------------------
	// Constructor
	//-----------------------------------------------------------------------------------------
	public Plane(AircraftSituationData asd_in, MapCords start_mcrd)
	{
		plane_num ++; // inc static count
		
		asd = new AircraftSituationData();

		asd.callsign = asd_in.callsign;
		asd.start_loc = asd_in.start_loc;
		asd.fin_loc = asd_in.fin_loc;
		asd.icao = asd_in.icao;
		asd.start_fl = asd_in.start_fl;
		asd.fin_fl = asd_in.fin_fl;
		asd.start_delay = asd_in.start_delay;
		asd.route1 = asd_in.route1;
		asd.route2 = asd_in.route2;
		asd.route3 = asd_in.route3;
		asd.opperator = asd_in.opperator;

		// create planeData with the icao code
		LoadPlaneData lpd = new LoadPlaneData();
		acd = lpd.getAircraftData(asd_in.icao) ;

		// copy cord pos from mapCord into current pos of planeCord
		current_plane_cord = new PlaneCord(start_mcrd);
	
	
		// setup plane direction
		plane_dir = new PlaneDirection() ;
		plane_dir.plane_name = asd.callsign ;
		plane_dir.current_fl = asd.start_fl ;
		plane_dir.current_heading = findAngle(asd.start_loc, asd.fin_loc);
		plane_dir.current_speed = acd.cruise_speed ;
		plane_dir.told_to_fl = (int)plane_dir.current_fl ;
		plane_dir.told_to_heading = plane_dir.current_heading ;
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
			try	
			{
				Thread.sleep(1000); 
				second_counter ++ ;
				
				if(second_counter > asd.start_delay) 
				{
					plane_dir.visable = true ;
			
					check_fl(); 

					turn();

					move_blip();
				}
			}
			catch(InterruptedException e)
			{
			}
		}
	}

	// resume
	//-----------------------------------------------------------------------------------------
	public void resume()
	{
		if(m_thread != null) {
			m_thread.resume();
		}
	}

	// isAlive
	//-----------------------------------------------------------------------------------------
	public boolean isAlive()
	{
		if(m_thread != null) {
			return m_thread.isAlive();
		}
		else
		return false ;
	}

	// suspend
	//-----------------------------------------------------------------------------------------
	public void suspend()
	{
		if(m_thread != null) {
			m_thread.suspend();
		}
	}

	// start
	//-----------------------------------------------------------------------------------------
	public void start()
	{
		if (m_thread==null)
		{
			m_thread = new Thread(this);
			m_thread.start();
		}
	}
	
	// stop
	//-----------------------------------------------------------------------------------------
	public void stop()
	{
		if (m_thread != null)
			m_thread.stop();
		m_thread=null;
	}

	public int getPlaneSpeed()
	{
		return plane_dir.current_speed ;
	}

	// getPlanePos
	//-----------------------------------------------------------------------------------------
	public FloatPoint getPlanePos()
	{
		return current_plane_cord.getValues() ;
	}

	// changeHeading
	//-----------------------------------------------------------------------------------------
	public void changeHeading(int heading, boolean right_turn)
	{
		plane_dir.told_to_heading = heading ;
		this.right_turn = right_turn ;
	}

	// changeFl
	//-----------------------------------------------------------------------------------------
	public void changeFl(int fl, boolean expedite)
	{
		plane_dir.told_to_fl = fl;
		plane_dir.expedite = true ;
	}

	// getPlaneDir
	//-----------------------------------------------------------------------------------------
	public PlaneDirection getPlaneDir()
	{
		return plane_dir ;
	}

	// toString
	//-----------------------------------------------------------------------------------------
	public String[] getInfo() 
	{
		String s[] = new String[2];  

		s[0] = plane_dir.plane_name ;

		if(plane_dir.current_fl<10)
		{
			s[1] = "00" + String.valueOf((int)plane_dir.current_fl);
		} 
		else if(plane_dir.current_fl<100)
		{
			s[1] = "0" + String.valueOf((int)plane_dir.current_fl);
		} 
		else 
			s[1] = String.valueOf((int)plane_dir.current_fl); 

		s[1]+= "   ";
		s[1]+= asd.route3.substring(2) ;
		s[1]+= "  [debug:fh-";
		s[1]+= String.valueOf(plane_dir.current_heading);
		s[1]+= ",speed-" ;
		s[1]+= String.valueOf(plane_dir.current_speed);
		s[1]+= "]" ;
				
		return s;
	}


	//-----------------------------------------------------------------------------------------
	// private methods
	//-----------------------------------------------------------------------------------------

	private int which ;
	private float c_s = (float)0.0003 ; //climb step
	private float d_s = (float)0.0003 ; //descent step
	float c_fl = (float)0.0 ;
		
	// check fl
	//-----------------------------------------------------------------------------------------
	private void check_fl()
	{
		c_fl = plane_dir.current_fl ;
		
		if(c_fl <= 100) {
			which = 0 ;
		}
		else if(c_fl > 100 && c_fl <= 200) {
			which = 1 ;
		}
		else if (c_fl > 200 && c_fl <= 300) {
			which = 2 ;
		}
		else if (c_fl > 300 && c_fl <= 400) {
			which = 3 ;
		}

		if(c_fl <= plane_dir.told_to_fl)
		{
			climb();
		}
		else if (c_fl > plane_dir.told_to_fl)
		{
			descend();
		}
	}
	
	private void climb()
	{
		float c_fl = plane_dir.current_fl ;
		float t_fl = plane_dir.told_to_fl ;

		float t1;
		if(plane_dir.expedite) {
			t1 = acd.expedited_climb_rate[which];
		}
		else {
			t1 = acd.climb_rate[which];
		}
		float t2 = t1/100;
		float m_c_s = t2/60 ;
		
		if(c_fl < t_fl) 
		{
			c_fl += c_s ;

			if(c_fl > t_fl) // climb over (level)
			{
				c_fl = t_fl ;
				c_s = (float)0.0003 ;
				plane_dir.current_speed = acd.cruise_speed ;
			}
			else if(t_fl -2 < c_fl) // end climb
			{
				c_s /= 1.1 ;
				if(c_s <= (float)0.0003) 
				{
					c_s = (float)0.0003 ;
					plane_dir.current_speed = acd.cruise_speed ;
				}

				plane_dir.current_speed += 2;				
				if (plane_dir.current_speed > acd.cruise_speed) {
					plane_dir.current_speed = acd.cruise_speed ;
				}
			}
			else 
			{
				c_s *= 2 ; // increase climb
				if(c_s > m_c_s) // constant climb
				{
					c_s = m_c_s ;
					plane_dir.current_speed = acd.speed_in_climb ;
				}

				plane_dir.current_speed -= 2;
				if (plane_dir.current_speed < acd.speed_in_climb) {
					plane_dir.current_speed = acd.speed_in_climb ;
				}
			}
		}

		plane_dir.current_fl = c_fl ;
	}

	private void descend()
	{	
		float c_fl = plane_dir.current_fl ;
		float t_fl = plane_dir.told_to_fl ;

		float t1;
		if(plane_dir.expedite) {
			t1 = acd.expedited_descent_rate[which] ;
		}
		else {
			t1 = acd.descent_rate[which] ;
		}
		
		float t2 = t1/100 ;
		float m_d_s = t2/60 ;

		if(c_fl > t_fl) 
		{
			c_fl -= d_s  ;

			if(c_fl < t_fl) // desc over
			{
				c_fl = t_fl ;
				d_s = (float)0.0003;
				plane_dir.current_speed = acd.cruise_speed ;
			}
			else if(t_fl+3 > c_fl) // end desc
			{
				d_s /= 1.1 ;
				if(d_s <= (float)0.0003)
				{
					d_s = (float)0.0003 ;
					plane_dir.current_speed = acd.cruise_speed ;
				}
				
				plane_dir.current_speed += 2;
				if (plane_dir.current_speed > acd.cruise_speed) {
					plane_dir.current_speed = acd.cruise_speed ;
				}
			}
			else
			{
				d_s *= 1.5 ; // increase desc
				if(d_s > m_d_s) // constant desc
				{
					d_s = m_d_s ;
					plane_dir.current_speed = acd.speed_in_descent ;
				}
			
				plane_dir.current_speed -= 2;				
				if (plane_dir.current_speed < acd.speed_in_descent) {
					plane_dir.current_speed = acd.speed_in_descent ;
				}
			}
		}

		plane_dir.current_fl = c_fl ;
	}

	int turn_rate = 0;

	// turn
	//-----------------------------------------------------------------------------------------
	private void turn()
	{
		if(plane_dir.told_to_heading != plane_dir.current_heading)
		{
			if(turn_rate <3) { turn_rate ++ ; }

			if (right_turn) 
			{	
				int diff =  plane_dir.current_heading - plane_dir.told_to_heading;
				if (diff <-turn_rate || diff > 0) //
				{
					plane_dir.current_heading += turn_rate;
				}
				else
				{
					plane_dir.current_heading = plane_dir.told_to_heading;
					turn_rate = 0;
				}
				if(plane_dir.current_heading > 360)
				{
					plane_dir.current_heading -= 360;
				}
			}
		
			else if (!right_turn)
			{
				int diff = plane_dir.current_heading - plane_dir.told_to_heading;
				if (diff <0 || diff > turn_rate) //
				{
					plane_dir.current_heading -= turn_rate;
				}
				else
				{
					plane_dir.current_heading = plane_dir.told_to_heading;
					turn_rate = 0;
				}
				if(plane_dir.current_heading < 0)
				{
					plane_dir.current_heading += 360;
				}
			}
		}
			
	}

	// move_blip
	// ---------------------------------------------------------------------------------------
	private void move_blip()
	{		
		double current_speed_tas = convert_to_tas(plane_dir.current_speed,c_fl) ; //convert from acd.cruise_speed (ias) to plane_dir... (tas)
		float newx = (float)Math.sin(deg(plane_dir.current_heading)) * ((float)current_speed_tas/60/60);
		float newy = (float)-Math.cos(deg(plane_dir.current_heading)) * ((float)current_speed_tas/60/60);

		current_plane_cord.latitude += newx;
		current_plane_cord.longitude += newy;
	}
	
	private int convert_to_tas(int ias, float c_fl)
	{
		Double convert = new Double(ias + ((ias * 1.75/100) * c_fl/10));
		return convert.intValue();
	}

	// findAngle
	//-----------------------------------------------------------------------------------------
	private int findAngle(String start, String fin)
	{
		if (start.compareTo("CHERY") == 0 && fin.compareTo("STONE") == 0)
		{
			return 85;
		}
		else if (start.compareTo("DAR") == 0 && fin.compareTo("TYN") == 0)
		{
			return 260;
		}
		else if (start.compareTo("HAYLE") == 0 && fin.compareTo("STONE") == 0)
		{
			return 60;
		}
		else if (start.compareTo("RYE") == 0 && fin.compareTo("TYN") == 0)
		{
			return 135;
		}
		else if (start.compareTo("SHARK") == 0 && fin.compareTo("TYN") == 0)
		{
			return 315;
		}
		else if (start.compareTo("STONE") == 0 && fin.compareTo("CHARD") == 0)
		{
			return 80;
		}
		else return 0 ;
	}


	// deg
	//-----------------------------------------------------------------------------------------
	private double deg(int rad) { return rad * Math.PI / 180;} // converts radions into degrees

	//-----------------------------------------------------------------------------------------
	// static methods	
	//-----------------------------------------------------------------------------------------

	// getNumPlanes
	//-----------------------------------------------------------------------------------------
	static public int getNumPlanes() 
	{
		return plane_num ;
	}	
}