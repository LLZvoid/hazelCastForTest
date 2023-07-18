package com.llzdemo.hazelcastfortest.util.common.config;

import cn.hutool.core.util.StrUtil;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Hazelcast 配置
 *
 * @author zyj
 */
@Data
@Configuration
public class HazelcastConf {

    @Value("${hazelcast.clusterName:fcm}")
    private String clusterName;
    @Value("${hazelcast.network.ip}")
    private String ip;
    @Value("${hazelcast.network.port:5701}")
    private Integer port;
    @Value("${hazelcast.network.portAutoIncrement:false}")
    private boolean portAutoIncrement;
    @Value("${hazelcast.loggingType:slf4j}")
    private String loggingType;


    @Bean
    public Config hazelcastConfig() {
        //网络配置
        NetworkConfig networkConfig = new NetworkConfig()
                //组网起始监听端口
                .setPort(port)
                //总端口数 默认 100 意思可以添加100个端口
                .setPortCount(100)
                .setPortAutoIncrement(portAutoIncrement);
        if (StringUtils.isNotBlank(ip)) {
            List<String> ips = StrUtil.split(ip, ",");
            TcpIpConfig tcpIpConfig = new TcpIpConfig()
                    .setEnabled(true)
                    .setMembers(ips);
            JoinConfig joinConfig = new JoinConfig().setTcpIpConfig(tcpIpConfig);
            networkConfig.setJoin(joinConfig);
        }
        //map 配置
        MapConfig mapConfig = new MapConfig()
                .setName("mapConfig")
                .setReadBackupData(true)
                .setBackupCount(1)
                .setAsyncBackupCount(1)
                .setEvictionConfig(new EvictionConfig())
                .setTimeToLiveSeconds(-1);
        //配置
        Config config = new Config()
                .setInstanceName("hazelcastInstance")
                .setClusterName(clusterName)
                .setProperty("hazelcast.logging.type", loggingType)
                .setNetworkConfig(networkConfig)
                .addMapConfig(mapConfig);
        return config;
    }

    /**
     * @param config
     * @return
     */
    @Bean("hazelcastInstance")
    public HazelcastInstance hazelcastInstance(Config config) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        return hazelcastInstance;
    }
}
