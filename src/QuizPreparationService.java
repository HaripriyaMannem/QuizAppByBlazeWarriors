import java.util.Arrays;
import java.util.Scanner;

public class QuizPreparationService {

    String easyLevel = QuizApp.quizLevels[0];
    String medLevel = QuizApp.quizLevels[1];
    String diffLevel = QuizApp.quizLevels[2];

    public void prepareQuiz(Trainer trainer){
        String quizLevels;
        String levels;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter which level"+ QuizApp.YELLOW_BOLD_BRIGHT + Arrays.toString(QuizApp.quizLevels) + RoleService.ANSI_RESET+" of Questions are you going to prepare:");
        String quizLevelType = sc.nextLine().trim();
        levels = quizLevelType;
        trainer.setQuizLevelType(quizLevelType);
        quizLevels = verifyQuizPreparedOrNot(levels);
        if(!quizLevels.equalsIgnoreCase("NA")){
            System.out.println("Enter " + QuizApp.YELLOW_BOLD_BRIGHT + "Y/N"+ RoleService.ANSI_RESET  +" to prepare Questions for level: " + QuizApp.YELLOW_BOLD_BRIGHT + convertFirstCharToUppercase(trainer.getQuizLevelType()) + RoleService.ANSI_RESET);
            String status = sc.nextLine().trim();
            if (status.equalsIgnoreCase("Y")) {
                //Display Questions According to level Type
                displayQuestions(trainer.getQuizLevelType());
                System.out.println(QuizApp.GREEN_BOLD_BRIGHT + trainer.getNoOfQuestions() + " Questions Prepared Successfully on " + convertFirstCharToUppercase(trainer.getQuizTitle()) + " for " + convertFirstCharToUppercase(trainer.getQuizLevelType()) + " Level by "+ convertFirstCharToUppercase(trainer.getName())+"." + RoleService.ANSI_RESET);
                System.out.println("Please enter which level "+ QuizApp.YELLOW_BOLD_BRIGHT  +quizLevels + RoleService.ANSI_RESET +" of Questions to be prepared next:");
                String quizLevelType2 = sc.nextLine().trim();
                levels = levels.concat(quizLevelType2);
                quizLevels = verifyQuizPreparedOrNot(levels);
                trainer.setQuizLevelType(quizLevelType2);
                if(!quizLevels.equalsIgnoreCase("NA")){
                    System.out.println("Enter " + QuizApp.YELLOW_BOLD_BRIGHT + "Y/N"+ RoleService.ANSI_RESET  +" to prepare Questions for level: " + QuizApp.YELLOW_BOLD_BRIGHT + convertFirstCharToUppercase(trainer.getQuizLevelType()) + RoleService.ANSI_RESET);
                    String status1 = sc.nextLine().trim();
                    if (status1.equalsIgnoreCase("Y")){
                        //Display Questions According to level Type
                        displayQuestions(trainer.getQuizLevelType());
                        System.out.println(QuizApp.GREEN_BOLD_BRIGHT + trainer.getNoOfQuestions() + " Questions Prepared Successfully on " + convertFirstCharToUppercase(trainer.getQuizTitle()) + " for " + convertFirstCharToUppercase(trainer.getQuizLevelType()) + " Level by "+ convertFirstCharToUppercase(trainer.getName())+ "." + RoleService.ANSI_RESET);
                        System.out.println("Please enter which level "+ quizLevels + " of Questions to be prepared next:");
                        String quizLevelType3 = sc.nextLine().trim();
                        trainer.setQuizLevelType(quizLevelType3);
                        quizLevels = verifyQuizPreparedOrNot(trainer.getQuizLevelType());
                        if(!quizLevels.equalsIgnoreCase("NA")){
                            System.out.println("Enter " + QuizApp.YELLOW_BOLD_BRIGHT + "Y/N"+ RoleService.ANSI_RESET  +" to prepare Questions for level: " + QuizApp.YELLOW_BOLD_BRIGHT + convertFirstCharToUppercase(trainer.getQuizLevelType()) + RoleService.ANSI_RESET);
                            String status2 = sc.nextLine().trim();
                            if (status2.equalsIgnoreCase("Y")){
                                //Display Questions According to level Type
                                displayQuestions(trainer.getQuizLevelType());
                                System.out.println(QuizApp.GREEN_BOLD_BRIGHT + trainer.getNoOfQuestions() + " Questions Prepared Successfully on " + convertFirstCharToUppercase(trainer.getQuizTitle()) + " for " + convertFirstCharToUppercase(trainer.getQuizLevelType()) + " Level by "+ convertFirstCharToUppercase(trainer.getName()) + "."+ RoleService.ANSI_RESET);
                                System.out.println(QuizApp.BLUE_BOLD_BRIGHT + "Hurray!!! "+ convertFirstCharToUppercase(trainer.getName()) + " you have done with the quiz preparation. Good job." + RoleService.ANSI_RESET);
                            }
                            else{
                                System.out.println(QuizApp.PURPLE_BOLD_BRIGHT+ convertFirstCharToUppercase(trainer.getName())+", hope you had a great time. Thanks for visiting QuizApp by Blaze Warriors!!!"+ RoleService.ANSI_RESET);
                            }
                        }
                        else{
                            System.out.println(RoleService.ANSI_RED+ "Oops!!! Quiz Level type is not valid" + RoleService.ANSI_RESET);
                            prepareQuiz(trainer);
                        }
                    } else{
                        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT+ convertFirstCharToUppercase(trainer.getName())+", hope you had a great time. Thanks for visiting QuizApp by Blaze Warriors!!!"+ RoleService.ANSI_RESET);
                    }
                }
                else{
                    System.out.println(RoleService.ANSI_RED+ "Oops!!! Quiz Level type is not valid" + RoleService.ANSI_RESET);
                    prepareQuiz(trainer);
                }
            }else{
                System.out.println(QuizApp.PURPLE_BOLD_BRIGHT+ convertFirstCharToUppercase(trainer.getName())+", hope you had a great time. Thanks for visiting QuizApp by Blaze Warriors!!!"+ RoleService.ANSI_RESET);
            }
        }
        else{
            System.out.println(RoleService.ANSI_RED+ "Oops!!! Quiz Level type is not valid" + RoleService.ANSI_RESET);
            prepareQuiz(trainer);
        }
    }

    private String verifyQuizPreparedOrNot(String quizLevels){
        String level = "";
        if(!quizLevels.isEmpty()){
            switch (quizLevels.toLowerCase()) {
                case "easy":
                    level = "[Medium, Difficult]";
                    break;
                case "medium":
                    level = "[Easy, Difficult]";
                    break;
                case "difficult":
                    level = "[Easy, Medium]";
                    break;
                case "easymedium":
                case "mediumeasy":
                    level = "[Difficult]";
                    break;
                case "mediumdifficult":
                case "difficultmedium":
                    level = "[Easy]";
                    break;
                case "easydifficult":
                case "difficulteasy":
                    level = "[Medium]";
                    break;
                default:
                    level = "NA";
            }
        }
        return level;
    }


    private void displayQuestions(String levelType){

        if(levelType.equalsIgnoreCase(easyLevel)){
            EasyLevelQuestions[] easyLevelQuestions= prepareEasyQuestions();
            for(EasyLevelQuestions question : easyLevelQuestions) {
                System.out.println(question.toString());
            }
        }
        else if(levelType.equalsIgnoreCase(medLevel)){
             MediumLevelQuestions[] mediumLevelQuestions = prepareMedQuestions();
            for(MediumLevelQuestions question : mediumLevelQuestions) {
                System.out.println(question.toString());
            }
        }
        else if(levelType.equalsIgnoreCase(diffLevel)){
            DifficultLevelQuestions[] difficultLevelQuestions = prepareDiffQuestions();
            for(DifficultLevelQuestions question : difficultLevelQuestions) {
                System.out.println(question.toString());
            }
        }
    }

    public EasyLevelQuestions[] prepareEasyQuestions(){

        EasyLevelQuestions[] easyLevelQuestions = new EasyLevelQuestions[5];
        easyLevelQuestions[0] =new EasyLevelQuestions(1,"In Java default real number is", "a) float", "b) int", "c) double", "d) long", "c");
        easyLevelQuestions[1] =new EasyLevelQuestions(2,"What is the ASCII code of G in Java", "a) 65", "b) 75", "c) 61", "d) 71", "d");
        easyLevelQuestions[2] =new EasyLevelQuestions(3,"In Java float number should prefix with f?", "a) true", "b) false", "", "", "a");
        easyLevelQuestions[3] =new EasyLevelQuestions(4,"What is the correct option for method naming in Java", "a) 1methodName", "b) _1methodName&", "c) _methodName$", "d) MethodName", "c");
        easyLevelQuestions[4] =new EasyLevelQuestions(5,"Identify Relational Operators in Java", "a) ><", "b) &&", "c) +-", "d) ?:", "a");

        return easyLevelQuestions;
    }

    public MediumLevelQuestions[] prepareMedQuestions(){

        MediumLevelQuestions[] mediumLevelQuestions = new MediumLevelQuestions[5];
        mediumLevelQuestions[0] =new MediumLevelQuestions(1,"In Java default real number is", "a) float", "b) int", "c) double", "d) long", "c");
        mediumLevelQuestions[1] =new MediumLevelQuestions(2,"What is the ASCII code of G in Java", "a) 65", "b) 75", "c) 61", "d) 71", "d");
        mediumLevelQuestions[2] =new MediumLevelQuestions(3,"In Java float number should prefix with f?", "a) true", "b) false", "", "", "a");
        mediumLevelQuestions[3] =new MediumLevelQuestions(4,"What is the correct option for method naming in Java", "a) 1methodName", "b) _1methodName&", "c) _methodName$", "d) MethodName", "c");
        mediumLevelQuestions[4] =new MediumLevelQuestions(5,"Identify Relational Operators in Java", "a) ><", "b) &&", "c) +-", "d) ?:", "a");

        return mediumLevelQuestions;
    }

    public DifficultLevelQuestions[] prepareDiffQuestions(){

        DifficultLevelQuestions[] difficultLevelQuestions = new DifficultLevelQuestions[5];
        difficultLevelQuestions[0] =new DifficultLevelQuestions(1,"In Java default real number is", "a) float", "b) int", "c) double", "d) long", "c");
        difficultLevelQuestions[1] =new DifficultLevelQuestions(2,"What is the ASCII code of G in Java", "a) 65", "b) 75", "c) 61", "d) 71", "d");
        difficultLevelQuestions[2] =new DifficultLevelQuestions(3,"In Java float number should prefix with f?", "a) true", "b) false", "", "", "a");
        difficultLevelQuestions[3] =new DifficultLevelQuestions(4,"What is the correct option for method naming in Java", "a) 1methodName", "b) _1methodName&", "c) _methodName$", "d) MethodName", "c");
        difficultLevelQuestions[4] =new DifficultLevelQuestions(5,"Identify Relational Operators in Java", "a) ><", "b) &&", "c) +-", "d) ?:", "a");

        return difficultLevelQuestions;
    }

    public String convertFirstCharToUppercase(String source){
        return source.substring(0, 1).toUpperCase() + source.substring(1);
    }
}
