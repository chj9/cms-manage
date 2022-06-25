package com.dliberty.cms.config;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import javax.annotation.PreDestroy;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfiguration {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes ;
    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;
    private TransportClient client;
 
    @Bean
    public TransportClient init(){
        logger.info("Building ElasticSearch client");
        Settings settings = Settings.builder().put("cluster.name", clusterName).build();
//        client = new PreBuiltTransportClient(settings);
        try {
            if (!"".equals(clusterNodes)){
                for (String nodes:clusterNodes.split(",")) {
                    String InetSocket[] = nodes.split(":");
                    String address = InetSocket[0];
                    Integer port = Integer.valueOf(InetSocket[1]);
                    
                    InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getByName(address), port);
                    client.addTransportAddress(new TransportAddress(inetSocketAddress));
                }
            }
        } catch (Exception e) {
            logger.error("Error build ElasticSearch client: {}", e.getMessage());
        }
        return client;
    }
 
    @PreDestroy
    public void destory() {
        if (client != null){
            client.close();
        }
    }


}
