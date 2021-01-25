import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileView;
import javax.swing.JFileChooser;

@SuppressWarnings("serial")
public class CheckInboxGUI extends JFrame implements Serializable{

	
	private AccountHolder user;
	
	private JButton button_item0, button_item1;
	private JTextArea area_item0;
	private JPanel panel_item0;
	
	
	
	private int response;
	private JFileChooser choose_items = new JFileChooser();

	private File toRead;
	private String key;
	
	
	public CheckInboxGUI(AccountHolder entered) 
	{
		super("Check your Inbox");
		
		
		this.user = entered;
		
		button_item0 = new JButton("Read Messages");
			button_item1 = new JButton("Back");
			
		area_item0 = new JTextArea();
			area_item0.setEditable(false);
				area_item0.setToolTipText("Your Decrypted Message will appear in this area once you select the Message to READ.");
			
		panel_item0 = new JPanel();	
			
		
		setLayout(new BorderLayout());
		
		panel_item0.setLayout(new FlowLayout());
		
		
		
		panel_item0.add(button_item0);
			panel_item0.add(button_item1);
							
						
			
			
		add(panel_item0,BorderLayout.NORTH);
			add(area_item0, BorderLayout.CENTER);
				
		
			
			
		button_item0.addActionListener(new Handler());
			button_item1.addActionListener(new Handler());
			
			
			
		setSize(800,600);
			setResizable(true);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
					setVisible(true);
		
	}
	
	public String decryptionKey(String path_to_extract_ID_from) 
	{
		
		
		String path = path_to_extract_ID_from;
		
		int ID = Integer.parseInt(path.substring(path.indexOf('=')+1, path.indexOf('N')));
		
		ArrayList<File> roots = new ArrayList<>(Arrays.asList(File.listRoots()));
		
		
		for(int n = 0; n<roots.size();n++)
		{	
		
		String drive = Character.toString(roots.get(n).toString().charAt(0));
			
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
						
						if(ID==(temp.getUser_Id()))
						{
								key = temp.getEncryptionKey();
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
		
		return key;
		
		
	}
	
	
	public void readMessages() throws Exception 
	{
		final File inbox = new File(user.path_for_inbox);
			
			choose_items.setCurrentDirectory(inbox);
			
				choose_items.setFileView(new FileView() {
					@Override
					public Boolean isTraversable(File f) {
						return inbox.equals(f);
					}
				});
			
			
						choose_items.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
							response = choose_items.showOpenDialog(null);
							
							
			if (response == JFileChooser.APPROVE_OPTION) 
			{
				toRead = choose_items.getSelectedFile();
				
				area_item0.setText(Tools.readFile(toRead.getAbsolutePath(), decryptionKey(toRead.getAbsolutePath())));
			}				
	}
	
	
	public class Handler implements ActionListener
	{
		public void actionPerformed(ActionEvent ae) 
		{
			
			if(ae.getActionCommand().equals(button_item0.getText())) 
			{
				
				try {
					readMessages();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}
			
			else if(ae.getActionCommand().equals(button_item1.getText())) 
			{
				dispose();
				
				new Homepage(user);
			}
			
		}
	}
	
}
