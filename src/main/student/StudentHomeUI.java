package main.student;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * الصفحة الرئيسية للطالب - عرض فرص التدريب المتاحة
 * Student Home Page - Available Training Opportunities
 */
public class StudentHomeUI extends Application {
    
    private Stage primaryStage;
    private TableView<TrainingOpportunity> opportunitiesTable;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("الصفحة الرئيسية - Student Home");
        
        Scene scene = createHomeScene();
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);
        primaryStage.show();
    }
    
    /**
     * إنشاء مشهد الصفحة الرئيسية
     */
    private Scene createHomeScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F5F7FA;");
        
        // الشريط العلوي
        root.setTop(createTopBar());
        
        // المحتوى الرئيسي
        root.setCenter(createMainContent());
        
        return new Scene(root);
    }
    
    /**
     * إنشاء الشريط العلوي
     */
    private VBox createTopBar() {
        VBox topBar = new VBox();
        topBar.setStyle("-fx-background-color: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2);");
        topBar.setPadding(new Insets(20, 30, 20, 30));
        
        // الصف الأول - الترحيب والقائمة
        HBox firstRow = new HBox();
        firstRow.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(firstRow, Priority.ALWAYS);
        
        // رسالة الترحيب
        VBox welcomeBox = new VBox(5);
        Label welcomeLabel = new Label("مرحباً، أحمد محمد");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        welcomeLabel.setTextFill(Color.web("#2C3E50"));
        
        Label dateLabel = new Label("الأحد، 5 أكتوبر 2025");
        dateLabel.setFont(Font.font("Arial", 12));
        dateLabel.setTextFill(Color.web("#7F8C8D"));
        
        welcomeBox.getChildren().addAll(welcomeLabel, dateLabel);
        
        // القائمة الجانبية
        HBox menuBox = new HBox(15);
        menuBox.setAlignment(Pos.CENTER_RIGHT);
        
        Button myApplicationsBtn = createMenuButton("📋 طلباتي");
        myApplicationsBtn.setOnAction(e -> showInfo("عرض طلباتي"));
        
        Button evaluationsBtn = createMenuButton("⭐ التقييمات");
        evaluationsBtn.setOnAction(e -> openEvaluations());
        
        Button profileBtn = createMenuButton("👤 الملف الشخصي");
        profileBtn.setOnAction(e -> showInfo("الملف الشخصي"));
        
        Button logoutBtn = createMenuButton("🚪 تسجيل خروج");
        logoutBtn.setStyle(
            "-fx-background-color: #E74C3C;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        logoutBtn.setOnAction(e -> logout());
        
        menuBox.getChildren().addAll(myApplicationsBtn, evaluationsBtn, profileBtn, logoutBtn);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        firstRow.getChildren().addAll(welcomeBox, spacer, menuBox);
        
        topBar.getChildren().add(firstRow);
        
        return topBar;
    }
    
    /**
     * إنشاء زر القائمة
     */
    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", 13));
        button.setPrefHeight(35);
        button.setStyle(
            "-fx-background-color: #ECF0F1;" +
            "-fx-text-fill: #2C3E50;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        
        button.setOnMouseEntered(e -> 
            button.setStyle(
                "-fx-background-color: #BDC3C7;" +
                "-fx-text-fill: #2C3E50;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            )
        );
        
        button.setOnMouseExited(e -> 
            button.setStyle(
                "-fx-background-color: #ECF0F1;" +
                "-fx-text-fill: #2C3E50;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            )
        );
        
        return button;
    }
    
    /**
     * إنشاء المحتوى الرئيسي
     */
    private VBox createMainContent() {
        VBox content = new VBox(20);
        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);
        
        // عنوان القسم
        Label titleLabel = new Label("🎯 فرص التدريب المتاحة");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        titleLabel.setTextFill(Color.web("#2C3E50"));
        
        Label subtitleLabel = new Label("Available Training Opportunities");
        subtitleLabel.setFont(Font.font("Arial", 14));
        subtitleLabel.setTextFill(Color.web("#7F8C8D"));
        
        // بطاقة الجدول
        VBox tableCard = new VBox(15);
        tableCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 3);"
        );
        tableCard.setPadding(new Insets(25));
        tableCard.setMaxWidth(900);
        
        // شريط البحث والفلاتر
        HBox searchBar = createSearchBar();
        
        // جدول الفرص
        opportunitiesTable = createOpportunitiesTable();
        
        // زر التقديم
        Button applyButton = new Button("✓ التقديم على التدريب المحدد");
        applyButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        applyButton.setPrefWidth(300);
        applyButton.setPrefHeight(45);
        applyButton.setStyle(
            "-fx-background-color: #4CAF50;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10;" +
            "-fx-cursor: hand;"
        );
        applyButton.setOnAction(e -> handleApply());
        
        HBox buttonBox = new HBox(applyButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        
        tableCard.getChildren().addAll(searchBar, opportunitiesTable, buttonBox);
        
        content.getChildren().addAll(titleLabel, subtitleLabel, tableCard);
        
        return content;
    }
    
    /**
     * إنشاء شريط البحث
     */
    private HBox createSearchBar() {
        HBox searchBar = new HBox(15);
        searchBar.setAlignment(Pos.CENTER_LEFT);
        searchBar.setPadding(new Insets(0, 0, 10, 0));
        
        // حقل البحث
        TextField searchField = new TextField();
        searchField.setPromptText("🔍 البحث عن فرصة تدريب...");
        searchField.setPrefWidth(300);
        searchField.setPrefHeight(38);
        searchField.setFont(Font.font("Arial", 13));
        searchField.setStyle(
            "-fx-background-color: #F8F9FA;" +
            "-fx-border-color: #E0E0E0;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 10;"
        );
        
        // فلتر المدة
        ComboBox<String> durationFilter = new ComboBox<>();
        durationFilter.setPromptText("المدة");
        durationFilter.getItems().addAll("جميع المدد", "شهر", "شهرين", "3 أشهر", "أكثر من 3 أشهر");
        durationFilter.setPrefHeight(38);
        durationFilter.setStyle("-fx-font-size: 13;");
        
        // فلتر الحالة
        ComboBox<String> statusFilter = new ComboBox<>();
        statusFilter.setPromptText("الحالة");
        statusFilter.getItems().addAll("جميع الحالات", "متاح", "مغلق");
        statusFilter.setPrefHeight(38);
        statusFilter.setStyle("-fx-font-size: 13;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        // زر تحديث
        Button refreshBtn = new Button("🔄 تحديث");
        refreshBtn.setPrefHeight(38);
        refreshBtn.setStyle(
            "-fx-background-color: #3498DB;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        refreshBtn.setOnAction(e -> showInfo("تم تحديث القائمة"));
        
        searchBar.getChildren().addAll(
            searchField, 
            durationFilter, 
            statusFilter, 
            spacer, 
            refreshBtn
        );
        
        return searchBar;
    }
    
    /**
     * إنشاء جدول فرص التدريب
     */
    private TableView<TrainingOpportunity> createOpportunitiesTable() {
        TableView<TrainingOpportunity> table = new TableView<>();
        table.setStyle("-fx-background-color: transparent;");
        table.setPrefHeight(400);
        
        // عمود اسم الشركة
        TableColumn<TrainingOpportunity, String> companyCol = new TableColumn<>("اسم الشركة\nCompany Name");
        companyCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        companyCol.setPrefWidth(200);
        companyCol.setStyle("-fx-alignment: CENTER;");
        
        // عمود عنوان التدريب
        TableColumn<TrainingOpportunity, String> titleCol = new TableColumn<>("عنوان التدريب\nTraining Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setPrefWidth(250);
        titleCol.setStyle("-fx-alignment: CENTER;");
        
        // عمود المدة
        TableColumn<TrainingOpportunity, String> durationCol = new TableColumn<>("المدة\nDuration");
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationCol.setPrefWidth(150);
        durationCol.setStyle("-fx-alignment: CENTER;");
        
        // عمود الحالة
        TableColumn<TrainingOpportunity, String> statusCol = new TableColumn<>("الحالة\nStatus");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(150);
        statusCol.setStyle("-fx-alignment: CENTER;");
        
        // تخصيص خلايا الحالة بالألوان
        statusCol.setCellFactory(column -> new TableCell<TrainingOpportunity, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER);
                    if (item.contains("متاح")) {
                        setStyle("-fx-background-color: #D4EDDA; -fx-text-fill: #155724; -fx-font-weight: bold;");
                    } else if (item.contains("قيد المراجعة")) {
                        setStyle("-fx-background-color: #FFF3CD; -fx-text-fill: #856404; -fx-font-weight: bold;");
                    } else {
                        setStyle("-fx-background-color: #F8D7DA; -fx-text-fill: #721C24; -fx-font-weight: bold;");
                    }
                }
            }
        });
        
        table.getColumns().addAll(companyCol, titleCol, durationCol, statusCol);
        
        // البيانات التجريبية
        table.setItems(getMockData());
        
        return table;
    }
    
    /**
     * الحصول على البيانات التجريبية
     */
    private ObservableList<TrainingOpportunity> getMockData() {
        ObservableList<TrainingOpportunity> data = FXCollections.observableArrayList();
        
        data.add(new TrainingOpportunity(
            "شركة التقنية المتقدمة", 
            "تدريب في تطوير تطبيقات الجوال", 
            "3 أشهر",
            "متاح / Available"
        ));
        
        data.add(new TrainingOpportunity(
            "مجموعة الابتكار الرقمي", 
            "تدريب في تصميم واجهات المستخدم", 
            "شهرين",
            "متاح / Available"
        ));
        
        data.add(new TrainingOpportunity(
            "شركة البيانات الذكية", 
            "تدريب في علم البيانات والذكاء الاصطناعي", 
            "4 أشهر",
            "متاح / Available"
        ));
        
        data.add(new TrainingOpportunity(
            "مؤسسة الأمن السيبراني", 
            "تدريب في أمن المعلومات", 
            "شهرين",
            "قيد المراجعة / Under Review"
        ));
        
        data.add(new TrainingOpportunity(
            "شركة التسويق الإلكتروني", 
            "تدريب في التسويق الرقمي ووسائل التواصل", 
            "شهر",
            "متاح / Available"
        ));
        
        data.add(new TrainingOpportunity(
            "مركز البرمجة الإبداعية", 
            "تدريب في تطوير الويب Full Stack", 
            "3 أشهر",
            "مغلق / Closed"
        ));
        
        return data;
    }
    
    /**
     * معالجة التقديم على التدريب
     */
    private void handleApply() {
        TrainingOpportunity selected = opportunitiesTable.getSelectionModel().getSelectedItem();
        
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "تنبيه", "الرجاء اختيار فرصة تدريب أولاً");
            return;
        }
        
        if (selected.getStatus().contains("مغلق")) {
            showAlert(Alert.AlertType.ERROR, "غير متاح", "هذه الفرصة مغلقة حالياً");
            return;
        }
        
        // رسالة نجاح التقديم
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("نجح التقديم");
        alert.setHeaderText("تم التقديم بنجاح!");
        alert.setContentText(
            "تم تقديم طلبك على:\n" +
            selected.getTitle() + "\n" +
            "الشركة: " + selected.getCompanyName() + "\n\n" +
            "سيتم مراجعة طلبك والرد عليك قريباً."
        );
        alert.showAndWait();
    }
    
    /**
     * فتح صفحة التقييمات
     */
    private void openEvaluations() {
        StudentEvaluationUI evaluationUI = new StudentEvaluationUI();
        Stage stage = new Stage();
        evaluationUI.start(stage);
    }
    
    /**
     * تسجيل الخروج
     */
    private void logout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("تسجيل خروج");
        alert.setHeaderText("هل أنت متأكد من تسجيل الخروج؟");
        alert.setContentText("سيتم إغلاق الجلسة الحالية");
        
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
    
    /**
     * عرض رسالة تنبيه
     */
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    /**
     * فئة فرصة التدريب
     */
    public static class TrainingOpportunity {
        private final SimpleStringProperty companyName;
        private final SimpleStringProperty title;
        private final SimpleStringProperty duration;
        private final SimpleStringProperty status;
        
        public TrainingOpportunity(String companyName, String title, String duration, String status) {
            this.companyName = new SimpleStringProperty(companyName);
            this.title = new SimpleStringProperty(title);
            this.duration = new SimpleStringProperty(duration);
            this.status = new SimpleStringProperty(status);
        }
        
        public String getCompanyName() { return companyName.get(); }
        public String getTitle() { return title.get(); }
        public String getDuration() { return duration.get(); }
        public String getStatus() { return status.get(); }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}