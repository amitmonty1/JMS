package com.amazonaws.samples;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
//@Component
public class BridgeRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
// sender
    /*	S3FileReader s3fr = new S3FileReader();
    	InputStream inputStream = s3fr.getcontextRouteFroms3();
    	Properties prop = new Properties();
    	//HAprop.keySet()
    	Set<Object> map = prop.keySet();
    	Iterator iterator = map.iterator();
    	while (iterator.hasNext()) {
    		String val = 
    		from("iterator.next()").to("");
    	}*/
    from("amazonMQ:queue:DEV.QUEUE.1?concurrentConsumers=1")
    .to("websphereMQ:queue:DEV.QUEUE.1?preserveMessageQos=true");
// receiver
    from("websphereMQ:queue:DEV.QUEUE.2?concurrentConsumers=1")
    .to("amazonMQ:queue:DEV.QUEUE.2?preserveMessageQos=true");
    }
}
