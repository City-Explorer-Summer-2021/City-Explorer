package com.example.cityexplorer.service;

import com.example.cityexplorer.exception.NotFoundException;
import com.example.cityexplorer.model.User;

import java.util.List;

/**
 * Service for working with {@link User user} entity
 */
public interface UserService {

    /**
     * Method for getting full list of {@link User user}s.
     *
     * @return full list of {@link User user}s
     */
    List<User> getList();

    /**
     * Method for finding a {@link User user} by its unique identifier.
     * The input argument should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id {@link User user} unique identifier
     * @return {@link User user} object with unique identifier like in the argument
     * @throws IllegalArgumentException if input id is null
     * @throws NotFoundException        if there is no {@link User user} object
     *                                  with unique identifier like in the argument
     */
    User getById(Long id);

    /**
     * Method for finding a {@link User user} by its username.
     * The input argument should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param username name of {@link User user}
     * @return {@link User user} object with username like in the argument
     * @throws IllegalArgumentException if input username is null
     * @throws NotFoundException        if there is no {@link User user} object
     *                                  with username like in the argument
     */
    User getByUsername(String username);

    /**
     * Method for saving a {@link User user} in a repository.
     * The input argument should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param user {@link User user} object to save
     * @return saved {@link User user} object
     * @throws IllegalArgumentException if input {@link User user} object is null
     */
    User save(User user);

    /**
     * Method for updating a {@link User user}.
     * Input arguments should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id   {@link User user} unique identifier
     * @param user {@link User user} object to update
     * @return updated {@link User user} object
     * @throws IllegalArgumentException if any of input arguments is null
     * @throws NotFoundException        if there is no {@link User user} object
     *                                  with unique identifier like in the argument is not found
     */
    User update(Long id, User user);

    /**
     * Method for deleting a {@link User user}.
     * The input argument should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id {@link User user} unique identifier
     * @throws IllegalArgumentException if input id is null
     * @throws NotFoundException        if there is no {@link User user} object
     *                                  with unique identifier like in the argument
     */
    void delete(Long id);
}
