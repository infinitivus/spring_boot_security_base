package com.infinitivus.project.spring_boot_security_base.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.infinitivus.project.spring_boot_security_base.entity.person.SpareParts;
import com.infinitivus.project.spring_boot_security_base.service.person_service.ISparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spareParts")
public class SparePartController {

    @Autowired
    private ISparePartService sparePartService;

    // Creating a new spare part record ok
// POST http://localhost:8080/part/create
//     {"nameSparePart":"PART PART","article":"111234","costPart":"999"}
    @PostMapping("/create")
    public ResponseEntity<String> createSpareParts(@RequestBody SpareParts spareParts) {
        ResponseEntity<String> resp;
        try {
          SpareParts parts= sparePartService.saveSpareParts(spareParts);
            resp = new ResponseEntity<>(
                    "Spare part '"+parts.getId()+"' created", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to save spare part",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Output a list of all spare parts about
//  GET http://localhost:8080/part/getAll
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllSpareParts() {
        ResponseEntity<?> resp;
        try {
            List<SpareParts> parts = sparePartService.allSpareParts();
            resp = new ResponseEntity<>(parts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to get all spare parts",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Output of all sorted spare parts by the name SparePart field is ok
//  GET http://localhost:8080/part/sort
    @GetMapping("/sort")
    public ResponseEntity<?> SortSpareParts() {
        ResponseEntity<?> resp;
        try {
            List<SpareParts> parts = sparePartService.sortSpareParts();
            resp = new ResponseEntity<>(parts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to get all sort spare parts",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Search for spare parts by line (nameSparePart & article) ok
//  GET  http://localhost:8080/part/search/parts
    @GetMapping("/search/{line}")
    public ResponseEntity<?> SearchSparePart(@PathVariable String line) {
        ResponseEntity<?> resp;
        try {
            List<SpareParts> parts = sparePartService.searchSpareParts(line);
            resp = new ResponseEntity<>(parts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to get all search spare parts",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Deleting a record about a spare part that is not linked to work by id ok
//DELETE http://localhost:8080/part/remove/1
    @DeleteMapping("remove/{id}")
    public ResponseEntity<String> deleteSpareParts(@PathVariable Integer id) {
        ResponseEntity<String> resp;
        try {
            sparePartService.deleteSpareParts(id);
            resp = new ResponseEntity<>(
                    "SpareParts '" + id + "' deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to delete spare part", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Changing the spare part data by id ok
//PATCH http://localhost:8080/part/modify/1
//     [{"op":"replace","path":"/nameSparePart","value":"SPARE PART"}]
    @PatchMapping(path = "/modify/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable Integer id, @RequestBody JsonPatch patch) {
        ResponseEntity<String> resp;
        try {
           SpareParts spareParts= sparePartService.updateSpareParts(id,patch);
            resp = new ResponseEntity<>(
                    "Spare part '"+spareParts.getId()+"' updated:", HttpStatus.PARTIAL_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to update spare part",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // adding spare parts to work by id
// POST http://localhost:8080/part/add/1/1
    @PostMapping(path = "/add/{workId}/{partId}")
    public ResponseEntity<String> addPartToWork(@PathVariable Integer workId, @PathVariable Integer partId) {
        ResponseEntity<String> resp;
        try {
            sparePartService.addPartToWork(workId, partId);
            resp = new ResponseEntity<>(
                    "SpareParts '" + partId + "' add to work " + workId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable add spare part to work", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }
}


