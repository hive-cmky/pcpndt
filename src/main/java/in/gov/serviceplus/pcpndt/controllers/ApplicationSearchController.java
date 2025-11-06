package in.gov.serviceplus.pcpndt.controllers;

import in.gov.serviceplus.pcpndt.dto.response.ApplicationSearchResult;
import in.gov.serviceplus.pcpndt.dto.response.ErrorResponse;
import in.gov.serviceplus.pcpndt.service.ApplicationSearchService;
import in.gov.serviceplus.pcpndt.service.exception.ApplicationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applications")
@CrossOrigin(origins = "*")
public class ApplicationSearchController {
    private final ApplicationSearchService applicationSearchService;

    public ApplicationSearchController(ApplicationSearchService applicationSearchService) {
        this.applicationSearchService = applicationSearchService;
    }


    // Search application by application reference number
    // GET /api/v1/applications/search?applRefNo=PCPNDT/2021/00099

    @GetMapping("/search")
    public ResponseEntity<?> searchByApplRefNo(
            @RequestParam String applRefNo,
            @RequestParam(required = false, defaultValue = "false") boolean basicOnly) {

        try {
            if (basicOnly) {
                // Return only basic application info
                return ResponseEntity.ok(applicationSearchService.getApplicationBasicInfo(applRefNo));
            } else {
                // Return complete application data with execution history
                ApplicationSearchResult result = applicationSearchService.searchByApplRefNo(applRefNo);
                return ResponseEntity.ok(result);
            }
        } catch (ApplicationNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("NOT_FOUND", ex.getMessage()));
        }
    }


     // Search application by application ID
     // GET /api/v1/applications/search-by-id?applId=33333226

    @GetMapping("/search-by-id")
    public ResponseEntity<?> searchByApplId(@RequestParam String applId) {
        try {
            ApplicationSearchResult result = applicationSearchService.searchByApplId(applId);
            return ResponseEntity.ok(result);
        } catch (ApplicationNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("NOT_FOUND", ex.getMessage()));
        }
    }



    // Health check endpoint

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Application Search API is running");
    }
}
