public class QuizScoreService {

    QuizPreparationService quizPreparationService = new QuizPreparationService();
    Score score = new Score();
    Student student = new Student();
    int noOfEasyCorrectAnswers = 0;
    int noOfEasyWrongAnswers = 0;
    int noOfMedCorrectAnswers = 0;
    int noOfMedWrongAnswers = 0;
    int noOfDiffCorrectAnswers = 0;
    int noOfDiffWrongAnswers = 0;

    public Score calcRealTimeScoreForEasyLevel(int questionId, String correctAnswer, String quizLevel){


        int easyLevelQuizScore;
        if(quizLevel.equalsIgnoreCase(QuizApp.quizLevels[0])) {
            EasyLevelQuestions[] easyLevelQuestions = quizPreparationService.prepareEasyQuestions();
            String actualCorrectAnswer = easyLevelQuestions[questionId-1].getAnswer();
            if (actualCorrectAnswer.equalsIgnoreCase(correctAnswer)) {
                noOfEasyCorrectAnswers++;
                score.setNoOfCorrectAnswersEasyLevel(noOfEasyCorrectAnswers);
                System.out.println(QuizApp.GREEN_BOLD_BRIGHT + "Correct Answer" + RoleService.ANSI_RESET);
            }
            else {
                noOfEasyWrongAnswers++;
                score.setNoOfWrongAnswersEasyLevel(noOfEasyWrongAnswers);
                System.out.println(RoleService.ANSI_RED + "Wrong Answer" + RoleService.ANSI_RESET);
                System.out.println(QuizApp.GREEN_BOLD_BRIGHT + "Correct Answer is: " + actualCorrectAnswer + RoleService.ANSI_RESET);
            }
        }
        easyLevelQuizScore = calculateScore(noOfEasyCorrectAnswers, noOfEasyWrongAnswers);
        score.setEasyLevelQuizScore(easyLevelQuizScore);
        student.setEasyLevelPlayed(true);
        return score;
    }

    public Score calcRealTimeScoreForMedLevel(int questionId, String correctAnswer, String quizLevel){

        int medLevelQuizScore;
        if(quizLevel.equalsIgnoreCase(QuizApp.quizLevels[1])) {
            MediumLevelQuestions[] mediumLevelQuestions = quizPreparationService.prepareMedQuestions();
            String actualCorrectAnswer = mediumLevelQuestions[questionId-1].getAnswer();
            if (actualCorrectAnswer.equalsIgnoreCase(correctAnswer)) {
                noOfMedCorrectAnswers++;
                score.setNoOfCorrectAnswersMedLevel(noOfMedCorrectAnswers);
                System.out.println(QuizApp.GREEN_BOLD_BRIGHT + "Correct Answer" + RoleService.ANSI_RESET);
            }
            else {
                noOfMedWrongAnswers++;
                score.setNoOfWrongAnswersMedLevel(noOfMedWrongAnswers);
                System.out.println(RoleService.ANSI_RED + "Wrong Answer" + RoleService.ANSI_RESET);
                System.out.println(QuizApp.GREEN_BOLD_BRIGHT + "Correct Answer is: " + actualCorrectAnswer + RoleService.ANSI_RESET);
            }
        }
        medLevelQuizScore = calculateScore(noOfMedCorrectAnswers, noOfMedWrongAnswers);
        score.setMedLevelQuizScore(medLevelQuizScore);
        student.setMedLevelPlayed(true);
        return score;
    }

    public Score calcRealTimeScoreForDiffLevel(int questionId, String correctAnswer, String quizLevel){

        int diffLevelQuizScore;
        if(quizLevel.equalsIgnoreCase(QuizApp.quizLevels[2])) {
            DifficultLevelQuestions[] difficultLevelQuestions = quizPreparationService.prepareDiffQuestions();
            String actualCorrectAnswer = difficultLevelQuestions[questionId-1].getAnswer();
            if (actualCorrectAnswer.equalsIgnoreCase(correctAnswer)) {
                noOfDiffCorrectAnswers++;
                score.setNoOfCorrectAnswersDiffLevel(noOfDiffCorrectAnswers);
                System.out.println(QuizApp.GREEN_BOLD_BRIGHT + "Correct Answer" + RoleService.ANSI_RESET);
            }
            else {
                noOfDiffWrongAnswers++;
                score.setNoOfWrongAnswersDiffLevel(noOfDiffWrongAnswers);
                System.out.println(RoleService.ANSI_RED + "Wrong Answer" + RoleService.ANSI_RESET);
                System.out.println(QuizApp.GREEN_BOLD_BRIGHT + "Correct Answer is: " + actualCorrectAnswer + RoleService.ANSI_RESET);
            }
        }
        diffLevelQuizScore = calculateScore(noOfDiffCorrectAnswers, noOfDiffWrongAnswers);
        score.setDiffLevelQuizScore(diffLevelQuizScore);
        student.setDiffLevelPlayed(true);
        return score;
    }


    private int calculateScore(int correctAnswers, int wrongAnswers){
        return (correctAnswers*2) - (wrongAnswers);
    }

    public void calculateFinalCumulativeScore(String name){
        System.out.println(RoleService.ANSI_CYAN  + "***********************************************************************");
        System.out.println(QuizApp.BLUE_BOLD_BRIGHT + "Summary of the all levels of Quiz you played" + RoleService.ANSI_RESET);
        int finalScore = score.getEasyLevelQuizScore() + score.getMedLevelQuizScore() + score.getDiffLevelQuizScore();
        if(student.isEasyLevelPlayed() && student.isMedLevelPlayed() && student.isDiffLevelPlayed()){
            if(finalScore == 30 && score.getNoOfSkippedQuestionsEasyLevel() == 0 && score.getNoOfSkippedQuestionsMedLevel() == 0 && score.getNoOfSkippedQuestionsDiffLevel()== 0){
                System.out.println(QuizApp.YELLOW_BOLD_BRIGHT + quizPreparationService.convertFirstCharToUppercase(name) + " you have answered all questions correctly. Total Score is " + finalScore + "/30" + RoleService.ANSI_RESET);
                System.out.println(QuizApp.GREEN_BOLD_BRIGHT + "Hurray!!! you have successfully completed all levels of Quiz" +  RoleService.ANSI_RESET);
                System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + "Congratulations!!! you are awarded by Blaze Warrior!!" +  RoleService.ANSI_RESET);
            }
        }
        else{
            System.out.println(QuizApp.YELLOW_BOLD_BRIGHT + "Well played " + quizPreparationService.convertFirstCharToUppercase(name) + " your total score is " + finalScore + "/30"+ RoleService.ANSI_RESET);
            System.out.println(QuizApp.PURPLE_BOLD_BRIGHT + " " +  RoleService.ANSI_RESET);
        }
        System.out.println(RoleService.ANSI_CYAN  +"***************************************************************************" + RoleService.ANSI_RESET);
    }
}
