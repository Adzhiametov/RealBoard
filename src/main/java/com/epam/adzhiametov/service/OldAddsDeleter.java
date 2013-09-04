package com.epam.adzhiametov.service;

import com.epam.adzhiametov.dao.AdvertDao;
import com.epam.adzhiametov.model.Advert;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@Configuration
@EnableScheduling
public class OldAddsDeleter{

    @Autowired
    private AdvertDao advertDao;

    //may be optimized by implementing dao-method with specific SELECT query. But here it is not matter.
    @Scheduled(fixedRate=5000)
    public void deleteOld(){
        List<Advert> adverts = advertDao.findAll();
        Iterator<Advert> it = adverts.iterator();
        Calendar now = Calendar.getInstance();
        while (it.hasNext()){
            Advert advert = it.next();
            if (compareDatesTo30DaysGap(now, advert.getTime())){
                 advertDao.delete(advert);
            }
        }
    }

    private static boolean compareDatesTo30DaysGap(Calendar from, Calendar to) {
        int days = Days.daysBetween(new DateTime(from), new DateTime(to)).getDays();
        return days > 30;
    }

}
