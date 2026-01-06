package com.pracitce.journalApp.controller;

import com.pracitce.journalApp.entity.JournalEntry;
import com.pracitce.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService  journalEntryService;

    @GetMapping
    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryService.getJournalEntries();
    }

    @PostMapping
    public JournalEntry createJournalEntry(@RequestBody JournalEntry journalEntry) {
        journalEntry.setLocalDate((LocalDateTime.now()));
        journalEntryService.saveEntry(journalEntry);
        return journalEntry;
    }

    @GetMapping("id/{id}")
    public JournalEntry findByid(@PathVariable ObjectId id){
        return journalEntryService.findByid(id);
    }

    @DeleteMapping("id/{id}")
    public void deleteJournalEntryById(@PathVariable ObjectId id){
        journalEntryService.deleteJournalEntryById(id);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry){
        JournalEntry existingJournal = journalEntryService.findByid(id);
        if (existingJournal != null) {
            existingJournal.setTitle(journalEntry.getTitle() != null && !journalEntry.getTitle().equals("") ? journalEntry.getTitle() : existingJournal.getTitle());
            existingJournal.setContent(journalEntry.getContent() != null && !journalEntry.getContent().equals("") ? journalEntry.getContent() : existingJournal.getContent());
            journalEntryService.saveEntry(existingJournal);
        }
        return existingJournal;
    }
}
