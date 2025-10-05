package main.admin;

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
 * صفحة التقارير والإحصائيات التفصيلية
 * Detailed Reports and Statistics Page
 */
public class ReportsUI extends Application {
    
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("التقارير والإحصائيات - Reports & Statistics");
        
        Scene scene = createReportsScene();
        primaryStage.setScene(scene);
        primaryStage.setWidth(1300);
        primaryStage.setHeight(850);
        primaryStage.show();
    }
    
    /**
     * إنشاء مشهد التقارير
     */
    private Scene createReportsScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #ECEFF1;");
        
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
        
        Label titleLabel = new Label("📈 التقارير والإحصائيات");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.web("#263238"));
        titleLabel.setPadding(new Insets(0, 0, 0, 20));
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        ComboBox<String> periodFilter = new ComboBox<>();
        periodFilter.getItems().addAll("هذا الأسبوع", "هذا الشهر", "هذا العام", "كل الوقت");
        periodFilter.setValue("هذا الشهر");
        periodFilter.setPrefHeight(35);
        
        Button exportBtn = new Button("📥 تصدير PDF");
        exportBtn.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        exportBtn.setPrefHeight(35);
        exportBtn.setStyle(
            "-fx-background-color: #E53935;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        
        Button printBtn = new Button("🖨️ طباعة");
        printBtn.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        printBtn.setPrefHeight(35);
        printBtn.setStyle(
            "-fx-background-color: #5E35B1;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        
        topBar.getChildren().addAll(backButton, titleLabel, spacer, periodFilter, exportBtn, printBtn);
        
        return topBar;
    }
    
    /**
     * إنشاء المحتوى الرئيسي
     */
    private VBox createMainContent() {
        VBox content = new VBox(30);
        content.setPadding(new Insets(30));
        
        // عنوان التقرير
        VBox headerBox = new VBox(5);
        headerBox.setAlignment(Pos.CENTER);
        
        Label titleLabel = new Label("تقرير شامل عن المنصة");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web("#263238"));
        
        Label dateLabel = new Label("الفترة: أكتوبر 2025");
        dateLabel.setFont(Font.font("Arial", 14));
        dateLabel.setTextFill(Color.web("#7F8C8D"));
        
        headerBox.getChildren().addAll(titleLabel, dateLabel);
        
        // الإحصائيات الرئيسية
        HBox mainStats = new HBox(20);
        mainStats.setAlignment(Pos.CENTER);
        
        mainStats.getChildren().addAll(
            createBigStatCard("👥", "المستخدمين الكلي", "247", "+15 هذا الشهر", "#2196F3"),
            createBigStatCard("🏢", "الشركات النشطة", "45", "+3 جديدة", "#4CAF50"),
            createBigStatCard("📝", "فرص التدريب", "89", "+12 منشورة", "#FF9800"),
            createBigStatCard("✅", "تدريبات مكتملة", "156", "+28 أُنجزت", "#9C27B0")
        );
        
        // صف الرسوم البيانية الأول
        HBox chartsRow1 = new HBox(20);
        chartsRow1.setAlignment(Pos.CENTER);
        
        VBox lineChartCard = createChartCard("📊 نمو المستخدمين", createLineChart(), 550);
        VBox pieChartCard = createChartCard("🥧 توزيع الفرص حسب الفئة", createPieChart(), 550);
        
        chartsRow1.getChildren().addAll(lineChartCard, pieChartCard);
        
        // صف الرسوم البيانية الثاني
        HBox chartsRow2 = new HBox(20);
        chartsRow2.setAlignment(Pos.CENTER);
        
        VBox barChartCard = createChartCard("📈 الطلبات الشهرية", createBarChart(), 550);
        VBox areaChartCard = createChartCard("📉 معدل النجاح", createAreaChart(), 550);
        
        chartsRow2.getChildren().addAll(barChartCard, areaChartCard);
        
        // جدول الإحصائيات التفصيلية
        VBox detailedStats = createDetailedStatsTable();
        
        // الملخص النصي
        VBox summaryBox = createSummaryBox();
        
        content.getChildren().addAll(
            headerBox,
            mainStats,
            chartsRow1,
            chartsRow2,
            detailedStats,
            summaryBox
        );
        
        return content;
    }
    
    /**
     * إنشاء بطاقة إحصائية كبيرة
     */
    private VBox createBigStatCard(String icon, String title, String value, String change, String color) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(250);
        card.setPrefHeight(150);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);" +
            "-fx-border-color: " + color + ";" +
            "-fx-border-width: 0 0 0 5;" +
            "-fx-border-radius: 12;"
        );
        card.setPadding(new Insets(20));
        
        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(40));
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        titleLabel.setTextFill(Color.web("#7F8C8D"));
        
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        valueLabel.setTextFill(Color.web(color));
        
        Label changeLabel = new Label(change);
        changeLabel.setFont(Font.font("Arial", 11));
        changeLabel.setTextFill(Color.web("#27AE60"));
        
        card.getChildren().addAll(iconLabel, titleLabel, valueLabel, changeLabel);
        
        return card;
    }
    
    /**
     * إنشاء بطاقة رسم بياني
     */
    private VBox createChartCard(String title, Chart chart, double width) {
        VBox card = new VBox(15);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);"
        );
        card.setPadding(new Insets(20));
        card.setPrefWidth(width);
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.web("#263238"));
        
        card.getChildren().addAll(titleLabel, chart);
        
        return card;
    }
    
    /**
     * إنشاء رسم بياني خطي
     */
    private LineChart<String, Number> createLineChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("الشهر");
        yAxis.setLabel("عدد المستخدمين");
        
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("");
        lineChart.setPrefHeight(280);
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("معدل النجاح");
        series.getData().add(new XYChart.Data<>("يناير", 78));
        series.getData().add(new XYChart.Data<>("فبراير", 82));
        series.getData().add(new XYChart.Data<>("مارس", 85));
        series.getData().add(new XYChart.Data<>("أبريل", 88));
        series.getData().add(new XYChart.Data<>("مايو", 90));
        series.getData().add(new XYChart.Data<>("يونيو", 92));
        series.getData().add(new XYChart.Data<>("يوليو", 91));
        series.getData().add(new XYChart.Data<>("أغسطس", 93));
        series.getData().add(new XYChart.Data<>("سبتمبر", 94));
        series.getData().add(new XYChart.Data<>("أكتوبر", 95));
        
        areaChart.getData().add(series);
        
        return areaChart;
    }
    
    /**
     * إنشاء جدول الإحصائيات التفصيلية
     */
    private VBox createDetailedStatsTable() {
        VBox card = new VBox(20);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);"
        );
        card.setPadding(new Insets(25));
        card.setMaxWidth(1150);
        
        Label titleLabel = new Label("📋 إحصائيات تفصيلية");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web("#263238"));
        
        GridPane grid = new GridPane();
        grid.setHgap(40);
        grid.setVgap(20);
        grid.setPadding(new Insets(20, 0, 0, 0));
        
        // الصف الأول
        grid.add(createStatRow("إجمالي الطلاب المسجلين:", "180 طالب"), 0, 0);
        grid.add(createStatRow("إجمالي الشركات:", "45 شركة"), 1, 0);
        
        // الصف الثاني
        grid.add(createStatRow("فرص التدريب المنشورة:", "89 فرصة"), 0, 1);
        grid.add(createStatRow("التدريبات النشطة حالياً:", "23 تدريب"), 1, 1);
        
        // الصف الثالث
        grid.add(createStatRow("التدريبات المكتملة:", "156 تدريب"), 0, 2);
        grid.add(createStatRow("معدل إكمال التدريب:", "95%"), 1, 2);
        
        // الصف الرابع
        grid.add(createStatRow("متوسط التقييم:", "4.6 / 5.0"), 0, 3);
        grid.add(createStatRow("معدل رضا الطلاب:", "92%"), 1, 3);
        
        // الصف الخامس
        grid.add(createStatRow("معدل قبول الطلبات:", "68%"), 0, 4);
        grid.add(createStatRow("متوسط مدة التدريب:", "2.5 شهر"), 1, 4);
        
        card.getChildren().addAll(titleLabel, new Separator(), grid);
        
        return card;
    }
    
    /**
     * إنشاء صف إحصائية
     */
    private HBox createStatRow(String label, String value) {
        HBox row = new HBox(15);
        row.setAlignment(Pos.CENTER_LEFT);
        
        Label labelText = new Label(label);
        labelText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        labelText.setTextFill(Color.web("#7F8C8D"));
        labelText.setPrefWidth(220);
        
        Label valueText = new Label(value);
        valueText.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        valueText.setTextFill(Color.web("#2C3E50"));
        
        row.getChildren().addAll(labelText, valueText);
        
        return row;
    }
    
    /**
     * إنشاء صندوق الملخص
     */
    private VBox createSummaryBox() {
        VBox card = new VBox(15);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);"
        );
        card.setPadding(new Insets(25));
        card.setMaxWidth(1150);
        
        Label titleLabel = new Label("📝 ملخص التقرير");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web("#263238"));
        
        TextArea summaryText = new TextArea(
            "✓ النقاط الإيجابية:\n" +
            "• نمو مستمر في عدد المستخدمين بمعدل 15 مستخدم جديد شهرياً\n" +
            "• معدل نجاح مرتفع في إتمام التدريبات يصل إلى 95%\n" +
            "• تقييمات إيجابية من الطلاب بمتوسط 4.6 من 5\n" +
            "• زيادة في عدد الشركات المشاركة بنسبة 7% هذا الشهر\n\n" +
            "⚠️ نقاط التحسين:\n" +
            "• العمل على زيادة معدل قبول الطلبات من 68% إلى 75%\n" +
            "• تنويع فرص التدريب في مجالات جديدة\n" +
            "• تحسين التواصل بين الطلاب والشركات\n\n" +
            "🎯 التوصيات:\n" +
            "• إطلاق حملة تسويقية لجذب المزيد من الشركات\n" +
            "• تطوير برنامج تدريبي للطلاب قبل التقديم\n" +
            "• إنشاء نظام تقييم أكثر تفصيلاً للتدريبات"
        );
        summaryText.setEditable(false);
        summaryText.setWrapText(true);
        summaryText.setPrefRowCount(12);
        summaryText.setFont(Font.font("Arial", 13));
        summaryText.setStyle(
            "-fx-background-color: #F8F9FA;" +
            "-fx-border-color: transparent;" +
            "-fx-control-inner-background: #F8F9FA;" +
            "-fx-text-fill: #2C3E50;"
        );
        
        card.getChildren().addAll(titleLabel, new Separator(), summaryText);
        
        return card;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
<String, Number> series = new XYChart.Series<>();
        series.setName("المستخدمين الجدد");
        series.getData().add(new XYChart.Data<>("يناير", 25));
        series.getData().add(new XYChart.Data<>("فبراير", 42));
        series.getData().add(new XYChart.Data<>("مارس", 58));
        series.getData().add(new XYChart.Data<>("أبريل", 73));
        series.getData().add(new XYChart.Data<>("مايو", 89));
        series.getData().add(new XYChart.Data<>("يونيو", 105));
        series.getData().add(new XYChart.Data<>("يوليو", 128));
        series.getData().add(new XYChart.Data<>("أغسطس", 156));
        series.getData().add(new XYChart.Data<>("سبتمبر", 187));
        series.getData().add(new XYChart.Data<>("أكتوبر", 220));
        
        lineChart.getData().add(series);
        
        return lineChart;
    }
    
    /**
     * إنشاء رسم بياني دائري
     */
    private PieChart createPieChart() {
        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(
            new PieChart.Data("تطوير البرمجيات (32)", 32),
            new PieChart.Data("تصميم (21)", 21),
            new PieChart.Data("تسويق رقمي (18)", 18),
            new PieChart.Data("علم البيانات (12)", 12),
            new PieChart.Data("إدارة أعمال (6)", 6)
        );
        pieChart.setLegendVisible(true);
        pieChart.setPrefHeight(280);
        
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
        barChart.setPrefHeight(280);
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("الطلبات");
        series.getData().add(new XYChart.Data<>("يناير", 45));
        series.getData().add(new XYChart.Data<>("فبراير", 62));
        series.getData().add(new XYChart.Data<>("مارس", 58));
        series.getData().add(new XYChart.Data<>("أبريل", 78));
        series.getData().add(new XYChart.Data<>("مايو", 85));
        series.getData().add(new XYChart.Data<>("يونيو", 92));
        series.getData().add(new XYChart.Data<>("يوليو", 105));
        series.getData().add(new XYChart.Data<>("أغسطس", 98));
        series.getData().add(new XYChart.Data<>("سبتمبر", 112));
        series.getData().add(new XYChart.Data<>("أكتوبر", 128));
        
        barChart.getData().add(series);
        
        return barChart;
    }
    
    /**
     * إنشاء رسم بياني مساحي
     */
    private AreaChart<String, Number> createAreaChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("الشهر");
        yAxis.setLabel("معدل النجاح %");
        
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("");
        areaChart.setPrefHeight(280);
        
        XYChart.Series
    }