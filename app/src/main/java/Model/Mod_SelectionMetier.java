package Model;

import java.util.ArrayList;

public class Mod_SelectionMetier {
    private int currentQuestionIdx;
    ArrayList<String> questions;
    

    public Mod_SelectionMetier() {
        currentQuestionIdx = 0;
        questions = new ArrayList<>();
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public int getCurrentQuestionIdx() {
        return currentQuestionIdx;
    }

    public void decCurrentQuestionIdx() {
        currentQuestionIdx--;
    }

    public void incCurrentQuestionIdx() {
        currentQuestionIdx++;
    }

    public String getCurrentQuestion() {
        return questions.get(currentQuestionIdx);
    }


}
