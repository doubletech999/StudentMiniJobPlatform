package main.employer;

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
 * صفحة عرض وإدارة الطلبات الواردة للشركة
 * Applications Management Page for Employer
 */
public class ApplicationsUI extends Application {
    
    private Stage primaryStage;
    private TableView<StudentApplication> applicationsTable;
    private Label totalApplicationsLabel;
    private Label pendingLabel;
    private Label acceptedLabel;
    private Label rejectedLabel;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("الطلبات الواردة - Applications");
        
        Scene scene = createApplicationsScene();
        primaryStage.setScene(scene);
        primaryStage.setWidth(1150);
        primaryStage.setHeight(700);
        primaryStage.show();
    }
    
    /**
     * إنشاء مشهد الطلبات
     */
    private Scene createApplicationsScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F5F7FA;");
        
        root.setTop(createTopBar());
        root.setCenter(createMainContent());
        
        return new Scene(root);
    }
    
    /**
     * إنشاء الشريط العلوي
     */
    private HBox createTopBar() {
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 5, 0, 0, 2);");
        topBar.setPadding(new Insets(20, 30, 20, 30));
        topBar.setAlignment(Pos.CENTER_LEFT);
        
        Button backButton = new Button("← العودة");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        backButton.setStyle(
            "-fx-background-color: #ECF0F1;" +
            "-fx-text-fill: #2C3E50;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        backButton.setOnAction(e -> primaryStage.close());
        
        // تأثير الهوفر للزر
        backButton.setOnMouseEntered(e -> 
            backButton.setStyle(
                "-fx-background-color: #BDC3C7;" +
                "-fx-text-fill: #2C3E50;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnMouseExited(e -> 
            backButton.setStyle(
                "-fx-background-color: #ECF0F1;" +
                "-fx-text-fill: #2C3E50;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            )
        );
        
        Label titleLabel = new Label("📋 الطلبات الواردة");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.web("#2C3E50"));
        titleLabel.setPadding(new Insets(0, 0, 0, 20));
        
        Label subtitleLabel = new Label("Received Applications");
        subtitleLabel.setFont(Font.font("Arial", 12));
        subtitleLabel.setTextFill(Color.web("#7F8C8D"));
        subtitleLabel.setPadding(new Insets(0, 0, 0, 20));
        
        VBox titleBox = new VBox(2);
        titleBox.getChildren().addAll(titleLabel, subtitleLabel);
        
        topBar.getChildren().addAll(backButton, titleBox);
        
        return topBar;
    }
    
    /**
     * إنشاء المحتوى الرئيسي
     */
    private VBox createMainContent() {
        VBox content = new VBox(25);
        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);
        
        // إحصائيات الطلبات
        HBox statsBox = new HBox(20);
        statsBox.setAlignment(Pos.CENTER);
        
        VBox totalCard = createStatCard("📥 إجمالي الطلبات", "45", "#2196F3");
        totalApplicationsLabel = (Label) ((VBox) totalCard.getChildren().get(0)).getChildren().get(0);
        
        VBox pendingCard = createStatCard("⏳ قيد المراجعة", "17", "#FF9800");
        pendingLabel = (Label) ((VBox) pendingCard.getChildren().get(0)).getChildren().get(0);
        
        VBox acceptedCard = createStatCard("✅ مقبولة", "28", "#4CAF50");
        acceptedLabel = (Label) ((VBox) acceptedCard.getChildren().get(0)).getChildren().get(0);
        
        VBox rejectedCard = createStatCard("❌ مرفوضة", "10", "#F44336");
        rejectedLabel = (Label) ((VBox) rejectedCard.getChildren().get(0)).getChildren().get(0);
        
        statsBox.getChildren().addAll(totalCard, pendingCard, acceptedCard, rejectedCard);
        
        // بطاقة الجدول
        VBox tableCard = new VBox(20);
        tableCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);"
        );
        tableCard.setPadding(new Insets(25));
        tableCard.setMaxWidth(1050);
        
        HBox toolBar = createToolBar();
        applicationsTable = createApplicationsTable();
        
        tableCard.getChildren().addAll(toolBar, applicationsTable);
        
        content.getChildren().addAll(statsBox, tableCard);
        
        return content;
    }
    
    /**
     * إنشاء بطاقة إحصائية
     */
    private VBox createStatCard(String title, String value, String color) {
        VBox card = new VBox(8);
        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(170);
        card.setPrefHeight(100);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 10;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);"
        );
        card.setPadding(new Insets(15));
        
        VBox innerBox = new VBox(5);
        innerBox.setAlignment(Pos.CENTER);
        
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        valueLabel.setTextFill(Color.web(color));
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        titleLabel.setTextFill(Color.web("#7F8C8D"));
        titleLabel.setWrapText(true);
        titleLabel.setAlignment(Pos.CENTER);
        
        innerBox.getChildren().addAll(valueLabel, titleLabel);
        card.getChildren().add(innerBox);
        
        return card;
    }
    
    /**
     * إنشاء شريط الأدوات
     */
    private HBox createToolBar() {
        HBox toolBar = new HBox(15);
        toolBar.setAlignment(Pos.CENTER_LEFT);
        toolBar.setPadding(new Insets(0, 0, 15, 0));
        
        TextField searchField = new TextField();
        searchField.setPromptText("🔍 البحث عن طالب...");
        searchField.setPrefWidth(280);
        searchField.setPrefHeight(38);
        searchField.setStyle(
            "-fx-background-color: #F8F9FA;" +
            "-fx-border-color: #E0E0E0;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 0 10 0 10;"
        );
        
        // البحث المباشر
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue);
        });
        
        ComboBox<String> statusFilter = new ComboBox<>();
        statusFilter.setPromptText("تصفية حسب الحالة");
        statusFilter.getItems().addAll("الكل", "قيد المراجعة", "مقبولة", "مرفوضة");
        statusFilter.setPrefHeight(38);
        statusFilter.setPrefWidth(150);
        statusFilter.setStyle("-fx-font-size: 13;");
        
        statusFilter.setOnAction(e -> {
            String selected = statusFilter.getValue();
            if (selected != null) {
                filterByStatus(selected);
            }
        });
        
        ComboBox<String> positionFilter = new ComboBox<>();
        positionFilter.setPromptText("الوظيفة");
        positionFilter.getItems().addAll("الكل", "تطوير تطبيقات", "تصميم UI", "علم البيانات", "تسويق رقمي");
        positionFilter.setPrefHeight(38);
        positionFilter.setPrefWidth(140);
        positionFilter.setStyle("-fx-font-size: 13;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Button refreshBtn = new Button("🔄 تحديث");
        refreshBtn.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        refreshBtn.setPrefHeight(38);
        refreshBtn.setStyle(
            "-fx-background-color: #2196F3;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        refreshBtn.setOnAction(e -> refreshTable());
        
        Button exportBtn = new Button("📥 تصدير");
        exportBtn.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        exportBtn.setPrefHeight(38);
        exportBtn.setStyle(
            "-fx-background-color: #4CAF50;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        exportBtn.setOnAction(e -> exportData());
        
        toolBar.getChildren().addAll(searchField, statusFilter, positionFilter, spacer, refreshBtn, exportBtn);
        
        return toolBar;
    }
    
    /**
     * إنشاء جدول الطلبات
     */
    private TableView<StudentApplication> createApplicationsTable() {
        TableView<StudentApplication> table = new TableView<>();
        table.setPrefHeight(400);
        table.setStyle("-fx-background-color: transparent;");
        
        // عمود اسم الطالب
        TableColumn<StudentApplication, String> studentCol = new TableColumn<>("اسم الطالب\nStudent Name");
        studentCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentCol.setPrefWidth(180);
        studentCol.setStyle("-fx-alignment: CENTER;");
        
        // عمود الوظيفة المطلوبة
        TableColumn<StudentApplication, String> positionCol = new TableColumn<>("الوظيفة المطلوبة\nPosition");
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        positionCol.setPrefWidth(200);
        positionCol.setStyle("-fx-alignment: CENTER;");
        
        // عمود الجامعة
        TableColumn<StudentApplication, String> universityCol = new TableColumn<>("الجامعة\nUniversity");
        universityCol.setCellValueFactory(new PropertyValueFactory<>("university"));
        universityCol.setPrefWidth(150);
        universityCol.setStyle("-fx-alignment: CENTER;");
        
        // عمود المعدل
        TableColumn<StudentApplication, String> gpaCol = new TableColumn<>("المعدل\nGPA");
        gpaCol.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        gpaCol.setPrefWidth(80);
        gpaCol.setStyle("-fx-alignment: CENTER;");
        
        // تلوين المعدل حسب القيمة
        gpaCol.setCellFactory(column -> new TableCell<StudentApplication, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER);
                    try {
                        double gpa = Double.parseDouble(item);
                        if (gpa >= 3.5) {
                            setStyle("-fx-text-fill: #4CAF50; -fx-font-weight: bold;");
                        } else if (gpa >= 3.0) {
                            setStyle("-fx-text-fill: #FF9800; -fx-font-weight: bold;");
                        } else {
                            setStyle("-fx-text-fill: #F44336;");
                        }
                    } catch (NumberFormatException e) {
                        setStyle("");
                    }
                }
            }
        });
        
        // عمود التاريخ
        TableColumn<StudentApplication, String> dateCol = new TableColumn<>("التاريخ\nDate");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
        dateCol.setPrefWidth(120);
        dateCol.setStyle("-fx-alignment: CENTER;");
        
        // عمود الحالة
        TableColumn<StudentApplication, String> statusCol = new TableColumn<>("الحالة\nStatus");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(120);
        statusCol.setStyle("-fx-alignment: CENTER;");
        
        // تخصيص خلايا الحالة بالألوان
        statusCol.setCellFactory(column -> new TableCell<StudentApplication, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER);
                    if (item.contains("قيد المراجعة")) {
                        setStyle("-fx-background-color: #FFF3CD; -fx-text-fill: #856404; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else if (item.contains("مقبولة")) {
                        setStyle("-fx-background-color: #D4EDDA; -fx-text-fill: #155724; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else {
                        setStyle("-fx-background-color: #F8D7DA; -fx-text-fill: #721C24; -fx-font-weight: bold; -fx-background-radius: 5;");
                    }
                }
            }
        });
        
        // عمود الإجراءات
        TableColumn<StudentApplication, Void> actionsCol = new TableColumn<>("الإجراءات\nActions");
        actionsCol.setPrefWidth(200);
        actionsCol.setStyle("-fx-alignment: CENTER;");
        
        actionsCol.setCellFactory(column -> new TableCell<StudentApplication, Void>() {
            private final Button viewBtn = new Button("👁");
            private final Button acceptBtn = new Button("✓");
            private final Button rejectBtn = new Button("✕");
            private final HBox buttons = new HBox(5, viewBtn, acceptBtn, rejectBtn);
            
            {
                buttons.setAlignment(Pos.CENTER);
                
                // زر العرض
                viewBtn.setStyle(
                    "-fx-background-color: #2196F3;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;" +
                    "-fx-font-size: 14;"
                );
                viewBtn.setPrefWidth(50);
                viewBtn.setPrefHeight(28);
                viewBtn.setTooltip(new Tooltip("عرض التفاصيل"));
                
                // زر القبول
                acceptBtn.setStyle(
                    "-fx-background-color: #4CAF50;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;" +
                    "-fx-font-size: 14;"
                );
                acceptBtn.setPrefWidth(50);
                acceptBtn.setPrefHeight(28);
                acceptBtn.setTooltip(new Tooltip("قبول الطلب"));
                
                // زر الرفض
                rejectBtn.setStyle(
                    "-fx-background-color: #F44336;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;" +
                    "-fx-font-size: 14;"
                );
                rejectBtn.setPrefWidth(50);
                rejectBtn.setPrefHeight(28);
                rejectBtn.setTooltip(new Tooltip("رفض الطلب"));
                
                // الأحداث
                viewBtn.setOnAction(event -> {
                    StudentApplication app = getTableView().getItems().get(getIndex());
                    showDetails(app);
                });
                
                acceptBtn.setOnAction(event -> {
                    StudentApplication app = getTableView().getItems().get(getIndex());
                    handleAccept(app);
                });
                
                rejectBtn.setOnAction(event -> {
                    StudentApplication app = getTableView().getItems().get(getIndex());
                    handleReject(app);
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    StudentApplication app = getTableView().getItems().get(getIndex());
                    if (app.getStatus().contains("قيد المراجعة")) {
                        setGraphic(buttons);
                    } else {
                        HBox viewOnly = new HBox(viewBtn);
                        viewOnly.setAlignment(Pos.CENTER);
                        setGraphic(viewOnly);
                    }
                }
            }
        });
        
        table.getColumns().addAll(studentCol, positionCol, universityCol, gpaCol, dateCol, statusCol, actionsCol);
        table.setItems(getMockData());
        
        // تفعيل اختيار صف واحد
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        return table;
    }
    
    /**
     * البيانات التجريبية
     */
    private ObservableList<StudentApplication> getMockData() {
        ObservableList<StudentApplication> data = FXCollections.observableArrayList();
        
        data.add(new StudentApplication("أحمد محمد علي", "تدريب في تطوير تطبيقات الجوال", "جامعة النجاح الوطنية", "3.8", "2025-10-03", "قيد المراجعة"));
        data.add(new StudentApplication("فاطمة حسن أحمد", "تدريب في تصميم واجهات المستخدم", "جامعة بيرزيت", "3.6", "2025-10-02", "قيد المراجعة"));
        data.add(new StudentApplication("محمود سعيد خالد", "تدريب في تطوير تطبيقات الجوال", "جامعة بوليتكنك فلسطين", "3.9", "2025-10-01", "مقبولة"));
        data.add(new StudentApplication("مريم عبدالله يوسف", "تدريب في علم البيانات والذكاء الاصطناعي", "الجامعة الإسلامية", "3.7", "2025-09-30", "قيد المراجعة"));
        data.add(new StudentApplication("عمر خالد حسن", "تدريب في تطوير تطبيقات الجوال", "جامعة الخليل", "3.5", "2025-09-28", "مقبولة"));
        data.add(new StudentApplication("سارة أحمد محمود", "تدريب في تصميم واجهات المستخدم", "جامعة النجاح الوطنية", "3.4", "2025-09-25", "مرفوضة"));
        data.add(new StudentApplication("ياسر علي إبراهيم", "تدريب في التسويق الرقمي", "جامعة بيرزيت", "3.2", "2025-09-24", "قيد المراجعة"));
        data.add(new StudentApplication("نور الدين محمد", "تدريب في علم البيانات والذكاء الاصطناعي", "جامعة النجاح الوطنية", "4.0", "2025-09-23", "مقبولة"));
        
        return data;
    }
    
    /**
     * عرض تفاصيل الطلب
     */
    private void showDetails(StudentApplication app) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("تفاصيل الطلب");
        alert.setHeaderText("معلومات تفصيلية عن الطالب");
        
        String details = String.format(
            "📋 معلومات الطلب:\n" +
            "━━━━━━━━━━━━━━━━━━━━━━\n\n" +
            "👤 الاسم: %s\n" +
            "💼 الوظيفة: %s\n" +
            "🎓 الجامعة: %s\n" +
            "📊 المعدل: %s\n" +
            "📅 تاريخ التقديم: %s\n" +
            "📌 الحالة: %s\n\n" +
            "━━━━━━━━━━━━━━━━━━━━━━\n" +
            "💡 المهارات: Java, Android Studio, Kotlin, Git\n" +
            "📝 الخبرات: مشروع تخرج في تطوير تطبيق موبايل\n" +
            "🏆 الإنجازات: جائزة أفضل مشروع تخرج 2024",
            app.getStudentName(),
            app.getPosition(),
            app.getUniversity(),
            app.getGpa(),
            app.getApplicationDate(),
            app.getStatus()
        );
        
        alert.setContentText(details);
        
        // تكبير النافذة
        alert.getDialogPane().setPrefWidth(500);
        
        alert.showAndWait();
    }
    
    /**
     * قبول الطلب
     */
    private void handleAccept(StudentApplication app) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("تأكيد القبول");
        alert.setHeaderText("قبول طلب الطالب");
        alert.setContentText("هل تريد قبول طلب:\n" + app.getStudentName() + "\nللتدريب في: " + app.getPosition() + "؟");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                app.setStatus("مقبولة");
                applicationsTable.refresh();
                updateStatistics();
                showInfo("✓ تم قبول الطلب بنجاح!\n\nسيتم إرسال إشعار للطالب عبر البريد الإلكتروني.");
            }
        });
    }
    
    /**
     * رفض الطلب
     */
    private void handleReject(StudentApplication app) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("رفض الطلب");
        dialog.setHeaderText("رفض طلب: " + app.getStudentName());
        dialog.setContentText("سبب الرفض (اختياري):");
        dialog.getDialogPane().setPrefWidth(400);
        
        dialog.showAndWait().ifPresent(reason -> {
            app.setStatus("مرفوضة");
            applicationsTable.refresh();
            updateStatistics();
            
            String message = "تم رفض الطلب.";
            if (!reason.trim().isEmpty()) {
                message += "\nالسبب: " + reason;
            }
            message += "\n\nسيتم إشعار الطالب.";
            
            showInfo(message);
        });
    }
    
    /**
     * تحديث الإحصائيات
     */
    private void updateStatistics() {
        ObservableList<StudentApplication> items = applicationsTable.getItems();
        
        int total = items.size();
        int pending = (int) items.stream().filter(app -> app.getStatus().contains("قيد المراجعة")).count();
        int accepted = (int) items.stream().filter(app -> app.getStatus().contains("مقبولة")).count();
        int rejected = (int) items.stream().filter(app -> app.getStatus().contains("مرفوضة")).count();
        
        totalApplicationsLabel.setText(String.valueOf(total));
        pendingLabel.setText(String.valueOf(pending));
        acceptedLabel.setText(String.valueOf(accepted));
        rejectedLabel.setText(String.valueOf(rejected));
    }
    
    /**
     * تصفية الجدول حسب البحث
     */
    private void filterTable(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            applicationsTable.setItems(getMockData());
        } else {
            ObservableList<StudentApplication> filtered = getMockData().filtered(
                app -> app.getStudentName().toLowerCase().contains(searchText.toLowerCase()) ||
                       app.getPosition().toLowerCase().contains(searchText.toLowerCase()) ||
                       app.getUniversity().toLowerCase().contains(searchText.toLowerCase())
            );
            applicationsTable.setItems(filtered);
        }
    }
    
    /**
     * تصفية حسب الحالة
     */
    private void filterByStatus(String status) {
        if ("الكل".equals(status)) {
            applicationsTable.setItems(getMockData());
        } else {
            ObservableList<StudentApplication> filtered = getMockData().filtered(
                app -> app.getStatus().contains(status)
            );
            applicationsTable.setItems(filtered);
        }
    }
    
    /**
     * تحديث الجدول
     */
    private void refreshTable() {
        applicationsTable.setItems(getMockData());
        updateStatistics();
        showInfo("✓ تم تحديث القائمة بنجاح!");
    }
    
    /**
     * تصدير البيانات
     */
    private void exportData() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("تصدير البيانات");
        alert.setHeaderText("تصدير الطلبات");
        alert.setContentText("سيتم تصدير جميع الطلبات إلى ملف Excel.\n\nالملف: applications_export.xlsx");
        alert.showAndWait();
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
     * فئة طلب الطالب
     */
    public static class StudentApplication {
        private final SimpleStringProperty studentName;
        private final SimpleStringProperty position;
        private final SimpleStringProperty university;
        private final SimpleStringProperty gpa;
        private final SimpleStringProperty applicationDate;
        private final SimpleStringProperty status;
        
        public StudentApplication(String studentName, String position, String university,
                                 String gpa, String applicationDate, String status) {
            this.studentName = new SimpleStringProperty(studentName);
            this.position = new SimpleStringProperty(position);
            this.university = new SimpleStringProperty(university);
            this.gpa = new SimpleStringProperty(gpa);
            this.applicationDate = new SimpleStringProperty(applicationDate);
            this.status = new SimpleStringProperty(status);
        }
        
        public String getStudentName() { return studentName.get(); }
        public String getPosition() { return position.get(); }
        public String getUniversity() { return university.get(); }
        public String getGpa() { return gpa.get(); }
        public String getApplicationDate() { return applicationDate.get(); }
        public String getStatus() { return status.get(); }
        
        public void setStudentName(String value) { studentName.set(value); }
        public void setPosition(String value) { position.set(value); }
        public void setUniversity(String value) { university.set(value); }
        public void setGpa(String value) { gpa.set(value); }
        public void setApplicationDate(String value) { applicationDate.set(value); }
        public void setStatus(String value) { status.set(value); }
        
        public SimpleStringProperty studentNameProperty() { return studentName; }
        public SimpleStringProperty positionProperty() { return position; }
        public SimpleStringProperty universityProperty() { return university; }
        public SimpleStringProperty gpaProperty() { return gpa; }
        public SimpleStringProperty applicationDateProperty() { return applicationDate; }
        public SimpleStringProperty statusProperty() { return status; }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}