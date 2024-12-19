package org.example.hibernatemusic.controller;

import org.example.hibernatemusic.model.Song;
import org.example.hibernatemusic.service.HibernateMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final HibernateMusicService songService;

    @Autowired
    public SongController(HibernateMusicService songService) {
        this.songService = songService;
    }

    // Lấy tất cả các bài hát và hiển thị trên trang index.html
    @GetMapping
    public String getAllSongs(Model model) {
        List<Song> songs = songService.findAll();
        model.addAttribute("songs", songs);  // Thêm danh sách bài hát vào model
        return "index";  // Trả về tên view (file HTML)
    }

    // Lấy thông tin bài hát theo id và hiển thị trang chi tiết
    @GetMapping("/{id}")
    public String getSongById(@PathVariable Long id, Model model) {
        Song song = songService.findById(id);
        if (song != null) {
            model.addAttribute("song", song);  // Thêm bài hát vào model
            return "songDetail";  // Trả về tên view chi tiết (songDetail.html)
        } else {
            return "404";  // Nếu không tìm thấy, hiển thị trang lỗi 404
        }
    }

    // Tạo mới bài hát, redirect về trang danh sách sau khi tạo
    @PostMapping
    public String createSong(@ModelAttribute Song song) {
        songService.save(song);
        return "redirect:/songs";  // Redirect đến trang danh sách bài hát
    }

    // Cập nhật thông tin bài hát
    @PutMapping("/{id}")
    public String updateSong(@PathVariable Long id, @ModelAttribute Song song) {
        Song existingSong = songService.findById(id);
        if (existingSong != null) {
            song.setId(id);
            songService.save(song);
            return "redirect:/songs";  // Redirect về trang danh sách bài hát
        } else {
            return "404";  // Nếu không tìm thấy, hiển thị trang lỗi 404
        }
    }

    // Xóa bài hát
    @DeleteMapping("/{id}")
    public String deleteSong(@PathVariable Long id) {
        Song song = songService.findById(id);
        if (song != null) {
            songService.remove(id);
            return "redirect:/songs";  // Redirect đến trang danh sách bài hát
        } else {
            return "404";  // Nếu không tìm thấy, hiển thị trang lỗi 404
        }
    }
}
