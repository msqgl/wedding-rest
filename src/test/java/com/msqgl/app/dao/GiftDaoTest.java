package com.msqgl.app.dao;

import com.msqgl.app.WeddingRespApp;
import com.msqgl.app.config.DBConfig;
import com.msqgl.app.model.Gift;
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
public class GiftDaoTest {

  @Autowired
  private GiftDao giftDao;

  @Test
  public void getAllGift() throws SQLException, ClassNotFoundException {
    final List allGift = giftDao.getAllGift();
    Assert.assertFalse(CollectionUtils.isEmpty(allGift));
    final Gift gift = (Gift) allGift.get(0);
    Assert.assertTrue(StringUtils.isNotEmpty(gift.getIdGift()));
    Assert.assertTrue(StringUtils.isNotEmpty(gift.getTitle()));
    Assert.assertTrue(StringUtils.isNotEmpty(gift.getDescription()));
    Assert.assertTrue(StringUtils.isNotEmpty(gift.getImgPath()));
    Assert.assertTrue(gift.getConsumedPrice() != null);
  }

}
