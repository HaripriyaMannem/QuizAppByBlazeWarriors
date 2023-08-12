import java.util.*;

public class PlayService {
    Student student = new Student();
    PrepService prepService = new PrepService();
    ScoreService scoreService = new ScoreService();
    Score score = new Score();

    String yellow = "\033[1;93m";
    String purple = "\033[1;95m";
    String blue = "\033[1;94m";
    String green =  "\033[1;92m";
    String reset = "\u001B[0m";
    String red = "\u001B[31m";
    String cyan = "\u001B[36m";

    String easy = QuizApp.quizLevels[0];
    String medium = QuizApp.quizLevels[1];
    String difficult = QuizApp.quizLevels[2];
    String status;

    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your Name:");
        String name = sc.nextLine().trim();
        student.setName(name);

        System.out.println("Enter " + yellow + "Y/N"+ reset  +" to play quiz: " );
        status = sc.nextLine().trim();
    }

    public void playQuiz() throws InvalidLevelException
    {
        String quizArr = Arrays.toString(QuizApp.quizLevels);
        String name = prepService.firstCharUppercase(student.getName());
        Scanner sc = new Scanner(System.in);

        if(status.equalsIgnoreCase("Y"))
        {
            System.out.println("Please enter which level" + yellow + quizArr + reset + " of " + yellow +
                    "Java " + reset + "Quiz do want to play :");
            String level = sc.nextLine().trim();
            student.setQuizLevel(level);

            if (student.getQuizLevel().equalsIgnoreCase(easy))
            {
                //Fetch and shuffle easy questions
                EasyQuestions[] easyQuests = prepService.prepEasyQuests();
                Questions[] shuffledQuests = shuffleElements(easyQuests);
                //play easy level quiz and show summary
                playEasyQuiz(shuffledQuests);
                summaryOfEasyQuiz();
            }
            else if (student.getQuizLevel().equalsIgnoreCase(medium))
            {
                //Fetch and shuffle medium questions
                MedQuestions[] mediumQuests = prepService.prepMedQuests();
                Questions[] shuffledQuests = shuffleElements(mediumQuests);
                //play medium level quiz and show summary
                playMedQuiz(shuffledQuests);
                summaryOfMedQuiz();
            }
            else if (student.getQuizLevel().equalsIgnoreCase(difficult))
            {
                //Fetch and shuffle difficult questions
                DiffQuestions[] difQuests = prepService.prepDiffQuests();
                Questions[] shuffledQuests = shuffleElements(difQuests);
                //play diff level quiz and show summary
                playDiffQuiz(shuffledQuests);
                summaryOfDiffQuiz();
            }
            else
            {
                InvalidLevelException invalidLevelExc =  new InvalidLevelException("Oops!!! " +
                        "Quiz Level is not valid please try again!!");
                System.out.println(red + invalidLevelExc.getMessage() + reset);
                throw invalidLevelExc;
            }
        }
        else
        {
            System.out.println(purple + name +", hope you had a great time. " +
                    "Thanks for visiting QuizApp by Blaze Warriors!!!"+ reset);

        }

        System.out.println("Do you want to play again "+ yellow + "Y/N"+ reset);
        status = sc.nextLine().trim();

        if(status.equalsIgnoreCase("Y"))
        {
            playQuiz();
        }
        else
        {
            scoreService.calcFinalScore(name, score);
            System.out.println(blue + name +", hope you had a great time. " +
                    "Thanks for playing QuizApp by Blaze Warriors!!!" + reset);
            System.out.println(cyan  +"**********************************************************" +
                    "*****************" + reset);
        }

    }

    private void playEasyQuiz(Questions[] easyQuestions)
    {
        int i=0;
        int noOfSkippedQuestion = 0;
        int noOfTimedOutQuestion = 0;
        System.out.println(purple + "Time Limit is set for each Question is: 10sec" + reset);

            for(Questions q : easyQuestions)
            {
                System.out.println("---------------------------------------------");
                long startTime = System.currentTimeMillis();
                System.out.println(purple + "Question " + (i+1) + " : " + reset);
                System.out.println(q.getQuestion());
                System.out.println(q.getOpt1());
                System.out.println(q.getOpt2());
                System.out.println(q.getOpt3());
                System.out.println(q.getOpt4());

                System.out.println( "Do you want to skip the Question: " + yellow + "Y/N" + reset);
                Scanner sc = new Scanner(System.in);
                String skip = sc.nextLine().trim();

                if(skip.equalsIgnoreCase("n"))
                {
                    System.out.println(blue + "Enter the right answer:" + reset);
                    String answer = sc.nextLine().trim();

                    long endTime = System.currentTimeMillis();
                    long finalTimeInMs = (endTime- startTime);
                    long finalTimeInSec = (finalTimeInMs/1000)%60;

                    if(finalTimeInSec <= 10)
                    {
                        score = scoreService.calcRealScoreEasy(q.getId(), answer, easy, score);
                        System.out.println(yellow + "Your current score is:" +
                                score.getEasyScore() + reset);
                    }
                    else
                    {
                        noOfTimedOutQuestion++;
                        score.setTimedOutQuestsEasy(noOfTimedOutQuestion);
                        System.out.println(purple + "Sorry,time out. " +
                                "Time taken to answer this Question is: "+  finalTimeInSec  +"s" + reset);
                    }
                }
                else
                {
                    noOfSkippedQuestion++;
                    score.setSkippedQuestsEasy(noOfSkippedQuestion);
                }
                i++;
            }
    }

    private void playMedQuiz(Questions[] mediumQuestions)
    {
        int i=0;
        int noOfSkippedQuestion = 0;
        int noOfTimedOutQuestion = 0;
        System.out.println(purple + "Time Limit is set for each Question is: 10sec" + reset);

            for(Questions q : mediumQuestions)
            {
                System.out.println("---------------------------------------------");
                long startTime = System.currentTimeMillis();
                System.out.println(purple + "Question " + (i+1) + " : " + reset);
                System.out.println(q.getQuestion());
                System.out.println(q.getOpt1());
                System.out.println(q.getOpt2());
                System.out.println(q.getOpt3());
                System.out.println(q.getOpt4());

                System.out.println( "Do you want to skip the Question: " +yellow + "Y/N" + reset);
                Scanner sc = new Scanner(System.in);
                String skip = sc.nextLine().trim();

                if(skip.equalsIgnoreCase("n"))
                {
                    System.out.println(blue + "Enter the right answer:" + reset);
                    String answer = sc.nextLine().trim();

                    long endTime = System.currentTimeMillis();
                    long finalTimeInMs = (endTime- startTime);
                    long finalTimeInSec = (finalTimeInMs/1000)%60;

                    if(finalTimeInSec <= 10)
                    {
                        score = scoreService.calcRealScoreMed(q.getId(), answer, medium, score);
                        System.out.println(yellow + "Your current score is:" + score.getMedScore() + reset);
                    }
                    else
                    {
                        noOfTimedOutQuestion++;
                        score.setTimedOutQuestsMed(noOfTimedOutQuestion);
                        System.out.println(purple + "Sorry,time out. " +
                                "Time taken to answer this Question is: "+ finalTimeInSec +"s" +reset);
                    }
                }
                else
                {
                    noOfSkippedQuestion++;
                    score.setSkippedQuestsMed(noOfSkippedQuestion);
                }
                i++;
            }
    }

    private void playDiffQuiz(Questions[] difficultQuestions)
    {
        int i=0;
        int noOfSkippedQuestion = 0;
        int noOfTimedOutQuestion = 0;
        System.out.println(purple + "Time Limit is set for each Question is: 10sec" +reset);

        for(Questions q : difficultQuestions)
        {
            System.out.println("---------------------------------------------");
            long startTime = System.currentTimeMillis();
            System.out.println(purple + "Question " + (i+1) + " : " + reset);
            System.out.println(q.getQuestion());
            System.out.println(q.getOpt1());
            System.out.println(q.getOpt2());
            System.out.println(q.getOpt3());
            System.out.println(q.getOpt4());

            System.out.println( "Do you want to skip the Question: " + yellow + "Y/N" + reset);
            Scanner sc = new Scanner(System.in);
            String skip = sc.nextLine().trim();

            if(skip.equalsIgnoreCase("n"))
            {
                System.out.println(blue + "Enter the right answer:" + reset);
                String answer = sc.nextLine().trim();

                long endTime = System.currentTimeMillis();
                long finalTimeInMs = (endTime- startTime);
                long finalTimeInSec = (finalTimeInMs/1000)%60;

                if(finalTimeInSec <= 10)
                {
                    score = scoreService.calcRealScoreDiff(q.getId(), answer, difficult, score);
                    System.out.println(yellow + "Your current score is:" + score.getDiffScore() + reset);
                }
                else
                {
                    noOfTimedOutQuestion++;
                    score.setTimedOutQuestsDiff(noOfTimedOutQuestion);
                    System.out.println(purple + "Sorry,time out. " +
                            "Time taken to answer this Question is: "+ finalTimeInSec +"s" + reset);
                }
            }
            else
            {
                noOfSkippedQuestion++;
                score.setSkippedQuestsDiff(noOfSkippedQuestion);
            }
            i++;
        }
    }

    private Questions[] shuffleElements(Questions[] questions)
    {
        Questions[] shuffledQuests = new Questions[5];
        Random random = new Random();
        int noOfRandomElements = 5;
        int randomIndex = random.nextInt(questions.length);

        Set<Integer> validate = new HashSet<>();
        validate.add(randomIndex);

        for (int i = 0; i < noOfRandomElements; i++)
        {
                while (validate.contains(randomIndex))
                {
                    randomIndex = random.nextInt(questions.length);
                }

            validate.add(randomIndex);
            Questions randomElement = questions[randomIndex];
            shuffledQuests[i] = randomElement;
        }
        return shuffledQuests;
    }

    private void summaryOfEasyQuiz()
    {
        System.out.println(cyan +"********************************");
        System.out.println(blue + "Summary of the Quiz you played:");

        System.out.println(green + "Total Correct Answers: " + score.getCorrectAnsEasy());
        System.out.println(red + "Total Wrong Answers: " + score.getWrongAnsEasy());
        System.out.println(purple + "Total Skipped Questions: " + score.getSkippedQuestsEasy());
        System.out.println("Total Timed Out Questions: " + score.getTimedOutQuestsEasy() );

        System.out.println(blue + "Total Score: " + score.getEasyScore()+ "/10");
        System.out.println(cyan +"********************************" + reset);
    }

    private void summaryOfMedQuiz()
    {
        System.out.println(cyan +"********************************");
        System.out.println(blue +"Summary of the Quiz you played:");

        System.out.println(green + "Total Correct Answers: " + score.getCorrectAnsMed());
        System.out.println(red + "Total Wrong Answers: " + score.getWrongAnsMed());
        System.out.println(purple + "Total Skipped Questions: " + score.getSkippedQuestsMed());
        System.out.println("Total Timed Out Questions: " + score.getTimedOutQuestsMed());

        System.out.println(blue +"Total Score: " + score.getMedScore()+ "/10");
        System.out.println(cyan +"********************************" + reset);
    }

    private void summaryOfDiffQuiz()
    {
        System.out.println(cyan +"*******************************");
        System.out.println(blue +"Summary of the Quiz you played:");

        System.out.println(green +"Total Correct Answers: " + score.getCorrectAnsDiff());
        System.out.println(red + "Total Wrong Answers: " + score.getWrongAnsDiff());
        System.out.println(purple +"Total Skipped Questions: " + score.getSkippedQuestsDiff());
        System.out.println("Total Timed Out Questions: " + score.getTimedOutQuestsDiff());

        System.out.println(blue +"Total Score: " + score.getDiffScore() + "/10");
        System.out.println(cyan +"*******************************" + reset);
    }
}
