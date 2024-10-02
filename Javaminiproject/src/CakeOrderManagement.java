import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CakeOrderManagement extends JFrame {

    private JComboBox<String> cakeComboBox;
    private JSpinner quantitySpinner;
    private ButtonGroup sizeGroup;
    private JRadioButton smallRadio;
    private JRadioButton mediumRadio;
    private JCheckBox discountCheckBox;
    private JTextField discountField, customerNameField ,cakeMessageField;
    private JTextField dateField;
    private JSpinner deliveryTimeSpinner;
    private JTextArea reportArea;
    private DefaultListModel<String> orderListModel;
    private JList<String> orderList;
    private final Image backgroundImage;

    private final HashMap<String, Double> cakePrices;
    private final ArrayList<Order> orders;

    // Define custom color
    private final Color customColor = new Color(99, 8, 70);  // #7d0760

    public CakeOrderManagement() {
        // Load the background image
        backgroundImage = new ImageIcon("/Users/caressecorreia/Downloads/CAKE TOWN-6.jpg").getImage();  // Provide the path to your image

        // Cake prices initialization
        cakePrices = new HashMap<>();
        cakePrices.put("Chocolate", 500.0);
        cakePrices.put("Vanilla", 400.0);
        cakePrices.put("Strawberry", 450.0);
        cakePrices.put("Red Velvet", 550.0);
        cakePrices.put("Black Forest", 600.0);
        cakePrices.put("Pineapple", 400.0);
        cakePrices.put("Carrot", 550.0);
        cakePrices.put("Cheesecake", 650.0);

        // Initialize order list
        orders = new ArrayList<>();

        // Setting up the frame
        setTitle("WELCOME TO CAKE TOWN");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set custom content pane with background image
        setContentPane(new BackgroundPanel());

        // Initialize UI components
        initComponents();

        // Set frame visibility
        setVisible(true);
    }

    // Panel to draw background image
    class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void initComponents() {
        // Cake Selection Panel
        JPanel cakeSelectionPanel = new JPanel(new GridBagLayout());
        cakeSelectionPanel.setOpaque(false);  // Make the panel transparent to show the background image

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Customer name
        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setForeground(customColor);
        customerNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        customerNameField = new JTextField(15);

        // Date
        JLabel dateLabel = new JLabel("Date :");
        dateLabel.setForeground(customColor);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dateField = new JTextField(15);
        dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        dateField.setEditable(false);

        // Delivery Time
        JLabel deliveryTimeLabel = new JLabel("Delivery Time:");
        deliveryTimeLabel.setForeground(customColor);
        deliveryTimeLabel.setFont(new Font("Arial", Font.BOLD, 14));
// Initialize delivery time spinner
        SpinnerDateModel timeModel = new SpinnerDateModel();
        deliveryTimeSpinner = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(deliveryTimeSpinner, "HH:mm");
        deliveryTimeSpinner.setEditor(timeEditor);
        deliveryTimeSpinner.setValue(new Date()); // Set current time as default


        // Cake type selection
        JLabel cakeLabel = new JLabel("Select Cake:");
        cakeLabel.setForeground(customColor);
        cakeLabel.setFont(new Font("Arial", Font.BOLD, 14));

        String[] cakes = {"Chocolate", "Vanilla", "Strawberry", "Red Velvet", "Black Forest", "Pineapple", "Carrot", "Cheesecake"};
        cakeComboBox = new JComboBox<>(cakes);

        // Quantity selection
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setForeground(customColor);
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        // Size selection
        JLabel sizeLabel = new JLabel("Select Size:");
        sizeLabel.setForeground(customColor);
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        smallRadio = new JRadioButton("Small");
        mediumRadio = new JRadioButton("Medium", true);
        var largeRadio = new JRadioButton("Large");


        sizeGroup = new ButtonGroup();
        sizeGroup.add(smallRadio);
        sizeGroup.add(mediumRadio);
        sizeGroup.add(largeRadio);

        JPanel sizePanel = new JPanel(new FlowLayout());
        sizePanel.setOpaque(false);
        sizePanel.add(smallRadio);
        sizePanel.add(mediumRadio);
        sizePanel.add(largeRadio);

        // Message on Cake
        JLabel messageLabel = new JLabel("Message on Cake:");
        messageLabel.setForeground(customColor);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cakeMessageField = new JTextField(15);

        // Adding components to the grid layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        cakeSelectionPanel.add(customerNameLabel, gbc);
        gbc.gridx = 1;
        cakeSelectionPanel.add(customerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        cakeSelectionPanel.add(dateLabel, gbc);
        gbc.gridx = 1;
        cakeSelectionPanel.add(dateField, gbc);

        gbc.gridx = 2;
        cakeSelectionPanel.add(deliveryTimeLabel, gbc);
        gbc.gridx = 3;
        cakeSelectionPanel.add(deliveryTimeSpinner, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        cakeSelectionPanel.add(cakeLabel, gbc);
        gbc.gridx = 1;
        cakeSelectionPanel.add(cakeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        cakeSelectionPanel.add(quantityLabel, gbc);
        gbc.gridx = 1;
        cakeSelectionPanel.add(quantitySpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        cakeSelectionPanel.add(sizeLabel, gbc);
        gbc.gridx = 1;
        cakeSelectionPanel.add(sizePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        cakeSelectionPanel.add(messageLabel, gbc);
        gbc.gridx = 1;
        cakeSelectionPanel.add(cakeMessageField, gbc);


        // Discount Panel
        JPanel discountPanel = new JPanel(new FlowLayout());
        discountPanel.setOpaque(false);
        discountCheckBox = new JCheckBox("Apply Discount");
        discountCheckBox.setOpaque(false);
        discountCheckBox.setForeground(customColor);
        discountField = new JTextField(5);
        discountField.setEnabled(false);

        discountCheckBox.addActionListener(_ -> discountField.setEnabled(discountCheckBox.isSelected()));

        discountPanel.add(discountCheckBox);
        discountPanel.add(new JLabel("Discount (%)"));
        discountPanel.add(discountField);

        // Order List Panel
        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        JScrollPane orderScrollPane = new JScrollPane(orderList);

        JButton addButton = new JButton("Add to Order");
        var removeButton = new JButton("Remove Selected");

        addButton.addActionListener(_ -> addOrder());
        removeButton.addActionListener(_ -> removeOrder());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.add(orderScrollPane, BorderLayout.CENTER);
        orderPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Report Panel
        JPanel reportPanel = new JPanel(new BorderLayout());
        reportArea = new JTextArea(18, 30);
        reportArea.setEditable(false);
        JScrollPane reportScrollPane = new JScrollPane(reportArea);
        var generateReportButton = new JButton("Generate Report");

        generateReportButton.addActionListener(_ -> generateReport());

        reportPanel.add(reportScrollPane, BorderLayout.CENTER);
        reportPanel.add(generateReportButton, BorderLayout.SOUTH);

        // Set transparency for panels
        orderPanel.setOpaque(false);
        reportPanel.setOpaque(false);

        // Layout Setup
        add(cakeSelectionPanel, BorderLayout.NORTH);
        add(discountPanel, BorderLayout.CENTER);
        add(orderPanel, BorderLayout.WEST);
        add(reportPanel, BorderLayout.EAST);
    }

    // Add order to list and store order details
    private void addOrder() {
        String customerName = customerNameField.getText();
        String cake = (String) cakeComboBox.getSelectedItem();
        int quantity = (int) quantitySpinner.getValue();
        String size = getSelectedSize();
        String date = dateField.getText();
        // Get delivery time
        Date deliveryDate = (Date) deliveryTimeSpinner.getValue();
        String deliveryTime = new SimpleDateFormat("HH:mm").format(deliveryDate);

        String message = cakeMessageField.getText();

        // Calculate base price
        double price = cakePrices.get(cake) * getSizeMultiplier(size);
        double finalPrice = price * quantity;

        // Apply discount
        if (discountCheckBox.isSelected()) {
            try {
                double discount = Double.parseDouble(discountField.getText());
                finalPrice -= finalPrice * (discount / 100);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid discount value", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Create and store the order
        Order order = new Order(customerName, cake, quantity, size, finalPrice, date, deliveryTime,message );
        orders.add(order);

        // Add order to list
        String orderSummary = String.format("%s - %d x %s (Price: ₹%.2f)", cake, quantity, size, finalPrice);
        orderListModel.addElement(orderSummary);

        // Show success message with tick image
        ImageIcon tickIcon = new ImageIcon(new ImageIcon("/Users/caressecorreia/Downloads/green_double_circle_check_mark.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));  // Resize the tick image
        JOptionPane.showMessageDialog(this, "Order Placed Successfully!!", "Success", JOptionPane.INFORMATION_MESSAGE, tickIcon);

        // Clear input fields
        clearFields();
    }

    private void removeOrder() {
        int selectedIndex = orderList.getSelectedIndex();
        if (selectedIndex != -1) {
            orders.remove(selectedIndex);
            orderListModel.remove(selectedIndex);
        }
    }

    private void generateReport() {
        StringBuilder report = new StringBuilder("Complete Order Report\n\n");

        double totalBill = 0;
        for (Order order : orders) {
            report.append("Date: ").append(order.getDate()).append("\n");
            report.append("Delivery Time: ").append(order.getDeliveryTime()).append("\n");
            report.append("Customer Name: ").append(order.getCustomerName()).append("\n");
            report.append("Cake: ").append(order.getCake()).append("\n");
            report.append("Quantity: ").append(order.getQuantity()).append("\n");
            report.append("Size: ").append(order.getSize()).append("\n");
            report.append("Price: ₹").append(String.format("%.2f", order.getFinalPrice())).append("\n");
            report.append("Message on Cake: ").append(order.getMessage()).append("\n\n");

            totalBill += order.getFinalPrice();
        }

        report.append("Total Bill for All Orders: ₹").append(String.format("%.2f", totalBill));

        reportArea.setText(report.toString());
    }

    private String getSelectedSize() {
        if (smallRadio.isSelected()) {
            return "Small";
        } else if (mediumRadio.isSelected()) {
            return "Medium";
        } else {
            return "Large";
        }
    }

    private double getSizeMultiplier(String size) {
        return switch (size) {
            case "Small" -> 0.75;
            case "Medium" -> 1.0;
            case "Large" -> 1.25;
            default -> 1.0;
        };
    }

    private void clearFields() {
        customerNameField.setText("");
        cakeMessageField.setText("");  // Clear the message on cake field
        quantitySpinner.setValue(1);
        sizeGroup.clearSelection();
        mediumRadio.setSelected(true);  // Set medium as default
        discountCheckBox.setSelected(false);
        discountField.setText("");
        discountField.setEnabled(false);
        // Reset delivery time to current time
        deliveryTimeSpinner.setValue(new Date());// Disable the discount field
    }


    public static void main(String[] ignoredArgs) {
        SwingUtilities.invokeLater(CakeOrderManagement::new);
    }
}

// Class to represent an order
// Class to represent an order
class Order {
    private final String customerName;
    private final String cake;
    private final int quantity;
    private final String size;
    private final double finalPrice;
    private final String date;
    private final String deliveryTime;
    private final String message;

    public Order(String customerName, String cake, int quantity, String size, double finalPrice, String date, String deliveryTime, String message) {
        this.customerName = customerName;
        this.cake = cake;
        this.quantity = quantity;
        this.size = size;
        this.finalPrice = finalPrice;
        this.date = date;
        this.deliveryTime = deliveryTime;
        this.message = message;
    }


    public String getCustomerName() {
        return customerName;
    }

    public String getCake() {
        return cake;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSize() {
        return size;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public String getDate() {
        return date;
    }
    public String getDeliveryTime() {
        return deliveryTime;
    }


    public String getMessage() {
        return message;
    }
}

