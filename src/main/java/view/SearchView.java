package view;

import entities.Student;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.home.HomeController;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executor;

public class SearchView extends JPanel implements PropertyChangeListener {
	private final SearchViewModel searchViewModel;
	private final SearchController searchController;
	private static final long serialVersionUID = 1L;
	private JTextField maximumPriceTextField;
	private JTextField maximumDistanceTextField;
	private JTextField maximumAgeTextField;
	private JComboBox<String> categoryComboBox;
	private JComboBox<String> typeComboBox;
	private JComboBox<Integer> conditionScoreComboBox;
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
	private GoHomeController goHomeController;
	private Image backgroundImage;


	/**
	 * Create the panel.
	 */
	public SearchView(SearchViewModel searchViewModel, SearchController searchController, GoHomeController goHomeController) {
		setBackground(new Color(0, 0, 0));
		this.setLayout(null);
		initializeComponents();
		addComponents();
		addButtonListeners();

		setVisible(true);

		HashMap<String, Object> currentFilters = new HashMap<>();

		this.searchViewModel = searchViewModel;
		this.searchController = searchController;
		this.goHomeController = goHomeController;

		this.searchViewModel.addPropertyChangeListener(this);

		try {
            String basePath = System.getProperty("user.dir");
            String imagePath = basePath + "/assets/images/UC2.png";
			backgroundImage = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lblNewLabel = new JLabel("Search");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 26));
		lblNewLabel.setBounds(435, 69, 131, 58);
		add(lblNewLabel);

		categoryComboBox.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().equals(categoryComboBox)){
						SearchState currentState = searchViewModel.getState();
						// HashMap<String, Object> currentFilters = currentState.getFilterChoices();
						Object selectedItem = categoryComboBox.getSelectedItem();
						currentFilters.put("category", (String) selectedItem);
						currentState.setFilterChoices(currentFilters);
						searchViewModel.setState(currentState);
						updateTypeComboBox();
						updateAdditionalFields();
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
						// HashMap<String, Object> currentFilters = currentState.getFilterChoices();
						Object selectedItem = typeComboBox.getSelectedItem();
						currentFilters.put("type", (String) selectedItem);
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
//					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("price", Integer.parseInt(text));
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
					// HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("distanceRange", Double.parseDouble(text));
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
//					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("age", Integer.parseInt(text));
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

		conditionScoreComboBox.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().equals(conditionScoreComboBox)){
						SearchState currentState = searchViewModel.getState();
//						HashMap<String, Object> currentFilters = currentState.getFilterChoices();
						Object selectedItem = conditionScoreComboBox.getSelectedItem();
						currentFilters.put("conditionScore", (Integer) selectedItem);
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
//					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("minLength", Integer.parseInt(text));
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
//					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("maxLength", Integer.parseInt(text));
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
//					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("minWidth", Integer.parseInt(text));
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
//					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("maxWidth", Integer.parseInt(text));
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
//					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("minHeight", Integer.parseInt(text));
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
//					HashMap<String, Object> currentFilters = currentState.getFilterChoices();
					currentFilters.put("maxHeight", Integer.parseInt(text));
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
//						HashMap<String, Object> currentFilters = currentState.getFilterChoices();
						Object selectedItem = brandComboBox.getSelectedItem();
						currentFilters.put("brand", (String) selectedItem);
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
//						HashMap<String, Object> currentFilters = currentState.getFilterChoices();
						Object selectedItem = colourComboBox.getSelectedItem();
						currentFilters.put("colour", (String) selectedItem);
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
//						HashMap<String, Object> currentFilters = currentState.getFilterChoices();
						Object selectedItem = sizeComboBox.getSelectedItem();
						currentFilters.put("size", (String) selectedItem);
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
		submitButton.setBounds(689, 698, 100, 40);
		backButton = new JButton("Back");
		backButton.setBounds(121, 698, 100, 40);

		categoryLabel = new JLabel("Category:");
		categoryLabel.setForeground(new Color(255, 255, 255));
		categoryLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		categoryLabel.setBounds(63, 173, 80, 25);
		categoryComboBox = new JComboBox<>(new String[]{"Furniture", "Technology", "SchoolItem", "Clothing"});
		categoryComboBox.setBounds(250, 175, 185, 25);

		typeLabel = new JLabel("Type:");
		typeLabel.setForeground(new Color(255, 255, 255));
		typeLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		typeLabel.setBounds(63, 237, 80, 25);
		typeComboBox = new JComboBox<>();
		typeComboBox.setBounds(250, 239, 185, 25);

		conditionScoreLabel = new JLabel("Condition Score");
		conditionScoreLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		conditionScoreLabel.setForeground(new Color(255, 255, 255));
		conditionScoreLabel.setBounds(63, 312, 134, 25);
		conditionScoreComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
		conditionScoreComboBox.setBounds(250, 314, 185, 25);

		maximumPriceLabel = new JLabel("Maximum price:");
		maximumPriceLabel.setForeground(new Color(255, 255, 255));
		maximumPriceLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		maximumPriceLabel.setBounds(63, 396, 134, 25);
		maximumPriceTextField = new JTextField();
		maximumPriceTextField.setBounds(250, 398, 185, 25);

		maximumAgeLabel = new JLabel("Maximum age (years)");
		maximumAgeLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		maximumAgeLabel.setForeground(new Color(255, 255, 255));
		maximumAgeLabel.setBounds(63, 471, 185, 25);
		maximumAgeTextField = new JTextField();
		maximumAgeTextField.setBounds(250, 473, 185, 25);

		maximumDistanceLabel = new JLabel("Maximum Distance (m)");
		maximumDistanceLabel.setForeground(new Color(255, 255, 255));
		maximumDistanceLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		maximumDistanceLabel.setBounds(63, 549, 180, 25);
		maximumDistanceTextField = new JTextField();
		maximumDistanceTextField.setBounds(250, 551, 185, 25);

		// Additional furniture fields
		minLengthLabel = new JLabel("Minimum Length:");
		minLengthLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		minLengthLabel.setForeground(new Color(255, 255, 255));
		minLengthLabel.setBounds(510, 173, 160, 25);
		minLengthTextField = new JTextField();
		minLengthTextField.setBounds(689, 173, 185, 25);
		minWidthLabel = new JLabel("Minimum Width:");
		minWidthLabel.setForeground(new Color(255, 255, 255));
		minWidthLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		minWidthLabel.setBounds(510, 237, 160, 25);
		minWidthTextField = new JTextField();
		minWidthTextField.setBounds(689, 239, 185, 25);
		minHeightLabel = new JLabel("Minimum Height:");
		minHeightLabel.setForeground(new Color(255, 255, 255));
		minHeightLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		minHeightLabel.setBounds(510, 312, 160, 25);
		minHeightTextField = new JTextField();
		minHeightTextField.setBounds(689, 314, 185, 25);
		maxLengthLabel = new JLabel("Maximum Length:");
		maxLengthLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		maxLengthLabel.setForeground(new Color(255, 255, 255));
		maxLengthLabel.setBounds(510, 396, 160, 25);
		maxLengthTextField = new JTextField();
		maxLengthTextField.setBounds(689, 398, 185, 25);
		maxWidthLabel = new JLabel("Maximum Width:");
		maxWidthLabel.setForeground(new Color(255, 255, 255));
		maxWidthLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		maxWidthLabel.setBounds(510, 471, 160, 25);
		maxWidthTextField = new JTextField();
		maxWidthTextField.setBounds(689, 473, 185, 25);
		maxHeightLabel = new JLabel("Maximum Height:");
		maxHeightLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		maxHeightLabel.setForeground(new Color(255, 255, 255));
		maxHeightLabel.setBounds(510, 549, 160, 25);
		maxHeightTextField = new JTextField();
		maxHeightTextField.setBounds(689, 551, 185, 25);

		// additional technology features:
		brandLabel = new JLabel("Brand:");
		brandLabel.setForeground(new Color(255, 255, 255));
		brandLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		brandLabel.setBounds(510, 175, 160, 25);
		brandComboBox = new JComboBox<>(new String[]{"Apple", "Samsung", "Huawei",
				"Dell", "HP", "Xiaomi", "Lenovo", "Asus"});
		brandComboBox.setBounds(689, 173, 185, 25);

		// additional clothing features:
		colourLabel = new JLabel("Colour:");
		colourLabel.setForeground(new Color(255, 255, 255));
		colourLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		colourLabel.setBounds(510, 175, 160, 25);
		colourComboBox = new JComboBox<>(new String[]{"Red", "Black", "Green", "Blue", "Grey", "White",
				"Orange", "Purple", "Yellow", "Brown", "Beige", "Pink", "Multicolour"});
		colourComboBox.setBounds(689, 173, 185, 25);
		sizeLabel = new JLabel("Size:");
		sizeLabel.setForeground(new Color(255, 255, 255));
		sizeLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		sizeLabel.setBounds(510, 237, 160, 25);
		sizeComboBox = new JComboBox<>(new String[]{"XXS", "XS", "S", "M", "L", "XL", "XXL"});
		sizeComboBox.setBounds(689, 239, 185, 25);


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
		add(conditionScoreComboBox);
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

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                SearchView.this.goHomeController.execute();
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
			case "SchoolItem":
				types = new String[]{"Textbook", "Notebook", "Stationery", "Pens", "Bag", "Calculator"};
				break;
			default:
				types = new String[0]; // Default to an empty array
				break;
		}
		typeComboBox.setModel(new DefaultComboBoxModel<>(types));
		typeComboBox.setSelectedIndex(0);
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
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

	}
}
