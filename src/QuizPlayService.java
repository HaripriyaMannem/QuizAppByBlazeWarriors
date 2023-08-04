import java.util.Arrays;
import java.util.Random;
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
        System.out.println("Please enter which level"+ QuizApp.YELLOW_BOLD_BRIGHT + Arrays.toString(QuizApp.quizLevels) + RoleService.ANSI_RESET+" of Quiz do want to play:");
        Scanner sc = new Scanner(System.in);
        String level = sc.nextLine().trim();
        student.setQuizLevel(level);
        System.out.println("Enter " + QuizApp.YELLOW_BOLD_BRIGHT + "Y/N"+ RoleService.ANSI_RESET  +" to play Quiz level: " + quizPreparationService.convertFirstCharToUppercase(student.getQuizLevel()));
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
            System.out.println(QuizApp.PURPLE_BOLD_BRIGHT+ quizPreparationService.convertFirstCharToUppercase(student.getName())+", hope you had a great time. Thanks for visiting QuizApp by Blaze Warriors!!!"+ RoleService.ANSI_RESET);
        }
        System.out.println("Do you want to play again "+ QuizApp.YELLOW_BOLD_BRIGHT + "Y/N"+ RoleService.ANSI_RESET);
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
        //EasyLevelQuestions[] ShuffledeasyLevelQuestions=  shuffleEasyArrayElems(easyLevelQuestions);
        int i=0;
        int noOfSkippedQuestion = 0;
        int noOfTimedOutQuestion = 0;
        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Time Limit is set for each Question is: 7sec" + RoleService.ANSI_RESET);
            for(EasyLevelQuestions q : easyLevelQuestions){
                    System.out.println("---------------------------------------------");
                    long startTime = System.currentTimeMillis();
                    System.out.println("Question " + (i+1) + " : ");
                    System.out.println(q.getQuestion());
                    System.out.println(q.getOpt1());
                    System.out.println(q.getOpt2());
                    System.out.println(q.getOpt3());
                    System.out.println(q.getOpt4());
                    System.out.println( "Do you want to skip the Question: " +QuizApp.YELLOW_BOLD_BRIGHT + "Y/N" + RoleService.ANSI_RESET);
                    Scanner sc = new Scanner(System.in);
                    String skip = sc.nextLine().trim();
                    if(skip.equalsIgnoreCase("n")){
                        System.out.println(QuizApp.BLUE_BOLD_BRIGHT + "Enter the right answer:" + RoleService.ANSI_RESET);
                        String answer = sc.nextLine().trim();
                        long endTime = System.currentTimeMillis();
                        long finalTimeInMs = (endTime- startTime);
                        long finalTimeInSec = (finalTimeInMs/1000)%60;
                        if(finalTimeInSec <= 7){
                            score = quizScoreService.calcRealTimeScoreForEasyLevel(q.getId(), answer, QuizApp.quizLevels[0]);
                            System.out.println(QuizApp.YELLOW_BOLD_BRIGHT + "Your current score is:" +  score.getEasyLevelQuizScore() + RoleService.ANSI_RESET);
                        } else{
                            noOfTimedOutQuestion++;
                            score.setNoOfTimedOutQuestionsEasyLevel(noOfTimedOutQuestion);
                            System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Sorry,time out. Time taken to answer this Question is: "+  finalTimeInSec  +"s" + RoleService.ANSI_RESET);
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
        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Time Limit is set for each Question is: 7sec" + RoleService.ANSI_RESET);
            for(MediumLevelQuestions q : mediumLevelQuestions){
                System.out.println("---------------------------------------------");
                long startTime = System.currentTimeMillis();
                System.out.println("Question " + (i+1) + " : ");
                System.out.println(q.getQuestion());
                System.out.println(q.getOpt1());
                System.out.println(q.getOpt2());
                System.out.println(q.getOpt3());
                System.out.println(q.getOpt4());
                System.out.println( "Do you want to skip the Question: " +QuizApp.YELLOW_BOLD_BRIGHT + "Y/N" + RoleService.ANSI_RESET);
                Scanner sc = new Scanner(System.in);
                String skip = sc.nextLine().trim();
                if(skip.equalsIgnoreCase("n")){
                    System.out.println(QuizApp.BLUE_BOLD_BRIGHT + "Enter the right answer:" + RoleService.ANSI_RESET);
                    String answer = sc.nextLine().trim();
                    long endTime = System.currentTimeMillis();
                    long finalTimeInMs = (endTime- startTime);
                    long finalTimeInSec = (finalTimeInMs/1000)%60;
                    if(finalTimeInSec <= 7){
                        score = quizScoreService.calcRealTimeScoreForMedLevel(q.getId(), answer, QuizApp.quizLevels[1]);
                        System.out.println(QuizApp.YELLOW_BOLD_BRIGHT + "Your current score is:" + score.getMedLevelQuizScore());
                    }else{
                        noOfTimedOutQuestion++;
                        score.setNoOfTimedOutQuestionsEasyLevel(noOfTimedOutQuestion);
                        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Sorry,time out. Time taken to answer this Question is: "+ finalTimeInSec +"s" +RoleService.ANSI_RESET);
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
        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Time Limit is set for each Question is: 7sec" + RoleService.ANSI_RESET);
        for(DifficultLevelQuestions q : difficultLevelQuestions){
            System.out.println("---------------------------------------------");
            long startTime = System.currentTimeMillis();
            System.out.println("Question " + (i+1) + " : ");
            System.out.println(q.getQuestion());
            System.out.println(q.getOpt1());
            System.out.println(q.getOpt2());
            System.out.println(q.getOpt3());
            System.out.println(q.getOpt4());

            System.out.println( "Do you want to skip the Question: " +QuizApp.YELLOW_BOLD_BRIGHT + "Y/N" + RoleService.ANSI_RESET);
            Scanner sc = new Scanner(System.in);
            String skip = sc.nextLine().trim();
            if(skip.equalsIgnoreCase("n")){
                System.out.println(QuizApp.BLUE_BOLD_BRIGHT + "Enter the right answer:" + RoleService.ANSI_RESET);
                String answer = sc.nextLine().trim();
                long endTime = System.currentTimeMillis();
                long finalTimeInMs = (endTime- startTime);
                long finalTimeInSec = (finalTimeInMs/1000)%60;
                if(finalTimeInSec <= 7) {
                    score = quizScoreService.calcRealTimeScoreForDiffLevel(q.getId(), answer, QuizApp.quizLevels[2]);
                    System.out.println(QuizApp.YELLOW_BOLD_BRIGHT + "Your current score is:" + score.getDiffLevelQuizScore());
                } else{
                    noOfTimedOutQuestion++;
                    score.setNoOfTimedOutQuestionsDiffLevel(noOfTimedOutQuestion);
                    System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Sorry,time out. Time taken to answer this Question is: "+ finalTimeInSec +"s" + RoleService.ANSI_RESET);
                }
            } else{
                noOfSkippedQuestion++;
                score.setNoOfSkippedQuestionsDiffLevel(noOfSkippedQuestion);
            }
            i++;
        }
    }

    private void displaySummaryOfEasyLevelQuiz(){
        System.out.println("**************************************************************");
        System.out.println(RoleService.ANSI_CYAN + "Summary of the Quiz you played:" + RoleService.ANSI_RESET);
        System.out.println(QuizApp.GREEN_BOLD_BRIGHT + "Total Correct Answers: " + score.getNoOfCorrectAnswersEasyLevel() + RoleService.ANSI_RESET);
        System.out.println(RoleService.ANSI_RED + "Total Wrong Answers: " + score.getNoOfWrongAnswersEasyLevel() + RoleService.ANSI_RESET);
        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Total Skipped Questions: " + score.getNoOfSkippedQuestionsEasyLevel());
        System.out.println("Total Timed Out Questions: " + score.getNoOfTimedOutQuestionsEasyLevel() + RoleService.ANSI_RESET);
        System.out.println(QuizApp.BLUE_BOLD_BRIGHT + "Total Score: " + score.getEasyLevelQuizScore() + RoleService.ANSI_RESET);
    }

    private void displaySummaryOfMedLevelQuiz(){
        System.out.println("**************************************************************");
        System.out.println(RoleService.ANSI_CYAN + "Summary of the Quiz you played:" + RoleService.ANSI_RESET);
        System.out.println(QuizApp.GREEN_BOLD_BRIGHT + "Total Correct Answers: " + score.getNoOfCorrectAnswersMedLevel());
        System.out.println(RoleService.ANSI_RED + "Total Wrong Answers: " + score.getNoOfWrongAnswersMedLevel());
        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Total Skipped Questions: " + score.getNoOfSkippedQuestionsMedLevel());
        System.out.println("Total Timed Out Questions: " + score.getNoOfTimedOutQuestionsMedLevel());
        System.out.println(QuizApp.BLUE_BOLD_BRIGHT +"Total Score: " + score.getMedLevelQuizScore()/10 + RoleService.ANSI_RESET);
    }

    private void displaySummaryOfDiffLevelQuiz(){
        System.out.println("**************************************************************");
        System.out.println(RoleService.ANSI_CYAN + "Summary of the Quiz you played:" + RoleService.ANSI_RESET);
        System.out.println(QuizApp.GREEN_BOLD_BRIGHT +"Total Correct Answers: " + score.getNoOfCorrectAnswersDiffLevel());
        System.out.println(RoleService.ANSI_RED + "Total Wrong Answers: " + score.getNoOfWrongAnswersDiffLevel());
        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT +"Total Skipped Questions: " + score.getNoOfSkippedQuestionsDiffLevel());
        System.out.println("Total Timed Out Questions: " + score.getNoOfTimedOutQuestionsDiffLevel());
        System.out.println(QuizApp.BLUE_BOLD_BRIGHT +"Total Score: " + score.getDiffLevelQuizScore());
    }

    private EasyLevelQuestions[] shuffleEasyArrayElems(EasyLevelQuestions[] easyLevelQuestions){
        EasyLevelQuestions[] easyLevelQuestions1 = new EasyLevelQuestions[5];
        Random random = new Random();
        int noOfrndmElem = 5;
        for (int i = 0; i < noOfrndmElem; i++) {
            int rndmIndx = random.nextInt(easyLevelQuestions.length);
            EasyLevelQuestions rndmElem = easyLevelQuestions[rndmIndx];
            easyLevelQuestions1[rndmIndx] = rndmElem;
        }
        System.out.println("shuffled array: " + Arrays.toString(easyLevelQuestions1));
        return easyLevelQuestions1;
    }

}
