package com.chj9.cms.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * es连接配置
 */
@Configuration
@ComponentScan(basePackageClasses = ElasticSearchConfiguration.class)
@ConditionalOnClass(ElasticsearchClient.class)
public class ElasticSearchConfiguration {

    public static final String EsConnectionConfigKeyRoots = "elasticsearch";

    @Bean
    @ConfigurationProperties(EsConnectionConfigKeyRoots)
    public EsConnectionConfig esConnectionConfig() {
        return new EsConnectionConfig();
    }

    @Bean(destroyMethod = "close")
    public RestClient restClient(EsConnectionConfig esConnectionConfig) {
        String[] hostNameArray = esConnectionConfig.getHosts().split(",");
        HttpHost[] httpHostArray = new HttpHost[hostNameArray.length];
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
            new UsernamePasswordCredentials(esConnectionConfig.getUsername(), esConnectionConfig.getPassword()));
        for (int i = 0; i < hostNameArray.length; i++) {
            String hostName = hostNameArray[i];
            String[] hostPortNames = hostName.trim().split(":");
            HttpHost httpHost;
            if (hostPortNames.length != 2) {
                httpHost = new HttpHost(hostName, esConnectionConfig.getHttpPort(), esConnectionConfig.getScheme());
            } else {
                httpHost = new HttpHost(hostPortNames[0], Integer.parseInt(hostPortNames[1]), esConnectionConfig.getScheme());
            }
            httpHostArray[i] = httpHost;
        }

        RestClientBuilder restClientBuilder = RestClient.builder(httpHostArray)
            .setHttpClientConfigCallback(httpClientBuilder -> {
                //    httpClientBuilder.disableAuthCaching();
                return httpClientBuilder
                    .setDefaultCredentialsProvider(credentialsProvider);
            })
            .setRequestConfigCallback(builder -> {
                builder.setConnectTimeout(esConnectionConfig.getConnectTimeout());
                builder.setSocketTimeout(esConnectionConfig.getSocketTimeout());
                return builder;
            });
        return restClientBuilder.build();
    }

    /**
     * 线程安全，通过底层 HTTP 客户端网络资源
     * 传输对象与 ES 集群相关联，须显式关闭才能释放底层资源
     */
    @Bean(destroyMethod = "close")
    public ElasticsearchTransport elasticsearchTransport(RestClient restClient) {
        return new RestClientTransport(
            restClient, new JacksonJsonpMapper());
    }

    /**
     * 同步客户端，会阻塞
     */
    @Bean
    public ElasticsearchClient elasticsearchClient(ElasticsearchTransport transport) {
        return new ElasticsearchClient(transport);
    }

    /**
     * 异步客户端，异步执行不堵塞，异步客户端上的所有方法都返回一个标准CompletableFuture
     */
    @Bean
    public ElasticsearchAsyncClient elasticsearchasyncClient(ElasticsearchTransport transport) {
        return new ElasticsearchAsyncClient(transport);
    }

    public static class EsConnectionConfig {
        /**
         * 主机名:端口
         */
        private String hosts;
        /**
         * 用户名
         */
        private String username;

        /**
         * 密码
         */
        private String password;
        /**
         * 搜索引擎端口
         */
        private int httpPort;
        /**
         * 连接方案
         */
        private String scheme = "http";
        /**
         * 连接搜索引擎超时的时间
         */
        private int connectTimeout = 10000;
        /**
         * 服务进行数据交互的超时时间
         */
        private int socketTimeout = 60000;

        public String getHosts() {
            return hosts;
        }

        public void setHosts(String hosts) {
            this.hosts = hosts;
        }

        public int getHttpPort() {
            return httpPort;
        }

        public void setHttpPort(int httpPort) {
            this.httpPort = httpPort;
        }


        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public int getConnectTimeout() {
            return connectTimeout;
        }

        public void setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        public int getSocketTimeout() {
            return socketTimeout;
        }

        public void setSocketTimeout(int socketTimeout) {
            this.socketTimeout = socketTimeout;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
