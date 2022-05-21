package hk.edu.polyu.comp.comp2021.assignment3.employee;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeeTest {

    public static final double DELTA = 1E-6;

    @Test
    public void testSalaryLevel01() {
        assertEquals(SalaryLevel.ENTRY.getScale(), 1, DELTA);
        assertEquals(SalaryLevel.JUNIOR.getScale(), 1.25, DELTA);
        assertEquals(SalaryLevel.SENIOR.getScale(), 1.5, DELTA);
        assertEquals(SalaryLevel.EXECUTIVE.getScale(), 2, DELTA);
    }

    @Test
    public void testEmployee02(){
        Employee employee1 = new Employee("A", SalaryLevel.ENTRY);
        assertEquals(employee1.salary(), 2000, DELTA);

        Employee employee2 = new Employee("B", SalaryLevel.JUNIOR);
        assertEquals(employee2.salary(), 2500, DELTA);
    }

}
