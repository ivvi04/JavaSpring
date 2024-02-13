package ru.lakeevda.lesson6.service;

import ru.lakeevda.lesson6.domain.Characters;
import ru.lakeevda.lesson6.domain.Result;

public interface ServiceApi {
    public Characters getAllCharacters();

    public Characters getCharactersWithPage(String page);

    public Result getCharacterById(Integer id);
}
