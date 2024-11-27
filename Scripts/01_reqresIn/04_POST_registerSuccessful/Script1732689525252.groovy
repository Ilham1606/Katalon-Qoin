import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil
import groovy.json.JsonSlurper

// Assign value ke dalam String
String email = 'eve.holt@reqres.in'
String password = 'pistol'


// Send request
RequestObject testObject = findTestObject('Object Repository/01_reqresIn/04_registerSuccessful', 
    [
        ("email") : email,
        ("password") : password
    ])

ResponseObject objectResp = WS.sendRequest(testObject, FailureHandling.STOP_ON_FAILURE)

// define fungsi untuk verify response
def verifyRegister (ResponseObject objectResp) {
    KeywordUtil.logInfo("HEADER\n" + objectResp.getHeaderFields() + "\n\nBODY\n" + objectResp.getResponseBodyContent())
    
    // Check status code
    if (objectResp.getStatusCode() == 200) {
        JsonSlurper jsonSluper = new JsonSlurper()
        def jsonResp = jsonSluper.parseText(objectResp.getResponseText())
        KeywordUtil.logInfo("Registration Successful, the Token is : " + jsonResp.token)
		
    } else {
        KeywordUtil.logInfo("Registration failed with status code: " + objectResp.getStatusCode())
        JsonSlurper jsonSluper = new JsonSlurper()
        def jsonResp = jsonSluper.parseText(objectResp.getResponseText())
        // Log error message dari response
        KeywordUtil.logInfo("Error message: " + jsonResp.error)
    }
}

// Memanggil fungsi verifyRegister
verifyRegister(objectResp)