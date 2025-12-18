package ie.atu.login_project.DTO;

import lombok.*;
    @Data
    @Builder

    public class PaymentDTO {

        private Long paymentId;
        private String amount;
        private String paymentMethod;
        private String currency;

        public PaymentDTO() {
        }

        public PaymentDTO(Long paymentId, String amount, String paymentMethod, String currency) {
            this.paymentId = paymentId;
            this.amount = amount;
            this.paymentMethod = paymentMethod;
            this.currency = currency;
        }

        public Long getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(Long paymentId) {
            this.paymentId = paymentId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }


