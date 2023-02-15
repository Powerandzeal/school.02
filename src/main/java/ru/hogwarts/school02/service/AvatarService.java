package ru.hogwarts.school02.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
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
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;


@Service
@Transactional
public class AvatarService {
    Logger logger = LoggerFactory.getLogger(AvatarService.class);
    @Value("${student.avatar.dir.path}")
    private String avatarDir;
    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Requesting avatar");
        Student student = studentService.getStudent(studentId);
        Path filePath = Path.of(avatarDir, studentId + "." + getExtensions(Objects.requireNonNull(avatarFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatarById(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    private String getExtensions(String fileName) {
        logger.info("get Extension file name {}",fileName);
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }



    public Avatar findAvatarById(Long avatarId) {
        logger.info("Find avatary by id {}",avatarId);
        return avatarRepository.findAvatarById(avatarId).orElse(new Avatar());
    }
    public Avatar findByStudentId(Long studentId) {
        logger.info("Find student by id {}",studentId);
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    private byte[] generateAvatarPreview(Path filePath) throws IOException {
        logger.info("generate Avatar Preview");
        try (
                InputStream is = Files.newInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }


    public String getExtension(String fileName) {
        logger.info("get Extension file name {}",fileName);

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Optional<Avatar> getAllAvatars() {
        logger.info("get all avatars");

        return avatarRepository.getAllBy();
    }

    public Collection<Avatar> getAllAvatars(Integer pageNum, Integer pageSize) {
        logger.info("get all avatars");
        PageRequest request = PageRequest.of(pageNum - 1, pageSize);
        return avatarRepository.findAll(request).getContent();
    }





}
