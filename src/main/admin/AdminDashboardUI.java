package main.admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * لوحة تحكم المدير الرئيسية
 * Admin Main Dashboard
 */
public class AdminDashboardUI extends Application {
    
    private Stage primaryStage;
    private BorderPane mainLayout;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("لوحة تحكم المدير - Admin Dashboard");
        
        Scene scene = createDashboardScene();
        primaryStage.setScene(scene);
        primaryStage.setWidth(1300);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
    
    /**
     * إنشاء مشهد لوحة التحكم
     */
    private Scene createDashboardScene() {
        mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #ECEFF1;");
        
        mainLayout.setTop(createTopBar());
        mainLayout.setLeft(createSidebar());
        mainLayout.setCenter(createDashboardContent());
        
        return new Scene(mainLayout);
    }
    
    /**
     * إنشاء الشريط العلوي
     */
    private VBox createTopBar() {
        VBox topBar = new VBox();
        topBar.setStyle("-fx-background-color: #263238; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 2);");
        topBar.setPadding(new Insets(20, 30, 20, 30));
        
        HBox content = new HBox();
        content.setAlignment(Pos.CENTER_LEFT);
        
        VBox titleBox = new VBox(5);
        Label titleLabel = new Label("🔧 لوحة تحكم المدير");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.WHITE);
        
        Label subtitleLabel = new Label("Student Mini Job Platform - Admin Panel");
        subtitleLabel.setFont(Font.font("Arial", 12));
        subtitleLabel.setTextFill(Color.web("#B0BEC5"));
        
        titleBox.getChildren().addAll(titleLabel, subtitleLabel);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        HBox rightSection = new HBox(15);
        rightSection.setAlignment(Pos.CENTER_RIGHT);
        
        Button notificationBtn = new Button("🔔 (5)");
        notificationBtn.setFont(Font.font("Arial", 13));
        notificationBtn.setStyle(
            "-fx-background-color: #FF5722;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        
        Button adminProfileBtn = new Button("👤 المدير");
        adminProfileBtn.setFont(Font.font("Arial", 13));
        adminProfileBtn.setStyle(
            "-fx-background-color: #546E7A;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        
        Button logoutBtn = new Button("🚪 خروج");
        logoutBtn.setFont(Font.font("Arial", 13));
        logoutBtn.setStyle(
            "-fx-background-color: #C62828;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        logoutBtn.setOnAction(e -> logout());
        
        rightSection.getChildren().addAll(notificationBtn, adminProfileBtn, logoutBtn);
        
        content.getChildren().addAll(titleBox, spacer, rightSection);
        topBar.getChildren().add(content);
        
        return topBar;
    }
    
    /**
     * إنشاء القائمة الجانبية
     */
    private VBox createSidebar() {
        VBox sidebar = new VBox(10);
        sidebar.setPrefWidth(260);
        sidebar.setStyle("-fx-background-color: #37474F;");
        sidebar.setPadding(new Insets(30, 15, 30, 15));
        
        Label menuLabel = new Label("القائمة الإدارية");
        menuLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        menuLabel.setTextFill(Color.web("#CFD8DC"));
        menuLabel.setPadding(new Insets(0, 0, 10, 10));
        
        Button dashboardBtn = createSidebarButton("📊 لوحة المعلومات", true);
        dashboardBtn.setOnAction(e -> switchContent("dashboard"));
        
        Button usersBtn = createSidebarButton("👥 إدارة المستخدمين", false);
        usersBtn.setOnAction(e -> openManageUsers());
        
        Button opportunitiesBtn = createSidebarButton("📝 مراجعة الفرص", false);
        opportunitiesBtn.setOnAction(e -> openReviewOpportunities());
        
        Button reportsBtn = createSidebarButton("📈 التقارير والإحصائيات", false);
        reportsBtn.setOnAction(e -> openReports());
        
        Button settingsBtn = createSidebarButton("⚙️ إعدادات النظام", false);
        Button logsBtn = createSidebarButton("📋 سجل الأنشطة", false);
        
        Separator separator = new Separator();
        separator.setPadding(new Insets(15, 0, 15, 0));
        
        Button helpBtn = createSidebarButton("❓ المساعدة", false);
        
        sidebar.getChildren().addAll(
            menuLabel,
            dashboardBtn,
            usersBtn,
            opportunitiesBtn,
            reportsBtn,
            separator,
            settingsBtn,
            logsBtn,
            helpBtn
        );
        
        return sidebar;
    }
    
    /**
     * إنشاء زر القائمة الجانبية
     */
    private Button createSidebarButton(String text, boolean isActive) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        button.setPrefWidth(230);
        button.setPrefHeight(45);
        button.setAlignment(Pos.CENTER_LEFT);
        
        if (isActive) {
            button.setStyle(
                "-fx-background-color: #FF5722;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #CFD8DC;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
            
            button.setOnMouseEntered(e -> 
                button.setStyle(
                    "-fx-background-color: #455A64;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 8;" +
                    "-fx-cursor: hand;"
                )
            );
            
            button.setOnMouseExited(e -> 
                button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #CFD8DC;" +
                    "-fx-background-radius: 8;" +
                    "-fx-cursor: hand;"
                )
            );
        }
        
        return button;
    }
    
    /**
     * إنشاء محتوى لوحة التحكم
     */
    private ScrollPane createDashboardContent() {
        VBox content = new VBox(25);
        content.setPadding(new Insets(30));
        
        // عنوان الصفحة
        Label titleLabel = new Label("نظرة عامة على النظام");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web("#263238"));
        
        // بطاقات الإحصائيات الرئيسية
        HBox statsCards = new HBox(20);
        statsCards.setAlignment(Pos.CENTER);
        
        statsCards.getChildren().addAll(
            createStatCard("👥 إجمالي المستخدمين", "247", "+12 هذا الأسبوع", "#2196F3"),
            createStatCard("🏢 الشركات المسجلة", "45", "+3 جديدة", "#4CAF50"),
            createStatCard("📝 فرص التدريب", "89", "+7 نشطة", "#FF9800"),
            createStatCard("✅ التدريبات المكتملة", "156", "+23 هذا الشهر", "#9C27B0")
        );
        
        // الصف الثاني - الرسوم البيانية
        HBox chartsRow = new HBox(20);
        chartsRow.setAlignment(Pos.CENTER);
        
        // رسم بياني دائري
        VBox pieChartCard = createChartCard("توزيع المستخدمين", createPieChart());
        
        // رسم بياني شريطي
        VBox barChartCard = createChartCard("فرص التدريب حسب الفئة", createBarChart());
        
        chartsRow.getChildren().addAll(pieChartCard, barChartCard);
        
        // جدول النشاطات الأخيرة
        VBox recentActivity = createRecentActivityCard();
        
        content.getChildren().addAll(titleLabel, statsCards, chartsRow, recentActivity);
        
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        
        return scrollPane;
    }
    
    /**
     * إنشاء بطاقة إحصائية
     */
    private VBox createStatCard(String title, String value, String subtitle, String color) {
        VBox card = new VBox(12);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPrefWidth(230);
        card.setPrefHeight(140);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);" +
            "-fx-border-color: " + color + ";" +
            "-fx-border-width: 0 0 0 4;" +
            "-fx-border-radius: 12;"
        );
        card.setPadding(new Insets(20));
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        titleLabel.setTextFill(Color.web("#7F8C8D"));
        titleLabel.setWrapText(true);
        
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        valueLabel.setTextFill(Color.web(color));
        
        Label subtitleLabel = new Label(subtitle);
        subtitleLabel.setFont(Font.font("Arial", 11));
        subtitleLabel.setTextFill(Color.web("#95A5A6"));
        
        card.getChildren().addAll(titleLabel, valueLabel, subtitleLabel);
        
        return card;
    }
    
    /**
     * إنشاء بطاقة رسم بياني
     */
    private VBox createChartCard(String title, Chart chart) {
        VBox card = new VBox(15);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);"
        );
        card.setPadding(new Insets(20));
        card.setPrefWidth(480);
        card.setPrefHeight(350);
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.web("#263238"));
        
        card.getChildren().addAll(titleLabel, chart);
        
        return card;
    }
    
    /**
     * إنشاء رسم بياني دائري
     */
    private PieChart createPieChart() {
        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(
            new PieChart.Data("طلاب", 180),
            new PieChart.Data("شركات", 45),
            new PieChart.Data("مدراء", 22)
        );
        pieChart.setLegendVisible(true);
        pieChart.setPrefHeight(250);
        
        return pieChart;
    }
    
    /**
     * إنشاء رسم بياني شريطي
     */
    private BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("الفئة");
        yAxis.setLabel("عدد الفرص");
        
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("");
        barChart.setLegendVisible(false);
        barChart.setPrefHeight(250);
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("تطوير", 25));
        series.getData().add(new XYChart.Data<>("تصميم", 18));
        series.getData().add(new XYChart.Data<>("تسويق", 15));
        series.getData().add(new XYChart.Data<>("إدارة", 12));
        series.getData().add(new XYChart.Data<>("بيانات", 19));
        
        barChart.getData().add(series);
        
        return barChart;
    }
    
    /**
     * إنشاء بطاقة النشاطات الأخيرة
     */
    private VBox createRecentActivityCard() {
        VBox card = new VBox(15);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);"
        );
        card.setPadding(new Insets(25));
        card.setMaxWidth(1000);
        
        Label titleLabel = new Label("📋 النشاطات الأخيرة");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.web("#263238"));
        
        VBox activitiesList = new VBox(10);
        activitiesList.getChildren().addAll(
            createActivityItem("✅", "تم قبول طلب أحمد محمد للتدريب في شركة التقنية", "منذ 5 دقائق", "#4CAF50"),
            createActivityItem("➕", "تم إضافة فرصة تدريب جديدة من مجموعة الابتكار", "منذ ساعة", "#2196F3"),
            createActivityItem("👤", "انضمام شركة جديدة: مؤسسة الأمن السيبراني", "منذ 3 ساعات", "#FF9800"),
            createActivityItem("⭐", "تم تقييم المتدرب محمود حسن بدرجة 4.8", "منذ 5 ساعات", "#9C27B0"),
            createActivityItem("🔄", "تحديث حالة فرصة التدريب في علم البيانات", "منذ يوم", "#607D8B")
        );
        
        card.getChildren().addAll(titleLabel, new Separator(), activitiesList);
        
        return card;
    }
    
    /**
     * إنشاء عنصر نشاط
     */
    private HBox createActivityItem(String icon, String text, String time, String color) {
        HBox item = new HBox(15);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(10));
        item.setStyle(
            "-fx-background-color: #F5F5F5;" +
            "-fx-background-radius: 8;"
        );
        
        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(20));
        iconLabel.setPrefWidth(30);
        iconLabel.setAlignment(Pos.CENTER);
        iconLabel.setStyle(
            "-fx-background-color: " + color + ";" +
            "-fx-background-radius: 6;" +
            "-fx-text-fill: white;"
        );
        
        VBox textBox = new VBox(3);
        Label textLabel = new Label(text);
        textLabel.setFont(Font.font("Arial", 13));
        textLabel.setTextFill(Color.web("#263238"));
        
        Label timeLabel = new Label(time);
        timeLabel.setFont(Font.font("Arial", 11));
        timeLabel.setTextFill(Color.web("#95A5A6"));
        
        textBox.getChildren().addAll(textLabel, timeLabel);
        HBox.setHgrow(textBox, Priority.ALWAYS);
        
        item.getChildren().addAll(iconLabel, textBox);
        
        return item;
    }
    
    /**
     * تبديل المحتوى
     */
    private void switchContent(String section) {
        showInfo("تم الانتقال إلى: " + section);
    }
    
    /**
     * فتح إدارة المستخدمين
     */
    private void openManageUsers() {
        ManageUsersUI manageUsers = new ManageUsersUI();
        Stage stage = new Stage();
        manageUsers.start(stage);
    }
    
    /**
     * فتح مراجعة الفرص
     */
    private void openReviewOpportunities() {
        ReviewOpportunitiesUI reviewOpp = new ReviewOpportunitiesUI();
        Stage stage = new Stage();
        reviewOpp.start(stage);
    }
    
    /**
     * فتح التقارير
     */
    private void openReports() {
        ReportsUI reports = new ReportsUI();
        Stage stage = new Stage();
        reports.start(stage);
    }
    
    /**
     * تسجيل الخروج
     */
    private void logout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("تسجيل خروج");
        alert.setHeaderText("هل أنت متأكد من تسجيل الخروج؟");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                primaryStage.close();
            }
        });
    }
    
    /**
     * عرض معلومات
     */
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("معلومات");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}