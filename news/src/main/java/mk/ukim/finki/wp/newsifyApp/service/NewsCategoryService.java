package mk.ukim.finki.wp.newsifyApp.service;

import mk.ukim.finki.wp.newsifyApp.model.exceptions.InvalidNewsCategoryIdException;
import mk.ukim.finki.wp.newsifyApp.model.NewsCategory;

import java.util.List;

public interface NewsCategoryService {

    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return
     * @throws InvalidNewsCategoryIdException when there is no  location with the given id
     */
    NewsCategory findById(Long id);

    /**
     * @return List of all entities in the database
     */
    List<NewsCategory> listAll();

    /**
     * This method is used to create a new entity, and save it in the database.
     *
     * @param name
     * @return The entity that is created. The id should be generated when the entity is created.
     */
    NewsCategory create(String name);
}
