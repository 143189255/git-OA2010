package com.ssm.pojo;


import java.io.Serializable;

public class Perssion implements Serializable {

  private long pId;
  private String pName;
  private long pParentid;
  private String pUrl;
  private String pIcon;
  private String pType;
  private long pDelete;
public Perssion(){

}
  public Perssion(long pId, String pName, long pParentid, String pUrl, String pIcon, String pType, long pDelete) {
    this.pId = pId;
    this.pName = pName;
    this.pParentid = pParentid;
    this.pUrl = pUrl;
    this.pIcon = pIcon;
    this.pType = pType;
    this.pDelete = pDelete;
  }

  public long getPId() {
    return pId;
  }

  public void setPId(long pId) {
    this.pId = pId;
  }


  public String getPName() {
    return pName;
  }

  public void setPName(String pName) {
    this.pName = pName;
  }


  public long getPParentid() {
    return pParentid;
  }

  public void setPParentid(long pParentid) {
    this.pParentid = pParentid;
  }


  public String getPUrl() {
    return pUrl;
  }

  public void setPUrl(String pUrl) {
    this.pUrl = pUrl;
  }


  public String getPType() {
    return pType;
  }

  public void setPType(String pType) {
    this.pType = pType;
  }


  public long getPDelete() {
    return pDelete;
  }

  public void setPDelete(long pDelete) {
    this.pDelete = pDelete;
  }

  public String getpIcon() {
    return pIcon;
  }

  public void setpIcon(String pIcon) {
    this.pIcon = pIcon;
  }

  @Override
  public String toString() {
    return "Perssion{" +
            "pId=" + pId +
            ", pName='" + pName + '\'' +
            ", pParentid=" + pParentid +
            ", pUrl='" + pUrl + '\'' +
            ", pIcon='" + pIcon + '\'' +
            ", pType='" + pType + '\'' +
            ", pDelete=" + pDelete +
            '}';
  }
}
