package com.study.springoauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

import static com.study.springoauth.constants.AttrConstants.*;
import static com.study.springoauth.constants.PathConstants.*;
import static com.study.springoauth.constants.ViewConstants.VIEW_INDEX;

@RestController
public class IndexController {
    @Autowired
    private HttpSession session;

    @RequestMapping(path = PATH_INDEX, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView index (@AuthenticationPrincipal OAuth2User user) {
        ModelAndView page = new ModelAndView();

        configureViewName(page);
        configureIndexModel(page.getModelMap(), user);

        return page;
    }

    public void configureViewName(ModelAndView page){
        page.setViewName(VIEW_INDEX);
    }

    private void configureIndexModel(ModelMap model, OAuth2User user){
        putUserName(model, user);
        putIsLogged(model, user);
        putErrorMessage(model);
    }

    private void putUserName(ModelMap model, OAuth2User user){
        String userName = user != null ? user.getAttribute("name") : "Unknown Person";
        model.put(ATTR_USER_NAME, userName);
    }

    private void putIsLogged(ModelMap model, OAuth2User user){
        model.put(ATTR_IS_LOGGED, user != null);
    }

    private void putErrorMessage(ModelMap model){
        model.put(ATTR_ERROR_MESSAGE, session.getAttribute(ATTR_ERROR_MESSAGE));
    }
}
