import javax.management.relation.Role;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class RoleService {
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public RoleService() {
        System.out.println(ANSI_CYAN + "*********************************************");
        System.out.println("Welcome to the Quiz App By Blaze Warriors!!!");
        System.out.println("*********************************************" + ANSI_RESET);
    }

    public void validateRole(){
        System.out.println("Please enter your Role: 1) Trainer");
        System.out.println("                        2) Student");
        Scanner sc = new Scanner(System.in);
        String role = sc.nextLine().trim();
        if(role.equalsIgnoreCase("Trainer") || role.equalsIgnoreCase("1")){
            Trainer trainer = new Trainer();
            System.out.println("Please enter your Name:");
            String name = sc.nextLine().trim();
            trainer.setName(name);
            System.out.println("Please enter Quiz Title:");
            String quizTitle = sc.nextLine().trim();
            trainer.setQuizTitle(quizTitle);
            QuizPreparationService quizPreparationService = new QuizPreparationService();
            quizPreparationService.prepareQuiz(trainer);
        } else if(role.equalsIgnoreCase("Student") || role.equalsIgnoreCase("2")){
            QuizPlayService quizPlayService = new QuizPlayService();
            quizPlayService.playQuiz();
        } else{
            System.out.println(ANSI_RED + "QuizApp By Blaze Warriors not supporting given role please try again, Thank you!" + ANSI_RESET);
            validateRole();
        }

    }

}
