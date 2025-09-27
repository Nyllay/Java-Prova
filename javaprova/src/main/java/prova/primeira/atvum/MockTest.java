package prova.primeira.atvum;

public class MockTest {
    public static void main(String[] args) {
        MockPaymentService mock = new MockPaymentService();
        
      
        //Demonstração do rasteamento de chamadas
        boolean result1 = mock.processPayment(100.0, "1234-5678");
       
        PaymentResult status = mock.getPaymentStatus("TXN123");
        mock.refundPayment("TXN123", 100.0);
        //Demontração da verificação das chamadas
        System.out.println("Chamadas processPayment: " + mock.getProcessPaymentCallCount());
        System.out.println("Chamadas getPaymentStatus: " + mock.getGetPaymentStatusCallCount());
        System.out.println("Chamadas refundPayment: " + mock.getRefundPaymentCallCount());
        //Demontração da verificação de parâmetros específicos
        System.out.println("Pagamento 100.0 com cartão 1234-5678 foi chamado: " + 
            mock.wasProcessPaymentCalledWith(100.0, "1234-5678"));
        // Demonstração dos valores de retorno
        System.out.println("Resultado do pagamento: " + result1);
        System.out.println("Status do pagamento: " + status.getStatus());
    }
}