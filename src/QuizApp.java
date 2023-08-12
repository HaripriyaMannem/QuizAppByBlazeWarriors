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
        String purple = "\033[1;95m";

        RoleService roleService = new RoleService(isStartQuiz);
        try
        {
            roleService.validateRole();
        }
        catch (InvalidRoleException e)
        {
            try
            {
                roleService.validateRole();
            }
            catch (InvalidRoleException ex)
            {
                System.out.println(purple + "you have exceeded the maximum number of attempts, " +
                        "please try again after some time.");
            }
        }
    }
}