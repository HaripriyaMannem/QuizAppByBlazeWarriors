import java.util.Arrays;
import java.util.Scanner;

public class QuizPlayService {
    Student student = new Student();
    QuizPreparationService quizPreparationService = new QuizPreparationService();

    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your Name:");
        String name = sc.nextLine().trim();
        student.setName(name);
    }

    public void playQuiz(){

        String status;
        System.out.println("Please enter which level"+ Arrays.toString(QuizApp.quizLevels) +" of quiz you want to play:");
        Scanner sc = new Scanner(System.in);
        String level = sc.nextLine().trim();
        student.setQuizLevel(level);
        System.out.println("Press Y/N to continue to play Quiz level: " + student.getQuizLevel());
        status = sc.nextLine().trim();
        if(status.equalsIgnoreCase("Y") && student.getQuizLevel().equalsIgnoreCase(QuizApp.quizLevels[0])){
            EasyLevelQuestions[] easyLevelQuestions =  quizPreparationService.prepareEasyQuestions();
            playEasyLevelQuiz(easyLevelQuestions);
        } else{
            System.out.println(student.getName()+", hope you had a great time. Thanks for visiting QuizApp by Blaze Warriors!!!");
        }
        playQuiz();
        if(status.equalsIgnoreCase("Y") && student.getQuizLevel().equalsIgnoreCase(QuizApp.quizLevels[1])){
            MediumLevelQuestions[] mediumLevelQuestions = quizPreparationService.prepareMedQuestions();
            playMedLevelQuiz(mediumLevelQuestions);
        }
        else{
            System.out.println(student.getName()+", hope you had a great time. Thanks for visiting QuizApp by Blaze Warriors!!!");
        }
        playQuiz();
        if(status.equalsIgnoreCase("Y") && student.getQuizLevel().equalsIgnoreCase(QuizApp.quizLevels[2])){
            DifficultLevelQuestions[] difficultLevelQuestions = quizPreparationService.prepareDiffQuestions();
            playDiffLevelQuiz(difficultLevelQuestions);
        }
        else{
            System.out.println(student.getName()+", hope you had a great time. Thanks for visiting QuizApp by Blaze Warriors!!!");
        }
        playQuiz();
    }

    private void playEasyLevelQuiz(EasyLevelQuestions[] easyLevelQuestions){
        int i=0;
        for(EasyLevelQuestions q : easyLevelQuestions){
            System.out.println("Question " + (i+1) + " : ");
            System.out.println(q.getQuestion());
            System.out.println(q.getOpt1());
            System.out.println(q.getOpt2());
            System.out.println(q.getOpt3());
            System.out.println(q.getOpt4());

            System.out.println("type the right answer:");
            Scanner sc = new Scanner(System.in);
            sc.nextLine().trim();
            i++;
        }
    }

    private void playMedLevelQuiz(MediumLevelQuestions[] mediumLevelQuestions){
         int i=0;
            for(MediumLevelQuestions q : mediumLevelQuestions){
                System.out.println("Question " + (i+1) + " : ");
                System.out.println(q.getQuestion());
                System.out.println(q.getOpt1());
                System.out.println(q.getOpt2());
                System.out.println(q.getOpt3());
                System.out.println(q.getOpt4());

                System.out.println("type the right answer:");
                Scanner sc = new Scanner(System.in);
                sc.nextLine().trim();
                i++;
            }
    }

    private void playDiffLevelQuiz(DifficultLevelQuestions[] difficultLevelQuestions){
        int i=0;
        for(DifficultLevelQuestions q : difficultLevelQuestions){
            System.out.println("Question " + (i+1) + " : ");
            System.out.println(q.getQuestion());
            System.out.println(q.getOpt1());
            System.out.println(q.getOpt2());
            System.out.println(q.getOpt3());
            System.out.println(q.getOpt4());

            System.out.println("type the right answer:");
            Scanner sc = new Scanner(System.in);
            sc.nextLine().trim();
            i++;
        }
    }

}
