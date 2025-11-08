import java.util.*;

class User {
    String userId;
    String password;
    String name;

    public User(String userId, String password, String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }
}

public class OnlineExaminationSystem {
    private static Scanner sc = new Scanner(System.in);
    private static boolean isLoggedIn = false;
    private static User currentUser;
    private static long startTime;
    private static long duration = 60 * 1000; // 1 minute timer

    // Dummy database
    private static Map<String, User> users = new HashMap<>();
    private static Map<Integer, String> answers = new HashMap<>();
    private static int score = 0;

    public static void main(String[] args) {
        // Adding sample user
        users.put("rakshika", new User("rakshika", "rakshi123", "Rakshika"));

        System.out.println("=====================================");
        System.out.println("       ONLINE EXAMINATION SYSTEM     ");
        System.out.println("=====================================");

        while (true) {
            if (!isLoggedIn) {
                login();
            } else {
                showMenu();
            }
        }
    }

    private static void login() {
        System.out.print("\nEnter User ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Password: ");
        String pwd = sc.nextLine();

        if (users.containsKey(id) && users.get(id).password.equals(pwd)) {
            currentUser = users.get(id);
            isLoggedIn = true;
            System.out.println("\n✅ Login successful! Welcome, " + currentUser.name + "!");
        } else {
            System.out.println("❌ Invalid credentials. Try again.");
        }
    }

    private static void showMenu() {
        System.out.println("\n===============================");
        System.out.println("  1. Update Profile & Password");
        System.out.println("  2. Start Exam");
        System.out.println("  3. Logout");
        System.out.println("===============================");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                updateProfile();
                break;
            case 2:
                startExam();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("Invalid option! Try again.");
        }
    }

    private static void updateProfile() {
        System.out.println("\n--- Update Profile ---");
        System.out.print("Enter new name: ");
        String newName = sc.nextLine();
        System.out.print("Enter new password: ");
        String newPass = sc.nextLine();

        currentUser.name = newName;
        currentUser.password = newPass;
        System.out.println("✅ Profile updated successfully!");
    }

    private static void startExam() {
        System.out.println("\n--- Exam Started ---");
        System.out.println("You have 1 minute to complete the test!");
        startTime = System.currentTimeMillis();
        score = 0;

        Map<Integer, String> correctAnswers = new HashMap<>();
        correctAnswers.put(1, "B");
        correctAnswers.put(2, "C");
        correctAnswers.put(3, "A");
        correctAnswers.put(4, "C");
        correctAnswers.put(5, "D");

        String[][] questions = {
                {"1. Which language is used for Android Development?", "A. Python", "B. Java", "C. Kotlin", "D. C++"},
                {"2. Which keyword is used to inherit a class in Java?", "A. implement", "B. import", "C. extends", "D. inherits"},
                {"3. What is JVM?", "A. Java Virtual Machine", "B. Java Visual Maker", "C. Java Variable Memory", "D. Java Version Manager"},
                {"4. Which of these is not OOP concept?", "A. Inheritance", "B. Encapsulation", "C. Compilation", "D. Polymorphism"},
                {"5. Which of these is used to handle exceptions?", "A. final", "B. static", "C. private", "D. try-catch"}
        };

        for (int i = 0; i < questions.length; i++) {
            long elapsed = System.currentTimeMillis() - startTime;
            if (elapsed >= duration) {
                System.out.println("\n⏰ Time's up! Auto-submitting your answers...");
                break;
            }

            System.out.println("\n" + questions[i][0]);
            System.out.println(questions[i][1]);
            System.out.println(questions[i][2]);
            System.out.println(questions[i][3]);
            System.out.println(questions[i][4]);
            System.out.print("Your answer: ");
            String ans = sc.next().toUpperCase();
            answers.put(i + 1, ans);

            if (correctAnswers.get(i + 1).equals(ans)) {
                score++;
            }
        }

        System.out.println("\n--- Exam Completed ---");
        System.out.println("✅ You scored " + score + " out of 5.");
    }

    private static void logout() {
        System.out.println("\nLogging out...");
        isLoggedIn = false;
        currentUser = null;
        System.out.println("✅ Logged out successfully.");
    }
}
