package com.sncj.core.test.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.test.service.ITestService;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TestService implements ITestService {
    public static void showData(Iterable result) {
        Integer i = 0;
        Iterator it = result.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            System.out.println(i++);
        }
    }

    @Override
    public List findAll(BasePage basePage) {
        List<Document> list=new ArrayList<>();
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("dbtest");
        MongoCollection<Document> collection = db.getCollection("users");
        FindIterable<Document> documents = collection.find().skip(basePage.getPage()).limit(basePage.getLimit()).sort(new BasicDBObject("_id",-1));
        for (Document document:documents){
            list.add(document);
        }
        mongoClient.close();
        return list;
    }
}
