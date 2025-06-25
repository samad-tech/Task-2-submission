package task2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Student Record Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }

    private static void addStudent() {
        int id = 0;
        while (true) {
            System.out.print("Enter ID (number): ");
            String input = scanner.nextLine();
            try {
                id = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a number.");
            }
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        double marks = 0;
        while (true) {
            System.out.print("Enter Marks (number): ");
            String input = scanner.nextLine();
            try {
                marks = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks. Please enter a number.");
            }
        }

        students.add(new Student(id, name, marks));
        System.out.println("✅ Student added successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠ No records found.");
        } else {
            System.out.println("\n--- Student List ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        String input = scanner.nextLine();
        int id;

        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Update aborted.");
            return;
        }

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.print("Enter new name: ");
                s.setName(scanner.nextLine());

                System.out.print("Enter new marks: ");
                try {
                    s.setMarks(Double.parseDouble(scanner.nextLine()));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid marks. Update skipped.");
                    return;
                }

                System.out.println("✅ Student updated successfully.");
                return;
            }
        }

        System.out.println("⚠ Student not found.");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String input = scanner.nextLine();
        int id;

        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Delete aborted.");
            return;
        }

        boolean removed = students.removeIf(s -> s.getId() == id);

        if (removed) {
            System.out.println("✅ Student deleted successfully.");
        } else {
            System.out.println("⚠ Student not found.");
        }
    }
}
