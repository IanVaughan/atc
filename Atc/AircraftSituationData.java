class AircraftSituationData
{
	String callsign;
	String start_loc;
	String fin_loc;
	String icao ;
	int start_fl;
	int fin_fl ;
	int start_delay;
	String route1;
	String route2;
	String route3;
	String opperator;

	public String toString()
	{
		String s ;
		s = getClass().getName() + "[" ;
		s+= "\n Callsign :" +callsign ;
		s+= "\n	Start loc :"+start_loc ;
		s+= "\n	Fin loc :"+fin_loc ;
		s+= "\n	ICAO :"+icao ;
		s+= "\n	Start flight level :"+start_fl;
		s+= "\n	Finish flight level :"+fin_fl;
		s+= "\n	Starting delay :"+start_delay;
		s+= "\n	Route 1 :"+route1;
		s+= "\n	Route 2 :"+route2;
		s+= "\n	Route 3 :"+route3;
		s+= "\n	Opperator :"+opperator;
		return s;
	}
}
