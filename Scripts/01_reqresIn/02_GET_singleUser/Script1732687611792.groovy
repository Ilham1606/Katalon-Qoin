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

// Send request
RequestObject testObject = findTestObject('Object Repository/01_reqresIn/02_singleUser')

ResponseObject objectResp = WS.sendRequest(testObject, FailureHandling.STOP_ON_FAILURE)

// Define fungsi untuk verify response
def verifySingleUser (ResponseObject objectResp){
	KeywordUtil.logInfo("HEADER\n"+objectResp.getHeaderFields()+"\n\nBODY\n"+objectResp.getResponseBodyContent())
	WS.verifyResponseStatusCode(objectResp, 200)
	JsonSlurper jsonSluper = new JsonSlurper()
	def jsonResp = jsonSluper.parseText(objectResp.getResponseText())
	KeywordUtil.logInfo("Verify ID : " + jsonResp.data.id)
	KeywordUtil.logInfo("Verify Email : " + jsonResp.data.email)
	KeywordUtil.logInfo("Verify First Name : " + jsonResp.data.first_name)
	KeywordUtil.logInfo("Verify Last Name : " + jsonResp.data.last_name)
}

// Memanggil fungsi verifySingleUser
verifySingleUser(objectResp)







