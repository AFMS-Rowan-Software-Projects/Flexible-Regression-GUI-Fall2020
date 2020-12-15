package src.main.java.flexible_regression_gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableModel;

/**
 * Implements a GUI
 * 
 * @author  Ryan Bothmann
 * @author Alex Caruso
 * @version 10/21/2020
 */
public class FlexRegBuilder extends JFrame implements ActionListener {
	  
	static JLabel errorLabel;
	static JTable leftTable;
	static JTable rightTable;
	static DefaultTableModel rightModel;
	static DefaultTableModel leftModel; 
	private ArrayList<Object> dataList = new ArrayList<>();

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
		
		errorLabel = new JLabel("Error: Wrong file type");									// Error label
		errorLabel.setFont(new Font("MV Boli", Font.BOLD, 15));
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
		JLabel testHeader = new JLabel("Steps in test sequence"); 							// Test header label
		testHeader.setHorizontalAlignment(JLabel.CENTER);
		testHeader.setVerticalAlignment(JLabel.TOP);
		testHeader.setVerticalTextPosition(JLabel.TOP);
		testHeader.setHorizontalTextPosition(JLabel.CENTER);
		testHeader.setFont(new Font("Helvetica", Font.BOLD, 24));
		testHeader.setForeground(Color.ORANGE);

		JLabel fileHeader = new JLabel("Files"); 											// File header label
		fileHeader.setHorizontalAlignment(JLabel.CENTER);
		fileHeader.setVerticalAlignment(JLabel.TOP);
		fileHeader.setVerticalTextPosition(JLabel.TOP);
		fileHeader.setHorizontalTextPosition(JLabel.CENTER);
		fileHeader.setFont(new Font("Helvetica", Font.BOLD, 40));
		fileHeader.setForeground(Color.ORANGE);

		// = = = = = = = = Buttons = = = = = = = =
		
		JButton downBtn = new JButton("Move Down");											// Down button to moves step down
		downBtn.setAlignmentY(BOTTOM_ALIGNMENT);
		downBtn.setAlignmentX(CENTER_ALIGNMENT);
		downBtn.addActionListener(f1);
		
		JButton upBtn = new JButton("Move Up");												// Up button to moves step up 
		upBtn.setAlignmentY(BOTTOM_ALIGNMENT);
		upBtn.setAlignmentX(CENTER_ALIGNMENT);
		upBtn.addActionListener(f1);
		
		JButton deleteBtn = new JButton("Delete File");											// Delete button to delete off application
		deleteBtn.setAlignmentY(BOTTOM_ALIGNMENT);
		deleteBtn.setAlignmentX(CENTER_ALIGNMENT);
		deleteBtn.addActionListener(f1);
		
		JButton saveBtn = new JButton("Save");												// Save button
		saveBtn.addActionListener(f1);
		
		JButton createBtn = new JButton("Create File");										// Create file button
		createBtn.setAlignmentY(BOTTOM_ALIGNMENT);
		createBtn.setAlignmentX(CENTER_ALIGNMENT);
		createBtn.addActionListener(f1);
		
		JButton multiBtn = new JButton("Multiplication");									// Multiplication button
		multiBtn.addActionListener(f1);
		
		JButton divideBtn = new JButton("Division");
		divideBtn.addActionListener(f1);
		
		/*
		JButton addressBtn = new JButton("Address Book");									// Address Book button
		addressBtn.addActionListener(f1);
		*/
		
		JButton addBtn = new JButton("Add");												// Add button to transfer sides
		addBtn.setAlignmentY(BOTTOM_ALIGNMENT);
		addBtn.setAlignmentX(CENTER_ALIGNMENT);
		addBtn.addActionListener(f1);
		
		JButton removeBtn = new JButton("Remove");											// Remove button to remove from panel
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
		rightModel.addColumn("Steps:");
		
		
		// = = = = = = = = = Panels = = = = = = = = =
		
		JPanel downPanel = new JPanel();													// Panel for move down button
		downPanel.setBounds(350, 375, 100, 30);
		downPanel.setLayout(new BorderLayout());
		downPanel.setAlignmentY(CENTER_ALIGNMENT);
		downPanel.setAlignmentX(CENTER_ALIGNMENT);
		downPanel.setBackground(new Color(44,87,70));
		downPanel.add(downBtn);
		
		JPanel upPanel = new JPanel();														// Panel for move up button
		upPanel.setBounds(350, 350, 100, 30);
		upPanel.setLayout(new BorderLayout());
		upPanel.setAlignmentY(CENTER_ALIGNMENT);
		upPanel.setAlignmentX(CENTER_ALIGNMENT);
		upPanel.setBackground(new Color(44,87,70));
		upPanel.add(upBtn);
		
		JPanel errorPanel = new JPanel(); 													// Panel for error label 
		errorPanel.setBackground(new Color(44,87,70));
		errorPanel.setBounds(315, 175, 325, 25);
		errorPanel.setLayout(new BorderLayout());
		errorPanel.setAlignmentY(CENTER_ALIGNMENT);
		errorPanel.setAlignmentX(CENTER_ALIGNMENT);
		errorPanel.add(errorLabel);
	
		JPanel deletePanel = new JPanel();													// Panel for delete button
		deletePanel.setBounds(350, 275, 100, 30);
		deletePanel.setLayout(new BorderLayout());
		deletePanel.setAlignmentY(CENTER_ALIGNMENT);
		deletePanel.setAlignmentX(CENTER_ALIGNMENT);
		deletePanel.setBackground(new Color(44,87,70));
		deletePanel.add(deleteBtn);
		
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
		
		JPanel savePanel = new JPanel();													// Panel for save button
		savePanel.setBounds(115, 170, 120, 25);
		savePanel.setLayout(new BorderLayout());
		savePanel.setAlignmentY(CENTER_ALIGNMENT);
		savePanel.setAlignmentX(CENTER_ALIGNMENT);
		savePanel.setBackground(new Color(44,87,70));
		savePanel.add(saveBtn);
		
		JPanel subTextPanel = new JPanel(); 												// Panel for choose file button
		subTextPanel.setBackground(new Color(44,87,70));
		subTextPanel.add(chooseFile);
		
		JPanel createFilePanel = new JPanel();												// Panel for creating file
		createFilePanel.setBounds(115, 145, 120, 25);
		createFilePanel.setLayout(new BorderLayout());
		createFilePanel.setAlignmentY(CENTER_ALIGNMENT);
		createFilePanel.setAlignmentX(CENTER_ALIGNMENT);
		createFilePanel.setBackground(new Color(44,87,70));
		createFilePanel.add(createBtn);
		
		JPanel toolPanel = new JPanel();													// Panel for tools
		toolPanel.setBounds(25, 380, 325, 270);
		toolPanel.setLayout(new GridLayout(2,2));
		TitledBorder border = BorderFactory.createTitledBorder("Tools");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitleColor(Color.DARK_GRAY);
		toolPanel.setBorder(border);
		toolPanel.add(multiBtn);
		toolPanel.add(divideBtn);
		//toolPanel.add(addressBtn);

		JPanel filePanelText = new JPanel(); 												// Panel for file header
		filePanelText.setBounds(100, 50, 150, 125);
		filePanelText.setLayout(new GridLayout(2, 1));
		filePanelText.setAlignmentY(TOP_ALIGNMENT);
		filePanelText.setAlignmentX(LEFT_ALIGNMENT);
		filePanelText.setBackground(new Color(44,87,70));
		filePanelText.add(fileHeader);
		filePanelText.add(subTextPanel);

		JPanel filePanel = new JPanel(); 													// Panel for the files
		filePanel.setBounds(25, 200, 325, 175);
		filePanel.setLayout(new BorderLayout());
		filePanel.setAlignmentY(BOTTOM_ALIGNMENT);
		filePanel.setAlignmentX(LEFT_ALIGNMENT);
		filePanel.add(new JScrollPane(leftTable));
		
		frame.add(downPanel);
		frame.add(upPanel);
		frame.add(savePanel);
		frame.add(createFilePanel);
		frame.add(toolPanel);
		frame.add(deletePanel);
		frame.add(errorPanel);
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
		if (com.contentEquals("Choose File")) {																		// If user presses choose file
			
			 String[] options = new String[] {"File", "Folder", "Nevermind"};
             int response = JOptionPane.showOptionDialog(null,
                     "Will you pick one .xml file or a folder of .xml files?", null,
                     JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                     null, options, options[0]);
             if(response == 0) { 																					// if user picked "One File"
            	 JFileChooser file = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());		// Create an object of JFileChooser 
            	 int tmp = file.showOpenDialog(null);																// Invoke to show the save dialog
            	 if (tmp == JFileChooser.APPROVE_OPTION) {
                     if(ButtonActions.isXML(file.getSelectedFile().getAbsolutePath())) {							// If it's an XML file
                         errorLabel.setVisible(false);
                         leftModel.insertRow(leftModel.getRowCount(), new Object[] { file.getSelectedFile().getAbsolutePath() });
                     } else {
                    	 errorLabel.setVisible(true);
                     }
            	 }
            	 
             } else if (response == 1) {																			// if user picked "Folder"
                 JFileChooser folder = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                 folder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                 int tmp = folder.showOpenDialog(null);																// Invoke to show the save dialog
                 if (tmp == JFileChooser.APPROVE_OPTION) {
                     File filesList[] = folder.getSelectedFile().listFiles();
                     errorLabel.setVisible(false);
                     for(File file : filesList) { 																	// insert every .xml file to left table
                         String filepath = file.toString();
                         if(ButtonActions.isXML(filepath)) {
                             leftModel.insertRow(leftModel.getRowCount(), new Object[] { filepath });
                         } else {
                        	 errorLabel.setText("Error: Some files not .xml files");
                        	 errorLabel.setVisible(true);
                         }
                     }
                 }
             }
             
             // otherwise, user picked "Nevermind," do nothing	
			
		} else if(com.contentEquals("Add")) {		// Add to test sequence, opens up steps
			int index = leftTable.getSelectedRow();
			String tempFile = (String) leftModel.getValueAt(index, 0);
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(tempFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			BufferedInputStream bis = new BufferedInputStream(fis);
			XMLDecoder decoder = new XMLDecoder(bis);
			
			//read file in
			Object obj = null;
			boolean firstLoop = true;
			while(obj != null || firstLoop) {
				obj = decoder.readObject();
				String tempString = obj.getClass().toString().substring(44); 
				// substring(44) used due to the file path currently containing 
				// 	 "class src.main.java.flexible_regression_gui." before the class name
				// Adjust this number accordingly if there are any errors.
				// Test print:
				// System.out.println(obj.getClass().toString());
			
				//If new buttons are added in, their class will need to be added to the switch
				Divide tempDivide = new Divide();
				MultBean tempMult = new MultBean();
				switch(tempString) { 
					case "Divide" : 	
						tempDivide = (Divide) obj;
						dataList.add(tempDivide);
						String divideString = "Divide: Num=" + tempDivide.getNum() + ", Denom=" + tempDivide.getDenom() + ", Result=" + tempDivide.getResult();
						rightModel.insertRow(rightModel.getRowCount(), new Object[] { divideString });
						break;
					case "MultBean" :
						tempMult = (MultBean) obj;
						dataList.add(tempMult);
						String multi = "Multiplication: Left=" + tempMult.getLeft() + ", Right=" + tempMult.getRight() + ", Expected Result:" + tempMult.getLeft()*tempMult.getRight();
						rightModel.insertRow(rightModel.getRowCount(), new Object[] { multi });
						break;
				}
				firstLoop = false;
			}
			decoder.close();
		} else if(com.contentEquals("Remove")) {
			try {
				int index = rightTable.getSelectedRow();
				rightModel.removeRow(index);
				dataList.remove(index);
				errorLabel.setVisible(false);
				
			} catch(ArrayIndexOutOfBoundsException e) {
				errorLabel.setText("Error: Wrong operation");
				errorLabel.setVisible(true);
			}
			
		} else if(com.contentEquals("Delete File")) {
			try {
				try {
					leftModel.removeRow(leftTable.getSelectedRow());
					
				} catch(ArrayIndexOutOfBoundsException e) {
					leftModel.removeRow(leftTable.getSelectedRow());
				}
				errorLabel.setVisible(false);
				
			} catch(ArrayIndexOutOfBoundsException e) {
				errorLabel.setText("Error: Select a file to delete");
				errorLabel.setVisible(true);
			}
			
		} else if(com.contentEquals("Multiplication")) {
			
			String left = JOptionPane.showInputDialog("Left Input: ");
			String right = JOptionPane.showInputDialog("Right Input: ");
			String exp = JOptionPane.showInputDialog("Expected Result: ");
			
			int leftIn = Integer.parseInt(left);
			int rightIn = Integer.parseInt(right);
			int expResult = Integer.parseInt(exp);
			
			//BeanBuilder temp = new BeanBuilder(leftIn, rightIn, expResult);
			MultBean temp = new MultBean();
			temp.setLeft(leftIn);
			temp.setRight(rightIn);
			String multi = "Multiplication: Left=" + leftIn + ", Right=" + rightIn + ", Expected Result:" + expResult;
			rightModel.insertRow(rightModel.getRowCount(), new Object[] { multi });
			dataList.add(temp);
			//JOptionPane.showInternalMessageDialog(frame.getContentPane(), temp.toString() + "\n" + temp.assertEqual());
			// Assert equals is boolean, tells if two are equal.*/
			
		} else if(com.contentEquals("Division")) {
			String numerator = JOptionPane.showInputDialog("Numerator: ");
			String denominator = JOptionPane.showInputDialog("Denominator: ");
			
			double num = Double.parseDouble(numerator);
			double denom = Double.parseDouble(denominator);
			
			Divide div = new Divide();
			div.setNum(num);
			div.setDenom(denom);
			div.setResult();
			String divideString = "Divide: Num=" + num + ", Denom=" + denom + ", Result=" + div.getResult();
			rightModel.insertRow(rightModel.getRowCount(), new Object[] { divideString });
			dataList.add(div);
			
			
		} else if(com.contentEquals("Create File")) {
			// By Emily Griscom
			// prompt for folder
			JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int tmp = chooser.showOpenDialog(null);																		// Invoke to show the save dialog
			if (tmp == JFileChooser.APPROVE_OPTION) {
				// name of folder that new file will be placed in
				String folderName = chooser.getSelectedFile().getPath();
			                
				// prompt for name of .xml file
			    JFrame createFrame = new JFrame("Flexible Regression Builder");
			    String fileName  = JOptionPane.showInputDialog(createFrame,
			    		"Please enter a name for your file."
			    	    + " No need to include '.xml' in the name", null);
			                
			    // if user didn't cancel, create new file in folder
			    if(fileName != null) {
			    	if(ButtonActions.isBlank(fileName)) {
			    		errorLabel.setText("Error: Blank filename");
			            errorLabel.setVisible(true);
			         /*   
			        } else if(ButtonActions.exists(folderName + "//" + fileName + ".xml")) {   
			            errorLabel.setText("Error: File already exists");
			            errorLabel.setVisible(true);
			        	*/
			        } else { // no errors, create file
			        	try {
			        		File newFile = new File(folderName + "//" + fileName + ".xml");
			                newFile.createNewFile();
			                errorLabel.setVisible(false);
			            } catch (IOException ex) {
			                errorLabel.setText("Error: Invalid characters in filename");
			                errorLabel.setVisible(true);
			            }
			        }
			    }
            }  
            
		} else if(com.contentEquals("Move Up")) {
			 int index = rightTable.getSelectedRow();
		        if(index > 0) {
		            rightModel.moveRow(index, index, index - 1);								
		            rightTable.setRowSelectionInterval(index - 1, index - 1);										// set selection to the new position
		            Object temp = dataList.get(index);
		            dataList.remove(index);
		            dataList.add(index-1, temp);
		        }
			
		} else if(com.contentEquals("Move Down")) {
			int index = rightTable.getSelectedRow();
	        if(index < rightModel.getRowCount() - 1){
	            rightModel.moveRow(index, index, index + 1);
	            rightTable.setRowSelectionInterval(index + 1, index + 1);
	            Object temp = dataList.get(index);
	            dataList.remove(index);
	            dataList.add(index+1, temp);
	        }
		        
		} else if (com.contentEquals("Save")) {																		// If user presses saves file
			int index = leftTable.getSelectedRow();
			String file = (String) leftModel.getValueAt(index, 0); 
			System.out.println(file);
			//File file = new File("divide.xml");
			//String path = file.getAbsolutePath();
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			XMLEncoder xmlEncoder = new XMLEncoder(bos);
			for(int i=0; i<dataList.size(); i++) {
			xmlEncoder.writeObject(dataList.get(i));
			}
			xmlEncoder.close();
			
		}
	}
}