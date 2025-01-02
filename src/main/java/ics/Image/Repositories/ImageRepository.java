package ics.Image.Repositories;

import ics.Image.Classes.Image;
import ics.Image.Classes.TopActiveUserDTO;
import ics.Image.Classes.TopTagDTO;
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

    @Query("SELECT s FROM Image s where s.user.id = ?1 order by s.uploadTime ASC")
    List<Image> findImagesByUserId(Long userId);

    @Query("SELECT s FROM Image s where s.privateImg = false ORDER BY s.likes DESC LIMIT 5")
    List<Image> findMostLikedImages();

    @Query("SELECT new ics.Image.Classes.TopTagDTO(t.name, COUNT(t)) " +
            "FROM Image i JOIN i.tags t " +
            "GROUP BY t.name " +
            "ORDER BY COUNT(t) DESC LIMIT 5")
    List<TopTagDTO> findTop5Tags();

    @Query("SELECT new ics.Image.Classes.TopActiveUserDTO(u.username, COUNT(u)) " +
            "FROM Image i JOIN i.user u " +
            "GROUP BY u.username " +
            "ORDER BY COUNT(u) DESC LIMIT 5")
    List<TopActiveUserDTO> findTop5UsersWithMostUploads();
}
