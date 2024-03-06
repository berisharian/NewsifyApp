package mk.ukim.finki.wp.newsifyApp.service.impl;

import mk.ukim.finki.wp.newsifyApp.repository.NewsRepository;
import mk.ukim.finki.wp.newsifyApp.service.NewsCategoryService;
import mk.ukim.finki.wp.newsifyApp.service.NewsService;
import mk.ukim.finki.wp.newsifyApp.model.News;
import mk.ukim.finki.wp.newsifyApp.model.NewsCategory;
import mk.ukim.finki.wp.newsifyApp.model.NewsType;
import mk.ukim.finki.wp.newsifyApp.model.exceptions.InvalidNewsIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsCategoryService newsCategoryService;

    public NewsServiceImpl(NewsRepository newsRepository, NewsCategoryService newsCategoryService) {
        this.newsRepository = newsRepository;
        this.newsCategoryService = newsCategoryService;
    }

    @Override
    public List<News> listAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElseThrow(InvalidNewsIdException::new);
    }

    @Override
    public News create(String name, String description, Double price, NewsType type, Long category) {
        NewsCategory newsCategory=newsCategoryService.findById(category);
        News news=new News(name, description, price, type, newsCategory);
        return newsRepository.save(news);
    }

    @Override
    public News update(Long id, String name, String description, Double price, NewsType type, Long category) {
        NewsCategory newsCategory=newsCategoryService.findById(category);
        News news=this.findById(id);
        news.setCategory(newsCategory);
        news.setName(name);
        news.setDescription(description);
        news.setPrice(price);
        news.setType(type);
        return newsRepository.save(news);
    }

    @Override
    public News delete(Long id) {
        News news=this.findById(id);
        newsRepository.delete(news);
        return news;
    }

    @Override
    public News like(Long id) {
        News news=this.findById(id);
        news.setLikes(news.getLikes()+1);
        return newsRepository.save(news);
    }

    @Override
    public List<News> listNewsWithPriceLessThanAndType(Double price, NewsType type) {
        if(price!=null && type !=null){
            return newsRepository.findAllByPriceLessThanAndType(price, type);
        }
        if(price!=null){
            return newsRepository.findAllByPriceLessThan(price);
        }
        if(type!=null){
            return newsRepository.findAllByType(type);
        }
        return listAllNews();
    }
}
