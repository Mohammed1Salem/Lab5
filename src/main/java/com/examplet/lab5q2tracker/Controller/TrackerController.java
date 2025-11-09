package com.examplet.lab5q2tracker.Controller;

import com.examplet.lab5q2tracker.ApiResponse.ApiResponse;
import com.examplet.lab5q2tracker.Model.Tracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/tracker")
public class TrackerController {
    ArrayList<Tracker> trackers = new ArrayList<>();

    @GetMapping("/get-trackers")
    public ArrayList<Tracker> getTrackers(){
        return trackers;
    }

    @GetMapping("/get-tracker-by-title/{title}")
    public Tracker getTrackerByTitle(@PathVariable String title){
        for (Tracker tracker : trackers){
            if(tracker.getTitle().equalsIgnoreCase(title)){
                return tracker;
            }
        }
        return null;
    }

    @GetMapping("/get-projects-by-company-name/{companyName}")
    public ArrayList<Tracker> getProjectByCompanyName(@PathVariable String companyName){
        ArrayList<Tracker> projects = new ArrayList<>();
        for (Tracker tracker : trackers){
            if(tracker.getCompanyName().equalsIgnoreCase(companyName)){
                projects.add(tracker);
            }
        }
        return projects;
    }

    @PostMapping("/add-tracker")
    public ApiResponse addTracker(@RequestBody Tracker tracker){
        trackers.add(tracker);
        return new ApiResponse("Project tracker added");
    }

    @PutMapping("/update-tracker/{index}")
    public ApiResponse updateTracker(@RequestBody Tracker tracker,@PathVariable int index){
        trackers.set(index,tracker);
        return new ApiResponse("Project tracker updated");
    }

    @PutMapping("/update-tracker-status/{index}")
    public ApiResponse updateTrackerStatus(@PathVariable int index){
        if(trackers.get(index).getStatus().equalsIgnoreCase( "true")){
            return new ApiResponse("Project is already done");
        }else{
            trackers.get(index).setStatus("True");
            return new ApiResponse("Project status updated to (done)");
        }
    }

    @DeleteMapping("/delete-tracker/{index}")
    public ApiResponse deleteTracker(@PathVariable int index){
        trackers.remove(index);
        return new ApiResponse("Project tracker deleted");
    }
}
