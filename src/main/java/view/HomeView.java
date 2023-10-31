package view;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class HomeView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HomeView() {
		this.setLayout(null);
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(316, 97, 124, 47);
		add(btnNewButton_1);
		
		JButton btnNewButton_1_2 = new JButton("New button");
		btnNewButton_1_2.setBounds(316, 213, 124, 47);
		add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("New button");
		btnNewButton_1_3.setBounds(316, 155, 124, 47);
		add(btnNewButton_1_3);
		
		JLabel lblNewLabel = new JLabel("TmarkeT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 33));
		lblNewLabel.setBounds(10, 22, 430, 37);
		add(lblNewLabel);
		

		JList list = new JList();
		
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setBounds(21, 70, 285, 219);
		add(listScrollPane);

	}
}
