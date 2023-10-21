package com.example.bmi;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class BMICalculatorController {
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private Label resultLabel;

    public void calculateBMI() {
        String weightText = weightField.getText();
        String heightText = heightField.getText();

        if (weightText.isEmpty() || heightText.isEmpty()) {
            resultLabel.setText("Please enter both weight and height.");
            return;
        }

        String[] weightParts = weightText.split(" ");
        String[] heightParts = heightText.split(" ");

        if (weightParts.length != 2 || heightParts.length != 2) {
            resultLabel.setText("Please enter weight and height in the format 'value unit' (e.g., '70 kg' or '170 cm').");
            return;
        }

        double weight;
        double height;

        try {
            weight = Double.parseDouble(weightParts[0]);
            height = Double.parseDouble(heightParts[0]);
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers for weight and height.");
            return;
        }

        String weightUnit = weightParts[1].toLowerCase();
        String heightUnit = heightParts[1].toLowerCase();

        double bmi;
        if ((weightUnit.equals("kg") || weightUnit.equals("lbs")) && (heightUnit.equals("cm") || heightUnit.equals("inch"))) {
            if (weightUnit.equals("lbs")) {
                // Convert weight from lbs to kg
                weight = weight * 0.453592;
            }
            if (heightUnit.equals("inch")) {
                // Convert height from inches to cm
                height = height * 2.54;
            }
            // Calculate BMI in metric units
            bmi = weight / ((height / 100) * (height / 100));
        } else {
            resultLabel.setText("Invalid units. Please use either kg/cm or lbs/inch.");
            return;
        }

        String status;
        if (bmi < 18.5) {
            status = "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            status = "Normal";
        } else if (bmi >= 25 && bmi < 29.9) {
            status = "Overweight";
        } else {
            status = "Obese";
        }

        resultLabel.setText("Your BMI: " + String.format("%.2f", bmi) + " (" + status + ")");
    }

    public void clearFields() {
        weightField.clear();
        heightField.clear();
        resultLabel.setText("");
    }

    public void showAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About BMI Calculator");
        alert.setHeaderText("BMI Calculator Application");
        alert.setContentText("This application calculates your BMI based on your weight and height.\n" +
                "Enter weight in kg or lbs and height in cm or inch.");
        alert.showAndWait();
    }

    public void exitApplication() {
        Platform.exit();
    }
}
