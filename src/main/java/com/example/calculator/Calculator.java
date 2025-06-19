package com.example.calculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

public class Calculator extends Application {

    private TextField display = new TextField();
    private String currentInput = "";
    private double xOffset = 0;
    private double yOffset = 0;
    private boolean darkMode = false;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        display.setEditable(false);
        display.setFocusTraversable(false); // prevent focus
        display.setId("display");
        display.setAlignment(Pos.CENTER_RIGHT);
        display.getStyleClass().add("display");

        GridPane grid = createButtonGrid();
        grid.setId("button-grid");

        Button closeBtn = new Button("×");
        closeBtn.setId("close-btn");
        closeBtn.setFocusTraversable(false); // no focus
        closeBtn.setOnAction(e -> primaryStage.close());

        Button minimizeBtn = new Button("-");
        minimizeBtn.setId("minimize-btn");
        minimizeBtn.setFocusTraversable(false); // no focus
        minimizeBtn.setOnAction(e -> primaryStage.setIconified(true));

        Button themeBtn = new Button("☀");
        themeBtn.setId("theme-btn");
        themeBtn.setFocusTraversable(false); // no focus
        themeBtn.setOnAction(e -> toggleTheme(primaryStage));

        GridPane windowControls = new GridPane();
        windowControls.setHgap(10);
        windowControls.setAlignment(Pos.TOP_RIGHT);
        windowControls.add(themeBtn, 0, 0);
        windowControls.add(minimizeBtn, 1, 0);
        windowControls.add(closeBtn, 2, 0);

        VBox root = new VBox(10, windowControls, display, grid);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);
        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        root.setId("root");

        Rectangle shadow = new Rectangle(350, 500);
        shadow.setArcWidth(20);
        shadow.setArcHeight(20);
        shadow.setFill(Color.TRANSPARENT);
        shadow.setEffect(new javafx.scene.effect.DropShadow(20, Color.gray(0, 0.3)));

        Scene scene = new Scene(root, 350, 500);
        scene.setFill(Color.TRANSPARENT);

        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        // ✅ Final Fixed Keyboard Handler
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            String key = event.getText();

            switch (event.getCode()) {
                case ENTER:
                    event.consume(); // prevent button trigger
                    animateButton("=");
                    handleInput("=");
                    break;
                case ESCAPE:
                case DELETE:
                    event.consume();
                    animateButton("C");
                    handleInput("C");
                    break;
                case BACK_SPACE:
                    event.consume();
                    animateButton("⌫");
                    handleInput("⌫");
                    break;
                case R:
                    animateButton("√");
                    handleInput("√");
                    break;
                case S:
                    animateButton("x²");
                    handleInput("x²");
                    break;
                case PLUS:
                case ADD:
                    animateButton("+");
                    handleInput("+");
                    break;
                case MINUS:
                case SUBTRACT:
                    animateButton("-");
                    handleInput("-");
                    break;
                case SLASH:
                case DIVIDE:
                    animateButton("/");
                    handleInput("/");
                    break;
                case ASTERISK:
                case MULTIPLY:
                    animateButton("*");
                    handleInput("*");
                    break;
                case PERIOD:
                case DECIMAL:
                    animateButton(".");
                    handleInput(".");
                    break;
                default:
                    if ("0123456789".contains(key)) {
                        animateButton(key);
                        handleInput(key);
                    }
                    break;
            }
        });

        primaryStage.setTitle("Modern Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createButtonGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        String[][] buttons = {
                {"7", "8", "9", "/", "⌫"},
                {"4", "5", "6", "*", "√"},
                {"1", "2", "3", "-", "x²"},
                {"0", ".", "=", "+", "C"}
        };

        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                String text = buttons[row][col];
                Button btn = new Button(text);
                btn.setPrefSize(65, 65);
                btn.setFocusTraversable(false); // focus off
                btn.getStyleClass().add("calc-button");

                if (text.matches("[0-9.]")) {
                    btn.getStyleClass().add("number-btn");
                } else if (text.equals("=")) {
                    btn.getStyleClass().add("equals-btn");
                } else if (text.equals("C") || text.equals("⌫")) {
                    btn.getStyleClass().add("clear-btn");
                } else {
                    btn.getStyleClass().add("operator-btn");
                }

                btn.setOnAction(e -> {
                    animateButton(text);
                    handleInput(text);
                });

                grid.add(btn, col, row);
            }
        }

        return grid;
    }

    private void animateButton(String value) {
        Button btn = findButtonByText(value);
        if (btn != null) {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), btn);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(0.9);
            st.setToY(0.9);
            st.setAutoReverse(true);
            st.setCycleCount(2);
            st.play();
        }
    }

    private Button findButtonByText(String text) {
        VBox root = (VBox) display.getParent();
        GridPane windowControls = (GridPane) root.getChildren().get(0);

        for (var node : windowControls.getChildren()) {
            if (node instanceof Button && ((Button) node).getText().equals(text)) {
                return (Button) node;
            }
        }

        GridPane buttonGrid = (GridPane) root.getChildren().get(2);
        for (var node : buttonGrid.getChildren()) {
            if (node instanceof Button && ((Button) node).getText().equals(text)) {
                return (Button) node;
            }
        }

        return null;
    }

    private void toggleTheme(Stage stage) {
        darkMode = !darkMode;
        VBox root = (VBox) stage.getScene().getRoot();
        if (darkMode) {
            root.getStylesheets().remove(0);
            root.getStylesheets().add(getClass().getResource("dark-style.css").toExternalForm());
            ((Button) findButtonByText("☀")).setText("☾");
        } else {
            root.getStylesheets().remove(0);
            root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            ((Button) findButtonByText("☾")).setText("☀");
        }

        FadeTransition ft = new FadeTransition(Duration.millis(300), root);
        ft.setFromValue(0.8);
        ft.setToValue(1);
        ft.play();
    }

    private void handleInput(String value) {
        switch (value) {
            case "=":
                try {
                    double result = eval(currentInput);
                    display.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);
                } catch (Exception e) {
                    display.setText("Error");
                    currentInput = "";
                }
                break;
            case "C":
                currentInput = "";
                display.setText("");
                break;
            case "⌫":
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    display.setText(currentInput);
                }
                break;
            case "√":
                try {
                    double num = currentInput.isEmpty() ? 0 : Double.parseDouble(currentInput);
                    double sqrt = Math.sqrt(num);
                    display.setText(String.valueOf(sqrt));
                    currentInput = String.valueOf(sqrt);
                } catch (Exception e) {
                    display.setText("Error");
                    currentInput = "";
                }
                break;
            case "x²":
                try {
                    double num = currentInput.isEmpty() ? 0 : Double.parseDouble(currentInput);
                    double square = num * num;
                    display.setText(String.valueOf(square));
                    currentInput = String.valueOf(square);
                } catch (Exception e) {
                    display.setText("Error");
                    currentInput = "";
                }
                break;
            default:
                currentInput += value;
                display.setText(currentInput);
        }
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                return x;
            }
        }.parse();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
