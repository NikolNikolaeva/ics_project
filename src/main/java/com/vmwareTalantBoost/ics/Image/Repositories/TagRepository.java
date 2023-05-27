package com.vmwareTalantBoost.ics.Image.Repositories;


import com.vmwareTalantBoost.ics.Image.Classes.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
}
