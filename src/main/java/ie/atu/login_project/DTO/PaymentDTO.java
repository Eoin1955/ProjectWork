package ie.atu.login_project.DTO;

public class PaymentDTO {

        private Long paymentId;
        private Long bookID;
        private String amount;
        private String paymentMethod;
        private String currency;

        public PaymentDTO() {
        }

        public PaymentDTO(Long bookID, String amount, String paymentMethod, String currency) {
            this.bookID = bookID;
            this.amount = amount;
            this.paymentMethod = paymentMethod;
            this.currency = currency;
        }

        public PaymentDTO(Long paymentId, Long bookID, String amount, String paymentMethod, String currency) {
            this.paymentId = paymentId;
            this.bookID = bookID;
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

        public Long getBookID() {
            return bookID;
        }

        public void setBookID(Long bookID) {
            this.bookID = bookID;
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
