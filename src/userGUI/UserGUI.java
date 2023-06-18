package userGUI;

import utils.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserGUI extends JFrame {
    private JLabel welcomeLabel;
    private JButton updateInfoButton;
    private JButton viewOrderButton;

    public UserGUI(String username) {
        // 设置窗口标题和大小
        setTitle("用户界面");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建欢迎标签
        welcomeLabel = new JLabel("hello, " + username + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        // 创建按钮
        updateInfoButton = new JButton("修改个人信息");
        viewOrderButton = new JButton("查询历史订单");

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(updateInfoButton);
        buttonPanel.add(viewOrderButton);

        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 添加主面板到窗口
        add(mainPanel);

        // 修改个人信息按钮点击事件
        updateInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(UserGUI.this, "跳转到修改个人信息界面");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // 创建修改个人信息界面并显示
                        UpdateProfileGUI updateProfileGUI = new UpdateProfileGUI(username);
                        updateProfileGUI.setVisible(true);
                    }
                });
            }
        });

        //查询历史订单按钮点击事件
        viewOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(UserGUI.this, "跳转到查询历史订单界面");
                SwingUtilities.invokeLater(() -> {
                    OrderHistoryGUI orderHistoryGUI = new OrderHistoryGUI(getUserID(username));
                    orderHistoryGUI.setSize(400, 300);
                    orderHistoryGUI.setVisible(true);
                });
            }
        });
    }


    private int getUserID(String username){
        try(Connection connection = DriverManager.getConnection(DB.DB_URL,DB.DB_USERNAME,DB.DB_PASSWORD)) {
            // 创建更新用户信息的 SQL 语句
            String sql = "SELECT UserID FROM user WHERE Username = ?";

            // 创建 PreparedStatement 对象
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            // 执行查询
            ResultSet resultSet = statement.executeQuery();
            // 处理查询结果
            while (resultSet.next()) {
                int UserID = resultSet.getInt("UserID");
                System.out.println("UserID: " + UserID);
                return UserID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
