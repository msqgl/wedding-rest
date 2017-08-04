package com.msqgl.app.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Msg implements Serializable {

  private String idMsg;

  private String idGift;

  private String msg;

  private String sender;

  private BigDecimal amount;

  private Date insertDate;

  public String getIdMsg() {
    return idMsg;
  }

  public void setIdMsg(String idMsg) {
    this.idMsg = idMsg;
  }

  public String getIdGift() {
    return idGift;
  }

  public void setIdGift(String idGift) {
    this.idGift = idGift;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Date getInsertDate() {
    return insertDate;
  }

  public void setInsertDate(Date insertDate) {
    this.insertDate = insertDate;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("idMsg", idMsg)
        .append("idGift", idGift)
        .append("msg", msg)
        .append("sender", sender)
        .append("amount", amount)
        .append("insertDate", insertDate)
        .toString();
  }
}
