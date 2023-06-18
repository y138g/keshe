package userGUI;

import utils.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class OrderHistoryGUI extends JFrame {
    private JTable orderTable;

    public OrderHistoryGUI(int UserID) {
        setTitle("个人历史订单信息");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建表格模型
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("订单ID");
        tableModel.addColumn("电宝ID");
        tableModel.addColumn("租借时间");
        tableModel.addColumn("归还时间");
        tableModel.addColumn("总价");

        // 创建表格
        orderTable = new JTable(tableModel);

        // 创建滚动面板，并将表格添加到其中
        JScrollPane scrollPane = new JScrollPane(orderTable);

        // 添加滚动面板到窗口
        getContentPane().add(scrollPane);

        // 查询并显示历史订单数据
        fetchOrderHistoryData(UserID);
    }

    private void fetchOrderHistoryData(int UserID) {
        try (Connection connection = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            // 创建查询语句
            String sql = "SELECT * FROM orderinfo WHERE UserID = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, UserID);

            // 执行查询
            ResultSet resultSet = statement.executeQuery();

            // 遍历结果集并添加数据到表格模型
            DefaultTableModel tableModel = (DefaultTableModel) orderTable.getModel();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("OrderID");
                String PowerBankID = resultSet.getString("PowerBankID");
                String StartTime = resultSet.getString("StartTime");
                String EndTime = resultSet.getString("EndTime");
                String TotalCost = resultSet.getString("TotalCost");
                Object[] rowData = {orderId, PowerBankID,StartTime,EndTime,TotalCost};
                tableModel.addRow(rowData);
            }
            // 关闭结果集和语句
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}