package view;

import entities.Student;
import interface_adapter.home.HomeController;
import interface_adapter.post.PostContoller;
import interface_adapter.post.PostState;
import interface_adapter.post.PostViewModel;
import interface_adapter.posting.PostingState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class PostingView extends JFrame {

    private final HomeController homeController;
    private final PostContoller postContoller;
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
    private JButton btnNewButton_1;

    public PostingView(HomeController homeController, PostContoller postContoller, PostViewModel postViewModel) {
        this.homeController = homeController;
        this.postContoller = postContoller;
        this.postViewModel = postViewModel;
        getContentPane().setBackground(new Color(214, 186, 250));
        setTitle("Posting View");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        initializeComponents();
        addComponents();
        addListeners();

        setVisible(true);

        categoryComboBox.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(categoryComboBox)){
                            PostState currentState = postViewModel.getState();
                            Object item = categoryComboBox.getSelectedItem();
                            currentState.setCategory((String)item);
                            postViewModel.setState(currentState);
                        }
                    }
                }
        );

        typeComboBox.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(typeComboBox)){
                            PostState currentState = postViewModel.getState();
                            Object item = typeComboBox.getSelectedItem();
                            currentState.setType((String)item);
                            postViewModel.setState(currentState);
                        }
                    }
                }
        );

        nameTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PostState currentState = postViewModel.getState();
                        String text = nameTextField.getText() + e.getKeyChar();
                        currentState.setName(text);
                        postViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        descriptionTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PostState currentState = postViewModel.getState();
                        String text = descriptionTextField.getText() + e.getKeyChar();
                        currentState.setDescription(text);
                        postViewModel.setState(currentState);
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
                            PostState currentState = postViewModel.getState();
                            Object item = conditionScoreComboBox.getSelectedItem();
                            currentState.setConditionScore((int)item);
                            postViewModel.setState(currentState);
                        }
                    }
                }
        );

        pickupAddressTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PostState currentState = postViewModel.getState();
                        String text = pickupAddressTextField.getText() + e.getKeyChar();
                        currentState.setPickupAddress(text);
                        postViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        ageTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PostState currentState = postViewModel.getState();
                        String text = (ageTextField.getText()) + e.getKeyChar();
                        currentState.setAge(Integer.parseInt(text));
                        postViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        priceTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PostState currentState = postViewModel.getState();
                        String text = (priceTextField.getText()) + e.getKeyChar();
                        currentState.setPrice(Integer.parseInt(text));
                        postViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        lengthTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PostState currentState = postViewModel.getState();
                        String text = (lengthTextField.getText()) + e.getKeyChar();
                        currentState.setLength(Double.parseDouble(text));
                        postViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        widthTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PostState currentState = postViewModel.getState();
                        String text = (widthTextField.getText()) + e.getKeyChar();
                        currentState.setWidth(Double.parseDouble(text));
                        postViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        heightTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PostState currentState = postViewModel.getState();
                        String text = (heightTextField.getText()) + e.getKeyChar();
                        currentState.setHeight(Double.parseDouble(text));
                        postViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
    }

    private void initializeComponents() {
        categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(10, 46, 87, 14);
        categoryComboBox = new JComboBox<>(new String[]{"Furniture", "Technology", "School Item", "Clothing"});
        categoryComboBox.setBounds(94, 46, 98, 14);

        typeLabel = new JLabel("Type:");
        typeLabel.setBounds(10,71,87,14);
        typeComboBox = new JComboBox<>();
        typeComboBox.setBounds(94, 71, 98, 14);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10,96,87,14);
        nameTextField = new JTextField();
        nameTextField.setBounds(94, 96, 100, 14);
        nameTextField.setColumns(10);

        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(10,121,87,14);
        descriptionTextField = new JTextField();
        descriptionTextField.setBounds(94, 121, 100, 14);
        descriptionTextField.setColumns(10);

        conditionScoreLabel = new JLabel("Condition Score:");
        conditionScoreLabel.setBounds(10,148,87,14);
        conditionScoreComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        conditionScoreComboBox.setBounds(94, 148, 98, 14);

        pickupAddressLabel = new JLabel("Pickup Address:");
        pickupAddressLabel.setBounds(10,172,87,14);
        pickupAddressTextField = new JTextField();
        pickupAddressTextField.setBounds(94, 172, 100, 14);
        pickupAddressTextField.setColumns(10);

        ageLabel = new JLabel("Age:");
        ageLabel.setBounds(10,197,87,14);
        ageTextField = new JTextField();
        ageTextField.setBounds(94, 197, 100, 14);
        ageTextField.setColumns(10);

        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10,219,87,14);
        priceTextField = new JTextField();
        priceTextField.setBounds(94, 219, 100, 14);
        priceTextField.setColumns(10);

        imageLabel = new JLabel("Image:");
        imageLabel.setBounds(94, 244, 100, 14);
        //PRANAV HELP

        lengthLabel = new JLabel("Length:");
        lengthLabel.setBounds(232,11,87,14);
        lengthTextField = new JTextField();
        lengthTextField.setBounds(340, 11, 100, 14);
        lengthTextField.setColumns(10);

        widthLabel = new JLabel("Width:");
        widthLabel.setBounds(232,33,87,14);
        widthTextField = new JTextField();
        widthTextField.setBounds(340, 33, 100, 14);
        widthTextField.setColumns(10);

        heightLabel = new JLabel("Height:");
        heightLabel.setBounds(232,55,87,14);
        heightTextField = new JTextField();
        heightTextField.setBounds(340, 55, 100, 14);
        heightTextField.setColumns(10);

        // Initially disable additional fields
        lengthLabel.setVisible(false);
        lengthTextField.setVisible(false);

        widthLabel.setVisible(false);
        widthTextField.setVisible(false);

        heightLabel.setVisible(false);
        heightTextField.setVisible(false);
    }

    private void addComponents() {
        getContentPane().add(categoryLabel);
        getContentPane().add(categoryComboBox);
        getContentPane().add(typeLabel);
        getContentPane().add(typeComboBox);
        getContentPane().add(nameLabel);
        getContentPane().add(nameTextField);
        getContentPane().add(descriptionLabel);
        getContentPane().add(descriptionTextField);
        getContentPane().add(pickupAddressLabel);
        getContentPane().add(pickupAddressTextField);
        getContentPane().add(conditionScoreLabel);
        getContentPane().add(conditionScoreComboBox);
        getContentPane().add(ageLabel);
        getContentPane().add(ageTextField);
        getContentPane().add(priceLabel);
        getContentPane().add(priceTextField);
        getContentPane().add(lengthLabel);
        getContentPane().add(lengthTextField);
        getContentPane().add(widthLabel);
        getContentPane().add(widthTextField);
        getContentPane().add(heightLabel);
        getContentPane().add(heightTextField);
        getContentPane().add(imageLabel);


        JButton postButton = new JButton("Post"); //adding Post button
        postButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(postButton)){
                    PostingState postingState = new PostingState();
                    PostState currentState = postViewModel.getState();
                    Student student = postingState.getStudent();
                    if ("Furniture".equals(currentState.getCategory())) {
                        postContoller.execute(student, currentState.getCategory(), currentState.getType(),
                                currentState.getName(), currentState.getDescription(), currentState.getPickupAddress(), currentState.getConditionScore(),
                                currentState.getAge(), currentState.getPrice(),
                                currentState.getLength(), currentState.getWidth(), currentState.getHeight());
                    }
                    else if ("Technology".equals(currentState.getCategory())) {
                        postContoller.execute(student, currentState.getCategory(), currentState.getType(),
                                currentState.getName(), currentState.getDescription(), currentState.getPickupAddress(), currentState.getConditionScore(),
                                currentState.getAge(), currentState.getPrice(),
                                currentState.getBrand(), currentState.getCapabilities(), currentState.getColour());
                    }
                    else if ("School Item".equals(currentState.getCategory())) {
                        postContoller.execute(student, currentState.getCategory(), currentState.getType(),
                                currentState.getName(), currentState.getDescription(), currentState.getPickupAddress(), currentState.getConditionScore(),
                                currentState.getAge(), currentState.getPrice(),
                                currentState.getBrand(), currentState.getColour());
                    }
                    else if ("Clothing".equals(currentState.getCategory())) {
                        postContoller.execute(student, currentState.getCategory(), currentState.getType(),
                                currentState.getName(), currentState.getDescription(), currentState.getPickupAddress(), currentState.getConditionScore(),
                                currentState.getAge(), currentState.getPrice(),
                                currentState.getBrand(), currentState.getColour(), currentState.getSize(), currentState.getMaterial());
                    }
                    else {
                        System.out.println("Invalid category selected or all fields have not been filled");
                    }// TODO: We don't know which of the optionals need to be passed, how to pass to controller?

                }
            }
        });
        postButton.setBounds(340, 329, 89, 23);
        getContentPane().add(postButton);


        JButton backButton = new JButton("Back"); //adding Back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(backButton)){
                    PostingState postingState = new PostingState();
                    Student student = postingState.getStudent();
                    homeController.execute(student);
                }
            }
        });
        backButton.setBounds(61, 329, 89, 23);
        getContentPane().add(backButton);
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
            case "School Items":
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
        lengthLabel.setVisible("Furniture".equals(selectedCategory));
        widthLabel.setVisible("Furniture".equals(selectedCategory));
        heightLabel.setVisible("Furniture".equals(selectedCategory));
        lengthTextField.setVisible("Furniture".equals(selectedCategory));
        widthTextField.setVisible("Furniture".equals(selectedCategory));
        heightTextField.setVisible("Furniture".equals(selectedCategory));
    }

//    public static void main(String[] args) {
////        HomeInputBoundary homeInteractor = new HomeInteractor();
////        HomeController homeController = new HomeController(homeInteractor);
////        SwingUtilities.invokeLater(() -> new PostingView(homeController));
//    }
}


//add stuff