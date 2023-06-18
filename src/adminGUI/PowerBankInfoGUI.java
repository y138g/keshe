package adminGUI;

import utils.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PowerBankInfoGUI extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton btnRefresh;

    public PowerBankInfoGUI() {
        setTitle("查询移动电源信息");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        // 创建表格模型
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("品牌");
        tableModel.addColumn("模型");
        tableModel.addColumn("容量");
        tableModel.addColumn("剩余电量");
        tableModel.addColumn("状态");

        // 创建表格
        table = new JTable(tableModel);

        // 创建刷新按钮
        btnRefresh = new JButton("刷新");
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPowerBankInfo();
            }
        });

        // 创建面板，添加表格和按钮
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(btnRefresh, BorderLayout.SOUTH);

        // 将面板添加到窗口
        add(panel);

        pack();
        setVisible(true);
    }

    private void refreshPowerBankInfo() {
        // 清空表格数据
        tableModel.setRowCount(0);

        try (Connection connection = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            // 执行查询语句
            String sql = "SELECT * FROM powerbank";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // 处理查询结果
            while (resultSet.next()) {
                int id = resultSet.getInt("PowerBankID");
                String brand = resultSet.getString("Brand");
                String model = resultSet.getString("Model");
                String capacity = resultSet.getString("Capacity");
                String remainingPower = resultSet.getString("RemainingPower");
                String status = resultSet.getString("Status");

                // 添加行数据
                Object[] row = {id, brand, model, capacity,remainingPower,status};
                tableModel.addRow(row);
            }

            // 关闭结果集和语句
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
