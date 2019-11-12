package com.example.demo.controller;

import com.example.demo.eneity.Echars;
import com.example.demo.eneity.Status;
import com.example.demo.eneity.User;
import com.example.demo.service.mongoservice;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class MongoController {

    int count=0;
    @Autowired
    mongoservice servicel;

    @RequestMapping(value = "/time")
    @ResponseBody
    public int time() {
        System.out.println("=============="+count);

        return (count++)%200;
    }

    @RequestMapping(value = "/EcharsShow")
    @ResponseBody
    public List<Echars> findById(Model model) {
        List<Echars> list = new ArrayList<Echars>();
        list.add(new Echars("帽子", 50));
        list.add(new Echars("鞋子", 126));
        list.add(new Echars("毛衣", 75));
        list.add(new Echars("羽绒服", 201));
        list.add(new Echars("羊毛衫", 172));
        System.err.println(list.toString());

        return list;
    }

    @GetMapping(value = "/Echarts")
    public String echarts4(Model model) {
        System.err.println("========开始");
        return "echart";
    }


    @RequestMapping("/delete")
    public String delete() {
        System.out.println("传到数据了------------------");
        User user = new User();
        user.setName("shirley3");
        DeleteResult deleteres = servicel.delete(user);

        return "index";
    }

    @RequestMapping("/all")
    public String all(Model model) {
        List<User> userlist = servicel.all();
        model.addAttribute("mess", userlist);
        return "myindex";
    }

    @RequestMapping("/test")
    public String gantetu(Model model) {

        return "ttttttt";
    }

    @RequestMapping("/getdata")
    public String getdata(User user, Model model) {
        System.out.println(user);
        User tmp = servicel.insert(user);
        model.addAttribute("result", "Succjihbiuftucrdtucdtxcess");
        return "success";
    }

    @RequestMapping("/gantetu")
    public String pic() {
        return "ttttttt";
    }

    @RequestMapping("/login")
    public String login(User user, Model model) {
        User result = servicel.IsExist(user);
        if (result != null) {
            List<User> userlist = servicel.all();
            model.addAttribute("mess", userlist);
            System.out.println("0000000000000000000000000000000000000000000000000000" + userlist);
            return "myindex";
        } else {
            return "index";
        }
    }
}
