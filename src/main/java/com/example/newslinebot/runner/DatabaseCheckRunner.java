package com.example.newslinebot.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCheckRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseCheckRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            if (result != null && result == 1) {
                System.out.println("✅ 資料庫連線成功！");
            }
        } catch (Exception e) {
            System.err.println("❌ 資料庫連線失敗: " + e.getMessage());
            System.exit(1); // 連線失敗就停止啟動
        }
    }
}