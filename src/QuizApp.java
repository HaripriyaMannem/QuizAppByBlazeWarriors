import java.util.Scanner;

public class QuizApp {

    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE

    static String[] quizLevels = new String[3];

    static{
        quizLevels[0] = "Easy";
        quizLevels[1] = "Medium";
        quizLevels[2] = "Difficult";
    }

    public static void main(String[] args) throws Exception {
        RoleService roleService = new RoleService();
        roleService.validateRole();

    }

}