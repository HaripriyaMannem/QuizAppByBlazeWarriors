import java.util.Scanner;

public class QuizApp {

    static String[] quizLevels = new String[3];

    static{
        quizLevels[0] = "Easy";
        quizLevels[1] = "Medium";
        quizLevels[2] = "Difficult";
    }

    public static void main(String[] args)  {

        boolean isStartQuiz = true;
        RoleService roleService = new RoleService(isStartQuiz);
        roleService.validateRole();

    }
}