package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloMapperTest {

    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    void testMapToBoards() {
        //Given
        List<TrelloListDto> trelloLists1 = new ArrayList<>();
        List<TrelloListDto> trelloLists2 = new ArrayList<>();
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloLists1.add(new TrelloListDto("3","different",false));
        trelloLists1.add(new TrelloListDto("4","every",true));
        trelloBoards.add(new TrelloBoardDto("7","something",trelloLists1));
        trelloLists2.add(new TrelloListDto("5","time",false));
        trelloLists2.add(new TrelloListDto("6","its even",true));
        trelloBoards.add(new TrelloBoardDto("8","randomized",trelloLists2));

        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoards);

        //Then
        assertEquals(result.size(),2);
        assertEquals(result.get(0).getLists().get(0).getId(),trelloBoards.get(0).getLists().get(0).getId());
        assertEquals(result.get(1).getLists().get(1).getName(),trelloBoards.get(1).getLists().get(1).getName());

    }

    @Test
    void testMapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists1 = new ArrayList<>();
        List<TrelloList> trelloLists2 = new ArrayList<>();
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloLists1.add(new TrelloList("3","different",false));
        trelloLists1.add(new TrelloList("4","every",true));
        trelloBoards.add(new TrelloBoard("7","something",trelloLists1));
        trelloLists2.add(new TrelloList("5","time",false));
        trelloLists2.add(new TrelloList("6","its even",true));
        trelloBoards.add(new TrelloBoard("8","randomized",trelloLists2));

        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(result.size(),2);
        assertEquals(result.get(0).getLists().get(0).getId(),trelloBoards.get(0).getLists().get(0).getId());
        assertEquals(result.get(0).getLists().get(0).isClosed(),trelloBoards.get(0).getLists().get(0).isClosed());
        assertEquals(result.get(1).getLists().get(1).getName(),trelloBoards.get(1).getLists().get(1).getName());

    }

    @Test
    void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Some Name","Description","6","1");

        //When
        TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(result.getName(),trelloCard.getName());
        assertEquals(result.getDescription(),trelloCard.getDescription());
        assertEquals(result.getListId(),trelloCard.getListId());
        assertEquals(result.getPos(),trelloCard.getPos());

    }

    @Test
    void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Some Name","Description","6","1");

        //When
        TrelloCard result = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(result.getName(),trelloCardDto.getName());
        assertEquals(result.getDescription(),trelloCardDto.getDescription());
        assertEquals(result.getListId(),trelloCardDto .getListId());
        assertEquals(result.getPos(),trelloCardDto.getPos());

    }

}
