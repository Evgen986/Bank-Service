package ru.maliutin.bankapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maliutin.bankapi.web.dto.ClientDto;
import ru.maliutin.bankapi.web.mapper.ClientMapper;
import ru.maliutin.bankapi.model.Client;
import ru.maliutin.bankapi.service.SearchService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;
    private final ClientMapper clientMapper;

    @GetMapping("/birthday")
    public ResponseEntity<List<ClientDto>> getByBirthday(
            @RequestParam("date") LocalDate date,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sort_by_year", required = false) boolean sort){
        List<Client> findClients;
        if (page == null && size == null){
            findClients = searchService.findOlderDate(date, sort);
        }else{
            findClients = searchService.findOlderDate(date, page, size, sort);
        }
        return ResponseEntity.ok(findClients.stream().map(clientMapper::toDto).toList());
    }

    @GetMapping("/phone")
    public ResponseEntity<ClientDto> getByPhone(
            @RequestParam String phone){
        return ResponseEntity.ok(
                clientMapper.toDto(searchService.findByPhone(phone)));
    }

    @GetMapping("/name")
    public ResponseEntity<List<ClientDto>> getByName(
            @RequestParam String name,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "sort_by_name", required = false) boolean sort){
        List<Client> findClients;
        name = name + "%";
        if (page == null && size == null){
            findClients = searchService.findByName(name, sort);
        }else{
            findClients = searchService.findByName(name, page, size, sort);
        }
        return ResponseEntity.ok(findClients
                .stream().map(clientMapper::toDto).toList());
    }

    @GetMapping("/email")
    public ResponseEntity<ClientDto> getByEmail(@RequestParam String email){
        return ResponseEntity.ok(
                clientMapper.toDto(searchService.findByEmail(email)));
    }
}
