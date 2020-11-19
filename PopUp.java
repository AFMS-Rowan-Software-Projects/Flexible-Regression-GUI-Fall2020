package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

public class PopUp extends JFrame implements ActionListener {
	
	
	static String path;
	static JFrame frame;
	static JLabel saveLabel;
	static JLabel errorLabel;
	static JOptionPane multiply;
	static JEditorPane editor;
	
	PopUp() {}																									// Constructor

	/**
	 * The creation of the pop-up window
	 * @param object 
	 * @throws IOException 
	 * @throws IOException 
	 */
	public static void popUp(String filePath) throws IOException {
		
		path = filePath;
		PopUp p1 = new PopUp();																					// Make an object of the class PopUp
		
		// = = = = = = Frame = = = = = =
		frame = new JFrame("Test Sequence Builder"); 													// Frame to contains pop-up elements
		frame.setSize(900, 700);
		frame.getContentPane().setBackground(new Color(44,87,70));
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLayout(null);
		
		// = = = = = = Labels = = = = = =
		errorLabel = new JLabel("Error: File not found");
		errorLabel.setFont(new Font("MV Boli", Font.BOLD, 16));
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
		saveLabel = new JLabel("File Saved");
		saveLabel.setFont(new Font("MV Boli", Font.BOLD, 12));
		saveLabel.setForeground(Color.ORANGE);
		saveLabel.setVisible(false);
		
		// = = = = = = Buttons = = = = = =
		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(p1);
		
		JButton multiBtn = new JButton("Multiplication");
		multiBtn.addActionListener(p1);

		JButton addressBtn = new JButton("Address Book");
		addressBtn.addActionListener(p1);
		
		// = = = = = = Edit file panel = = = = = =
		TitledBorder border = BorderFactory.createTitledBorder("Editing area");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitleColor(Color.DARK_GRAY);
			
		editor = new JEditorPane();
		editor.setText("");

		JScrollPane editorScroller = new JScrollPane(editor);
		editorScroller.setBounds(25, 25, 850, 450);
		editorScroller.setBorder(border);
		
		// = = = = = = Other Panels = = = = = =
		JPanel errorPanel = new JPanel(); 																			// Panel for error label 
		errorPanel.setBounds(365, 550, 325, 25);
		errorPanel.setLayout(new BorderLayout());
		errorPanel.setBackground(new Color(44,87,70));
		errorPanel.add(errorLabel);
		
		JPanel addressPanel = new JPanel();
		addressPanel.setBounds(295, 485, 125, 30);
		addressPanel.setLayout(new BorderLayout());
		addressPanel.setBackground(new Color(44,87,70));
		addressPanel.add(addressBtn);
		
		JPanel multiPanel = new JPanel();
		multiPanel.setBounds(160, 485, 125, 30);
		multiPanel.setLayout(new BorderLayout());
		multiPanel.setBackground(new Color(44,87,70));
		multiPanel.add(multiBtn);
		
		JPanel saveLblPanel = new JPanel();
		saveLblPanel.setBounds(70, 515, 65, 20);
		saveLblPanel.setLayout(new BorderLayout());
		saveLblPanel.setBackground(new Color(44,87,70));
		saveLblPanel.add(saveLabel);
		
		JPanel savePanel = new JPanel();
		savePanel.setBounds(50, 485, 100, 30);
		savePanel.setLayout(new BorderLayout());
		savePanel.setBackground(new Color(44,87,70));
		savePanel.add(saveBtn);
		
		// = = = = = = Import file = = = = = =
		JTextComponent text = editor;
		FileReader inputReader = null;
		try {
			inputReader = new FileReader(filePath);
			text.read(inputReader, filePath); 
			
		} catch(IOException e) {
			errorLabel.setVisible(true);
		}
		
		inputReader.close();
			
		// = = = = = = = = = = = = =
		frame.add(saveLblPanel);
		frame.add(errorPanel);
		//frame.add(addressPanel);
		//frame.add(multiPanel);
		frame.add(savePanel);
		frame.add(editorScroller);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		String com = event.getActionCommand();
		if (com.contentEquals("Save")) {																			// If user presses saves file
			JTextComponent text = editor;
			FileWriter writer = null; 
			try {
				writer = new FileWriter(path);
				text.write(writer);
				writer.close();
				saveLabel.setVisible(true);
				errorLabel.setVisible(false);
				int delay = 5000; 																					// Milliseconds
				   ActionListener taskPerformer = new ActionListener() {
				       public void actionPerformed(ActionEvent evt) {
				           saveLabel.setVisible(false);
				       }
				   };
				   
				new javax.swing.Timer(delay, taskPerformer).start();												// Delay removal of saveLabel
				
			} catch (IOException e) {
				errorLabel.setText("Error: Save failed");
				errorLabel.setVisible(true);
			}			
			
		} /*else if(com.contentEquals("Multiplication")) {
			String left = JOptionPane.showInputDialog("Left Input: ");
			String right = JOptionPane.showInputDialog("Right Input: ");
			String exp = JOptionPane.showInputDialog("Expected Result: ");
			
			int leftIn = Integer.parseInt(left);
			int rightIn = Integer.parseInt(right);
			int expResult = Integer.parseInt(exp);
			
			BeanBuilder temp = new BeanBuilder(leftIn, rightIn, expResult);
			JOptionPane.showInternalMessageDialog(frame.getContentPane(), temp.toString() + "\n" + temp.assertEqual());
			// Assert equals is boolean, tells if two are equal.
			
		}*/
	}
}
