package kr.hs.dgsw.web_326.Controller;

import kr.hs.dgsw.web_326.Domain.Comment;
import kr.hs.dgsw.web_326.Domain.User;
import kr.hs.dgsw.web_326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_326.Service.CommentService;
import kr.hs.dgsw.web_326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

    private final UserService userService;

    private final CommentService commentService;

    @Autowired
    public AttachmentController(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }

    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile uploadFile) {
        String destFilename = "E:\\Project\\Visual Studio 2017\\dgsw-web\\web_326\\upload\\"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "\\"
                + UUID.randomUUID().toString() + "_"
                + uploadFile.getOriginalFilename();
        try {
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            uploadFile.transferTo(destFile);
            return new AttachmentProtocol(destFilename, uploadFile.getOriginalFilename());
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/userAttachment/{id}")
    public void downloadUser(@PathVariable Long id, HttpServletRequest rq, HttpServletResponse resp) {

        User user = userService.getUser(id);
        if (user == null) return;

        String filePath = user.getStoredPath();
        this.download(filePath, rq, resp);
    }

    @GetMapping("/commentAttachment/{id}")
    public void downloadComment(@PathVariable Long id, HttpServletRequest rq, HttpServletResponse resp) {

        Comment comment = commentService.getComment(id);
        if (comment == null) return;

        String filePath = comment.getStoredPath();
        System.out.println(filePath);
        this.download(filePath, rq, resp);
    }

    private void download(String filePath, HttpServletRequest rq, HttpServletResponse resp) {
        if (filePath == null) return;
        try {
            File file = new File(filePath);
            if (file.exists() == false) return;

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) mimeType = "application/octet-stream";

            resp.setContentType(mimeType);
            resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            resp.setContentLength((int) file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, resp.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
