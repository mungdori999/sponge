package com.petweb.sponge.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3UploadService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;
    private static final ArrayList<String> fileValidate = new ArrayList<>();

    static {
        // 이미지 확장자
        fileValidate.add(".jpg");
        fileValidate.add(".jpeg");
        fileValidate.add(".png");
        fileValidate.add(".JPG");
        fileValidate.add(".JPEG");
        fileValidate.add(".PNG");

        // 동영상 확장자 추가
        fileValidate.add(".mp4");
        fileValidate.add(".avi");
        fileValidate.add(".mov");
        fileValidate.add(".mkv");
    }


    public String saveImage(MultipartFile file, String dir) {
        String originalFilename = dir + "/" + createFileName(file.getOriginalFilename());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        try {
            amazonS3.putObject(bucket, originalFilename, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return originalFilename;
    }

    /**
     * 복수의 사진,동영상 파일 AWS에 저장
     *
     * @param multipartFile
     * @param dir
     * @return
     */
    public List<String> saveFiles(List<MultipartFile> multipartFile, String dir) {
        List<String> fileNameList = new ArrayList<>();

        multipartFile.forEach(file -> {
            String originalFilename = dir + "/" + createFileName(file.getOriginalFilename());
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            try {
                amazonS3.putObject(bucket, originalFilename, file.getInputStream(), metadata);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            fileNameList.add(originalFilename);
        });


        return fileNameList;
    }

    /**
     * 복수의 사진 파일 AWS에서 삭제
     *
     * @param fileNames
     */
    public void deleteImages(List<String> fileNames) {
        for (String fileName : fileNames) {
            amazonS3.deleteObject(bucket, fileName);
        }
    }

    // 파일명 중복 방지
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    // 파일 유효성 검사
    private String getFileExtension(String fileName) {
        if (fileName.isEmpty()) {
            throw new RuntimeException("INVALID FILE");
        }
        String idxFileName = fileName.substring(fileName.lastIndexOf("."));
        if (!fileValidate.contains(idxFileName)) {
            throw new RuntimeException("INVALID FILE EXTENSION");
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }


}
