package main.models;

/**
 * كلاس الشركة - Employer Model
 * يمثل بيانات الشركة في النظام
 */
public class Employer {
    
    private String id;
    private String companyName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String industry;
    private String website;
    private String description;
    private String logo;
    private String status; // Active, Suspended
    private String registrationDate;
    private int totalOpportunities;
    private int activeOpportunities;
    
    // Constructor الافتراضي
    public Employer() {
    }
    
    // Constructor مع البيانات الأساسية
    public Employer(String id, String companyName, String email, String password) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.status = "Active";
        this.totalOpportunities = 0;
        this.activeOpportunities = 0;
    }
    
    // Constructor الكامل
    public Employer(String id, String companyName, String email, String password,
                   String phone, String address, String industry, String website) {
        this.id = id;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.industry = industry;
        this.website = website;
        this.status = "Active";
        this.totalOpportunities = 0;
        this.activeOpportunities = 0;
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getCompanyName() {
        return companyName;
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
    
    public String getAddress() {
        return address;
    }
    
    public String getIndustry() {
        return industry;
    }
    
    public String getWebsite() {
        return website;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getLogo() {
        return logo;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getRegistrationDate() {
        return registrationDate;
    }
    
    public int getTotalOpportunities() {
        return totalOpportunities;
    }
    
    public int getActiveOpportunities() {
        return activeOpportunities;
    }
    
    // Setters
    public void setId(String id) {
        this.id = id;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public void setTotalOpportunities(int totalOpportunities) {
        this.totalOpportunities = totalOpportunities;
    }
    
    public void setActiveOpportunities(int activeOpportunities) {
        this.activeOpportunities = activeOpportunities;
    }
    
    // Methods للعمليات
    
    /**
     * إضافة فرصة تدريب جديدة
     */
    public void addOpportunity() {
        this.totalOpportunities++;
        this.activeOpportunities++;
    }
    
    /**
     * إغلاق فرصة تدريب
     */
    public void closeOpportunity() {
        if (this.activeOpportunities > 0) {
            this.activeOpportunities--;
        }
    }
    
    /**
     * التحقق من صحة البيانات
     */
    public boolean validate() {
        return companyName != null && !companyName.isEmpty() &&
               email != null && email.contains("@") &&
               password != null && password.length() >= 6;
    }
    
    @Override
    public String toString() {
        return "Employer{" +
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", industry='" + industry + '\'' +
                ", status='" + status + '\'' +
                ", totalOpportunities=" + totalOpportunities +
                ", activeOpportunities=" + activeOpportunities +
                '}';
    }
}