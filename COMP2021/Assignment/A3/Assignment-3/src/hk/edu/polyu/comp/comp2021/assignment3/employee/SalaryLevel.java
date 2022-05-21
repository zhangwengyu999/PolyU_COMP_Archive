package hk.edu.polyu.comp.comp2021.assignment3.employee;
// OOP_A3-1 Made by ZHANG Wengyu(21098431d)
/**
 * Levels of salary.
 */
enum SalaryLevel {
    ENTRY(1), JUNIOR(1.25), SENIOR(1.5), EXECUTIVE(2);

    // Add missing code here.
    private final double level; // store the levels
    private SalaryLevel(double level){this.level = level;} // constructor to set the level
    public double getScale(){return level;} // get the salary scale
}// OOP_A3-1 Made by ZHANG Wengyu(21098431d)