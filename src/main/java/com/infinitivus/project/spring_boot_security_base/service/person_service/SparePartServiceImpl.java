package com.infinitivus.project.spring_boot_security_base.service.person_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.infinitivus.project.spring_boot_security_base.entity.person.RepairWork;
import com.infinitivus.project.spring_boot_security_base.entity.person.SpareParts;
import com.infinitivus.project.spring_boot_security_base.repository.person_repository.IRepairWorkRepository;
import com.infinitivus.project.spring_boot_security_base.repository.person_repository.ISparePartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SparePartServiceImpl implements ISparePartService {

    @Autowired
    private ISparePartsRepository sparePartsRepository;

    @Autowired
    private IRepairWorkRepository repairWorkRepository;

    @Override
    public List<SpareParts> allSpareParts() {
        return sparePartsRepository.findAll();
    }

    @Override
    public SpareParts saveSpareParts(SpareParts spareParts) {
        return sparePartsRepository.save(spareParts);
    }

    @Override
    public SpareParts getSpareParts(Integer id) {
        return sparePartsRepository.findById(id).get();
    }

    @Override
    public void deleteSpareParts(Integer id) {
        sparePartsRepository.deleteById(id);
    }

    @Override
    public void addPartToWork(Integer workId, Integer partId) {
        RepairWork repairWork = repairWorkRepository.getById(workId);
        repairWork.addSparePartsToRepairWork(sparePartsRepository.getById(partId));
        repairWorkRepository.save(repairWork);
    }

    @Override
    public List<SpareParts> searchSpareParts(String line) {
        return sparePartsRepository.findByNameSparePartOrArticle(line, line);
    }

    @Override
    public List<SpareParts> sortSpareParts() {
        return sparePartsRepository.findAll(Sort.by("nameSparePart"));
    } //???

    @Override
    public SpareParts updateSpareParts(Integer id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        SpareParts spareParts = getSpareParts(id);
        SpareParts sparePartsPatched = applyPatchToSpareParts(patch, spareParts);
        return saveSpareParts(sparePartsPatched);
    }

    @Override
    public SpareParts applyPatchToSpareParts(JsonPatch patch, SpareParts spareParts) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(spareParts, JsonNode.class));
        return objectMapper.treeToValue(patched, SpareParts.class);
    }
}
