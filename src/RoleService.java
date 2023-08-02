import javax.management.relation.Role;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class RoleService {
    public RoleService() {
        System.out.println("Welcome to the Quiz App By BlazeWarriors!!!");
    }

    public void validateRole(){
        System.out.println("Please enter your Role: Trainer/Student");
        Scanner sc = new Scanner(System.in);
        String role = sc.nextLine().trim();
        if(role.equalsIgnoreCase("Trainer")){
            QuizPreparationService quizPreparationService = new QuizPreparationService();
            quizPreparationService.prepareQuiz();
        } else if(role.equalsIgnoreCase("Student")){
            QuizPlayService quizPlayService = new QuizPlayService();
            quizPlayService.playQuiz();
        } else{
            System.out.println("QuizApp By BlazeWarriors not supporting given role, Thank you!");
            validateRole();
        }

    }

}
