public class ScoreService {

    PrepService prepService = new PrepService();
    Student student = new Student();
    int easyCorrectAnswers = 0;
    int easyWrongAnswers = 0;
    int medCorrectAnswers = 0;
    int medWrongAnswers = 0;
    int diffCorrectAnswers = 0;
    int diffWrongAnswers = 0;
    String green =  "\033[1;92m";
    String red = "\u001B[31m";
    String blue = "\033[1;94m";
    String reset = "\u001B[0m";
    String cyan = "\u001B[36m";
    String yellow = "\033[1;93m";
    String purple = "\033[1;95m";

    String easy = QuizApp.quizLevels[0];
    String medium = QuizApp.quizLevels[1];
    String difficult = QuizApp.quizLevels[2];

    public Score calcRealScoreEasy(int qId, String correctAnswer, String quizLevel, Score score)
    {
        if(quizLevel.equalsIgnoreCase(easy))
        {
            EasyQuestions[] easyQuests = prepService.prepEasyQuests();
            String actualCorrectAns = easyQuests[qId-1].getAnswer();

            if (actualCorrectAns.equalsIgnoreCase(correctAnswer))
            {
                easyCorrectAnswers++;
                score.setCorrectAnsEasy(easyCorrectAnswers);
                System.out.println(green + "Correct Answer" + reset);
            }
            else
            {
                easyWrongAnswers++;
                score.setWrongAnsEasy(easyWrongAnswers);

                System.out.println(red + "Wrong Answer" + reset);
                System.out.println(green + "Correct Answer is: " + actualCorrectAns + reset);
            }
        }
        int totalScore = calcScore(easyCorrectAnswers, easyWrongAnswers);
        score.setEasyScore(totalScore);
        student.setEasyPlayed(true);

        return score;
    }

    public Score calcRealScoreMed(int qId, String correctAns, String quizLevel, Score score)
    {
        if(quizLevel.equalsIgnoreCase(medium))
        {
            MedQuestions[] medQuests = prepService.prepMedQuests();
            String actualCorrectAns = medQuests[qId-1].getAnswer();

            if (actualCorrectAns.equalsIgnoreCase(correctAns))
            {
                medCorrectAnswers++;
                score.setCorrectAnsMed(medCorrectAnswers);
                System.out.println(green + "Correct Answer" + reset);
            }
            else
            {
                medWrongAnswers++;
                score.setWrongAnsMed(medWrongAnswers);

                System.out.println(red + "Wrong Answer" + reset);
                System.out.println(green + "Correct Answer is: " + actualCorrectAns + reset);
            }
        }
        int totalScore = calcScore(medCorrectAnswers, medWrongAnswers);
        score.setMedScore(totalScore);
        student.setMedPlayed(true);

        return score;
    }

    public Score calcRealScoreDiff(int qId, String correctAns, String quizLevel, Score score)
    {
        if(quizLevel.equalsIgnoreCase(difficult))
        {
            DiffQuestions[] diffQuests = prepService.prepDiffQuests();
            String actualCorrectAns = diffQuests[qId-1].getAnswer();

            if (actualCorrectAns.equalsIgnoreCase(correctAns))
            {
                diffCorrectAnswers++;
                score.setCorrectAnsDiff(diffCorrectAnswers);
                System.out.println(green + "Correct Answer" + reset);
            }
            else
            {
                diffWrongAnswers++;
                score.setWrongAnsDiff(diffWrongAnswers);

                System.out.println(red + "Wrong Answer" + reset);
                System.out.println(green + "Correct Answer is: " + actualCorrectAns + reset);
            }
        }
        int totalScore = calcScore(diffCorrectAnswers, diffWrongAnswers);
        score.setDiffScore(totalScore);
        student.setDiffPlayed(true);

        return score;
    }


    private int calcScore(int correctAnswers, int wrongAnswers){
        return (correctAnswers*2) - (wrongAnswers);
    }

    public void calcFinalScore(String name, Score score)
    {
        System.out.println(cyan + "***********************************************************************");
        System.out.println(blue + "Summary of the all levels of Quiz you played");
        int finalScore = score.getEasyScore() + score.getMedScore() + score.getDiffScore();

        if(student.isEasyPlayed() && student.isMedPlayed() && student.isDiffPlayed())
        {
            if(finalScore == 30 && score.getSkippedQuestsEasy() == 0 &&
                    score.getSkippedQuestsMed() == 0 && score.getSkippedQuestsDiff()== 0)
            {
                System.out.println(yellow + name + " you have answered all questions correctly. " +
                        "Total Score is " + finalScore + "/30" + reset);
                System.out.println(green + "You have successfully completed all levels of Quiz");
                System.out.println(purple + "Congratulations!!! you are awarded by Blaze Warrior!!" + reset);
            }
            else{
                System.out.println(yellow + "Well played " + name + " your total score is " + finalScore + reset);
            }
        }
        else{
            System.out.println(yellow + "Well played " + name + " your total score is " + finalScore + reset);
            System.out.println(purple + " " +  reset);
        }

    }
}
