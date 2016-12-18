/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment_info;

import java.io.Serializable;
import java.util.List;
import payment_system.Operations;


/**
 *
 * @author Alexey
 */
public class OperationsForSell implements Serializable{

    public OperationsForSell(List<Operations> lst, List<String> sellers, List<String> receivers) {
        this.lst = lst;
        this.sellers = sellers;
        this.receivers = receivers;
    }

    public List<Operations> getLst() {
        return lst;
    }

    public void setLst(List<Operations> lst) {
        this.lst = lst;
    }

    public List<String> getSellers() {
        return sellers;
    }

    public void setSellers(List<String> sellers) {
        this.sellers = sellers;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    private List<Operations> lst;
    private List<String> sellers;
    private List<String> receivers;
}
