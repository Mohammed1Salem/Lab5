package com.examplet.lab5q3eventsystem.Controller;

import com.examplet.lab5q3eventsystem.ApiResponse.ApiResponse;
import com.examplet.lab5q3eventsystem.Model.EventSystem;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/event-system")
public class EventSystemController {
    ArrayList<EventSystem> events = new ArrayList<>();

    @GetMapping("/get-events")
    public ArrayList<EventSystem> getEvents(){
        return events;
    }

    @GetMapping("/get-event-by-id/{id}")
    public EventSystem getEventById(@PathVariable String id){
        for(EventSystem event : events){
            if(event.getId().equalsIgnoreCase(id)){
                return event;
            }
        }
        return null;
    }

    @PostMapping("/add-event")
    public ApiResponse addEvent(@RequestBody EventSystem event){
        event.setStartDate(LocalDateTime.now());
        events.add(event);
        return new ApiResponse("Event added");
    }

    @PutMapping("/update-event/{index}")
    public ApiResponse updateEvent(@RequestBody EventSystem event,@PathVariable int index){
        events.set(index,event);
        return new ApiResponse("Event updated");
    }

    @PutMapping("/update-event-capacity/{index}/{capacity}")
    public ApiResponse updateCapacity(@PathVariable int index,@PathVariable int capacity){
        events.get(index).setCapacity(capacity);
        return new ApiResponse("Capacity updated");
    }

    @DeleteMapping("/delete-event/{index}")
    public ApiResponse deleteEvent(@PathVariable int index){
        events.remove(index);
        return new ApiResponse("Event deleted");
    }

}
