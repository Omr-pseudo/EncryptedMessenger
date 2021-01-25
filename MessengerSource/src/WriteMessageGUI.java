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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class WriteMessageGUI extends JFrame implements Serializable {

	private JLabel l0,l1;
	private JTextField t0;
	private JTextArea a0;
	private JButton b0,b1;
	private JPanel p0,p1,p2;
	private String Destination;
	private String Tar_Inbox;
	
	private boolean found_Tar_ID = false;
	private boolean ID_isNot_Digit = false;
	
	
	
	
	protected AccountHolder user; 
	
	
	
	public WriteMessageGUI(AccountHolder entered) 
	{
		
		super("Write an Encrypted Message");
		
		
		this.user = entered;
		
		l0 = new JLabel("Put ID of Reciever here: ");
			l1 = new JLabel("Write your message here: ");
		
			
			
		
		t0 = new JTextField(20);
			
		
		
		a0 = new JTextArea();
				a0.setEditable(true);
				
		
				
				
		setLayout(new BorderLayout());		
				
				
				
		p0 = new JPanel();
		
		p0.setLayout(new FlowLayout());
		
		p0.add(l0);
			p0.add(t0);
		
			
			
		p1 = new JPanel();
		
		p1.setLayout(new GridLayout(1,1));
		
		p1.add(l1);
			p1.add(a0);
			
			
				
		
		b0 = new JButton("Send");
		
		b1 = new JButton("Back");
				
				
		p2 = new JPanel();
		
		p2.setLayout(new FlowLayout());
		
		p2.add(b0);
			p2.add(b1);
		
			
			
			
		add(p0,BorderLayout.NORTH);
			add(p1,BorderLayout.CENTER);
				add(p2,BorderLayout.SOUTH);
				
				
				
		b0.addActionListener(new Handler());
			b1.addActionListener(new Handler());
		
				
				
		setSize(800,600);
			setResizable(false);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
					setVisible(true);
				
				
	}
	
	
	
	
	
	
	public void sendMessage() throws Exception 
	{
		int ID_length = t0.getText().toString().length(); 
		
		
		for(int i=0; i<ID_length;i++) 
		{
			char Chars_of_ID = t0.getText().toString().charAt(i);
			
			if(!Character.isDigit(Chars_of_ID)) 
			{
				this.ID_isNot_Digit = true;
				break;
			}
		}
		
		
		
		if(!ID_isNot_Digit)
		{		
		ArrayList<File> root = new ArrayList<>(Arrays.asList(File.listRoots()));
		
		
		
	for(int j=0; j<root.size();j++)	
	{	
		
			
			String drive = Character.toString(root.get(j).toString().charAt(0));
			
			if(new File(drive+":\\MessengerStorage").isDirectory())
			{
			ArrayList<File> listDirectory = new ArrayList<>(Arrays.asList(new File(drive+":\\MessengerStorage").listFiles()));
			
			String path_for_local_data = drive+":\\MessengerStorage\\Data";
			
			for(int i=0; i < (listDirectory.size()/2); i++) 
			{
				String local = path_for_local_data+"_"+i;
					String file = local+"\\dat_"+i;
				
				try {
					
					@SuppressWarnings("resource")
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
					AccountHolder temp = (AccountHolder)in.readObject();
					
					if(Integer.parseInt(t0.getText())==(temp.getUser_Id()))
					{
						Tar_Inbox =  temp.path_for_inbox;
							Destination = temp.path_for_inbox+"\\msgfromID="+user.getUser_Id()+"Number1.txt";
								found_Tar_ID = true;
								
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
	if(found_Tar_ID) 
	{
		

		String newPath = Destination; 
		 
		if(new File(newPath).exists()) 
		{	
			ArrayList<File> numberOfMessages = new ArrayList<>(Arrays.asList(new File(Tar_Inbox).listFiles()));
			
				newPath = numberOfMessages.get(numberOfMessages.size()-1).getAbsolutePath().toString();
			
					int add = Integer.parseInt(Character.toString(newPath.charAt(newPath.length()-5)));
			
						add +=1;
			
							newPath = newPath.substring(0, newPath.length()-5)+add+".txt";
			
			
			
		}
		
			MakeDirectory.createTextFile(newPath);
		
				Tools.writeFile(newPath, a0.getText(), user.getEncryptionKey());
		
		dispose();
			new Homepage(user);
		
		
	}
	
	
	else 
	{

		JOptionPane.showMessageDialog(t0, "Please enter a valid ID.");
		
			dispose();
			
				new WriteMessageGUI(user);
		
	}
	
}
	
	
	
	public class Handler implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) 
		{
			
			if(e.getActionCommand().equals(b0.getText())) 
			{
				try {		
						sendMessage();
				
					
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
			
			else if(e.getActionCommand().equals(b1.getText())) 
			{
				dispose();
				
				new Homepage(user);
			}
			
		}
		
	}
}
