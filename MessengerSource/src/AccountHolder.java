import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


@SuppressWarnings("serial")
public class AccountHolder extends Person implements Serializable {


	
	//===========================================================================Data Members==================================================================================================
		
		private int user_id;
		private String password;
		private String encryption_key;
		private String LOCAL_DRIVE;
		
		/*
		 * 
		 * The File ArrayList initialized by the name of roots contains the file address
		 * of all local drives
		 * 
		 * The count array contains the count of all object that have been created with respect to drives
		 */
		
		
		private ArrayList<File> roots = new ArrayList<>(Arrays.asList(File.listRoots()));
		private int[] count = new int [roots.size()]; 
		
		private int count_to_add;
		
		
		protected String path_for_local_data;
		protected String path_for_inbox;
		protected String path_for_ObjectSaving_file;
	//===========================================================================Constructors=====================================================================================================
		
	public AccountHolder()
	{
		super();

	}
	

	public AccountHolder(String f_name, String l_name, Date entered_birthDate, int entered_user_id, String entered_pass, String entered_key, String drive_location) 
	{
	
		
		/*
		 * 
		 * 
		 * The data members are being filled
		 */
			
		
		
			super(f_name, l_name, entered_birthDate);
			
											
			this.user_id = entered_user_id;
					
			this.password = entered_pass; //digits 0-9, special char, atleast one cap and low letter
			
			this.encryption_key = entered_key; //one_time 16 or 24 or 32 character encryption key
			
			this.LOCAL_DRIVE = drive_location;
			
			
			
			/*
			 * 
			 * 
			 * 
			 * 
			 * After the data members are filled the Read method from Tools class does the object reading operation and 
			 * updates the count array with respect to the system root drives and  if no file has been created the array is 
			 * initialized to default values 
			 * 
			 */
			
			
	if(new File("C:\\Users\\Public\\countValC.txt").exists()) {		
				try {
					for(int i=0;i<count.length;i++)
					{
						// The count array is initialized to ArrayList size; ArrayList size is initialized to root drives number
						
						
						count[i]= Integer.parseInt(Tools.Read("C:\\Users\\Public\\countVal"+Character.toString(roots.get(i).toString().charAt(0))+".txt"));
					}
				 
				}
				
				catch(IOException e) 
				{
					e.printStackTrace();
				}
				
	}	
					path_for_local_data= this.LOCAL_DRIVE.toUpperCase().trim()+":\\MessengerStorage\\Data_0";  // path for first Data folder is initialized
	
					
						path_for_inbox= this.LOCAL_DRIVE.toUpperCase().trim()+":\\MessengerStorage\\Inbox_0";  // path for first Inbox folder is initialized
					
					
							path_for_ObjectSaving_file= path_for_local_data+"\\dat_0"; // path for first object file in Data folder is initialized
							
							
							/*
							 * 
							 * 
							 *  The MakeDirectory class calls the makeDir method to create local folders for data and inbox and if the address 
							 *  does not exist the method returns 0 and creates the folders.
							 * 
							 * 
							 */
								if(MakeDirectory.makeDir(path_for_local_data)==0 && MakeDirectory.makeDir(path_for_inbox)==0) {
									
									
									
									
									
										try {
												// The object Output Stream with File Output Stream creates a file at path_for_ObjectSaving_file
											
												ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path_for_ObjectSaving_file));
													out.writeObject(this); // this keyword saves the object at runtime
														out.close();
							}
							
										catch(FileNotFoundException e) 
										{
												e.printStackTrace();
						}
							
										catch(IOException io) 
										{
												io.printStackTrace();
					}
				
								}
								
								/*
								 * 
								 * 
								 *  The MakeDirectory class calls the makeDir method to create local folders for data and inbox and if the address 
								 *  does exist the method returns 1 and creates the folders.
								 * 
								 * 
								 */			
							
			else if(MakeDirectory.makeDir(path_for_local_data)==1 && MakeDirectory.makeDir(path_for_inbox)==1) 
			{
				for(int i = 0; i<roots.size();i++) 
				{
					
					/*
					 * 
					 * The for loop iterates through the roots array to find a match for the drive location of the object
					 */
					
					if(Character.toString(roots.get(i).toString().charAt(0)).equals((this.LOCAL_DRIVE).toUpperCase().trim()))
						
						// Character is converted to sting; char is string address char at 0 which will be a name of Local Drive i.e. C
						
					{
						this.count_to_add = count[i]; // before adding this int stores the previous count to be added later on 
						
						int temp = count[i]; // temp stores the previous and it is increased later on so previous object file is not overwritten
						
						temp +=1; // the existing count is increased by one to mark new object created
						
						
						count[i] = temp; // count is updated
					}
					
				}
				
				
				/*
				 * 
				 *  If the Folder already exists, we have to update the filename and folder names
				 * 
				 */
				
				
				
				path_for_local_data = path_for_local_data.substring(0, 24);  // string saves the substring after cutting the folder name so we can update the number at the end after
				
					path_for_inbox      = path_for_inbox.substring(0, 25);
				
						path_for_local_data = path_for_local_data+"_"+this.count_to_add;  // the new count value is appended to new directory address
				
							path_for_inbox = path_for_inbox+"_"+this.count_to_add;
				
								path_for_ObjectSaving_file = path_for_local_data+"\\dat_"+this.count_to_add; // to store new object
				
				
				
				MakeDirectory.makeDir(path_for_local_data); // new substring directory is created 
				MakeDirectory.makeDir(path_for_inbox);
				
				
				
				
								try {
										ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path_for_ObjectSaving_file));
											out.writeObject(this);  // object file written
												out.close();
									}

								catch(FileNotFoundException e) 
								{
										e.printStackTrace();
							}

								catch(IOException io) 
								{
										io.printStackTrace();
						}
			
			}				
								
			try {	if(!(new File("C:\\Users\\Public\\countVal"+"C.txt").exists())) { // C drive exists in most systems so the count text values are stored there in a text file
				
					for(int i=0; i<roots.size();i++) 
					{
						count[i]=1; // count values are initialized to 1 because on the first run all directories are created with 0 and 1 updates the count values for next run
					}
					
					/*
					 * 
					 * If the count val file does not exist in C; which means it is the first run so the program creates a file in that directory for data storage
					 */
					
					for(int i=0;i<roots.size();i++) 
					{
							MakeDirectory.createTextFile("C:\\Users\\Public\\countVal"+Character.toString(roots.get(i).toString().charAt(0))+".txt");
						
							String new_count = Integer.toString(count[i]);
									
							Tools.Write("C:\\Users\\Public\\countVal"+Character.toString(roots.get(i).toString().charAt(0))+".txt", new_count); // directory address is w.r.t the drive name i.e. B at the counter i.e. i=0
							
							/* From Tools CLass The program calls write method to write the new count value which is 1 at the given directory
							 * 
							 * 
							 * 
							 */
							
							
						}
					}
					
			else 
				
				
			{
				for(int i=0;i<count.length;i++) 
				{
						/*
						 * Integer is converted to string so we can write with the help of buffered writer all default values of count with respect to their location
						 */
					
						String new_count = Integer.toString(count[i]);
								
						// new file is created with the name of directory from roots ArrayList to keep check all folders...charAt(0) will be the drive i.e. C
						
						
						Tools.Write("C:\\Users\\Public\\countVal"+Character.toString(roots.get(i).toString().charAt(0))+".txt", new_count);
					
					}
			}
				}				
			catch(IOException e) 
					{
						e.printStackTrace();
					}
						
				
			
		}

	//============================================================================= Setters ======================================================================================================


	public void setUser_id(int entered_user_id)
	{
		this.user_id = entered_user_id;


		}


	public void setPassword(String pass)
	{
		this.password = pass;
		
		}
	
	
	public void setlocalDrive(String drive) 
	{
		this.LOCAL_DRIVE = drive;
		
	}
	//============================================================================= Getters ======================================================================================================


	public int getUser_Id()
	{
			return this.user_id;
	}

	public String getPassword()
	{
			return this.password;
	}
	
	public String getlocalDrive() 
	{
		
		return this.LOCAL_DRIVE;
	}
	
	public String getPathForInbox() 
	{
		return this.path_for_inbox;
	}
	
	public String getEncryptionKey() 
	{
		return this.encryption_key;
	}
	
	

	
}
