public class Student {
    private String name;

    private String quizLevel;
    private boolean isEasyLevelPlayed;
    private boolean isMedLevelPlayed;
    private boolean isDiffLevelPlayed;
    private String award;

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

    public boolean isEasyLevelPlayed() {
        return isEasyLevelPlayed;
    }

    public void setEasyLevelPlayed(boolean easyLevelPlayed) {
        isEasyLevelPlayed = easyLevelPlayed;
    }

    public boolean isMedLevelPlayed() {
        return isMedLevelPlayed;
    }

    public void setMedLevelPlayed(boolean medLevelPlayed) {
        isMedLevelPlayed = medLevelPlayed;
    }

    public boolean isDiffLevelPlayed() {
        return isDiffLevelPlayed;
    }

    public void setDiffLevelPlayed(boolean diffLevelPlayed) {
        isDiffLevelPlayed = diffLevelPlayed;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = "Blaze Warrior";
    }
}
