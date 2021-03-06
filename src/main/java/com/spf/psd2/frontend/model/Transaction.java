
package com.spf.psd2.frontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Transaction
 */

public class Transaction {

  @JsonProperty("transactionId")

  private String transactionId = null;

  @JsonProperty("endToEndId")

  private String endToEndId = null;

  @JsonProperty("bookingDate")

  private LocalDate bookingDate = null;

  @JsonProperty("valueDate")

  private LocalDate valueDate = null;

  @JsonProperty("executionDateTime")

  private OffsetDateTime executionDateTime = null;

  @JsonProperty("transactionAmount")

  private Amount transactionAmount = null;

  @JsonProperty("creditorName")

  private String creditorName = null;

  @JsonProperty("creditorAccount")

  private CounterpartyAccount creditorAccount = null;

  @JsonProperty("debtorName")

  private String debtorName = null;

  @JsonProperty("debtorAccount")

  private CounterpartyAccount debtorAccount = null;

  @JsonProperty("transactionType")

  private String transactionType = null;

  @JsonProperty("remittanceInformationUnstructured")

  private String remittanceInformationUnstructured = null;

  @JsonProperty("remittanceInformationStructured")

  private Object remittanceInformationStructured = null;
  public Transaction transactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  

  /**
  * The unique identifier submitted by ING to the entry (transaction)
  * @return transactionId
  **/
  public String getTransactionId() {
    return transactionId;
  }
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }
  public Transaction endToEndId(String endToEndId) {
    this.endToEndId = endToEndId;
    return this;
  }

  

  /**
  * Unique transaction reference given by the initiator of the transaction which can be followed throughout the payment chain. If this information is not available, this will be defaulted to \&quot;Not Provided\&quot;.
  * @return endToEndId
  **/
  public String getEndToEndId() {
    return endToEndId;
  }
  public void setEndToEndId(String endToEndId) {
    this.endToEndId = endToEndId;
  }
  public Transaction bookingDate(LocalDate bookingDate) {
    this.bookingDate = bookingDate;
    return this;
  }

  

  /**
  * Get bookingDate
  * @return bookingDate
  **/
  public LocalDate getBookingDate() {
    return bookingDate;
  }
  public void setBookingDate(LocalDate bookingDate) {
    this.bookingDate = bookingDate;
  }
  public Transaction valueDate(LocalDate valueDate) {
    this.valueDate = valueDate;
    return this;
  }

  

  /**
  * Get valueDate
  * @return valueDate
  **/
  public LocalDate getValueDate() {
    return valueDate;
  }
  public void setValueDate(LocalDate valueDate) {
    this.valueDate = valueDate;
  }
  public Transaction executionDateTime(OffsetDateTime executionDateTime) {
    this.executionDateTime = executionDateTime;
    return this;
  }

  

  /**
  * Date and time at which the transaction was executed. The date-time is in UTC ISO 8601 Date Time format: YYYY-MM-DDThh:mm:ss.sTZD and in the UTC timezone. This is made explicit by value Z (for Zulu) being returned as time zone designator.
  * @return executionDateTime
  **/
  public OffsetDateTime getExecutionDateTime() {
    return executionDateTime;
  }
  public void setExecutionDateTime(OffsetDateTime executionDateTime) {
    this.executionDateTime = executionDateTime;
  }
  public Transaction transactionAmount(Amount transactionAmount) {
    this.transactionAmount = transactionAmount;
    return this;
  }

  

  /**
  * Get transactionAmount
  * @return transactionAmount
  **/
  public Amount getTransactionAmount() {
    return transactionAmount;
  }
  public void setTransactionAmount(Amount transactionAmount) {
    this.transactionAmount = transactionAmount;
  }
  public Transaction creditorName(String creditorName) {
    this.creditorName = creditorName;
    return this;
  }

  

  /**
  * Name of the creditor if a ???Debited??? transaction
  * @return creditorName
  **/
  public String getCreditorName() {
    return creditorName;
  }
  public void setCreditorName(String creditorName) {
    this.creditorName = creditorName;
  }
  public Transaction creditorAccount(CounterpartyAccount creditorAccount) {
    this.creditorAccount = creditorAccount;
    return this;
  }

  

  /**
  * Get creditorAccount
  * @return creditorAccount
  **/
  public CounterpartyAccount getCreditorAccount() {
    return creditorAccount;
  }
  public void setCreditorAccount(CounterpartyAccount creditorAccount) {
    this.creditorAccount = creditorAccount;
  }
  public Transaction debtorName(String debtorName) {
    this.debtorName = debtorName;
    return this;
  }

  

  /**
  * Name of the debtor if a ???Credited??? transaction
  * @return debtorName
  **/
  public String getDebtorName() {
    return debtorName;
  }
  public void setDebtorName(String debtorName) {
    this.debtorName = debtorName;
  }
  public Transaction debtorAccount(CounterpartyAccount debtorAccount) {
    this.debtorAccount = debtorAccount;
    return this;
  }

  

  /**
  * Get debtorAccount
  * @return debtorAccount
  **/
  public CounterpartyAccount getDebtorAccount() {
    return debtorAccount;
  }
  public void setDebtorAccount(CounterpartyAccount debtorAccount) {
    this.debtorAccount = debtorAccount;
  }
  public Transaction transactionType(String transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  

  /**
  * Transaction type of the transaction
  * @return transactionType
  **/
  public String getTransactionType() {
    return transactionType;
  }
  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }
  public Transaction remittanceInformationUnstructured(String remittanceInformationUnstructured) {
    this.remittanceInformationUnstructured = remittanceInformationUnstructured;
    return this;
  }

  

  /**
  * Unstructured remittance information
  * @return remittanceInformationUnstructured
  **/
  public String getRemittanceInformationUnstructured() {
    return remittanceInformationUnstructured;
  }
  public void setRemittanceInformationUnstructured(String remittanceInformationUnstructured) {
    this.remittanceInformationUnstructured = remittanceInformationUnstructured;
  }
  public Transaction remittanceInformationStructured(Object remittanceInformationStructured) {
    this.remittanceInformationStructured = remittanceInformationStructured;
    return this;
  }

  

  /**
  * Remittance information in a structured way
  * @return remittanceInformationStructured
  **/
  public Object getRemittanceInformationStructured() {
    return remittanceInformationStructured;
  }
  public void setRemittanceInformationStructured(Object remittanceInformationStructured) {
    this.remittanceInformationStructured = remittanceInformationStructured;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.transactionId, transaction.transactionId) &&
        Objects.equals(this.endToEndId, transaction.endToEndId) &&
        Objects.equals(this.bookingDate, transaction.bookingDate) &&
        Objects.equals(this.valueDate, transaction.valueDate) &&
        Objects.equals(this.executionDateTime, transaction.executionDateTime) &&
        Objects.equals(this.transactionAmount, transaction.transactionAmount) &&
        Objects.equals(this.creditorName, transaction.creditorName) &&
        Objects.equals(this.creditorAccount, transaction.creditorAccount) &&
        Objects.equals(this.debtorName, transaction.debtorName) &&
        Objects.equals(this.debtorAccount, transaction.debtorAccount) &&
        Objects.equals(this.transactionType, transaction.transactionType) &&
        Objects.equals(this.remittanceInformationUnstructured, transaction.remittanceInformationUnstructured) &&
        Objects.equals(this.remittanceInformationStructured, transaction.remittanceInformationStructured);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, endToEndId, bookingDate, valueDate, executionDateTime, transactionAmount, creditorName, creditorAccount, debtorName, debtorAccount, transactionType, remittanceInformationUnstructured, remittanceInformationStructured);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    endToEndId: ").append(toIndentedString(endToEndId)).append("\n");
    sb.append("    bookingDate: ").append(toIndentedString(bookingDate)).append("\n");
    sb.append("    valueDate: ").append(toIndentedString(valueDate)).append("\n");
    sb.append("    executionDateTime: ").append(toIndentedString(executionDateTime)).append("\n");
    sb.append("    transactionAmount: ").append(toIndentedString(transactionAmount)).append("\n");
    sb.append("    creditorName: ").append(toIndentedString(creditorName)).append("\n");
    sb.append("    creditorAccount: ").append(toIndentedString(creditorAccount)).append("\n");
    sb.append("    debtorName: ").append(toIndentedString(debtorName)).append("\n");
    sb.append("    debtorAccount: ").append(toIndentedString(debtorAccount)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
    sb.append("    remittanceInformationUnstructured: ").append(toIndentedString(remittanceInformationUnstructured)).append("\n");
    sb.append("    remittanceInformationStructured: ").append(toIndentedString(remittanceInformationStructured)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
