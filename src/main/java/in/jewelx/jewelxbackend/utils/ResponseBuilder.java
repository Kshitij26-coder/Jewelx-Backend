package in.jewelx.jewelxbackend.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

public class ResponseBuilder {
	public static ResponseEntity<?> success(Object data) {

		Map<String, Object> map = new HashMap<>();
		map.put("status", "success");

		if (data != null)
			map.put("data", data);

		return ResponseEntity.ok(map);
	}

	public static ResponseEntity<?> error(Object err) {

		Map<String, Object> map = new HashMap<>();
		map.put("status", "error");

		if (err != null)
			map.put("error", err);

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}

	public static BodyBuilder status(HttpStatus status) {

		return  ResponseEntity.status(status);

	}
}
