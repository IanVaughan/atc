class PlaneDirection
{
	float current_fl;
	int current_heading;
	int current_speed;
	int told_to_fl;
	int told_to_heading;
	boolean expedite = false;
	String plane_name;
	boolean visable = false ;

	public String toString()
	{
		String s ;
		
		s = getClass().getName() + "[" ;
		s+= "current_fl=" + current_fl ;
		s+= ",current_heading=" + current_heading ;
		s+= ",current_speed="+ current_speed ;
		s+= ",told_to_fl=" + told_to_fl ;
		s+= ",told_to_heading=" + told_to_heading ;
		s+= ",expedite=" + expedite ;
		s+= ",plane_name=" + plane_name ;
		s+= ", visable=" + "]";

		return s ;
	}
}