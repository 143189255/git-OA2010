package com.ssm.pojo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;

public class Student implements Serializable {

  private long id;
  private String no;
  private String name;
  private String sex;
  private String birthday;
  private String cardno;
  private String school;
  private String education;
  private long classId;
  private long flag;
  private String email;
  private String qq;
  private String phone;
  private String createdate;
  private String photo;
  private long del;


private TClass tClass;
public Student(){}
    public TClass gettClass() {
        return tClass;
    }

  public Student(long id, String no, String name, String sex, String birthday, String cardno, String school, String education, long classId, long flag, String email, String qq, String phone, String createdate, String photo, long del, String nickname, TClass tClass) {
    this.id = id;
    this.no = no;
    this.name = name;
    this.sex = sex;
    this.birthday = birthday;
    this.cardno = cardno;
    this.school = school;
    this.education = education;
    this.classId = classId;
    this.flag = flag;
    this.email = email;
    this.qq = qq;
    this.phone = phone;
    this.createdate = createdate;
    this.photo = photo;
    this.del = del;

    this.tClass = tClass;
  }



  public void settClass(TClass tClass) {
        this.tClass = tClass;
    }

    public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getCardno() {
    return cardno;
  }

  public void setCardno(String cardno) {
    this.cardno = cardno;
  }


  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }


  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }


  public long getClassId() {
    return classId;
  }

  public void setClassId(long classId) {
    this.classId = classId;
  }


  public long getFlag() {
    return flag;
  }

  public void setFlag(long flag) {
    this.flag = flag;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getCreatedate() {
    return createdate;
  }

  public void setCreatedate(String createdate) {
    this.createdate = createdate;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }


  public long getDel() {
    return del;
  }

  public void setDel(long del) {
    this.del = del;
  }

  @Override
  public String toString() {
    return "Student{" +
            "id=" + id +
            ", no='" + no + '\'' +
            ", name='" + name + '\'' +
            ", sex='" + sex + '\'' +
            ", birthday='" + birthday + '\'' +
            ", cardno='" + cardno + '\'' +
            ", school='" + school + '\'' +
            ", education='" + education + '\'' +
            ", classId=" + classId +
            ", flag=" + flag +
            ", email='" + email + '\'' +
            ", qq='" + qq + '\'' +
            ", phone='" + phone + '\'' +
            ", createdate='" + createdate + '\'' +
            ", photo='" + photo + '\'' +
            ", del=" + del +
            ", tClass=" + tClass +
            '}';
  }
}
