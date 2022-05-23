package com.nttdata.bootcamp.models.products;

import java.math.BigDecimal;

import com.nttdata.bootcamp.exceptions.ExceededLimitPersonExcepcion;
import com.nttdata.bootcamp.exceptions.InsufficientAmountExcepcion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalCredit {
	
	private String id;
	private String idCustomerPerson;
	private String accountingBalance;
	private String availableBalance;
	
	public void withdrawal(BigDecimal amount) {

		String limitPerson = String .valueOf(1);
		if(this.idCustomerPerson.equals(limitPerson) ) {		
		BigDecimal accBigDec = new BigDecimal(this.accountingBalance);
		BigDecimal newAmount = accBigDec.subtract(amount);
		if(newAmount.compareTo(BigDecimal.ZERO)<0) {
			throw new InsufficientAmountExcepcion("The account balance can't be less than zero.");
		}
		this.accountingBalance =newAmount.toPlainString();
		}else {
			throw new ExceededLimitPersonExcepcion("Only one person per credit it's available."); 
		}
	}
	
	public void deposit(BigDecimal amount) {
		BigDecimal accBigDec = new BigDecimal(this.accountingBalance);
		BigDecimal result = accBigDec.add(amount);
		this.accountingBalance=result.toPlainString();
	}
	
}
