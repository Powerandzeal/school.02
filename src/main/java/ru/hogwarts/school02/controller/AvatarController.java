package ru.hogwarts.school02.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school02.model.Avatar;
import ru.hogwarts.school02.service.AvatarService;
import ru.hogwarts.school02.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("avatars")
public class AvatarController {
    private final AvatarService avatarService;
    private final StudentService studentService;

    public AvatarController(AvatarService avatarService, StudentService studentService) {
        this.avatarService = avatarService;
        this.studentService = studentService;
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id,
                                               @RequestParam MultipartFile avatar)
            throws IOException {
        if (avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/cover/preview")
    public ResponseEntity<byte[]> downLoadAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatarById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar")
    public void downloadCover(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatarById(id);

        Path path = Path.of(avatar.getFilePath());

        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream();
        ) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    @GetMapping(value = "/{id}/getByStydentId")
    public void find(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findByStudentId(id);

        Path path = Path.of(avatar.getFilePath());

        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream();
        ) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    //    @GetMapping
//    public ResponseEntity<List<Avatar>> getAllAvatar(@RequestParam("page") Integer pageNumber,
//                                                     @RequestParam("size") Integer pageSize) {
//        List<Avatar> avatars = avatarService.getAllAvatars(pageNumber,pageSize)
//    }
    @GetMapping("getAllAvatarsWithPagination")
    public Optional<Avatar> getAllAvatar() {
        return avatarService.getAllAvatars();
    }
    @GetMapping("all")
    public ResponseEntity<Collection<Avatar>> getAll(@RequestParam("page") Integer pageNum,
                                                     @RequestParam("size") Integer pageSize) {
        return ResponseEntity.ok(avatarService.getAllAvatars(pageNum, pageSize));
    }

}
