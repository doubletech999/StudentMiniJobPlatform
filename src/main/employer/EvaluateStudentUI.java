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
 * صفحة تقييم الطلاب بعد انتهاء التدريب
 * Student Evaluation Page
 */
public class EvaluateStudentUI extends Application {
    
    private Stage primaryStage;
    private ComboBox<String> studentCombo;
    private Slider ratingSlider;
    private Label ratingLabel;
    private TextArea evaluationArea;
    private CheckBox recommendCheckbox;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("تقييم المتدربين - Evaluate Students");
        
        Scene scene = createEvaluateScene();
        primaryStage.setScene(scene);
        primaryStage.setWidth(750);
        primaryStage.setHeight(750);
        primaryStage.show();
    }
    
    /**
     * إنشاء مشهد صفحة التقييم
     */
    private Scene createEvaluateScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F5F7FA;");
        
        root.setTop(createTopBar());
        
        ScrollPane scrollPane = new ScrollPane(createMainContent());
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
        
        Label titleLabel = new Label("⭐ تقييم أداء المتدربين");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web("#2C3E50"));
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
        
        // بطاقة التقييم
        VBox evaluationCard = new VBox(20);
        evaluationCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 3);"
        );
        evaluationCard.setPadding(new Insets(30));
        evaluationCard.setMaxWidth(650);
        
        // عنوان النموذج
        Label formTitle = new Label("نموذج تقييم المتدرب");
        formTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        formTitle.setTextFill(Color.web("#FF9800"));
        
        Label formSubtitle = new Label("ساعد الطلاب على تطوير مهاراتهم من خلال تقييمك");
        formSubtitle.setFont(Font.font("Arial", 13));
        formSubtitle.setTextFill(Color.web("#7F8C8D"));
        
        Separator separator1 = new Separator();
        separator1.setPadding(new Insets(5, 0, 10, 0));
        
        // اختيار الطالب
        VBox studentBox = new VBox(10);
        Label studentLabel = new Label("اختر المتدرب *");
        studentLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        studentLabel.setTextFill(Color.web("#34495E"));
        
        studentCombo = new ComboBox<>();
        studentCombo.setPromptText("اختر الطالب من القائمة...");
        studentCombo.getItems().addAll(
            "أحمد محمد علي - تطوير تطبيقات الجوال",
            "محمود حسن - علم البيانات",
            "عمر سعيد - علم البيانات",
            "مريم عبدالله - تصميم واجهات المستخدم"
        );
        studentCombo.setPrefHeight(40);
        studentCombo.setMaxWidth(Double.MAX_VALUE);
        studentCombo.setStyle("-fx-font-size: 14;");
        
        studentBox.getChildren().addAll(studentLabel, studentCombo);
        
        // التقييم بالنجوم
        VBox ratingBox = new VBox(10);
        Label ratingTitle = new Label("التقييم العام *");
        ratingTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ratingTitle.setTextFill(Color.web("#34495E"));
        
        HBox sliderBox = new HBox(15);
        sliderBox.setAlignment(Pos.CENTER_LEFT);
        
        ratingSlider = new Slider(0, 5, 4);
        ratingSlider.setShowTickLabels(true);
        ratingSlider.setShowTickMarks(true);
        ratingSlider.setMajorTickUnit(1);
        ratingSlider.setMinorTickCount(0);
        ratingSlider.setSnapToTicks(true);
        ratingSlider.setPrefWidth(400);
        
        ratingLabel = new Label("4.0 ⭐");
        ratingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        ratingLabel.setTextFill(Color.web("#FF9800"));
        
        ratingSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double value = newVal.doubleValue();
            ratingLabel.setText(String.format("%.1f ⭐", value));
        });
        
        sliderBox.getChildren().addAll(ratingSlider, ratingLabel);
        ratingBox.getChildren().addAll(ratingTitle, sliderBox);
        
        // معايير التقييم
        VBox criteriaBox = new VBox(15);
        criteriaBox.setStyle(
            "-fx-background-color: #FFF9C4;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 20;"
        );
        
        Label criteriaTitle = new Label("📊 معايير التقييم:");
        criteriaTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        VBox criteriaList = new VBox(8);
        criteriaList.getChildren().addAll(
            createCriteriaRow("الالتزام والانضباط"),
            createCriteriaRow("جودة العمل المنجز"),
            createCriteriaRow("القدرة على التعلم والتطور"),
            createCriteriaRow("مهارات التواصل والعمل الجماعي"),
            createCriteriaRow("المبادرة وحل المشكلات")
        );
        
        criteriaBox.getChildren().addAll(criteriaTitle, criteriaList);
        
        // نقاط القوة
        VBox strengthsBox = createTextSection(
            "💪 نقاط القوة",
            "اذكر نقاط القوة التي لاحظتها في أداء المتدرب..."
        );
        
        // نقاط التحسين
        VBox improvementsBox = createTextSection(
            "📈 نقاط التحسين",
            "اذكر المجالات التي يمكن للمتدرب تحسينها..."
        );
        
        // التقييم التفصيلي
        VBox detailedEvalBox = new VBox(10);
        Label evalLabel = new Label("📝 التقييم التفصيلي *");
        evalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        evalLabel.setTextFill(Color.web("#34495E"));
        
        evaluationArea = new TextArea();
        evaluationArea.setPromptText(
            "اكتب تقييماً شاملاً عن أداء المتدرب خلال فترة التدريب...\n\n" +
            "يمكنك ذكر:\n" +
            "- المهام التي أنجزها\n" +
            "- مستوى الأداء\n" +
            "- التطور الملحوظ\n" +
            "- أي ملاحظات أخرى"
        );
        evaluationArea.setPrefRowCount(6);
        evaluationArea.setWrapText(true);
        evaluationArea.setFont(Font.font("Arial", 14));
        evaluationArea.setStyle(
            "-fx-background-color: #F8F9FA;" +
            "-fx-border-color: #E0E0E0;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 10;"
        );
        
        detailedEvalBox.getChildren().addAll(evalLabel, evaluationArea);
        
        // خيارات إضافية
        VBox optionsBox = new VBox(10);
        optionsBox.setStyle(
            "-fx-background-color: #E8F5E9;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 15;"
        );
        
        recommendCheckbox = new CheckBox("أوصي بهذا المتدرب للفرص المستقبلية");
        recommendCheckbox.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        recommendCheckbox.setSelected(true);
        
        CheckBox allowShareCheckbox = new CheckBox("السماح بمشاركة هذا التقييم مع الجامعة");
        allowShareCheckbox.setFont(Font.font("Arial", 12));
        
        optionsBox.getChildren().addAll(recommendCheckbox, allowShareCheckbox);
        
        // الأزرار
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        
        Button submitButton = new Button("✓ إرسال التقييم");
        submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        submitButton.setPrefWidth(200);
        submitButton.setPrefHeight(45);
        submitButton.setStyle(
            "-fx-background-color: #4CAF50;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10;" +
            "-fx-cursor: hand;"
        );
        submitButton.setOnAction(e -> handleSubmit());
        
        Button cancelButton = new Button("✕ إلغاء");
        cancelButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        cancelButton.setPrefWidth(200);
        cancelButton.setPrefHeight(45);
        cancelButton.setStyle(
            "-fx-background-color: #9E9E9E;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10;" +
            "-fx-cursor: hand;"
        );
        cancelButton.setOnAction(e -> primaryStage.close());
        
        buttonBox.getChildren().addAll(submitButton, cancelButton);
        
        // إضافة جميع العناصر
        evaluationCard.getChildren().addAll(
            formTitle,
            formSubtitle,
            separator1,
            studentBox,
            ratingBox,
            criteriaBox,
            strengthsBox,
            improvementsBox,
            detailedEvalBox,
            optionsBox,
            buttonBox
        );
        
        content.getChildren().add(evaluationCard);
        
        return content;
    }
    
    /**
     * إنشاء صف معيار تقييم
     */
    private HBox createCriteriaRow(String criteriaText) {
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);
        
        Label bullet = new Label("•");
        bullet.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        bullet.setTextFill(Color.web("#FF9800"));
        
        Label text = new Label(criteriaText);
        text.setFont(Font.font("Arial", 12));
        text.setTextFill(Color.web("#5D4037"));
        
        row.getChildren().addAll(bullet, text);
        
        return row;
    }
    
    /**
     * إنشاء قسم نصي
     */
    private VBox createTextSection(String title, String promptText) {
        VBox section = new VBox(10);
        
        Label label = new Label(title);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        label.setTextFill(Color.web("#34495E"));
        
        TextArea textArea = new TextArea();
        textArea.setPromptText(promptText);
        textArea.setPrefRowCount(3);
        textArea.setWrapText(true);
        textArea.setFont(Font.font("Arial", 13));
        textArea.setStyle(
            "-fx-background-color: #F8F9FA;" +
            "-fx-border-color: #E0E0E0;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 10;"
        );
        
        section.getChildren().addAll(label, textArea);
        
        return section;
    }
    
    /**
     * معالجة إرسال التقييم
     */
    private void handleSubmit() {
        // التحقق من الحقول المطلوبة
        if (studentCombo.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "تنبيه", "الرجاء اختيار الطالب");
            return;
        }
        
        if (evaluationArea.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "تنبيه", "الرجاء كتابة التقييم التفصيلي");
            return;
        }
        
        // رسالة تأكيد
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("تأكيد إرسال التقييم");
        confirmAlert.setHeaderText("هل أنت متأكد من إرسال هذا التقييم؟");
        confirmAlert.setContentText(
            "الطالب: " + studentCombo.getValue() + "\n" +
            "التقييم: " + String.format("%.1f", ratingSlider.getValue()) + " من 5"
        );
        
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // رسالة نجاح
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("تم إرسال التقييم");
                successAlert.setHeaderText("✓ تم إرسال التقييم بنجاح!");
                successAlert.setContentText(
                    "تم حفظ تقييمك للطالب: " + studentCombo.getValue() + "\n\n" +
                    "التقييم العام: " + String.format("%.1f", ratingSlider.getValue()) + " ⭐\n" +
                    "التوصية: " + (recommendCheckbox.isSelected() ? "نعم ✓" : "لا") + "\n\n" +
                    "سيتمكن الطالب من مشاهدة تقييمك."
                );
                
                successAlert.showAndWait().ifPresent(r -> {
                    if (r == ButtonType.OK) {
                        primaryStage.close();
                    }
                });
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