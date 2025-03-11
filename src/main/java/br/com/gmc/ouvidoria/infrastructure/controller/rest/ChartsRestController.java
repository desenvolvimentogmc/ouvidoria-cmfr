package br.com.gmc.ouvidoria.infrastructure.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.gmc.ouvidoria.usecase.request.CountRequestsByStatus;
import br.com.gmc.ouvidoria.usecase.request.CountRequestsByRequestType;
import br.com.gmc.ouvidoria.infrastructure.dto.RequestsByStatusDTO;
import br.com.gmc.ouvidoria.infrastructure.dto.RequestsBySubjectDTO;
import br.com.gmc.ouvidoria.enums.Status;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/charts")
public class ChartsRestController {

    private final CountRequestsByStatus countRequestsByStatus;
    private final CountRequestsByRequestType countRequestsByRequestType;

    public ChartsRestController(CountRequestsByStatus countRequestsByStatus, CountRequestsByRequestType countRequestsByRequestType) {
        this.countRequestsByStatus = countRequestsByStatus;
        this.countRequestsByRequestType = countRequestsByRequestType;
    }

    @GetMapping("/status")
    public ResponseEntity<List<RequestsByStatusDTO>> get() {

        List<RequestsByStatusDTO> statusDTOs = new ArrayList<>();

        statusDTOs.add(new RequestsByStatusDTO(Status.OPEN, countRequestsByStatus.execute(Status.OPEN)));
        statusDTOs.add(new RequestsByStatusDTO(Status.UNDER_ANALYSIS, countRequestsByStatus.execute(Status.UNDER_ANALYSIS)));
        statusDTOs.add(new RequestsByStatusDTO(Status.ANSWERED, countRequestsByStatus.execute(Status.ANSWERED)));
        statusDTOs.add(new RequestsByStatusDTO(Status.APPEAL, countRequestsByStatus.execute(Status.APPEAL)));
        statusDTOs.add(new RequestsByStatusDTO(Status.CLOSED, countRequestsByStatus.execute(Status.CLOSED)));

        return ResponseEntity.ok(statusDTOs);
    }
    
    @GetMapping("/subjects")
    public ResponseEntity<List<RequestsBySubjectDTO>> getBySubject() {
        return ResponseEntity.ok(this.countRequestsByRequestType.execute());
    }
    
    
}
