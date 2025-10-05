package main.employer;

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
 * لوحة تحكم الشركة الرئيسية
 * Employer Main Dashboard
 */
public class EmployerDashboardUI extends Application {
    
    private Stage primaryStage;
    private BorderPane mainLayout;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("لوحة تحكم الشركة - Employer Dashboard");
        
        Scene scene = createDashboardScene();
        primaryStage.setScene(scene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(750);
        primaryStage.show();
    }
    
    /**
     * إنشاء مشهد لوحة التحكم
     */
    private Scene createDashboardScene() {
        mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #F5F7FA;");
        
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
        topBar.setStyle("-fx-background-color: #2196F3; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 2);");
        topBar.setPadding(new Insets(20, 30, 20, 30));
        
        HBox content = new HBox();
        content.setAlignment(Pos.CENTER_LEFT);
        
        VBox titleBox = new VBox(5);
        Label titleLabel = new Label("🏢 لوحة تحكم الشركة");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.WHITE);
        
        Label subtitleLabel = new Label("Employer Dashboard - شركة التقنية المتقدمة");
        subtitleLabel.setFont(Font.font("Arial", 12));
        subtitleLabel.setTextFill(Color.web("#E3F2FD"));
        
        titleBox.getChildren().addAll(titleLabel, subtitleLabel);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        HBox rightSection = new HBox(15);
        rightSection.setAlignment(Pos.CENTER_RIGHT);
        
        Button notificationBtn = new Button("🔔 (3)");
        notificationBtn.setFont(Font.font("Arial", 13));
        notificationBtn.setStyle(
            "-fx-background-color: #FF5722;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        
        Button profileBtn = new Button("👤 الحساب");
        profileBtn.setFont(Font.font("Arial", 13));
        profileBtn.setStyle(
            "-fx-background-color: #1976D2;" +
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
        
        rightSection.getChildren().addAll(notificationBtn, profileBtn, logoutBtn);
        
        content.getChildren().addAll(titleBox, spacer, rightSection);
        topBar.getChildren().add(content);
        
        return topBar;
    }
    
    /**
     * إنشاء القائمة الجانبية
     */
    private VBox createSidebar() {
        VBox sidebar = new VBox(10);
        sidebar.setPrefWidth(240);
        sidebar.setStyle("-fx-background-color: white;");
        sidebar.setPadding(new Insets(30, 15, 30, 15));
        
        Label menuLabel = new Label("القائمة الرئيسية");
        menuLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        menuLabel.setTextFill(Color.web("#2C3E50"));
        menuLabel.setPadding(new Insets(0, 0, 10, 10));
        
        Button dashboardBtn = createSidebarButton("📊 لوحة المعلومات", true);
        Button opportunitiesBtn = createSidebarButton("📝 إدارة الفرص", false);
        opportunitiesBtn.setOnAction(e -> openAddOpportunity());
        
        Button applicationsBtn = createSidebarButton("📋 الطلبات الواردة", false);
        applicationsBtn.setOnAction(e -> openApplications());
        
        Button evaluateBtn = createSidebarButton("⭐ تقييم المتدربين", false);
        evaluateBtn.setOnAction(e -> openEvaluate());
        
        Button reportsBtn = createSidebarButton("📈 التقارير", false);
        Button settingsBtn = createSidebarButton("⚙️ الإعدادات", false);
        
        Separator separator = new Separator();
        separator.setPadding(new Insets(15, 0, 15, 0));
        
        Button helpBtn = createSidebarButton("❓ المساعدة", false);
        
        sidebar.getChildren().addAll(
            menuLabel,
            dashboardBtn,
            opportunitiesBtn,
            applicationsBtn,
            evaluateBtn,
            reportsBtn,
            separator,
            settingsBtn,
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
        button.setPrefWidth(210);
        button.setPrefHeight(45);
        button.setAlignment(Pos.CENTER_LEFT);
        
        if (isActive) {
            button.setStyle(
                "-fx-background-color: #2196F3;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #2C3E50;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
            
            button.setOnMouseEntered(e -> 
                button.setStyle(
                    "-fx-background-color: #E3F2FD;" +
                    "-fx-text-fill: #1976D2;" +
                    "-fx-background-radius: 8;" +
                    "-fx-cursor: hand;"
                )
            );
            
            button.setOnMouseExited(e -> 
                button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #2C3E50;" +
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
        
        Label titleLabel = new Label("نظرة عامة على الشركة");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        titleLabel.setTextFill(Color.web("#2C3E50"));
        
        // بطاقات الإحصائيات
        HBox statsCards = new HBox(20);
        statsCards.setAlignment(Pos.CENTER);
        
        statsCards.getChildren().addAll(
            createStatCard("📝 الفرص المنشورة", "12", "+2 هذا الشهر", "#2196F3"),
            createStatCard("📋 الطلبات الواردة", "45", "+8 جديدة", "#FF9800"),
            createStatCard("✅ الطلبات المقبولة", "28", "62% معدل القبول", "#4CAF50"),
            createStatCard("👥 المتدربين الحاليين", "15", "نشط", "#9C27B0")
        );
        
        // الرسوم البيانية
        HBox chartsRow = new HBox(20);
        chartsRow.setAlignment(Pos.CENTER);
        
        VBox pieChartCard = createChartCard("توزيع الطلبات", createPieChart());
        VBox barChartCard = createChartCard("الطلبات الشهرية", createBarChart());
        
        chartsRow.getChildren().addAll(pieChartCard, barChartCard);
        
        // جدول الطلبات الأخيرة
        VBox recentApplications = createRecentApplicationsCard();
        
        content.getChildren().addAll(titleLabel, statsCards, chartsRow, recentApplications);
        
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
        card.setPrefWidth(210);
        card.setPrefHeight(130);
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
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        titleLabel.setTextFill(Color.web("#7F8C8D"));
        titleLabel.setWrapText(true);
        
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 34));
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
        card.setPrefWidth(430);
        card.setPrefHeight(320);
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.web("#2C3E50"));
        
        card.getChildren().addAll(titleLabel, chart);
        
        return card;
    }
    
    /**
     * إنشاء رسم بياني دائري
     */
    private PieChart createPieChart() {
        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(
            new PieChart.Data("قيد المراجعة", 17),
            new PieChart.Data("مقبولة", 28),
            new PieChart.Data("مرفوضة", 10)
        );
        pieChart.setLegendVisible(true);
        pieChart.setPrefHeight(230);
        
        return pieChart;
    }
    
    /**
     * إنشاء رسم بياني شريطي
     */
    private BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("الشهر");
        yAxis.setLabel("عدد الطلبات");
        
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("");
        barChart.setLegendVisible(false);
        barChart.setPrefHeight(230);
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("يوليو", 12));
        series.getData().add(new XYChart.Data<>("أغسطس", 18));
        series.getData().add(new XYChart.Data<>("سبتمبر", 15));
        series.getData().add(new XYChart.Data<>("أكتوبر", 22));
        
        barChart.getData().add(series);
        
        return barChart;
    }
    
    /**
     * إنشاء بطاقة الطلبات الأخيرة
     */
    private VBox createRecentApplicationsCard() {
        VBox card = new VBox(15);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);"
        );
        card.setPadding(new Insets(25));
        card.setMaxWidth(900);
        
        Label titleLabel = new Label("📋 الطلبات الأخيرة");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.web("#2C3E50"));
        
        VBox applicationsList = new VBox(10);
        applicationsList.getChildren().addAll(
            createApplicationItem("أحمد محمد علي", "تطوير تطبيقات الجوال", "منذ ساعتين", "#4CAF50"),
            createApplicationItem("فاطمة حسن", "تصميم واجهات المستخدم", "منذ 4 ساعات", "#2196F3"),
            createApplicationItem("محمود سعيد", "تطوير تطبيقات الجوال", "منذ يوم", "#FF9800"),
            createApplicationItem("مريم عبدالله", "علم البيانات", "منذ يومين", "#9C27B0")
        );
        
        card.getChildren().addAll(titleLabel, new Separator(), applicationsList);
        
        return card;
    }
    
    /**
     * إنشاء عنصر طلب
     */
    private HBox createApplicationItem(String studentName, String position, String time, String color) {
        HBox item = new HBox(15);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(12));
        item.setStyle(
            "-fx-background-color: #F8F9FA;" +
            "-fx-background-radius: 8;"
        );
        
        Label iconLabel = new Label("👤");
        iconLabel.setFont(Font.font(18));
        iconLabel.setPrefWidth(30);
        iconLabel.setAlignment(Pos.CENTER);
        iconLabel.setStyle(
            "-fx-background-color: " + color + ";" +
            "-fx-background-radius: 6;" +
            "-fx-padding: 5;"
        );
        
        VBox textBox = new VBox(3);
        Label nameLabel = new Label(studentName);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        nameLabel.setTextFill(Color.web("#2C3E50"));
        
        Label positionLabel = new Label("تقدم على: " + position);
        positionLabel.setFont(Font.font("Arial", 12));
        positionLabel.setTextFill(Color.web("#7F8C8D"));
        
        Label timeLabel = new Label(time);
        timeLabel.setFont(Font.font("Arial", 11));
        timeLabel.setTextFill(Color.web("#95A5A6"));
        
        textBox.getChildren().addAll(nameLabel, positionLabel, timeLabel);
        HBox.setHgrow(textBox, Priority.ALWAYS);
        
        Button viewBtn = new Button("عرض");
        viewBtn.setStyle(
            "-fx-background-color: #2196F3;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );
        
        item.getChildren().addAll(iconLabel, textBox, viewBtn);
        
        return item;
    }
    
    /**
     * فتح صفحة إضافة فرصة
     */
    private void openAddOpportunity() {
        AddOpportunityUI addOpp = new AddOpportunityUI();
        Stage stage = new Stage();
        addOpp.start(stage);
    }
    
    /**
     * فتح صفحة الطلبات
     */
    private void openApplications() {
        ApplicationsUI applications = new ApplicationsUI();
        Stage stage = new Stage();
        applications.start(stage);
    }
    
    /**
     * فتح صفحة التقييم
     */
    private void openEvaluate() {
        EvaluateStudentUI evaluate = new EvaluateStudentUI();
        Stage stage = new Stage();
        evaluate.start(stage);
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
    
    public static void main(String[] args) {
        launch(args);
    }
}