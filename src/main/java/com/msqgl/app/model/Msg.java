package com.msqgl.app.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Msg implements Serializable {

  @Column(name = "ID_MSG")
  private String idMsg;

  @Column(name = "ID_GIFT")
  private String idGift;

  @Column(name = "MSG")
  private String msg;

  @Column(name = "SENDER")
  private String sender;

  @Column(name = "AMOUNT")
  private BigDecimal amount;

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

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("idMsg", idMsg)
        .append("idGift", idGift)
        .append("msg", msg)
        .append("sender", sender)
        .append("amount", amount)
        .toString();
  }
}
