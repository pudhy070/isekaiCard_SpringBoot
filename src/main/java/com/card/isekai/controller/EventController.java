package com.card.isekai.controller;

import com.card.isekai.dto.EventDto;
import com.card.isekai.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDto.Response>> getAllEvents() {
        return ResponseEntity.ok(eventService.findAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto.Response> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.findEventById(id));
    }

    @PostMapping
    public ResponseEntity<EventDto.Response> createEvent(@RequestBody EventDto.CreateRequest createRequest) {
        EventDto.Response createdEvent = eventService.createEvent(createRequest);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto.Response> updateEvent(@PathVariable Long id, @RequestBody EventDto.UpdateRequest updateRequest) {
        return ResponseEntity.ok(eventService.updateEvent(id, updateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
