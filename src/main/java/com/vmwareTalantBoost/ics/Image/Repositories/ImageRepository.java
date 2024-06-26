package com.vmwareTalantBoost.ics.Image.Repositories;

import com.vmwareTalantBoost.ics.Image.Classes.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT s FROM Image s where s.url = ?1")
    Optional<Image> findImageExistByUrl(String url);

    @Query("SELECT s FROM Image s where s.id = ?1")
    Image findImageById(Long id);

    @Query("SELECT s FROM Image s where s.url = ?1")
    Image findImageByUrl(String url);

    @Query("SELECT s FROM Image s order by s.uploadTime ASC ")
    List<Image> findAllImages();

    @Query("SELECT s FROM Image s JOIN s.tags m WHERE m.name IN :tagsToSearch")
    List<Image> findImagesByTags(List<String> tagsToSearch);

    @Query("SELECT s FROM Image s where s.id = ?1")
    Optional<Image> findImageExistsById(Long id);

    @Query("SELECT s FROM Image s JOIN s.users m WHERE m.username IN :username")
    List<Image> findImagesByUsername(String username);


}
