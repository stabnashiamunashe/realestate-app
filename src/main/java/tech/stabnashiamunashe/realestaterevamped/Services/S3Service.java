package tech.stabnashiamunashe.realestaterevamped.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Slf4j
public class S3Service {

    private final S3Properties s3Properties;

    private final S3Client s3;

    public S3Service(S3Properties s3Properties,S3Client s3) {
        this.s3Properties = s3Properties;
        this.s3 = s3;
    }

    public String uploadFile(MultipartFile multipartFile) {
        try {

            Tag tag1 = Tag.builder()
                    .key("Tag 1")
                    .value("This is tag 1")
                    .build();

            Tag tag2 = Tag.builder()
                    .key("Tag 2")
                    .value("This is tag 2")
                    .build();

            List<Tag> tags = new ArrayList<>();
            tags.add(tag1);
            tags.add(tag2);

            Tagging allTags = Tagging.builder()
                    .tagSet(tags)
                    .build();

            String objectKey = UUID.randomUUID().toString();
            Map<String, String> metadata = new HashMap<>();
            metadata.put("x-amz-meta", "hit-accommodation");
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(s3Properties.bucketName())
                    .key(objectKey)
                    .tagging(allTags)
                    .metadata(metadata)
                    .build();

            s3.putObject(putOb, RequestBody.fromFile(convertMultiPartFileToFile(multipartFile)));

            return "https://" + s3Properties.bucketName() + ".s3.amazonaws.com/" + objectKey;

        } catch (S3Exception e) {
            log.error("Error uploading file to S3", e);
            return null;
        }
    }

    public File convertMultiPartFileToFile(MultipartFile multipartFile){
        File convertedFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            java.nio.file.Files.copy(
                    multipartFile.getInputStream(),
                    convertedFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
