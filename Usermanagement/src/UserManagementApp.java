/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SP23-BSE-108
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class UserManagementApp extends JFrame {

    // ----- Model -----
    static class User {
        String username;
        String password;
        String profileInfo;

        User(String username, String password, String profileInfo) {
            this.username = username;
            this.password = password;
            this.profileInfo = profileInfo;
        }

        boolean checkPassword(String password) {
            return this.password.equals(password);
        }
    }

    HashMap<String, User> users = new HashMap<>();

    // ----- View Components -----
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JTextArea profileArea = new JTextArea(5, 30);
    JButton loginButton = new JButton("Login");
    JButton updateButton = new JButton("Update Profile");
    JLabel messageLabel = new JLabel();

    // ----- Constructor -----
    public UserManagementApp() {
        // Initial Setup
        setTitle("User Management System");
        setSize(400, 350);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add demo users
        users.put("admin", new User("admin", "admin123", "Admin Profile"));
        users.put("teacher", new User("teacher", "teach123", "Teacher Profile"));
        users.put("student", new User("student", "stud123", "Student Profile"));

        // View setup
        add(new JLabel("Username:"));
        add(usernameField);

        add(new JLabel("Password:"));
        add(passwordField);

        add(loginButton);
        add(messageLabel);

        add(new JLabel("Profile Info:"));
        profileArea.setLineWrap(true);
        profileArea.setWrapStyleWord(true);
        profileArea.setEditable(false);
        add(new JScrollPane(profileArea));

        updateButton.setEnabled(false);
        add(updateButton);

        // Controller logic: login
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            User user = users.get(username);
            if (user != null && user.checkPassword(password)) {
                messageLabel.setText("Login successful!");
                profileArea.setText(user.profileInfo);
                profileArea.setEditable(true);
                updateButton.setEnabled(true);
            } else {
                messageLabel.setText("Login failed.");
                profileArea.setText("");
                profileArea.setEditable(false);
                updateButton.setEnabled(false);
            }
        });

        // Controller logic: update profile
        updateButton.addActionListener(e -> {
            String username = usernameField.getText();
            User user = users.get(username);
            if (user != null) {
                user.profileInfo = profileArea.getText();
                JOptionPane.showMessageDialog(this, "Profile updated!");
            }
        });

        setVisible(true);
    }

    // ----- Main Method -----
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserManagementApp::new);
    }
}


