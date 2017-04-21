package com.action;

import com.model.entity.QueryWalletBasicData;
import com.service.CashManager;
import common.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by li.huan
 * Create Date 2017-04-21 11:26
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Resource(name = "cashManager")
    private CashManager cashManager;


    @RequestMapping(value = "mainJson")
    public void mainJson(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        List<QueryWalletBasicData> list = cashManager.queryWalletBasicData();
        String json = JsonUtil.toJson(list);
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(json);
    }


    @RequestMapping(value = "main")
    public ModelAndView main(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        return new ModelAndView("main");
    }
}
