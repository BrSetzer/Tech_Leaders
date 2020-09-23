import java.math.BigDecimal;
import java.util.*;

public class Company {

    List<Employee> employees = new ArrayList<>();
    private BigDecimal income = new BigDecimal(0.0); // доход компании

    public Company() {
        this.employees = employees;
        this.income = income;
    }


    public int hire(Employee e) { // найм одного сотрудника
        if (e instanceof Manager) {
            income = income.add(((Manager) e).getEarnForCompany()); // Manager заработал для компании денег
        }
        employees.add(e);

        return employees.size();
    }

    public void hireAll(List<Employee> list) {
        for (Employee e : list) {
            hire(e);
        }
    }

    public BigDecimal getIncome() {
        return income;
    }

    public int fire(String id) { // увольнение работника
        for (Iterator<Employee> itr = employees.iterator(); itr.hasNext(); ) {
            Employee e = itr.next();
            if (e.getId().equals(id)) {
                itr.remove();
                if (e instanceof Manager) {
                    income = income.subtract(((Manager) e).getEarnForCompany()); // после увольнения работника компания
                }                                                           // теряет деньги, которые тот приносил
            }
        }
        return employees.size();
    }

    public int fireAll(String beginningId, int workers) {
        List<Employee> loosers = new ArrayList<>();

        for (Iterator<Employee> itr = employees.iterator(); itr.hasNext(); ) {
            Employee e = itr.next();
            if (e.getId().substring(0, 2).equals(beginningId)) {
                loosers.add(e);
                itr.remove();
                if (e instanceof Manager) {
                    income = income.subtract(((Manager) e).getEarnForCompany());
                }
                if (loosers.size() == workers) {
                    break;
                }
            }
        }
        loosers.removeAll(loosers);
        return employees.size();
    }

    public BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);

    }

    public List<Employee> getTopSalaryStaff(int count) { // сотрудники, отсортированные в порядке убывания заработной платы
        employees.sort(Comparator.reverseOrder());
        if (count > employees.size() || count <= 0) {
            System.out.println("Incorrect count");
            count = 0;
        }
        return employees.subList(0, count);
    }

    public List<Employee> getLowestSalaryStaff(int count) { // сотрудники, отсортированные в порядке возрастания заработной платы
        employees.sort(Comparator.naturalOrder());
        if (count > employees.size() || count <= 0) {
            System.out.println("Incorrect count");
            count = 0;
        }
        return employees.subList(0, count);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

}
