import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private Image backgroundImage;

    public Main() {
        setTitle("Welcome to Star Hospital");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== Maximize to full screen =====
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // ===== Load Background Image =====
        ImageIcon bgIcon = new ImageIcon("hospital_image.png"); // Make sure image exists
        backgroundImage = bgIcon.getImage();

        // ===== Background Panel with Scaled Image =====
        JPanel background = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(background);

        // ===== Overlay Panel =====
        JPanel overlay = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 160));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        overlay.setOpaque(false);
        background.add(overlay);

        // ===== Title =====
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);

        JLabel hospitalName = new JLabel("Welcome to Star Hospital", SwingConstants.CENTER);
        hospitalName.setFont(new Font("Serif", Font.BOLD, 52));
        hospitalName.setForeground(Color.WHITE);

        JLabel quote = new JLabel("\"Health is Wealth\"", SwingConstants.CENTER);
        quote.setFont(new Font("Serif", Font.ITALIC, 28));
        quote.setForeground(Color.YELLOW);

        titlePanel.add(hospitalName, BorderLayout.NORTH);
        titlePanel.add(quote, BorderLayout.SOUTH);

        // ===== Introduction =====
        JLabel intro = new JLabel(
            "<html><div style='text-align:center; color:white;'>"
            + "Star Hospital is committed to providing world-class healthcare services.<br>"
            + "Our team of experienced doctors, nurses, and staff ensures that every patient receives the best care."
            + "</div></html>",
            SwingConstants.CENTER
        );
        intro.setFont(new Font("Arial", Font.PLAIN, 20));

        // ===== Option Boxes =====
        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 30));
        optionsPanel.setOpaque(false);

        String[] options = {"About Hospital", "Patient's Corner", "Doctor Corner", "Staff Corner"};
        for (String opt : options) {
            JPanel box = new JPanel();
            box.setBackground(new Color(255, 255, 255, 210));
            box.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
            box.setLayout(new BorderLayout());
            box.setPreferredSize(new Dimension(200, 120));

            JLabel label = new JLabel(opt, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setForeground(new Color(0, 0, 139));
            box.add(label, BorderLayout.CENTER);

            // Hover + Click effect
            box.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    box.setBackground(new Color(173, 216, 230, 230));
                    box.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    box.setBackground(new Color(255, 255, 255, 210));
                    box.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }

                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (opt.equals("About Hospital")) {
                        new AboutHospital();
                    } else if (opt.equals("Patient's Corner")) {
                        new PatientsCorner();
                    } else if (opt.equals("Doctor Corner")) {
                        new LoginWindow("Doctor");
                    } else if (opt.equals("Staff Corner")) {
                        new LoginWindow("Staff");
                    }
                }
            });

            optionsPanel.add(box);
        }

        // ===== Footer =====
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(50, 50, 50));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));

        JLabel footer = new JLabel(
            "<html><div style='text-align:center; color:lightgray;'>"
            + "<h3>Star Hospital</h3>"
            + "123 Health Street, Hyderabad<br>"
            + "Phone: +91-9876543210 | Email: info@starhospital.com"
            + "</div></html>",
            SwingConstants.CENTER
        );
        footer.setFont(new Font("Arial", Font.PLAIN, 16));
        footerPanel.add(footer, BorderLayout.CENTER);

        // ===== Layout =====
        JPanel centerContent = new JPanel(new BorderLayout());
        centerContent.setOpaque(false);
        centerContent.add(intro, BorderLayout.NORTH);
        centerContent.add(optionsPanel, BorderLayout.CENTER);

        overlay.add(titlePanel, BorderLayout.NORTH);
        overlay.add(centerContent, BorderLayout.CENTER);
        overlay.add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

// ===== Simple Login Window for Doctor & Staff =====
class LoginWindow extends JFrame {
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
