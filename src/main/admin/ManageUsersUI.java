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

public class ManageUsersUI extends Application {
    
    private Stage primaryStage;
    private TableView<User> usersTable;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("إدارة المستخدمين - Manage Users");
        
        Scene scene = createManageUsersScene();
        primaryStage.setScene(scene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(750);
        primaryStage.show();
    }
    
    private Scene createManageUsersScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #ECEFF1;");
        
        root.setTop(createTopBar());
        root.setCenter(createMainContent());
        
        return new Scene(root);
    }
    
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
        
        Label titleLabel = new Label("👥 إدارة المستخدمين");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.web("#263238"));
        titleLabel.setPadding(new Insets(0, 0, 0, 20));
        
        topBar.getChildren().addAll(backButton, titleLabel);
        
        return topBar;
    }
    
    private VBox createMainContent() {
        VBox content = new VBox(25);
        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);
        
        HBox statsBox = new HBox(20);
        statsBox.setAlignment(Pos.CENTER);
        
        statsBox.getChildren().addAll(
            createStatCard("👥 إجمالي", "247", "#2196F3"),
            createStatCard("👤 طلاب", "180", "#4CAF50"),
            createStatCard("🏢 شركات", "45", "#FF9800"),
            createStatCard("✅ نشط", "225", "#9C27B0"),
            createStatCard("⏸️ معلق", "22", "#F44336")
        );
        
        VBox tableCard = new VBox(20);
        tableCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);"
        );
        tableCard.setPadding(new Insets(25));
        tableCard.setMaxWidth(1100);
        
        HBox toolBar = createToolBar();
        usersTable = createUsersTable();
        
        tableCard.getChildren().addAll(toolBar, usersTable);
        content.getChildren().addAll(statsBox, tableCard);
        
        return content;
    }
    
    private VBox createStatCard(String title, String value, String color) {
        VBox card = new VBox(8);
        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(160);
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
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        titleLabel.setTextFill(Color.web("#7F8C8D"));
        
        card.getChildren().addAll(valueLabel, titleLabel);
        
        return card;
    }
    
    private HBox createToolBar() {
        HBox toolBar = new HBox(15);
        toolBar.setAlignment(Pos.CENTER_LEFT);
        toolBar.setPadding(new Insets(0, 0, 15, 0));
        
        TextField searchField = new TextField();
        searchField.setPromptText("🔍 البحث عن مستخدم...");
        searchField.setPrefWidth(300);
        searchField.setPrefHeight(38);
        searchField.setStyle(
            "-fx-background-color: #F5F5F5;" +
            "-fx-border-color: #E0E0E0;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;"
        );
        
        ComboBox<String> typeFilter = new ComboBox<>();
        typeFilter.setPromptText("نوع المستخدم");
        typeFilter.getItems().addAll("الكل", "طالب", "شركة", "مدير");
        typeFilter.setPrefHeight(38);
        
        ComboBox<String> statusFilter = new ComboBox<>();
        statusFilter.setPromptText("الحالة");
        statusFilter.getItems().addAll("الكل", "نشط", "معلق");
        statusFilter.setPrefHeight(38);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Button addUserBtn = new Button("➕ إضافة مستخدم");
        addUserBtn.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        addUserBtn.setPrefHeight(38);
        addUserBtn.setStyle(
            "-fx-background-color: #4CAF50;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        
        Button exportBtn = new Button("📥 تصدير");
        exportBtn.setFont(Font.font("Arial", 13));
        exportBtn.setPrefHeight(38);
        exportBtn.setStyle(
            "-fx-background-color: #2196F3;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        
        toolBar.getChildren().addAll(searchField, typeFilter, statusFilter, spacer, addUserBtn, exportBtn);
        
        return toolBar;
    }
    
    private TableView<User> createUsersTable() {
        TableView<User> table = new TableView<>();
        table.setPrefHeight(450);
        
        TableColumn<User, String> idCol = new TableColumn<>("المعرف\nID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(80);
        idCol.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<User, String> nameCol = new TableColumn<>("الاسم\nName");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(200);
        nameCol.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<User, String> emailCol = new TableColumn<>("البريد\nEmail");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(220);
        emailCol.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<User, String> typeCol = new TableColumn<>("النوع\nType");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeCol.setPrefWidth(120);
        typeCol.setStyle("-fx-alignment: CENTER;");
        
        typeCol.setCellFactory(column -> new TableCell<User, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER);
                    if (item.contains("طالب")) {
                        setStyle("-fx-background-color: #E3F2FD; -fx-text-fill: #1565C0; -fx-font-weight: bold;");
                    } else if (item.contains("شركة")) {
                        setStyle("-fx-background-color: #FFF3E0; -fx-text-fill: #E65100; -fx-font-weight: bold;");
                    } else {
                        setStyle("-fx-background-color: #F3E5F5; -fx-text-fill: #6A1B9A; -fx-font-weight: bold;");
                    }
                }
            }
        });
        
        TableColumn<User, String> dateCol = new TableColumn<>("تاريخ التسجيل\nDate");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        dateCol.setPrefWidth(130);
        dateCol.setStyle("-fx-alignment: CENTER;");
        
        TableColumn<User, String> statusCol = new TableColumn<>("الحالة\nStatus");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(100);
        statusCol.setStyle("-fx-alignment: CENTER;");
        
        statusCol.setCellFactory(column -> new TableCell<User, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER);
                    if (item.contains("نشط")) {
                        setStyle("-fx-background-color: #C8E6C9; -fx-text-fill: #2E7D32; -fx-font-weight: bold;");
                    } else {
                        setStyle("-fx-background-color: #FFCDD2; -fx-text-fill: #C62828; -fx-font-weight: bold;");
                    }
                }
            }
        });
        
        TableColumn<User, Void> actionsCol = new TableColumn<>("الإجراءات\nActions");
        actionsCol.setPrefWidth(200);
        actionsCol.setStyle("-fx-alignment: CENTER;");
        
        actionsCol.setCellFactory(column -> new TableCell<User, Void>() {
            private final Button activateBtn = new Button("✓");
            private final Button suspendBtn = new Button("⏸");
            private final Button deleteBtn = new Button("🗑");
            private final HBox buttons = new HBox(5, activateBtn, suspendBtn, deleteBtn);
            
            {
                buttons.setAlignment(Pos.CENTER);
                
                activateBtn.setStyle(
                    "-fx-background-color: #4CAF50;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
                );
                activateBtn.setPrefWidth(50);
                activateBtn.setPrefHeight(28);
                
                suspendBtn.setStyle(
                    "-fx-background-color: #FF9800;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
                );
                suspendBtn.setPrefWidth(50);
                suspendBtn.setPrefHeight(28);
                
                deleteBtn.setStyle(
                    "-fx-background-color: #F44336;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 5;" +
                    "-fx-cursor: hand;"
                );
                deleteBtn.setPrefWidth(50);
                deleteBtn.setPrefHeight(28);
                
                activateBtn.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    handleActivate(user);
                });
                
                suspendBtn.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    handleSuspend(user);
                });
                
                deleteBtn.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    handleDelete(user);
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : buttons);
            }
        });
        
        table.getColumns().addAll(idCol, nameCol, emailCol, typeCol, dateCol, statusCol, actionsCol);
        table.setItems(getMockData());
        
        return table;
    }
    
    private ObservableList<User> getMockData() {
        ObservableList<User> data = FXCollections.observableArrayList();
        
        data.add(new User("001", "أحمد محمد", "ahmad@example.com", "طالب", "2025-01-15", "نشط"));
        data.add(new User("002", "فاطمة أحمد", "fatima@example.com", "طالب", "2025-02-20", "نشط"));
        data.add(new User("003", "شركة التقنية", "tech@company.com", "شركة", "2025-01-10", "نشط"));
        data.add(new User("004", "محمود حسن", "mahmoud@example.com", "طالب", "2025-03-05", "نشط"));
        data.add(new User("005", "مجموعة الابتكار", "innovation@company.com", "شركة", "2025-02-15", "نشط"));
        data.add(new User("006", "سارة خالد", "sara@example.com", "طالب", "2025-04-12", "معلق"));
        
        return data;
    }
    
    private void handleActivate(User user) {
        user.setStatus("نشط");
        usersTable.refresh();
        showInfo("تم تفعيل المستخدم بنجاح!");
    }
    
    private void handleSuspend(User user) {
        user.setStatus("معلق");
        usersTable.refresh();
        showInfo("تم تعليق المستخدم بنجاح!");
    }
    
    private void handleDelete(User user) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("تأكيد الحذف");
        alert.setContentText("هل تريد حذف: " + user.getName() + "؟");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                usersTable.getItems().remove(user);
                showInfo("تم الحذف بنجاح!");
            }
        });
    }
    
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("معلومات");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static class User {
        private final SimpleStringProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty email;
        private final SimpleStringProperty type;
        private final SimpleStringProperty joinDate;
        private final SimpleStringProperty status;
        
        public User(String id, String name, String email, String type, String joinDate, String status) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
            this.type = new SimpleStringProperty(type);
            this.joinDate = new SimpleStringProperty(joinDate);
            this.status = new SimpleStringProperty(status);
        }
        
        public String getId() { return id.get(); }
        public String getName() { return name.get(); }
        public String getEmail() { return email.get(); }
        public String getType() { return type.get(); }
        public String getJoinDate() { return joinDate.get(); }
        public String getStatus() { return status.get(); }
        public void setStatus(String value) { status.set(value); }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}