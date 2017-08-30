package com.test;

import com.dao.impl.StudentDaoImpl;
import com.domain.Student;

public class TestHello {
    public static void main(String[] args) {
        System.out.println("Hello world in JAVA");
        StudentDaoImpl sdi = new StudentDaoImpl();
        Student s = new Student();
        sdi.addStudent(s);
    }
}
