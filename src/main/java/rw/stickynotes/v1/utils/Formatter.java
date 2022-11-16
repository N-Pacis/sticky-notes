package rw.stickynotes.v1.utils;

import org.springframework.http.ResponseEntity;
import rw.stickynotes.v1.payload.ApiResponse;

public class Formatter {

    public static ResponseEntity<ApiResponse> ok(Object body) {
        return ResponseEntity.ok(ApiResponse.success(body));
    }

    public static ResponseEntity<ApiResponse> done(){
        return ResponseEntity.ok(ApiResponse.success("Done"));
    }

}
