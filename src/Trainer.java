public class Trainer {
    private String name;
    private String quizTitle;
    private String quizLevelType;
    private final int noOfQuestions;

    public Trainer() {
        this.noOfQuestions = 10;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getQuizLevelType() {
        return quizLevelType;
    }

    public void setQuizLevelType(String quizLevelType) {
        this.quizLevelType = quizLevelType;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }
}
