# Integrated-Hospital-Management-and-Doctor-Consultation-Request

The **Integrated-Hospital-Management-and-Doctor-Consultation-Request** is a **Java Swing-based desktop application** designed to simplify and digitalize hospital operations. It bridges the gap between patients, doctors, and hospital staff by providing a **unified platform** where patients can book appointments, check doctor availability, provide feedback, and explore hospital services, while doctors and staff can securely log in to manage hospital workflows.  

This project aims to:  
- **Enhance patient convenience** by offering easy appointment booking and service details.  
- **Improve doctor efficiency** by organizing schedules and patient information.  
- **Support hospital staff** in managing daily operations like appointments, services, and reports.  
- **Provide transparency** with clear hospital information, services, and feedback.  

Unlike traditional hospital systems, this project focuses on **simplicity, usability, and real-time information display** without requiring a backend database (though it can be extended for production use). It is ideal for **college projects, portfolios, and small healthcare centers**.  

---

## 🌟 Key Features  

### 🧑‍⚕️ About Hospital  
- Brief history and mission of the hospital  
- Showcase of **specialist doctors** with names, qualifications, and departments  
- Modern infrastructure highlights: **advanced labs, robotic surgery units, imaging systems**  

---

### 👨‍👩‍👧 Patient’s Corner  
🔹 **Appointment Booking**  
- Collects **basic patient details** (Name, Age, Gender, Address, Phone)  
- Dropdown menu for **reason of visit** (fever, heart issues, kidney problems, etc.)  
- Auto-suggests **three doctors per issue**  
- Date selection and unique **appointment number generation**  
- Options to **clear form** or **submit**  

🔹 **Doctor Timings & Availability**  
- Interactive **weekly timetable** (Mon–Sun vs. Time Slots)  
- Shows doctor names in each time slot  

🔹 **Feedback Form**  
- 6 categories of feedback (maintenance, staff behavior, consultation, waiting time, facilities, overall rating)  
- ⭐⭐⭐⭐⭐ star rating for each  

🔹 **Lab Reports**  
- Displays “Reports are under process, will be updated soon”  

🔹 **Our Services**  
- Displays the list of services provided by the hospital  

🔹 **Special Doctors**  
- Lists doctors with specialization and **available days**  
- Example: *“Dr. Anjali Reddy – Pediatrician – Available Mon & Thu”*  

🔹 **Appointment Timings Checker**  
- Enter **appointment number** → See exact appointment time  

---

### 🧑‍⚕️ Doctor’s Corner  
- **Secure login** → Username: `doctor`, Password: `1234`  
- After login:  
  - View **appointment schedule**  
  - Check **patient details** for the day  
  - Update **availability status** (Available / On Leave / Emergency Duty)  
  - Access patient history (future DB integration)  

---

### 🧑‍💼 Staff Corner  
- **Secure login** → Username: `staff`, Password: `1234`  
- After login:  
  - Manage **patient check-ins and check-outs**  
  - Assign patients to doctors  
  - Update **lab report status**  
  - Maintain and update hospital **services and announcements**  
  - Assist patients in emergencies  

---

## 🖼️ User Interface Highlights  
- **Full-screen design** with scrollable panels  
- Transparent overlays for a **modern hospital feel**  
- Smooth hover effects on menu options  
- Consistent **blue & white theme** (medical aesthetics)  

---

## ⚙️ Installation & Running  

### 1️⃣ Clone the Repository  
```bash
git clone https://github.com/your-username/Hospital-Information-and-Appointment-Management-System.git
cd Hospital-Information-and-Appointment-Management-System

