package ru.lakeevda.lesson6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.lakeevda.lesson6.domain.Characters;
import ru.lakeevda.lesson6.domain.Result;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceApiImpl implements ServiceApi {
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    @Value("${external.api.url}")
    private String CHARACTER_API;

    @Override
    public Characters getAllCharacters() {
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> stringHttpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Characters> charactersResponseEntity = restTemplate.exchange(CHARACTER_API,
                HttpMethod.GET, stringHttpEntity, Characters.class);
        return charactersResponseEntity.getBody();
    }

    @Override
    public Result getCharacterById(Integer id) {
        String uriString = UriComponentsBuilder.fromHttpUrl(CHARACTER_API)
                .pathSegment(id.toString())
                .toUriString();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> stringHttpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Result> resultResponseEntity = restTemplate.exchange(uriString,
                HttpMethod.GET, stringHttpEntity, Result.class);
        return resultResponseEntity.getBody();
    }

    @Override
    public Characters getCharactersWithPage(String page) {
        String uriString = UriComponentsBuilder.fromHttpUrl(CHARACTER_API)
                .queryParam("page", page)
                .toUriString();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> stringHttpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Characters> charactersResponseEntity = restTemplate.exchange(uriString,
                HttpMethod.GET, stringHttpEntity, Characters.class);
        return charactersResponseEntity.getBody();
    }
}
