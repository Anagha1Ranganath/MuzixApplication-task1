package com.stackroute.MuzixApplication.service;

import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exception.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getAllTracks();

    public boolean delete(int trackId) throws TrackNotFoundException;

    public Track updateComments(int trackId,Track track);
}
