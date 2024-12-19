import java.util.*;

class Item {
    private int id;
    private String name;
    private int quantity;
    private double price;

    public Item(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item ID: " + id + ", Name: " + name + ", Quantity: " + quantity + ", Price: " + price;
    }
}

public class Main {
    private static Map<Integer, Item> inventory = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. View Items");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                addItem();
            } else if (choice == 2) {
                updateItem();
            } else if (choice == 3) {
                viewItems();
            } else if (choice == 4) {
                deleteItem();
            } else if (choice == 5) {
                System.out.println("Exiting... Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addItem() {
        System.out.print("Enter Item ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (inventory.containsKey(id)) {
            System.out.println("Item with this ID already exists.");
            return;
        }

        System.out.print("Enter Item Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        Item item = new Item(id, name, quantity, price);
        inventory.put(id, item);
        System.out.println("Item added successfully.");
    }

    private static void updateItem() {
        System.out.print("Enter Item ID to update: ");
        int id = scanner.nextInt();

        if (!inventory.containsKey(id)) {
            System.out.println("Item with this ID does not exist.");
            return;
        }

        Item item = inventory.get(id);
        System.out.println("Current Details: " + item);

        System.out.print("Enter new Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter new Price: ");
        double price = scanner.nextDouble();

        item.setQuantity(quantity);
        item.setPrice(price);
        System.out.println("Item updated successfully.");
    }

    private static void viewItems() {
        if (inventory.isEmpty()) {
            System.out.println("No items in inventory.");
        } else {
            System.out.println("\nInventory Items:");
            for (Item item : inventory.values()) {
                System.out.println(item);
            }
        }
    }

    private static void deleteItem() {
        System.out.print("Enter Item ID to delete: ");
        int id = scanner.nextInt();

        if (inventory.remove(id) != null) {
            System.out.println("Item deleted successfully.");
        } else {
            System.out.println("Item with this ID does not exist.");
        }
    }
}
