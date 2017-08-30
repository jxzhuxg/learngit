package com.dao;

import com.domain.Student;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface StudentDao {
    /**
     * 添加学生信息到数据库
     * @param student 封装了要保存的信息的学生对象
     * @return 成功返回ture,否则false
     */
    boolean addStudent (Student student);

    /**
     * 根据准考证号查询学生的信息
     * @param examid 准考证号
     * @return 没有找到返回null
     */
    Student findByExamid(String examid) throws IOException, SAXException, ParserConfigurationException;

    /**
     * 根据姓名删除学生信息
     * @param name 学生姓名
     * @return 删除成功返回true, 删除失败或者学生不存在都返回false
     */
    boolean delStudentByName(String name);
}
