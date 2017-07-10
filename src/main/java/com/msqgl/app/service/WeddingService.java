package com.msqgl.app.service;

import com.msqgl.app.dao.GiftDao;
import com.msqgl.app.dao.MsgDao;
import com.msqgl.app.model.Gift;
import com.msqgl.app.model.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Service
public class WeddingService {

  @Autowired
  private GiftDao giftDao;

  @Autowired
  private MsgDao msgDao;

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
