import java.util.Arrays;
import java.util.Scanner;

public class QuizPlayService {
    Student student = new Student();
    QuizPreparationService quizPreparationService = new QuizPreparationService();
    QuizScoreService quizScoreService = new QuizScoreService();
    Score score = new Score();

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
        System.out.println("Press Y/N to play Quiz level: " + student.getQuizLevel());
        status = sc.nextLine().trim();
        if(status.equalsIgnoreCase("Y") && (student.getQuizLevel().equalsIgnoreCase(QuizApp.quizLevels[0]) || student.getQuizLevel().contentEquals(QuizApp.quizLevels[0]))){
            EasyLevelQuestions[] easyLevelQuestions =  quizPreparationService.prepareEasyQuestions();
            playEasyLevelQuiz(easyLevelQuestions);
            displaySummaryOfEasyLevelQuiz();
        }
        else if(status.equalsIgnoreCase("Y") && (student.getQuizLevel().equalsIgnoreCase(QuizApp.quizLevels[1])  || student.getQuizLevel().contentEquals(QuizApp.quizLevels[1]))){
            MediumLevelQuestions[] mediumLevelQuestions = quizPreparationService.prepareMedQuestions();
            playMedLevelQuiz(mediumLevelQuestions);
            displaySummaryOfMedLevelQuiz();
        }
        else if(status.equalsIgnoreCase("Y") && (student.getQuizLevel().equalsIgnoreCase(QuizApp.quizLevels[2]) || student.getQuizLevel().contentEquals(QuizApp.quizLevels[2]))){
            DifficultLevelQuestions[] difficultLevelQuestions = quizPreparationService.prepareDiffQuestions();
            playDiffLevelQuiz(difficultLevelQuestions);
            displaySummaryOfDiffLevelQuiz();
        }
        else{
            System.out.println(student.getName()+", hope you had a great time. Thanks for playing QuizApp by Blaze Warriors!!!");
        }
        System.out.println("Do you want to play again Y/N:");
        status = sc.nextLine().trim();
        if(status.equalsIgnoreCase("Y"))
        {
            playQuiz();
        }
        else{
            quizScoreService.calculateFinalCumulativeScore();
            System.out.println(student.getName()+", hope you had a great time. Thanks for playing QuizApp by Blaze Warriors!!!");
        }

    }

    private void playEasyLevelQuiz(EasyLevelQuestions[] easyLevelQuestions){
        int i=0;
        int noOfSkippedQuestion = 0;
        for(EasyLevelQuestions q : easyLevelQuestions){
            System.out.println("Question " + (i+1) + " : ");
            System.out.println(q.getQuestion());
            System.out.println(q.getOpt1());
            System.out.println(q.getOpt2());
            System.out.println(q.getOpt3());
            System.out.println(q.getOpt4());

            System.out.println("Do you want to skip the Question: Y/N");
            Scanner sc = new Scanner(System.in);
            String skip = sc.nextLine().trim();
            if(skip.equalsIgnoreCase("n")){
                System.out.println("Enter the right answer:");
                int score = quizScoreService.calcRealTimeScoreForEasyLevel(q.getId(), sc.nextLine().trim(), QuizApp.quizLevels[0]);
                System.out.println("Your current score is:" + score);

            } else{
                noOfSkippedQuestion++;
                score.setNoOfSkippedQuestionsEasyLevel(noOfSkippedQuestion);
            }
            i++;
        }
    }

    private void playMedLevelQuiz(MediumLevelQuestions[] mediumLevelQuestions){
         int i=0;
         int noOfSkippedQuestion = 0;
            for(MediumLevelQuestions q : mediumLevelQuestions){
                System.out.println("Question " + (i+1) + " : ");
                System.out.println(q.getQuestion());
                System.out.println(q.getOpt1());
                System.out.println(q.getOpt2());
                System.out.println(q.getOpt3());
                System.out.println(q.getOpt4());

                System.out.println("Enter the right answer:");
                Scanner sc = new Scanner(System.in);
                String skip = sc.nextLine().trim();
                if(skip.equalsIgnoreCase("n")){
                    System.out.println("Enter the right answer:");
                    int score = quizScoreService.calcRealTimeScoreForMedLevel(q.getId(), sc.nextLine().trim(), QuizApp.quizLevels[1]);
                    System.out.println("Your current score is:" + score);
                } else{
                    noOfSkippedQuestion++;
                    score.setNoOfSkippedQuestionsMedLevel(noOfSkippedQuestion);
                }
                i++;
            }
    }

    private void playDiffLevelQuiz(DifficultLevelQuestions[] difficultLevelQuestions){
        int i=0;
        int noOfSkippedQuestion = 0;
        for(DifficultLevelQuestions q : difficultLevelQuestions){
            System.out.println("Question " + (i+1) + " : ");
            System.out.println(q.getQuestion());
            System.out.println(q.getOpt1());
            System.out.println(q.getOpt2());
            System.out.println(q.getOpt3());
            System.out.println(q.getOpt4());

            System.out.println("Enter the right answer:");
            Scanner sc = new Scanner(System.in);
            String skip = sc.nextLine().trim();
            if(skip.equalsIgnoreCase("n")){
                System.out.println("Enter the right answer:");
                int score = quizScoreService.calcRealTimeScoreForDiffLevel(q.getId(), sc.nextLine().trim(), QuizApp.quizLevels[2]);
                System.out.println("Your current score is:" + score);
            } else{
                noOfSkippedQuestion++;
                score.setNoOfSkippedQuestionsDiffLevel(noOfSkippedQuestion);
            }
            i++;
        }
    }

    private void displaySummaryOfEasyLevelQuiz(){
        System.out.println("Summary of the Quiz you played:");
        System.out.println("Total Correct Answers: " + score.getNoOfCorrectAnswersEasyLevel());
        System.out.println("Total Wrong Answers: " + score.getNoOfWrongAnswersEasyLevel());
        System.out.println("Total Skipped Questions: " + score.getNoOfSkippedQuestionsEasyLevel());
        System.out.println("Total Score: " + score.getEasyLevelQuizScore());
    }

    private void displaySummaryOfMedLevelQuiz(){
        System.out.println("Summary of the Quiz you played:");
        System.out.println("Total Correct Answers: " + score.getNoOfCorrectAnswersMedLevel());
        System.out.println("Total Wrong Answers: " + score.getNoOfWrongAnswersMedLevel());
        System.out.println("Total Skipped Questions: " + score.getNoOfSkippedQuestionsMedLevel());
        System.out.println("Total Score: " + score.getMedLevelQuizScore());
    }

    private void displaySummaryOfDiffLevelQuiz(){
        System.out.println("Summary of the Quiz you played:");
        System.out.println("Total Correct Answers: " + score.getNoOfCorrectAnswersDiffLevel());
        System.out.println("Total Wrong Answers: " + score.getNoOfWrongAnswersDiffLevel());
        System.out.println("Total Skipped Questions: " + score.getNoOfSkippedQuestionsDiffLevel());
        System.out.println("Total Score: " + score.getDiffLevelQuizScore());
    }

}
