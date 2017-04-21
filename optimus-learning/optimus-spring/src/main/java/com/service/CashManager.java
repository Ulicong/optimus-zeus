package com.service;

import com.model.entity.QueryWalletBasicData;

import java.util.List;

/**
 * Created by li.huan
 * Create Date 2017-04-21 11:16
 */
public interface CashManager {

    List<QueryWalletBasicData> queryWalletBasicData();
}
