import adminGUI.AdminGUI;
import userGUI.UserGUI;
import utils.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton adminButton;


    /**
     * 主界面
     */
    public MainGUI() {
        setTitle("移动电源租赁系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("登录");
        registerButton = new JButton("注册");
        adminButton = new JButton("管理员登录");

        loginButton.addActionListener(new ActionListener() {
            //用户登录逻辑
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                boolean isValidUser = validateUser(username, password);
                if (isValidUser) {
                    JOptionPane.showMessageDialog(MainGUI.this, "登录成功");
                    dispose();
                    UserGUI userGUI = new UserGUI(username);
                    userGUI.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainGUI.this, "用户名或密码错误");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                boolean isRegistered = registerUser(username, password);

                if (isRegistered) {
                    JOptionPane.showMessageDialog(MainGUI.this, "注册成功");
                } else {
                    JOptionPane.showMessageDialog(MainGUI.this, "注册失败");
                }
            }
        });

        adminButton.addActionListener(new ActionListener() {
            //管理员登录逻辑
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                boolean isValidAdmin = validateAdmin(username, password);
                if (isValidAdmin) {
                    JOptionPane.showMessageDialog(MainGUI.this, "登录成功");
                    dispose();
                    AdminGUI adminGUI = new AdminGUI(username);
                    adminGUI.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainGUI.this, "用户名或密码错误");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(new JLabel("用户名："));
        panel.add(usernameField);
        panel.add(new JLabel("密码："));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(adminButton);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }


    /**
     * 判断用户是否存在
     * @param username 用户名
     * @param password 密码
     * @return 返回结果
     */
    private boolean validateUser(String username, String password) {
        DB.toSql();
        try (Connection conn = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            String sql = "SELECT * FROM user WHERE Username = ? AND Password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断管理员是否存在
     * @param username 管理员名
     * @param password 密码
     * @return 返回结果
     */
    private boolean validateAdmin(String username, String password) {
        DB.toSql();
        try (Connection conn = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            String sql = "SELECT * FROM admin WHERE Username = ? AND Password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @return 返回结果
     */
    private boolean registerUser(String username, String password) {
        DB.toSql();
        try (Connection conn = DriverManager.getConnection(DB.DB_URL, DB.DB_USERNAME, DB.DB_PASSWORD)) {
            String sql = "INSERT INTO user (Username, Password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();
            stmt.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 程序主入口
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainGUI gui = new MainGUI();
                gui.setVisible(true);
            }
        });
    }

}
