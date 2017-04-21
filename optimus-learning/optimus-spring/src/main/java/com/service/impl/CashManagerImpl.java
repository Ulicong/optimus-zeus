package com.service.impl;

import com.model.entity.QueryWalletBasicData;
import com.model.entity.QueryWalletBasicDataExample;
import com.model.mapper.QueryWalletBasicDataMapper;
import com.service.CashManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by li.huan
 * Create Date 2017-04-21 11:16
 */
@Service("cashManager")
public class CashManagerImpl implements CashManager {

    @Autowired
    private QueryWalletBasicDataMapper queryWalletBasicDataMapper;


    public List<QueryWalletBasicData> queryWalletBasicData() {
        QueryWalletBasicDataExample ex = new QueryWalletBasicDataExample();
        ex.setOrderByClause("stat_date desc");
        return queryWalletBasicDataMapper.selectByExample(ex);
    }


}
