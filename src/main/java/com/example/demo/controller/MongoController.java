package com.example.demo.controller;

import com.example.demo.eneity.User;
import com.example.demo.service.mongoservice;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class MongoController {
    @Autowired
    mongoservice servicel;
    @RequestMapping("/delete")
    public  String delete()
    {
        System.out.println("传到数据了------------------");
        User user=new User();
        user.setName("shirley3");
        DeleteResult deleteres=servicel.delete(user);

        return "index";
    }
    @RequestMapping("/all")
    public String all(Model model){
        List<User> userlist=servicel.all();
        model.addAttribute("mess",userlist);
        return "myindex";
    }
    @RequestMapping("/getdata")
    public String getdata(User user,Model model){
         System.out.println(user);
         User tmp=servicel.insert(user);
         model.addAttribute("result","Succjihbiuftucrdtucdtxcess");
        return "success";
    }
    @RequestMapping("/login")
    public String login(User user,Model model)
    {
        User result=servicel.IsExist(user);
        if(result!=null)
        {
            List<User> userlist=servicel.all();
            model.addAttribute("mess",userlist);
            System.out.println("0000000000000000000000000000000000000000000000000000"+userlist);
            return "myindex";
        }else
        {
           return "index";
        }
    }
}
