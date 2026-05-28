package com.example.newslinebot.Model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class News {
    private String guid;
    private String newsUrl;
    private String title;
    private String summary;
    private LocalDateTime pubDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
