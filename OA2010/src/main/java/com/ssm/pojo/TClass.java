package com.ssm.pojo;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Date;

public class TClass implements Serializable {

  private long id;
  private long majorId;
  @NotNull(message="班级名称不能为空！")
  @Length(min=2,max=10,message = "班级名称仅限于2到10位字符")
  @Pattern(regexp ="^[^ ]+$",message="班级名称不能包含空格")
  private String className;
  private String classDate;
  private String classTime;
  private String classAddress;
  private long classDelete;
public TClass(){}

  public TClass(long id, long majorId, String className, String classDate, String classTime, String classAddress, long classDelete) {
    this.id = id;
    this.majorId = majorId;
    this.className = className;
    this.classDate = classDate;
    this.classTime = classTime;
    this.classAddress = classAddress;
    this.classDelete = classDelete;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getMajorId() {
    return majorId;
  }

  public void setMajorId(long majorId) {
    this.majorId = majorId;
  }


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }


  public String getClassDate() {
    return classDate;
  }

  public void setClassDate(String classDate) {
    this.classDate = classDate;
  }

  public String getClassTime() {
    return classTime;
  }

  public void setClassTime(String classTime) {
    this.classTime = classTime;
  }


  public String getClassAddress() {
    return classAddress;
  }

  public void setClassAddress(String classAddress) {
    this.classAddress = classAddress;
  }


  public long getClassDelete() {
    return classDelete;
  }

  public void setClassDelete(long classDelete) {
    this.classDelete = classDelete;

  }

  @Override
  public String toString() {
    return "TClass{" +
            "id=" + id +
            ", majorId=" + majorId +
            ", className='" + className + '\'' +
            ", classDate='" + classDate + '\'' +
            ", classTime='" + classTime + '\'' +
            ", classAddress='" + classAddress + '\'' +
            ", classDelete=" + classDelete +
            '}';
  }
}
