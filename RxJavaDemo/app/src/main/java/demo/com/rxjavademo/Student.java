package demo.com.rxjavademo;

/**
 * Created by ff on 2017/1/20.
 */

public class Student {
    private String name;
    private int age;
    private String sex;

    public Student(String name, int age, String sex, String[] course) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.course = course;
    }

    public String[] getCourse() {
        return course;
    }

    public void setCourse(String[] course) {
        this.course = course;
    }

    private String[] course;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
}
