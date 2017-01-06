import java.awt.*;

class AtcOptions
{
	int num_trail ;
	
	Windows windows ;
	Colors colors ;

	public AtcOptions()
	{
	//	System.out.println("Load windows data here....");
	}

	public String toString()
	{
		return "i" ;
	}
}


class Colors
{
	Color map ;
	Color blip ;
	Color background;
}

class Windows
{
	Rectangle radar ;
	Rectangle controls ;
	Rectangle clock ;
	Rectangle fps ;
	Rectangle zoom ;
}
