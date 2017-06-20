import com.optimus.module.user.ao.UserInfoAO;
import com.optimus.module.user.dal.entity.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by li.huan
 * Create Date 2017-06-20 14:04
 */
public class TestAO {

    private ClassPathXmlApplicationContext context;

    @Before
    public void befor(){
        context = new ClassPathXmlApplicationContext("config/spring-view.xml");
    }

    @Test
    public void queryData() {
        UserInfoAO infoAO = context.getBean(UserInfoAO.class);
        UserInfo userInfo = infoAO.queryById(1089);
        System.out.println(userInfo.getUsername());
    }


}
