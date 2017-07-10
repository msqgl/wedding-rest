package com.msqgl.app.dao;

import com.msqgl.app.WeddingRespApp;
import com.msqgl.app.config.DBConfig;
import com.msqgl.app.model.Msg;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spark.utils.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WeddingRespApp.class, DBConfig.class})
public class MsgDaoTest {

  private static final Logger LOG = LoggerFactory.getLogger(MsgDaoTest.class);

  @Autowired
  private MsgDao msgDao;

  @Test
  public void getAllGift() {
    final List allGift = msgDao.getAllMsg();
    Assert.assertFalse(CollectionUtils.isEmpty(allGift));
    LOG.info("Gift list size: {}", allGift.size());
    final Msg msg = (Msg) allGift.get(0);
    Assert.assertTrue(StringUtils.isNotEmpty(msg.getIdMsg()));
    Assert.assertTrue(StringUtils.isNotEmpty(msg.getIdGift()));
    Assert.assertTrue(StringUtils.isNotEmpty(msg.getMsg()));
    Assert.assertTrue(StringUtils.isNotEmpty(msg.getSender()));
    Assert.assertTrue(msg.getAmount() != null);
  }

  @Test
  public void saveMsg() {
    final List allMsg = msgDao.getAllMsg();

    final Msg msg = new Msg();
    msg.setIdGift(String.valueOf(1));
    msg.setMsg("Text message");
    msg.setSender("Sender");
    msg.setAmount(new BigDecimal(50));
    msgDao.saveMsg(msg);
    Assert.assertTrue(allMsg.size() + 1 == msgDao.getAllMsg().size());
  }

}
