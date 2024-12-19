package org.example.hibernatemusic.controller;


import org.example.hibernatemusic.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.hibernatemusic.service.HibernateMusicService;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private HibernateMusicService songService;

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.findAll();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Song song = songService.findById(id);
        if (song != null) {
            return ResponseEntity.ok(song);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createSong(@RequestBody Song song) {
        songService.save(song);
        return ResponseEntity.ok("Song created successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSong(@PathVariable Long id, @RequestBody Song song) {
        Song existingSong = songService.findById(id);
        if (existingSong != null) {
            song.setId(id);
            songService.save(song);
            return ResponseEntity.ok("Song updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        Song song = songService.findById(id);
        if (song != null) {
            songService.remove(id);
            return ResponseEntity.ok("Song deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}