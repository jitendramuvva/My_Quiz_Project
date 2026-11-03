package Java_Projects;

import java.util.Scanner;

public class Quiz {

	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // 10 Questions
	        String[] questions = {
	            "1. What is the correct extension for Java source files?",
	            "2. Which data type is used to create a variable that should store text?",
	            "3. What is the output of 5 + 3 * 2 in Java?",
	            "4. Which keyword is used to declare a class in Java?",
	            "5. Which operator is used to compare two values for equality?",
	            "6. Which loop is best when the exact number of iterations is known?",
	            "7. Which keyword is used to create an instance of a class?",
	            "8. What is the default value of a boolean variable in Java?",
	            "9. Which memory area in Java holds objects?",
	            "10. Which keyword is used to prevent method overriding?"
	        };

	        // 4 options per question
	        String[][] options = {
	            {"1) .class", "2) .java", "3) .txt", "4) .exe"},
	            {"1) String", "2) txt", "3) myString", "4) string"},
	            {"1) 16", "2) 11", "3) 10", "4) 13"},
	            {"1) struct", "2) class", "3) public", "4) define"},
	            {"1) =", "2) ==", "3) equals", "4) &&"},
	            {"1) for", "2) while", "3) do-while", "4) loop"},
	            {"1) new", "2) create", "3) alloc", "4) make"},
	            {"1) true", "2) null", "3) false", "4) 0"},
	            {"1) Stack", "2) Heap", "3) Queue", "4) Register"},
	            {"1) static", "2) abstract", "3) final", "4) protected"}
	        };

	        // Correct answers (1-4) and cash rewards per question
	        int[] correctAnswers = {2, 1, 2, 2, 2, 1, 1, 3, 2, 3};
	        int[] rewards = {1000, 2000, 3000, 5000, 10000, 20000, 50000, 100000, 200000, 500000};

	        boolean audiencePollUsed = false;
	        boolean fiftyFiftyUsed = false;

	        int currentScore = 0;
	        int safeScore = 0;

	        for (int i = 0; i < 10; i++) {
	            System.out.println("\n--- Question " + (i + 1) + " for " + rewards[i] + " Points ---");
	            System.out.println(questions[i]);

	            // Display standard options
	            for (int j = 0; j < 4; j++) {
	                System.out.println(options[i][j]);
	            }

	            // Option 5 is Lifeline (disabled for Q8, Q9, Q10)
	            boolean canUseLifeline = (i < 7);
	            if (canUseLifeline) {
	                System.out.println("5) Use Lifeline");
	            }

	            System.out.print("Enter your choice: ");
	            int userChoice = scanner.nextInt();

	            // Lifeline processing
	            if (userChoice == 5) {
	                if (!canUseLifeline) {
	                    System.out.println("Lifelines are disabled for questions 8, 9, and 10!");
	                    System.out.print("Enter your choice (1-4): ");
	                    userChoice = scanner.nextInt();
	                } else {
	                    System.out.println("\nAvailable Lifelines:");
	                    if (!audiencePollUsed) {
	                        System.out.println("1) Audience Poll");
	                    }
	                    if (!fiftyFiftyUsed) {
	                        System.out.println("2) 50:50");
	                    }

	                    if (audiencePollUsed && fiftyFiftyUsed) {
	                        System.out.println("No lifelines remaining!");
	                        System.out.print("Enter your choice (1-4): ");
	                        userChoice = scanner.nextInt();
	                    } else {
	                        System.out.print("Select Lifeline: ");
	                        int lifelineChoice = scanner.nextInt();

	                        if (lifelineChoice == 1 && !audiencePollUsed) {
	                            audiencePollUsed = true;
	                            System.out.println("\n[Audience Poll]: Option " + correctAnswers[i] + " has the highest votes (82%)!");
	                            System.out.print("Enter your final answer (1-4): ");
	                            userChoice = scanner.nextInt();
	                        } else if (lifelineChoice == 2 && !fiftyFiftyUsed) {
	                            fiftyFiftyUsed = true;
	                            int correct = correctAnswers[i];
	                            int wrong = (correct == 1) ? 2 : 1;
	                            System.out.println("\n[50:50]: Remaining options are Option " + correct + " and Option " + wrong);
	                            System.out.print("Enter your final answer: ");
	                            userChoice = scanner.nextInt();
	                        } else {
	                            System.out.println("Invalid choice or lifeline already used.");
	                            System.out.print("Enter your answer (1-4): ");
	                            userChoice = scanner.nextInt();
	                        }
	                    }
	                }
	            }

	            // Check correctness
	            if (userChoice == correctAnswers[i]) {
	                currentScore = rewards[i];
	                System.out.println("Correct! Current Points: " + currentScore);

	                // Lock safe zone after question 4 (index 3)
	                if (i == 3) {
	                    safeScore = currentScore;
	                    System.out.println("*** Safe Zone reached! Minimum locked score: " + safeScore + " Points ***");
	                }
	            } else {
	                System.out.println("\nIncorrect answer! Game Over.");
	                int finalScore = (i < 4) ? 0 : safeScore;
	                System.out.println("Total Winnings: " + finalScore + " Points");
	                scanner.close();
	                return;
	            }
	        }

	        System.out.println("\nCongratulations! You answered all questions correctly and won " + currentScore + " Points!");
	        scanner.close();
	    }
	}


