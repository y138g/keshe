package adminGUI;

import utils.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditPowerBankGUI extends JFrame {
    private JTextField powerBankIDField;
    private JTextField brandField;
    private JTextField modelField;
    private JTextField capacityField;
    private JTextField remainingPowerField;
    private JTextField statusField;
    private JButton updateButton;

    public EditPowerBankGUI() {
        // 设置窗口标题
        setTitle("修改移动电源");
        // 设置窗口大小
        setSize(300, 200);
        // 设置窗口关闭时退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局为网格布局
        setLayout(new GridLayout(4, 2));

        // 创建标签和文本框
        JLabel idLabel = new JLabel("PowerBankID:");
        powerBankIDField = new JTextField();
        JLabel brandLabel = new JLabel("Brand:");
        brandField = new JTextField();
        JLabel modelLabel = new JLabel("Model:");
        modelField = new JTextField();
        JLabel capacityLabel = new JLabel("Capacity:");
        capacityField = new JTextField();
        JLabel remainingPowerLabel = new JLabel("RemainingPower:");
        remainingPowerField = new JTextField();
        JLabel statusLabel = new JLabel("Status:");
        statusField = new JTextField();

        // 创建更新按钮
        updateButton = new JButton("Update");
        // 添加按钮点击事件监听器
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePowerBank();
            }
        });

        // 将组件添加到窗口中
        add(idLabel);
        add(brandLabel);
        add(modelLabel);
        add(capacityLabel);
        add(remainingPowerLabel);
        add(statusLabel);
        add(new JLabel()); // 空标签，占位用
        add(updateButton);

        // 显示窗口
        setVisible(true);
    }

    private void updatePowerBank() {
        try (Connection connection = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            int powerBankID = Integer.parseInt(powerBankIDField.getText());
            String brand = brandField.getText();
            String model = modelField.getText();
            String capacity = capacityField.getText();
            String remaining = remainingPowerField.getText();
            String status = statusField.getText();

            // 创建更新语句
            String sql = "UPDATE powerbank SET Brand = ?, Model = ? , Capacity = ? ,Remaining = ?,Status = ?" +
                    "WHERE PowerBankID = ?";

            // 创建 PreparedStatement 对象
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, brand);
            statement.setString(2, model);
            statement.setString(3, capacity);
            statement.setString(4, remaining);
            statement.setString(5, status);
            statement.setInt(6, powerBankID);

            // 执行更新
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "移动电源修改成功!");
            } else {
                JOptionPane.showMessageDialog(this, "移动电源修改失败！");
            }

            // 关闭语句
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}