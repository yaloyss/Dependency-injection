package com.yaloys.presentation;
import com.yaloys.data.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import com.yaloys.data.DaggerAppComponent;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        AppComponent appComponent = DaggerAppComponent.create();
        RemindersRepository remindersRepository = appComponent.getRemindersRepository();

        remindersRepository.loadFromFile();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("====================================");
        System.out.println("Welcome to the Reminder App!");
        System.out.println("Choose a command: create, delete, show, update, search, sort, exit");
        System.out.println("====================================");
        while (running) {
            System.out.print(">");
            String command = scanner.nextLine();

            switch (command) {
                case "create":
                    System.out.print("Enter reminder text: ");
                    String text = scanner.nextLine();
                    var reminderDate = remindersRepository.getValidDate(scanner, dateFormatter);
                    remindersRepository.createReminder(text, reminderDate);
                    break;

                case "delete":
                    if (remindersRepository.isEmpty()) {
                        System.out.println("No reminders to delete.");
                        break;
                    }
                    remindersRepository.deleteReminder();
                    break;

                case "show":
                    remindersRepository.showReminders();
                    break;

                case "update":
                    if (remindersRepository.isEmpty()) {
                        System.out.println("No reminders to update.");
                        break;
                    }
                    remindersRepository.updateReminder();
                    break;

                case "search":
                    System.out.println("Search by text or date? (text/date)");
                    String searchType = scanner.nextLine().toLowerCase();

                    if (searchType.equals("text")) {
                        System.out.print("Enter the reminder text to search for: ");
                        String searchText = scanner.nextLine();
                        List<Reminders> foundByText = remindersRepository.searchReminderByText(searchText);
                        if (foundByText.isEmpty())
                        {
                            System.out.println("No matching reminders found.");
                        } else {
                            System.out.println("\nFound reminders:");
                            for (Reminders reminder : foundByText)
                            {
                                System.out.println(reminder);
                            }
                        }
                    } else if (searchType.equals("date")) {
                        LocalDate searchDate = RemindersRepository.getValidDate(scanner, dateFormatter);
                        List<Reminders> foundByDate = remindersRepository.searchReminderByDate(searchDate);
                        if (foundByDate.isEmpty())
                        {
                            System.out.println("No reminders found for this date.");
                        } else {
                            System.out.println("\nReminders on " + searchDate + ":");
                            for (Reminders reminder : foundByDate)
                            {
                                System.out.println(reminder);
                            }
                        }
                    } else {
                        System.out.println("Invalid search type.");
                    }
                    break;

                case "sort":
                    System.out.println("Choose sorting criteria: time, text, or completed");
                    String sortCriteria = scanner.nextLine();
                    remindersRepository.sortReminders(sortCriteria);
                    break;

                case "exit":
                    System.out.println("\nThe program is closing. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("\nInvalid command. Available commands: create, delete, show, update, search, sort, exit");
                    break;
            }
        }
        scanner.close();
        remindersRepository.saveToFile();
    }
}
