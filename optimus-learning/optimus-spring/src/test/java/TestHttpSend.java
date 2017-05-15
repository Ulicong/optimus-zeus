import common.util.HttpRequestAccess;
import org.junit.Test;

/**
 * Created by li.huan
 * Create Date 2017-05-12 16:17
 */
public class TestHttpSend {


    /*
        private ClassPathXmlApplicationContext context;

        @Before
        public void before() {
            context = new ClassPathXmlApplicationContext(new String[]{"config/applicationContext.xml", "config/springMVC-servlet.xml"});
        }


        @Test
        public void testJsonSend(){
            //HttpTestController controller = context.getBean(HttpTestController.class);
        }
    */

    @Test
    public void testJsonSend() {
        HttpRequestAccess.httpPostAccess("http://localhost:8080/optimus-spring/http/user_rest_info.pm",
                "{\"userName\":\"tom\",\"userId\":12313,\"userAge\":23,\"userAddress\":\"杭州\"}");
    }


}
