//========================================================================================
//	
//	Planes.java
//
//	inits the all the planes
//	
//========================================================================================  
import java.io.*;
import java.awt.*;

class Sky
{
	Plane[] m_plane = new Plane[10];  // allocate planes objects

	private MapCords mcrds[] = new MapCords[100] ;

	private int num_planes ;

	//-----------------------------------------------------------------------------------------
	// Constructor
	//-----------------------------------------------------------------------------------------
	public Sky(String situation_fileName)
	{	
		// load in all lats and longs form file
		getCordsFromFile("data/tyne_cords.map");

		// load in starting plane data
		getSimDataFromFile(situation_fileName) ;
	}

	//-----------------------------------------------------------------------------------------
	// public methods
	//-----------------------------------------------------------------------------------------

	public int getPlaneSpeed(int plane_num) 
	{
		return m_plane[plane_num].getPlaneSpeed();
	}

	// getnumPlanes
	//-----------------------------------------------------------------------------------------
	public int getnumPlanes()
	{
		return Plane.getNumPlanes();
	}
	
	// getPlanePos
	//-----------------------------------------------------------------------------------------
	public FloatPoint getPlanePos(int plane_num)
	{
		return m_plane[plane_num].getPlanePos() ;
	}
	
	// getPlaneDetails
	//-----------------------------------------------------------------------------------------
	public String[] getPlaneDetails(int plane_num)
	{
		return m_plane[plane_num].getInfo() ;
	}
	
	// getPlaneDir
	//-----------------------------------------------------------------------------------------
	public PlaneDirection getPlaneDir(int num)
	{
		return m_plane[num].getPlaneDir();
	}

	// updatePlane
	//-----------------------------------------------------------------------------------------
	public void updatePlane(int which_plane, int fh, int fl, boolean right_turn, boolean expedite) // called from commands. were sent the changed planeData
	{		
	}	//2272

	public void updataPlaneFH(int which_plane, int fh, boolean right_turn)
	{	
		m_plane[which_plane].changeHeading(fh,right_turn) ; //cl:11123
	}

	public void updatePlaneFL(int which_plane, int fl, boolean expedite)
	{	
		m_plane[which_plane].changeFl(fl,expedite) ; 
	}

	// startPlanes
	//-----------------------------------------------------------------------------------------
	public void startPlanes()		// called from clock when button start is pressed
	{
		for (int i=0; i<num_planes; i++){
			if (m_plane[i].isAlive()) {
				m_plane[i].resume(); // start if been suspended (paused from clock)
			}
			else {
				m_plane[i].start();	// start for first time (start button from clock)
			}
		}
	}

	// pausePlanes
	//-----------------------------------------------------------------------------------------
	public void pausePlanes()		// called from clock when button pause is pressed
	{
		for (int i=0; i<num_planes; i++){
			m_plane[i].suspend();
		}
	}



	//-----------------------------------------------------------------------------------------
	// private methods
	//-----------------------------------------------------------------------------------------


	// toInt string
	//-----------------------------------------------------------------------------------------
	private int toInt(String sin)
	{
		int i =  Integer.valueOf(
					String.valueOf(
						sin.toCharArray(),
						0,
						sin.length()-1)
					).intValue();
		return i;
	}

	// toInt char
	//-----------------------------------------------------------------------------------------
	private int toInt(char char_in)
	{// converts a char read from in from a file, to an Integer then to an int????
		return Integer.valueOf( String.valueOf(char_in) ).intValue();
	}

	// removedEnd
	//-----------------------------------------------------------------------------------------
	private String removeEnd(String sin)
	{
		String s = 	String.valueOf(
						sin.toCharArray(),
						0,
						sin.length()-1);
		return s;
	}
	
	
	// getCordsFromFile
	//-----------------------------------------------------------------------------------------
	private void getCordsFromFile(String filename)
	{
		RandomAccessFile map_file ;
	
		int line_counter = -1;

		try
		{
			map_file = new RandomAccessFile(filename,"r");

			String text_line = "";
			line_counter = -1;
			
			char[] achar;

			text_line = map_file.readLine();

			do
			{
				String ans = "";
				line_counter ++ ;
				int part = 1 ;
				mcrds[line_counter] = new MapCords() ;	
				
				achar = text_line.toCharArray();

				int char_counter = 0 ;
	
				while(char_counter < text_line.length())
				{
					if(Character.isSpace(achar[char_counter]) ||
						achar[char_counter] == '\r' ||
						achar[char_counter] == '\f' ||
						achar[char_counter] == '\n')
					{		
						char_counter ++;
					
						if(part==2)
						{
							char[] charArray = ans.toCharArray() ;
							int deg = (toInt(charArray[0])*10) 
										+ toInt(charArray[1]) ;

							int min = (toInt(charArray[3])*10)
										+ toInt(charArray[4]) ;

							int sec = (toInt(charArray[6])*1000)
									+ (toInt(charArray[7])*100) 
									+ (toInt(charArray[8])*10) 
									+ toInt(charArray[9]) ;

							double nms = (deg*60) + min  ;
						
							mcrds[line_counter].f_longitude = (float)nms ;
							mcrds[line_counter].c_longitude = charArray[10];
						}	
						if(part==3)
						{
							char[] charArray = ans.toCharArray() ;
							int deg = (toInt(charArray[0]) * 100)
									+ (toInt(charArray[1]) * 10) 
									+ toInt(charArray[2]) ;

							int min = (toInt(charArray[4]) * 10)
									+ toInt(charArray[5]) ;

							int sec = (toInt(charArray[7]) * 1000)
									+ (toInt(charArray[8]) * 100) 
									+ (toInt(charArray[9]) * 10) 
									+ toInt(charArray[10]) ;

							double nms = (deg*60) 
									   + min ;

							mcrds[line_counter].f_latitude = (float)nms ;
							mcrds[line_counter].c_latitude = charArray[11];
						}
						if(part==4)
						{
							mcrds[line_counter].text = ans ;
						}

						part++ ;
						ans = "";
					}
					else
					if(Character.isLetter(achar[char_counter]) && char_counter == 0)
					{	
						mcrds[line_counter].what = achar[char_counter] ;
						char_counter ++;
					}
					else
					if(Character.isLetter(achar[char_counter]))
					{
						ans += String.valueOf(achar[char_counter]);
						char_counter ++;
					}
					else
					{
						ans += String.valueOf(achar[char_counter]);
						char_counter ++;
					}
				}

				text_line = map_file.readLine();
			}while(text_line != null);
		
			map_file.close();
		}
		catch(java.io.IOException ioe)
		{	
			System.out.println(ioe);
		}
	}

	
	// getSimDataFromFile
	//-----------------------------------------------------------------------------------------	
	private void getSimDataFromFile(String situation_fileName)
	{
		AircraftSituationData asd = new AircraftSituationData();
		RandomAccessFile st_file ;
		
		try
		{
			st_file = new RandomAccessFile(situation_fileName,"r");
			String blank ;

			do
			{
				asd.callsign= removeEnd(st_file.readLine());	
				asd.start_loc = removeEnd(st_file.readLine());
				asd.fin_loc = removeEnd(st_file.readLine());
				asd.icao = removeEnd(st_file.readLine());

				asd.start_fl = toInt(st_file.readLine());
				asd.fin_fl = toInt(st_file.readLine());
				asd.start_delay = toInt(st_file.readLine());

				asd.route1 = removeEnd(st_file.readLine());
				asd.route2 = removeEnd(st_file.readLine());
				asd.route3 = removeEnd(st_file.readLine());
				asd.opperator = removeEnd(st_file.readLine());
				blank = st_file.readLine();

				// find from mapcords the ref which this plane wants to start from
		
				MapCords start_mpcrds = null ;
				MapCords fin_mpcrds = null ;

				boolean found = false ;
				int counter = 0;
				
				do
				{
					if( mcrds[counter].text.compareTo(asd.start_loc) == 0 )
					{
						start_mpcrds = mcrds[counter] ;
						counter = 0;

						do
						{
							if( mcrds[counter].text.compareTo(asd.fin_loc) == 0 )
							{
								fin_mpcrds = mcrds[counter] ;

								m_plane[num_planes] = new Plane(asd, start_mpcrds);
								num_planes ++ ;
								found = true ;
							}
							counter ++;
						}
						while(!found && counter < 100);
					}
					
					counter ++ ;
				}
				while(!found && counter < 100);

				// create a plane with this data
				
			}
			while(blank != null);
			
			st_file.close();
		}
		catch(java.io.IOException ioe)
		{
			System.out.println(ioe);
		}
	}
}