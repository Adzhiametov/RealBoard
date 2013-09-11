package com.epam.adzhiametov.controller;

import com.epam.adzhiametov.dao.AdvertDao;
import com.epam.adzhiametov.enumeration.Operation;
import com.epam.adzhiametov.enumeration.Section;
import com.epam.adzhiametov.model.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.epam.adzhiametov.controller.MVCConstants.*;

@Controller
public class RedirectController {

    @Autowired
    private AdvertDao advertDao;

    @RequestMapping(value = REQUEST_ADVERTS_LIST, method = RequestMethod.GET)
    public String goToAdverts(ModelMap model) {
        model.addAttribute(ATTRIBUTE_ADVERTS, advertDao.findRange(1, ITEMS_ON_PAGE));
        model.addAttribute(ATTRIBUTE_PAGE, FIRST_PAGE);
        return PAGE_ADVERTS_LIST;
    }

    @RequestMapping(value = REQUEST_GOTO_ADD_ADVERT, method = RequestMethod.GET)
    public String goToPage(@ModelAttribute(ATTRIBUTE_ADVERT) Advert advert, ModelMap model) {
        model.addAttribute(ATTRIBUTE_SECTIONS, Section.values());
        model.addAttribute(ATTRIBUTE_OPERATIONS, Operation.values());
        return PAGE_ADD_ADVERT;
    }
}