package com.ssm.pojo;


import java.io.Serializable;

public class Major implements Serializable {

  private long id;
  private String majorName;
  private String majorTime;
  private String majorDate;
  private long majorType;
  private long majorDelete;
 public Major(){}
  public Major(long id, String majorName, String majorTime, String majorDate, long majorType, long majorDelete) {
    this.id = id;
    this.majorName = majorName;
    this.majorTime = majorTime;
    this.majorDate = majorDate;
    this.majorType = majorType;
    this.majorDelete = majorDelete;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public String getMajorTime() {
    return majorTime;
  }

  public void setMajorTime(String majorTime) {
    this.majorTime = majorTime;
  }

  public String getMajorDate() {
    return majorDate;
  }

  public void setMajorDate(String majorDate) {
    this.majorDate = majorDate;
  }

  public long getMajorType() {
    return majorType;
  }

  public void setMajorType(long majorType) {
    this.majorType = majorType;
  }

  public long getMajorDelete() {
    return majorDelete;
  }

  public void setMajorDelete(long majorDelete) {
    this.majorDelete = majorDelete;
  }

  @Override
  public String toString() {
    return "Major{" +
            "id=" + id +
            ", majorName='" + majorName + '\'' +
            ", majorTime='" + majorTime + '\'' +
            ", majorDate='" + majorDate + '\'' +
            ", majorType=" + majorType +
            ", majorDelete=" + majorDelete +
            '}';
  }
}
