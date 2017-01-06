class MapPoints
{
	static int points;
	char what;
	float bearing;
	float miles;
	String text ;

	public MapPoints()
	{
		points ++ ;
	}

	public String toString()
	{
		String s ;

		s= getClass().getName() + "[" ;
		s+= "what :" + what ;
		s+= ",bearing :" + bearing ;
		s+= ",miles :" + miles ;
		s+= ",text :" + text + "]";

		return s ;
	}
}
