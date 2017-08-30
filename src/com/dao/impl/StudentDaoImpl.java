package com.dao.impl;

import com.dao.StudentDao;
import com.domain.Student;
import com.util.JaxpUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StudentDaoImpl implements StudentDao {
    /**
     * 添加学生信息到数据库
     *
     * @param s 封装了要保存的信息的学生对象
     * @return 成功返回ture, 否则false
     */
    @Override
    public boolean addStudent(Student s) {
        boolean result = false;
        // TODO: 2017/8/29  
        //得到Document对象
        try {
            Document doc = JaxpUtil.getDocument();
            //创建<student>:设置属性
            Element studentE = doc.createElement("student");
            studentE.setAttribute("idcard", s.getIdcard());
            studentE.setAttribute("examid", s.getExamid());
            //依次创建节点并设置主体内容
            Element nameE = doc.createElement("name");
            nameE.setTextContent(s.getName());
            Element locationE = doc.createElement("location");
            locationE.setTextContent(s.getLocation());
            Element gradeE = doc.createElement("grade");
            gradeE.setTextContent(s.getGrade()+"");
            //建立与student元素的父子关系
            studentE.appendChild(nameE);
            studentE.appendChild(locationE);
            studentE.appendChild(gradeE);
            //把student挂接到根元素上
            Node rootNode = doc.getElementsByTagName("exam").item(0);
            rootNode.appendChild(studentE);
            //写回xml文档中
            JaxpUtil.write2xml(doc);
            result = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  result;
    }

    /**
     * 根据准考证号查询学生的信息
     *
     * @param examid 准考证号
     * @return 没有找到返回null
     */
    @Override
    public Student findByExamid(String examid) {
        Student s = null;
        try {
            //得到Document对象
            Document doc = JaxpUtil.getDocument();
            //得到所有的<student>元素
            NodeList nl  = doc.getElementsByTagName("student");
            //遍历：判断属性的值和参数的值是否相等
            for(int i=0;i<nl.getLength();i++){
                //相等：读取属性和子元素的文本，封装到Student对象中
                Node node = nl.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element e = (Element)node;
                    if(e.getAttribute("examid").equals(examid)){
                        s = new Student();
                        s.setIdcard(e.getAttribute("idcard"));
                        s.setExamid(examid);
                        s.setName(e.getElementsByTagName("name").item(0).getTextContent());
                        s.setLocation(e.getElementsByTagName("location").item(0).getTextContent());
                        s.setGrade(Float.parseFloat(e.getElementsByTagName("grade").item(0).getTextContent()));
                        break;
                    }
                }
            }
            //返回数据
        } catch (Exception e) {
            throw new RuntimeException(e);//编译时异常--》运行时异常：异常转义；异常链
        }
        return s;
    }

    /**
     * 根据姓名删除学生信息
     *
     * @param name 学生姓名
     * @return 删除成功返回true, 删除失败或者学生不存在都返回false
     */
    @Override
    public boolean delStudentByName(String name) {
        boolean result = false;
        try {
            //得到Document对象
            Document doc = JaxpUtil.getDocument();
            //得到所有的name元素
            NodeList nl = doc.getElementsByTagName("name");
            //遍历：判断元素的文本和参数是否相等
            for(int i=0;i<nl.getLength();i++){
                Node node = nl.item(i);
                if(node.getTextContent().equals(name)){
                    //如果是：爷爷干掉爸爸
                    node.getParentNode().getParentNode().removeChild(node.getParentNode());
                    //写回xml文档
                    JaxpUtil.write2xml(doc);
                    //设置标记为true
                    result = true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);//编译时异常--》运行时异常：异常转义；异常链
        }
        return result;
    }
}
