/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment_system;

import java.util.List;
import payment_info.PaymentStatus;
import javax.ejb.Remote;
import payment_info.PaymentPack;
import payment_info.OperationsForSell;

/**
 *
 * @author Alexey
 */
@Remote
public interface PaymentSessionRemote {

    PaymentStatus addAccount(float count, String login, String password);

    PaymentPack authAccount(String login, String password);

    void closeAuth(String token);

    PaymentPack payAction(String token, String receiver, float count);

    PaymentPack addMoney(String token, float count);
    
    OperationsForSell getHitory(String token);
}
