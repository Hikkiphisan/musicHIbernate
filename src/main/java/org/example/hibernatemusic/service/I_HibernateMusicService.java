package org.example.hibernatemusic.service;

import org.example.hibernatemusic.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface I_HibernateMusicService {
    List<Song> findAll();

    void save(Song customer);

    Song findById(Long id);

    void remove(Long id);
}
