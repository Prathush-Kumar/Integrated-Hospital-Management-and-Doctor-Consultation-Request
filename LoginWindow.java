import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    public LoginWindow(String role) {
        setTitle(role + " Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());

            if (role.equals("Doctor") && username.equals("doctor") && password.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Doctor Page is under maintenance.");
                dispose();
            } else if (role.equals("Staff") && username.equals("staff") && password.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Staff Page is under maintenance.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials! Try again.");
            }
        });

        panel.add(userLabel); panel.add(userField);
        panel.add(passLabel); panel.add(passField);
        panel.add(new JLabel("")); panel.add(loginBtn);

        add(panel);
        setVisible(true);
    }
}
