package com.ssm.pojo;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.sql.Date;

public class Depart  implements Serializable {
  private long id;
 @Length(max = 10,min = 2,message = "部门名称在2到10位字符之间")
  private String name;
 @NotEmpty(message = "创建日期不能为空")
  private String createtime;
  private long del;
  private String manager;
  //各部门人数
  private long count;
public Depart(){

}

  public Depart(long id, String name, String createtime, long del, String manager, long count) {
    this.id = id;
    this.name = name;
    this.createtime = createtime;
    this.del = del;
    this.manager = manager;
    this.count = count;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }


  public long getDel() {
    return del;
  }

  public void setDel(long del) {
    this.del = del;
  }

  @Override
  public String toString() {
    return "Depart{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", createtime='" + createtime + '\'' +
            ", del=" + del +
            ", manager='" + manager + '\'' +
            ", count=" + count +
            '}';
  }
}
