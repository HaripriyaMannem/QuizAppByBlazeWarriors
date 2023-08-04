import java.util.*;

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
        System.out.println("Please enter which level"+ QuizApp.YELLOW_BOLD_BRIGHT + Arrays.toString(QuizApp.quizLevels) + RoleService.ANSI_RESET+" of " + QuizApp.YELLOW_BOLD_BRIGHT + "Java " +  RoleService.ANSI_RESET + "Quiz do want to play :");
        Scanner sc = new Scanner(System.in);
        String level = sc.nextLine().trim();
        student.setQuizLevel(level);
        System.out.println("Enter " + QuizApp.YELLOW_BOLD_BRIGHT + "Y/N"+ RoleService.ANSI_RESET  +" to play Quiz level: " + QuizApp.YELLOW_BOLD_BRIGHT + quizPreparationService.convertFirstCharToUppercase(student.getQuizLevel()) + RoleService.ANSI_RESET);
        status = sc.nextLine().trim();
        if(status.equalsIgnoreCase("Y") && (student.getQuizLevel().equalsIgnoreCase(QuizApp.quizLevels[0]))){
            EasyLevelQuestions[] easyLevelQuestions =  quizPreparationService.prepareEasyQuestions();
            EasyLevelQuestions[] ShuffledEayQuestions=  shuffleEasyArrayElements(easyLevelQuestions);
            playEasyLevelQuiz(ShuffledEayQuestions);
            displaySummaryOfEasyLevelQuiz();
        }
        else if(status.equalsIgnoreCase("Y") && (student.getQuizLevel().equalsIgnoreCase(QuizApp.quizLevels[1]))){
            MediumLevelQuestions[] mediumLevelQuestions = quizPreparationService.prepareMedQuestions();
            MediumLevelQuestions[] ShuffledMedQuestions=  shuffleMedArrayElements(mediumLevelQuestions);
            playMedLevelQuiz(ShuffledMedQuestions);
            displaySummaryOfMedLevelQuiz();
        }
        else if(status.equalsIgnoreCase("Y") && (student.getQuizLevel().equalsIgnoreCase(QuizApp.quizLevels[2]) || student.getQuizLevel().contentEquals(QuizApp.quizLevels[2]))){
            DifficultLevelQuestions[] difficultLevelQuestions = quizPreparationService.prepareDiffQuestions();
            DifficultLevelQuestions[] ShuffledDefQuestions=  shuffleDiffArrayElements(difficultLevelQuestions);
            playDiffLevelQuiz(ShuffledDefQuestions);
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
            quizScoreService.calculateFinalCumulativeScore(student.getName());
            System.out.println(QuizApp.BLUE_BOLD_BRIGHT + quizPreparationService.convertFirstCharToUppercase(student.getName())+", hope you had a great time. Thanks for playing QuizApp by Blaze Warriors!!!" + RoleService.ANSI_RESET);
        }

    }

    private void playEasyLevelQuiz(EasyLevelQuestions[] easyLevelQuestions){

        int i=0;
        int noOfSkippedQuestion = 0;
        int noOfTimedOutQuestion = 0;
        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Time Limit is set for each Question is: 10sec" + RoleService.ANSI_RESET);
            for(EasyLevelQuestions q : easyLevelQuestions){
                    System.out.println("---------------------------------------------");
                    long startTime = System.currentTimeMillis();
                    System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Question " + (i+1) + " : " + RoleService.ANSI_RESET);
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
                        if(finalTimeInSec <= 10){
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
        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Time Limit is set for each Question is: 10sec" + RoleService.ANSI_RESET);
            for(MediumLevelQuestions q : mediumLevelQuestions){
                System.out.println("---------------------------------------------");
                long startTime = System.currentTimeMillis();
                System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Question " + (i+1) + " : " + RoleService.ANSI_RESET);
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
                    if(finalTimeInSec <= 10){
                        score = quizScoreService.calcRealTimeScoreForMedLevel(q.getId(), answer, QuizApp.quizLevels[1]);
                        System.out.println(QuizApp.YELLOW_BOLD_BRIGHT + "Your current score is:" + score.getMedLevelQuizScore() + RoleService.ANSI_RESET);
                    }else{
                        noOfTimedOutQuestion++;
                        score.setNoOfTimedOutQuestionsMedLevel(noOfTimedOutQuestion);
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
        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Time Limit is set for each Question is: 10sec" + RoleService.ANSI_RESET);
        for(DifficultLevelQuestions q : difficultLevelQuestions){
            System.out.println("---------------------------------------------");
            long startTime = System.currentTimeMillis();
            System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Question " + (i+1) + " : " + RoleService.ANSI_RESET);
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
                if(finalTimeInSec <= 10) {
                    score = quizScoreService.calcRealTimeScoreForDiffLevel(q.getId(), answer, QuizApp.quizLevels[2]);
                    System.out.println(QuizApp.YELLOW_BOLD_BRIGHT + "Your current score is:" + score.getDiffLevelQuizScore() + RoleService.ANSI_RESET);
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
        System.out.println(RoleService.ANSI_CYAN +"********************************");
        System.out.println(QuizApp.BLUE_BOLD_BRIGHT + "Summary of the Quiz you played:" + RoleService.ANSI_RESET);
        System.out.println(QuizApp.GREEN_BOLD_BRIGHT + "Total Correct Answers: " + score.getNoOfCorrectAnswersEasyLevel());
        System.out.println(RoleService.ANSI_RED + "Total Wrong Answers: " + score.getNoOfWrongAnswersEasyLevel());
        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Total Skipped Questions: " + score.getNoOfSkippedQuestionsEasyLevel());
        System.out.println("Total Timed Out Questions: " + score.getNoOfTimedOutQuestionsEasyLevel() );
        System.out.println(QuizApp.BLUE_BOLD_BRIGHT + "Total Score: " + score.getEasyLevelQuizScore()+ "/10");
        System.out.println(RoleService.ANSI_CYAN +"********************************" + RoleService.ANSI_RESET);
    }

    private void displaySummaryOfMedLevelQuiz(){
        System.out.println(RoleService.ANSI_CYAN +"********************************");
        System.out.println(QuizApp.BLUE_BOLD_BRIGHT +"Summary of the Quiz you played:" + RoleService.ANSI_RESET);
        System.out.println(QuizApp.GREEN_BOLD_BRIGHT + "Total Correct Answers: " + score.getNoOfCorrectAnswersMedLevel());
        System.out.println(RoleService.ANSI_RED + "Total Wrong Answers: " + score.getNoOfWrongAnswersMedLevel());
        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Total Skipped Questions: " + score.getNoOfSkippedQuestionsMedLevel());
        System.out.println("Total Timed Out Questions: " + score.getNoOfTimedOutQuestionsMedLevel());
        System.out.println(QuizApp.BLUE_BOLD_BRIGHT +"Total Score: " + score.getMedLevelQuizScore()+ "/10");
        System.out.println(RoleService.ANSI_CYAN +"********************************" + RoleService.ANSI_RESET);
    }

    private void displaySummaryOfDiffLevelQuiz(){
        System.out.println(RoleService.ANSI_CYAN +"*******************************");
        System.out.println(QuizApp.BLUE_BOLD_BRIGHT +"Summary of the Quiz you played:" + RoleService.ANSI_RESET);
        System.out.println(QuizApp.GREEN_BOLD_BRIGHT +"Total Correct Answers: " + score.getNoOfCorrectAnswersDiffLevel());
        System.out.println(RoleService.ANSI_RED + "Total Wrong Answers: " + score.getNoOfWrongAnswersDiffLevel());
        System.out.println(QuizApp.PURPLE_BOLD_BRIGHT +"Total Skipped Questions: " + score.getNoOfSkippedQuestionsDiffLevel());
        System.out.println("Total Timed Out Questions: " + score.getNoOfTimedOutQuestionsDiffLevel());
        System.out.println(QuizApp.BLUE_BOLD_BRIGHT +"Total Score: " + score.getDiffLevelQuizScore() + "/10");
        System.out.println(RoleService.ANSI_CYAN +"*******************************" + RoleService.ANSI_RESET);
    }

    private EasyLevelQuestions[] shuffleEasyArrayElements(EasyLevelQuestions[] easyLevelQuestions){
        EasyLevelQuestions[] shuffledEasyLevelQuestions = new EasyLevelQuestions[5];
        Random random = new Random();
        int noOfRandomElements = 5;
        Set<Integer> validate = new HashSet<>();
        int randomIndex = random.nextInt(easyLevelQuestions.length);
        validate.add(randomIndex);
        for (int i = 0; i < noOfRandomElements; i++) {
                while (validate.contains(randomIndex)){
                    randomIndex = random.nextInt(easyLevelQuestions.length);
                }
            validate.add(randomIndex);
            EasyLevelQuestions randomElement = easyLevelQuestions[randomIndex];
            shuffledEasyLevelQuestions[i] = randomElement;
        }
        return shuffledEasyLevelQuestions;
    }

    private MediumLevelQuestions[] shuffleMedArrayElements(MediumLevelQuestions[] mediumLevelQuestions){
        MediumLevelQuestions[] shuffledMedLevelQuestions = new MediumLevelQuestions[5];
        Random random = new Random();
        int noOfRandomElements = 5;
        Set<Integer> validateMedElems = new HashSet<>();
        int  randomIndexofMed = random.nextInt(mediumLevelQuestions.length);
        validateMedElems.add(randomIndexofMed);
        for (int i = 0; i < noOfRandomElements; i++) {
            while (validateMedElems.contains(randomIndexofMed)){
                randomIndexofMed = random.nextInt(mediumLevelQuestions.length);
            }
            validateMedElems.add(randomIndexofMed);
            MediumLevelQuestions randomElement = mediumLevelQuestions[randomIndexofMed];
            shuffledMedLevelQuestions[i] = randomElement;
        }
        return shuffledMedLevelQuestions;
    }

    private DifficultLevelQuestions[] shuffleDiffArrayElements(DifficultLevelQuestions[] difficultLevelQuestions){
        DifficultLevelQuestions[] shuffledDiffLevelQuestions = new DifficultLevelQuestions[5];
        Random random = new Random();
        int noOfRandomElements = 5;
        Set<Integer> validateDiffElems = new HashSet<>();
        int randomIndexDiff = random.nextInt(difficultLevelQuestions.length);
        validateDiffElems.add(randomIndexDiff);;
        for (int i = 0; i < noOfRandomElements; i++) {
            while (validateDiffElems.contains(randomIndexDiff)){
                randomIndexDiff = random.nextInt(difficultLevelQuestions.length);
            }
            validateDiffElems.add(randomIndexDiff);
            DifficultLevelQuestions randomElement = difficultLevelQuestions[randomIndexDiff];
            shuffledDiffLevelQuestions[i] = randomElement;
        }
        return shuffledDiffLevelQuestions;
    }
}
