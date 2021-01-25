import java.io.File;
import java.io.IOException;

public class MakeDirectory {

	public static int makeDir(String dir)
	{
		File new_file = new File(dir);
		
		boolean created = new_file.mkdirs();
		
		if (created)
			
			return 0;
		
		else if(new_file.exists()) 
			
			return 1;
		
		else

			return 2;
		
	}
	
	public static void createTextFile(String path) throws IOException
	{
		
		File file1 = new File(path);
		file1.createNewFile();
	}
}
