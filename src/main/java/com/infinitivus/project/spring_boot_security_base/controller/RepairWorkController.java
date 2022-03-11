package com.infinitivus.project.spring_boot_security_base.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.infinitivus.project.spring_boot_security_base.entity.person.RepairWork;
import com.infinitivus.project.spring_boot_security_base.service.person_service.IRepairWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repairWorks")
public class RepairWorkController {

    @Autowired
    private IRepairWorkService repairWorkService;

    //  Creating a new repair record for a motorhome with id
// POST http://localhost:8080/repairWorks/create/1
//     {"nameTheWork":"customer remont", "master":"ALEX", "cost":"999"}
    @PostMapping("/create/{homeId}")
    public ResponseEntity<String> createRepairWork(@RequestBody RepairWork repairWork,@PathVariable Integer homeId) {
        ResponseEntity<String> resp;
        try {
            RepairWork work = repairWorkService.saveRepairWork(repairWork,homeId);
            resp = new ResponseEntity<>(
                    "RepairWork '" + repairWork.getId() + "' created:", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to save repair work",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Output a list of all repairs
// GET  http://localhost:8080/repairWorks/getAll
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllRepairWork() {
        ResponseEntity<?> resp;
        try {
            List<RepairWork> repairWork = repairWorkService.allRepairWork();
            resp = new ResponseEntity<>(repairWork, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to get all repair work",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // getting repairs by motorhome id
// GET http://localhost:8080/repairWorks/getWorks/1
    @GetMapping("/getWorks/{homeId}")
    public ResponseEntity<?> getRepairWork(@PathVariable Integer homeId) {
        ResponseEntity<?> resp;
        try {
            List<RepairWork>  repairWork= repairWorkService.getRepairWork(homeId);
            resp = new ResponseEntity<>(repairWork, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to find repair work by id mobile home",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Deleting repair information by id
//DELETE http://localhost:8080/repairWorks/remove/1
    @DeleteMapping("remove/{id}")
    public ResponseEntity<String> deleteRepairWork(@PathVariable Integer id) {
        ResponseEntity<String> resp;
        try {
            repairWorkService.deleteRepairWork(id);
            resp = new ResponseEntity<>(
                    "RepairWork '" + id + "' deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to delete repair work", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    //   change of repair data by id
//PATCH  http://localhost:8080/repairWorks/modify/1
//       [{"op":"replace", "path":"/nameTheWork", "value":"rabota"}]
    @PatchMapping(path = "/modify/{id}")
    public ResponseEntity<String> updateRepairWork(@PathVariable Integer id, @RequestBody JsonPatch patch) {
        ResponseEntity<String> resp ;
        try {
           RepairWork repairWork= repairWorkService.updateWork(id,patch);
            resp = new ResponseEntity<>(
                    "RepairWork '" + repairWork.getId() + "' updated:",
                    HttpStatus.PARTIAL_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to update repair work",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }
}
