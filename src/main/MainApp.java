package main;

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
import main.student.StudentLoginUI;
import main.employer.EmployerLoginUI;
import main.admin.AdminDashboardUI;

/**
 * التطبيق الرئيسي - واجهة اختيار نوع المستخدم
 * Main Application - User Type Selection Interface
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Student Mini Job Platform");
        
        // إنشاء الواجهة الرئيسية
        Scene scene = createMainScene();
        primaryStage.setScene(scene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);
        primaryStage.show();
    }
    
    /**
     * إنشاء المشهد الرئيسي لاختيار نوع المستخدم
     */
    private Scene createMainScene() {
        // Container رئيسي
        VBox mainContainer = new VBox(30);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(50));
        mainContainer.setStyle("-fx-background-color: linear-gradient(to bottom, #667eea 0%, #764ba2 100%);");
        
        // عنوان التطبيق
        Label titleLabel = new Label("🎓 Student Mini Job Platform");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        titleLabel.setTextFill(Color.WHITE);
        
        // وصف التطبيق
        Label subtitleLabel = new Label("منصة تدريب مصغّرة للطلاب");
        subtitleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        subtitleLabel.setTextFill(Color.web("#E0E7FF"));
        
        // Separator
        Separator separator = new Separator();
        separator.setMaxWidth(300);
        
        // بطاقة الأزرار
        VBox buttonCard = new VBox(15);
        buttonCard.setAlignment(Pos.CENTER);
        buttonCard.setPadding(new Insets(40, 50, 40, 50));
        buttonCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 20, 0, 0, 5);"
        );
        buttonCard.setMaxWidth(400);
        
        // زر تسجيل دخول الطالب
        Button studentBtn = createStyledButton("👤 تسجيل دخول طالب", "#4CAF50");
        studentBtn.setOnAction(e -> openStudentLogin());
        
        // زر تسجيل دخول الشركة
        Button employerBtn = createStyledButton("🏢 تسجيل دخول شركة", "#2196F3");
        employerBtn.setOnAction(e -> openEmployerLogin());
        
        // زر تسجيل دخول المدير
        Button adminBtn = createStyledButton("🔧 تسجيل دخول مدير", "#FF5722");
        adminBtn.setOnAction(e -> openAdminDashboard());
        
        buttonCard.getChildren().addAll(studentBtn, employerBtn, adminBtn);
        
        // إضافة جميع العناصر
        mainContainer.getChildren().addAll(
            titleLabel, 
            subtitleLabel, 
            separator, 
            buttonCard
        );
        
        return new Scene(mainContainer);
    }
    
    /**
     * إنشاء زر بتنسيق موحد
     */
    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setPrefWidth(300);
        button.setPrefHeight(50);
        button.setStyle(
            "-fx-background-color: " + color + ";" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10;" +
            "-fx-cursor: hand;"
        );
        
        // تأثير عند التمرير
        button.setOnMouseEntered(e -> 
            button.setStyle(
                "-fx-background-color: derive(" + color + ", -10%);" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;" +
                "-fx-scale-x: 1.05;" +
                "-fx-scale-y: 1.05;"
            )
        );
        
        button.setOnMouseExited(e -> 
            button.setStyle(
                "-fx-background-color: " + color + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;"
            )
        );
        
        return button;
    }
    
    /**
     * فتح واجهة تسجيل دخول الطالب
     */
    private void openStudentLogin() {
        StudentLoginUI studentLogin = new StudentLoginUI();
        Stage stage = new Stage();
        studentLogin.start(stage);
        primaryStage.close();
    }
    
    /**
     * فتح واجهة تسجيل دخول الشركة
     */
    private void openEmployerLogin() {
        EmployerLoginUI employerLogin = new EmployerLoginUI();
        Stage stage = new Stage();
        employerLogin.start(stage);
        primaryStage.close();
    }
    
    /**
     * فتح واجهة لوحة تحكم المدير
     */
    private void openAdminDashboard() {
        AdminDashboardUI adminDashboard = new AdminDashboardUI();
        Stage stage = new Stage();
        adminDashboard.start(stage);
        primaryStage.close();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}