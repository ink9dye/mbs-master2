package com.xcd.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

public class BooksInfo implements Serializable {
    @TableId
    private Integer bid;

    private String name;

    private String card;

    private String author;

    private Integer num;

    private String press;
    //2 未借出 1 借出
    private Integer status;

    @TableField("publish_date") // 映射数据库字段
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") // 设置返回格式和时区
    private Date publishDate;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BooksInfo{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                ", card='" + card + '\'' +
                ", author='" + author + '\'' +
                ", num=" + num +
                ", press='" + press + '\'' +
                ", type='" + type + '\'' +
                ", publishDate=" + publishDate + // 确保 toString 包含 publishDate
                '}';
    }

    private String type;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getPublishDate() {return publishDate;}

    public void setPublishDate(Date publishDate) {this.publishDate = publishDate;}
}