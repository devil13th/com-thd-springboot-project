package com.thd.testframework.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thd.springboot.framework.jackson.jsondeserializers.JsonLocalDateDeserializer;
import com.thd.springboot.framework.jackson.jsondeserializers.JsonLocalDateTimeDeserializer;
import com.thd.springboot.framework.jackson.jsonserializers.JsonLocalDateSerializer;
import com.thd.springboot.framework.jackson.jsonserializers.JsonLocalDateTimeSerializer;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author devil13th
 **/
public class User implements Serializable {
    private String userId;
    private String userName;
    private Integer userAge;
//    @JsonSerialize(using = JsonDateSerializer.class)
//    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date userBirthday;

    private Item item;
    private List<Item> itemList;
    private List<User> children;
    @JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    private LocalDateTime ldt;
    @JsonDeserialize(using = JsonLocalDateDeserializer.class)
    @JsonSerialize(using = JsonLocalDateSerializer.class)
    private LocalDate ld;


    @JsonFormat(pattern="yyyy|MM|dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp userCreateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Timestamp getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Timestamp userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public List<User> getChildren() {
        return children;
    }

    public void setChildren(List<User> children) {
        this.children = children;
    }

    public LocalDate getLd() {
        return ld;
    }

    public void setLd(LocalDate ld) {
        this.ld = ld;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userBirthday=" + userBirthday +
                ", item=" + item +
                ", itemList=" + itemList +
                ", children=" + children +
                ", ldt=" + ldt +
                ", ld=" + ld +
                ", userCreateTime=" + userCreateTime +
                '}';
    }
}
