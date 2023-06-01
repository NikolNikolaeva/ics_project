package com.vmwareTalantBoost.ics.Image.Repositories;


import com.vmwareTalantBoost.ics.Image.Classes.Image;
import com.vmwareTalantBoost.ics.Image.Classes.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {

    @Query("SELECT s FROM Tag s where s.name = ?1 AND s.confidence=?2")
    Optional<Tag> findTagByNameAndConfidence(String name,int confidence);

    @Query("SELECT s.id FROM Tag s where s.name = ?1 AND s.confidence=?2")
    Long findTagId(String name,int confidence);

    @Query("SELECT s FROM Tag s where s.id = ?1")
    Tag findTagByTagId(Long tagId);

    @Query("SELECT s.id FROM Tag s where s.name = ?1")
    Optional<Tag> findTagByName(String name);

    @Query("SELECT s FROM Tag s where s.name LIKE ?1%")
    List<Tag> findTagByStr(String str);
}
