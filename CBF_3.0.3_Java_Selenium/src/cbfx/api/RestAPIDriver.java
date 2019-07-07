package cbfx.api;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import cbf.utils.JsonUtils;
import cbf.utils.LogUtils;

import static cbf.engine.TestResultLogger.failed;
import static cbf.engine.TestResultLogger.passed;

public class RestAPIDriver {
	ObjectMapper mapper = new ObjectMapper();

	public RestAPIDriver(){

	mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}
	
	public void open(){
		// Not required for API but added here for consistency.
	}
    public void close(){
    	// Not required for API but added here for consistency.
    }
    
    public void validateResponseStatus(Response response, int expectedStatusCode){
    	int statusCode = response.getStatusCode();
    		if(new Integer(statusCode).equals(new Integer(expectedStatusCode))){
    			passed("Check Correct Status code", "Should return Correct Status" , "Correct Status code returned: " + statusCode);
    		}else{
    			failed("Check Correct Status code", "Should return Correct Status" , "InCorrect Status code returned: " + statusCode);
    		}
 
    }
    
    public boolean validateResponseSchema(Response response, String schema){
    	boolean validatedOk = true;
    	try{
    	assertThat(response.body().asString(),matchesJsonSchemaInClasspath(schema));
    	logger.debug("json Schema validation OK");
    	}catch(Exception e){
    		validatedOk = false;
			logger.handleError("json Schema validation NOT OK" , e);
    	}
    	return validatedOk;
    }
    
    public boolean validateResponseSchema(Response response, File file){
    	boolean validatedOk = true;
    	try{
    	assertThat(response.body().asString(),matchesJsonSchema(file));
    	logger.debug("json Schema validation OK");
	}catch(Exception e){
		validatedOk = false;
		logger.handleError("json Schema validation NOT OK" , e);		
	}
	return validatedOk;
    }

    public void validateTextInResponse(Response response, String expected){

    	ResponseBody body = response.getBody();
    	if(body.asString().contains(expected)){
    	passed("Validate Text in response", "Should find the text in response string" , "Expected text found:" + expected);
    	}else{
    		failed("Validate Text in response", "Should find the text in response string" , "Expected text not found:" + expected);
    	}
    	
    }
    
    public List<String> getList(Response response, String jsonPath){
    	return response.jsonPath().getList(jsonPath);
    }
    
    public <T> void validateTextInResponsePath(Response response, String jsonPath, T expected){
    	try{
    	ResponseBody body = response.getBody();
    		if((body.jsonPath().get(jsonPath)).equals(expected)){
    		passed("Validate Text in the path", "Should find the expected text" , "Expected text found in Path:" + jsonPath);
    		}else{
    			failed("Validate Text in the path", "Should find the expected text" , "Expected text not found in Path:" + jsonPath);
    		}
    	}catch(Exception e){
    		logger.handleError("Expected text not found" , e);
    	}
    }

    
    public ArrayNode createJsonArrayNode(){
    	return mapper.createArrayNode();
    }
    
    public void addNode2ArrayNode(ArrayNode arrayNode, ObjectNode node){
    	arrayNode.add(node);
    }
    
    public ObjectNode createJsonObjectNode(){
    	return mapper.createObjectNode();
    }
    
    public void addElement2JsonNode(ObjectNode node, String str1, Object str2){
  	    if(isInteger(str2)){
	    	node.put(str1, Integer.parseInt((String) str2));
	    }else if (isDouble(str2)){
	    	node.put(str1, Double.parseDouble((String) str2));
	    }
	    else   
	    	node.put(str1, (String)str2);

   }
    
    @SuppressWarnings("deprecation")
	public void addJsonString2Node(ObjectNode node, String str1, String str2) throws JsonProcessingException, IOException{
    	    	node.put(str1, mapper.readTree((String)str2));

        }
    
    public ObjectNode addJson2ObjectNode(String str) throws JsonProcessingException, IOException {
 
				return (ObjectNode) mapper.readTree(new File(str));
	
        }
    
    public void addNode2JsonNode(ObjectNode jNode, String str, ObjectNode node){
    	jNode.putPOJO(str, node);
 
    }
    
    public void addArrayNode2JsonNode( ObjectNode jNode, String str, ArrayNode arrayNode){
    	jNode.putPOJO(str, arrayNode);

   }
    
   public void processArrayNodes(JsonNode jArray, String expectedField, String expectedVal){
	   try{
	   Iterator<JsonNode> it = jArray.iterator();
       while (it.hasNext()) {
           JsonNode node = it.next();
           if (node.isArray()){
        	   processArrayNodes(node, expectedField, expectedVal);
           }
           else{
        	   processSingleNode(node, expectedField, expectedVal);
           }
         }
	   }catch(Exception e){
		   logger.handleError("Error in processing an array Node:" + jArray , e);
	   }
	   

  }
   
   public void processSingleNode(JsonNode node, String expectedField, String expectedVal){

	   try{
	   if(node.isMissingNode()){
				logger.error("The node is missing");
           }
	   else if(node.isObject()){
		   Iterator<String> fieldNames = node.fieldNames();
	        while (fieldNames.hasNext()) {
	            String fieldName = fieldNames.next();
	            if (node.path(fieldName).isArray()){
	         	   processArrayNodes(node.path(fieldName), expectedField, expectedVal);
	            }
	            else{
	            if(fieldName.equals(expectedField)){
	            	validateVal(fieldName, node.path(fieldName), expectedVal);	            	
	            }else{
	            	processSingleNode(node.path(fieldName), expectedField, expectedVal);
	            }
	        }
	   }
	}
	   }catch(Exception e){
		   logger.handleError("Error in processing the Json Node:"+ node , e);
	   }
	   
}
   
   public void validateVal(String fieldName, JsonNode keyNode, String value){
	   if (keyNode.asText().equalsIgnoreCase(value)){
	   passed("Match Values", "Should match with expected value" , "Matched with expected value :" + value);
	   } else{
		   failed("Match Values", "Should match with expected value" , "Error getting expected value :" + value + " Actual: " + keyNode.asText());
		   
	   }
   }
    
    public void validateResponseInJsonNode(Response response, String key, String val){
			JsonNode rootNode;
			try {
				rootNode = mapper.readTree(response.asString());
				processArrayNodes(rootNode, key, val);
			} catch (JsonProcessingException je) {
				logger.handleError("Failure: JsonProocessing Exception", je);
				failed("Validate response in node", "Should find the value for the given key" , "Failure: JsonProocessing Exception:" + je.getMessage());

			} catch (IOException e) {
				logger.handleError("Failure: IOException", e);
				failed("Validate response in node", "Should find the expected value" , "Failure: IOException:" + e.getMessage());
			}
    	
   }
    
    public void validateResponseAgainstFile(Response response, String file){

		JsonNode rootNode;
		JsonNode expected = null;
		try {
			rootNode = mapper.readTree(response.asString());
			if (rootNode.isArray()){
		    org.json.simple.JSONArray expect = JsonUtils.parseFileToJsonArray(file);
		    expected = mapper.readTree(expect.toString());
		    if(expected.equals(rootNode)){
				passed("Validate response in file", "Should match the expected response in file" , "Response Matched with expected file");
				}else{
					failed("Validate response in file", "Should match the expected response in file " , "Failure in responce matching:");
				}
			}
		    else if (rootNode.isObject()){
		    	org.json.simple.JSONObject expect = JsonUtils.parseFileToJson(file);
		    
			expected = mapper.readTree(expect.toString());
			if(expected.equals(rootNode)){
				passed("Validate response against file", "Should match the expected response against file" , "Response Matched with expected file");
				}else{
					failed("Validate response against file", "Should match the expected response against file" , "Failure in responce matching:");
				}
		    }			
				
		}catch (JsonProcessingException je) {
			logger.handleError("Failure: JsonProocessing Exception", je);
			failed("Validate response in the file", "Should find the expected value" , "Failure: JsonProocessing Exception:" + je.getMessage());

		} catch (IOException e) {
			logger.handleError("Failure: IOException", e);
			failed("Validate response in the file", "Should find the expected value" , "Failure: IOException:" + e.getMessage());
		}
    }

    public String readSingleValuefromJsonResponse(Response response, String key) throws JSONException{
    	JSONArray jsonResponse = new JSONArray(response.jsonPath().getString(key));
		String value= null;
		try {
			value = jsonResponse.getString(0);
		} catch (JSONException e) {
			logger.handleError("Failure: JSONException", e);
			failed("Read Value from Json","Error in reading Json" , "Failure: JSONException:" +e.getMessage());
		}
		if (value != null){
		return value;
		}else{
    	return null;
		}
    }
    
    public String readValuefromJsonResponse(Response response, String key) throws JSONException{
    	JSONObject jsonResponse = new JSONObject(response.jsonPath().getString(key));
		String value= null;
		try {
			value = jsonResponse.toString();
		} catch (Exception e) {
			logger.handleError("Error in reading Json" , e);
		}
		if (value != null){
		return value;
		}else{
    	return null;
		}
    }
    public void validateHeaders(Response response, String headerString, String expectedHeaderVal){
   	
    	try{

    	if(response.header(headerString).equalsIgnoreCase(expectedHeaderVal)){
    		passed("Check Headers", "Should return Correct Header value" , "Correct Header returned: " + response.header(headerString));
    	}else{
    		failed("Check Headers", "Should return Correct Header value" , "Incorrect Header returned: " + response.header(headerString));
    	}
    	}catch(Exception e){
			logger.handleError("Response header validation failed" , e);
    	}
    }
   
    public static <T> boolean isInteger(T expected) {
        boolean isValidInteger = false;
        try
        {
           Integer.parseInt((String) expected);
           isValidInteger = true;
        }
        catch (NumberFormatException ex)
        {
           // s is not an integer
        }
           return isValidInteger;
     }
    
    public static boolean isDouble(Object expected) {
        boolean isValidDouble = false;
        try
        {
           Double.parseDouble((String) expected);
           isValidDouble = true;
        }
        catch (NumberFormatException ex)
        {
           // s is not an integer
        }
           return isValidDouble;
     }
    
    

    private LogUtils logger = new LogUtils(this);
}
