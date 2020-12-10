package com.ssm.pojo;


import java.io.Serializable;
import java.sql.Date;

public class Loginlog implements Serializable {

  private long id;
  private String ip;
  private String no;
  private Date createtime;
  private String location;

public Loginlog(){}
  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
      this.createtime = createtime;
  }

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Loginlog( String ip, String no, String location) {
    this.ip = ip;
    this.no = no;
    this.location = location;
  }

  @Override
  public String toString() {
    return "Loginlog{" +
            "id=" + id +
            ", ip='" + ip + '\'' +
            ", no='" + no + '\'' +
            ", createtime=" + createtime +
            ", location='" + location + '\'' +
            '}';
  }
}
