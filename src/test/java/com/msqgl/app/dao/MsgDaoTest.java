package com.msqgl.app.dao;

import com.msqgl.app.data.Msg;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import spark.utils.CollectionUtils;

import java.sql.SQLException;
import java.util.List;

public class MsgDaoTest {

  private MsgDao msgDao = new MsgDao();

  @Test
  public void getAllGift() throws SQLException, ClassNotFoundException {
    final List allGift = msgDao.getAllMsg();
    Assert.assertFalse(CollectionUtils.isEmpty(allGift));
    final Msg msg = (Msg) allGift.get(0);
    Assert.assertTrue(StringUtils.isNotEmpty(msg.getIdMsg()));
    Assert.assertTrue(StringUtils.isNotEmpty(msg.getIdGift()));
    Assert.assertTrue(StringUtils.isNotEmpty(msg.getMsg()));
    Assert.assertTrue(StringUtils.isNotEmpty(msg.getSender()));
    Assert.assertTrue(msg.getAmount() != null);
  }

}
