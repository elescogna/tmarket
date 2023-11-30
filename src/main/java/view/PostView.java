package view;

import entities.Student;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.post.PostController;
import interface_adapter.post.PostState;
import interface_adapter.post.PostViewModel;
import interface_adapter.posting.PostingState;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class PostView extends JPanel {
    private final GoHomeController goHomeController;
    private final PostController postController;
    private final PostViewModel postViewModel;
    private JLabel categoryLabel;
    private JLabel typeLabel;
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel conditionScoreLabel;
    private JLabel pickupAddressLabel;
    private JLabel ageLabel;
    private JLabel priceLabel;
    private JLabel imageLabel;
    private JLabel lengthLabel;
    private JLabel widthLabel;
    private JLabel heightLabel;
    private JLabel technologyBrandLabel;
    private JLabel capabilitiesLabel;
    private JLabel technologyColourLabel;
    private JLabel schoolItemBrandLabel;
    private JLabel schoolItemColourLabel;
    private JLabel clothingBrandLabel;
    private JLabel clothingColourLabel;
    private JLabel sizeLabel;
    private JLabel materialLabel;
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> typeComboBox;
    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JComboBox<Integer> conditionScoreComboBox;
    private JTextField pickupAddressTextField;
    private JTextField ageTextField;
    private JTextField priceTextField;
    private JTextField lengthTextField;
    private JTextField widthTextField;
    private JTextField heightTextField;
    private JComboBox<String> technologyBrandComboBox;
    private JTextField capabilitiesTextField;
    private JTextField technologyColourTextField;
    private JTextField schoolItemBrandTextField;
    private JTextField schoolItemColourTextField;
    private JComboBox<String> clothingColourComboBox;
    private JComboBox<String> sizeComboBox;
    private JTextField materialTextField;
    private JTextField clothingBrandTextField;
    private JButton postButton;
    private JButton backButton;
    private JLabel lblTitle;

    public PostView(GoHomeController goHomeController,
            PostController postController, PostViewModel postViewModel) {
        this.postController = postController;
        this.postViewModel = postViewModel;
        this.goHomeController = goHomeController;

        this.setLayout(null);
        initializeComponents();
        addComponents();
        addListeners();

        setVisible(true);

        postButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                System.out.println(currentState.getCategory());
                if ("Furniture".equals(currentState.getCategory())) {
                    PostView.this.postController.execute(
                            currentState.getStudent(), currentState.getCategory(),
                            currentState.getType(), currentState.getName(),
                            currentState.getDescription(), currentState.getPickupAddress(),
                            currentState.getConditionScore(), currentState.getAge(),
                            currentState.getPrice(), currentState.getLength(),
                            currentState.getWidth(), currentState.getHeight());
                } else if ("Technology".equals(currentState.getCategory())) {
                    PostView.this.postController.execute(
                            currentState.getStudent(), currentState.getCategory(),
                            currentState.getType(), currentState.getName(),
                            currentState.getDescription(), currentState.getPickupAddress(),
                            currentState.getConditionScore(), currentState.getAge(),
                            currentState.getPrice(), currentState.getBrand(),
                            currentState.getCapabilities(), currentState.getColour());
                } else if ("School Item".equals(currentState.getCategory())) {
                    // __AUTO_GENERATED_PRINTF_START__
                    System.out.println("PostView#actionPerformed#if#if#if 1"); // __AUTO_GENERATED_PRINTF_END__
                    PostView.this.postController.execute(
                            currentState.getStudent(), currentState.getCategory(),
                            currentState.getType(), currentState.getName(),
                            currentState.getDescription(), currentState.getPickupAddress(),
                            currentState.getConditionScore(), currentState.getAge(),
                            currentState.getPrice(), currentState.getBrand(),
                            currentState.getColour());
                } else if ("Clothing".equals(currentState.getCategory())) {
                    PostView.this.postController.execute(
                            currentState.getStudent(), currentState.getCategory(),
                            currentState.getType(), currentState.getName(),
                            currentState.getDescription(), currentState.getPickupAddress(),
                            currentState.getConditionScore(), currentState.getAge(),
                            currentState.getPrice(), currentState.getBrand(),
                            currentState.getColour(), currentState.getSize(),
                            currentState.getMaterial());
                } else {
                    System.out.println(
                            "Invalid category selected or all fields have not been filled");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(backButton)) {
                    PostView.this.goHomeController.execute();
                }
            }
        });

        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(categoryComboBox)) {
                    PostState currentState = PostView.this.postViewModel.getState();
                    Object item = categoryComboBox.getSelectedItem();
                    currentState.setCategory((String)item);
                    PostView.this.postViewModel.setState(currentState);
                }
            }
        });

        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(typeComboBox)) {
                    PostState currentState = PostView.this.postViewModel.getState();
                    Object item = typeComboBox.getSelectedItem();
                    currentState.setType((String)item);
                    PostView.this.postViewModel.setState(currentState);
                }
            }
        });

        nameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = nameTextField.getText() + e.getKeyChar();
                currentState.setName(text);
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        descriptionTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = descriptionTextField.getText() + e.getKeyChar();
                currentState.setDescription(text);
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        conditionScoreComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(conditionScoreComboBox)) {
                    PostState currentState = PostView.this.postViewModel.getState();
                    Object item = conditionScoreComboBox.getSelectedItem();
                    currentState.setConditionScore((int)item);
                    PostView.this.postViewModel.setState(currentState);
                }
            }
        });

        pickupAddressTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = pickupAddressTextField.getText() + e.getKeyChar();
                currentState.setPickupAddress(text);
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        ageTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = (ageTextField.getText()) + e.getKeyChar();
                currentState.setAge(Integer.parseInt(text));
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        priceTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = (priceTextField.getText()) + e.getKeyChar();
                currentState.setPrice(Integer.parseInt(text));
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        lengthTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = (lengthTextField.getText()) + e.getKeyChar();
                currentState.setLength(Double.parseDouble(text));
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        widthTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = (widthTextField.getText()) + e.getKeyChar();
                currentState.setWidth(Double.parseDouble(text));
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        heightTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = (heightTextField.getText()) + e.getKeyChar();
                currentState.setHeight(Double.parseDouble(text));
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        technologyBrandComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(technologyBrandComboBox)) {
                    PostState currentState = PostView.this.postViewModel.getState();
                    Object item = technologyBrandComboBox.getSelectedItem();
                    currentState.setBrand((String)item);
                    PostView.this.postViewModel.setState(currentState);
                }
            }
        });

        capabilitiesTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = (capabilitiesTextField.getText()) + e.getKeyChar();
                currentState.setCapabilities(text);
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        technologyColourTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = (technologyColourTextField.getText()) + e.getKeyChar();
                currentState.setColour(text);
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        schoolItemBrandTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = (schoolItemBrandTextField.getText()) + e.getKeyChar();
                currentState.setBrand(text);
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        schoolItemColourTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = (schoolItemColourTextField.getText()) + e.getKeyChar();
                currentState.setColour(text);
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        clothingBrandTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = (clothingBrandTextField.getText()) + e.getKeyChar();
                currentState.setBrand(text);
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        clothingColourComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(clothingColourComboBox)) {
                    PostState currentState = PostView.this.postViewModel.getState();
                    Object item = clothingColourComboBox.getSelectedItem();
                    currentState.setColour((String)item);
                    PostView.this.postViewModel.setState(currentState);
                }
            }
        });

        sizeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(sizeComboBox)) {
                    PostState currentState = PostView.this.postViewModel.getState();
                    Object item = sizeComboBox.getSelectedItem();
                    currentState.setSize((String)item);
                    PostView.this.postViewModel.setState(currentState);
                }
            }
        });

        materialTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PostState currentState = PostView.this.postViewModel.getState();
                String text = (materialTextField.getText()) + e.getKeyChar();
                currentState.setMaterial(text);
                PostView.this.postViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    private void initializeComponents() {
        postButton = new JButton("Post");
        postButton.setBounds(340, 329, 89, 23);

        backButton = new JButton("Back");
        backButton.setBounds(61, 329, 89, 23);

        categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(10, 78, 87, 14);
        categoryComboBox = new JComboBox<>(
                new String[] {"Furniture", "Technology", "School Item", "Clothing"});
        categoryComboBox.setBounds(94, 78, 98, 14);
        categoryComboBox.setSelectedIndex(0);

        typeLabel = new JLabel("Type:");
        typeLabel.setBounds(10, 104, 87, 14);
        typeComboBox = new JComboBox<>();
        typeComboBox.setBounds(94, 104, 98, 14);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 130, 87, 14);
        nameTextField = new JTextField();
        nameTextField.setBounds(94, 130, 100, 14);
        nameTextField.setColumns(10);

        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(10, 156, 87, 14);
        descriptionTextField = new JTextField();
        descriptionTextField.setBounds(94, 156, 100, 14);
        descriptionTextField.setColumns(10);

        conditionScoreLabel = new JLabel("Condition:");
        conditionScoreLabel.setBounds(10, 182, 109, 14);
        conditionScoreComboBox = new JComboBox<>(new Integer[] {1, 2, 3, 4, 5});
        conditionScoreComboBox.setBounds(94, 182, 100, 14);
        conditionScoreComboBox.setSelectedIndex(0);

        pickupAddressLabel = new JLabel("Pickup Address:");
        pickupAddressLabel.setBounds(10, 208, 87, 14);
        pickupAddressTextField = new JTextField();
        pickupAddressTextField.setBounds(94, 208, 100, 14);
        pickupAddressTextField.setColumns(10);

        ageLabel = new JLabel("Age:");
        ageLabel.setBounds(10, 234, 87, 14);
        ageTextField = new JTextField();
        ageTextField.setBounds(94, 234, 100, 14);
        ageTextField.setColumns(10);

        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 260, 87, 14);
        priceTextField = new JTextField();
        priceTextField.setBounds(92, 260, 100, 14);
        priceTextField.setColumns(10);

        imageLabel = new JLabel("Image:");
        imageLabel.setBounds(10, 286, 100, 14);

        lengthLabel = new JLabel("Length:");
        lengthLabel.setBounds(232, 78, 87, 14);
        lengthTextField = new JTextField();
        lengthTextField.setBounds(340, 78, 100, 14);
        lengthTextField.setColumns(10);

        widthLabel = new JLabel("Width:");
        widthLabel.setBounds(232, 104, 87, 14);
        widthTextField = new JTextField();
        widthTextField.setBounds(340, 104, 100, 14);
        widthTextField.setColumns(10);

        heightLabel = new JLabel("Height:");
        heightLabel.setBounds(232, 130, 87, 14);
        heightTextField = new JTextField();
        heightTextField.setBounds(340, 130, 100, 14);
        heightTextField.setColumns(10);

        technologyBrandLabel = new JLabel("Brand:");
        technologyBrandLabel.setBounds(232, 78, 87, 14);
        technologyBrandComboBox =
            new JComboBox<>(new String[] {"apple", "samsung", "dell", "hp",
                "xiaomi", "huawei", "lenovo", "asus"});
        technologyBrandComboBox.setSize(100, 14);
        technologyBrandComboBox.setLocation(340, 78);
        technologyBrandComboBox.setSelectedIndex(0);

        capabilitiesLabel = new JLabel("Capabilities:");
        capabilitiesLabel.setBounds(232, 104, 87, 14);
        capabilitiesTextField = new JTextField();
        capabilitiesTextField.setBounds(340, 104, 100, 14);
        capabilitiesTextField.setColumns(10);

        technologyColourLabel = new JLabel("Colour:");
        technologyColourLabel.setBounds(232, 130, 87, 14);
        technologyColourTextField = new JTextField();
        technologyColourTextField.setBounds(340, 130, 100, 14);
        technologyColourTextField.setColumns(10);

        schoolItemBrandLabel = new JLabel("Brand:");
        schoolItemBrandLabel.setBounds(232, 78, 87, 14);
        schoolItemBrandTextField = new JTextField();
        schoolItemBrandTextField.setBounds(340, 78, 100, 14);
        schoolItemBrandTextField.setColumns(10);

        schoolItemColourLabel = new JLabel("Colour:");
        schoolItemColourLabel.setBounds(232, 104, 87, 14);
        schoolItemColourTextField = new JTextField();
        schoolItemColourTextField.setBounds(340, 104, 100, 14);
        schoolItemColourTextField.setColumns(10);

        clothingBrandLabel = new JLabel("Brand:");
        clothingBrandLabel.setBounds(232, 78, 87, 14);
        clothingBrandTextField = new JTextField();
        clothingBrandTextField.setBounds(340, 78, 100, 14);
        clothingBrandTextField.setColumns(10);

        clothingColourLabel = new JLabel("Colour:");
        clothingColourLabel.setBounds(232, 104, 87, 14);
        clothingColourComboBox = new JComboBox<>(new String[] {
            "red", "black", "blue", "green", "grey", "white", "purple", "orange",
                "yellow", "brown", "beige", "pink", "multicolour"});
        clothingColourComboBox.setBounds(340, 104, 100, 14);
        clothingColourComboBox.setSelectedIndex(0);

        sizeLabel = new JLabel("Size:");
        sizeLabel.setBounds(232, 130, 87, 14);
        sizeComboBox =
            new JComboBox<>(new String[] {"XXS", "XS", "S", "M", "L", "XL", "XXL"});
        sizeComboBox.setBounds(340, 130, 100, 14);
        sizeComboBox.setSelectedIndex(0);

        materialLabel = new JLabel("Material:");
        materialLabel.setBounds(232, 156, 87, 14);
        materialTextField = new JTextField();
        materialTextField.setBounds(340, 156, 100, 14);
        materialTextField.setColumns(10);

        // Initially disable additional fields
        lengthLabel.setVisible(false);
        lengthTextField.setVisible(false);

        widthLabel.setVisible(false);
        widthTextField.setVisible(false);

        heightLabel.setVisible(false);
        heightTextField.setVisible(false);

        technologyBrandLabel.setVisible(false);
        technologyBrandComboBox.setVisible(false);

        capabilitiesLabel.setVisible(false);
        capabilitiesTextField.setVisible(false);

        technologyColourLabel.setVisible(false);
        technologyColourTextField.setVisible(false);

        schoolItemColourLabel.setVisible(false);
        schoolItemColourTextField.setVisible(false);

        schoolItemBrandLabel.setVisible(false);
        schoolItemBrandTextField.setVisible(false);

        clothingBrandLabel.setVisible(false);
        clothingBrandTextField.setVisible(false);

        clothingColourLabel.setVisible(false);
        clothingColourComboBox.setVisible(false);

        sizeLabel.setVisible(false);
        sizeComboBox.setVisible(false);

        materialLabel.setVisible(false);
        materialTextField.setVisible(false);
    }

    private void addComponents() {
        this.add(postButton);
        this.add(backButton);
        this.add(categoryLabel);
        this.add(categoryComboBox);
        this.add(typeLabel);
        this.add(typeComboBox);
        this.add(nameLabel);
        this.add(nameTextField);
        this.add(descriptionLabel);
        this.add(descriptionTextField);
        this.add(pickupAddressLabel);
        this.add(pickupAddressTextField);
        this.add(conditionScoreLabel);
        this.add(conditionScoreComboBox);
        this.add(ageLabel);
        this.add(ageTextField);
        this.add(priceLabel);
        this.add(priceTextField);
        this.add(lengthLabel);
        this.add(lengthTextField);
        this.add(widthLabel);
        this.add(widthTextField);
        this.add(heightLabel);
        this.add(heightTextField);
        this.add(imageLabel);
        this.add(technologyBrandLabel);
        this.add(technologyBrandComboBox);
        this.add(capabilitiesLabel);
        this.add(capabilitiesTextField);
        this.add(technologyColourLabel);
        this.add(technologyColourTextField);
        this.add(schoolItemBrandLabel);
        this.add(schoolItemBrandTextField);
        this.add(schoolItemColourLabel);
        this.add(schoolItemColourTextField);
        this.add(clothingBrandLabel);
        this.add(clothingBrandTextField);
        this.add(clothingColourLabel);
        this.add(clothingColourComboBox);
        this.add(sizeLabel);
        this.add(sizeComboBox);
        this.add(materialLabel);
        this.add(materialTextField);

        lblTitle = new JLabel("Post Item");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 25));
        lblTitle.setBounds(186, 35, 122, 26);
        add(lblTitle);
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
        String selectedCategory = (String)categoryComboBox.getSelectedItem();
        String[] types;

        // Customize types based on the selected category
        switch (selectedCategory) {
            case "Technology":
                types = new String[] {"Laptop",  "Phone",  "Tablet",     "Earphones",
                    "Charger", "Stylus", "Television", "Speaker"};
                break;
            case "Furniture":
                types = new String[] {"Chair",    "Table",    "Couch",      "Bedframe",
                    "Mattress", "Desk",     "Nightstand", "Lights",
                    "Shelf",    "Cupboard", "Decor"};
                break;
            case "Clothing":
                types = new String[] {"Shirt",  "Pants",  "Dress",       "Jeans", "Tops",
                    "Formal", "Jacket", "Winter Gear", "Hoodie"};
                break;
            case "School Item":
                types = new String[] {"Textbook", "Notebook", "Stationery",
                    "Pens",     "Bag",      "Calculator"};
                break;
            default:
                types = new String[0]; // Default to an empty array
                break;
        }
        typeComboBox.setModel(new DefaultComboBoxModel<>(types));
        typeComboBox.setSelectedIndex(0);
    }

    private void updateAdditionalFields() {
        String selectedCategory = (String)categoryComboBox.getSelectedItem();

        // Enable or disable additional fields based on the selected category
        lengthLabel.setVisible("Furniture".equals(selectedCategory));
        widthLabel.setVisible("Furniture".equals(selectedCategory));
        heightLabel.setVisible("Furniture".equals(selectedCategory));
        lengthTextField.setVisible("Furniture".equals(selectedCategory));
        widthTextField.setVisible("Furniture".equals(selectedCategory));
        heightTextField.setVisible("Furniture".equals(selectedCategory));
        technologyBrandLabel.setVisible("Technology".equals(selectedCategory));
        technologyBrandComboBox.setVisible("Technology".equals(selectedCategory));
        capabilitiesLabel.setVisible("Technology".equals(selectedCategory));
        capabilitiesTextField.setVisible("Technology".equals(selectedCategory));
        technologyColourLabel.setVisible("Technology".equals(selectedCategory));
        technologyColourTextField.setVisible("Technology".equals(selectedCategory));
        schoolItemBrandLabel.setVisible("SchoolItem".equals(selectedCategory));
        schoolItemBrandTextField.setVisible("SchoolItem".equals(selectedCategory));
        schoolItemColourLabel.setVisible("SchoolItem".equals(selectedCategory));
        schoolItemColourTextField.setVisible("SchoolItem".equals(selectedCategory));
        clothingBrandLabel.setVisible("Clothing".equals(selectedCategory));
        clothingBrandTextField.setVisible("Clothing".equals(selectedCategory));
        clothingColourLabel.setVisible("Clothing".equals(selectedCategory));
        clothingColourComboBox.setVisible("Clothing".equals(selectedCategory));
        sizeLabel.setVisible("Clothing".equals(selectedCategory));
        sizeComboBox.setVisible("Clothing".equals(selectedCategory));
        materialLabel.setVisible("Clothing".equals(selectedCategory));
        materialTextField.setVisible("Clothing".equals(selectedCategory));
    }
}
