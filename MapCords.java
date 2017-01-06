class MapCords
{
	static int num;
	char what;

	float f_longitude ;
	char c_longitude ;

	float f_latitude ;
	char c_latitude ;

	String text ;

	public MapCords()
	{
		num ++ ;
	}

	public String toString()
	{
		String s ;

		s= getClass().getName() + "[" ;
		s+= "what :" + what ;
		s+= ",longitude :" + f_longitude + c_longitude ;
		s+= ",latitude :" + f_latitude + c_latitude;
		s+= ",text :" + text + "]";

		return s ;
	}
}