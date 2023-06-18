package mobilePower;

import utils.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MobilePowerManagementGUI extends JFrame {
    private JTable table;
    private JButton refreshButton;
    private JButton rentButton;

    public MobilePowerManagementGUI() {
        // 设置窗口属性
        setTitle("移动电源管理");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建组件
        table = new JTable();
        refreshButton = new JButton("刷新");
        rentButton = new JButton("租");

        // 创建布局
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        buttonPanel.add(rentButton);

        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // 添加事件监听器
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAvailableMobilePower();
            }
        });

        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int powerID = (int) table.getValueAt(selectedRow, 0);
                    int Status = (int) table.getValueAt(selectedRow, 3);
                    int RemainingPower = (int) table.getValueAt(selectedRow, 4);

                    if (Status == 0 && RemainingPower > 50) {
                        // 计算租赁费用
                        double rentalFee = calculateRentalFee();

                        // 生成租赁订单
                        generateRentalOrder(powerID, rentalFee);

                        // 更新电源状态
                        updatePowerStatus(powerID, "更新");

                        // 刷新表格显示
                        showAvailableMobilePower();
                        JOptionPane.showMessageDialog(null, "出租成功！租金: " + rentalFee);
                    } else {
                        JOptionPane.showMessageDialog(null, "所选移动电源不可出租！");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请选择要租用的移动电源");
                }
            }
        });
    }

    private void showAvailableMobilePower() {
        try (Connection connection = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            // 创建查询语句
            String sql = "SELECT * FROM MobilePower WHERE Status = 0 AND RemainingPower > 50";
            Statement statement = connection.createStatement();

            // 执行查询
            ResultSet resultSet = statement.executeQuery(sql);

            // 创建表格模型
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("PowerBankID");
            tableModel.addColumn("Brand");
            tableModel.addColumn("Model");
            tableModel.addColumn("Capacity");
            tableModel.addColumn("Status");
            tableModel.addColumn("RemainingPower");

            // 处理查询结果
            while (resultSet.next()) {
                int powerID = resultSet.getInt("PowerID");
                String brand = resultSet.getString("Brand");
                String model = resultSet.getString("Model");
                String capacity = resultSet.getString("Capacity");
                String powerStatus = resultSet.getString("PowerStatus");
                int remainingBattery = resultSet.getInt("RemainingBattery");

                tableModel.addRow(new Object[]{powerID, brand, model,capacity, powerStatus, remainingBattery});
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

    private double calculateRentalFee() {
        // 在这里实现计算租赁费用的逻辑，根据实际需求进行计算并返回费用值
        return 0.0;
    }

    private void generateRentalOrder(int powerID, double rentalFee) {
        // 在这里实现生成租赁订单的逻辑，根据实际需求进行处理
    }

    private void updatePowerStatus(int powerID, String powerStatus) {
        // 在这里实现更新电源状态的逻辑，根据实际需求进行处理
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MobilePowerManagementGUI().setVisible(true);
            }
        });
    }
}
