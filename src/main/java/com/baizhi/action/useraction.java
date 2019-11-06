package com.baizhi.action;

import com.baizhi.entity.user;
import com.baizhi.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class useraction {
    @Autowired
    private userservice userservice;

    @RequestMapping("/insert")
    public String insert(user user) throws Exception {
        userservice.insert(user);
        return "redirect:/login.jsp";
    }

    @RequestMapping("/login")
    public String login(user user, HttpServletRequest request, HttpSession session) throws Exception {
        com.baizhi.entity.user login = userservice.login(user);
        session.setAttribute("login",login);
        session.setAttribute("name",login.getName());
        if(user.getShenfen().equals(login.getShenfen())) {

            if ("管理员".equals(user.getShenfen())) {
                return "redirect:/showAll.do";
            }
            user u = userservice.select(login.getId());
            request.setAttribute("u", u);
            return "userlist";
            }else{
            return "redirect:/login.jsp";
        }
    }
         @RequestMapping("/showAll")
        public  String showAll(HttpServletRequest request,Integer yeshu,Integer number)throws Exception{
             if (number==null){
                 number=3;
             }
             Integer tiaoshu = userservice.tiaoshu();
             if (yeshu==null||yeshu<1){
                 yeshu=1;
             }
             Integer zongyeshu;
             if (tiaoshu%number==0){
                 zongyeshu=tiaoshu/number;
             }else {
                 zongyeshu=tiaoshu/number+1;
             }
             if (yeshu>zongyeshu){
                 yeshu=zongyeshu;
             }

             List<user> users = userservice.showAll((yeshu-1)*number,number);
             request.setAttribute("yeshu",yeshu);
             request.setAttribute("number",number);
             request.setAttribute("zongyeshu",zongyeshu);
             request.setAttribute("list",users);
             return  "admin";
         }
         @RequestMapping("/tianjia")
        public  String tianjia(user user)throws  Exception{
            userservice.tianjia(user);
            return "redirect:/showAll.do";
         }
         @RequestMapping("/delete")
        public  String delete(String id)throws  Exception{
            userservice.delete(id);
            return  "redirect:/showAll.do";
         }
         @RequestMapping("/update")
        public  String update(user user)throws  Exception{
            userservice.update(user);
            return  "redirect:/showAll.do";
         }
         @RequestMapping("/select1")
         @ResponseBody
        public  user select1(String id)throws  Exception{
             user user = userservice.select1(id);
             return  user;
         }
        @RequestMapping("/sousuo")
        public  String sousuo(String name,HttpServletRequest request)throws  Exception{
            List<user> sousuo = userservice.sousuo(name);
            request.setAttribute("list",sousuo);
            return "admin";
        }
        @RequestMapping("/zhuangtai")
        public String zhuangtai(user user)throws  Exception{
            userservice.update1(user);
            return "redirect:/showAll.do";
        }
        @RequestMapping("/tuichu")
        public String tuichu(HttpSession session)throws  Exception {
            session.invalidate();
            return "login";
        }
}
