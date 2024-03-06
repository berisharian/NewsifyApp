package mk.ukim.finki.wp.newsifyApp.repository;

import mk.ukim.finki.wp.newsifyApp.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Long> {
}
