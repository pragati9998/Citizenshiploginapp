package com.example.mainlogin;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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

    @FXML
    private Label label_timer;

    private int currentQuestionIndex;
    private int correctAnswersCount;
    private int totalQuestions;
    private int timeSeconds = 300; // 5 minutes

    private final String[] questions = {
            "What's the purpose of the Masathai Citizenship Test?",
            "Which countries are creating Masathai?",
            "Goal of MCQ System in Masathai?",
            "Importance of Citizenship Test?",
            "Era for Citizenship Test importance?",
            "Value of Citizenship Test?",
            "MCQ System's role in cultural integration?",
            "Challenge for MCQ System?",
            "Citizenship Test's focus on citizens?",
            "Context for MCQ System's value?",
            "Citizenship Test insights into?",
            "MCQ System's purpose for new citizens?",
            "Citizenship Test importance in Masathai?",
            "Important era for Citizenship Test?",
            "MCQ System's insights into?",
            "Not a focus of Citizenship Assessment System?",
            "Valuable context for Citizenship Assessment System?",
            "MCQ System's main takeaway?",
            "Citizenship Assessment System's insights into?",
            "Era with increased demand for cultural integration in Masathai?"
    };

    private final String[][] options = {
            {"Economic", "Cultural", "Language", "Political"},
            {"M, I, T", "S, V, T", "M, S, T", "P, M, T"},
            {"Bureaucracy", "Migration", "Citizenship", "Diversity"},
            {"Slow down", "Bureaucracy", "Integration", "Strict policies"},
            {"Isolationism", "Decrease", "Diversity", "Closed borders"},
            {"Opportunities", "Sports", "Culture", "Geography"},
            {"Hinder", "Proficiency", "Encourage", "Enforce"},
            {"Expedite", "Civic ed", "Mobility", "Backlog"},
            {"Migration", "Knowledge", "Integration", "Diplomacy"},
            {"Decline", "Isolation", "Expansion", "Borders"},
            {"Opportunities", "Ideologies", "Masathai's history", "Law"},
            {"Discourage", "Policies", "Masathai info", "Diversity limit"},
            {"Bureaucracy", "Slow down", "Integration", "Strict policies"},
            {"Homogeneity", "Isolation", "Decline", "Diversity"},
            {"Integration", "Education", "Proficiency", "Citizenship"},
            {"Expedite", "Isolation", "Citizenship", "Knowledge"},
            {"Decline", "Backlog", "Education", "Mobility"},
            {"Hinder", "Proficiency", "Mobility", "Integration"},
            {"Opportunities", "Ideologies", "History", "Law"},
            {"Homogeneity", "Isolation", "Decline", "Diversity"}
    };

    private final int[] answers = {
            1,  // Question 1, correct answer is option B (Cultural)
            2,  // Question 2, correct answer is option C (Citizenship)
            3,  // Question 3, correct answer is option C (Integration)
            3,  // Question 4, correct answer is option D (Strict policies)
            1,  // Question 5, correct answer is option B (Sports)
            3,  // Question 6, correct answer is option C (Encourage)
            4,  // Question 7, correct answer is option D (Borders)
            2,  // Question 8, correct answer is option B (Civic ed)
            3,  // Question 9, correct answer is option C (Integration)
            1,  // Question 10, correct answer is option A (Expedite)
            3,  // Question 11, correct answer is option C (Integration)
            4,  // Question 12, correct answer is option D (Diversity)
            1,  // Question 13, correct answer is option A (Integration)
            2,  // Question 14, correct answer is option B (Backlog)
            3,  // Question 15, correct answer is option C (Proficiency)
            3,  // Question 16, correct answer is option C (Citizenship)
            2,  // Question 17, correct answer is option B (Isolation)
            3,  // Question 18, correct answer is option C (Citizenship)
            4,  // Question 19, correct answer is option D (Knowledge)
            1   // Question 20, correct answer is option A (Homogeneity)
    };

    private final String[] correctAnswers = {
            "Cultural", "Citizenship", "Integration", "Strict policies", "Sports",
            "Encourage", "Borders", "Civic ed", "Integration", "Expedite",
            "Integration", "Diversity", "Integration", "Backlog", "Proficiency",
            "Citizenship", "Isolation", "Citizenship", "Knowledge", "Homogeneity"
    };

    private Timeline timer;

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

        // Initialize timer
        initializeTimer();
    }

    private void initializeTimer() {
        timer = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (timeSeconds > 0) {
                        timeSeconds--;
                        updateTimerLabel();
                    } else {
                        timer.stop();
                        handleQuizTimeout();
                    }
                })
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void updateTimerLabel() {
        int minutes = timeSeconds / 60;
        int seconds = timeSeconds % 60;
        label_timer.setText(String.format("Time: %02d:%02d", minutes, seconds));
    }

    private void handleQuizTimeout() {
        label_result.setText("Time's up! Quiz has ended.");
        button_next.setDisable(true);

        // Disable all radio buttons in the toggle group
        toggleGroup.getToggles().forEach(toggle -> {
            if (toggle instanceof RadioButton) {
                ((RadioButton) toggle).setDisable(true);
            }
        });
    }

    @FXML
    private void handleNextButton(ActionEvent event) {
        // ... (unchanged)
        int selectedAnswerIndex = getSelectedAnswerIndex();
        if (selectedAnswerIndex == -1) {
            label_result.setText("Please select an answer.");
            label_result.setTextFill(Color.RED);
            return;
        }

        if (selectedAnswerIndex == answers[currentQuestionIndex]) {
            correctAnswersCount++;
            label_result.setText("Correct!");
            label_result.setTextFill(Color.WHITE);
        } else {
            label_result.setText("Incorrect. The correct answer is: " + correctAnswers[currentQuestionIndex]);
            label_result.setTextFill(Color.WHITE);
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
        // ... (unchanged)
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
        // ... (unchanged)
        label_question.setText(questions[index]);
        option1.setText(options[index][0]);
        option2.setText(options[index][1]);
        option3.setText(options[index][2]);
        option4.setText(options[index][3]);
        label_result.setText("");
    }

    private int getSelectedAnswerIndex() {
        // ... (unchanged)
        RadioButton selectedOption = (RadioButton) toggleGroup.getSelectedToggle();
        if (selectedOption != null) {
            int selectedAnswerIndex = Integer.parseInt(selectedOption.getUserData().toString());
            return selectedAnswerIndex;
        }
        return -1;
    }
}
