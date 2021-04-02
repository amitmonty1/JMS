package com.amazonaws.samples;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.RoutesDefinition;
import org.apache.camel.spring.Main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import com.amazonaws.util.IOUtils;


public class Bootstrap {

    public static void main(String... args) throws Exception {
        Main main = new Main();
      
        S3FileReader s3fr = new S3FileReader();
        InputStream inputStream = s3fr.getcontextRouteFroms3();
        /*RoutesDefinition routes = context.loadRoutesDefinition(inputStream);
        System.out.println("@@@@@@:"+routes.getRoutes());
        context.addRouteDefinitions(routes.getRoutes());*/
       
       // main.addRouteBuilder();
        //main.setApplicationContextUri("camel-context-with-ssm-parameter-store.xml");
        //CamelContext context = new DefaultCamelContext();
       // context.addRoutes(new BridgeRouteBuilder());
        
        byte[] bytes = IOUtils.toByteArray(inputStream);
        Resource resource = new ByteArrayResource(bytes);
        //System.out.println(str);
       
        main.setApplicationContext(new GenericXmlApplicationContext(resource));
        
       
        main.run(args);
    }
}