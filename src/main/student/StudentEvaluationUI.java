package main.student;

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
 * صفحة عرض تقييمات الطالب
 * Student Evaluations View Page
 */
public class StudentEvaluationUI extends Application {
    
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("تقييماتي - My Evaluations");
        
        Scene scene = createEvaluationScene();
        primaryStage.setScene(scene);
        primaryStage.setWidth(900);
        primaryStage.setHeight(700);
        primaryStage.show();
    }
    
    /**
     * إنشاء مشهد التقييمات
     */
    private Scene createEvaluationScene() {
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
        
        Label titleLabel = new Label("⭐ تقييماتي");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
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
        
        // بطاقة الملخص
        VBox summaryCard = createSummaryCard();
        
        // عنوان التقييمات
        Label evaluationsTitle = new Label("📋 تقييمات التدريبات السابقة");
        evaluationsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        evaluationsTitle.setTextFill(Color.web("#2C3E50"));
        
        // بطاقات التقييمات
        VBox evaluationsContainer = new VBox(15);
        evaluationsContainer.setAlignment(Pos.TOP_CENTER);
        evaluationsContainer.setMaxWidth(800);
        
        evaluationsContainer.getChildren().addAll(
            createEvaluationCard(
                "شركة التقنية المتقدمة",
                "تدريب في تطوير تطبيقات الجوال",
                "4.8",
                "سبتمبر 2025",
                "أداء ممتاز، التزام كامل بالمواعيد، ومهارات تقنية قوية. أظهر أحمد قدرة عالية على التعلم السريع وحل المشكلات. نوصي به بشدة للفرص المستقبلية.",
                "✓ موصى به للفرص المستقبلية",
                "#4CAF50"
            ),
            createEvaluationCard(
                "مجموعة الابتكار الرقمي",
                "تدريب في UI/UX Design",
                "4.5",
                "يوليو 2025",
                "طالب مجتهد ومبدع، أظهر مهارات جيدة في التصميم والعمل الجماعي. قدم تصاميم مبتكرة لعدة مشاريع. يحتاج لمزيد من التدريب على بعض الأدوات المتقدمة.",
                "✓ موصى به للفرص المستقبلية",
                "#4CAF50"
            ),
            createEvaluationCard(
                "شركة البيانات الذكية",
                "تدريب في علم البيانات",
                "4.2",
                "مايو 2025",
                "أداء جيد بشكل عام. أظهر فهماً جيداً للمفاهيم الأساسية في علم البيانات. يحتاج لمزيد من الممارسة في التحليل الإحصائي المتقدم.",
                "يمكن التوصية به",
                "#FF9800"
            )
        );
        
        content.getChildren().addAll(summaryCard, evaluationsTitle, evaluationsContainer);
        
        return content;
    }
    
    /**
     * إنشاء بطاقة الملخص
     */
    private VBox createSummaryCard() {
        VBox card = new VBox(20);
        card.setStyle(
            "-fx-background-color: linear-gradient(to right, #667eea 0%, #764ba2 100%);" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 12, 0, 0, 4);"
        );
        card.setPadding(new Insets(30));
        card.setMaxWidth(800);
        card.setAlignment(Pos.CENTER);
        
        Label summaryTitle = new Label("ملخص التقييمات");
        summaryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        summaryTitle.setTextFill(Color.WHITE);
        
        HBox statsBox = new HBox(40);
        statsBox.setAlignment(Pos.CENTER);
        
        VBox totalBox = createSummaryStatBox("⭐", "المعدل الكلي", "4.5 / 5.0");
        VBox countBox = createSummaryStatBox("📊", "عدد التقييمات", "3");
        VBox recommendBox = createSummaryStatBox("✅", "نسبة التوصية", "100%");
        
        statsBox.getChildren().addAll(totalBox, countBox, recommendBox);
        
        card.getChildren().addAll(summaryTitle, statsBox);
        
        return card;
    }
    
    /**
     * إنشاء صندوق إحصائية ملخص
     */
    private VBox createSummaryStatBox(String icon, String label, String value) {
        VBox box = new VBox(8);
        box.setAlignment(Pos.CENTER);
        
        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(32));
        
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        valueLabel.setTextFill(Color.WHITE);
        
        Label labelText = new Label(label);
        labelText.setFont(Font.font("Arial", 13));
        labelText.setTextFill(Color.web("#E3F2FD"));
        
        box.getChildren().addAll(iconLabel, valueLabel, labelText);
        
        return box;
    }
    
    /**
     * إنشاء بطاقة تقييم
     */
    private VBox createEvaluationCard(String company, String position, String rating, 
                                     String date, String feedback, String recommendation, 
                                     String color) {
        VBox card = new VBox(15);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 3);"
        );
        card.setPadding(new Insets(25));
        
        // رأس البطاقة
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        
        VBox companyBox = new VBox(5);
        Label companyLabel = new Label(company);
        companyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        companyLabel.setTextFill(Color.web("#2C3E50"));
        
        Label positionLabel = new Label(position);
        positionLabel.setFont(Font.font("Arial", 13));
        positionLabel.setTextFill(Color.web("#7F8C8D"));
        
        companyBox.getChildren().addAll(companyLabel, positionLabel);
        HBox.setHgrow(companyBox, Priority.ALWAYS);
        
        VBox ratingBox = new VBox(3);
        ratingBox.setAlignment(Pos.CENTER_RIGHT);
        
        Label ratingLabel = new Label(rating + " ⭐");
        ratingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        ratingLabel.setTextFill(Color.web("#FF9800"));
        
        Label dateLabel = new Label(date);
        dateLabel.setFont(Font.font("Arial", 11));
        dateLabel.setTextFill(Color.web("#95A5A6"));
        
        ratingBox.getChildren().addAll(ratingLabel, dateLabel);
        
        header.getChildren().addAll(companyBox, ratingBox);
        
        Separator separator = new Separator();
        
        // التقييم النصي
        VBox feedbackBox = new VBox(8);
        
        Label feedbackTitle = new Label("📝 التقييم:");
        feedbackTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        feedbackTitle.setTextFill(Color.web("#34495E"));
        
        Label feedbackText = new Label(feedback);
        feedbackText.setFont(Font.font("Arial", 13));
        feedbackText.setTextFill(Color.web("#2C3E50"));
        feedbackText.setWrapText(true);
        feedbackText.setStyle("-fx-line-spacing: 3;");
        
        feedbackBox.getChildren().addAll(feedbackTitle, feedbackText);
        
        // التوصية
        HBox recommendBox = new HBox(10);
        recommendBox.setAlignment(Pos.CENTER_LEFT);
        recommendBox.setStyle(
            "-fx-background-color: " + color + "20;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 12;"
        );
        
        Label recommendLabel = new Label(recommendation);
        recommendLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        recommendLabel.setTextFill(Color.web(color));
        
        recommendBox.getChildren().add(recommendLabel);
        
        card.getChildren().addAll(header, separator, feedbackBox, recommendBox);
        
        return card;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}