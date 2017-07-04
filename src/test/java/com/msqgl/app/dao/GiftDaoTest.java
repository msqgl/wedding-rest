package com.msqgl.app.dao;

import com.msqgl.app.data.Gift;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import spark.utils.CollectionUtils;

import java.sql.SQLException;
import java.util.List;

public class GiftDaoTest {

  private GiftDao giftDao = new GiftDao();

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
