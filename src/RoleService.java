import java.util.Scanner;

public class RoleService {

    String cyan = "\u001B[36m";
    String red = "\u001B[31m";
    String purple = "\033[1;95m";
    String reset = "\u001B[0m";
    public RoleService(boolean isStartQuiz)
    {
        if(isStartQuiz)
        {
            System.out.println(cyan + "*********************************************");
            System.out.println("Welcome to the Quiz App By Blaze Warriors!!!");
            System.out.println("*********************************************" + reset);
        }
    }

    public void validateRole() throws InvalidRoleException
    {
        System.out.println("Please enter your Role: 1) Trainer");
        System.out.println("                        2) Student");

        Scanner sc = new Scanner(System.in);
        String role = sc.nextLine().trim();

        if(role.equalsIgnoreCase("Trainer") || role.equalsIgnoreCase("1"))
        {
            Trainer trainer = new Trainer();
            System.out.println("Please enter your Name:");
            String name = sc.nextLine().trim();
            trainer.setName(name);

            System.out.println("Please enter Quiz Title:");
            String title = sc.nextLine().trim();
            trainer.setTitle(title);

            PrepService prepService = new PrepService();
            try
            {
                prepService.prepareQuiz(trainer);
            }
            catch (InvalidLevelException e)
            {
                try
                {
                    prepService.prepareQuiz(trainer);
                }
                catch(InvalidLevelException exc)
                {
                    System.out.println(purple + "you have exceeded the maximum number of attempts, " +
                            "please try again after some time.");
                }
            }

        }
        else if(role.equalsIgnoreCase("Student") || role.equalsIgnoreCase("2"))
        {
            PlayService playService = new PlayService();
            try
            {
                playService.playQuiz();
            }
            catch (InvalidLevelException e)
            {
                try
                {
                    playService.playQuiz();
                }
                catch(InvalidLevelException exc)
                {
                    System.out.println(purple + "you have exceeded the maximum number of attempts, " +
                            "please try again after some time.");
                }
            }
        }
        else
        {
            InvalidRoleException invalidRoleExc =  new InvalidRoleException("Given role is not valid, please try again!!");
            System.out.println(red + invalidRoleExc.getMessage() + reset);
            throw invalidRoleExc;
        }
    }
}
