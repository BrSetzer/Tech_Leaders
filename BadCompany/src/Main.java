import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Company abidas = new Company();
        List<Employee> topManagers = new ArrayList<>();
        List<Employee> Managers = new ArrayList<>();
        List<Employee> Operators = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            topManagers.add(new TopManager(abidas));
        }

        for (int i = 0; i < 80; i++) {
            Managers.add(new Manager(abidas));
        }

        for (int i = 0; i < 180; i++) {
            Operators.add(new Operator(abidas));
        }

        abidas.hireAll(topManagers);
        abidas.hireAll(Managers);
        abidas.hireAll(Operators);

        System.out.println(abidas.getIncome() + " - прибыль");

        System.out.println(abidas.getEmployees().size() + " " + "работников");
        System.out.println();

        abidas.getTopSalaryStaff(10);

        System.out.println("От большего к меньшему");

        for (int i = 0; i < 10; i++) {
            System.out.println(abidas.getEmployees().get(i).getMonthSalary());
        }


        System.out.println();


        abidas.getLowestSalaryStaff(30);

        System.out.println("От меньшего к большему");

        for (int i = 0; i < 30; i++) {
            System.out.println(abidas.getEmployees().get(i).getMonthSalary());
        }

        abidas.fireAll("op", 135);

        System.out.println(abidas.getIncome() + " - прибыль");

        System.out.println();
        System.out.println(abidas.getEmployees().size() + " " + "работников");

        System.out.println();


        abidas.getTopSalaryStaff(1000);

        abidas.getTopSalaryStaff(15);

        System.out.println("От большего к меньшему");

        for (int i = 0; i < 15; i++) {
            System.out.println(abidas.getEmployees().get(i).getMonthSalary());
        }

        System.out.println();


        abidas.getLowestSalaryStaff(30);

        abidas.getLowestSalaryStaff(5000);

        System.out.println("От меньшего к большему");

        for (int i = 0; i < 30; i++) {
            System.out.println(abidas.getEmployees().get(i).getMonthSalary());
        }

    }
}