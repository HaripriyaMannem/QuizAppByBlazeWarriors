import java.util.Arrays;
import java.util.Scanner;

public class RoleService {

    String quizLevels;
    QuizPreparationService quizPreparationService = new QuizPreparationService();
    Trainer trainer = new Trainer();
    public void validateRole(){
        String levels = "";
        System.out.println("Welcome to the Quiz App By BlazeWarriors!!!");
        System.out.println("Please enter your Role: Trainer/Student");
        Scanner sc = new Scanner(System.in);
        String role = sc.nextLine().trim();
        if(role.equalsIgnoreCase("Trainer")){
            System.out.println("Please enter your Name:");
            String name = sc.nextLine().trim();
            trainer.setName(name);
            System.out.println("Please enter Quiz Title:");
            String quizTitle = sc.nextLine().trim();
            trainer.setQuizTitle(quizTitle);
            System.out.println("Please enter which level(Easy/Medium/Difficult) of Questions to be prepared:");
            String quizLevelType = sc.nextLine().trim();
            levels = quizLevelType;
            trainer.setQuizLevelType(quizLevelType);
            System.out.println("Press Y/N to prepare Questions for level: " + trainer.getQuizLevelType());
            String status = sc.nextLine().trim();
            if (status.equalsIgnoreCase("Y")) {
                //Display Questions According to level Type
                //quizPreparationService.prepareQuiz(trainer.setQuizLevelType(quizLevelType));
                System.out.println(trainer.getNoOfQuestions() + " Questions Prepared Successfully on " + trainer.getQuizTitle() + " for " + trainer.getQuizLevelType() + " Level by "+ trainer.getName());
                quizLevels = verifyQuizPreparedORNot(levels);
                System.out.println("Please enter which level("+ quizLevels + ")of Questions to be prepared next:");
                String quizLevelType2 = sc.nextLine().trim();
                levels = levels.concat(quizLevelType2);
                trainer.setQuizLevelType(quizLevelType2);
                quizLevels = verifyQuizPreparedORNot(levels);
                System.out.println("Press Y/N to prepare Questions for level: " + trainer.getQuizLevelType());
                String status1 = sc.nextLine().trim();
                if (status1.equalsIgnoreCase("Y")){
                    //Display Questions According to level Type
                    //quizPreparationService.prepareQuiz(trainer.setQuizLevelType(quizLevelType));
                    System.out.println(trainer.getNoOfQuestions() + " Questions Prepared Successfully on " + trainer.getQuizTitle() + " for " + trainer.getQuizLevelType() + " Level by "+ trainer.getName());
                    quizLevels = verifyQuizPreparedORNot(levels);
                    System.out.println("Please enter which level("+ quizLevels + ")of Questions to be prepared next:");
                    String quizLevelType3 = sc.nextLine().trim();
                    levels = levels.concat(quizLevelType3);
                    trainer.setQuizLevelType(quizLevelType2);
                    System.out.println("Press Y/N to prepare Questions for level: " + trainer.getQuizLevelType());
                    String status2 = sc.nextLine().trim();
                    if (status2.equalsIgnoreCase("Y")){
                        //Display Questions According to level Type
                        //quizPreparationService.prepareQuiz(trainer.setQuizLevelType(quizLevelType));
                        System.out.println(trainer.getNoOfQuestions() + " Questions Prepared Successfully on " + trainer.getQuizTitle() + " for " + trainer.getQuizLevelType() + " Level by "+ trainer.getName());
                        System.out.println("Hurray!!! "+ trainer.getName() + "you have done with the quiz preparation. Good job.");
                    }
                    else{
                        System.out.println(trainer.getName() +" hope you had a great time,thanks for visiting QuizApp by Blaze Warriors!!!");
                    }
                } else{
                    System.out.println(trainer.getName() +" hope you had a great time,thanks for visiting QuizApp by Blaze Warriors!!!");
                }
            } else{
                System.out.println(trainer.getName() +" hope you had a great time,thanks for visiting QuizApp by Blaze Warriors!!!");
            }
            quizPreparationService.prepareQuiz();
        } else if(role.equalsIgnoreCase("Student")){
            quizPreparationService.playQuiz();
        } else{
            System.out.println("QuizApp By BlazeWarriors not supporting given role, Thank you!");
            validateRole();
        }

    }

    private String verifyQuizPreparedORNot(String quizLevels){
        String level = "";
            if(!quizLevels.isEmpty()){
                switch (quizLevels.toLowerCase()) {
                    case "easy":
                        level = "Medium/Difficult";
                        break;
                    case "medium":
                        level = "Easy/Difficult";
                        break;
                    case "difficult":
                        level = "Easy/Medium";
                        break;
                    case "easymedium":
                    case "mediumeasy":
                        level = "Difficult";
                        break;
                    case "mediumdifficult":
                    case "difficultmedium":
                        level = "Easy";
                        break;
                    case "easydifficult":
                    case "difficulteasy":
                        level = "Medium";
                        break;
                    default:
                        level = "NA";
                }
        }
        return level;
    }
}
