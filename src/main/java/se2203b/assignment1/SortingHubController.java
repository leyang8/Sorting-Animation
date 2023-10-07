package se2203b.assignment1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.*;

public class SortingHubController implements Initializable {
    @FXML
    private int[] intArray;
    @FXML
    private ComboBox<String> AlgorithmChoiceBox;
    @FXML
    private Slider ArraySizeSlider;
    @FXML
    public Button SortButton;
    @FXML
    public Button ResetButton;
    @FXML
    private Label SliderValueShownLabel;
    @FXML
    private Pane BarShowingPane;
    private SortingStrategy sortingMethod;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AlgorithmChoiceBox.getItems().setAll("Merge Sort", "Selection Sort");
        AlgorithmChoiceBox.setValue("Merge Sort");
        ArraySizeSlider.setValue(64);
        SliderValueShownLabel.setText(String.valueOf((int) ArraySizeSlider.getValue()));
        generateArray(64);
        updateGraph(intArray);
    }

    public void generateArray(int length){
        intArray = new int[length];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(i+1);
        }
        Collections.shuffle(list);
        for (int i = 0; i < list.size(); i++) {
            intArray[i] = list.get(i);
        }
    }
    public void updateGraph(int[] list) {
        BarShowingPane.getChildren().clear();
        for (int i = 0; i < list.length; i++) {
            double width = (BarShowingPane.getPrefWidth() - list.length - 1)/ list.length ;
            double height = (double) list[i] / list.length * BarShowingPane.getPrefHeight() - 2;
            Rectangle rectangle = new Rectangle((width+1) * i + 1,BarShowingPane.getPrefHeight() - height - 1, width,height );
            rectangle.setFill(Color.RED);
            BarShowingPane.getChildren().add(rectangle);
        }
    }

        public void ChangeArraySize () {
            SliderValueShownLabel.setText(String.valueOf((int) ArraySizeSlider.getValue()));
            generateArray((int)ArraySizeSlider.getValue());
            updateGraph(intArray);
        }

        public void setSortStrategy(){
            if (Objects.equals(AlgorithmChoiceBox.getValue(), "Merge Sort")){
                this.sortingMethod = new MergeSort(intArray,this);
            }else {
                this.sortingMethod = new SelectionSort(intArray,this);
            }
        }
        public void StartSorting () {
            setSortStrategy();
            SortingStrategy sortingStrategy = sortingMethod;
            Thread thread = new Thread(sortingStrategy);
            thread.start();
        }

        public void ResetToInitial () {
            intArray = new int[64];
            AlgorithmChoiceBox.setValue("Merge Sort");
            ArraySizeSlider.setValue(64);
            SliderValueShownLabel.setText(String.valueOf((int) ArraySizeSlider.getValue()));
            generateArray(64);
            updateGraph(intArray);
        }
    }

