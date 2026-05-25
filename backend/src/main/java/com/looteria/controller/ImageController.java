package com.looteria.controller;

import com.looteria.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/imagenes")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Value("${upload.dir:uploads}")
    private String uploadDir;

    @PostMapping("/upload/{listingId}")
    public ResponseEntity<?> uploadImage(@PathVariable Long listingId,
                                          @RequestParam("file") MultipartFile file) {
        try {
            String ruta = imageService.uploadImage(listingId, file);
            return ResponseEntity.ok(new UploadResponse(ruta));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al subir la imagen");
        }
    }

    @GetMapping("/serve/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = Paths.get(uploadDir).toAbsolutePath().resolve(filename);
            System.out.println(">>> Buscando imagen en: " + file.toString());
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                System.out.println(">>> NO encontrada: " + file.toString());
                return ResponseEntity.notFound().build();
            }
            String contentType = filename.endsWith(".png") ? "image/png" :
                                 filename.endsWith(".jpg") || filename.endsWith(".jpeg") ? "image/jpeg" :
                                 "application/octet-stream";
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{listingId:[0-9]+}")
    public ResponseEntity<List<ImageService.ImageDTO>> getImages(@PathVariable Long listingId) {
        return ResponseEntity.ok(imageService.getImageDTOsByListing(listingId));
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable Long imageId) {
        try {
            imageService.deleteImage(imageId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar la imagen");
        }
    }

    static class UploadResponse {
        public String rutaImagen;
        public UploadResponse(String rutaImagen) {
            this.rutaImagen = rutaImagen;
        }
    }
}
