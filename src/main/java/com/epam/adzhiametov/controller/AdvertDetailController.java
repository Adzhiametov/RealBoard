package com.epam.adzhiametov.controller;

import com.epam.adzhiametov.dao.AdvertDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.epam.adzhiametov.controller.MVCConstants.*;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@Controller
public class AdvertDetailController {

    @Autowired
    private AdvertDao advertDao;

    @RequestMapping(value = REQUEST_ALL_OF_AUTHOR, method = RequestMethod.GET)
    public String viewAllOfAuthor(@PathVariable(PHONE) String phone, Model model) {
        model.addAttribute(ATTRIBUTE_ADVERTS, advertDao.find(phone));
        return PAGE_ADVERTS_LIST;
    }
}
