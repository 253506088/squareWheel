package com.blacktv.shejimoshi.结构型模式.组合模式或部分整体模式;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Employee {
    private String name;
    private String dept;
    private int salary;
    private List<Employee> subordinates;

    public Employee(String name, String dept, int salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public void add(Employee employee){
        if(this.subordinates == null){
            this.subordinates = new ArrayList<>();
        }
        this.subordinates.add(employee);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                '}';
    }
}
