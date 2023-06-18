package adminGUI;

import utils.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserInformationQueryGUI extends JFrame {
    private JTextField usernameTextField;
    private JButton searchButton;
    private JTable table;

    public UserInformationQueryGUI() {
        // 设置窗口属性
        setTitle("查询用户");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建组件
        usernameTextField = new JTextField(20);
        searchButton = new JButton("查找");
        table = new JTable();

        // 创建布局
        JPanel panel = new JPanel();
        panel.add(new JLabel("用户名: "));
        panel.add(usernameTextField);
        panel.add(searchButton);

        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // 添加事件监听器
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                queryUserInfo(username);
            }
        });
    }

    private void queryUserInfo(String username) {
        try (Connection connection = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            // 创建查询语句
            String sql = "SELECT * FROM user WHERE Username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            // 执行查询
            ResultSet resultSet = statement.executeQuery();

            // 创建表格模型
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("UserID");
            tableModel.addColumn("Username");
            tableModel.addColumn("Email");
            tableModel.addColumn("PhoneNumber");

            // 处理查询结果
            while (resultSet.next()) {
                int userID = resultSet.getInt("UserID");
                String retrievedUsername = resultSet.getString("Username");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");

                tableModel.addRow(new Object[]{userID, retrievedUsername, email, phoneNumber});
            }

            // 设置表格模型
            table.setModel(tableModel);

            // 关闭结果集和语句
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
