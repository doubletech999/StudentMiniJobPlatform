package main.employer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * صفحة إضافة فرصة تدريب جديدة
 * Add New Training Opportunity Page
 */
public class AddOpportunityUI extends Application {
    
    private Stage primaryStage;
    private TextField titleField;
    private TextField durationField;
    private TextField requirementsField;
    private TextArea descriptionArea;
    private ComboBox<String> categoryCombo;
    private ComboBox<String> locationCombo;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("إضافة فرصة تدريب - Add Opportunity");
        
        Scene scene = createAddOpportunityScene();
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(750);
        primaryStage.show();
    }
    
    /**
     * إنشاء مشهد إضافة الفرصة
     */
    private Scene createAddOpportunityScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F5F7FA;");
        
        // الشريط العلوي
        root.setTop(createTopBar());
        
        // المحتوى الرئيسي
        ScrollPane scrollPane = new ScrollPane(createFormContent());
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        root.setCenter(scrollPane);
        
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
        
        Label titleLabel = new Label("➕ إضافة فرصة تدريب جديدة");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web("#2C3E50"));
        titleLabel.setPadding(new Insets(0, 0, 0, 20));
        
        topBar.getChildren().addAll(backButton, titleLabel);
        
        return topBar;
    }
    
    /**
     * إنشاء محتوى النموذج
     */
    private VBox createFormContent() {
        VBox content = new VBox(25);
        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);
        content.setMaxWidth(700);
        
        // بطاقة النموذج
        VBox formCard = new VBox(20);
        formCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 3);"
        );
        formCard.setPadding(new Insets(30));
        
        // عنوان النموذج
        Label formTitle = new Label("معلومات فرصة التدريب");
        formTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        formTitle.setTextFill(Color.web("#1565C0"));
        
        Label formSubtitle = new Label("الرجاء ملء جميع البيانات المطلوبة");
        formSubtitle.setFont(Font.font("Arial", 13));
        formSubtitle.setTextFill(Color.web("#7F8C8D"));
        
        Separator separator1 = new Separator();
        separator1.setPadding(new Insets(5, 0, 5, 0));
        
        // حقل عنوان التدريب
        VBox titleBox = createInputField("عنوان التدريب *", "مثال: تدريب في تطوير تطبيقات الجوال");
        titleField = (TextField) ((HBox) titleBox.getChildren().get(1)).getChildren().get(0);
        
        // صف المدة والتصنيف
        HBox row1 = new HBox(15);
        row1.setAlignment(Pos.TOP_CENTER);
        
        VBox durationBox = createInputField("المدة *", "مثال: 3 أشهر");
        durationField = (TextField) ((HBox) durationBox.getChildren().get(1)).getChildren().get(0);
        HBox.setHgrow(durationBox, Priority.ALWAYS);
        
        VBox categoryBox = createComboField("التصنيف *", 
            new String[]{"تطوير البرمجيات", "تصميم", "تسويق", "إدارة أعمال", "علم البيانات", "أخرى"});
        categoryCombo = (ComboBox<String>) ((HBox) categoryBox.getChildren().get(1)).getChildren().get(0);
        HBox.setHgrow(categoryBox, Priority.ALWAYS);
        
        row1.getChildren().addAll(durationBox, categoryBox);
        
        // حقل الموقع
        VBox locationBox = createComboField("الموقع *",
            new String[]{"نابلس", "رام الله", "القدس", "الخليل", "بيت لحم", "عن بعد"});
        locationCombo = (ComboBox<String>) ((HBox) locationBox.getChildren().get(1)).getChildren().get(0);
        
        // حقل المتطلبات
        VBox requirementsBox = createInputField("المتطلبات *", "مثال: إتقان Java, خبرة سابقة في Android Studio");
        requirementsField = (TextField) ((HBox) requirementsBox.getChildren().get(1)).getChildren().get(0);
        
        // حقل الوصف
        VBox descriptionBox = createTextAreaField("وصف التدريب *", 
            "اكتب وصفاً تفصيلياً عن فرصة التدريب، المهام المتوقعة، والفوائد...");
        descriptionArea = (TextArea) ((VBox) descriptionBox.getChildren().get(1)).getChildren().get(0);
        
        // معلومات إضافية
        VBox additionalInfo = new VBox(10);
        additionalInfo.setStyle(
            "-fx-background-color: #E3F2FD;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 15;"
        );
        
        Label infoLabel = new Label("ℹ️ معلومات إضافية:");
        infoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        
        CheckBox isPaid = new CheckBox("تدريب مدفوع");
        CheckBox providesCertificate = new CheckBox("توفير شهادة");
        CheckBox isRemote = new CheckBox("عمل عن بعد");
        
        isPaid.setFont(Font.font("Arial", 12));
        providesCertificate.setFont(Font.font("Arial", 12));
        isRemote.setFont(Font.font("Arial", 12));
        
        additionalInfo.getChildren().addAll(infoLabel, isPaid, providesCertificate, isRemote);
        
        // أزرار الحفظ والإلغاء
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        
        Button saveButton = new Button("✓ حفظ الفرصة");
        saveButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        saveButton.setPrefWidth(200);
        saveButton.setPrefHeight(45);
        saveButton.setStyle(
            "-fx-background-color: #4CAF50;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10;" +
            "-fx-cursor: hand;"
        );
        saveButton.setOnAction(e -> handleSave());
        
        Button cancelButton = new Button("✕ إلغاء");
        cancelButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        cancelButton.setPrefWidth(200);
        cancelButton.setPrefHeight(45);
        cancelButton.setStyle(
            "-fx-background-color: #F44336;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10;" +
            "-fx-cursor: hand;"
        );
        cancelButton.setOnAction(e -> primaryStage.close());
        
        buttonBox.getChildren().addAll(saveButton, cancelButton);
        
        // إضافة جميع العناصر
        formCard.getChildren().addAll(
            formTitle,
            formSubtitle,
            separator1,
            titleBox,
            row1,
            locationBox,
            requirementsBox,
            descriptionBox,
            additionalInfo,
            buttonBox
        );
        
        content.getChildren().add(formCard);
        
        return content;
    }
    
    /**
     * إنشاء حقل إدخال
     */
    private VBox createInputField(String labelText, String promptText) {
        VBox container = new VBox(8);
        
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        label.setTextFill(Color.web("#34495E"));
        
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setPrefHeight(40);
        textField.setFont(Font.font("Arial", 14));
        textField.setStyle(
            "-fx-background-color: #F8F9FA;" +
            "-fx-border-color: #E0E0E0;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 10;"
        );
        
        HBox fieldBox = new HBox(textField);
        HBox.setHgrow(textField, Priority.ALWAYS);
        
        container.getChildren().addAll(label, fieldBox);
        
        return container;
    }
    
    /**
     * إنشاء حقل قائمة منسدلة
     */
    private VBox createComboField(String labelText, String[] items) {
        VBox container = new VBox(8);
        
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        label.setTextFill(Color.web("#34495E"));
        
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        comboBox.setPromptText("اختر...");
        comboBox.setPrefHeight(40);
        comboBox.setMaxWidth(Double.MAX_VALUE);
        comboBox.setStyle("-fx-font-size: 14;");
        
        HBox fieldBox = new HBox(comboBox);
        HBox.setHgrow(comboBox, Priority.ALWAYS);
        
        container.getChildren().addAll(label, fieldBox);
        
        return container;
    }
    
    /**
     * إنشاء حقل نص متعدد الأسطر
     */
    private VBox createTextAreaField(String labelText, String promptText) {
        VBox container = new VBox(8);
        
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        label.setTextFill(Color.web("#34495E"));
        
        TextArea textArea = new TextArea();
        textArea.setPromptText(promptText);
        textArea.setPrefRowCount(5);
        textArea.setWrapText(true);
        textArea.setFont(Font.font("Arial", 14));
        textArea.setStyle(
            "-fx-background-color: #F8F9FA;" +
            "-fx-border-color: #E0E0E0;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 10;"
        );
        
        VBox fieldBox = new VBox(textArea);
        
        container.getChildren().addAll(label, fieldBox);
        
        return container;
    }
    
    /**
     * معالجة حفظ الفرصة
     */
    private void handleSave() {
        // التحقق من الحقول المطلوبة
        if (titleField.getText().isEmpty() || 
            durationField.getText().isEmpty() || 
            requirementsField.getText().isEmpty() || 
            descriptionArea.getText().isEmpty() ||
            categoryCombo.getValue() == null ||
            locationCombo.getValue() == null) {
            
            showAlert(Alert.AlertType.WARNING, "تنبيه", 
                "الرجاء ملء جميع الحقول المطلوبة المميزة بـ *");
            return;
        }
        
        // رسالة نجاح الحفظ
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("نجح الحفظ");
        alert.setHeaderText("تم إضافة فرصة التدريب بنجاح!");
        alert.setContentText(
            "عنوان التدريب: " + titleField.getText() + "\n" +
            "التصنيف: " + categoryCombo.getValue() + "\n" +
            "المدة: " + durationField.getText() + "\n" +
            "الموقع: " + locationCombo.getValue() + "\n\n" +
            "تم نشر الفرصة وستكون متاحة للطلاب قريباً."
        );
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                primaryStage.close();
            }
        });
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
    
    public static void main(String[] args) {
        launch(args);
    }
}