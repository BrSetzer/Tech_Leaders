import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Contacts {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private HashMap<String, String> cleverBook;

    public Contacts() {
        cleverBook = new HashMap<>();
    }

    private static boolean isNumber(String input) {
        return input.chars().noneMatch(Character::isLetter) && input.chars().anyMatch(Character::isDigit);
    }

    private static boolean isName(String s) {
        return !isNumber(s) && !s.equals("LIST") && !s.isEmpty();
    }

    private static String numberEntering() throws IOException {
        boolean b = false;
        String number = null;
        while (!b) { //Номер вводим пока оно не соответствует isNumber()
            System.out.println("Enter the number: ");
            number = reader.readLine().trim();
            if (isNumber(number))
                b = true;
            else System.out.println("Number is incorrect, try again");
        }
        return number;
    }

    private static String nameEntering() throws IOException {
        boolean b = false;
        String name = null;
        while (!b) {
            System.out.println("Enter the name: ");
            name = reader.readLine().trim();
            if (isName(name))
                b = true;
            else System.out.println("Name is incorrect, try again");
        }
        return name;
    }


    public void work() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String something;
        for (; ; ) {
            System.out.print("Please type: ");
            something = reader.readLine().trim();

            if (something.equals("LIST")) {
                List<String> order = new ArrayList<>();
                for (Map.Entry<String, String> e : cleverBook.entrySet()) {
                    order.add(e.getValue() + " - " + e.getKey());
                }
                Collections.sort(order);
                String all = String.join("\n", order);
                System.out.println(all);
            } else if (something.equals("Close")) {
                break;
            }

            if (!isNumber(something)) { //если введен не Номер, то проверка на Имя
                if (isName(something)) { //если Имя, то поиск имени в HashMap, и выводим все Номера с таким Именем
                    int count = 0; //счетчик, чтобы узнать сколько Номеров у данного Имени
                    for (Map.Entry entry : cleverBook.entrySet()) {
                        if (entry.getValue().equals(something)) {
                            count++;
                            System.out.println("Name: " + something + " Number: " + entry.getKey());
                        }
                    }
                    if (count == 0) { //если Имени не присвоен номер, то сохраняем это имя, и запрашиваем для него новый Номер
                        cleverBook.put(numberEntering(), something);
                    }
                }

            } else {
                if (cleverBook.get(something) == null) {
                    cleverBook.put(something, nameEntering());
                } else {
                    System.out.println("Number: " + something + " Name: " + cleverBook.get(something));
                }
            }
        }
    }
}
