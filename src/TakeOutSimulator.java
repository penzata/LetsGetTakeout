import java.util.Scanner;

public class TakeOutSimulator {
    private Customer customer;
    private FoodMenu menu;
    private Scanner input;

    TakeOutSimulator(Customer customer, Scanner input) {
        this.customer = customer;
        this.input = input;
        this.menu = new FoodMenu();
    }

    private <T> T getOutputOnIntUserInput(String userInputPrompt, IntUserInputRetriever<T>  intUserInputRetriever) {
        System.out.print(userInputPrompt);
        while(true) {
            if (input.hasNextInt()) {
                int inputInt = input.nextInt();
                try {
                    return intUserInputRetriever.produceOutputOnIntUserInput(inputInt);
                } catch (IllegalArgumentException ex) {
                    System.err.println(inputInt + " is not a valid input. Try Again!");
                }
            } else {
                System.out.println("Input needs to be an 'int' type! ");
                input.next();
            }
    }
   }

    boolean shouldSimulate() {
        String userPrompt = "Type '1' to proceed with simulation or '0' to stop simulation: ";

        IntUserInputRetriever<Boolean> intUserInputRetriever = selection -> {
            if (selection == 1 && customer.getMoney() >= menu.getLowestCostFood().getPrice()) {
                return true;
            } else if (selection == 0 || customer.getMoney() < menu.getLowestCostFood().getPrice()) {
                System.out.println("You don't have enough money to continue shopping :( - ending simulation...");
                return false;
            } else {
                throw new IllegalArgumentException();
            }
        };
        return Boolean.TRUE.equals(getOutputOnIntUserInput(userPrompt, intUserInputRetriever));
    }

    Food getMenuSelection() {
        String userPrompt = "Choose food from the menu:\n" + menu.toString();

        IntUserInputRetriever<Food> intUserInputRetriever = selection -> {
            if (menu.getFood(selection) != null) {
                return menu.getFood(selection);
            } else {
                System.out.println("The food you selected is not on the menu.");
                throw new IllegalArgumentException();
            }
        };
        return getOutputOnIntUserInput(userPrompt, intUserInputRetriever);
    }

    boolean isStillOrderingFood() {
        String userPrompt = "enter '1' to continue shopping or '0' to checkout: ";

        IntUserInputRetriever<Boolean> intUserInputRetriever = selection -> {
            if (selection == 1) {
                return true;
            } else if (selection == 0) {
                return false;
            } else {
                throw new IllegalArgumentException();
            }
        };
       return Boolean.TRUE.equals(getOutputOnIntUserInput(userPrompt, intUserInputRetriever));
    }

    void checkoutCustomer(ShoppingBag<Food> shoppingBag) {
        System.out.println("\nProcessing payment...\n");
        int remainingMoney = customer.getMoney() - shoppingBag.getTotalPrice();
        customer.setMoney(remainingMoney);
        System.out.print(shoppingBag);
        System.out.printf("Your remaining money: %d. ",remainingMoney);
        System.out.println("Thank you and enjoy your food!");
    }

    void takeOutPrompt() {
        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
        int customerMoneyLeft = customer.getMoney();

        while(isStillOrderingFood()) {
            System.out.printf("\nYou have %d left to spend.", customerMoneyLeft);
            System.out.println("\nToday's Menu Options!");
            Food selectedFood = getMenuSelection();

            if(customerMoneyLeft >= selectedFood.getPrice()) {
                customerMoneyLeft -= selectedFood.getPrice();
                shoppingBag.addItem(selectedFood);
            } else {
                System.out.println("\nYou don't have enough money. Please pick another item or checkout.");
            }
        }
        checkoutCustomer(shoppingBag);
    }

    public void startTakeOutSimulator() {
        System.out.printf("Hello, %s, welcome to my restaurant!\n\n", customer.getName());

        while(shouldSimulate()) {
            takeOutPrompt();
           break;
        }
    }

}