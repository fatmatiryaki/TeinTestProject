package user;

import javax.validation.ConstraintValidator;

import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator<ConstraintvalidatorContext> implements ConstraintValidator<UniqueUsername, String>{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean isValid(String username, ConstraintvalidatorContext context) {
         //todo
		return false;
	}
}
