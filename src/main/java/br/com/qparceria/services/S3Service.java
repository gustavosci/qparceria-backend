package br.com.qparceria.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {
	
	@Autowired
	private AmazonS3 s3Client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
	
	public void uploadFile(String localFilePath) {
		try {
			File file = new File(localFilePath);	
			s3Client.putObject(new PutObjectRequest(bucketName, file.getName(), file));			
		}
		catch(AmazonServiceException ex) {
			LOG.info("Amazon ex: " + ex.getMessage());
			LOG.info("Status HTTP: " + ex.getErrorCode());
		}
		catch(AmazonClientException e){
			LOG.info("Amazon client ex: " + e.getMessage());
		}
	}

}
