package com.msqgl.app.service;

import com.msqgl.app.dao.GiftDao;
import com.msqgl.app.dao.MsgDao;
import com.msqgl.app.data.Gift;
import com.msqgl.app.data.Msg;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class WeddingService {

  private GiftDao giftDao;
  private MsgDao msgDao;

  public WeddingService() {
    this.giftDao = new GiftDao();
    this.msgDao = new MsgDao();
  }

  public List<Gift> getAllGift() throws SQLException, ClassNotFoundException {
    return giftDao.getAllGift();
  }

  public List<Msg> getAllMsg() throws SQLException, ClassNotFoundException {
    return msgDao.getAllMsg();
  }

  public void saveGiftMsg(final String idGift,
                          final String msg,
                          final String sender,
                          final BigDecimal amount) throws SQLException, ClassNotFoundException {
    final Gift gift = giftDao.getGiftFromId(idGift);
    if (gift != null) {
      final Msg msgObj = new Msg();
      msgObj.setIdGift(idGift);
      msgObj.setMsg(msg);
      msgObj.setSender(sender);
      msgObj.setAmount(amount);
      msgDao.saveMsg(msgObj);
      final BigDecimal newConsumedPrice = gift.getConsumedPrice() != null ? amount.add(gift.getConsumedPrice()) : amount;
      giftDao.updateConsumedPrice(idGift, newConsumedPrice);
    } else {
      throw new IllegalStateException(String.format("Gift with idGift=%s not found.", idGift));
    }
  }

}
