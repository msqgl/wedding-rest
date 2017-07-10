package com.msqgl.app.dao;

import com.msqgl.app.WeddingRespApp;
import com.msqgl.app.config.DBConfig;
import com.msqgl.app.model.Msg;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spark.utils.CollectionUtils;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WeddingRespApp.class, DBConfig.class})
public class MsgDaoTest {

  @Autowired
  private MsgDao msgDao;

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
