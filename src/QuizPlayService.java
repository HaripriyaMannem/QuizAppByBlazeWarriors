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
        System.out.println("Please enter which level"+ QuizPreparationService.ANSI_YELLOW + Arrays.toString(QuizApp.quizLevels) + RoleService.ANSI_RESET+" of Questions are you going to prepare:");
        Scanner sc = new Scanner(System.in);
        String level = sc.nextLine().trim();
        student.setQuizLevel(level);
        System.out.println("Enter " + QuizPreparationService.ANSI_YELLOW + "Y/N"+ RoleService.ANSI_RESET  +" to play Quiz level: " + quizPreparationService.convertFirstCharToUppercase(student.getQuizLevel()));
        status = sc.nextLine().trim();
        if(status.equalsIgnoreCase("Y") && (student.getQuizLevel().equalsIgnoreCase(QuizApp.quizLevels[0]))){
            EasyLevelQuestions[] easyLevelQuestions =  quizPreparationService.prepareEasyQuestions();
            playEasyLevelQuiz(easyLevelQuestions);
            displaySummaryOfEasyLevelQuiz();
        }
        else if(status.equalsIgnoreCase("Y") && (student.getQuizLevel().equalsIgnoreCase(QuizApp.quizLevels[1]))){
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
            System.out.println(QuizPreparationService.ANSI_PURPLE+ quizPreparationService.convertFirstCharToUppercase(student.getName())+", hope you had a great time. Thanks for visiting QuizApp by Blaze Warriors!!!"+ RoleService.ANSI_RESET);
        }
        System.out.println("Do you want to play again "+ QuizPreparationService.ANSI_YELLOW + "Y/N"+ RoleService.ANSI_RESET);
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
        int noOfTimedOutQuestion = 0;
        System.out.println(QuizPreparationService.ANSI_PURPLE + "Time Limit is set for each Question is: 7sec" + RoleService.ANSI_RESET);
            for(EasyLevelQuestions q : easyLevelQuestions){
                    System.out.println("---------------------------------------------");
                    long startTime = System.currentTimeMillis();
                    System.out.println("Question " + (i+1) + " : ");
                    System.out.println(q.getQuestion());
                    System.out.println(q.getOpt1());
                    System.out.println(q.getOpt2());
                    System.out.println(q.getOpt3());
                    System.out.println(q.getOpt4());
                    System.out.println( "Do you want to skip the Question: " + QuizPreparationService.ANSI_YELLOW + "Y/N" + RoleService.ANSI_RESET);
                    Scanner sc = new Scanner(System.in);
                    String skip = sc.nextLine().trim();
                    if(skip.equalsIgnoreCase("n")){
                        System.out.println(QuizPreparationService.ANSI_BLUE + "Enter the right answer:" + RoleService.ANSI_RESET);
                        String answer = sc.nextLine().trim();
                        long endTime = System.currentTimeMillis();
                        long finalTimeInMs = (endTime- startTime);
                        long finalTimeInSec = (finalTimeInMs/1000)%60;
                        if(finalTimeInSec <= 7){
                            score = quizScoreService.calcRealTimeScoreForEasyLevel(q.getId(), answer, QuizApp.quizLevels[0]);
                            System.out.println(QuizPreparationService.ANSI_YELLOW + "Your current score is:" +  score.getEasyLevelQuizScore() + RoleService.ANSI_RESET);
                        } else{
                            noOfTimedOutQuestion++;
                            score.setNoOfTimedOutQuestionsEasyLevel(noOfTimedOutQuestion);
                            System.out.println("Sorry,time out. Time taken to answer this Question is: "+ QuizPreparationService.ANSI_YELLOW + finalTimeInSec + RoleService.ANSI_RESET +"s");
                        }
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
        int noOfTimedOutQuestion = 0;
            System.out.println("Time Limit is set for each Question is: 7sec");
            for(MediumLevelQuestions q : mediumLevelQuestions){
                long startTime = System.currentTimeMillis();
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
                    String answer = sc.nextLine().trim();
                    long endTime = System.currentTimeMillis();
                    long finalTimeInMs = (endTime- startTime);
                    long finalTimeInSec = (finalTimeInMs/1000)%60;
                    if(finalTimeInSec <= 7){
                        score = quizScoreService.calcRealTimeScoreForMedLevel(q.getId(), answer, QuizApp.quizLevels[1]);
                        System.out.println("Your current score is:" + score.getMedLevelQuizScore());
                    }else{
                        noOfTimedOutQuestion++;
                        score.setNoOfTimedOutQuestionsEasyLevel(noOfTimedOutQuestion);
                        System.out.println("Sorry,time out. Time taken to answer this Question is: "+ finalTimeInSec +"s");
                    }
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
        int noOfTimedOutQuestion = 0;
        System.out.println("Time Limit is set for each Question is: 7sec");
        for(DifficultLevelQuestions q : difficultLevelQuestions){
            long startTime = System.currentTimeMillis();
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
                String answer = sc.nextLine().trim();
                long endTime = System.currentTimeMillis();
                long finalTimeInMs = (endTime- startTime);
                long finalTimeInSec = (finalTimeInMs/1000)%60;
                if(finalTimeInSec <= 7) {
                    score = quizScoreService.calcRealTimeScoreForDiffLevel(q.getId(), answer, QuizApp.quizLevels[2]);
                    System.out.println("Your current score is:" + score.getDiffLevelQuizScore());
                } else{
                    noOfTimedOutQuestion++;
                    score.setNoOfSkippedQuestionsDiffLevel(noOfTimedOutQuestion);
                }
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
        System.out.println("Total Timed Out Questions: " + score.getNoOfTimedOutQuestionsEasyLevel());
        System.out.println("Total Score: " + score.getEasyLevelQuizScore());
    }

    private void displaySummaryOfMedLevelQuiz(){
        System.out.println("Summary of the Quiz you played:");
        System.out.println("Total Correct Answers: " + score.getNoOfCorrectAnswersMedLevel());
        System.out.println("Total Wrong Answers: " + score.getNoOfWrongAnswersMedLevel());
        System.out.println("Total Skipped Questions: " + score.getNoOfSkippedQuestionsMedLevel());
        System.out.println("Total Timed Out Questions: " + score.getNoOfTimedOutQuestionsMedLevel());
        System.out.println("Total Score: " + score.getMedLevelQuizScore());
    }

    private void displaySummaryOfDiffLevelQuiz(){
        System.out.println("Summary of the Quiz you played:");
        System.out.println("Total Correct Answers: " + score.getNoOfCorrectAnswersDiffLevel());
        System.out.println("Total Wrong Answers: " + score.getNoOfWrongAnswersDiffLevel());
        System.out.println("Total Skipped Questions: " + score.getNoOfSkippedQuestionsDiffLevel());
        System.out.println("Total Timed Out Questions: " + score.getNoOfTimedOutQuestionsDiffLevel());
        System.out.println("Total Score: " + score.getDiffLevelQuizScore());
    }

}
