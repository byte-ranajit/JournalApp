package com.pracitce.journalApp.controller;

import com.pracitce.journalApp.entity.JournalEntry;
import com.pracitce.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    private final JournalEntryService journalEntryService;

    public JournalEntryControllerV2(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @GetMapping
    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryService.getJournalEntries();
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry) {
        journalEntry.setLocalDateTime(LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);
        return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> findById(@PathVariable ObjectId id) {
        JournalEntry entry = journalEntryService.findByid(id);

        if (entry != null) {
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteJournalEntryById(@PathVariable ObjectId id) {
        JournalEntry entry = journalEntryService.findByid(id);

        if (entry != null) {
            journalEntryService.deleteJournalEntryById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntry(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry journalEntry) {

        JournalEntry existingJournal = journalEntryService.findByid(id);

        if (existingJournal != null) {

            if (journalEntry.getTitle() != null && !journalEntry.getTitle().isEmpty()) {
                existingJournal.setTitle(journalEntry.getTitle());
            }

            if (journalEntry.getContent() != null && !journalEntry.getContent().isEmpty()) {
                existingJournal.setContent(journalEntry.getContent());
            }

            journalEntryService.saveEntry(existingJournal);
            return new ResponseEntity<>(existingJournal, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}