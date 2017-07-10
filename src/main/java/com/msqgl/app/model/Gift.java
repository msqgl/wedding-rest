package com.msqgl.app.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Gift implements Serializable {

  @Column(name = "ID_GIFT")
  private String idGift;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "IMG_PATH")
  private String imgPath;

  @Column(name = "TOTAL_PRICE")
  private BigDecimal totalPrice;

  @Column(name = "CONSUMED_PRICE")
  private BigDecimal consumedPrice;

  public String getIdGift() {
    return idGift;
  }

  public void setIdGift(String idGift) {
    this.idGift = idGift;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImgPath() {
    return imgPath;
  }

  public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public BigDecimal getConsumedPrice() {
    return consumedPrice;
  }

  public void setConsumedPrice(BigDecimal consumedPrice) {
    this.consumedPrice = consumedPrice;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("idGift", idGift)
        .append("title", title)
        .append("description", description)
        .append("imgPath", imgPath)
        .append("totalPrice", totalPrice)
        .append("consumedPrice", consumedPrice)
        .toString();
  }
}
