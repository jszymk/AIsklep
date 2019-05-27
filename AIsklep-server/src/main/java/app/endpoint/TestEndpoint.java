package app.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestEndpoint {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getEndpoint() {
        return ResponseEntity.ok().build();
    }
}
