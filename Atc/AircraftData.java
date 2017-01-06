class AircraftData
{
	String ICAO = "";
	int cruise_speed = 0;
	int speed_in_climb = 0;
	int speed_in_descent = 0;
	int[] climb_rate = new int[4];
	int[] descent_rate = new int[4];
	int[] expedited_climb_rate = new int[4];
	int[] expedited_descent_rate = new int[4];
	int delay2climb = 0;
	int delay2descend = 0;
	int delay2cruise_climb= 0; 
	int delay2cruise_descend = 0;
	String fuselarge = "";
	int passengers = 0;
	String weight_vortex = "";
	int engines = 0;
	String engine_type ="";
	String name="";
	String manufacture ="";
	String picture_filename="";

	public String toString()
	{
		String s ;
		
		s = getClass().getName() + "[" ;
		s+= "\n ICAO :" + ICAO;
		s+= "\n cruise speed :" + cruise_speed;
		s+= "\n speed in climb :" + speed_in_climb;
		s+= "\n speed in decsent :" + speed_in_descent;
		s+= "\n climb rate :" + climb_rate;
		s+= "\n descent rate :" + descent_rate ;
		s+= "\n expedited climb rate :" + expedited_climb_rate ;
		s+= "\n expedited descent rate :" + expedited_descent_rate ;
		s+= "\n delay to clomb :" + delay2climb;
		s+= "\n delay to descend :" + delay2descend;
		s+= "\n delay to cruise from climb :" + delay2cruise_climb;
		s+= "\n delay to cruise from descend :" + delay2cruise_descend;
		s+= "\n !!fuselarge :" + fuselarge ;
		s+= "\n number of passengers :" + passengers;
		s+= "\n weight vortex type :" + weight_vortex;
		s+= "\n number of engines :" + engines;
		s+= "\n engin type :" + engine_type;
		s+= "\n full name :" + name;
		s+= "\n manufacture :" + manufacture ;
		s+= "\n picture filename :" + picture_filename;

		return s;
	}
}