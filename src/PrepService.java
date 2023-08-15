import java.util.Arrays;
import java.util.Scanner;

public class PrepService {

    String easy = QuizApp.quizLevels[0];
    String medium = QuizApp.quizLevels[1];
    String diff = QuizApp.quizLevels[2];

    String yellow = "\033[1;93m";
    String purple = "\033[1;95m";
    String blue = "\033[1;94m";
    String green =  "\033[1;92m";
    String reset = "\u001B[0m";
    String red = "\u001B[31m";
    String cyan = "\u001B[36m";
    RoleService roleService = new RoleService(false);

    String status;
    public PrepService(boolean isPrepQuiz)
    {
        if(isPrepQuiz)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter " + yellow + "Y/N"+ reset  +" to prepare Questions: " );
            status = sc.nextLine().trim();
        }
    }

    public void prepareQuiz(Trainer trainer) throws InvalidRoleException, InvalidLevelException {
        String quizArr = Arrays.toString(QuizApp.quizLevels);
        String title =  firstCharUppercase(trainer.getTitle());
        String name = firstCharUppercase(trainer.getName());

        String quizLevels;
        String levels;

        Scanner sc = new Scanner(System.in);

        if(status.equalsIgnoreCase("Y"))
        {
            System.out.println("Please enter which level"+ yellow + quizArr + reset +
                    " of Questions are you going to prepare:");
            String level1 = sc.nextLine().trim();
            levels = level1;
            trainer.setLevel(level1);
            //Verifying Quiz Level prepared or not
            quizLevels = quizPrepedOrNot(levels);

            if (!quizLevels.equalsIgnoreCase("NA"))
            {
                //Display Questions According to level Type
                displayQuests(trainer.getLevel());
                showMsg(trainer, title, name);

                System.out.println("Enter " + yellow + "Y/N"+ reset  +" to prepare other level Questions: " );
                String status1 = sc.nextLine().trim();

                if(status1.equalsIgnoreCase("Y"))
                {
                    showLevels(quizLevels);
                    String Level2 = sc.nextLine().trim();
                    levels = levels.concat(Level2);
                    //Verifying Quiz Level prepared or not
                    quizLevels = quizPrepedOrNot(levels);
                    trainer.setLevel(Level2);

                    if (!quizLevels.equalsIgnoreCase("NA"))
                    {
                        //Display Questions According to level Type
                        displayQuests(trainer.getLevel());
                        showMsg(trainer, title, name);

                        System.out.println("Enter " + yellow + "Y/N"+
                                reset  +" to prepare other level Questions: " );
                        String status2 = sc.nextLine().trim();

                        if(status2.equalsIgnoreCase("Y") )
                        {
                            showLevels(quizLevels);
                            String level3 = sc.nextLine().trim();
                            trainer.setLevel(level3);
                            //Verifying Quiz Level prepared or not
                            quizLevels = quizPrepedOrNot(trainer.getLevel());

                            if (!quizLevels.equalsIgnoreCase("NA"))
                            {
                                //Display Questions According to level Type
                                displayQuests(trainer.getLevel());
                                showMsg(trainer, title, name);
                                //Showing Success msg when all level Quiz prep completed
                                successMsg(name);
                                roleService.validateRole();
                            }
                            else
                            {
                                failedMsg();
                            }
                        }
                        else
                        {
                            //show msg when users does want to continue to play
                            displayMsg(name);
                            roleService.validateRole();
                        }
                    }
                    else
                    {
                        failedMsg();
                    }
                }
                else
                {
                    //show msg when users does want to continue to play
                    displayMsg(name);
                    roleService.validateRole();
                }
            }
            else
            {
                failedMsg();
            }
        }
        else
        {
            //show msg when users does want to continue to play
            displayMsg(name);
            roleService.validateRole();
        }
    }

    private String quizPrepedOrNot(String quizLevels)
    {
        String level = "";

        if(!quizLevels.isEmpty())
        {
            switch (quizLevels.toLowerCase())
            {
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


    private void displayQuests(String levelType)
    {
        if(levelType.equalsIgnoreCase(easy))
        {
            EasyQuestions[] easyQuestions = prepEasyQuests();
            for(EasyQuestions question : easyQuestions)
            {
                System.out.println(question.toString());
            }
        }
        else if(levelType.equalsIgnoreCase(medium))
        {
            MedQuestions[] medQuestions = prepMedQuests();
            for(MedQuestions question : medQuestions)
            {
                System.out.println(question.toString());
            }
        }
        else if(levelType.equalsIgnoreCase(diff))
        {
            DiffQuestions[] diffQuestions = prepDiffQuests();
            for(DiffQuestions question : diffQuestions)
            {
                System.out.println(question.toString());
            }
        }
    }

    public EasyQuestions[] prepEasyQuests()
    {
        EasyQuestions[] easyQuestions = new EasyQuestions[10];
        easyQuestions[0] =new EasyQuestions(
                1,"In Java default real number is?",
                "a) float", "b) int", "c) double", "d) long", "c");
        easyQuestions[1] =new EasyQuestions(
                2,"What is the ASCII code of G in Java?",
                "a) 65", "b) 75", "c) 61", "d) 71", "d");
        easyQuestions[2] =new EasyQuestions(
                3,"In Java float number should prefix with f?",
                "a) true", "b) false", "", "", "a");
        easyQuestions[3] =new EasyQuestions(
                4,"What is the correct option for method naming in Java?",
                "a) 1methodName", "b) _1methodName&", "c) _methodName$",
                "d) MethodName", "c");
        easyQuestions[4] =new EasyQuestions(
                5,"Identify Relational Operators in Java?",
                "a) ><", "b) &&", "c) +-", "d) ?:", "a");
        easyQuestions[5] =new EasyQuestions(
                6,"If condition is true or not on which loop at least one time body will execute?",
                "a) for", "b) while", "c) do while", "d) for-each", "c");
        easyQuestions[6] =new EasyQuestions(
                7,"What are the variables which are declared at class level?",
                "a) local", "b) static", "c) instance", "d) method", "c");
        easyQuestions[7] =new EasyQuestions(
                8,"Which of the variables memory allocated in stack",
                "a) static", "b) instance", "c) method", "d) none of the above", "a");
        easyQuestions[8] =new EasyQuestions(
                9,"Who's job is to collect reference less objects in heap memory",
                "a) collectors", "b) memory refresh", "c) garbage collector",
                "d) garbage refresh", "c");
        easyQuestions[9] =new EasyQuestions(10,"Which operator is used to create Object?",
                "a) new", "b) create", "c) object", "d) init", "a");

        return easyQuestions;
    }

    public MedQuestions[] prepMedQuests()
    {
        MedQuestions[] medQuestions = new MedQuestions[10];
        medQuestions[0] =new MedQuestions(
                1,"What is the OOPS concept used for setting and getting data?",
                "a) Abstraction", "b) Encapsulation", "c) polymorphism", "d) inheritance",
                "b");
        medQuestions[1] =new MedQuestions(
                2,"Which of the Variables/methods can call with out reference of Object",
                "a) method", "b) local", "c) instance", "d) static", "d");
        medQuestions[2] =new MedQuestions(3,"Example of Compile Time Polymorphism?",
                "a) Method Overloading", "b) Method Overriding", "c) Method Overridden",
                "d) Method Overloaded", "a");
        medQuestions[3] =new MedQuestions(
                4,"When ever Object is created by default which Constructor will get invoked?",
                "a) Parameterized", "b) private", "c) default", "d) public", "c");
        medQuestions[4] =new MedQuestions(
                5,"More than one Constructor with same name and different parameters is called?",
                "a) constructor overloading", "b) constructor Overriding",
                "c) constructor Overridden", "d) constructor Overloaded", "a");
        medQuestions[5] =new MedQuestions(
                6,"Which method will use to call parent class Constructor",
                "a) this()", "b) parent()", "c) super()", "d) new()", "c");
        medQuestions[6] =new MedQuestions(
                7,"Which inheritance type is not supported by Java?",
                "a) single level", "b) hybrid", "c) multi level", "d) multiple", "d");
        medQuestions[7] =new MedQuestions(
                8,"Which keyword is used to achieve Inheritance? ",
                "a) extends", "b) implements", "c) throwed", "d) invoked", "a");
        medQuestions[8] =new MedQuestions(
                9,"Is String literal memory will store in?",
                "a) heap", "b) stack", "c) string constant pool", "d) registry", "c");
        medQuestions[9] =new MedQuestions(
                10,"Which of the following doesn't participate in Inheritance?",
                "a) public variables", "b) constructor", "c) methods", "d) constants", "b");

        return medQuestions;
    }

    public DiffQuestions[] prepDiffQuests()
    {
        DiffQuestions[] diffQuestions = new DiffQuestions[10];
        diffQuestions[0] =new DiffQuestions(
                1,"Methods which are present in child class and not available in parent class is called?",
                "a) overridden", "b) inherited", "c) specialized", "d) overloaded", "c");
        diffQuestions[1] =new DiffQuestions(
                2,"which variables can access from out side of package and if only have parent child relationship?",
                "a) public", "b) private", "c) default", "d) protected", "d");
        diffQuestions[2] =new DiffQuestions(
                3,"How can we achieve 100% abstraction?",
                "a) interfaces", "b) abstract class", "c) lambda expression",
                "d) streams", "a");
        diffQuestions[3] =new DiffQuestions(
                4,"Which of the following is used to initiate Abstract class?",
                "a) inner class", "b) initialization block", "c) Anonymous class",
                "d) static block", "c");
        diffQuestions[4] =new DiffQuestions(
                5,"Storing application state is called?",
                "a) serialization", "b) deserialization", "c) serializable",
                "d) deserializable", "a");
        diffQuestions[5] =new DiffQuestions(
                6,"How many objects created when we initialized string with new keyword?",
                "a) 1", "b) 3", "c) 2", "d) 4", "c");
        diffQuestions[6] =new DiffQuestions(
                7,"Which stage is not present in class loader subsystem?",
                "a) running", "b) linking", "c) initialization", "d) loading", "a");
        diffQuestions[7] =new DiffQuestions(
                8,"Diamond problem can be resolved by using?",
                "a) interface", "b) abstract class", "c) inner class",
                "d) static block", "a");
        diffQuestions[8] =new DiffQuestions(
                9,"Which of the block will execute before constructor executing?",
                "a) inner class", "b) abstract class", "c) initialization block",
                "d) Anonymous class", "c");
        diffQuestions[9] =new DiffQuestions(
                10,"Which methods included in interfaces in java8 version?",
                "a) default", "b) static", "c) concrete", "d) abstract", "a");

        return diffQuestions;
    }

    public String firstCharUppercase(String source)
    {
        return source.substring(0, 1).toUpperCase() + source.substring(1);
    }

    private void showMsg(Trainer trainer, String title, String name)
    {
        System.out.println(green + trainer.getNoOfQuestions() +
                " Questions Prepared Successfully on " + title + " for " +
                firstCharUppercase(trainer.getLevel()) + " Level by "+ name + "." + reset);
    }
    private void showLevels(String quizLevels)
    {
        System.out.println("Please enter which level "+ yellow  +
                quizLevels + reset +" of Questions to be prepared next:");
    }

    private void successMsg(String name)
    {
        System.out.println(blue + "Hurray!!! "+ name +
                " you have done with the quiz preparation. Good job." + reset);
        System.out.println(cyan + "*****************************************" +
                "*****************************" + reset);
    }

    private void displayMsg(String name)
    {
        System.out.println(purple + name +
                ", hope you had a great time. Thanks for visiting QuizApp by Blaze Warriors!!!" + reset);
        System.out.println(cyan + "*****************************************" +
                "****************************************" + reset);

    }

    private void failedMsg() throws InvalidLevelException
    {
        InvalidLevelException invalidLevelExc =  new InvalidLevelException("Oops!!! " +
                "Quiz Level is not valid please try again!!");
        System.out.println(red + invalidLevelExc.getMessage() + reset);
        throw invalidLevelExc;
    }
}
