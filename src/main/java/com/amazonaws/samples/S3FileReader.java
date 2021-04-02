package com.amazonaws.samples;

import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;


public class S3FileReader {
//public class S3FileReader implements InitializingBean{

	final Logger log = LoggerFactory.getLogger(S3FileReader.class);
   /* @Override
    public void configure() {
    	try{
		        from("aws-s3://" + "testjars-749042557449-eu-west-1" + "?accessKey="+getCredentials().getAWSAccessKeyId()+"&secretKey="+getCredentials().getAWSSecretKey()+"&prefix=hello.txt")
		        .to("file:/home/ec2-user/");
    	}catch(Exception e){
    		log.info("Error setting route", e.getMessage());
    	}
    	
       // from("aws-s3://" + "s3_bucket_name" + "?amazonS3Client=#amazonS3Client&deleteAfterRead=false&fileName=myfile.csv")
       // .idempotentConsumer(header("CamelAwsS3Key"), FileIdempotentRepository.fileIdempotentRepository(new File("target/file.data"), 250, 512000));
    }*/
    
    
	protected InputStream getcontextRouteFroms3() {
    	InputStream in = null;
    	try{
    		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
  	              .withCredentials(new InstanceProfileCredentialsProvider(false))
  	              .build();
  		  //log.info("Bucket name is:"+System.getenv("s3.bucketName"));
  		  //log.info("Bucket key is:"+System.getenv("s3.bucketName.key"));
  		  S3Object s3object = s3Client.getObject(
	              new GetObjectRequest("testjars-749042557449-eu-west-1","route.xml"));    		
    		in = (InputStream)s3object.getObjectContent();
    	}catch(NullPointerException npe){
    		log.info("Error getting object from s3(object is null):", npe);
    	}
    	catch(Exception e){
    		log.info("Error getting object from s3:", e);
    	}
        return in;
    }
	/*@Override
	public void afterPropertiesSet() {
    	try{
    		
    		Assert.notNull(System.getenv("s3.bucketName"), "Bucket name is required");
    		Assert.notNull(System.getenv("s3.bucketName.key"), "Bucket key is required");

    		  log.info("Bucket name is:"+System.getenv("s3.bucketName"));
    		  log.info("Bucket key is:"+System.getenv("s3.bucketName.key"));

    	     // displayTextInputStream(in);
    	}catch(Exception e){
    		log.info("Error getting object from s3:", e.getMessage());
    	}
    }*/
	

   /* private void displayTextInputStream(InputStream input) throws IOException
    {
      // Read one text line at a time and display.
    	try{
    	//byte[] buf = new byte[1024];	
    	 Files.copy(input, Paths.get("/home/ec2-user/camel-context-with-ssm-parameter-store2.xml"));
    	}catch(IOException ioe){
    		log.info("Error coping file", ioe.getMessage());
    	}
    }*/
}
