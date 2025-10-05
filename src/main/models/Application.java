package main.models;

import java.time.LocalDate;

/**
 * كلاس الطلب - Application Model
 * يمثل بيانات طلب الطالب للتقديم على فرصة تدريب
 */
public class Application {
    
    private String id;
    private String studentId;
    private String studentName;
    private String opportunityId;
    private String opportunityTitle;
    private String employerId;
    private String companyName;
    private String applicationDate;
    private String status; // Pending, UnderReview, Accepted, Rejected, Withdrawn
    private String coverLetter;
    private String cv;
    private double studentGpa;
    private String studentUniversity;
    private String studentMajor;
    private String studentSkills;
    private String reviewNotes;
    private String reviewDate;
    private String reviewedBy;
    
    // Constructor الافتراضي
    public Application() {
        this.applicationDate = LocalDate.now().toString();
        this.status = "Pending";
    }
    
    // Constructor مع البيانات الأساسية
    public Application(String id, String studentId, String studentName, 
                      String opportunityId, String opportunityTitle) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.opportunityId = opportunityId;
        this.opportunityTitle = opportunityTitle;
        this.applicationDate = LocalDate.now().toString();
        this.status = "Pending";
    }
    
    // Constructor مع معلومات الشركة
    public Application(String id, String studentId, String studentName,
                      String opportunityId, String opportunityTitle,
                      String employerId, String companyName) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.opportunityId = opportunityId;
        this.opportunityTitle = opportunityTitle;
        this.employerId = employerId;
        this.companyName = companyName;
        this.applicationDate = LocalDate.now().toString();
        this.status = "Pending";
    }
    
    // Constructor الكامل
    public Application(String id, String studentId, String studentName,
                      String opportunityId, String opportunityTitle,
                      String employerId, String companyName,
                      String coverLetter, String cv,
                      double studentGpa, String studentUniversity, 
                      String studentMajor, String studentSkills) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.opportunityId = opportunityId;
        this.opportunityTitle = opportunityTitle;
        this.employerId = employerId;
        this.companyName = companyName;
        this.coverLetter = coverLetter;
        this.cv = cv;
        this.studentGpa = studentGpa;
        this.studentUniversity = studentUniversity;
        this.studentMajor = studentMajor;
        this.studentSkills = studentSkills;
        this.applicationDate = LocalDate.now().toString();
        this.status = "Pending";
    }
    
    // ==================== Getters ====================
    
    public String getId() {
        return id;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public String getOpportunityId() {
        return opportunityId;
    }
    
    public String getOpportunityTitle() {
        return opportunityTitle;
    }
    
    public String getEmployerId() {
        return employerId;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public String getApplicationDate() {
        return applicationDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getCoverLetter() {
        return coverLetter;
    }
    
    public String getCv() {
        return cv;
    }
    
    public double getStudentGpa() {
        return studentGpa;
    }
    
    public String getStudentUniversity() {
        return studentUniversity;
    }
    
    public String getStudentMajor() {
        return studentMajor;
    }
    
    public String getStudentSkills() {
        return studentSkills;
    }
    
    public String getReviewNotes() {
        return reviewNotes;
    }
    
    public String getReviewDate() {
        return reviewDate;
    }
    
    public String getReviewedBy() {
        return reviewedBy;
    }
    
    // ==================== Setters ====================
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }
    
    public void setOpportunityTitle(String opportunityTitle) {
        this.opportunityTitle = opportunityTitle;
    }
    
    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
    
    public void setCv(String cv) {
        this.cv = cv;
    }
    
    public void setStudentGpa(double studentGpa) {
        this.studentGpa = studentGpa;
    }
    
    public void setStudentUniversity(String studentUniversity) {
        this.studentUniversity = studentUniversity;
    }
    
    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }
    
    public void setStudentSkills(String studentSkills) {
        this.studentSkills = studentSkills;
    }
    
    public void setReviewNotes(String reviewNotes) {
        this.reviewNotes = reviewNotes;
    }
    
    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
    
    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }
    
    // ==================== Business Methods ====================
    
    /**
     * قبول الطلب
     * Accept the application
     * 
     * @param reviewerName اسم المراجع
     * @param notes ملاحظات المراجعة
     */
    public void accept(String reviewerName, String notes) {
        this.status = "Accepted";
        this.reviewedBy = reviewerName;
        this.reviewNotes = notes;
        this.reviewDate = LocalDate.now().toString();
    }
    
    /**
     * رفض الطلب
     * Reject the application
     * 
     * @param reviewerName اسم المراجع
     * @param notes ملاحظات الرفض
     */
    public void reject(String reviewerName, String notes) {
        this.status = "Rejected";
        this.reviewedBy = reviewerName;
        this.reviewNotes = notes;
        this.reviewDate = LocalDate.now().toString();
    }
    
    /**
     * سحب الطلب من قبل الطالب
     * Withdraw the application by student
     */
    public void withdraw() {
        if (isPending()) {
            this.status = "Withdrawn";
            this.reviewDate = LocalDate.now().toString();
        }
    }
    
    /**
     * بدء مراجعة الطلب
     * Start reviewing the application
     */
    public void startReview() {
        if ("Pending".equals(this.status)) {
            this.status = "UnderReview";
        }
    }
    
    /**
     * التحقق إذا كان الطلب قيد الانتظار أو المراجعة
     * Check if application is pending or under review
     * 
     * @return true إذا كان الطلب قيد الانتظار
     */
    public boolean isPending() {
        return "Pending".equals(status) || "UnderReview".equals(status);
    }
    
    /**
     * التحقق إذا كان الطلب مقبول
     * Check if application is accepted
     * 
     * @return true إذا كان الطلب مقبول
     */
    public boolean isAccepted() {
        return "Accepted".equals(status);
    }
    
    /**
     * التحقق إذا كان الطلب مرفوض
     * Check if application is rejected
     * 
     * @return true إذا كان الطلب مرفوض
     */
    public boolean isRejected() {
        return "Rejected".equals(status);
    }
    
    /**
     * التحقق إذا كان الطلب مسحوب
     * Check if application is withdrawn
     * 
     * @return true إذا كان الطلب مسحوب
     */
    public boolean isWithdrawn() {
        return "Withdrawn".equals(status);
    }
    
    /**
     * الحصول على حالة الطلب باللغة العربية
     * Get status in Arabic
     * 
     * @return حالة الطلب بالعربية
     */
    public String getStatusArabic() {
        switch (status) {
            case "Pending":
                return "قيد الانتظار";
            case "UnderReview":
                return "قيد المراجعة";
            case "Accepted":
                return "مقبول";
            case "Rejected":
                return "مرفوض";
            case "Withdrawn":
                return "مسحوب";
            default:
                return status;
        }
    }
    
    /**
     * التحقق من صحة البيانات الأساسية
     * Validate basic application data
     * 
     * @return true إذا كانت البيانات صحيحة
     */
    public boolean validate() {
        return studentId != null && !studentId.isEmpty() &&
               opportunityId != null && !opportunityId.isEmpty() &&
               studentName != null && !studentName.isEmpty() &&
               opportunityTitle != null && !opportunityTitle.isEmpty();
    }
    
    /**
     * التحقق من اكتمال البيانات الشخصية للطالب
     * Validate complete student information
     * 
     * @return true إذا كانت بيانات الطالب كاملة
     */
    public boolean hasCompleteStudentInfo() {
        return studentGpa > 0 &&
               studentUniversity != null && !studentUniversity.isEmpty() &&
               studentMajor != null && !studentMajor.isEmpty() &&
               studentSkills != null && !studentSkills.isEmpty();
    }
    
    /**
     * الحصول على ملخص الطلب
     * Get application summary
     * 
     * @return ملخص نصي للطلب
     */
    public String getSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Application ID: ").append(id).append("\n");
        summary.append("Student: ").append(studentName).append("\n");
        summary.append("Opportunity: ").append(opportunityTitle).append("\n");
        summary.append("Company: ").append(companyName).append("\n");
        summary.append("Date: ").append(applicationDate).append("\n");
        summary.append("Status: ").append(getStatusArabic()).append("\n");
        
        if (studentUniversity != null) {
            summary.append("University: ").append(studentUniversity).append("\n");
        }
        if (studentGpa > 0) {
            summary.append("GPA: ").append(studentGpa).append("\n");
        }
        
        return summary.toString();
    }
    
    /**
     * نسخ بيانات الطالب من كائن Student
     * Copy student data from Student object
     * 
     * @param student كائن الطالب
     */
    public void copyStudentData(main.models.Student student) {
        this.studentId = student.getId();
        this.studentName = student.getName();
        this.studentGpa = student.getGpa() != null ? 
                         Double.parseDouble(student.getGpa()) : 0.0;
        this.studentUniversity = student.getUniversity();
        this.studentMajor = student.getMajor();
        this.studentSkills = student.getSkills();
        this.cv = student.getCv();
    }
    
    // ==================== Override Methods ====================
    
    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", opportunityTitle='" + opportunityTitle + '\'' +
                ", companyName='" + companyName + '\'' +
                ", applicationDate='" + applicationDate + '\'' +
                ", status='" + status + '\'' +
                ", studentGpa=" + studentGpa +
                ", studentUniversity='" + studentUniversity + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Application that = (Application) obj;
        return id != null ? id.equals(that.id) : that.id == null;
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}