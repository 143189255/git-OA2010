package com.ssm.pojo;


import org.hibernate.validator.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


public class Emp implements Serializable {
  private long id;
  @NotEmpty(message="工号不能为空")
  private String no;
  private String pass;
  @Length(min=2,max=10,message = "姓名仅限于2到10位字符")
  private String name;
  @Min(value = -2,message = "部门不能为空")
  private long did;
  private long flag;
  private String sex;
  @NotEmpty(message="请输入邮箱")
  @Email
  private String email;
  private String qq;
@Pattern(regexp = "^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$",message = "请输入正确的手机号")
  private String phone;
  @NotNull(message="日期不能为空")
  private String cratedate;
@NotEmpty(message="请选择头像")
  private String photo;
  private long del;
  //关联查询使用
  private Depart depart;

  public Depart getDepart() {
    return depart;
  }

  public void setDepart(Depart depart) {
    this.depart = depart;
  }

  public Emp(){}
  public Emp(long id, String no, String pass, String name, long did, long flag, String sex, String email, String qq, String phone, String cratedate, String photo, long del) {
    this.id = id;
    this.no = no;
    this.pass = pass;
    this.name = name;
    this.did = did;
    this.flag = flag;
    this.sex = sex;
    this.email = email;
    this.qq = qq;
    this.phone = phone;
    this.cratedate = cratedate;
    this.photo = photo;
    this.del = del;
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

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getDid() {
    return did;
  }

  public void setDid(long did) {
    this.did = did;
  }

  public long getFlag() {
    return flag;
  }

  public void setFlag(long flag) {
    this.flag = flag;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
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

  public String getCratedate() {
    return cratedate;
  }

  public void setCratedate(String cratedate) {
    this.cratedate = cratedate;
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
    return "Emp{" +
            "id=" + id +
            ", no='" + no + '\'' +
            ", pass='" + pass + '\'' +
            ", name='" + name + '\'' +
            ", did=" + did +
            ", flag=" + flag +
            ", sex='" + sex + '\'' +
            ", email='" + email + '\'' +
            ", qq='" + qq + '\'' +
            ", phone='" + phone + '\'' +
            ", cratedate='" + cratedate + '\'' +
            ", photo='" + photo + '\'' +
            ", del=" + del +
            '}';
  }

}
