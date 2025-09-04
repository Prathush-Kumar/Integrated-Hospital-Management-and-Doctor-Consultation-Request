import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PatientsCorner extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Map<Integer, String[]> appointments = new HashMap<>();
    private int appointmentCounter = 1000;

    public PatientsCorner() {
        setTitle("Patient's Corner - Star Hospital");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Home Menu
        mainPanel.add(createHomeMenu(), "HOME");
        mainPanel.add(createAppointmentBooking(), "BOOK_APPOINTMENT");
        mainPanel.add(createDoctorTimings(), "DOCTOR_TIMINGS");
        mainPanel.add(createFeedbackForm(), "FEEDBACK");
        mainPanel.add(createLabReports(), "LAB_REPORTS");
        mainPanel.add(createServicesPage(), "SERVICES");
        mainPanel.add(createSpecialDoctorsPage(), "SPECIAL_DOCTORS");
        mainPanel.add(createAppointmentCheckPage(), "APPOINTMENT_CHECK");

        setContentPane(mainPanel);
        cardLayout.show(mainPanel, "HOME");
        setVisible(true);
    }

    // ---------------- HOME MENU ----------------
    private JPanel createHomeMenu() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        panel.setBackground(new Color(230, 245, 255));

        String[] options = {
            "Appointment Booking", "Doctors Timings & Availability",
            "Feedback Report", "Lab Reports",
            "Our Services", "Special Doctors",
            "Appointment Timings"
        };

        for (String opt : options) {
            JButton btn = new JButton(opt);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setBackground(new Color(0, 102, 204));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> {
                switch (opt) {
                    case "Appointment Booking" -> cardLayout.show(mainPanel, "BOOK_APPOINTMENT");
                    case "Doctors Timings & Availability" -> cardLayout.show(mainPanel, "DOCTOR_TIMINGS");
                    case "Feedback Report" -> cardLayout.show(mainPanel, "FEEDBACK");
                    case "Lab Reports" -> cardLayout.show(mainPanel, "LAB_REPORTS");
                    case "Our Services" -> cardLayout.show(mainPanel, "SERVICES");
                    case "Special Doctors" -> cardLayout.show(mainPanel, "SPECIAL_DOCTORS");
                    case "Appointment Timings" -> cardLayout.show(mainPanel, "APPOINTMENT_CHECK");
                }
            });
            panel.add(btn);
        }

        return panel;
    }

    // ---------------- APPOINTMENT BOOKING ----------------
    private JPanel createAppointmentBooking() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 250, 255));

        JLabel heading = new JLabel("Star Hospital - Appointment Form", SwingConstants.CENTER);
        heading.setFont(new Font("Serif", Font.BOLD, 32));
        heading.setForeground(new Color(0, 51, 102));
        panel.add(heading, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(9, 2, 15, 15));
        form.setBorder(BorderFactory.createEmptyBorder(30, 300, 30, 300));
        form.setBackground(new Color(245, 250, 255));

        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JTextArea addressArea = new JTextArea(3, 15);
        JScrollPane addressScroll = new JScrollPane(addressArea);

        JComboBox<String> reasonBox = new JComboBox<>(new String[]{
            "Fever", "General Check-up", "Heart Issues",
            "Liver Issues", "Kidney Issues", "Stomach Problems",
            "Body/Leg Pain", "Neurology", "Orthopedic Issues"
        });

        JComboBox<String> doctorBox = new JComboBox<>();
        reasonBox.addActionListener(e -> {
            doctorBox.removeAllItems();
            String reason = (String) reasonBox.getSelectedItem();
            if (reason.equals("Fever") || reason.equals("General Check-up")) {
                doctorBox.addItem("Dr. Ramesh - General Physician");
                doctorBox.addItem("Dr. Sneha - Family Doctor");
                doctorBox.addItem("Dr. Arjun - Internal Medicine");
            } else if (reason.equals("Heart Issues")) {
                doctorBox.addItem("Dr. John - Cardiologist");
                doctorBox.addItem("Dr. Priya - Heart Specialist");
                doctorBox.addItem("Dr. Mohan - Cardiac Surgeon");
            } else if (reason.equals("Liver Issues")) {
                doctorBox.addItem("Dr. David - Hepatologist");
                doctorBox.addItem("Dr. Anjali - Gastroenterologist");
                doctorBox.addItem("Dr. Rakesh - Liver Specialist");
            } else if (reason.equals("Kidney Issues")) {
                doctorBox.addItem("Dr. Neha - Nephrologist");
                doctorBox.addItem("Dr. Sanjay - Urologist");
                doctorBox.addItem("Dr. Kumar - Renal Specialist");
            } else if (reason.equals("Stomach Problems")) {
                doctorBox.addItem("Dr. Suresh - Gastroenterologist");
                doctorBox.addItem("Dr. Meera - Stomach Specialist");
                doctorBox.addItem("Dr. Kiran - Digestive Care");
            } else if (reason.equals("Body/Leg Pain") || reason.equals("Orthopedic Issues")) {
                doctorBox.addItem("Dr. Raj - Orthopedic Surgeon");
                doctorBox.addItem("Dr. Swati - Bone Specialist");
                doctorBox.addItem("Dr. Lee - Joint Specialist");
            } else if (reason.equals("Neurology")) {
                doctorBox.addItem("Dr. Priya - Neurologist");
                doctorBox.addItem("Dr. Naveen - Brain Specialist");
                doctorBox.addItem("Dr. Sonia - Neuro Surgeon");
            }
        });

        JTextField dateField = new JTextField("DD/MM/YYYY");

        form.add(new JLabel("Name:")); form.add(nameField);
        form.add(new JLabel("Age:")); form.add(ageField);
        form.add(new JLabel("Gender:")); form.add(genderBox);
        form.add(new JLabel("Address:")); form.add(addressScroll);
        form.add(new JLabel("Reason:")); form.add(reasonBox);
        form.add(new JLabel("Select Doctor:")); form.add(doctorBox);
        form.add(new JLabel("Date of Appointment:")); form.add(dateField);

        JButton submitBtn = new JButton("Submit");
        JButton clearBtn = new JButton("Clear");
        JButton backBtn = new JButton("Back");

        submitBtn.addActionListener(e -> {
            String name = nameField.getText();
            String age = ageField.getText();
            String gender = (String) genderBox.getSelectedItem();
            String address = addressArea.getText();
            String reason = (String) reasonBox.getSelectedItem();
            String doctor = (String) doctorBox.getSelectedItem();
            String date = dateField.getText();

            if (name.isEmpty() || age.isEmpty() || address.isEmpty() || doctor == null) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            int appNo = ++appointmentCounter;
            appointments.put(appNo, new String[]{name, age, gender, address, reason, doctor, date});

            JOptionPane.showMessageDialog(this,
                "Appointment Confirmed!\nAppointment No: " + appNo +
                "\nName: " + name + "\nDoctor: " + doctor + "\nDate: " + date +
                "\n\nThank you for booking!");
        });

        clearBtn.addActionListener(e -> {
            nameField.setText("");
            ageField.setText("");
            addressArea.setText("");
            reasonBox.setSelectedIndex(0);
            doctorBox.removeAllItems();
            dateField.setText("DD/MM/YYYY");
        });

        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));

        form.add(submitBtn); form.add(clearBtn);

        panel.add(form, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);

        return panel;
    }

    // ---------------- DOCTOR TIMINGS ----------------
    private JPanel createDoctorTimings() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 250, 255));

        JLabel heading = new JLabel("Doctors Timings & Availability", SwingConstants.CENTER);
        heading.setFont(new Font("Serif", Font.BOLD, 28));
        panel.add(heading, BorderLayout.NORTH);

        String[] days = {"Time", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String[][] data = {
            {"9AM-12PM", "Dr. John", "Dr. Priya", "Dr. Mohan", "Dr. Raj", "Dr. Sneha", "Dr. Arjun", "-"},
            {"12PM-3PM", "Dr. Neha", "Dr. Kumar", "Dr. Sonia", "Dr. Naveen", "Dr. Meera", "-", "Dr. David"},
            {"3PM-6PM", "Dr. Swati", "Dr. Lee", "Dr. Kiran", "Dr. Rakesh", "Dr. Anjali", "Dr. Suresh", "Dr. Sanjay"}
        };

        JTable table = new JTable(new DefaultTableModel(data, days));
        JScrollPane scroll = new JScrollPane(table);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);

        return panel;
    }

    // ---------------- FEEDBACK FORM ----------------
    private JPanel createFeedbackForm() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 250, 255));

        JLabel heading = new JLabel("Feedback Form", SwingConstants.CENTER);
        heading.setFont(new Font("Serif", Font.BOLD, 28));
        panel.add(heading, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(30, 300, 30, 300));

        String[] feedbacks = {
            "Hospital Maintenance", "Cleanliness", "Doctor Care",
            "Nursing Care", "Staff Support", "Overall Experience"
        };

        for (String f : feedbacks) {
            form.add(new JLabel(f + ":"));
            JComboBox<Integer> stars = new JComboBox<>(new Integer[]{1,2,3,4,5});
            form.add(stars);
        }

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));

        panel.add(form, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);

        return panel;
    }

    // ---------------- LAB REPORTS ----------------
    private JPanel createLabReports() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Lab reports are in process. We will update once ready.", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(label, BorderLayout.CENTER);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));
        panel.add(backBtn, BorderLayout.SOUTH);

        return panel;
    }

    // ---------------- SERVICES ----------------
    private JPanel createServicesPage() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel heading = new JLabel("Our Services", SwingConstants.CENTER);
        heading.setFont(new Font("Serif", Font.BOLD, 28));
        panel.add(heading, BorderLayout.NORTH);

        String[] services = {
            "Cardiology", "Neurology", "Orthopedics", "General Medicine", "Pediatrics",
            "Dermatology", "Radiology", "Pathology", "Emergency Care", "Intensive Care",
            "Urology", "Nephrology", "Gastroenterology", "Pulmonology", "Endocrinology",
            "ENT", "Ophthalmology", "Dental Care", "Physiotherapy", "Nutrition & Diet"
        };

        JList<String> list = new JList<>(services);
        list.setFont(new Font("Arial", Font.PLAIN, 20));
        JScrollPane scroll = new JScrollPane(list);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);

        return panel;
    }

    // ---------------- SPECIAL DOCTORS ----------------
    private JPanel createSpecialDoctorsPage() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel heading = new JLabel("Special Doctors for Special Checkups", SwingConstants.CENTER);
        heading.setFont(new Font("Serif", Font.BOLD, 28));
        panel.add(heading, BorderLayout.NORTH);

        JTextArea area = new JTextArea(
            "Dr. John - Heart Specialist - Available Mon, Wed, Fri\n" +
            "Dr. Priya - Neurologist - Available Tue, Thu, Sat\n" +
            "Dr. Raj - Orthopedic Surgeon - Available Mon-Sat\n" +
            "Dr. Neha - Kidney Specialist - Available Tue, Thu\n" +
            "Dr. Meera - Stomach Specialist - Available Mon, Wed, Fri"
        );
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane scroll = new JScrollPane(area);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);

        return panel;
    }

    // ---------------- APPOINTMENT CHECK ----------------
    private JPanel createAppointmentCheckPage() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel heading = new JLabel("Check Appointment Timings", SwingConstants.CENTER);
        heading.setFont(new Font("Serif", Font.BOLD, 28));
        panel.add(heading, BorderLayout.NORTH);

        JPanel form = new JPanel(new FlowLayout());
        JTextField appField = new JTextField(10);
        JButton checkBtn = new JButton("Check");

        form.add(new JLabel("Enter Appointment Number:"));
        form.add(appField);
        form.add(checkBtn);

        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultArea);

        checkBtn.addActionListener(e -> {
            try {
                int appNo = Integer.parseInt(appField.getText().trim());
                if (appointments.containsKey(appNo)) {
                    String[] info = appointments.get(appNo);
                    resultArea.setText("Appointment Found!\n\n" +
                        "Name: " + info[0] +
                        "\nAge: " + info[1] +
                        "\nGender: " + info[2] +
                        "\nReason: " + info[4] +
                        "\nDoctor: " + info[5] +
                        "\nDate: " + info[6] +
                        "\nAppointment No: " + appNo);
                } else {
                    resultArea.setText("No appointment found with number: " + appNo);
                }
            } catch (Exception ex) {
                resultArea.setText("Invalid number format!");
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "HOME"));

        panel.add(form, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(backBtn, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        new PatientsCorner();
    }
}
