package user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tein.ws.error.ApiError;
import com.tein.ws.shared.GenericResponse;

@RestController
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/1.0/users")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
		Map<String, String> validationErrors = new HashMap<>(); 
		
		
		String username = user.getDisplayName();
		@SuppressWarnings("unused")
		String displayName= user.getDisplayName();
		
		if(username==null || username.isEmpty()) {
			
			validationErrors.put("username", "Username cannot be null");
			error.setValidationErrors(validationErrors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		
		if(username==null || username.isEmpty()) {
			
			validationErrors.put("username", "Cannot be null");
			error.setValidationErrors(validationErrors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		
		userService.save(user);
		return ResponseEntity.ok(new GenericResponse("user created"));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationException(MethodArgumentNotValidException exception) {
		ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
		Map<String, String> validationErrors = new HashMap<>();
		for (FieldError fieldError: exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		error.setValidationErrors(validationErrors);
		return error;
	}
}