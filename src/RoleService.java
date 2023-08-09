import javax.management.relation.Role;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class RoleService {
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String YELLOW_BOLD_BRIGHT = "\u001b[1;93m";
    Scanner sc = new Scanner(System.in);
    String role;
    int count = 1;

    public RoleService() throws InvalidRoleException {
        System.out.println(ANSI_CYAN + "*********************************************");
        System.out.println("Welcome to the Quiz App By Blaze Warriors!!!");
        System.out.println("*********************************************" + ANSI_RESET);
        try {
            selectRole();
            validateRole();
        } catch (InvalidRoleException e) {
            try {
                selectRole();
                validateRole();
            } catch (InvalidRoleException x) {
                try {
                    selectRole();
                    validateRole();
                } catch (InvalidRoleException ex) {
                    System.out.println(ANSI_RED + "Invalid Role" + ANSI_RESET);
                    System.exit(0);
                }

            }
        }
    }

    public void selectRole() {

        System.out.println("Please enter your Role: 1) Trainer");
        System.out.println("                        2) Student");
        role = sc.nextLine().trim();
    }

    public void validateRole() throws InvalidRoleException {
        if (role.equalsIgnoreCase("Trainer") || role.equalsIgnoreCase("1")) {
            Trainer trainer = new Trainer();
            System.out.println("Please enter your Name:");
            String name = sc.nextLine().trim();
            trainer.setName(name);
            System.out.println("Please enter Quiz Title:");
            String quizTitle = sc.nextLine().trim();
            trainer.setQuizTitle(quizTitle);
            QuizPreparationService quizPreparationService = new QuizPreparationService();
            quizPreparationService.prepareQuiz(trainer);
        } else if (role.equalsIgnoreCase("Student") || role.equalsIgnoreCase("2")) {
            QuizPlayService quizPlayService = new QuizPlayService();
            quizPlayService.playQuiz();
        } else {
            InvalidRoleException ex = new InvalidRoleException();
            if (count <= 2) {
                System.out.println(YELLOW_BOLD_BRIGHT
                        + "QuizApp By Blaze Warriors not supporting given role please try again..." + ANSI_RESET);
                count++;
            }
            throw ex;
        }
    }

}

class InvalidRoleException extends Exception {

}
