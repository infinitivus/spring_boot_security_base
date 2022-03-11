package com.infinitivus.project.spring_boot_security_base.repository.person_repository;

import com.infinitivus.project.spring_boot_security_base.entity.person.SpareParts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISparePartsRepository extends JpaRepository<SpareParts,Integer> {

    List<SpareParts> findByNameSparePartOrArticle
            (String nameSparePart,String article);
}
