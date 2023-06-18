package adminGUI;

import utils.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RemoveMobilePowerGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public RemoveMobilePowerGUI() {
        super("移除移动电源");

        // 创建表格模型
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Brand");
        tableModel.addColumn("Model");
        tableModel.addColumn("Status");

        // 创建表格
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // 创建按钮
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获取选中行的索引
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // 获取移动电源的ID
                    int mobilePowerId = (int) table.getValueAt(selectedRow, 0);

                    // 从数据库中删除移动电源
                    removeMobilePowerFromDatabase(mobilePowerId);

                    // 从表格中移除选中行
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(RemoveMobilePowerGUI.this, "请选择要删除的行");
                }
            }
        });

        // 创建布局
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(removeButton, BorderLayout.SOUTH);

        // 设置窗口属性
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        add(panel);
    }

    private void removeMobilePowerFromDatabase(int mobilePowerId) {
        try (Connection connection = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            String sql = "DELETE FROM powerbank WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mobilePowerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadMobilePowersFromDatabase() {
        try (Connection connection = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            String sql = "SELECT * FROM powerbank";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("PowerBankID");
                String brand = resultSet.getString("Brand");
                String model = resultSet.getString("Model");
                String status = resultSet.getString("Status");

                tableModel.addRow(new Object[]{id, brand, model, status});
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
