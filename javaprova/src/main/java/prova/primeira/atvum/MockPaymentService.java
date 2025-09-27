package prova.primeira.atvum;

import java.util.*;

interface PaymentService {
    boolean processPayment(double amount, String cardNumber);
    PaymentResult getPaymentStatus(String transactionId);
    void refundPayment(String transactionId, double amount);
}
//Cria um objeto para armazenar o resultado do pagamento(como"SUCCESS"ou"FAILED").
class PaymentResult {
    private String status;
    public PaymentResult(String status) { this.status = status; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

public class MockPaymentService implements PaymentService {
  //Contadores que registram o número de vezes que cada método foi chamado.
    private int processPaymentCallCount = 0;
    private int getPaymentStatusCallCount = 0;
    private int refundPaymentCallCount = 0;
//Armazenam os parâmetro usados em cada chamada de método.
    private List<Object[]> processPaymentCalls = new ArrayList<>();
    private List<String> getPaymentStatusCalls = new ArrayList<>();  
    private List<Object[]> refundPaymentCalls = new ArrayList<>();   
//Permitem cofigura o que o mock vai retornar.
    private boolean processPaymentResult = true;
    private PaymentResult getPaymentStatusResult = new PaymentResult("SUCCESS");
//implementação que atende aos requisitos de ratreamento
    @Override
    public boolean processPayment(double amount, String cardNumber) {
        processPaymentCallCount++;  
        processPaymentCalls.add(new Object[]{amount, cardNumber});
        return processPaymentResult;
    }
    
    @Override
    public PaymentResult getPaymentStatus(String transactionId) {
        getPaymentStatusCallCount++;
        getPaymentStatusCalls.add(transactionId);
        return getPaymentStatusResult;
    }
         
    @Override
    public void refundPayment(String transactionId, double amount) {
        refundPaymentCallCount++;  
        refundPaymentCalls.add(new Object[]{transactionId, amount});
    }

//Fornecer métodos para verificação das chamadas
    public int getProcessPaymentCallCount() { return processPaymentCallCount; }
    public int getGetPaymentStatusCallCount() { return getPaymentStatusCallCount; }
    public int getRefundPaymentCallCount() { return refundPaymentCallCount; }
    

    public List<Object[]> getProcessPaymentCalls() { return processPaymentCalls; }
    public List<String> getGetPaymentStatusCalls() { return getPaymentStatusCalls; }
    public List<Object[]> getRefundPaymentCalls() { return refundPaymentCalls; }
    
//'Métodos de verificação adicional'
    public boolean wasProcessPaymentCalledWith(double amount, String cardNumber) {
        for (Object[] call : processPaymentCalls) {
            if (call[0].equals(amount) && call[1].equals(cardNumber)) {
                return true;
            }
        }
        return false;
    }
    // Permitir configurar valores de retorno
    public void setProcessPaymentResult(boolean result) {
        this.processPaymentResult = result;
    }
    
    public void setGetPaymentStatusResult(PaymentResult result) {
        this.getPaymentStatusResult = result;
    }
    
    public void setGetPaymentStatusResult(String status) {
        this.getPaymentStatusResult = new PaymentResult(status);
    }
}