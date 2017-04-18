package zeus.optimus.module.container;

import common.jetty.RestApiServer;

/**
 * Created by weber on 2017/4/16.
 * Jetty服务启动类
 */
public class StartModel {

    public static void main(String[] args) {
        RestApiServer.start();
    }
}
