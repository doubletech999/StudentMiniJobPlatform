package main.models;

/**
 * كلاس فرصة التدريب - Opportunity Model
 * يمثل بيانات فرصة التدريب في النظام
 */
public class Opportunity {
    
    private String id;
    private String employerId;
    private String companyName;
    private String title;
    private String description;
    private String category;
    private String duration;
    private String location;
    private String requirements;
    private boolean isPaid;
    private boolean providesCertificate;
    private boolean isRemote;
    private String status; // Available, UnderReview, Approved, Rejected, Closed
    private String postDate;
    private int applicationsCount;
    private int maxApplicants;
    
    // Constructor الافتراضي
    public Opportunity() {
    }
    
    // Constructor مع البيانات الأساسية
    public Opportunity(String id, String employerId, String companyName, String title) {
        this.id = id;
        this.employerId = employerId;
        this.companyName = companyName;
        this.title = title;
        this.status = "UnderReview";
        this.applicationsCount = 0;
        this.isPaid = false;
        this.providesCertificate = false;
        this.isRemote = false;
    }
    
    // Constructor الكامل
    public Opportunity(String id, String employerId, String companyName, String title,
                      String description, String category, String duration, String location,
                      String requirements) {
        this.id = id;
        this.employerId = employerId;
        this.companyName = companyName;
        this.title = title;
        this.description = description;
        this.category = category;
        this.duration = duration;
        this.location = location;
        this.requirements = requirements;
        this.status = "UnderReview";
        this.applicationsCount = 0;
        this.isPaid = false;
        this.providesCertificate = false;
        this.isRemote = false;
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getEmployerId() {
        return employerId;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public String getLocation() {
        return location;
    }
    
    public String getRequirements() {
        return requirements;
    }
    
    public boolean isPaid() {
        return isPaid;
    }
    
    public boolean providesCertificate() {
        return providesCertificate;
    }
    
    public boolean isRemote() {
        return isRemote;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getPostDate() {
        return postDate;
    }
    
    public int getApplicationsCount() {
        return applicationsCount;
    }
    
    public int getMaxApplicants() {
        return maxApplicants;
    }
    
    // Setters
    public void setId(String id) {
        this.id = id;
    }
    
    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
    
    public void setPaid(boolean paid) {
        isPaid = paid;
    }
    
    public void setProvidesCertificate(boolean providesCertificate) {
        this.providesCertificate = providesCertificate;
    }
    
    public void setRemote(boolean remote) {
        isRemote = remote;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
    
    public void setApplicationsCount(int applicationsCount) {
        this.applicationsCount = applicationsCount;
    }
    
    public void setMaxApplicants(int maxApplicants) {
        this.maxApplicants = maxApplicants;
    }
    
    // Methods للعمليات
    
    /**
     * إضافة طلب جديد
     */
    public void addApplication() {
        this.applicationsCount++;
    }
    
    /**
     * الموافقة على الفرصة
     */
    public void approve() {
        this.status = "Approved";
    }
    
    /**
     * رفض الفرصة
     */
    public void reject() {
        this.status = "Rejected";
    }
    
    /**
     * إغلاق الفرصة
     */
    public void close() {
        this.status = "Closed";
    }
    
    /**
     * التحقق إذا كانت الفرصة متاحة
     */
    public boolean isAvailable() {
        return "Approved".equals(status) && 
               (maxApplicants == 0 || applicationsCount < maxApplicants);
    }
    
    /**
     * التحقق من صحة البيانات
     */
    public boolean validate() {
        return title != null && !title.isEmpty() &&
               description != null && !description.isEmpty() &&
               category != null && !category.isEmpty() &&
               duration != null && !duration.isEmpty() &&
               requirements != null && !requirements.isEmpty();
    }
    
    @Override
    public String toString() {
        return "Opportunity{" +
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", duration='" + duration + '\'' +
                ", status='" + status + '\'' +
                ", applicationsCount=" + applicationsCount +
                '}';
    }
}