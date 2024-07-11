package model.services;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void proccessContract(Contract contract, Integer months) {
        Double basicQuota = contract.getTotalValue() / months;
        for (int i = 1; i <= months; i++) {
            Double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
            Double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);
            contract.getInstallments().add(new Installment(
                contract.getDate().plusMonths(i),
                fullQuota
            ));
        }
    }
}
