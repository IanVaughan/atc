import java.awt.*;
import java.io.*;

class Map
{
	Rectangle window = null ;
	Rectangle scale = null ;
	Rectangle zoom = null ;

	Point cen = null ;
	Point cur_pos = null ;
	MapPoints[] mps = null ;
	Point plot_point = null ;

	public Map(Rectangle window_size, Rectangle zoom_fac)
	{	
		window = new Rectangle();
		scale = new Rectangle() ;
		zoom = new Rectangle() ;

		cen = new Point(0,0);
		cur_pos = new Point(0,0);
		mps = new MapPoints[200] ;
		plot_point = new Point(0,0); 

		window = window_size; 
		zoom = zoom_fac ;
	
		cen.x = window.width/2 ;
		cen.y = window.height/2 ;
		
		getStuffFromFile();
	}


	public void updateSizes(Rectangle new_sizes, Rectangle scale_in, Rectangle zoom_in)
	{
		window = new_sizes;
		
		cen.x = window.width/2 ;
		cen.y = window.height/2 ;

	    scale = scale_in ;
		zoom = zoom_in ;
	}

	
	void setCur(int x, int y)
	{
		cur_pos.x = x ;
		cur_pos.y = y ;	
	}
	void cenCur()
	{
		setCur(cen.x, cen.y);
	}
	void moveCur(int miles, int bearing)
	{
		Point temp = new Point(0,0);
		temp = plotDist(miles,bearing);

		cur_pos.x = cen.x + temp.x ;
		cur_pos.y = cen.y + temp.y ;
	}
	void plot(Graphics g, int miles, int bearing)
	{
		Point temp = new Point(0,0);
		temp = plotDist(miles,bearing);

		g.drawLine(cur_pos.x, cur_pos.y, cen.x + temp.x, cen.y + temp.y);

		moveCur(miles,bearing);
	}
	public Point plotDist(int miles, int bearing)
	{
		double dx = Math.sin(deg(bearing)) * scaleX(miles);
		double dy = -Math.cos(deg(bearing)) * scaleY(miles);
				
		Point temp = new Point((int)dx,(int)dy);

		return temp ;
	}

	private double deg(int rad) { return rad * Math.PI / 180;} // converts radions into degrees

	private int scaleX(int in_val)
	{
		return (in_val * window.width)/zoom.width ;
	}
	private int scaleY(int in_val)
	{
		return (in_val * window.height)/zoom.height ;
	}


	void plotPoints(Graphics g)
	{
		setCur(cen.x,cen.y);

		Point temp = new Point(0,0);

		for (int t=0; t<=MapPoints.points-1 ;t++)
		{
			if (mps[t].what == 'T')
			{
				g.setColor(Color.green);

				temp = plotDist((int)mps[t].miles, (int)mps[t].bearing) ;

				temp.x += cen.x;
				temp.y += cen.y;

				g.drawString(mps[t].text,temp.x, temp.y);
			}
			
			if (mps[t].what == 'M')
			{
				moveCur((int)mps[t].miles, (int)mps[t].bearing);
			}
			
			if (mps[t].what == 'L')
			{
				g.setColor(Color.green);

				plot(g, (int)mps[t].miles, (int)mps[t].bearing);
			}

			if (mps[t].what == 'A')
			{
				g.setColor(Color.green);

				temp = plotDist((int)mps[t].miles, (int)mps[t].bearing) ;

				temp.x += cen.x;
				temp.y += cen.y;

				g.drawOval(temp.x, temp.y, 5,5);
			}

			if (mps[t].what == 'D')
			{
				g.setColor(Color.darkGray);
				plot(g, (int)mps[t].miles, (int)mps[t].bearing);
			}

			if (mps[t].what == 'O')
			{
				g.setColor(Color.darkGray);
				temp = plotDist((int)mps[t].miles, (int)mps[t].bearing) ;

				temp.x += cen.x;
				temp.y += cen.y;

				int x = scaleX(4);
				int y = scaleY(4);

				g.drawOval(temp.x-x/2, temp.y-y/2, x,y);
			}

			if (mps[t].what == 'F')
			{
				g.setColor(Color.green);

				temp = plotDist((int)mps[t].miles, (int)mps[t].bearing) ;

				temp.x += cen.x;
				temp.y += cen.y;

				int x = scaleX(1);
				int y = scaleY(1);

				g.drawLine(temp.x-x,temp.y,temp.x+x,temp.y);
				g.drawLine(temp.x,temp.y-y,temp.x,temp.y+y);
			}
		}
	}

	void getStuffFromFile()
	{
		RandomAccessFile map_file ;
	
		int line_counter = -1;

		try
		{
			map_file = new RandomAccessFile("data/tyne.map","r");

			String text_line = "";
			line_counter = -1;
			
			char[] achar;

			text_line = map_file.readLine();

			do
			{
				String ans = "";
				line_counter ++ ;
				int part = 1 ;
				mps[line_counter] = new MapPoints() ;	
				
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
					
						if(part==3)
						{
							mps[line_counter].miles = (Float.valueOf(ans)).floatValue();
						}
						if(part==2)
						{
							mps[line_counter].bearing = (Float.valueOf(ans)).floatValue();
						}	
						if(part==4)
						{
							mps[line_counter].text = ans ;
						}
						part++ ;
						ans = "";
					}
					else
					if(Character.isLetter(achar[char_counter]) && char_counter == 0)
					{	
						mps[line_counter].what = achar[char_counter] ;
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
}
	
