public class MediumLevelQuestions extends Questions {

    private  String level;

    public MediumLevelQuestions(int id, String question, String opt1, String opt2, String opt3, String opt4, String answer) {
        super(id, question, opt1, opt2, opt3, opt4, answer);
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = QuizApp.quizLevels[1];
    }
}
