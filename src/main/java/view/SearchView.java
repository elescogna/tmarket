package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField maximumPriceTextField;
	private JTextField maximumDistanceTextField;
	private JTextField maximumAgeTextField;
	private JComboBox<String> categoryComboBox;
	private JComboBox<String> typeComboBox;
	private JComboBox<Integer> conditionComboBox;
	private JTextField minLengthTextField;
	private JTextField minWidthTextField;
	private JTextField minHeightTextField;
	private JTextField maxLengthTextField;
	private JTextField maxWidthTextField;
	private JTextField maxHeightTextField;
	private JLabel minLengthLabel;
	private JLabel minWidthLabel;
	private JLabel minHeightLabel;
	private JLabel maxLengthLabel;
	private JLabel maxWidthLabel;
	private JLabel maxHeightLabel;
	private JLabel categoryLabel;
	private JLabel typeLabel;
	private JLabel maximumPriceLabel;
	private JLabel maximumAgeLabel;
	private JLabel maximumDistanceLabel;
	private JLabel conditionScoreLabel;
	

	/**
	 * Create the panel.
	 */
	public SearchView() {
		setTitle("Search View");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		initializeComponents();
		addComponents();
		addListeners();

		setVisible(true);
	}

	private void initializeComponents() {
		categoryLabel = new JLabel("Category:");
		categoryLabel.setBounds(10, 10, 80, 14);
		categoryComboBox = new JComboBox<>(new String[]{"Furniture", "Technology", "School Item", "Clothing"});
		categoryComboBox.setBounds(110, 10, 80, 14);
		
		typeLabel = new JLabel("Type:");
		typeLabel.setBounds(10, 78, 80, 14);
		typeComboBox = new JComboBox<>();
		typeComboBox.setBounds(110, 78, 80, 14);
		
		conditionScoreLabel = new JLabel("Condition Score");
		conditionScoreLabel.setBounds(10, 146, 80, 14);
		conditionComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
		conditionComboBox.setBounds(110, 146, 80, 14);
		
		maximumPriceLabel = new JLabel("Maximum price:");
		maximumPriceLabel.setBounds(10, 214, 80, 14);
		maximumPriceTextField = new JTextField();
		maximumPriceTextField.setBounds(110, 214, 80, 14);
		
		maximumAgeLabel = new JLabel("Maximum age (years)");
		maximumAgeLabel.setBounds(10, 282, 80, 14);
		maximumAgeTextField = new JTextField();
		maximumAgeTextField.setBounds(110, 282, 80, 14);
		
		maximumDistanceLabel = new JLabel("Maximum Distance");
		maximumDistanceLabel.setBounds(10, 350, 80, 14);
		maximumDistanceTextField = new JTextField();
		maximumDistanceTextField.setBounds(110, 350, 80, 14);

		// Additional furniture fields
		minLengthLabel = new JLabel("Minimum Length:");
		minLengthLabel.setBounds(210, 10, 160, 14);
		minLengthTextField = new JTextField();
		minLengthTextField.setBounds(350, 10, 80, 14);
		minWidthLabel = new JLabel("Minimum Width:");
		minWidthLabel.setBounds(210, 80, 160, 14);
		minWidthTextField = new JTextField();
		minWidthTextField.setBounds(350, 80, 80, 14);
		minHeightLabel = new JLabel("Minimum Height:");
		minHeightLabel.setBounds(210, 130, 160, 14);
		minHeightTextField = new JTextField();
		minHeightTextField.setBounds(350, 130, 80, 14);
		maxLengthLabel = new JLabel("Maximum Length");
		maxLengthLabel.setBounds(210, 180, 160, 14);
		maxLengthTextField = new JTextField();
		maxLengthTextField.setBounds(350, 180, 80, 14);
		maxWidthLabel = new JLabel("Maximum Width:");
		maxWidthLabel.setBounds(210, 230, 160, 14);
		maxWidthTextField = new JTextField();
		maxWidthTextField.setBounds(350, 230, 80, 14);
		maxHeightLabel = new JLabel("Maximum Height:");
		maxHeightLabel.setBounds(210, 280, 160, 14);
		maxHeightTextField = new JTextField();
		maxHeightTextField.setBounds(350, 280, 80, 14);

		// Initially disable additional fields
		minLengthTextField.setVisible(false);
		minWidthTextField.setVisible(false);
		minHeightTextField.setVisible(false);
		maxLengthTextField.setVisible(false);
		maxWidthTextField.setVisible(false);
		maxHeightTextField.setVisible(false);
		minLengthLabel.setVisible(false);
		minWidthLabel.setVisible(false);
		minHeightLabel.setVisible(false);
		maxLengthLabel.setVisible(false);
		maxWidthLabel.setVisible(false);
		maxHeightLabel.setVisible(false);
	}

	private void addComponents() {
		// general filters
		add(categoryLabel);
		add(categoryComboBox);
		add(typeLabel);
		add(typeComboBox);
		add(conditionScoreLabel);
		add(conditionComboBox);
		add(maximumAgeLabel);
		add(maximumAgeTextField);
		add(maximumPriceLabel);
		add(maximumPriceTextField);
		add(maximumDistanceLabel);
		add(maximumDistanceTextField);
		
		// furniture filters
		add(minLengthLabel);
		add(minLengthTextField);
		add(minWidthLabel);
		add(minWidthTextField);
		add(minHeightLabel);
		add(minHeightTextField);
		add(maxLengthLabel);
		add(maxLengthTextField);
		add(maxWidthLabel);
		add(maxWidthTextField);
		add(maxHeightLabel);
		add(maxHeightTextField);
	}

	private void addListeners() {
		categoryComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTypeComboBox();
				updateAdditionalFields();
			}
		});
	}

	private void updateTypeComboBox() {
		String selectedCategory = (String) categoryComboBox.getSelectedItem();
		String[] types;

		// Customize types based on the selected category
		switch (selectedCategory) {
			case "Technology":
				types = new String[]{"Laptop", "Phone", "Tablet", "Earphones", "Charger",
				"Stylus", "Television", "Speaker"};
				break;
			case "Furniture":
				types = new String[]{"Chair", "Table", "Couch", "Bedframe", "Mattress", "Desk",
				"Nightstand", "Lights", "Shelf", "Cupboard", "Decor"};
				break;
			case "Clothing":
				types = new String[]{"Shirt", "Pants", "Dress", "Jeans", "Tops", "Formal",
				"Jacket", "Winter Gear", "Hoodie"};
				break;
			case "School Item":
				types = new String[]{"Textbook", "Notebook", "Stationery", "Pens", "Bag", "Calculator"};
				break;
			default:
				types = new String[0]; // Default to an empty array
				break;
		}

		typeComboBox.setModel(new DefaultComboBoxModel<>(types));
	}

	private void updateAdditionalFields() {
		String selectedCategory = (String) categoryComboBox.getSelectedItem();

		// Enable or disable additional fields based on the selected category
		minLengthTextField.setVisible("Furniture".equals(selectedCategory));
		minWidthTextField.setVisible("Furniture".equals(selectedCategory));
		minHeightTextField.setVisible("Furniture".equals(selectedCategory));
		minLengthLabel.setVisible("Furniture".equals(selectedCategory));
		minWidthLabel.setVisible("Furniture".equals(selectedCategory));
		minHeightLabel.setVisible("Furniture".equals(selectedCategory));
		maxLengthTextField.setVisible("Furniture".equals(selectedCategory));
		maxWidthTextField.setVisible("Furniture".equals(selectedCategory));
		maxHeightTextField.setVisible("Furniture".equals(selectedCategory));
		maxLengthLabel.setVisible("Furniture".equals(selectedCategory));
		maxWidthLabel.setVisible("Furniture".equals(selectedCategory));
		maxHeightLabel.setVisible("Furniture".equals(selectedCategory));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new SearchView());
	}

}
