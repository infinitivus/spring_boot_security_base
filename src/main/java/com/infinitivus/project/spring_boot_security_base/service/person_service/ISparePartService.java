package com.infinitivus.project.spring_boot_security_base.service.person_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.infinitivus.project.spring_boot_security_base.entity.person.SpareParts;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface ISparePartService {
    @Secured({"ROLE_ADMIN","ROLE_MASTER"})
    SpareParts saveSpareParts(SpareParts spareParts);

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_MASTER"})
    SpareParts getSpareParts(Integer id);

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_MASTER"})
    List<SpareParts> allSpareParts();

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_MASTER"})
    List<SpareParts> searchSpareParts(String line);

    @Secured({"ROLE_ADMIN","ROLE_MASTER"})
    List<SpareParts> sortSpareParts();

    @Secured({"ROLE_ADMIN"})
    void deleteSpareParts(Integer id);

    SpareParts updateSpareParts(Integer id, JsonPatch patch) throws JsonPatchException, JsonProcessingException;

    @Secured({"ROLE_ADMIN","ROLE_MASTER"})
    SpareParts applyPatchToSpareParts(JsonPatch patch, SpareParts spareParts) throws JsonPatchException, JsonProcessingException;

    @Secured({"ROLE_ADMIN","ROLE_MASTER"})
    void addPartToWork(Integer workId, Integer partId);
}
