package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController
{
    private final ShoppingCartDao cartDao;
    private final UserDao userDao;

    @Autowired
    public ShoppingCartController(ShoppingCartDao cartDao, UserDao userDao)
    {
        this.cartDao = cartDao;
        this.userDao = userDao;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ShoppingCart getCart(Principal principal)
    {
        User user = userDao.getByUserName(principal.getName());
        return cartDao.getByUserId(user.getId());
    }


    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    public void clearCart(Principal principal)
    {
        User user = userDao.getByUserName(principal.getName());
        cartDao.clearCart(user.getId());
    }

    // add a POST method to add a product to the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be added


    // add a PUT method to update an existing product in the cart - the url should be
    // https://localhost:8080/cart/products/15 (15 is the productId to be updated)
    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated
}

