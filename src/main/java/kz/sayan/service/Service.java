package kz.sayan.service;

import kz.sayan.config.HibernateUtil;
import kz.sayan.config.MD5;
import kz.sayan.entity.User;
import org.hibernate.Session;

import java.util.List;

/**
 * User: Sayan.Zhumashev
 * Date: 7/16/14
 * Time: 5:36 PM
 */
public class Service {

    public User saveUser(User user){
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("id: " + user.getId());
            HibernateUtil.getSessionFactory().getCurrentSession().close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public int checkEmail(String email){
        int count = 0;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Long result = (Long) session.createQuery("select count(*) from User u where u.email = :email").setParameter("email", email).uniqueResult();
            if(result!=0)
                count = 1;
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public User signIn(String email, String password){
        User user = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.createQuery("from User u where u.email = :email and u.password = :password").setParameter("email", email).setParameter("password", MD5.hash(password)).uniqueResult();
            HibernateUtil.getSessionFactory().getCurrentSession().close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
