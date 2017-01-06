//-----------------------------------------------------------------------------------------
//
//		Atc.java
//
//-----------------------------------------------------------------------------------------
import java.io.*;
import java.awt.*;
import AtcMain; // dont need to import a class in the project

//-----------------------------------------------------------------------------------------
// Atc class
//-----------------------------------------------------------------------------------------
public class Atc
{
	public static void main(String args[])
	{
		String file_name = null ;
		int screen_res = Toolkit.getDefaultToolkit().getScreenResolution();
		Dimension screen_size =  Toolkit.getDefaultToolkit().getScreenSize();
	
		System.out.println("Air Traffic Control Demo program (ver 42.4)");
		System.out.println("Written for CATC Bournemouth\n");
		System.out.println("Written by PC(Programming Concepts) Ltd. (c) 1993 - 1998\n\n");
			
		System.out.println("The screen resolution is :"+screen_res+"dpi");
		System.out.println("The screen size is :"+screen_size.width+"x"+screen_size.height+"\n\n");

		
		if(args.length > 0) {

			if(args[0].compareTo("/?") == 0) {
				System.out.println("Help!!");
				System.out.println("Type :-jview Atc <filename>\n");
				System.out.println(" filename can be of either type .sts or .stl.\n");
				System.out.println("Thanks for using Help!!");
			}

			else
			{
				file_name = args[0] ;
				RandomAccessFile poem_file ;
	
				try
				{
					poem_file = new RandomAccessFile(file_name,"r");

					System.out.println("Found file :"+file_name);
			
					poem_file.close();

					Frame f = new AtcMain(file_name, screen_size); //screen_size
				}
				catch(java.io.IOException ioe)
				{	
					System.out.println("Error - File not found, check file name and path, then try again.");
				}
			}
		}
		else 
		{
			System.out.println("No file name gived.");
		} 
	}
}
