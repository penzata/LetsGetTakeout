import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

            try (Scanner input = new Scanner(System.in)) {
                System.out.print("Hello, please type your name: ");
                String customerName = input.nextLine();
                System.out.print("What is your starting money: ");
                int money = input.nextInt();
                Customer customer = new Customer(customerName, money);
                TakeOutSimulator takeOutSimulator = new TakeOutSimulator(customer, input);
                takeOutSimulator.startTakeOutSimulator();
            } catch (InputMismatchException ex) {
                System.err.println("Not correctly entered money value, please try again!");
            }
        }
    }
