package com.card.isekai.service;

import com.card.isekai.dto.EventDto;
import com.card.isekai.entity.Event;
import com.card.isekai.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDto.Response> findAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public EventDto.Response findEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + id));
        return convertToDto(event);
    }

    @Transactional
    public EventDto.Response createEvent(EventDto.CreateRequest createRequest) {
        Event event = new Event();
        event.setTitle(createRequest.getTitle());
        event.setContent(createRequest.getContent());
        event.setAuthor(createRequest.getAuthor());
        Event savedEvent = eventRepository.save(event);
        return convertToDto(savedEvent);
    }

    @Transactional
    public EventDto.Response updateEvent(Long id, EventDto.UpdateRequest updateRequest) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + id));
        event.setTitle(updateRequest.getTitle());
        event.setContent(updateRequest.getContent());
        Event updatedEvent = eventRepository.save(event);
        return convertToDto(updatedEvent);
    }

    @Transactional
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + id));
        eventRepository.delete(event);
    }

    private EventDto.Response convertToDto(Event event) {
        EventDto.Response response = new EventDto.Response();
        response.setId(event.getId());
        response.setTitle(event.getTitle());
        response.setContent(event.getContent());
        response.setAuthor(event.getAuthor());
        response.setCreatedAt(event.getCreatedAt());
        response.setUpdatedAt(event.getUpdatedAt());
        return response;
    }
}
