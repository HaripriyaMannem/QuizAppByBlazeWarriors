public class Student {
    private String name;
    private String quizLevel;
    private boolean isEasyPlayed;
    private boolean isMedPlayed;
    private boolean isDiffPlayed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuizLevel() {
        return quizLevel;
    }

    public void setQuizLevel(String quizLevel) {
        this.quizLevel = quizLevel;
    }

    public boolean isEasyPlayed() {
        return isEasyPlayed;
    }

    public void setEasyPlayed(boolean easyPlayed) {
        isEasyPlayed = easyPlayed;
    }

    public boolean isMedPlayed() {
        return isMedPlayed;
    }

    public void setMedPlayed(boolean medPlayed) {
        isMedPlayed = medPlayed;
    }

    public boolean isDiffPlayed() {
        return isDiffPlayed;
    }

    public void setDiffPlayed(boolean diffPlayed) {
        isDiffPlayed = diffPlayed;
    }
}
