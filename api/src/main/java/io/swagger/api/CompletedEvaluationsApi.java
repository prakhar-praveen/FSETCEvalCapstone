/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.9).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.CompletedEvaluation;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-07T08:20:05.576Z")

@Api(value = "completedEvaluations", description = "the completedEvaluations API")
public interface CompletedEvaluationsApi {

    @ApiOperation(value = "Get all completed evaluations", nickname = "getAllCompletedEvaluations", notes = "", response = CompletedEvaluation.class, responseContainer = "List", tags={ "completedEvaluations", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = CompletedEvaluation.class, responseContainer = "List") })
    @RequestMapping(value = "/completedEvaluations",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<CompletedEvaluation>> getAllCompletedEvaluations();


    @ApiOperation(value = "Get all completed evaluations that have been shared with a user", nickname = "getSharedUserEvaluations", notes = "", response = CompletedEvaluation.class, responseContainer = "List", tags={ "completedEvaluations", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = CompletedEvaluation.class, responseContainer = "List") })
    @RequestMapping(value = "/completedEvaluations/shared/{asurite}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<CompletedEvaluation>> getSharedUserEvaluations(@ApiParam(value = "", required = true) @PathVariable("asurite") String asurite);


    @ApiOperation(value = "Get all completed evaluations for a user", nickname = "getUserEvaluations", notes = "", response = CompletedEvaluation.class, responseContainer = "List", tags={ "completedEvaluations", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = CompletedEvaluation.class, responseContainer = "List") })
    @RequestMapping(value = "/completedEvaluations/{asurite}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<CompletedEvaluation>> getUserEvaluations(@ApiParam(value = "", required = true) @PathVariable("asurite") String asurite);

}
