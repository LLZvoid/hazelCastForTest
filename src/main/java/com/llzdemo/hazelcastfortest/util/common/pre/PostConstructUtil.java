package com.llzdemo.hazelcastfortest.util.common.pre;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author llz
 */
@Component("preRunning")
public class PostConstructUtil {
    String selfCode;
    Integer runningCode;

    @PostConstruct
    public void tt() {



    }
}
