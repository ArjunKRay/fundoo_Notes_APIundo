package com.bridgelabz.user.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.lucene.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.bridgelabz.exception.UserException;
import com.bridgelabz.response.Response;
import com.bridgelabz.user.model.User;
import com.bridgelabz.user.repository.UserRepository;
import com.bridgelabz.utility.ITockenGenerator;


@Service
public class AmazonS3BucketServiceImpl {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private Environment environment;

	@Autowired
	private ITockenGenerator userToken;

	private AmazonS3 s3client;

	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;
	@Value("${amazonProperties.bucketName}")
	private String bucketName;
	@Value("${amazonProperties.accessKey}")
	private String accessKey;
	@Value("${amazonProperties.secretKey}")
	private String secretKey;

	@SuppressWarnings("deprecation")
	@PostConstruct
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		this.s3client = new AmazonS3Client(credentials);

	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}

	private void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(
				new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	public Response uploadFile(MultipartFile multipartFile, String tocken) throws IOException {
		String id = userToken.verifyTocken(tocken);
		Optional<User> optionaluser = userRepository.findById(id);
		if (optionaluser.isPresent()) {
			User user = optionaluser.get();
			String fileUrl = "";
			File file = convertMultiPartToFile(multipartFile);
			String fileName = generateFileName(multipartFile);
			fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
			uploadFileTos3bucket(fileName, file);
			file.delete();
			user.setImage(fileUrl);
			userRepository.save(user);
			return new Response(200, "fileUploaded successfully", null);
		} else {
			throw new UserException("user id not present");
		}

	}

	public Response deleteFileFromS3Bucket(String fileName, String tocken) {

		String id = userToken.verifyTocken(tocken);
		Optional<User> optionaluser = userRepository.findById(id);
		if (optionaluser.isPresent()) {
			User user = optionaluser.get();
			s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
			user.setImage(null);
			userRepository.save(user);
			return new Response(200, "file delete successfully", null);
		} else {
			throw new UserException("user id not present");
		}
	}
}
