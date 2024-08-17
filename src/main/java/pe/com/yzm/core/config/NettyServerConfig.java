package pe.com.yzm.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ContextPathCompositeHandler;
import org.springframework.http.server.reactive.HttpHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class NettyServerConfig {

    @Value("${server.port:8080}")
    private int port;

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    /**
     * This method is used to get NettyReactiveWebServerFactory.
     * @return NettyReactiveWebServerFactory.
     */
    @Bean
    public NettyReactiveWebServerFactory nettyReactiveWebServerFactory() {
        NettyReactiveWebServerFactory webServerFactory = new NettyReactiveWebServerFactory() {
            @Override
            public WebServer getWebServer(HttpHandler httpHandler) {
                Map<String, HttpHandler> handlerMap = new HashMap<>();
                handlerMap.put(contextPath, httpHandler);
                return super.getWebServer(new ContextPathCompositeHandler(handlerMap));
            }
        };
        webServerFactory.addServerCustomizers(portCustomizer());
        return webServerFactory;
    }

    /**
     * This method is used to get NettyServerCustomizer.
     * @return NettyServerCustomizer.
     */
    public NettyServerCustomizer portCustomizer() {
        return httpServer -> httpServer.port(port);
    }

}
