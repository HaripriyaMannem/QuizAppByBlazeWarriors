public class QuizScoreService {

    QuizPreparationService quizPreparationService = new QuizPreparationService();

    Score score = new Score();
    Student student = new Student();


    public int calcRealTimeScoreForEasyLevel(int questionId, String correctAnswer, String quizLevel){

        int noOfCorrectAnswers = 0;
        int noOfWrongAnswers = 0;
        int easyLevelQuizScore;
        if(quizLevel.equalsIgnoreCase(QuizApp.quizLevels[0])) {
            EasyLevelQuestions[] easyLevelQuestions = quizPreparationService.prepareEasyQuestions();
            String actualCorrectAnswer = easyLevelQuestions[questionId-1].getAnswer();
            if (actualCorrectAnswer.equalsIgnoreCase(correctAnswer)) {
                noOfCorrectAnswers++;
                score.setNoOfCorrectAnswersEasyLevel(noOfCorrectAnswers);
                System.out.println("Correct Answer");
            }
            else {
                noOfWrongAnswers++;
                score.setNoOfWrongAnswersEasyLevel(noOfWrongAnswers);
                System.out.println("Wrong Answer");
                System.out.println("Correct Answer is: " + actualCorrectAnswer);
            }
        }
        easyLevelQuizScore = calculateScore(noOfCorrectAnswers, noOfWrongAnswers);
        score.setEasyLevelQuizScore(easyLevelQuizScore);
        student.setEasyLevelPlayed(true);
        return easyLevelQuizScore;
    }

    public int calcRealTimeScoreForMedLevel(int questionId, String correctAnswer, String quizLevel){
        int noOfCorrectAnswers = 0;
        int noOfWrongAnswers = 0;
        int medLevelQuizScore;
        if(quizLevel.equalsIgnoreCase(QuizApp.quizLevels[1])) {
            MediumLevelQuestions[] mediumLevelQuestions = quizPreparationService.prepareMedQuestions();
            String actualCorrectAnswer = mediumLevelQuestions[questionId-1].getAnswer();
            if (actualCorrectAnswer.equalsIgnoreCase(correctAnswer)) {
                noOfCorrectAnswers++;
                score.setNoOfCorrectAnswersMedLevel(noOfCorrectAnswers);
                System.out.println("Correct Answer");
            }
            else {
                noOfWrongAnswers++;
                score.setNoOfWrongAnswersMedLevel(noOfWrongAnswers);
                System.out.println("Wrong Answer");
                System.out.println("Correct Answer is: " + actualCorrectAnswer);
            }
        }
        medLevelQuizScore = calculateScore(noOfCorrectAnswers, noOfWrongAnswers);
        score.setMedLevelQuizScore(medLevelQuizScore);
        student.setMedLevelPlayed(true);
        return medLevelQuizScore;
    }

    public int calcRealTimeScoreForDiffLevel(int questionId, String correctAnswer, String quizLevel){
        int noOfCorrectAnswers = 0;
        int noOfWrongAnswers = 0;
        int diffLevelQuizScore;
        if(quizLevel.equalsIgnoreCase(QuizApp.quizLevels[2])) {
            DifficultLevelQuestions[] difficultLevelQuestions = quizPreparationService.prepareDiffQuestions();
            String actualCorrectAnswer = difficultLevelQuestions[questionId-1].getAnswer();
            if (actualCorrectAnswer.equalsIgnoreCase(correctAnswer)) {
                noOfCorrectAnswers++;
                score.setNoOfCorrectAnswersDiffLevel(noOfCorrectAnswers);
                System.out.println("Correct Answer");
            }
            else {
                noOfWrongAnswers++;
                score.setNoOfWrongAnswersDiffLevel(noOfWrongAnswers);
                System.out.println("Wrong Answer");
                System.out.println("Correct Answer is: " +actualCorrectAnswer);
            }
        }
        diffLevelQuizScore = calculateScore(noOfCorrectAnswers, noOfWrongAnswers);
        score.setDiffLevelQuizScore(diffLevelQuizScore);
        student.setDiffLevelPlayed(true);
        return diffLevelQuizScore;
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
