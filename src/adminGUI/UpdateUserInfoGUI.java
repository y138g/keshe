package adminGUI;

import utils.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateUserInfoGUI extends JFrame {
    private JTextField usernameTextField;
    private JTextField emailTextField;
    private JTextField phoneNumberTextField;
    
    public UpdateUserInfoGUI() {
        // 设置窗口标题
        setTitle("修改用户信息");
        
        // 创建文本标签和文本框
        JLabel usernameLabel = new JLabel("用户名:");
        JLabel emailLabel = new JLabel("邮箱:");
        JLabel phoneNumberLabel = new JLabel("电话号码:");
        
        usernameTextField = new JTextField(20);
        emailTextField = new JTextField(20);
        phoneNumberTextField = new JTextField(20);
        
        // 创建保存按钮
        JButton saveButton = new JButton("保存");
        
        // 添加保存按钮的事件监听器
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取输入的用户信息
                String username = usernameTextField.getText();
                String email = emailTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                
                // 更新数据库中的用户信息
                updateUserInfo(username, email, phoneNumber);
            }
        });
        
        // 创建面板，并添加组件
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(usernameLabel);
        panel.add(usernameTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberTextField);
        panel.add(saveButton);
        
        // 将面板添加到窗口
        getContentPane().add(panel);
        
        // 设置窗口大小和关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setVisible(true);
    }
    
    private void updateUserInfo(String username, String email, String phoneNumber) {
        try (Connection connection = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            // 创建更新语句
            String sql = "UPDATE user SET Email = ?, PhoneNumber = ? WHERE Username = ?";
            
            // 创建 PreparedStatement 对象
            PreparedStatement statement = connection.prepareStatement(sql);
            
            // 设置参数值
            statement.setString(1, email);
            statement.setString(2, phoneNumber);
            statement.setString(3, username);
            
            // 执行更新
            int rowsAffected = statement.executeUpdate();
            
            // 根据受影响的行数判断更新是否成功
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "修改成功！");
            } else {
                JOptionPane.showMessageDialog(this, "修改失败！");
            }
            
            // 关闭语句
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while updating user information.");
        }
    }
}
