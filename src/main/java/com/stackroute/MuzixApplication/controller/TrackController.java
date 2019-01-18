package com.stackroute.MuzixApplication.controller;

import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class TrackController {
    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {

        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Created", HttpStatus.CREATED);

        }
        catch(Exception exception)
        {
            responseEntity=new ResponseEntity<String>(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTracks()
    {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @DeleteMapping("track/{trackId}")
    public ResponseEntity<?> delete(@PathVariable(value = "trackId") int trackId) {
        try {
            trackService.delete(trackId);
            return new ResponseEntity<String>("deleted", HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("track/{trackId}")
    public ResponseEntity<?> update(@RequestBody Track trackRequest,@PathVariable(value = "trackId") int trackId){
        try{
            Track track= trackService.updateComments(trackId,trackRequest);
            return new ResponseEntity<Track>(track, HttpStatus.OK);
        }
        catch (Exception trackNotFoundException) {
            return new ResponseEntity<>(trackNotFoundException.getMessage(), HttpStatus.CONFLICT);

        }
    }
}
