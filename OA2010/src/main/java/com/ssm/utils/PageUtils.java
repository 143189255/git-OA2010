package com.ssm.utils;

import java.util.List;

public class PageUtils<T> {
    private long pageIndex;  //当前页码
    private long pageSize;   //页面大小
    private long totalCount; //总条数
    private long pageCount;  //总页数

    private List<T> records;  //每页的数据

    private long numberStart;  //页码开始位置
    private long numberEnd;    //页码结束位置
  public PageUtils(){}
    public PageUtils(long pageIndex, long pageSize, long totalCount, List<T> records) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.records = records;
        //计算总页数
        this.pageCount = (totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize+1);
        //数学算法给序号赋值
        //一共显示十个页面，动态伸缩
        if(this.pageCount<=10){
            this.numberStart=1;
            this.numberEnd=pageCount;
        }else{
            this.numberStart=pageIndex-4;
            this.numberEnd=pageIndex+5;
            if(numberStart<1){
                this.numberStart=1;
               this.numberEnd=10;
            }else if(numberEnd>pageCount){
                this.numberEnd=pageCount;
                this.numberStart=pageCount-9;
            }
        }
    }
    public long getNumberStart() {
        return numberStart;
    }

    public void setNumberStart(long numberStart) {
        this.numberStart = numberStart;
    }

    public long getNumberEnd() {
        return numberEnd;
    }

    public void setNumberEnd(long numberEnd) {
        this.numberEnd = numberEnd;
    }

    public long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "PageUtils{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", pageCount=" + pageCount +
                ", records=" + records +
                ", numberStart=" + numberStart +
                ", numberEnd=" + numberEnd +
                '}';
    }
}
