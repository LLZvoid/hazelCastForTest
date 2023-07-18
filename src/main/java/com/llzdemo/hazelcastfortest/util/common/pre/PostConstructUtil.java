package com.llzdemo.hazelcastfortest.util.common.pre;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author llz
 */
@Component("preRunning")
public class PostConstructUtil {
    String selfCode;
    Integer runningCode;

    @Resource
    private HazelcastInstance hazelcastInstance;

    @PostConstruct
    public void tt() {
        this.ttt();

        ITopic<Object> firstMsg = hazelcastInstance.getTopic("myFirstMsg");

        firstMsg.publish("give me five");

    }

    public void ttt() {
        ITopic<Object> firstMsg = hazelcastInstance.getTopic("myFirstMsg");

        firstMsg.addMessageListener(message -> {
            System.out.println("hi guys");
        });

    }
}
