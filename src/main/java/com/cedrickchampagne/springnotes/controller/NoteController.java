package com.cedrickchampagne.springnotes.controller;

import com.cedrickchampagne.springnotes.data.NoteRepository;
import com.cedrickchampagne.springnotes.entity.Note;
import com.cedrickchampagne.springnotes.exception.NoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping
    public List<Note> getNotes(){
        return noteRepository.findAll();
    }

    @GetMapping("/{noteID}")
    public Note getNotes(@PathVariable("noteID") int noteId){
        return noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException("Note not found."));
    }

    @PostMapping
    public Note addNote(@RequestBody Note note){
        note.setId(0);

        Note newNote = noteRepository.save(note);

        return newNote;
    }

    @PutMapping
    public Note updateNote(@RequestBody Note note){
        noteRepository.findById(note.getId()).orElseThrow(() -> new NoteNotFoundException("Note not found."));

        Note newNote = noteRepository.save(note);

        return newNote;
    }

    @DeleteMapping("/{noteID}")
    public String deleteNote(@PathVariable("noteID") int noteId){
        noteRepository.deleteById(noteId);

        return "Note deleted successfully";
    }
}
