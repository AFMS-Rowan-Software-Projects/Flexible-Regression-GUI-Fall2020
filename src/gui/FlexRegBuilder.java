package gui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableModel;

/**
 * Implements a GUI
 * 
 * @author  Ryan Bothmann
 * @version 10/21/2020
 */
public class FlexRegBuilder extends JFrame implements ActionListener {
	  
	static JLabel errorLabel;
	static JTable leftTable;
	static JTable rightTable;
	static DefaultTableModel rightModel;
	static DefaultTableModel leftModel; 

	FlexRegBuilder() {}																		// Constructor

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) { createWindow(); }

	/**
	 * Tools to create the window.
	 */
	static void createWindow() {

		FlexRegBuilder f1 = new FlexRegBuilder();											// Make an object of the class FlexRegBuilder
				
		// = = = = = = = = Frame = = = = = = = =
		
		JFrame frame = new JFrame("Flexible Regression Builder"); 							// Frame to contains GUI elements
		frame.setSize(800, 700);
		frame.getContentPane().setBackground(new Color(44,87,70));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		// = = = = = = = = Labels = = = = = = = =
		
		errorLabel = new JLabel("Error: Wrong file type");
		errorLabel.setFont(new Font("MV Boli", Font.BOLD, 15));
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
		JLabel testHeader = new JLabel("Files in test sequence"); 							// Test header label
		testHeader.setHorizontalAlignment(JLabel.CENTER);
		testHeader.setVerticalAlignment(JLabel.TOP);
		testHeader.setVerticalTextPosition(JLabel.TOP);
		testHeader.setHorizontalTextPosition(JLabel.CENTER);
		testHeader.setFont(new Font("MV Boli", Font.BOLD, 24));
		testHeader.setForeground(Color.ORANGE);

		JLabel fileHeader = new JLabel("Files"); 											// File header label
		fileHeader.setHorizontalAlignment(JLabel.CENTER);
		fileHeader.setVerticalAlignment(JLabel.TOP);
		fileHeader.setVerticalTextPosition(JLabel.TOP);
		fileHeader.setHorizontalTextPosition(JLabel.CENTER);
		fileHeader.setFont(new Font("MV Boli", Font.BOLD, 40));
		fileHeader.setForeground(Color.ORANGE);

		// = = = = = = = = Buttons = = = = = = = =
		
		JButton deleteBtn = new JButton("Delete");											// Delete button to delete off application
		deleteBtn.setAlignmentY(BOTTOM_ALIGNMENT);
		deleteBtn.setAlignmentX(CENTER_ALIGNMENT);
		deleteBtn.addActionListener(f1);
		
		JButton editBtn = new JButton("Edit");												// Edit button to edit files on right side
		editBtn.setAlignmentY(BOTTOM_ALIGNMENT);
		editBtn.setAlignmentX(CENTER_ALIGNMENT);
		editBtn.addActionListener(f1);
		
		JButton addBtn = new JButton("Add ->");												// Add button to transfer sides
		addBtn.setAlignmentY(BOTTOM_ALIGNMENT);
		addBtn.setAlignmentX(CENTER_ALIGNMENT);
		addBtn.addActionListener(f1);
		
		JButton removeBtn = new JButton("<- Remove");										// Remove button to remove from panel
		removeBtn.setAlignmentY(BOTTOM_ALIGNMENT);
		removeBtn.setAlignmentX(CENTER_ALIGNMENT);
		removeBtn.addActionListener(f1);
		
		JButton chooseFile = new JButton("Choose File"); 									// Button to choose file
		chooseFile.setAlignmentY(BOTTOM_ALIGNMENT);
		chooseFile.setAlignmentX(CENTER_ALIGNMENT);
		chooseFile.addActionListener(f1);

		// = = = = = = = = = Tables = = = = = = = = =
		
		leftModel = new DefaultTableModel(); 												// Left table		
		leftTable = new JTable(leftModel);
		leftTable.setBounds(25,200,325,450);
		leftTable.setDefaultEditor(Object.class, null);
		leftTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		leftModel.addColumn("Files:");
		
		rightModel = new DefaultTableModel(); 												// Right table
		rightTable = new JTable(rightModel);
		rightTable.setBounds(450, 200, 325, 450);
		rightTable.setDefaultEditor(Object.class, null);
		rightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rightModel.addColumn("Files:");
		
		
		// = = = = = = = = = Panels = = = = = = = = =
		
		JPanel errorPanel = new JPanel(); 													// Panel for error label 
		errorPanel.setBackground(new Color(44,87,70));
		errorPanel.setBounds(315, 175, 325, 25);
		errorPanel.setLayout(new BorderLayout());
		errorPanel.setAlignmentY(CENTER_ALIGNMENT);
		errorPanel.setAlignmentX(CENTER_ALIGNMENT);
		errorPanel.add(errorLabel);
		
		JPanel deletePanel = new JPanel();													// Panel for delete button
		deletePanel.setBounds(350, 300, 100, 30);
		deletePanel.setLayout(new BorderLayout());
		deletePanel.setAlignmentY(CENTER_ALIGNMENT);
		deletePanel.setAlignmentX(CENTER_ALIGNMENT);
		deletePanel.setBackground(new Color(44,87,70));
		deletePanel.add(deleteBtn);

		JPanel editPanel = new JPanel();													// Panel for edit button
		editPanel.setBounds(350, 275, 100, 30);
		editPanel.setLayout(new BorderLayout());
		editPanel.setAlignmentY(CENTER_ALIGNMENT);
		editPanel.setAlignmentX(CENTER_ALIGNMENT);
		editPanel.setBackground(new Color(44,87,70));
		editPanel.add(editBtn);
		
		JPanel removePanel = new JPanel();													// Panel for add button
		removePanel.setBounds(350, 250, 100, 30);
		removePanel.setLayout(new BorderLayout());
		removePanel.setAlignmentY(CENTER_ALIGNMENT);
		removePanel.setAlignmentX(CENTER_ALIGNMENT);
		removePanel.setBackground(new Color(44,87,70));
		removePanel.add(removeBtn);
		
		JPanel addPanel = new JPanel();														// Panel for add button
		addPanel.setBounds(350, 225, 100, 30);
		addPanel.setLayout(new BorderLayout());
		addPanel.setAlignmentY(CENTER_ALIGNMENT);
		addPanel.setAlignmentX(CENTER_ALIGNMENT);
		addPanel.setBackground(new Color(44,87,70));
		addPanel.add(addBtn);
		
		JPanel testPanelText = new JPanel();												// Panel for test header
		testPanelText.setBounds(450, 60, 325, 100);
		testPanelText.setLayout(new BorderLayout());
		testPanelText.setAlignmentY(TOP_ALIGNMENT);
		testPanelText.setAlignmentX(RIGHT_ALIGNMENT);
		testPanelText.setBackground(new Color(44,87,70));
		testPanelText.add(testHeader);
		
		JPanel testPanel = new JPanel();													// Panel for the test side
		testPanel.setBounds(450, 200, 325, 450);
		testPanel.setLayout(new BorderLayout());
		testPanel.setAlignmentY(BOTTOM_ALIGNMENT);
		testPanel.setAlignmentX(RIGHT_ALIGNMENT);
		testPanel.add(new JScrollPane(rightTable));
		
		JPanel subTextPanel = new JPanel(); 												// Panel for choose file button
		subTextPanel.setBackground(new Color(44,87,70));
		subTextPanel.add(chooseFile);

		JPanel filePanelText = new JPanel(); 												// Panel for file header
		filePanelText.setBounds(100, 50, 150, 100);
		filePanelText.setLayout(new GridLayout(2, 1));
		filePanelText.setAlignmentY(TOP_ALIGNMENT);
		filePanelText.setAlignmentX(LEFT_ALIGNMENT);
		filePanelText.setBackground(new Color(44,87,70));
		filePanelText.add(fileHeader);
		filePanelText.add(subTextPanel);

		JPanel filePanel = new JPanel(); 													// Panel for the files
		filePanel.setBounds(25, 200, 325, 450);
		filePanel.setLayout(new BorderLayout());
		filePanel.setAlignmentY(BOTTOM_ALIGNMENT);
		filePanel.setAlignmentX(LEFT_ALIGNMENT);
		filePanel.add(new JScrollPane(leftTable));
		
		frame.add(deletePanel);
		frame.add(errorPanel);
		frame.add(editPanel);
		frame.add(removePanel);
		frame.add(addPanel);
		frame.add(filePanel);															    // Add panels to the frame
		frame.add(filePanelText);
		frame.add(testPanel);
		frame.add(testPanelText);
		frame.setVisible(true);
	}

	/**
	 * When action is performed, do something.
	 * 
	 * @param event
	 */
	public void actionPerformed(ActionEvent event) {

		String com = event.getActionCommand();
		if (com.contentEquals("Choose File")) {																			// If user presses choose file
				
			JFileChooser file = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());				// Create an object of JFileChooser 
			int tmp = file.showOpenDialog(null);																		// Invoke to show the save dialog
			if (tmp == JFileChooser.APPROVE_OPTION) {
				
				if(CreateXML.isXML(file.getSelectedFile().getAbsolutePath()) == true) {									// If it's an XML file
					errorLabel.setVisible(false);
					leftModel.insertRow(leftModel.getRowCount(), new Object[] { file.getSelectedFile().getAbsolutePath() });// Add row that contains file name
					
				} else {
					errorLabel.setVisible(true);
				}
			}
			
		} else if(com.contentEquals("Add ->")) {
			try {
				rightModel.insertRow(rightModel.getRowCount(), new Object[] { leftTable.getValueAt(leftTable.getSelectedRow(), 0) } );
				leftModel.removeRow(leftTable.getSelectedRow());
				errorLabel.setVisible(false);
				
			} catch(ArrayIndexOutOfBoundsException e) {
				errorLabel.setText("Error: Wrong operation");
				errorLabel.setVisible(true);
			}
			
		} else if(com.contentEquals("<- Remove")) {
			try {
				leftModel.insertRow(leftModel.getRowCount(), new Object[] { rightTable.getValueAt(rightTable.getSelectedRow(), 0) } );
				rightModel.removeRow(rightTable.getSelectedRow());
				errorLabel.setVisible(false);
				
			} catch(ArrayIndexOutOfBoundsException e) {
				errorLabel.setText("Error: Wrong operation");
				errorLabel.setVisible(true);
			}
			
		} else if(com.contentEquals("Edit")) {
			try {
				PopUp.popUp((String) rightTable.getValueAt(rightTable.getSelectedRow(), 0));
				errorLabel.setVisible(false);
				
			} catch(ArrayIndexOutOfBoundsException | IOException e) {
				try {
					PopUp.popUp((String) leftTable.getValueAt(leftTable.getSelectedRow(), 0));
					errorLabel.setVisible(false);

				} catch(ArrayIndexOutOfBoundsException | IOException ex) {
					errorLabel.setText("Error: Select a file to edit");
					errorLabel.setVisible(true);
				}
			}
			
		} else if(com.contentEquals("Delete")) {
			try {
				rightModel.removeRow(rightTable.getSelectedRow());
				
			} catch(ArrayIndexOutOfBoundsException e) {
				leftModel.removeRow(leftTable.getSelectedRow());
			}
		}
	}
}