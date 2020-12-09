package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public void getTrelloBoards() throws BoardsNotFoundException {
        if (trelloClient.getTrelloBoards().isEmpty()) throw new BoardsNotFoundException();
        List<TrelloBoardDto> trelloBoards;
        trelloBoards = trelloClient.getTrelloBoards().get();
        trelloBoards.stream()
                .filter(e -> (e.getId() != null && e.getName().contains("Kodilla")))
                .forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
        });
    }
}
