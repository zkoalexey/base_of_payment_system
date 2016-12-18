/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment_info;
import java.io.Serializable;

/**
 *
 * @author Alexey
 */
public class PaymentPack implements Serializable{
    private PaymentStatus status;
    private float count;
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public PaymentPack(PaymentStatus status, float count, String token) {
        this.status = status;
        this.count = count;
        this.token = token;
    }

    public PaymentPack(PaymentStatus status) {
        this.status = status;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public float getCount() {
        return count;
    }

    public String getToken() {
        return token;
    }
}
