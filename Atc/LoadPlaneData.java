//-----------------------------------------------------------------------------------------
//
//		LoadPlaneData.java
//
//      Loads the plane from the data file by searching on the ICAO
//		then puts all the data in AircraftData class and sends it back
//-----------------------------------------------------------------------------------------

import java.io.*;
//-----------------------------------------------------------------------------------------
// LoadPlaneData class
//-----------------------------------------------------------------------------------------
class LoadPlaneData
{

	// getAircraftData
	//-----------------------------------------------------------------------------------------
	public AircraftData getAircraftData(String icao)
	{	
		AircraftData plane_data = new AircraftData();

		RandomAccessFile pd_file ;

		try
		{
			pd_file = new RandomAccessFile("data/pd.acd","r");
			
			boolean found = false ;
			String text_line;

			do
			{
				text_line = removeEnd(pd_file.readLine());

				if (text_line.compareTo(icao) == 0) {
					found = true ;
				}
			}
			while((text_line != null) && (!found));
			
			if(found){
			do
			{
				plane_data.ICAO = icao;
				plane_data.cruise_speed = toInt(pd_file.readLine());
				plane_data.speed_in_climb = toInt(pd_file.readLine());
				plane_data.speed_in_descent = toInt(pd_file.readLine());

				plane_data.climb_rate[0] = toInt(pd_file.readLine());
				plane_data.climb_rate[1] = toInt(pd_file.readLine());
				plane_data.climb_rate[2] = toInt(pd_file.readLine());
				plane_data.climb_rate[3] = toInt(pd_file.readLine());
				plane_data.descent_rate[0] = toInt(pd_file.readLine());
				plane_data.descent_rate[1] = toInt(pd_file.readLine());
				plane_data.descent_rate[2] = toInt(pd_file.readLine());
				plane_data.descent_rate[3] = toInt(pd_file.readLine());
				plane_data.expedited_climb_rate[0] = toInt(pd_file.readLine());
				plane_data.expedited_climb_rate[1] = toInt(pd_file.readLine());
				plane_data.expedited_climb_rate[2] = toInt(pd_file.readLine());
				plane_data.expedited_climb_rate[3] = toInt(pd_file.readLine());
				plane_data.expedited_descent_rate[0] = toInt(pd_file.readLine());
				plane_data.expedited_descent_rate[1] = toInt(pd_file.readLine());
				plane_data.expedited_descent_rate[2] = toInt(pd_file.readLine());
				plane_data.expedited_descent_rate[3] = toInt(pd_file.readLine());

				plane_data.delay2climb = toInt(pd_file.readLine());
				plane_data.delay2descend = toInt(pd_file.readLine());
				plane_data.delay2cruise_climb = toInt(pd_file.readLine());
				plane_data.delay2cruise_descend = toInt(pd_file.readLine());
				plane_data.fuselarge  = removeEnd(pd_file.readLine());
				plane_data.passengers = toInt(pd_file.readLine());
				plane_data.weight_vortex = removeEnd(pd_file.readLine());
				plane_data.engines = toInt(pd_file.readLine());
				plane_data.engine_type = removeEnd(pd_file.readLine());
				plane_data.name = removeEnd(pd_file.readLine());
				plane_data.manufacture  = removeEnd(pd_file.readLine());
				plane_data.picture_filename = removeEnd(pd_file.readLine());
				found = false ;
			}
			while(text_line != null && found);
			}
			else
			{
				System.out.println("Plane not found in file!!!");
			}

			pd_file.close();
		}
		catch(java.io.IOException ioe)
		{	
			System.out.println(ioe);
		}
		return plane_data ;
	}


	// toInt
	//-----------------------------------------------------------------------------------------
	public int toInt(String sin)
	{
		int i =  Integer.valueOf(
					String.valueOf(
						sin.toCharArray(),
						0,
						sin.length()-1)
					).intValue();
		return i;
	}

	// removeEnd
	//-----------------------------------------------------------------------------------------
	public String removeEnd(String sin)
	{
		String s = 	String.valueOf(
						sin.toCharArray(),
						0,
						sin.length()-1);
		return s;
	}
}
