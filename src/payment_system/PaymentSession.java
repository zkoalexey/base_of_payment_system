/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment_system;

import java.util.ArrayList;
import payment_info.PaymentStatus;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import java.util.Date;
import javax.ejb.Remove;
import payment_info.PaymentPack;
import payment_info.OperationsForSell;
/**
 *
 * @author Alexey
 */


@Stateful
public class PaymentSession implements PaymentSessionRemote, PaymentSessionLocal {

    @PersistenceContext(unitName = "PaymentSystem-ejbPU")
    private EntityManager em;

    private Accounts acc;
    private String token;
    private Exception Exception;
  
    @Override
    public PaymentStatus addAccount(float count, String login, String password) { 
        // Проверка, что такого логина нет
        Query query = em.createNamedQuery("Accounts.findByLogin");
        query.setParameter("login", login);
        List temp_list = query.getResultList();
        if(temp_list.size() > 0){
            return PaymentStatus.accountExist;
        }
        if(count<0){
            return PaymentStatus.minusCount;
        }
        
        acc = new Accounts(count, login, password);
        em.persist(acc);
        em.flush();
        return PaymentStatus.allGood;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public PaymentPack authAccount(String login, String password) {
        
        // Проверка, что такой логин есть
        Query query = em.createNamedQuery("Accounts.findByLogin");
        query.setParameter("login", login);
        List temp_list = query.getResultList();
        if(temp_list.size() == 0){
            return new PaymentPack(PaymentStatus.accountNotExist);
        }
        query = em.createNamedQuery("Accounts.auth", Accounts.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        List<Accounts> temp_list1 = query.getResultList();
        if(temp_list1.isEmpty()){
            return new PaymentPack(PaymentStatus.wrongPassword);
        }
        acc=temp_list1.get(0);
        token=UUID.randomUUID().toString();
        return new PaymentPack(PaymentStatus.allGood,acc.getCount(),token);
    }

    @Override
    @Remove
    public void closeAuth(String token) {
         try {
            checkToken(token);
        } catch (java.lang.Exception ex) {
            Logger.getLogger(PaymentSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public PaymentPack payAction(String token, String receiver, float count) {
        try {
            checkToken(token);
        } catch (java.lang.Exception ex) {
            Logger.getLogger(PaymentSession.class.getName()).log(Level.SEVERE, null, ex);
        }
       // Проверка, что получатель не текущий пользователь
       if(acc.getLogin()==receiver)
           return new PaymentPack(PaymentStatus.wrongReceiver);
        
        // Проверка, что получатель существует
        Query query = em.createNamedQuery("Accounts.findByLogin");
        query.setParameter("login", receiver);
        List temp_list = query.getResultList();
        if(temp_list.size() == 0){
            return new PaymentPack(PaymentStatus.accountNotExist);
        }
        if(count<0){
            return new PaymentPack(PaymentStatus.minusCount);
        }
        // Проверка, что денег на счету достаточно
        if(acc.getCount()<count)
        {
            return new PaymentPack(PaymentStatus.notEnoughMoney);
        }
        acc.setCount(acc.getCount()-count);
        Accounts rec=(Accounts)temp_list.get(0);
        rec.setCount(rec.getCount()+count);
        
        Operations op=new Operations();
        op.setCount(count);
        op.setDatetime(new Date());
        op.setSeller(acc);
        op.setReceiver(rec);
        em.merge(acc);
        em.persist(op);
        em.flush();
        
        return new PaymentPack(PaymentStatus.allGood,acc.getCount(),"");
    }
    
    private void checkToken(String token) throws java.lang.Exception
    {
        if(this.token!=token) throw Exception;
    }  

    @Override
    public PaymentPack addMoney(String token, float count) {
        try {
            checkToken(token);
        } catch (java.lang.Exception ex) {
            Logger.getLogger(PaymentSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(count<0){
            return new PaymentPack(PaymentStatus.minusCount);
        }
        acc.setCount(acc.getCount()+count);
        
        Operations op=new Operations();
        op.setCount(count);
        op.setDatetime(new Date());
        op.setSeller(acc);
        op.setReceiver(acc);
        em.persist(op);
        em.merge(acc);
        em.flush();
        return new PaymentPack(PaymentStatus.allGood,acc.getCount(),"");
    }
    
    @Override
    public OperationsForSell getHitory(String token) {
         try {
            checkToken(token);
        } catch (java.lang.Exception ex) {
            Logger.getLogger(PaymentSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Operations> lst=em.createNamedQuery("Operations.history", Operations.class).setParameter("user", acc).getResultList();
        List<String> sellers = new ArrayList<String>();
        List<String> receivers = new ArrayList<String>();
        for (int i = 0; i < lst.size(); i++) {
            sellers.add(lst.get(i).getSeller().getLogin());
            receivers.add(lst.get(i).getReceiver().getLogin());
        }
        OperationsForSell op=new OperationsForSell(lst,sellers,receivers);
        return op;
    }
    
}
