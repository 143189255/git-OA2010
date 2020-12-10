package com.ssm.pojo;


import java.io.Serializable;

public class Role implements Serializable {

  private long rId;
  private String rName;
  private long rDelete;
public Role(){}
  public Role(long rId, String rName, long rDelete) {
    this.rId = rId;
    this.rName = rName;
    this.rDelete = rDelete;
  }

  public long getRId() {
    return rId;
  }

  public void setRId(long rId) {
    this.rId = rId;
  }


  public String getRName() {
    return rName;
  }

  public void setRName(String rName) {
    this.rName = rName;
  }


  public long getRDelete() {
    return rDelete;
  }

  public void setRDelete(long rDelete) {
    this.rDelete = rDelete;
  }

  @Override
  public String toString() {
    return "Role{" +
            "rId=" + rId +
            ", rName='" + rName + '\'' +
            ", rDelete=" + rDelete +
            '}';
  }
}
