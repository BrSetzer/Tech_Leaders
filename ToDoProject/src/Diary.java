import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Diary {
    Scanner scanner;
    List<String> items;
    private Pattern addPattern;
    private Pattern editPattern;
    private Pattern deletePattern;

    public Diary() {
        addPattern = Pattern.compile("(ADD) (?:(\\d+) )?(.*)");
        editPattern = Pattern.compile("(EDIT) (\\d+) (.*)");
        deletePattern = Pattern.compile("(DELETE) (\\d+)");
        scanner = new Scanner(System.in);
        items = new ArrayList<>();
    }

    public void work() {
        while (true) {
            System.out.println("Please type your command:");
            String command = scanner.nextLine().trim();

            Matcher addMatcher = addPattern.matcher(command);
            Matcher editMatcher = editPattern.matcher(command);
            Matcher deleteMatcher = deletePattern.matcher(command);

            if (addMatcher.matches()) {
                String indexStr = addMatcher.group(2);
                String item = addMatcher.group(3);
                if (indexStr == null) {
                    items.add(item);
                } else {
                    int index = Integer.parseInt(indexStr);
                    if (index < items.size() && index >= 0) {
                        items.add(index, item);
                    } else {
                        System.out.println("Invalid command");
                        continue;
                    }
                }
            } else if (editMatcher.matches()) {
                String editIndexStr = editMatcher.group(2);
                String editItem = editMatcher.group(3);
                if (editIndexStr == null) {
                    System.out.println("Invalid command : wrong index");
                    continue;
                } else {
                    int editIndex = Integer.parseInt(editIndexStr);
                    if (editIndex < items.size() && editIndex >= 0) {
                        items.set(editIndex, editItem);
                    } else {
                        System.out.println("Invalid command : wrong index");
                        continue;
                    }

                }
            } else if (deleteMatcher.matches()) {
                if (items.isEmpty()) {
                    System.out.println("List is empty");
                    continue;
                }
                String deleteIndexStr = deleteMatcher.group(2);
                if (deleteIndexStr == null) {
                    System.out.println("Invalid command");
                    continue;
                } else {
                    int deleteIndex = Integer.parseInt(deleteIndexStr);
                    if (deleteIndex < items.size() && deleteIndex >= 0) {
                        items.remove(deleteIndex);
                    } else {
                        System.out.println("Invalid command : wrong index");
                        continue;
                    }
                }

            } else if (command.equals("LIST")) {
                if (!items.isEmpty()) {
                    System.out.println(items);
                    continue;
                } else if (items.isEmpty()) {
                    System.out.println("Empty");
                    continue;
                } else {
                    break;
                }
            } else if (command.equals("Close")) {
                break;
            } else {
                System.out.println("Invalid command");
                continue;
            }

            System.out.println("List:");
            System.out.println("-------------------");
            for (String item : items) {
                System.out.println(item);
            }
            System.out.println("-------------------");
        }
    }
}
