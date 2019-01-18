package com.stackroute.MuzixApplication.service;

import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exception.TrackNotFoundException;
import com.stackroute.MuzixApplication.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService{
    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackId()))
        {
            throw new TrackAlreadyExistsException("Track already exists");
        }

        Track savedTrack=trackRepository.save(track);
        if(savedTrack==null)
        {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {

        return trackRepository.findAll();
    }


    @Override
    public boolean delete(int trackId) throws TrackNotFoundException {
        //boolean status=false;
        if(trackRepository.existsById(trackId)) {
            trackRepository.deleteById(trackId);
            //status=true;
            return true;
        }
        return false;
        //return status;
    }

    @Override
    public Track updateComments(int trackId,Track track){
        Track updatedTrack;
        updatedTrack=track;
        if(trackRepository.existsById(trackId)){
            updatedTrack = trackRepository.save(track);
            return updatedTrack;
        }
//        else {
//            throw new TrackNotFoundException("Track not found");
//        }
//        else {
//            return updatedTrack;
//        }
        return updatedTrack;
    }
}
