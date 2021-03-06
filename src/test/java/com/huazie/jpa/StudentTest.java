package com.huazie.jpa;

import com.huazie.jpa.entity.Student;
import com.huazie.jpa.service.interfaces.IStudentSV;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class StudentTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testInsertStudent() {
        try {
            IStudentSV studentSV = (IStudentSV) applicationContext.getBean("studentSV");
            Student student = new Student();
            student.setStuName("张三3");
            student.setStuAge(18);
            student.setStuSex(1);
            student.setStuState(1);
            studentSV.save(student);

            student = new Student();
            student.setStuName("李四3");
            student.setStuAge(19);
            student.setStuSex(1);
            student.setStuState(1);
            studentSV.save(student);

            student = new Student();
            student.setStuName("王二麻子3");
            student.setStuAge(20);
            student.setStuSex(1);
            student.setStuState(1);
            studentSV.save(student);

        } catch (Exception e) {
            LOGGER.error("Exception : ", e);
        }
    }

    @Test
    public void testStudentUpdate() {
        try {
            IStudentSV studentSV = (IStudentSV) applicationContext.getBean("studentSV");
            // 根据主键查询学生信息
            Student student = studentSV.query(3L);
            LOGGER.error("Before : {}", student);
            student.setStuName("王三麻子");
            student.setStuAge(19);
            // 更新学生信息
            studentSV.update(student);
            // 最后再根据主键查询学生信息
            student = studentSV.query(3L);
            LOGGER.error("After : {}", student);
        } catch (Exception e) {
            LOGGER.error("Exception : ", e);
        }
    }

    @Test
    public void testStudentDelete() {
        try {
            IStudentSV studentSV = (IStudentSV) applicationContext.getBean("studentSV");
            // 根据主键查询学生信息
            Student student = studentSV.query(3L);
            LOGGER.error("Before : {}", student);
            // 删除学生信息(里面会先去将学生实体信息查出来，然后再删除)
            studentSV.remove(3L);
            // 最后再根据主键查询学生信息
            student = studentSV.query(3L);
            LOGGER.error("After : {}", student);
        } catch (Exception e) {
            LOGGER.error("Exception : ", e);
        }
    }

    @Test
    public void testStudentQueryPage() {
        try {

            IStudentSV studentSV = (IStudentSV) applicationContext.getBean("studentSV");
            List<Student> studentList = studentSV.getStudentList("张三", 1, 18, 20, 1, 5);
            LOGGER.debug("Student List = {}", studentList);
        } catch (Exception e) {
            LOGGER.error("Exception : ", e);
        }
    }

    @Test
    public void testStudentQueryCount() {
        try {
            IStudentSV studentSV = (IStudentSV) applicationContext.getBean("studentSV");
            long count = studentSV.getStudentCount("张三", 1, 18, 20);
            LOGGER.debug("Student Count = {}", count);
        } catch (Exception e) {
            LOGGER.error("Exception : ", e);
        }
    }

}
