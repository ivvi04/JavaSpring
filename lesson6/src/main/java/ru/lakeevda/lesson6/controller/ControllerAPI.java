package ru.lakeevda.lesson6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lakeevda.lesson6.domain.Characters;
import ru.lakeevda.lesson6.service.ServiceApi;

@RestController
@RequiredArgsConstructor
public class ControllerAPI {
    private final ServiceApi serviceApi;

    @GetMapping("/")
    public ResponseEntity<Characters> getCharacters() {
        Characters characters = serviceApi.getAllCharacters();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }
}
