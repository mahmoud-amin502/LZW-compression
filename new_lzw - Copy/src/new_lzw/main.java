package new_lzw;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
public class main {
	
	public static void main ( String[] args ) throws IOException
	{
		 compress d=new compress();
	        d.readFile("x1.txt");
	        
	        
//	        String s1="abcba";
//	        System.out.println(s1.length());
	        //d.readFile("x1.txt");
	        //System.out.println(d.readfcofile("c1.txt"));
	        d.compress("x1.txt");
	        
	        
	        d.decompress("c1.txt");
		
	
		
	}

}
