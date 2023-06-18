package adminGUI;

import utils.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPowerBankGUI extends JFrame {

    private JTextField brandField;
    private JTextField modelField;
    private JTextField capacityField;
    private JButton addButton;

    public AddPowerBankGUI() {
        setTitle("添加移动电源");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        // 品牌标签和文本框
        JLabel brandLabel = new JLabel("品牌:");
        brandField = new JTextField();
        add(brandLabel);
        add(brandField);

        // 型号标签和文本框
        JLabel modelLabel = new JLabel("型号:");
        modelField = new JTextField();
        add(modelLabel);
        add(modelField);

        // 容量标签和文本框
        JLabel capacityLabel = new JLabel("容量:");
        capacityField = new JTextField();
        add(capacityLabel);
        add(capacityField);

        // 添加按钮
        addButton = new JButton("添加");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPowerBank();
            }
        });
        add(addButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addPowerBank() {
        String brand = brandField.getText();
        String model = modelField.getText();
        int capacity = Integer.parseInt(capacityField.getText());

        try (Connection connection = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            String sql = "INSERT INTO PowerBank (Brand, Model, Capacity) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, brand);
            statement.setString(2, model);
            statement.setInt(3, capacity);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this, "移动电源添加成功!");
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "移动电源添加失败!");
        }
    }

    private void clearFields() {
        brandField.setText("");
        modelField.setText("");
        capacityField.setText("");
    }
    
}
