package com.looteria.service;

import com.looteria.entity.Image;
import com.looteria.entity.ListingPost;
import com.looteria.repository.ImageRepository;
import com.looteria.repository.ListingPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ListingPostRepository listingPostRepository;

    @Value("${upload.dir:uploads}")
    private String uploadDir;

    @Transactional
    public String uploadImage(Long listingId, MultipartFile file) throws IOException {
        ListingPost listing = listingPostRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        long count = ((List<Image>) imageRepository.findByPublicacion_IdPublicacion(listingId)).size();
        if (count >= 3) {
            throw new RuntimeException("Máximo 3 imágenes por publicación");
        }

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String extension = "";
        String originalName = file.getOriginalFilename();
        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID().toString() + extension;
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        String rutaImagen = "/imagenes/serve/" + fileName;

        Image image = new Image();
        image.setPublicacion(listing);
        image.setRutaImagen(rutaImagen);
        imageRepository.save(image);

        return rutaImagen;
    }

    @Transactional(readOnly = true)
    public List<String> getImagesByListing(Long listingId) {
        Iterable<Image> images = imageRepository.findByPublicacion_IdPublicacion(listingId);
        List<String> rutas = new ArrayList<>();
        for (Image img : images) {
            rutas.add(img.getRutaImagen());
        }
        return rutas;
    }

    @Transactional
    public void deleteImage(Long imageId) throws IOException {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        String ruta = image.getRutaImagen();
        if (ruta != null) {
            String fileName = ruta.contains("/") ? ruta.substring(ruta.lastIndexOf("/") + 1) : ruta;
            Path filePath = Paths.get(uploadDir).resolve(fileName);
            Files.deleteIfExists(filePath);
        }

        imageRepository.deleteById(imageId);
    }

    @Transactional(readOnly = true)
    public List<ImageDTO> getImageDTOsByListing(Long listingId) {
        Iterable<Image> images = imageRepository.findByPublicacion_IdPublicacion(listingId);
        List<ImageDTO> result = new ArrayList<>();
        for (Image img : images) {
            result.add(new ImageDTO(img.getIdImagen(), img.getRutaImagen()));
        }
        return result;
    }

    public static class ImageDTO {
        public Long idImagen;
        public String rutaImagen;

        public ImageDTO(Long idImagen, String rutaImagen) {
            this.idImagen = idImagen;
            this.rutaImagen = rutaImagen;
        }
    }
}
