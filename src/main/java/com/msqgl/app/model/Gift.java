package com.msqgl.app.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

public class Gift implements Serializable {

  private String idGift;

  private String title;

  private String description;

  private String imgPath;

  private BigDecimal totalPrice;

  private BigDecimal consumedPrice;

  private int indexOrder;

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

  public int getIndexOrder() {
    return indexOrder;
  }

  public void setIndexOrder(int indexOrder) {
    this.indexOrder = indexOrder;
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
        .append("indexOrder", indexOrder)
        .toString();
  }
}
