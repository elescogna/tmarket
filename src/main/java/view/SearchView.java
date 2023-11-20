package view;

import entities.Student;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class SearchView extends JFrame {
	private final SearchViewModel searchViewModel;
	private final SearchController searchController;
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
	private JLabel brandLabel;
	private JComboBox brandComboBox;
	private JLabel colourLabel;
	private JComboBox colourComboBox;
	private JLabel sizeLabel;
	private JComboBox sizeComboBox;
	private JButton submitButton;
	private JButton backButton;

	/**
	 * Create the panel.
	 */
	public SearchView(SearchViewModel searchViewModel, SearchController searchController) {
		setTitle("Search View");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		initializeComponents();
		addComponents();
		addButtonListeners();

		setVisible(true);

		this.searchViewModel = searchViewModel;
		this.searchController = searchController;

		categoryComboBox.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().equals(categoryComboBox)){
						SearchState currentState = searchViewModel.getState();
						HashMap<String, Object> currentFilters = currentState.getFilterChoices();
						Object selectedItem = categoryComboBox.getSelectedItem();
						currentFilters.put("category", selectedItem);
						currentState.setFilterChoices(currentFilters);
						searchViewModel.setState(currentState);
					}
				}
			}
		);

		typeComboBox.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().equals(typeComboBox)){
						SearchState currentState = searchViewModel.getState();
						HashMap<String, Object> currentFilters = currentState.getFilterChoices();
						Object selectedItem = typeComboBox.getSelectedItem();
						currentFilters.put("type", selectedItem);
						currentState.setFilterChoices(currentFilters);
						searchViewModel.setState(currentState);
					}
				}
			}
		);

		maximumPriceTextField.addKeyListener(
			new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					SearchState currentState = searchViewModel.getState();
					String text = maximumPriceTextField.getText() + e.getKeyChar();
					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("price", text);
					currentState.setFilterChoices(currentFilters);
					searchViewModel.setState(currentState);
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
			});

		maximumDistanceTextField.addKeyListener(
			new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					SearchState currentState = searchViewModel.getState();
					String text = maximumDistanceTextField.getText() + e.getKeyChar();
					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("distanceRange", text);
					currentState.setFilterChoices(currentFilters);
					searchViewModel.setState(currentState);
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
			});

		maximumAgeTextField.addKeyListener(
			new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					SearchState currentState = searchViewModel.getState();
					String text = maximumAgeTextField.getText() + e.getKeyChar();
					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("age", text);
					currentState.setFilterChoices(currentFilters);
					searchViewModel.setState(currentState);
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
			});

		conditionComboBox.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().equals(conditionComboBox)){
						SearchState currentState = searchViewModel.getState();
						HashMap<String, Object> currentFilters = currentState.getFilterChoices();
						Object selectedItem = conditionComboBox.getSelectedItem();
						currentFilters.put("condition", selectedItem);
						currentState.setFilterChoices(currentFilters);
						searchViewModel.setState(currentState);
					}
				}
			}
		);

		minLengthTextField.addKeyListener(
			new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					SearchState currentState = searchViewModel.getState();
					String text = minLengthTextField.getText() + e.getKeyChar();
					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("minLength", text);
					currentState.setFilterChoices(currentFilters);
					searchViewModel.setState(currentState);
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
			});

		maxLengthTextField.addKeyListener(
			new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					SearchState currentState = searchViewModel.getState();
					String text = maxLengthTextField.getText() + e.getKeyChar();
					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("maxLength", text);
					currentState.setFilterChoices(currentFilters);
					searchViewModel.setState(currentState);
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
			});

		minWidthTextField.addKeyListener(
			new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					SearchState currentState = searchViewModel.getState();
					String text = minWidthTextField.getText() + e.getKeyChar();
					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("minWidth", text);
					currentState.setFilterChoices(currentFilters);
					searchViewModel.setState(currentState);
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
			});

		maxWidthTextField.addKeyListener(
			new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					SearchState currentState = searchViewModel.getState();
					String text = maxWidthTextField.getText() + e.getKeyChar();
					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("maxWidth", text);
					currentState.setFilterChoices(currentFilters);
					searchViewModel.setState(currentState);
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
			});

		minHeightTextField.addKeyListener(
			new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					SearchState currentState = searchViewModel.getState();
					String text = minHeightTextField.getText() + e.getKeyChar();
					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("minHeight", text);
					currentState.setFilterChoices(currentFilters);
					searchViewModel.setState(currentState);
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
			});

		maxHeightTextField.addKeyListener(
			new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					SearchState currentState = searchViewModel.getState();
					String text = maxHeightTextField.getText() + e.getKeyChar();
					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("maxHeight", text);
					currentState.setFilterChoices(currentFilters);
					searchViewModel.setState(currentState);
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
			});

		brandComboBox.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().equals(brandComboBox)){
						SearchState currentState = searchViewModel.getState();
						HashMap<String, Object> currentFilters = currentState.getFilterChoices();
						Object selectedItem = brandComboBox.getSelectedItem();
						currentFilters.put("brand", selectedItem);
						currentState.setFilterChoices(currentFilters);
						searchViewModel.setState(currentState);
					}
				}
			}
		);

		colourComboBox.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().equals(colourComboBox)){
						SearchState currentState = searchViewModel.getState();
						HashMap<String, Object> currentFilters = currentState.getFilterChoices();
						Object selectedItem = colourComboBox.getSelectedItem();
						currentFilters.put("colour", selectedItem);
						currentState.setFilterChoices(currentFilters);
						searchViewModel.setState(currentState);
					}
				}
			}
		);

		sizeComboBox.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().equals(sizeComboBox)){
						SearchState currentState = searchViewModel.getState();
						HashMap<String, Object> currentFilters = currentState.getFilterChoices();
						Object selectedItem = sizeComboBox.getSelectedItem();
						currentFilters.put("size", selectedItem);
						currentState.setFilterChoices(currentFilters);
						searchViewModel.setState(currentState);
					}
				}
			}
		);
	}

	private void initializeComponents() {
		// Add the Submit and Back buttons
		submitButton = new JButton("Submit");
		submitButton.setBounds(400, 500, 100, 40);
		backButton = new JButton("Back");
		backButton.setBounds(100, 500, 100, 40);

		categoryLabel = new JLabel("Category:");
		categoryLabel.setBounds(10, 50, 80, 25);
		categoryComboBox = new JComboBox<>(new String[]{"Furniture", "Technology", "School Item", "Clothing"});
		categoryComboBox.setBounds(110, 50, 80, 25);

		typeLabel = new JLabel("Type:");
		typeLabel.setBounds(10, 100, 80, 25);
		typeComboBox = new JComboBox<>();
		typeComboBox.setBounds(110, 100, 80, 25);

		conditionScoreLabel = new JLabel("Condition Score");
		conditionScoreLabel.setBounds(10, 150, 80, 25);
		conditionComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
		conditionComboBox.setBounds(110, 150, 80, 25);

		maximumPriceLabel = new JLabel("Maximum price:");
		maximumPriceLabel.setBounds(10, 200, 80, 25);
		maximumPriceTextField = new JTextField();
		maximumPriceTextField.setBounds(110, 200, 80, 25);

		maximumAgeLabel = new JLabel("Maximum age (years)");
		maximumAgeLabel.setBounds(10, 250, 80, 25);
		maximumAgeTextField = new JTextField();
		maximumAgeTextField.setBounds(110, 250, 80, 25);

		maximumDistanceLabel = new JLabel("Maximum Distance");
		maximumDistanceLabel.setBounds(10, 300, 80, 25);
		maximumDistanceTextField = new JTextField();
		maximumDistanceTextField.setBounds(110, 300, 80, 25);

		// Additional furniture fields
		minLengthLabel = new JLabel("Minimum Length:");
		minLengthLabel.setBounds(210, 50, 160, 25);
		minLengthTextField = new JTextField();
		minLengthTextField.setBounds(350, 50, 80, 25);
		minWidthLabel = new JLabel("Minimum Width:");
		minWidthLabel.setBounds(210, 100, 160, 25);
		minWidthTextField = new JTextField();
		minWidthTextField.setBounds(350, 100, 80, 25);
		minHeightLabel = new JLabel("Minimum Height:");
		minHeightLabel.setBounds(210, 150, 160, 25);
		minHeightTextField = new JTextField();
		minHeightTextField.setBounds(350, 150, 80, 25);
		maxLengthLabel = new JLabel("Maximum Length");
		maxLengthLabel.setBounds(210, 200, 160, 25);
		maxLengthTextField = new JTextField();
		maxLengthTextField.setBounds(350, 200, 80, 25);
		maxWidthLabel = new JLabel("Maximum Width:");
		maxWidthLabel.setBounds(210, 250, 160, 25);
		maxWidthTextField = new JTextField();
		maxWidthTextField.setBounds(350, 250, 80, 25);
		maxHeightLabel = new JLabel("Maximum Height:");
		maxHeightLabel.setBounds(210, 300, 160, 25);
		maxHeightTextField = new JTextField();
		maxHeightTextField.setBounds(350, 300, 80, 25);

		// additional technology features:
		brandLabel = new JLabel("Brand:");
		brandLabel.setBounds(210, 50, 160, 25);
		brandComboBox = new JComboBox<>(new String[]{"Apple", "Samsung", "Huawei",
				"Dell", "HP", "Xiaomi", "Lenovo", "Asus"});
		brandComboBox.setBounds(350, 50, 80, 25);

		// additional clothing features:
		colourLabel = new JLabel("Colour:");
		colourLabel.setBounds(210, 50, 160, 25);
		colourComboBox = new JComboBox<>(new String[]{"Red", "Black", "Green", "Blue", "Grey", "White",
				"Orange", "Purple", "Yellow", "Brown", "Beige", "Pink", "Multicolour"});
		colourComboBox.setBounds(350, 50, 80, 25);
		sizeLabel = new JLabel("Size:");
		sizeLabel.setBounds(210, 100, 160, 25);
		sizeComboBox = new JComboBox<>(new String[]{"XXS", "XS", "S", "M", "L", "XL", "XXL"});
		sizeComboBox.setBounds(350, 100, 80, 25);


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
		brandLabel.setVisible(false);
		brandComboBox.setVisible(false);
		colourLabel.setVisible(false);
		colourComboBox.setVisible(false);
		sizeLabel.setVisible(false);
		sizeComboBox.setVisible(false);
	}

	private void addComponents() {
		// Submit and Back buttons
		add(submitButton);
		add(backButton);

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

		// technology filters
		add(brandLabel);
		add(brandComboBox);

		// clothing filters
		add(colourLabel);
		add(colourComboBox);
		add(sizeLabel);
		add(sizeComboBox);
	}

	private void addButtonListeners() {

		// make view dynamic depending on category chosen
		categoryComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTypeComboBox();
				updateAdditionalFields();
			}
		});

		// implement the submit button
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (evt.getSource().equals(submitButton)) {
					SearchState currentState = searchViewModel.getState();
					HashMap<String, Object> filteredAttributes = currentState.getFilterChoices();
					Student currentStudent = currentState.getCurrentStudent();

					searchController.execute(filteredAttributes, currentStudent);
				}
			}
		});

		// implement the back button
		// TODO: this is not doing the right thing
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if (evt.getSource().equals(backButton)) {
					SearchState currentState = searchViewModel.getState();
					HashMap<String, Object> filteredAttributes = currentState.getFilterChoices();
					Student currentStudent = currentState.getCurrentStudent();

					searchController.execute(filteredAttributes, currentStudent);
				}
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

		// furniture enable
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

		// technology enable
		brandLabel.setVisible("Technology".equals(selectedCategory));
		brandComboBox.setVisible("Technology".equals(selectedCategory));

		// clothing enable
		colourLabel.setVisible("Clothing".equals(selectedCategory));
		colourComboBox.setVisible("Clothing".equals(selectedCategory));
		sizeLabel.setVisible("Clothing".equals(selectedCategory));
		sizeComboBox.setVisible("Clothing".equals(selectedCategory));
	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> new SearchView());
//	}

}