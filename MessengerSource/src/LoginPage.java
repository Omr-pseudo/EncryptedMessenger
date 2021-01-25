import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class LoginPage extends JFrame implements Serializable{

	private JLabel l0,l1,l2;
	private JTextField	t0,t1;
	private JPasswordField p1;
	private JButton b1,b2;
	private JPanel panel1, panel2;
	
	private boolean credentials_matched = false;
	private boolean not_valid_ID = false;
	private boolean valid_root = false;
	
	private AccountHolder login;
	
	
	public LoginPage()
	{
		super("Encrypted Messenger Login");
		
		l0 = new JLabel("Drive ");
		l1 = new JLabel("ID ");
		l2 = new JLabel("Password ");
		
		t0 = new JTextField(20);
		t1 = new JTextField(20);
		p1 = new JPasswordField(20);
		
		b1 = new JButton("Login");
		b2 = new JButton("Create an Account");
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		setLayout(new BorderLayout());
		
		panel1.setLayout(new GridLayout(3,3,10,10));
		panel2.setLayout(new FlowLayout());
		
		panel1.add(l0);
		panel1.add(t0);
		panel1.add(l1);
		panel1.add(t1);
		panel1.add(l2);
		panel1.add(p1);
		
		
		panel2.add(b1);
		panel2.add(b2);
		
		add(panel1,BorderLayout.CENTER);
		add(panel2,BorderLayout.SOUTH);
		
		b1.addActionListener(new Handler());
		b2.addActionListener(new Handler());
		
		setSize(400,200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
		
		
		}
	
	public int check_valid_ID() 
	{
		
		int ID_length = t0.getText().toString().length(); 
		
		
		for(int i=0; i<ID_length;i++) 
		{
			char Chars_of_ID = t0.getText().toString().charAt(i);
			
			if(!Character.isDigit(Chars_of_ID)) 
			{
				not_valid_ID = true;
				break;
			}
		}
		
		if(not_valid_ID) 
		
			return 1;
		
		else
			return 0;
		
	}
	
	
	
	public int check_valid_root() 
	{
	

		char drive = t0.getText().trim().charAt(0);
		ArrayList<File> roots = new ArrayList<> (Arrays.asList(File.listRoots()));
		
		
		for (int i=0; i<roots.size();i++) 
		{
			if(drive == roots.get(i).toString().charAt(0) && (t0.getText().toString().length() == 1)) 
			{
				valid_root = true;
				break;
			}
		}
		
		if(valid_root) 
		{
			return 0;
			
		}
		
		else 
		{
			return 1;
		}
		
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	private void SearchCredentials(String Drive_location)
	{
		String drive = (Drive_location).toUpperCase();
		
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
					
					if(Integer.parseInt(t1.getText())==(temp.getUser_Id()) && p1.getText().equals(temp.getPassword()))
					{
						login = temp;
							credentials_matched = true;
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
	
		
		if(credentials_matched) 
		{
			
			dispose();
				new Homepage(login);
		
			
		}
		
		else 
		{
			JOptionPane.showMessageDialog(l1, "Enter a valid ID/Password/Drive Location");
			
				dispose();
			
					new LoginPage();
		}
		
		
		
	}


	public class Handler implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getActionCommand().equals(b1.getText())) 
			{
				if(check_valid_ID()==0 && check_valid_root()==0) {
				
					SearchCredentials(t0.getText().trim());
		
				}
				
				else
				{	
					JOptionPane.showMessageDialog(b1, "Please Enter valid Credentials");
					
					t0.setText(null);
					t1.setText(null);
					p1.setText(null);
				}
				
			}			
			else if(e.getActionCommand().equals(b2.getText())) 
			{
				dispose();
				
				new CreateAnAccount();
			}
		}
		
	
	
	

		}
	}




	

