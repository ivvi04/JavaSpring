package ru.lakeevda.lesson6.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lakeevda.lesson6.domain.Characters;
import ru.lakeevda.lesson6.domain.Result;
import ru.lakeevda.lesson6.service.ServiceApi;

@Controller
@RequiredArgsConstructor
@RequestMapping("/character")
public class CharacterController {
    private final ServiceApi serviceApi;

    @GetMapping()
    public String getCharacters(@RequestParam(required = false) String page, Model model) {
        Characters characters;
        if (StringUtils.isEmpty(page)) characters = serviceApi.getAllCharacters();
        else characters = serviceApi.getCharactersWithPage(page);
        model.addAttribute("characters", characters.getResults());
        String prevUrl = characters.getInfo().getPrev();
        String nextUrl = characters.getInfo().getNext();
        if (prevUrl != null) {
            String prevPage = UriComponentsBuilder.fromUriString(prevUrl).build().getQueryParams().get("page").get(0);
            model.addAttribute("prevPage", prevPage);
        }
        if (nextUrl != null) {
            String nextPage = UriComponentsBuilder.fromUriString(nextUrl).build().getQueryParams().get("page").get(0);
            model.addAttribute("nextPage", nextPage);
        }
        return "characters";
    }

    @GetMapping("/{id}")
    public String getCharacterDetails(@PathVariable Integer id, Model model) {
        Result result = serviceApi.getCharacterById(id);
        model.addAttribute("pageTitle", "Персонаж " + result.getName());
        model.addAttribute("character", result);
        model.addAttribute("page", (id - 1) / 20 + 1);
        return "character";
    }

}
