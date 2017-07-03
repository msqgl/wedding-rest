package com.msqgl.app.service;

import com.msqgl.app.dao.GiftDao;
import com.msqgl.app.dao.MsgDao;
import com.msqgl.app.data.Gift;
import com.msqgl.app.data.Msg;

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

  public void saveGiftMsg(final Msg msg) throws SQLException, ClassNotFoundException {
    msgDao.saveGiftMsg(msg);
  }
}
