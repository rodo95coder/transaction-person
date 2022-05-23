package com.nttdata.bootcamp.models.products;

import java.math.BigDecimal;
import java.util.Date;

import com.nttdata.bootcamp.exceptions.InsufficientAmountExcepcion;
import com.nttdata.bootcamp.exceptions.WrongDayExcepcion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FixedTermAccount {
	
	private String id;
	private String idCustomerPerson;
	private String accountingBalance;
	private String movementDay;
	
	public void withdrawal(BigDecimal amount) {
		String dateToday = String .valueOf(new Date());
		if(this.movementDay.equals(dateToday) ) {
		BigDecimal accBigDec = new BigDecimal(this.accountingBalance);
		BigDecimal newAmount = accBigDec.subtract(amount);
		if(newAmount.compareTo(BigDecimal.ZERO)<0) {
			throw new InsufficientAmountExcepcion("The account balance can't be less than zero");
		}
		this.accountingBalance =newAmount.toPlainString();
		}else {
			throw new WrongDayExcepcion("Today, you can't make transactions"); 
		}
		
	}
	
	public void deposit(BigDecimal amount) {
		String dateToday = String .valueOf(new Date());
		if(this.movementDay.equals(dateToday) ) {
		BigDecimal accBigDec = new BigDecimal(this.accountingBalance);
		BigDecimal result = accBigDec.add(amount);
		this.accountingBalance=result.toPlainString();
		}else {
			throw new WrongDayExcepcion("Today, you can't make transactions"); 
		}
	}
	
}
