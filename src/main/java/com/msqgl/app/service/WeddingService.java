package com.msqgl.app.service;

import com.lowagie.text.DocumentException;
import com.msqgl.app.dao.GiftDao;
import com.msqgl.app.dao.MsgDao;
import com.msqgl.app.model.Gift;
import com.msqgl.app.model.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Service
public class WeddingService {

  @Autowired
  private GiftDao giftDao;

  @Autowired
  private MsgDao msgDao;

  @Autowired
  private PDFService pdfService;

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
      final BigDecimal newConsumedPrice = gift.getConsumedPrice() != null ? amount.add(gift.getConsumedPrice()) : amount;
      checkPrice(gift, newConsumedPrice);
      giftDao.updateConsumedPrice(idGift, newConsumedPrice);

      final Msg msgObj = new Msg();
      msgObj.setIdGift(idGift);
      msgObj.setMsg(msg);
      msgObj.setSender(sender);
      msgObj.setAmount(amount);
      msgDao.saveMsg(msgObj);
    } else {
      throw new IllegalStateException(String.format("Gift with idGift=%s not found.", idGift));
    }
  }

  private void checkPrice(final Gift gift,
                          final BigDecimal newConsumedPrice) {
    if (gift.getTotalPrice() != null && gift.getTotalPrice().compareTo(newConsumedPrice) < 0) {
      throw new IllegalStateException(
          String.format("There is a problem with price. Gift total is %s, and the new consumedPrice is %s", gift.getTotalPrice(), newConsumedPrice));
    }
  }

  public void createPDF(BufferedOutputStream bufferedOutputStream) throws FileNotFoundException, DocumentException {
    pdfService.createPDF(bufferedOutputStream);
  }

}
