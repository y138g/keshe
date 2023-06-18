package userGUI;

import utils.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProfileGUI extends JFrame {
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JButton updateButton;
    private Connection connection;
    private String username;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JLabel mailLabel;
    private JTextField mailField;

    public UpdateProfileGUI(String username) {
        try {
            this.connection = DriverManager.getConnection(DB.DB_URL,DB.DB_USERNAME,DB.DB_PASSWORD);
            this.username = username;

            // 设置窗口标题和大小
            setTitle("修改个人信息");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // 创建标签和文本框
            nameLabel = new JLabel("用户名：");
            nameTextField = new JTextField();
            passwordLabel = new JLabel("密码：");
            passwordField = new JPasswordField();
            phoneLabel = new JLabel("手机号：");
            phoneField = new JTextField();
            mailLabel = new JLabel("邮箱：");
            mailField = new JTextField();

            // 创建按钮
            updateButton = new JButton("更新");

            // 创建按钮面板
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(updateButton);


            // 创建主面板
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(6, 4));
            mainPanel.add(nameLabel);
            mainPanel.add(nameTextField);
            mainPanel.add(passwordLabel);
            mainPanel.add(passwordField);
            mainPanel.add(phoneLabel);
            mainPanel.add(phoneField);
            mainPanel.add(mailLabel);
            mainPanel.add(mailField);
            mainPanel.add(buttonPanel);


            // 添加主面板到窗口
            add(mainPanel);

            // 注册按钮点击事件
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 获取新的姓名
                    String newName = nameTextField.getText();
                    String newPassword = passwordField.getText();
                    String newPhone = phoneField.getText();
                    String newMail = mailField.getText();

                    // 更新数据库中的用户信息
                    updateUserInfo(newName,newPassword,newPhone,newMail);

                    // 提示信息
                    JOptionPane.showMessageDialog(UpdateProfileGUI.this, "个人信息已更新");

                    // 关闭当前窗口
                    dispose();
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void updateUserInfo(String newName,String newPassword,String newPhone,String newMail) {
        try {
            // 创建更新用户信息的 SQL 语句
            String sql = "UPDATE User SET Username = ?" +
                    " , Password = ?" +
                    " , PhoneNumber = ?" +
                    " , Email = ?" +
                    "WHERE Username = ?";

            // 创建 PreparedStatement 对象
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setString(2, newPassword);
            statement.setString(3, newPhone);
            statement.setString(4, newMail);
            statement.setString(5, username);

            // 执行更新操作
            statement.executeUpdate();

            // 关闭 PreparedStatement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
