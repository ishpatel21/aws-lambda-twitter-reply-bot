package com.example.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

@Cacheable("lambda")
public class StreamLambdaHandler implements RequestStreamHandler {
    private static Logger logger = LoggerFactory.getLogger(StreamLambdaHandler.class);     

    public static final SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
 
    static { 
       try { 
           handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Demo1Application.class);
       } catch (ContainerInitializationException e) { 
           // if we fail here. We re-throw the exception to force another cold start 
           String errMsg = "Could not initialize Spring Boot application"; 
           logger.error(errMsg); 
           throw new RuntimeException("Could not initialize Spring Boot application", e); 
       } 
    }

    @Override 
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
        // just in case it wasn't closed 
        outputStream.close(); 
    }
}