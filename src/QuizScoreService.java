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
                System.out.println("correct answer count: " + score.getNoOfCorrectAnswersEasyLevel());
                System.out.println("Correct Answer");
            }
            else {
                noOfEasyWrongAnswers++;
                score.setNoOfWrongAnswersEasyLevel(noOfEasyWrongAnswers);
                System.out.println("Wrong Answer");
                System.out.println("Correct Answer is: " + actualCorrectAnswer);
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
                System.out.println("Correct Answer");
            }
            else {
                noOfMedWrongAnswers++;
                score.setNoOfWrongAnswersMedLevel(noOfMedWrongAnswers);
                System.out.println("Wrong Answer");
                System.out.println("Correct Answer is: " + actualCorrectAnswer);
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
                System.out.println("Correct Answer");
            }
            else {
                noOfDiffWrongAnswers++;
                score.setNoOfWrongAnswersDiffLevel(noOfDiffWrongAnswers);
                System.out.println("Wrong Answer");
                System.out.println("Correct Answer is: " +actualCorrectAnswer);
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

    public void calculateFinalCumulativeScore(){
        System.out.println("Summary of the all levels of Quiz played by " + student.getName());
        int finalScore = score.getEasyLevelQuizScore() + score.getMedLevelQuizScore() + score.getDiffLevelQuizScore();
        System.out.println("Total Score" + finalScore);
        if(student.isEasyLevelPlayed() && student.isMedLevelPlayed() && student.isDiffLevelPlayed()){
            if(finalScore == 30){
                System.out.println("Hurray!!! "+ student.getName() + " you have successfully completed all levels of Quiz");
                System.out.println("Congratulations "+ student.getName() + " you are awarded by "+ student.getAward());
            }
        }
    }
}
