package com.example.mainlogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class QuizController {
    @FXML
    private Label label_question;

    @FXML
    private RadioButton option1;

    @FXML
    private RadioButton option2;

    @FXML
    private RadioButton option3;

    @FXML
    private RadioButton option4;

    @FXML
    private Button button_next;

    @FXML
    private Button button_back;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private Label label_result;

    private int currentQuestionIndex;
    private int correctAnswersCount;
    private int totalQuestions;

    private final String[] questions = {
            "What is the capital of France?",
            "What is the largest ocean on Earth?",
            "What is the chemical symbol for water?",
            "What is the longest river in the world?",
            "Which planet is closest to the sun?",
            "What is the capital city of Nepal?",
            "What is the hottest planet in our solar system?",
            "What is the largest animal on Earth?",
            "What is the tallest mountain in the world?",
            "What is the most populous country in the world?",
            "What is the longest language in the world?",
            "What is the oldest living mammal on Earth?",
            "What is the highest mountain range in the world?",
            "What is the largest desert in the world?",
            "What is the coldest continent on Earth?",
            "What is the largest ocean trench in the world?",
            "Which gas makes up the majority of Earth's atmosphere?",
            "What is the largest moon of Jupiter?",
            "What is the chemical symbol for gold?",
            "Which planet is known as the 'Red Planet'?"
    };

    private final String[][] options = {
            {"London", "Paris", "Berlin", "Rome"},
            {"Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"},
            {"H2O", "CO2", "O2", "N2"},
            {"Nile River", "Amazon River", "Yangtze River", "Mississippi River"},
            {"Mercury", "Venus", "Earth", "Mars"},
            {"Pokhara", "Kathmandu", "Bhaktapur", "Patan"},
            {"Mercury", "Venus", "Earth", "Mars"},
            {"Blue whale", "African elephant", "Giant squid", "American bison"},
            {"Mount Everest", "K2", "Kanchenjunga", "Lhotse"},
            {"China", "India", "United States", "Indonesia"},
            {"Tamil", "Finnish", "Hungarian", "Turkish"},
            {"Bowhead whale", "Greenland shark", "Ocean quahog", "Rougheye rockfish"},
            {"Himalayas", "Andes", "Rocky Mountains", "Alps"},
            {"Sahara Desert", "Arabian Desert", "Gobi Desert", "Patagonian Desert"},
            {"Antarctica", "Greenland", "Siberia", "Alaska"},
            {"Mariana Trench", "Tonga Trench", "Kuril-Kamchatka Trench", "Philippine Trench"},
            {"Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"},
            {"Callisto", "Ganymede", "Europa", "Io"},
            {"Go", "Gl", "Au", "Ag"},
            {"Mercury", "Venus", "Earth", "Mars"}
    };

    private final int[] answers = {
            1,  // Question 1, correct answer is option 1 (Paris)
            0,  // Question 2, correct answer is option 0 (Pacific Ocean)
            0,  // Question 3, correct answer is option 0 (H2O)
            1,  // Question 4, correct answer is option 1 (Amazon River)
            0,  // Question 5, correct answer is option 0 (Mercury)
            1,  // Question 6, correct answer is option 1 (Kathmandu)
            0,  // Question 7, correct answer is option 0 (Venus)
            1,  // Question 8, correct answer is option 1 (Blue whale)
            0,  // Question 9, correct answer is option 0 (Mount Everest)
            2,  // Question 10, correct answer is option 2 (China)
            0,  // Question 11, correct answer is option 0 (Tamil)
            1,  // Question 12, correct answer is option 1 (Greenland shark)
            0,  // Question 13, correct answer is option 0 (Himalayas)
            0,  // Question 14, correct answer is option 0 (Sahara Desert)
            0,  // Question 15, correct answer is option 0 (Antarctica)
            3,  // Question 16, correct answer is option 3 (Mariana Trench)
            2,  // Question 17, correct answer is option 2 (Nitrogen)
            1,  // Question 18, correct answer is option 1 (Ganymede)
            2,  // Question 19, correct answer is option 2 (Au)
            3   // Question 20, correct answer is option 3 (Mars)
    };

    private final String[] correctAnswers = {
            "Paris",
            "Pacific Ocean",
            "H2O",
            "Amazon River",
            "Mercury",
            "Kathmandu",
            "Venus",
            "Blue whale",
            "Mount Everest",
            "China",
            "Tamil",
            "Greenland shark",
            "Himalayas",
            "Sahara Desert",
            "Antarctica",
            "Mariana Trench",
            "Nitrogen",
            "Ganymede",
            "Au",
            "Mars"
    };

    public void initialize() {
        toggleGroup = new ToggleGroup();
        option1.setToggleGroup(toggleGroup);
        option2.setToggleGroup(toggleGroup);
        option3.setToggleGroup(toggleGroup);
        option4.setToggleGroup(toggleGroup);

        currentQuestionIndex = 0;
        correctAnswersCount = 0;
        totalQuestions = questions.length;

        loadQuestion(currentQuestionIndex);
        button_back.setDisable(true);
    }

    @FXML
    private void handleNextButton(ActionEvent event) {
        int selectedAnswerIndex = getSelectedAnswerIndex();
        if (selectedAnswerIndex == -1) {
            label_result.setText("Please select an answer.");
            label_result.setTextFill(Color.RED);
            return;
        }

        if (selectedAnswerIndex == answers[currentQuestionIndex]) {
            correctAnswersCount++;
            label_result.setText("Correct!");
            label_result.setTextFill(Color.GREEN);
        } else {
            label_result.setText("Incorrect. The correct answer is: " + correctAnswers[currentQuestionIndex]);
            label_result.setTextFill(Color.RED);
        }

        button_back.setDisable(false);
        if (currentQuestionIndex < totalQuestions - 1) {
            currentQuestionIndex++;
            loadQuestion(currentQuestionIndex);
        } else {
            double percentage = (double) correctAnswersCount / totalQuestions * 100;
            label_result.setText("Quiz has ended! Your Percentage: " + String.format("%.2f%%", percentage) +
                    " Total Correct Answers: " + correctAnswersCount + "/" + totalQuestions);
            button_next.setDisable(true);
        }
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            loadQuestion(currentQuestionIndex);
            label_result.setText("");
            button_next.setDisable(false);
        }
        if (currentQuestionIndex == 0) {
            button_back.setDisable(true);
        }
    }

    private void loadQuestion(int index) {
        label_question.setText(questions[index]);
        option1.setText(options[index][0]);
        option2.setText(options[index][1]);
        option3.setText(options[index][2]);
        option4.setText(options[index][3]);
        label_result.setText("");
    }

    private int getSelectedAnswerIndex() {
        RadioButton selectedOption = (RadioButton) toggleGroup.getSelectedToggle();
        if (selectedOption != null) {
            int selectedAnswerIndex = Integer.parseInt(selectedOption.getUserData().toString());
            return selectedAnswerIndex;
        }
        return -1;
    }
}
