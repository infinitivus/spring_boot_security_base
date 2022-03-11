package com.infinitivus.project.spring_boot_security_base.service.person_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.infinitivus.project.spring_boot_security_base.entity.person.RepairWork;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface IRepairWorkService {
    @Secured({"ROLE_ADMIN","ROLE_MASTER"})
    RepairWork saveRepairWork(RepairWork repairWork, Integer homeId);

    @Secured({"ROLE_ADMIN","ROLE_MASTER"})
    RepairWork getOneWork(Integer id);

    @Secured({"ROLE_ADMIN","ROLE_MASTER"})
    RepairWork updateWork(Integer id,JsonPatch patch) throws JsonPatchException, JsonProcessingException;

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_MASTER"})
    List<RepairWork> getRepairWork(Integer homeId);

    @Secured({"ROLE_ADMIN","ROLE_MASTER"})
    List<RepairWork> allRepairWork();

    @Secured({"ROLE_ADMIN"})
    void deleteRepairWork(Integer workId);

    @Secured({"ROLE_ADMIN","ROLE_MASTER"})
    RepairWork applyPatchToRepairWork(JsonPatch patch, RepairWork repairWork) throws JsonPatchException, JsonProcessingException;
}
