//package com.vmwareTalantBoost.ics.Image.Repositories;
//
//import com.vmwareTalantBoost.ics.Image.Classes.Image_tag;
//import com.vmwareTalantBoost.ics.Image.Classes.Tag;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface Image_tagRepository extends JpaRepository<Image_tag,Long> {
//    @Query("SELECT s FROM Image_tag s where s.id_image = ?1")
//    Optional<Image_tag> findImageTagsByImageId(Long imageId);
//
//   // @Query("SELECT s.tags FROM Image_tag s where s.id_image = ?1")
//    //List<Long> findTagIdsByImageId(Long imageId);
//
//}
