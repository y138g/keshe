package adminGUI;

import utils.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RemoveUserGUI extends JFrame {
    private JTextField usernameField;

    public RemoveUserGUI() {
        setTitle("删除用户");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 创建组件
        JLabel usernameLabel = new JLabel("用户名:");
        usernameField = new JTextField(20);
        JButton removeButton = new JButton("删除");
        
        // 添加事件监听器
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                removeUser(username);
            }
        });
        
        // 创建布局
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(removeButton);
        
        // 添加面板到窗口
        getContentPane().add(panel);
        
        // 设置窗口大小并可见
        pack();
        setVisible(true);
    }

    private void removeUser(String username) {
        try (Connection connection = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            // 创建删除语句
            String sql = "DELETE FROM user WHERE Username = ?";
            
            // 创建 PreparedStatement 对象
            PreparedStatement statement = connection.prepareStatement(sql);
            
            // 设置参数值
            statement.setString(1, username);
            
            // 执行删除
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "用户删除成功!");
            } else {
                JOptionPane.showMessageDialog(this, "用户删除失败!");
            }
            
            // 关闭语句
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error removing user: " + ex.getMessage());
        }
    }
}
