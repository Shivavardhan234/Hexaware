package Entity;

import java.math.BigDecimal;

import exceptions.InvalidInputException;

public class CarLoan extends Loan{
	
	private String carModel;
	private BigDecimal carValue;
	public CarLoan(int loanId, int customer, BigDecimal principalAmount, BigDecimal interestRate,int loanTerm, String loanStatus, String CarModel,BigDecimal CarValue ) throws InvalidInputException {
			super(loanId, customer, principalAmount, interestRate, loanTerm, "CarLoan", loanStatus);
			setCarModel(CarModel);
			setCarValue(CarValue);
}
	
	
	
	
	//-------------------------getters and setters-------------------------------
	public String getCarModel() {
		return carModel;
	}
	public BigDecimal getCarValue() {
		return carValue;
	}
	public void setCarModel(String carModel) throws InvalidInputException {
		if(carModel==null|| carModel.trim().isEmpty()) {
			throw new InvalidInputException("Car model Should not be empty should not be empty");
		}
		
		this.carModel = carModel;
	}
	public void setCarValue(BigDecimal carValue)throws InvalidInputException {
		if(carValue==null || carValue.compareTo(BigDecimal.ZERO)<=0) {
			throw new InvalidInputException("Car value Should not be negative or 0");
		}
		this.carValue = carValue;
	}

}
