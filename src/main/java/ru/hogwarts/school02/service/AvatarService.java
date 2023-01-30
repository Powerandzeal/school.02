package ru.hogwarts.school02.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school02.model.Avatar;
import ru.hogwarts.school02.model.Student;
import ru.hogwarts.school02.repositories.AvatarRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;

import static java.nio.file.StandardOpenOption.CREATE_NEW;


@Service
public class AvatarService {
    @Value("${student.avatar.dir.path}")
    private String avatarDir;
    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    public void uploadAvatar(Long avatarId, MultipartFile file) throws IOException {
        Student student = studentService.getStudent(avatarId);

        Path filePath = Path.of(avatarDir, avatarId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatarById(avatarId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
//        avatar.setData(generateAvatarPreview(filePath));

        avatarRepository.save(avatar);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Avatar findAvatarById(Long avatarId) {
        return avatarRepository.findAvatarById(avatarId).orElse(new Avatar());
    }

//    private byte[] generateAvatarPreview(Path filePath) throws IOException {
//        try (
//                InputStream is = Files.newInputStream(filePath);
//                BufferedInputStream bis = new BufferedInputStream(is, 1024);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//            BufferedImage image = ImageIO.read(bis);
//
//            int height = image.getHeight() / (image.getWidth() / 100);
//            BufferedImage preview = new BufferedImage(100, height, image.getType());
//            Graphics2D graphics = preview.createGraphics();
//            graphics.drawImage(image, 0, 0, 100, height, null);
//            graphics.dispose();
//
//            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
//            return baos.toByteArray();
//        }
//    }


    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
