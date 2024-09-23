package io.springmvc.upload.controller;

import io.springmvc.domain.item.entity.Item;
import io.springmvc.upload.entity.UploadFile;
import io.springmvc.upload.entity.UploadItem;
import io.springmvc.upload.file.FileStore;
import io.springmvc.upload.repository.UploadItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UploadItemController {
    private final UploadItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping("/uploadItems/new")
    public String newItem(@ModelAttribute ItemForm form) {
        return "upload/item-form";
    }

    @PostMapping("/uploadItems/new")
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {
        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());

        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

        //데이터베이스에 저장
        UploadItem item = new UploadItem();
        item.setItemName(form.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFiles(storeImageFiles);
        itemRepository.save(item);
        redirectAttributes.addAttribute("uploadItemId", item.getId());
        return "redirect:/uploadItems/{uploadItemId}";
    }

    @GetMapping("/uploadItems/{id}")
    public String items(@PathVariable Long id, Model model) {
        UploadItem item = itemRepository.findById(id);
        model.addAttribute("item", item);
        return "/upload/item-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @GetMapping("/attach/{uploadItemId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long uploadItemId) throws MalformedURLException {
        UploadItem item = itemRepository.findById(uploadItemId);

        String storeFileName = item.getAttachFile().getStoreFileName();
        String uploadFileName = item.getAttachFile().getUploadFileName();

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        log.info("uploadFileName={}", uploadFileName);
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

}
