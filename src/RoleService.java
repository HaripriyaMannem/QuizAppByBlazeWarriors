import java.util.Scanner;

public class RoleService {

    String cyan = "\u001B[36m";
    String red = "\u001B[31m";
    String reset = "\u001B[0m";
    public RoleService()
    {
        System.out.println(cyan + "*********************************************");
        System.out.println("Welcome to the Quiz App By Blaze Warriors!!!");
        System.out.println("*********************************************" + reset);

    }

    public void validateRole()
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
            prepService.prepareQuiz(trainer);

        }
        else if(role.equalsIgnoreCase("Student") || role.equalsIgnoreCase("2"))
        {
            PlayService playService = new PlayService();
            playService.playQuiz();

        }
        else
        {
            System.out.println(red +
                    "QuizApp By Blaze Warriors not supporting given role please try again, Thank you!" +
                    reset);
            validateRole();
        }
    }
}
