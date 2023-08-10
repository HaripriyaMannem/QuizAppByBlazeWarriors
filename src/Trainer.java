public class Trainer {
    private String name;
    private String title;
    private String Level;
    private final int noOfQuests;

    public Trainer() {
        this.noOfQuests = 10;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public int getNoOfQuestions() {
        return noOfQuests;
    }
}
