package br.com.qparceria.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.com.qparceria.services.exceptions.FileException;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			return uploadFile(multipartFile.getInputStream(), multipartFile.getOriginalFilename(),
					multipartFile.getContentType());			
		} catch (IOException ioe) {
			throw new FileException("Erro de IO: " + ioe.getMessage());
		}
	}

	private URI uploadFile(InputStream is, String fileName, String contentType) {		
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			s3Client.putObject(bucketName, fileName, is, meta);
			return s3Client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao gerar URI: " + e.getMessage());
		}
	}

}
