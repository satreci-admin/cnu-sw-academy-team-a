package com.example.simplerpa.controller;

import com.example.simplerpa.model.Email;

import java.time.LocalDateTime;

public record CreateWorkRequest(String email, String name, String contents, String schedulerCron) {
}
