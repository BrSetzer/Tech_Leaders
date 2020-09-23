import java.math.BigDecimal;

public class Operator implements Employee {
    private static int idInc = 1;
    private String id;
    private String op = "op";
    private BigDecimal FixSalary;
    private Company company;

    public Operator(Company company) {
        this.id = op.concat(String.valueOf(idInc++));
        this.company = company;
        this.FixSalary = company.generateRandomBigDecimalFromRange(BigDecimal.valueOf(25000.0), BigDecimal.valueOf(35000.0));
    }


    @Override
    public BigDecimal getMonthSalary() {
        return FixSalary;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public BigDecimal getFixSalary() {
        return FixSalary;
    }
}

