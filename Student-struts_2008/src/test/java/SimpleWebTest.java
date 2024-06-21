import net.sourceforge.jwebunit.junit.WebTestCase;
import net.sourceforge.jwebunit.util.TestContext;

public class SimpleWebTest extends WebTestCase{
	public SimpleWebTest(String name){
		super(name);
	}
	
	public void setUp() throws Exception{
		TestContext testContext = getTestContext();
		testContext.setBaseUrl("http://localhost:8080/Student_struts_2008_war");
		testContext.setResourceBundleName("MessageResources");
	}
	
	public void testAppAvailable(){
		beginAt("/index.jsp");
		
		assertFormElementPresent("login");
		assertFormElementPresent("password");
	}
	
	public void testLanguage(){
		beginAt("/index.jsp");
		
		this.clickLinkWithImage("/Student/css/img/ru.jpg");
	}
	
	public void testAuthorization(){
		beginAt("/index.jsp");
		
		setTextField("login", "Postrigan");
		setTextField("password", "pos");
		
		submit();
	}
	
	public void testAddTopicToForum() {
		this.testAuthorization();
        
		assertSubmitButtonPresent("buttonAdd");
		
        setTextField("newForumName", "test");
        setTextField("newForumPassword", "1");
        
        submit("buttonAdd");
    }
	
	public void testDeleteTopicToForum() {
		this.testAuthorization();
		
		assertSubmitButtonPresent("buttonDelete");
		
		this.checkCheckbox("selectedItems");
	}
	
	public void testChunkSize() {
		this.testAuthorization();		
		
		setTextField("chunkSize", "-40");
		this.assertTextPresent("20");		
		setTextField("chunkSize", "1");
		this.assertTextPresent("20");
		setTextField("chunkSize", "qw");
		this.assertTextPresent("20");
	}
	
	public void testUsers() {
		this.testAuthorization();
		
		this.clickLinkWithText("������������");		
		this.clickLinkWithText("��������������� � ��������������");
	}
	
	public void testDeleteUser() {
		this.testUsers();
		
		setWorkingForm("deleteUserForm");
		this.assertFormElementPresent("code");
	}
	
	public void testAddAndDeleteUser() {
		this.testUsers();
		
		String s = this.getFormElementValue("people.id_People");
		setTextField("people.surname", "Test_people.surname");
		setTextField("people.name", "Test_people.name");
		setTextField("people.patronymic", "Test_people.patronymic");
		setTextField("people.login", "Test_people.login");
		setTextField("people.password", "Test_people.password");
		setTextField("people.age", "2000");
		setTextField("people.address", "people.address");
		this.selectOption("people.people_type", "Admin");
		
		this.clickButtonWithText("��������");
		
		setWorkingForm("deleteUserForm");
		setTextField("code", s);
		submit();
	}
	
	public void testHelp() {
		this.testAuthorization();
		
		this.clickLinkWithText("������");
	}
}
