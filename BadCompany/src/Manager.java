import java.math.BigDecimal;

public class Manager implements Employee {
    private static int idInc = 1;
    private String id;
    private String sm = "sm";
    private BigDecimal salary; // фиксированная часть зарплаты

    private BigDecimal bonus = new BigDecimal("0.05"); // бонус в виде 5% от заработанных для компании денег
    private BigDecimal earnForCompany; // сумма, заработанная менеджером для компании
    private Company company;

    public Manager(Company company) {
        this.id = sm.concat(String.valueOf(idInc++));
        this.company = company;
        this.salary = company.generateRandomBigDecimalFromRange(BigDecimal.valueOf(40000.0), BigDecimal.valueOf(60000.0));
        this.earnForCompany = company.generateRandomBigDecimalFromRange(BigDecimal.valueOf(150000.0), BigDecimal.valueOf(200000.0));
    }

    @Override
    public BigDecimal getMonthSalary() {
        return salary.add(earnForCompany.multiply(bonus));
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public BigDecimal getFixSalary() {
        return salary;
    }

    public BigDecimal getEarnForCompany() {
        return earnForCompany;
    }

}
