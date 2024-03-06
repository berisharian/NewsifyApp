package mk.ukim.finki.wp.newsifyApp.service.impl;

import mk.ukim.finki.wp.newsifyApp.model.exceptions.InvalidNewsCategoryIdException;
import mk.ukim.finki.wp.newsifyApp.repository.NewsCategoryRepository;
import mk.ukim.finki.wp.newsifyApp.service.NewsCategoryService;
import mk.ukim.finki.wp.newsifyApp.model.NewsCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsCategoryServiceImpl implements NewsCategoryService {
    private final NewsCategoryRepository newsCategoryRepository;

    public NewsCategoryServiceImpl(NewsCategoryRepository newsCategoryRepository) {
        this.newsCategoryRepository = newsCategoryRepository;
    }

    @Override
    public NewsCategory findById(Long id) {
        return newsCategoryRepository.findById(id).orElseThrow(InvalidNewsCategoryIdException::new);
    }

    @Override
    public List<NewsCategory> listAll() {
        return newsCategoryRepository.findAll();
    }

    @Override
    public NewsCategory create(String name) {
        NewsCategory newsCategory=new NewsCategory(name);
        return newsCategoryRepository.save(newsCategory);
    }
}
