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
 * واجهة تسجيل دخول الشركة
 * Employer Login Interface
 */
public class EmployerLoginUI extends Application {
    
    private Stage primaryStage;
    private TextField usernameField;
    private PasswordField passwordField;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("تسجيل دخول الشركة - Employer Login");
        
        Scene scene = createLoginScene();
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(600);
        primaryStage.show();
    }
    
    /**
     * إنشاء مشهد تسجيل الدخول
     */
    private Scene createLoginScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F5F7FA;");
        
        // بطاقة تسجيل الدخول
        VBox loginCard = new VBox(20);
        loginCard.setAlignment(Pos.CENTER);
        loginCard.setPadding(new Insets(40));
        loginCard.setMaxWidth(400);
        loginCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 15, 0, 0, 5);"
        );
        
        // أيقونة الشركة
        Label iconLabel = new Label("🏢");
        iconLabel.setFont(Font.font(60));
        
        // عنوان
        Label titleLabel = new Label("تسجيل دخول الشركة");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web("#1565C0"));
        
        Label subtitleLabel = new Label("Employer Login");
        subtitleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        subtitleLabel.setTextFill(Color.web("#7F8C8D"));
        
        // حقل اسم المستخدم
        VBox usernameBox = createInputField("اسم المستخدم / Username", "company@example.com");
        usernameField = (TextField) ((VBox) usernameBox.getChildren().get(1)).getChildren().get(0);
        
        // حقل كلمة المرور
        VBox passwordBox = createPasswordField("كلمة المرور / Password");
        passwordField = (PasswordField) ((VBox) passwordBox.getChildren().get(1)).getChildren().get(0);
        
        // خيار "تذكرني"
        CheckBox rememberMe = new CheckBox("تذكرني / Remember me");
        rememberMe.setFont(Font.font("Arial", 12));
        
        // زر تسجيل الدخول
        Button loginButton = new Button("تسجيل الدخول");
        loginButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        loginButton.setPrefWidth(320);
        loginButton.setPrefHeight(45);
        loginButton.setStyle(
            "-fx-background-color: #2196F3;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        loginButton.setOnAction(e -> handleLogin());
        
        // رابط نسيت كلمة المرور
        Hyperlink forgotPassword = new Hyperlink("نسيت كلمة المرور؟");
        forgotPassword.setFont(Font.font("Arial", 12));
        
        // رابط إنشاء حساب جديد
        HBox signupBox = new HBox(5);
        signupBox.setAlignment(Pos.CENTER);
        Label signupLabel = new Label("ليس لديك حساب؟");
        signupLabel.setFont(Font.font("Arial", 12));
        Hyperlink signupLink = new Hyperlink("تسجيل شركة");
        signupLink.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        signupBox.getChildren().addAll(signupLabel, signupLink);
        
        // زر العودة
        Button backButton = new Button("← العودة");
        backButton.setFont(Font.font("Arial", 12));
        backButton.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #7F8C8D;" +
            "-fx-cursor: hand;"
        );
        backButton.setOnAction(e -> primaryStage.close());
        
        // إضافة جميع العناصر
        loginCard.getChildren().addAll(
            iconLabel,
            titleLabel,
            subtitleLabel,
            usernameBox,
            passwordBox,
            rememberMe,
            loginButton,
            forgotPassword,
            signupBox
        );
        
        VBox centerContainer = new VBox(loginCard);
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setPadding(new Insets(40));
        
        HBox topBar = new HBox(backButton);
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: white;");
        
        root.setCenter(centerContainer);
        root.setTop(topBar);
        
        return new Scene(root);
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
            "-fx-border-radius: 5;" +
            "-fx-background-radius: 5;" +
            "-fx-padding: 10;"
        );
        
        VBox fieldBox = new VBox(textField);
        container.getChildren().addAll(label, fieldBox);
        
        return container;
    }
    
    /**
     * إنشاء حقل كلمة المرور
     */
    private VBox createPasswordField(String labelText) {
        VBox container = new VBox(8);
        
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        label.setTextFill(Color.web("#34495E"));
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("••••••••");
        passwordField.setPrefHeight(40);
        passwordField.setFont(Font.font("Arial", 14));
        passwordField.setStyle(
            "-fx-background-color: #F8F9FA;" +
            "-fx-border-color: #E0E0E0;" +
            "-fx-border-radius: 5;" +
            "-fx-background-radius: 5;" +
            "-fx-padding: 10;"
        );
        
        VBox fieldBox = new VBox(passwordField);
        container.getChildren().addAll(label, fieldBox);
        
        return container;
    }
    
    /**
     * معالجة تسجيل الدخول
     */
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "تنبيه", "الرجاء ملء جميع الحقول");
            return;
        }
        
        // بيانات تجريبية للتحقق
        if (username.equals("employer") && password.equals("123")) {
            showAlert(Alert.AlertType.INFORMATION, "نجح", "تم تسجيل الدخول بنجاح!");
            openEmployerDashboard();
        } else {
            showAlert(Alert.AlertType.ERROR, "خطأ", "اسم المستخدم أو كلمة المرور غير صحيحة");
        }
    }
    
    /**
     * فتح لوحة تحكم الشركة
     */
    private void openEmployerDashboard() {
        EmployerDashboardUI dashboard = new EmployerDashboardUI();
        Stage stage = new Stage();
        dashboard.start(stage);
        primaryStage.close();
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