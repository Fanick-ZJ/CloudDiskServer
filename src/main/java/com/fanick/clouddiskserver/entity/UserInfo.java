package com.fanick.clouddiskserver.entity;


import java.util.Date;

public class UserInfo {

  private long id;
  private String userNickname;
  private String userAccount;
  private String userPassword;
  private Date registerDate;
  private String userEmail;
  private String userCloudRootPath;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserNickname() {
    return userNickname;
  }

  public void setUserNickname(String userNickname) {
    this.userNickname = userNickname;
  }


  public String getUserAccount() {
    return userAccount;
  }

  public void setUserAccount(String userAccount) {
    this.userAccount = userAccount;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public Date getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(Date registerDate) {
    this.registerDate = registerDate;
  }


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }


  public String getUserCloudRootPath() {
    return userCloudRootPath;
  }

  public void setUserCloudRootPath(String userCloudRootPath) {
    this.userCloudRootPath = userCloudRootPath;
  }

}
