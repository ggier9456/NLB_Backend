package com.example.newslinebot.DAO;

import com.example.newslinebot.Model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAO {

    private final JdbcTemplate jdbcTemplate;

    public NewsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 新增一筆新聞
     */
    public int insert(News news) {
        String sql = """
            INSERT INTO news (
                guid,
                news_url,
                title,
                summary,
                pub_date,
                created_at,
                updated_at
            ) VALUES (?, ?, ?, ?, ?, NOW(), NOW())
        """;

        return jdbcTemplate.update(
                sql,
                news.getGuid(),
                news.getNewsUrl(),
                news.getTitle(),
                news.getSummary(),
                news.getPubDate()
        );
    }

    /**
     * 依 guid 查詢單筆
     */
    public News findByGuid(String guid) {
        String sql = "SELECT * FROM news WHERE guid = ?";
        List<News> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(News.class), guid);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * 查詢全部新聞（依發布時間排序）
     */
    public List<News> findAll() {
        String sql = """
            SELECT
                guid,
                news_url,
                title,
                summary,
                pub_date,
                created_at,
                updated_at
            FROM news
            ORDER BY pub_date DESC
        """;

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(News.class)
        );
    }

    /**
     * 刪除新聞
     */
    public int deleteByGuid(Integer guid) {
        String sql = "DELETE FROM news WHERE guid = ?";
        return jdbcTemplate.update(sql, guid);
    }
}
