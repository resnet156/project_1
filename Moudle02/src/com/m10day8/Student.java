package com.m10day8;

/**
 * @ClassName Student
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/10/13 22:38
 * @Version 1.0
 **/
public class Student {
    private int flowID;
    private int type;
    private String IDCard;
    private String examCard;
    private String name;
    private String localtion;
    private int grade;

    public Student() {
    }

    public Student(int flowID, int type, String IDCard, String examCard, String name, String localtion, int grade) {
        this.flowID = flowID;
        this.type = type;
        this.IDCard = IDCard;
        this.examCard = examCard;
        this.name = name;
        this.localtion = localtion;
        this.grade = grade;
    }

    @Override
    public String toString() {
        System.out.println("================查询结果================");
        return info();
    }

    private String info() {
        return "流水号：" + flowID + "\n四级/六级：" + type + "\n身份证号：" + IDCard + "\n准考证号：" + examCard +
                "\n学生姓名：" + name + "\n区域" + localtion + "\n成绩: " + grade;
    }

    public int getFlowID() {
        return flowID;
    }

    public void setFlowID(int flowID) {
        this.flowID = flowID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getExamCard() {
        return examCard;
    }

    public void setExamCard(String examCard) {
        this.examCard = examCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
