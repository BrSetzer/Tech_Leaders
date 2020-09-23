import java.math.BigDecimal;

public class TopManager implements Employee {
    private static int idInc = 1;
    private String id;
    private String tm = "tm";
    private BigDecimal salary; // фиксированная часть зарплаты
    private BigDecimal Bonus = new BigDecimal("1.5"); // бонус в виде 150% от зарплаты
    private Company company;

    public TopManager(Company company) {
        this.id = tm.concat(String.valueOf(idInc++));
        this.company = company;
        this.salary = company.generateRandomBigDecimalFromRange(BigDecimal.valueOf(65000.0), BigDecimal.valueOf(80000.0));

    }

    @Override
    public BigDecimal getMonthSalary() {

        if (company.getIncome().compareTo(BigDecimal.valueOf(10000000.0)) == 1) { // Если доход компании более 10 млн рублей,
            return salary.multiply(Bonus); // то к фиксированной части зарплаты добавляется бонус
        }
        return salary;
    }

    @Override
    public String getId() {
        return id;
    }


    @Override
    public BigDecimal getFixSalary() {
        return salary;
    }
}
