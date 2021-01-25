import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreateAnAccount extends JFrame implements Serializable{
	
	private JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
	private JTextField t1,t2,t3,t4,t5,t6,t7,t8;
	private JPasswordField p1;
	private JButton b1;
	private JPanel panel1;
	
	
	
	
	
	public CreateAnAccount() 
	{
		super("Create a New Account");
		
		
		l1 = new JLabel("Enter Your First Name: ");
			l2 = new JLabel("Enter Your Last Name: ");
				l3 = new JLabel("Enter Your Day of Birth: ");
					l4 = new JLabel("Enter Your Month of Birth: ");
						l5 = new JLabel("Enter Your Year of Birth: ");
							l6 = new JLabel("Enter Your User ID to set: ");
								l7 = new JLabel("Enter Your Password to set: ");
									l8 = new JLabel("Enter Your 16/24/36 Characters Key: ");
										l9 = new JLabel("Enter Your Local Drive to set for Storage: ");
	
	
		t1 = new JTextField(20);
			t1.setToolTipText("Please enter your First name and keep the first Alphabet Capital");
			
			t2 = new JTextField(20);
				t2.setToolTipText("Please enter your Last name and keep the first Alphabet Capital");
			
					t3 = new JTextField(20);
						t3.setToolTipText("Please enter a valid day.(between 1 and 31)");
					
							t4 = new JTextField(20);
								t4.setToolTipText("Please enter a valid Month. (between 1 and 12)");
						
								t5 = new JTextField(20);
									t5.setToolTipText("Please enter a valid year.");
								
									t6 = new JTextField(20);
										t6.setToolTipText("Please enter a unique ID that does not exist in the System. (no spaces between the digits)");
								
										t7 = new JTextField(20);
											t7.setToolTipText("Please enter your Encryption key with length of 16, 24 or 32. Key can contain special characters, numerals and alphabets.");
										
												t8 = new JTextField(20);
													t8.setToolTipText("Please Enter your existing drive name. e.g. C");
		
		p1 = new JPasswordField(20);
		p1.setToolTipText("Your Password must be between the lengths 6-20, it should contain atleast one digit, small alphabet, capital alphabet and special character");
		
		b1 = new JButton("Submit");
		
				
		panel1 = new JPanel();
		
		
		setLayout(new BorderLayout());
			panel1.setLayout(new GridLayout(9,9,10,10));
			
			
		panel1.add(l1);
			panel1.add(t1);
				panel1.add(l2);
					panel1.add(t2);
						panel1.add(l3);
							panel1.add(t3);
								panel1.add(l4);
									panel1.add(t4);
										panel1.add(l5);
											panel1.add(t5);
												panel1.add(l6);
													panel1.add(t6);
														panel1.add(l7);
															panel1.add(p1);
																panel1.add(l8);
																	panel1.add(t7);
																		panel1.add(l9);
																			panel1.add(t8);
		
		add(panel1,BorderLayout.CENTER);																	
			add(b1,BorderLayout.SOUTH);
			
		b1.addActionListener(new Handler());	
		
		setSize(800,600);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
				setVisible(true);
					setResizable(false);
	}
	
	public int checkFirstName() 
	{
		
		String name = t1.getText().toString();
		int length = t1.getText().toString().length();
		
		boolean Spaces = false;
		
		boolean checkFirstAlphabet_isCap = false;
		
		boolean check_other_than_Alphabets = false;
		
		
		if (Character.toString(name.charAt(0)).equals(Character.toString(name.charAt(0)).toUpperCase())) 
		{
			checkFirstAlphabet_isCap = true;
		}
		
		
		for(int i = 0; i<length;i++) 
		{
			
			if(Character.isWhitespace(name.charAt(i)) || !(Character.isAlphabetic(name.charAt(i)))) 
			{
				Spaces = true;
				check_other_than_Alphabets = true;
				break;
			}
		}
		
		
		
		if(!Spaces && checkFirstAlphabet_isCap && !check_other_than_Alphabets)
		{
			return 0;
		}
		else 
		{
			return 1;
		}
	}
	
	public int checkLastName() 
	{
		
		String name = t2.getText().toString();
		int length = t2.getText().toString().length();
		
		boolean Spaces = false;
		
		boolean checkFirstAlphabet_isCap = false;
		
		boolean check_other_than_Alphabets = false;
		
		
		if (Character.toString(name.charAt(0)).equals(Character.toString(name.charAt(0)).toUpperCase())) 
		{
			checkFirstAlphabet_isCap = true;
		}
		
		
		for(int i = 0; i<length;i++) 
		{
			
			if(Character.isWhitespace(name.charAt(i)) || !(Character.isAlphabetic(name.charAt(i)))) 
			{
				Spaces = true;
				check_other_than_Alphabets = true;
				break;
			}
		}
		
		
		
		
		if(!Spaces && checkFirstAlphabet_isCap && !check_other_than_Alphabets)
		{
			return 0;
		}
		else 
		{
			return 1;
		}
	}
	
	
	public int checkDate() 
	{
		
		boolean validDate = false;
		
		
		int day = 0;
			int month = 0;
				int year = 0;
				
		
		String day_s = t3.getText().toString(); String month_s = t4.getText().toString(); String year_s = t5.getText().toString();
		
		
		//
		////
		/////VERIFY THE LENGTH OF DATE
		//////////////////////////////
		///////////////////////////////////
		if((!(day_s.length() <=0)&&(day_s.length()<=2))&&(!(month_s.length()<=0)&&(month_s.length()<=2))&&(!(year_s.length()<=0)&&(year_s.length()<=4))) 
		{
			day = Integer.parseInt(day_s);
				month = Integer.parseInt(month_s);
					year = Integer.parseInt(year_s);
					
					if((!(day<=0)&&(day<=31))&&(!(month<=0)&&(month<=12))) 
					{
						try {
						LocalDate.parse(year+"-"+month+"-"+day, DateTimeFormatter.ofPattern("uuu-M-d").withResolverStyle(ResolverStyle.STRICT));
						
						validDate = true;
						}
						catch(DateTimeParseException e) 
						{
							validDate = false;
						}
					}
			
			
		}
		
		
		
		
		if(validDate) 
		{
			return 0;
		}
		else 
		{
			return 1;
		}
		
	} 
	
	

	
	public int checkUserID() 
	{
		String toCheck= t6.toString();
		int length = toCheck.length();
		
		boolean noSpaces= true;
		boolean exists = false;
		
		for(int i=0; i<length; i++)
		{
			if(Character.isWhitespace(toCheck.charAt(i)) || !(Character.isDigit(toCheck.charAt(i)))) 
			{
			    
			    noSpaces= false;
			    break;
			}
			
		}
		
		
		
		
		if(noSpaces)
		{
			ArrayList<File> roots = new ArrayList<>(Arrays.asList(File.listRoots()));
			
			for(int count = 0; count<roots.size();count++) 
			{
				String drive = Character.toString(roots.get(count).toString().charAt(0));
				
				String path_for_local_data = drive+":\\MessengerStorage";
			
			if(new File(path_for_local_data).isDirectory())	
				{		
					
				
				ArrayList<File> listDirectory = new ArrayList<>(Arrays.asList(new File(drive+":\\MessengerStorage").listFiles()));

					path_for_local_data = drive+":\\MessengerStorage\\Data";
				
				for(int i=0; i < (listDirectory.size()/2); i++) 
				{
					String local = path_for_local_data+"_"+i;
						String file = local+"\\dat_"+i;
						
						try {
							
							ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
							AccountHolder temp = (AccountHolder)in.readObject();
							
							if(Integer.parseInt(t6.getText())==(temp.getUser_Id()))
							{
									exists = true;
									in.close();
								
										
										
							}
							
							else 
							{
								in.close();
							}
							
							
						}
						
						catch(IOException e) 
						{
							e.printStackTrace();
						}
						catch(ClassNotFoundException c) 
						{
							c.printStackTrace();
							}
						
						}
				
					}
				
				}
			}
		
		
		
		if(!exists) 
		{
			return 0;
		}
		else 
		{
			return 1;
		}
	}
	
	
	
	public int checkCharacterKey() 
	{
		String toCheck = t7.getText();
		int length = toCheck.length();
		
		if(length == 16 || length == 24 || length == 32) 
		{
			
			return 0;
			
		}
		
		else 
		{	
			return 1;
		}
	}
	
	
	public int checkDriveLocation() 
	{
		char drive = t8.getText().toString().toUpperCase().trim().charAt(0);
	
		
		ArrayList<File> roots = new ArrayList<> (Arrays.asList(File.listRoots()));
		
			boolean Drive_exists = false;
		
		for (int i=0; i<roots.size();i++) 
		{
			
			
			
			if((drive == roots.get(i).toString().charAt(0)) && (t8.getText().toString().length() == 1)) 
			{
			
				Drive_exists = true;
			}
		}
		
		
		if(Drive_exists) 
		{
			return 0;
			
		}
		
		else 
		{
			return 1;
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public int checkPass() 
	{
		
		if(PasswordValidator.checkPassword(p1.getText().toString()) == 0) 
		{
			return 0;
			
			
			
		}
		else
		{
			
			return 1;
		}
	}
	
	
	public int checkFields() 
	{	

		if(this.checkFirstName()==0 && this.checkLastName()==0 && this.checkDate()==0 && this.checkUserID()==0 && this.checkCharacterKey()==0 && this.checkDriveLocation()==0 && this.checkPass() == 0)
			return 0;
		else
			return 1;
	}
	
	class Handler implements ActionListener
	{
		
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) 
		{
			String Firstname, Lastname, Password, Key, Drive;
			int Day, Month, Year, ID;
			
			
			if(e.getActionCommand()=="Submit") 
			{
				if(checkFields()==0) 
				{
					Firstname = t1.getText();
					Lastname = t2.getText();
						Day = Integer.parseInt(t3.getText());
							Month = Integer.parseInt(t4.getText());
								Year = Integer.parseInt(t5.getText());
									ID = Integer.parseInt(t6.getText());
										Password = p1.getText();
											Key = t7.getText();
												Drive = t8.getText();
					
											
				Date birth_date = new Date(Day,Month,Year);
					new AccountHolder(Firstname,Lastname,birth_date,ID,Password,Key,Drive);
											
				dispose();
				
				new LoginPage();
				
				}
				
				else 
				{
					
					
					JOptionPane.showMessageDialog(b1, "Please Enter Valid Data: Check all fields");
					
					dispose();
					
						new CreateAnAccount();
				}
				
				
			}
		}
		
	}
}
