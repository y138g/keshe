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

/**
 * 添加用户
 */
public class AddUserGUI extends JFrame {
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JTextField emailTextField;
    private JTextField phoneNumberTextField;

    public AddUserGUI() {
        setTitle("Add User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 200));
        setLayout(new GridLayout(5, 2));

        // 添加用户名标签和文本框
        JLabel usernameLabel = new JLabel("用户名:");
        usernameTextField = new JTextField();
        add(usernameLabel);
        add(usernameTextField);

        // 添加密码标签和密码框
        JLabel passwordLabel = new JLabel("密码:");
        passwordField = new JPasswordField();
        add(passwordLabel);
        add(passwordField);

        // 添加邮箱标签和文本框
        JLabel emailLabel = new JLabel("邮箱:");
        emailTextField = new JTextField();
        add(emailLabel);
        add(emailTextField);

        // 添加电话号码标签和文本框
        JLabel phoneNumberLabel = new JLabel("手机号:");
        phoneNumberTextField = new JTextField();
        add(phoneNumberLabel);
        add(phoneNumberTextField);

        // 添加添加按钮
        JButton addButton = new JButton("添加用户");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });
        add(addButton);

        pack();
        setVisible(true);
    }

    private void addUser() {
        String username = usernameTextField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();

        try (Connection connection = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            String sql = "INSERT INTO user (Username, Password, Email, PhoneNumber) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "添加用户成功!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "添加用户失败！");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        usernameTextField.setText("");
        passwordField.setText("");
        emailTextField.setText("");
        phoneNumberTextField.setText("");
    }
}
