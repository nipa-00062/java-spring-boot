package com.advpro.gpb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Payments")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column 
    @NotNull(message = "Amount is mandatory")
    @NotBlank(message = "Amount is mandatory")
    private String amount;

    @Column
    @NotNull(message = "Payment Method is mandatory")
    @NotBlank(message = "Payment Method is mandatory")
    private String paymentMethod;

    public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

}