import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Person implements Serializable{


	//==============================================================================Data Members==========================================================================================	
		private String first_name;
		private String last_name;
		private Date birth_date;
		
		
		
	//==============================================================================Constructors============================================================================================	
		public Person () 
		{
		
			
		
		}
		
		public Person(String f_name, String l_name, Date entered_birthDate)
		
		{
			this.first_name = f_name;
			this.last_name = l_name;
			this.birth_date = new Date(entered_birthDate);
		}
		
		
		public Person(Person a) 
		{
			this.first_name = a.first_name;
			this.last_name = a.last_name;
			this.birth_date = a.birth_date;
			
				
		}

	//====================================================================================Setters=========================================================================================
		
		public void setFirstname(String name) 
		{
			
			this.first_name = name;
		}
		
		public void setLastname(String name)
		{
			
			this.last_name = name;
		}
		
			
		public void setBirthDate(Date dob)
		{
			this.birth_date = new Date(dob);
			
		}
		
	//======================================================================================Getters==============================================================================================	
		
		public String getFirstname() 
		{
			
			return this.first_name;
		}
		
		public String getLastname() 
		{
			
			return this.last_name;
		}
		
		public Date getBirthDate() 
		{
			
			return this.birth_date;
		}
		
		

	}

	



