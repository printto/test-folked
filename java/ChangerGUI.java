package aliaser;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChangerGUI extends JFrame{

	FileCreater saver = new FileCreater();
	SuffixChanger suffixChanger;

	JLabel filePath = new JLabel("No file selected.");
	JButton browseBtn = new JButton("Browse...");
	JTextField suffix = new JTextField(5);
	JTextArea box = new JTextArea(20,0);
	JButton changeBtn = new JButton("Change");
	JButton exportBtn = new JButton("Save");

	public ChangerGUI () {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
		initActions();
	}

	private void initComponents() {
		this.setLayout(new BorderLayout());
		this.setTitle("Suffix Aliaser");
		JPanel north = new JPanel();
		JPanel south = new JPanel();
		north.add(filePath);
		north.add(browseBtn);
		south.add(new JLabel(" Suffix:"));
		south.add(suffix);
		south.add(changeBtn);
		south.add(exportBtn);
		this.add(north,BorderLayout.NORTH);
		this.add(south, BorderLayout.SOUTH);
		box.setEditable(false);
		box.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18) );
		JScrollPane scroll = new JScrollPane(box);
		this.add(scroll,BorderLayout.CENTER);
		this.pack();
	}

	private void initActions(){
		browseBtn.addActionListener(new BrowsingAction());
	}

	public void run(){
		this.setVisible(true);
	}

	/**
	 * ActionListener for browsing for oto.ini
	 * @author Pappim Pipatkasrira
	 * @version 1.0
	 * @since Apr 20, 2017
	 */
	public class BrowsingAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);
			int checkValid = fileChooser.showOpenDialog(null);
			filePath.setText(fileChooser.getSelectedFile().toString());
			suffixChanger = new SuffixChanger(filePath.getText());
			try {
		        byte bt[]= Files.readAllBytes(fileChooser.getSelectedFile().toPath());   
		        String lines = new String(bt);
		        box.setText(lines);
		    } catch (IOException ex) {
		        throw new RuntimeException(ex);
		    }
			pack();
		}
	}

	/**
	 * ActionListener for export oto.ini
	 * @author Pappim Pipatkasrira
	 * @version 1.0
	 * @since Apr 20, 2017
	 */
	public class ExportAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO Finish this.
		}
	}

}
