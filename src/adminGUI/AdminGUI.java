package adminGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame {
    private JLabel welcomeLabel;
    private JButton addPowerBankButton;
    private JButton removePowerBankButton;
    private JButton updatePowerBankButton;
    private JButton searchPowerBankButton;
    private JButton addUserButton;
    private JButton removeUserButton;
    private JButton updateUserButton;
    private JButton searchUserButton;

    public AdminGUI(String username) {
        // 设置窗口标题和大小
        setTitle("管理员界面");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建欢迎标签
        welcomeLabel = new JLabel("hello, " + username + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        // 创建按钮
        addPowerBankButton = new JButton("添加移动电源");
        removePowerBankButton = new JButton("移除移动电源");
        updatePowerBankButton = new JButton("修改移动电源信息");
        searchPowerBankButton = new JButton("查询移动电源信息");
        addUserButton = new JButton("添加用户");
        removeUserButton = new JButton("移除用户");
        updateUserButton = new JButton("修改用户信息");
        searchUserButton = new JButton("查询用户信息");

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2));
        buttonPanel.add(addPowerBankButton);
        buttonPanel.add(removePowerBankButton);
        buttonPanel.add(updatePowerBankButton);
        buttonPanel.add(searchPowerBankButton);
        buttonPanel.add(addUserButton);
        buttonPanel.add(removeUserButton);
        buttonPanel.add(updateUserButton);
        buttonPanel.add(searchUserButton);

        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 添加主面板到窗口
        add(mainPanel);

        // 注册按钮点击事件
        addPowerBankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminGUI.this, "添加移动电源");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new AddPowerBankGUI();
                    }
                });
            }
        });

        removePowerBankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminGUI.this, "移除移动电源");
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        RemoveMobilePowerGUI gui = new RemoveMobilePowerGUI();
                        gui.loadMobilePowersFromDatabase();
                        gui.setVisible(true);
                    }
                });
            }
        });

        updatePowerBankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminGUI.this, "修改移动电源信息");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new EditPowerBankGUI();
                    }
                });
            }
        });

        searchPowerBankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminGUI.this, "查询移动电源信息");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new PowerBankInfoGUI();
                    }
                });
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminGUI.this, "添加用户");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new AddUserGUI();
                    }
                });
            }
        });

        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminGUI.this, "移除用户");
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new RemoveUserGUI();
                    }
                });
            }
        });

        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminGUI.this, "修改用户信息");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new UpdateUserInfoGUI();
                    }
                });
            }
        });

        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminGUI.this, "查询用户信息");
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new UserInformationQueryGUI().setVisible(true);
                    }
                });
            }
        });
    }
}
