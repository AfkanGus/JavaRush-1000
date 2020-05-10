package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University{
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {

    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student forReturn = null;
        for (Student s:students){
            if (s.getAverageGrade()==averageGrade){
                forReturn = s;
            }
        }
        return forReturn;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student forReturn = students.get(0);
        double maxAG = students.get(0).getAverageGrade();
        for (int i = 0; i<students.size();i++){
            if (maxAG<students.get(i).getAverageGrade()){
                forReturn = students.get(i);
                maxAG = students.get(i).getAverageGrade();
            }
        }
        return forReturn;
    }

    public Student getStudentWithMinAverageGrade() {
        Student forReturn = students.get(0);
        double minAG = students.get(0).getAverageGrade();
        for (int i = 0; i<students.size();i++){
            if (minAG>students.get(i).getAverageGrade()){
                forReturn = students.get(i);
                minAG = students.get(i).getAverageGrade();
            }
        }
        return forReturn;
    }

    public void expel(Student student){
        students.remove(student);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}