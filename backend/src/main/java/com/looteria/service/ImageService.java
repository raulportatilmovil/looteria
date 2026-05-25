package com.looteria.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.looteria.entity.Image;
import com.looteria.entity.ListingPost;
import com.looteria.repository.ImageRepository;
import com.looteria.repository.ListingPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ListingPostRepository listingPostRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Transactional
    public String uploadImage(Long listingId, MultipartFile file) throws IOException {
        ListingPost listing = listingPostRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        long count = ((List<Image>) imageRepository.findByPublicacion_IdPublicacion(listingId)).size();
        if (count >= 3) {
            throw new RuntimeException("Máximo 3 imágenes por publicación");
        }

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("url");
        String publicId = (String) uploadResult.get("public_id");

        Image image = new Image();
        image.setPublicacion(listing);
        image.setRutaImagen(imageUrl);
        image.setPublicId(publicId);
        imageRepository.save(image);

        return imageUrl;
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

        String publicId = image.getPublicId();
        if (publicId != null) {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
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
