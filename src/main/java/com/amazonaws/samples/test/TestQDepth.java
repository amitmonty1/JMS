package com.amazonaws.samples.test;

import java.util.Hashtable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.MQConstants;

public class TestQDepth {
	
		public static void main(String... args) throws Exception {
			final Logger log = LoggerFactory.getLogger(TestQDepth.class);
	        MQQueue destQueue;
	        MQQueueManager qMgr;
	     	int openOptions = MQConstants.MQOO_INQUIRE;
			Hashtable<String,Object> props = new Hashtable<String,Object>(); 
	            props.put(CMQC.CHANNEL_PROPERTY, "");
	            props.put(CMQC.HOST_NAME_PROPERTY, "");
	            props.put(CMQC.PORT_PROPERTY, new Integer(1414));
	            props.put(CMQC.USER_ID_PROPERTY, "");
	            props.put(CMQC.PASSWORD_PROPERTY, "");
	            //MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,
	            // MQC.TRANSPORT_MQSERIES);
	            try{
	            qMgr = new MQQueueManager("queueManager", props);
	            destQueue = qMgr.accessQueue("DEV.QUEUE.1", openOptions);
	            int depth = destQueue.getCurrentDepth();
	            log.info("Queue depth is :", depth);
	            }catch(Exception ex){
	            	log.info("Received exception in connecting to MQ Queue manager{}", ex);
	            }
			
		}
}
