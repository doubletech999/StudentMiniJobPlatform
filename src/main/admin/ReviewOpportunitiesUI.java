
package main.admin;

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
 * صفحة مراجعة والموافقة على فرص التدريب
 * Review and Approve Training Opportunities
 */
public class ReviewOpportunitiesUI extends Application {
    
    private Stage primaryStage;
    private TableView<Opportunity> opportunitiesTable;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("مراجعة فرص التدريب - Review Opportunities");
        
        Scene scene = createReviewScene();
        primaryStage.setScene(scene);
        primaryStage.setWidth(1250);
        primaryStage.setHeight(750);
        primaryStage.show();
    }
    
    /**
     * إنشاء مشهد المراجعة
     */
    private Scene createReviewScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #ECEFF1;");
        
        root.setTop(createTopBar());
        root.setCenter(createMainContent());
        
        return new Scene(root);
    }
    
    /**
     * إنشاء الشريط العلوي
     */
    private HBox createTopBar() {
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: white;");
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
        
        Label titleLabel = new Label("📝 مراجعة فرص التدريب");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.web("#263238"));
        titleLabel.setPadding(new Insets(0, 0, 0, 20));
        
        topBar.getChildren().addAll(backButton, titleLabel);
        
        return topBar;
    }
    
    /**
     * إنشاء المحتوى الرئيسي
     */
    private VBox createMainContent() {
        VBox content = new VBox(25);
        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);
        
        // إحصائيات الفرص
        HBox statsBox = new HBox(20);
        statsBox.setAlignment(Pos.CENTER);
        
        statsBox.getChildren().addAll(
            createStatCard("📝 إجمالي الفرص", "89", "#2196F3"),
            createStatCard("⏳ قيد المراجعة", "12", "#FF9800"),
            createStatCard("✅ موافق عليها", "68", "#4CAF50"),
            createStatCard("❌ مرفوضة", "9", "#F44336")
        );
        
        // بطاقة الجدول
        VBox tableCard = new VBox(20);
        tableCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);"
        );
        tableCard.setPadding(new Insets(25));
        tableCard.setMaxWidth(1150);
        
        // شريط الأدوات
        HBox toolBar = createToolBar();
        
        // جدول الفرص
        opportunitiesTable = createOpportunitiesTable();
        
        tableCard.getChildren().addAll(toolBar, opportunitiesTable);
        
        content.getChildren().addAll(statsBox, tableCard);
        
        return content;
    }
    
    /**
     * إنشاء بطاقة إحصائية
     */
    private VBox createStatCard(String title, String value, String color) {
        VBox card = new VBox(8);
        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(180);
        card.setPrefHeight(100);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 10;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);"
        );
        card.setPadding(new Insets(15));
        
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        valueLabel.setTextFill(Color.web(color));
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 12));
        titleLabel.setTextFill(Color.web("#7F8C8D"));
        titleLabel.setWrapText(true);
        titleLabel.setAlignment(Pos.CENTER);
        
        card.getChildren().addAll(valueLabel, titleLabel);
        
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
        searchField.setPromptText("🔍 البحث عن فرصة...");
        searchField.setPrefWidth(280);
        searchField.setPrefHeight(38);
        searchField.setStyle(
            "-fx-background-color: #F5F5F5;" +
            "-fx-border-color: #E0E0E0;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;"
        );
        
        ComboBox<String> statusFilter = new ComboBox<>();
        statusFilter.setPromptText("تصفية حسب الحالة");
        statusFilter.getItems().addAll("الكل", "قيد المراجعة", "موافق عليها", "مرفوضة");
        statusFilter.setPrefHeight(38);
        
        ComboBox<String> categoryFilter = new ComboBox<>();
        categoryFilter.setPromptText("الفئة");
        categoryFilter.getItems().addAll("الكل", "تطوير", "تصميم", "تسويق", "إدارة", "بيانات");
        categoryFilter.setPrefHeight(38);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Button refreshBtn = new Button("🔄 تحديث");
        refreshBtn.setFont(Font.font("Arial", 13));
        refreshBtn.setPrefHeight(38);
        refreshBtn.setStyle(
            "-fx-background-color: #2196F3;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        
        toolBar.getChildren().addAll(searchField, statusFilter, categoryFilter, spacer, refreshBtn);
        
        return toolBar;
    }
    
    /**
     * إنشاء جدول الفرص
     */
    private TableView<Opportunity> createOpportunitiesTable() {
        TableView<Opportunity> table = new TableView<>();
        table.setPrefHeight(450);
        
        TableColumn<Opportunity, String> companyCol = new TableColumn<>("الشركة\nCompany");
        companyCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        companyCol.setPrefWidth(180);
        companyCol.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Opportunity, String> titleCol = new TableColumn<>("عنوان الفرصة\nTitle");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setPrefWidth(230);
        titleCol.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Opportunity, String> categoryCol = new TableColumn<>("الفئة\nCategory");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setPrefWidth(120);
        categoryCol.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Opportunity, String> durationCol = new TableColumn<>("المدة\nDuration");
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationCol.setPrefWidth(100);
        durationCol.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Opportunity, String> dateCol = new TableColumn<>("تاريخ النشر\nPost Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("postDate"));
        dateCol.setPrefWidth(120);
        dateCol.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<Opportunity, String> statusCol = new TableColumn<>("الحالة\nStatus");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(130);
        statusCol.setStyle("-fx-alignment: CENTER;");
        
        statusCol.setCellFactory(column -> new TableCell<Opportunity, String>() {
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
                        setStyle("-fx-background-color: #FFF3CD; -fx-text-fill: #856404; -fx-font-weight: bold;");
                    } else if (item.contains("موافق")) {
                        setStyle("-fx-background-color: #D4EDDA; -fx-text-fill: #155724; -fx-font-weight: bold;");
                    } else {
                        setStyle("-fx-background-color: #F8D7DA; -fx-text-fill: #721C24; -fx-font-weight: bold;");
                    }
                }
            }
        });
        
        TableColumn<Opportunity, Void> actionsCol = new TableColumn<>("الإجراءات\nActions");
        actionsCol.setPrefWidth(220);
        actionsCol.setStyle("-fx-alignment: CENTER;");
        
        actionsCol.setCellFactory(column -> new TableCell<Opportunity, Void>() {
            private final Button viewBtn = new Button("👁");
            private final Button approveBtn = new Button("✓");
            private final Button rejectBtn = new Button("✕");
            private final HBox buttons = new HBox(5, viewBtn, approveBtn, rejectBtn);
            
            {
                buttons.setAlignment(Pos.CENTER);
                
                viewBtn.setStyle(
                    "-fx-background-color: #2196F3;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
                );
                viewBtn.setPrefWidth(55);
                viewBtn.setPrefHeight(28);
                viewBtn.setTooltip(new Tooltip("عرض التفاصيل"));
                
                approveBtn.setStyle(
                    "-fx-background-color: #4CAF50;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
                );
                approveBtn.setPrefWidth(55);
                approveBtn.setPrefHeight(28);
                approveBtn.setTooltip(new Tooltip("موافقة"));
                
                rejectBtn.setStyle(
                    "-fx-background-color: #F44336;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
                );
                rejectBtn.setPrefWidth(55);
                rejectBtn.setPrefHeight(28);
                rejectBtn.setTooltip(new Tooltip("رفض"));
                
                viewBtn.setOnAction(event -> {
                    Opportunity opp = getTableView().getItems().get(getIndex());
                    showDetails(opp);
                });
                
                approveBtn.setOnAction(event -> {
                    Opportunity opp = getTableView().getItems().get(getIndex());
                    handleApprove(opp);
                });
                
                rejectBtn.setOnAction(event -> {
                    Opportunity opp = getTableView().getItems().get(getIndex());
                    handleReject(opp);
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Opportunity opp = getTableView().getItems().get(getIndex());
                    if (opp.getStatus().contains("قيد المراجعة")) {
                        setGraphic(buttons);
                    } else {
                        setGraphic(new HBox(viewBtn));
                    }
                }
            }
        });
        
        table.getColumns().addAll(companyCol, titleCol, categoryCol, durationCol, dateCol, statusCol, actionsCol);
        table.setItems(getMockData());
        
        return table;
    }
    
    /**
     * البيانات التجريبية
     */
    private ObservableList<Opportunity> getMockData() {
        ObservableList<Opportunity> data = FXCollections.observableArrayList();
        
        data.add(new Opportunity("شركة التقنية المتقدمة", "تدريب في تطوير تطبيقات الجوال", "تطوير", "3 أشهر", "2025-10-01", "قيد المراجعة"));
        data.add(new Opportunity("مجموعة الابتكار الرقمي", "تدريب في تصميم واجهات المستخدم", "تصميم", "شهرين", "2025-10-02", "قيد المراجعة"));
        data.add(new Opportunity("شركة البيانات الذكية", "تدريب في علم البيانات والذكاء الاصطناعي", "بيانات", "4 أشهر", "2025-09-28", "موافق عليها"));
        data.add(new Opportunity("مؤسسة الأمن السيبراني", "تدريب في أمن المعلومات", "تطوير", "شهرين", "2025-10-03", "قيد المراجعة"));
        data.add(new Opportunity("شركة التسويق الإلكتروني", "تدريب في التسويق الرقمي ووسائل التواصل", "تسويق", "شهر", "2025-09-25", "موافق عليها"));
        data.add(new Opportunity("مركز البرمجة الإبداعية", "تدريب في تطوير الويب Full Stack", "تطوير", "3 أشهر", "2025-09-20", "مرفوضة"));
        data.add(new Opportunity("شركة الإعلام الحديث", "تدريب في إدارة المحتوى الرقمي", "تسويق", "شهرين", "2025-10-04", "قيد المراجعة"));
        
        return data;
    }
    
    /**
     * عرض تفاصيل الفرصة
     */
    private void showDetails(Opportunity opp) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("تفاصيل الفرصة");
        alert.setHeaderText("معلومات تفصيلية عن الفرصة");
        alert.setContentText(
            "الشركة: " + opp.getCompanyName() + "\n" +
            "العنوان: " + opp.getTitle() + "\n" +
            "الفئة: " + opp.getCategory() + "\n" +
            "المدة: " + opp.getDuration() + "\n" +
            "تاريخ النشر: " + opp.getPostDate() + "\n" +
            "الحالة: " + opp.getStatus()
        );
        alert.showAndWait();
    }
    
    /**
     * الموافقة على الفرصة
     */
    private void handleApprove(Opportunity opp) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("تأكيد الموافقة");
        alert.setHeaderText("الموافقة على فرصة التدريب");
        alert.setContentText("هل تريد الموافقة على:\n" + opp.getTitle() + "؟");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                opp.setStatus("موافق عليها");
                opportunitiesTable.refresh();
                showInfo("✓ تمت الموافقة على الفرصة بنجاح!\nسيتم نشرها للطلاب.");
            }
        });
    }
    
    /**
     * رفض الفرصة
     */
    private void handleReject(Opportunity opp) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("رفض الفرصة");
        dialog.setHeaderText("رفض فرصة التدريب: " + opp.getTitle());
        dialog.setContentText("سبب الرفض (اختياري):");
        
        dialog.showAndWait().ifPresent(reason -> {
            opp.setStatus("مرفوضة");
            opportunitiesTable.refresh();
            showInfo("تم رفض الفرصة.\nسيتم إشعار الشركة.");
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
     * فئة الفرصة
     */
    public static class Opportunity {
        private final SimpleStringProperty companyName;
        private final SimpleStringProperty title;
        private final SimpleStringProperty category;
        private final SimpleStringProperty duration;
        private final SimpleStringProperty postDate;
        private final SimpleStringProperty status;
        
        public Opportunity(String companyName, String title, String category, 
                          String duration, String postDate, String status) {
            this.companyName = new SimpleStringProperty(companyName);
            this.title = new SimpleStringProperty(title);
            this.category = new SimpleStringProperty(category);
            this.duration = new SimpleStringProperty(duration);
            this.postDate = new SimpleStringProperty(postDate);
            this.status = new SimpleStringProperty(status);
        }
        
        public String getCompanyName() { return companyName.get(); }
        public String getTitle() { return title.get(); }
        public String getCategory() { return category.get(); }
        public String getDuration() { return duration.get(); }
        public String getPostDate() { return postDate.get(); }
        public String getStatus() { return status.get(); }
        public void setStatus(String value) { status.set(value); }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}