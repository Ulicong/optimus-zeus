package stu.mvc.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import stu.mvc.http.vo.UserInfoVO;

import javax.annotation.PostConstruct;

/**
 * @RequestBody注解用于接收前端request-body 里的json数据
 * @ResponseBody 自动把java对象转成Json并输出到前端
 * <p>
 * Created by li.huan
 * Create Date 2017-05-12 16:11
 */
@Controller
@RequestMapping(value = "/http")
public class HttpTestController {


    /**
     * 定义初始化方法
     */
    @PostConstruct
    public void init() {
        System.out.println();
    }

    /**
     * 止方法适合提供Rest接口
     * 接收前端request-body 里的json数据;并自动映射成JavaBean
     *
     * @param infoVO
     * @return
     */
    @RequestMapping(value = "user_rest_info", method = RequestMethod.POST,
            headers = "content-type=application/json")
    @ResponseBody
    public UserInfoVO user_rest_info(@RequestBody UserInfoVO infoVO) {
        return infoVO;
    }

}
