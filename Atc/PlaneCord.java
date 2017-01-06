class PlaneCord
{
	float longitude ;
	float latitude ;

	
	public PlaneCord()
	{
	}

	public PlaneCord(MapCords in)
	{
		if(in.c_latitude == 'E')
		{
			latitude = in.f_latitude ;
		}
		else
		{
			latitude = -in.f_latitude ;
		}

		if(in.c_longitude == 'N')
		{
			longitude = -in.f_longitude ;
		}
		else
		{
			longitude = in.f_longitude ;
		}
	}

//	public void setCords(Point vals)
//	{
//		latitude = vals.x ;
//		longitude = vals.y;
//	}

	public FloatPoint getValues()
	{
		FloatPoint temp = new FloatPoint() ;
		
		temp.latitude = latitude;
		temp.longitude = longitude;

		return temp;
	}

	public String toString()
	{
		String s ;

		s= getClass().getName() + "[" ;
		s+= "longitude :" + longitude ;
		s+= ",latitude :" + latitude + "]" ;

		return s ;
	}
}