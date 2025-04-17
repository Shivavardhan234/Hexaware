package com.hexaware.PayXpert.entity;

import java.time.LocalDate;
import java.time.Period;

import com.hexaware.PayXpert.exceptions.InvalidInputException;

public class Employee {
	
	private int employeeId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    private String phoneNumber;
    private String address;
    private String position;
    private LocalDate joiningDate;
    private LocalDate terminationDate;
    
//------------------------------------constructor--------------------------------------
    public Employee(int employeeId, String firstName, String lastName, LocalDate dateOfBirth,String gender, String email, String phoneNumber, String address,String position, LocalDate joiningDate, LocalDate terminationDate)throws InvalidInputException {
setEmployeeId( employeeId);
setFirstName ( firstName);
setLastName ( lastName);
setDateOfBirth ( dateOfBirth);
setGender ( gender);
setEmail ( email);
setPhoneNumber ( phoneNumber);
setAddress ( address);
setPosition ( position);
setJoiningDate ( joiningDate);
setTerminationDate ( terminationDate);
}
    
    
    
    
//--------------------------------calculate age-------------------------------------
    public int calculateAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }


//----------------------------------getters and setters-------------------------------------------

	public int getEmployeeId() {
		return employeeId;
	}




	public String getFirstName() {
		return firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}




	public String getGender() {
		return gender;
	}




	public String getEmail() {
		return email;
	}




	public String getPhoneNumber() {
		return phoneNumber;
	}




	public String getAddress() {
		return address;
	}




	public String getPosition() {
		return position;
	}




	public LocalDate getJoiningDate() {
		return joiningDate;
	}




	public LocalDate getTerminationDate() {
		return terminationDate;
	}




	public void setEmployeeId(int employeeId)throws InvalidInputException {
        if (employeeId <= 0) {
            throw new InvalidInputException("Employee ID must be greater than 0.");
        }
        this.employeeId = employeeId;
    }




	public void setFirstName(String firstName)throws InvalidInputException {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new InvalidInputException("First name cannot be null or empty.");
        }
        this.firstName = firstName;
    }




	public void setLastName(String lastName)throws InvalidInputException {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidInputException("Last name cannot be null or empty.");
        }
        this.lastName = lastName;
    }




	public void setDateOfBirth(LocalDate dateOfBirth)throws InvalidInputException {
        if (dateOfBirth == null) {
            throw new InvalidInputException("Date of birth cannot be null.");
        }
        this.dateOfBirth = dateOfBirth;
    }




	public void setGender(String gender)throws InvalidInputException {
        if (gender == null || !(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("T"))) {
            throw new InvalidInputException("Gender must be 'M', 'F', or 'T'.");
        }
        this.gender = gender.toUpperCase();
    }




	public void setEmail(String email)throws InvalidInputException {
        if (email == null || !(email.contains("@") && (email.endsWith(".com") || email.endsWith(".in")))) {
            throw new InvalidInputException("Invalid email format.");
        }
        this.email = email;
    }




	public void setPhoneNumber(String phoneNumber)throws InvalidInputException {
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            throw new InvalidInputException("Phone number must be exactly 10 digits.");
        }
        this.phoneNumber = phoneNumber;
    }




	public void setAddress(String address)throws InvalidInputException {
        if (address == null || address.trim().isEmpty()) {
            throw new InvalidInputException("Address cannot be null or empty.");
        }
        this.address = address;
    }




	public void setPosition(String position)throws InvalidInputException {
        if (position == null || position.trim().isEmpty()) {
            throw new InvalidInputException("Position cannot be null or empty.");
        }
        this.position = position;
    }




	public void setJoiningDate(LocalDate joiningDate) throws InvalidInputException {
	    if (joiningDate == null) {
	        throw new InvalidInputException("Joining date cannot be null.");
	    }
	    if (joiningDate.isAfter(LocalDate.now())) {
	        throw new InvalidInputException("Joining date cannot be in the future.");
	    }
	    this.joiningDate = joiningDate;
	}




	public void setTerminationDate(LocalDate terminationDate) throws InvalidInputException {
        if (terminationDate != null  && terminationDate.isBefore(joiningDate)) {
            throw new InvalidInputException("Termination date cannot be before joining date.");
        }
        this.terminationDate = terminationDate;
    }

}
