package com.crm.miniCRM.controller;

import com.crm.miniCRM.dto.EventDto;
import com.crm.miniCRM.controller.model.Event;
import com.crm.miniCRM.controller.model.persistence.interfaces.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public String getevent(Model model) {
        Iterable<Event> events = eventRepository.findAll();
        List<EventDto> eventDtos = new ArrayList<>();
        events.forEach(p -> eventDtos.add(convertToDto(p)));
        model.addAttribute("events", eventDtos);
        return "events";
    }

    @GetMapping("/new")
    public String newevent(Model model) {
        model.addAttribute("event", new EventDto());
        return "new-event";
    }

    @PostMapping
    public String addevent(EventDto event) {
        eventRepository.save(convertToEntity(event));
        return "redirect:/events";
    }

    @GetMapping("/edit/{id}")
    public String editEvent(Model model, @PathVariable("id") Long id) {
        Optional<Event> event = null;
        event = eventRepository.findById(id);
        model.addAttribute("event", event);
        return "edit-event";
    }

    @PostMapping("/edit")
    public String saveEvent(@ModelAttribute("event") Event event) {
        return "events";
    }

    protected EventDto convertToDto(Event entity) {
        return new EventDto(entity.getId(), entity.getDescription(), entity.getDate().toString());
    }

    protected Event convertToEntity(EventDto dto) {
        int year = Integer.parseInt(dto.getDate().toString().substring(6, 10));
        int month = Integer.parseInt(dto.getDate().toString().substring(3, 5));
        int day = Integer.parseInt(dto.getDate().toString().substring(0, 2));
        Event event = new Event(dto.getDescription(), LocalDate.of(year, month, day));
        if (!StringUtils.isEmpty(dto.getId())) {
            event.setId(dto.getId());
        }
        return event;
    }

}
