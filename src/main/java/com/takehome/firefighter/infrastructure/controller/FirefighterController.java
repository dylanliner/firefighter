package com.takehome.firefighter.infrastructure.controller;

import com.takehome.firefighter.domain.usecases.CreateFirefighterUseCase;
import com.takehome.firefighter.domain.usecases.ChangeFirefighterAvailabilityUseCase;
import com.takehome.firefighter.domain.usecases.DesignateFirefighterUseCase;
import com.takehome.firefighter.domain.usecases.QueryFirefighterHistoryUseCase;
import com.takehome.firefighter.infrastructure.controller.request.FirefighterRequest;
import com.takehome.firefighter.infrastructure.controller.response.FirefighterDTO;
import com.takehome.firefighter.infrastructure.controller.response.FirefighterHistoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class FirefighterController {

    private final DesignateFirefighterUseCase designateFirefighterUsecase;

    private final QueryFirefighterHistoryUseCase queryFirefighterHistoryUseCase;

    private final ChangeFirefighterAvailabilityUseCase changeFirefighterAvailabilityUseCase;

    private final CreateFirefighterUseCase createFirefighterUseCase;

    public FirefighterController(DesignateFirefighterUseCase designateFirefighterUsecase, QueryFirefighterHistoryUseCase queryFirefighterHistoryUseCase, ChangeFirefighterAvailabilityUseCase deactivateFirefighterUseCase, CreateFirefighterUseCase createFirefighterUseCase) {
        this.designateFirefighterUsecase = designateFirefighterUsecase;
        this.queryFirefighterHistoryUseCase = queryFirefighterHistoryUseCase;
        this.changeFirefighterAvailabilityUseCase = deactivateFirefighterUseCase;
        this.createFirefighterUseCase = createFirefighterUseCase;
    }

    @PostMapping("/firefighter/designateFirefighter")
    ResponseEntity<FirefighterDTO> designateFirefighter() {
        var firefighter = designateFirefighterUsecase.designateFirefighter();
        return ResponseEntity.of(Optional.of(FirefighterDTO.toDTO(firefighter)));
    }

    @PostMapping("/firefighter")
    ResponseEntity<Void> createFirefighter(@RequestBody FirefighterRequest firefighterRequest) {
        createFirefighterUseCase.createFirefighter(firefighterRequest.toDomain());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/firefighter")
    ResponseEntity<List<FirefighterHistoryDTO>> getFirefighterHistory() {
        var firefighterHistoryList = queryFirefighterHistoryUseCase.findAllFirefighterHistory();
        return ResponseEntity.of(Optional.of(firefighterHistoryList.stream().map(FirefighterHistoryDTO::toDto).collect(Collectors.toList())));
    }

    @PutMapping("/firefighter/{firefighterId}")
    ResponseEntity<Void> updateFirefighterAvailability(@PathVariable UUID firefighterId, @RequestParam boolean available) {
        changeFirefighterAvailabilityUseCase.updateFirefighterAvailability(firefighterId, available);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
