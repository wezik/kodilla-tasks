package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloValidatorTest {

    private final TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    void shouldFilterTestBoards() {
        // Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("0","TeSt",new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("1","Board",new ArrayList<>()));

        // When
        List<TrelloBoard> resultList = trelloValidator.validateTrelloBoards(trelloBoards);

        // Then
        assertNotNull(resultList);
        assertEquals(resultList.size(),1);
    }

}