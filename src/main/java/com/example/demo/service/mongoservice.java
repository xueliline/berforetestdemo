package com.example.demo.service;

import com.example.demo.eneity.User;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class mongoservice {
    @Autowired
    MongoTemplate templete;
    public List<User> all()
    {
        return templete.findAll(User.class);
    }

    public User insert(User user)
    {
        return templete.insert(user);

    }
    public DeleteResult delete(User user)
    {
        Query query=new Query(Criteria.where("name").is("shirley3"));
       DeleteResult res=templete.remove(query,User.class);
        System.out.println("接受到了ajax传输到的数据");
        return res;
    }
    public User  IsExist(User user){
        Query query = new Query();
        Criteria criteria = new Criteria().andOperator(Criteria.where("name").is(user.getName()),Criteria.where("password").is(user.getPassword()));
        query.addCriteria(criteria);
       User user1=  templete.findOne(query,User.class);
       if(user1==null)
       {
           System.out.println("dddddddddddddddddd");
           return null;
       }
      else{
          return user1;
       }
    }

}
