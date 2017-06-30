package com.egen.management.service.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil
{
    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    
   private static SessionFactory sessionFactory = buildSessionFactory();   
   private static SessionFactory buildSessionFactory()
   {
      try
      {
         if (sessionFactory == null)
         {
             Configuration configuration = new Configuration();
             configuration.configure("hibernate.cfg.xml");

             StandardServiceRegistryBuilder serviceBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
             sessionFactory = configuration.buildSessionFactory(serviceBuilder.build());
         }
         return sessionFactory;
      } catch (Throwable ex)
      {
         logger.debug("Initial SessionFactory creation failed." + ex);
      }
    return sessionFactory;
   }
 
   public static SessionFactory getSessionFactory()
   {
      return sessionFactory;
   }
 
   public static void shutdown()
   {
      getSessionFactory().close();
   }
}
