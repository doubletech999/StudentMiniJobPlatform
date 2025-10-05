package main.models;

/**
 * كلاس الطالب - Student Model
 * يمثل بيانات الطالب في النظام
 */
public class Student {
    
    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String university;
    private String major;
    private String gpa;
    private String skills;
    private String cv;
    private String status; // Active, Suspended
    private String registrationDate;
    
    // Constructor الافتراضي
    public Student() {
    }
    
    // Constructor مع البيانات الأساسية
    public Student(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = "Active";
    }
    
    // Constructor الكامل
    public Student(String id, String name, String email, String password, 
                   String phone, String university, String major, String gpa) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.university = university;
        this.major = major;
        this.gpa = gpa;
        this.status = "Active";
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getUniversity() {
        return university;
    }
    
    public String getMajor() {
        return major;
    }
    
    public String getGpa() {
        return gpa;
    }
    
    public String getSkills() {
        return skills;
    }
    
    public String getCv() {
        return cv;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getRegistrationDate() {
        return registrationDate;
    }
    
    // Setters
    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setUniversity(String university) {
        this.university = university;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    public void setGpa(String gpa) {
        this.gpa = gpa;
    }
    
    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    public void setCv(String cv) {
        this.cv = cv;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    // Method للتحقق من صحة البيانات
    public boolean validate() {
        return name != null && !name.isEmpty() &&
               email != null && email.contains("@") &&
               password != null && password.length() >= 6;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", university='" + university + '\'' +
                ", major='" + major + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}