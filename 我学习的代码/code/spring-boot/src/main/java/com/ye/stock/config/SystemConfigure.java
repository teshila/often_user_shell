package com.ye.stock.config;


import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Configuration;

@SpringBootConfiguration
public class SystemConfigure implements TomcatConnectorCustomizer {




    @Override
    public void customize(Connector connector) {
        connector.setPort(9090);
        connector.setRedirectPort(9092);

    }
}
