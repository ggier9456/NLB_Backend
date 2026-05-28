package com.example.newslinebot.DAO;

import com.example.newslinebot.Model.NewsToCategory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsToCategoryDAO {
    public final JdbcTemplate jdbcTemplate;

    public NewsToCategoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int insert(String news_id, Integer c_id) {
        String sql = """
                INSERT INTO news_to_category (
                    news_id,
                    c_id
                ) VALUES (?, ?)
                """;
        return jdbcTemplate.update(sql, news_id, c_id);
    }

    public NewsToCategory findByNewsIdAndCategoryId(String news_id, Integer c_id) {
        String sql = """
                SELECT
                    *
                    FROM news_to_category
                    WHERE news_id = ? AND c_id = ?
                """;
        List<NewsToCategory> list = jdbcTemplate.query(sql,
                                        new BeanPropertyRowMapper<>(NewsToCategory.class),
                                        news_id,
                                        c_id);
        return list.isEmpty() ? null : list.get(0);
    }
}
