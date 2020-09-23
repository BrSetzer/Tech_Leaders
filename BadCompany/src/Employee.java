import java.math.BigDecimal;

public interface Employee extends Comparable<Employee> {
    BigDecimal getMonthSalary();

    String getId();

    BigDecimal getFixSalary();

    @Override

    default int compareTo(Employee o) {

        return getMonthSalary().compareTo(o.getMonthSalary());

    }
}
