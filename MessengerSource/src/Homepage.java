import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Homepage extends JFrame implements Serializable {

	
	private JButton b0,b1,b2,b3; 
	private JTextArea a0;
	private JPanel p0;
	
	
	protected AccountHolder user;
	
	public Homepage(AccountHolder entered) 
	{
		
		super("Welcome! to the Homepage.");
		
		this.user = entered;
		
		a0 = new JTextArea();
			a0.setText("Hello !\nDear,"+" "+user.getFirstname()+" "+user.getLastname()+" "+"welcome to your Homepage of Encrypted Messenger.\nThis Application provides you with the ability to send and receive encrypted messages,\nSend messages without the stress of people eavesdropping onto your conversations.\nLet's get started!\n");
				a0.setEditable(false);
					
		
		b0 = new JButton("Write a Message");			
			b1 = new JButton("Check your Inbox");
				b2 = new JButton("Log Out");
					b3 = new JButton("Exit");
					
		
					
					
		p0 = new JPanel();
		
		
		setLayout(new BorderLayout());
		
		p0.setLayout(new GridLayout(1,4));
		
		p0.add(b0);
			p0.add(b1);
				p0.add(b2);
					p0.add(b3);
		
		
		add(a0,BorderLayout.NORTH);
			add(p0,BorderLayout.CENTER);
			
			
		b0.addActionListener(new Handler());	
			b1.addActionListener(new Handler());
				b2.addActionListener(new Handler());
					b3.addActionListener(new Handler());
	
					
					
				
		setSize(800,600);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
				setResizable(false);
					setVisible(true);
					
					
		
		
	}
	
	public class Handler implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getActionCommand().equals(b0.getText())) 
			{
				dispose();
				
				new WriteMessageGUI(user);
			}
			
			else if(e.getActionCommand().equals(b1.getText())) 
			{
				dispose();
				
				new CheckInboxGUI(user);
			}
			else if(e.getActionCommand().equals(b2.getText())) 
			{
				dispose();
				
				new LoginPage();
			}
			else if(e.getActionCommand().equals(b3.getText())) 
			{
				dispose();
				
				System.exit(0);
			}
		}
		
	}
}
