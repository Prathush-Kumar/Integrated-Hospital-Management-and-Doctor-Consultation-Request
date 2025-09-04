import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AboutHospital extends JFrame {

    private Image backgroundImage;

    public AboutHospital() {
        setTitle("About Star Hospital");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // ===== Load Background =====
        backgroundImage = new ImageIcon("about_hospital.png").getImage(); // hospital background

        // ===== Main Background Panel =====
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JPanel overlay = new JPanel(new BorderLayout());
        overlay.setOpaque(false);

        // ===== About Text (TOP) =====
        JLabel aboutText = new JLabel(
            "<html><div style='text-align:center;'>"
            + "<h1 style='color:yellow;'>About Star Hospital</h1>"
            + "<p style='color:white; font-size:18px;'>Founded in 1998, Star Hospital has successfully treated thousands of patients.<br>"
            + "We specialize in <b>cardiology, neurology, orthopedics</b>, and advanced surgical procedures.<br>"
            + "Our hospital is equipped with <b>modern labs, advanced imaging systems, and robotic surgery units.</b></p>"
            + "</div></html>",
            SwingConstants.CENTER
        );

        // ===== Doctors Section (MIDDLE) =====
        JPanel doctorsPanel = new JPanel(new GridLayout(1, 4, 20, 20));
        doctorsPanel.setOpaque(false);
        doctorsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        String[][] doctors = {
            {"men.png", "Dr. John Smith", "Cardiologist", "MD, AIIMS"},
            {"women.png", "Dr. Anjali Reddy", "Pediatrician", "MD, Osmania"},
            {"men.png", "Dr. David Lee", "Orthopedic Surgeon", "MS, Apollo"},
            {"women.png", "Dr. Priya Sharma", "Neurologist", "MD, NIMS"}
        };

        for (String[] doc : doctors) {
            JPanel card = createCard(doc[0], doc[1], doc[2], doc[3], true);
            doctorsPanel.add(card);
        }

        // ===== Facilities Section (BOTTOM) =====
        JLabel facilitiesTitle = new JLabel(
            "<html><div style='text-align:center;'>"
            + "<h2 style='color:yellow;'>Our Facilities</h2></div></html>",
            SwingConstants.CENTER
        );

        JPanel facilitiesPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        facilitiesPanel.setOpaque(false);
        facilitiesPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

        String[][] facilities = {
            {"lab.png", "Advanced Lab Facilities"},
            {"operation.png", "Modern Operation Theatres"},
            {"care.png", "Dedicated Patient Care"}
        };

        for (String[] f : facilities) {
            JPanel card = createCard(f[0], f[1], null, null, false);
            facilitiesPanel.add(card);
        }

        // ===== Content Layout with TOP-MIDDLE-BOTTOM =====
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        // TOP
        content.add(Box.createVerticalStrut(20));
        content.add(aboutText);
        content.add(Box.createVerticalGlue());

        // MIDDLE
        content.add(doctorsPanel);
        content.add(Box.createVerticalGlue());

        // BOTTOM
        content.add(facilitiesTitle);
        content.add(facilitiesPanel);
        content.add(Box.createVerticalStrut(30));

        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        overlay.add(scrollPane, BorderLayout.CENTER);
        backgroundPanel.add(overlay, BorderLayout.CENTER);

        setContentPane(backgroundPanel);
        setVisible(true);
    }

    // ===== Create Stylish Card with Hover =====
    private JPanel createCard(String imagePath, String title, String subtitle, String extra, boolean isDoctor) {
        JPanel card = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 220));
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        ImageIcon icon = new ImageIcon(imagePath);
        int imgSize = isDoctor ? 160 : 180;
        Image scaledImg = icon.getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(scaledImg));
        picLabel.setHorizontalAlignment(SwingConstants.CENTER);

        StringBuilder text = new StringBuilder("<html><div style='text-align:center; color:black;'>");
        text.append("<b style='color:navy; font-size:14px;'>").append(title).append("</b>");
        if (subtitle != null) text.append("<br>").append(subtitle);
        if (extra != null) text.append("<br><i>").append(extra).append("</i>");
        text.append("</div></html>");

        JLabel info = new JLabel(text.toString(), SwingConstants.CENTER);

        card.add(picLabel, BorderLayout.CENTER);
        card.add(info, BorderLayout.SOUTH);

        // Size
        card.setPreferredSize(new Dimension(220, 260));

        return card;
    }

    public static void main(String[] args) {
        new AboutHospital();
    }
}
