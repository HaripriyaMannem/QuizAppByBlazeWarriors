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

        EasyLevelQuestions[] easyLevelQuestions = new EasyLevelQuestions[10];
        easyLevelQuestions[0] =new EasyLevelQuestions(1,"In Java default real number is?", "a) float", "b) int", "c) double", "d) long", "c");
        easyLevelQuestions[1] =new EasyLevelQuestions(2,"What is the ASCII code of G in Java?", "a) 65", "b) 75", "c) 61", "d) 71", "d");
        easyLevelQuestions[2] =new EasyLevelQuestions(3,"In Java float number should prefix with f?", "a) true", "b) false", "", "", "a");
        easyLevelQuestions[3] =new EasyLevelQuestions(4,"What is the correct option for method naming in Java?", "a) 1methodName", "b) _1methodName&", "c) _methodName$", "d) MethodName", "c");
        easyLevelQuestions[4] =new EasyLevelQuestions(5,"Identify Relational Operators in Java?", "a) ><", "b) &&", "c) +-", "d) ?:", "a");
        easyLevelQuestions[5] =new EasyLevelQuestions(6,"If condition is true or not on which loop at least one time body will execute?", "a) for", "b) while", "c) do while", "d) for-each", "c");
        easyLevelQuestions[6] =new EasyLevelQuestions(7,"What are the variables which are declared at class level?", "a) local", "b) static", "c) instance", "d) method", "c");
        easyLevelQuestions[7] =new EasyLevelQuestions(8,"Which of the variables memory allocated in stack", "a) static", "b) instance", "c) method", "d) none of the above", "a");
        easyLevelQuestions[8] =new EasyLevelQuestions(9,"Who's job is to collect reference less objects in heap memory", "a) collectors", "b) memory refresh", "c) garbage collector", "d) garbage refresh", "c");
        easyLevelQuestions[9] =new EasyLevelQuestions(10,"Which operator is used to create Object?", "a) new", "b) create", "c) object", "d) init", "a");

        return easyLevelQuestions;
    }

    public MediumLevelQuestions[] prepareMedQuestions(){

        MediumLevelQuestions[] mediumLevelQuestions = new MediumLevelQuestions[10];
        mediumLevelQuestions[0] =new MediumLevelQuestions(1,"What is the OOPS concept used for setting and getting data?", "a) Abstraction", "b) Encapsulation", "c) polymorphism", "d) inheritance", "b");
        mediumLevelQuestions[1] =new MediumLevelQuestions(2,"Which of the Variables/methods can call with out reference of Object", "a) method", "b) local", "c) instance", "d) static", "d");
        mediumLevelQuestions[2] =new MediumLevelQuestions(3,"Example of Compile Time Polymorphism?", "a) Method Overloading", "b) Method Overriding", "c) Method Overridden", "d) Method Overloaded", "a");
        mediumLevelQuestions[3] =new MediumLevelQuestions(4,"When ever Object is created by default which Constructor will get invoked?", "a) Parameterized", "b) private", "c) default", "d) public", "c");
        mediumLevelQuestions[4] =new MediumLevelQuestions(5,"More than one Constructor with same name and different parameters is called?", "a) constructor overloading", "b) constructor Overriding", "c) constructor Overridden", "d) constructor Overloaded", "a");
        mediumLevelQuestions[5] =new MediumLevelQuestions(6,"Which method will use to call parent class Constructor", "a) this()", "b) parent()", "c) super()", "d) new()", "c");
        mediumLevelQuestions[6] =new MediumLevelQuestions(7,"Which inheritance type is not supported by Java?", "a) single level", "b) hybrid", "c) multi level", "d) multiple", "d");
        mediumLevelQuestions[7] =new MediumLevelQuestions(8,"Which keyword is used to achieve Inheritance? ", "a) extends", "b) implements", "c) throwed", "d) invoked", "a");
        mediumLevelQuestions[8] =new MediumLevelQuestions(9,"Is String literal memory will store in?", "a) heap", "b) stack&", "c) string constant pool", "d) registry", "c");
        mediumLevelQuestions[9] =new MediumLevelQuestions(10,"Which of the following doesn't participate in Inheritance?", "a) public variables", "b) constructor", "c) methods", "d) constants", "b");

        return mediumLevelQuestions;
    }

    public DifficultLevelQuestions[] prepareDiffQuestions(){

        DifficultLevelQuestions[] difficultLevelQuestions = new DifficultLevelQuestions[10];
        difficultLevelQuestions[0] =new DifficultLevelQuestions(1,"Methods which are present in child class and not available in parent class is called?", "a) overridden", "b) inherited", "c) specialized", "d) overloaded", "c");
        difficultLevelQuestions[1] =new DifficultLevelQuestions(2,"which variables can access from out side of package and if only have parent child relationship?", "a) public", "b) private", "c) default", "d) protected", "d");
        difficultLevelQuestions[2] =new DifficultLevelQuestions(3,"How can we achieve 100% abstraction?", "a) interfaces", "b) abstract class", "c) lambda expression", "d) streams", "a");
        difficultLevelQuestions[3] =new DifficultLevelQuestions(4,"Which of the following is used to initiate Abstract class?", "a) inner class", "b) initialization block", "c) Anonymous class", "d) static block", "c");
        difficultLevelQuestions[4] =new DifficultLevelQuestions(5,"Storing application state is called?", "a) serialization", "b) deserialization", "c) serializable", "d) deserializable", "a");
        difficultLevelQuestions[5] =new DifficultLevelQuestions(6,"How many objects created when we initialized string with new keyword?", "a) 1", "b) 3", "c) 2", "d) 4", "c");
        difficultLevelQuestions[6] =new DifficultLevelQuestions(7,"Which stage is not present in class loader subsystem?", "a) running", "b) linking", "c) initialization", "d) loading", "a");
        difficultLevelQuestions[7] =new DifficultLevelQuestions(8,"Diamond problem can be resolved by using?", "a) interface", "b) abstract class", "c) inner class", "d) static block", "a");
        difficultLevelQuestions[8] =new DifficultLevelQuestions(9,"Which of the block will execute before constructor executing?", "a) inner class", "b) abstract class", "c) initialization block", "d) Anonymous class", "c");
        difficultLevelQuestions[9] =new DifficultLevelQuestions(10,"Which methods included in interfaces in java8 version?", "a) default", "b) static", "c) concrete", "d) abstract", "a");

        return difficultLevelQuestions;
    }

    public String convertFirstCharToUppercase(String source){
        return source.substring(0, 1).toUpperCase() + source.substring(1);
    }
}
