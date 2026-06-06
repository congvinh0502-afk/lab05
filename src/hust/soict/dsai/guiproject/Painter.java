package hust.soict.dsai.guiproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Painter extends Application {

    private static final double CANVAS_W  = 800;
    private static final double CANVAS_H  = 580;
    private static final double PEN_SIZE   = 4;
    private static final double ERASER_SIZE = 24;

    private boolean erasing = false;
    private Color   penColor = Color.BLACK;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(CANVAS_W, CANVAS_H);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, CANVAS_W, CANVAS_H);
        gc.setStroke(penColor);
        gc.setLineWidth(PEN_SIZE);
        gc.setLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        gc.setLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (!erasing) {
                gc.beginPath();
                gc.moveTo(e.getX(), e.getY());
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (erasing) {
                double half = ERASER_SIZE / 2;
                gc.setFill(Color.WHITE);
                gc.fillRect(e.getX() - half, e.getY() - half, ERASER_SIZE, ERASER_SIZE);
            } else {
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();
                gc.moveTo(e.getX(), e.getY());
            }
        });

        // ── Toolbar ──────────────────────────────────────────────────
        ColorPicker colorPicker = new ColorPicker(penColor);
        colorPicker.setOnAction(e -> {
            penColor = colorPicker.getValue();
            if (!erasing) gc.setStroke(penColor);
        });

        ToggleButton btnEraser = new ToggleButton("Eraser");
        btnEraser.setOnAction(e -> erasing = btnEraser.isSelected());

        Button btnClear = new Button("Clear");
        btnClear.setOnAction(e -> {
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, CANVAS_W, CANVAS_H);
        });

        Slider sizeSlider = new Slider(1, 30, PEN_SIZE);
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setMajorTickUnit(10);
        sizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (!erasing) gc.setLineWidth(newVal.doubleValue());
        });

        ToolBar toolbar = new ToolBar(
            new Label("Color:"), colorPicker,
            new Separator(),
            btnEraser,
            new Separator(),
            new Label("Size:"), sizeSlider,
            new Separator(),
            btnClear
        );

        BorderPane root = new BorderPane();
        root.setTop(toolbar);
        root.setCenter(canvas);

        stage.setScene(new Scene(root));
        stage.setTitle("Painter");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
