package finals;

/**
 * <p>Final класс для хранения констант</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public final class Final {
	
	public static final String DATA_BASE_CONFIG = "dataBaseConfig";
	public static final String PATH_NAME = "/Upload/";
	public static final String PATH_STUDENT = "/Student-struts_2008/Upload/";
	public static final String PATH_AVATAR_DEFAULT = "/Student-struts_2008/css/img/Avatar.png";
	
	public static final String AVATAR_DEFAULT = "/Student-struts_2008/css/img/AvatarDefault.jpg";
	
	
	public static final String ERROR_DB = "error.db";
	
	public static final String ERROR_AUTHORIZATION_LOGIN = "error.authorization.login";
	public static final String ERROR_AUTHORIZATION_INCORRECT_LOGIN = "error.authorization.incorrect.login";
	public static final String ERROR_AUTHORIZATION_INCORRECT_PASSWORD = "error.authorization.incorrect.password";
	
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	
	public static final String PEOPLE_TYPE_ADMIN = "Admin";
	public static final String PEOPLE_TYPE_STUDENT = "Student";
	public static final String PEOPLE_TYPE_TEACHER = "Teacher";
	public static final String PEOPLE_TYPE_EMPLOYEE = "Employee";
			
	// Размер выборки
	public static final int CHUNK_SIZE_VALUE = 20;
	// Год рождения
	public static final int AGE_DEFAULT = 2000;
	// SimpleDateFormat
	public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd"; 
	
	// FORWARDS ----------------------------------------------------------------------	
		
		public static final String FORWARD_SUCCESS = "success";
	// ----------------------------------------------------------------------
	
	// BEANS ----------------------------------------------------------------------
		
		public static final String CHUNK_SIZE = "chunkSize";
		public static final String START = "start";
		
		// Forum_8
		public static final String ID_CHAPTER = "id_Chapter";
		
		public static final String TASK = "task";
		public static final String ALTERNATIVE = "alternative";
		public static final String CRITERION = "criterion";
		
		public static final String RESULT = "result";
		
		public static final String STUDENT = "student";
		
		// Message
		public static final String MESSAGE_READ = "messageRead";
		public static final String MESSAGE_NEW = "messageNew";
		public static final String NEW_MESSAGE = "newMessage";
		
		public static final String MENU_LIST = "menuList";
		public static final String MENU_LIST_NEW = "menuListNew";
		
		// Comment
		public static final String NEW_COMMENT = "newComment";
		public static final String ID_OBJECT = "id_Object";
		public static final String OBJECT_NAME = "objectName";
		public static final String OBJECT = "object";
		
		// People
		public static final String PEOPLE = "people";
	// ----------------------------------------------------------------------
	
	// ACTIONS ----------------------------------------------------------------------
		
		// Action edit {
			public static final String CODE_COMMIT_ADD = "code_add";
			public static final String NAME_COMMIT_ADD = "name_add";	
		
			public static final String CODE_COMMIT_DELETE = "code_delete";
		
			public static final String CODE_COMMIT_EDIT = "code_edit";
			public static final String NAME_COMMIT_EDIT = "name_edit";
		// }		
		
		// Action edit button
		public static final String BUTTON_EDIT = "buttonEdit";
		public static final String BUTTON_DELETE = "buttonDelete";
		public static final String BUTTON_ADD = "buttonAdd";
		public static final String BUTTON_SHOW = "buttonShow";
		public static final String BUTTON_UPLOAD = "buttonUpload";
		public static final String BUTTON_SOLVE = "buttonSolve";
		
		public static final String BUTTON_DELETE_EMAIL = "buttonDeleteEmail";
		public static final String BUTTON_ADD_EMAIL = "buttonAddEmail";
		
		public static final String BUTTON_DELETE_PHONE = "buttonDeletePhone";
		public static final String BUTTON_ADD_PHONE = "buttonAddPhone";
		
		public static final String BUTTON_DELETE_EMPLOYMENT = "buttonDeleteEmployment";
		public static final String BUTTON_ADD_EMPLOYMENT = "buttonAddEmployment";
		
		public static final String BUTTON_DELETE_ALTERNATIVE = "buttonDeleteAlternative";
		public static final String BUTTON_ADD_ALTERNATIVE = "buttonAddAlternative";
		
		public static final String BUTTON_DELETE_CRITERION = "buttonDeleteCriterion";
		public static final String BUTTON_ADD_CRITERION = "buttonAddCriterion";
		
		// Action edit success	
		public static final String STRING_DELETED = "stringDeleted";
		public static final String STRING_INSERTED = "stringInserted";
		public static final String STRING_EDITED = "stringEdited";
	// ----------------------------------------------------------------------
	
	// ERRORS ----------------------------------------------------------------------
	
		//public static final String ERROR_DB = "error.db";			
		public static final String ERROR_NO_VALUE = "error.noValue";
		public static final String ERROR_FILE = "error.file";
		public static final String ERROR_CHUNK_SIZE = "error.chunkSize";
		
		
		// Modify people messages (MessageResources) {
			public static final String ERROR_MESSAGE_USER__CODE = "error.message.user.code";
			public static final String ERROR_MESSAGE_USER__SURNAME = "error.message.user.surname";
			public static final String ERROR_MESSAGE_USER__NAME = "error.message.user.name";
			public static final String ERROR_MESSAGE_USER__PATRONYMIC = "error.message.user.patronymic";
			public static final String ERROR_MESSAGE_USER__LOGIN = "error.message.user.login";
			public static final String ERROR_MESSAGE_USER__PASSWORD = "error.message.user.password";
			public static final String ERROR_MESSAGE_USER__AGE = "error.message.user.age";
			public static final String ERROR_MESSAGE_USER__ADDRESS = "error.message.user.address";
			
			public static final String ERROR_MESSAGE_ALTERNATIVE = "error.message.alternative";
			public static final String ERROR_MESSAGE_CRITERION = "error.message.criterion";
			
			public static final String ERROR_MESSAGE_FORUM_NAME = "error.forum.name";
			public static final String ERROR_MESSAGE_PASSWORD = "error.password.name";
			
			public static final String ERROR_MESSAGE_NEW_THEME = "error.messageNew.theme";
		// }
			
		// Insert {
			public static final String ERROR_CODE_ADD = "error.code.add";
			public static final String ERROR_PEOPLE_SURNAME_ADD = "error.people.surname.add";
			public static final String ERROR_PEOPLE_NAME_ADD = "error.people.name.add";
			public static final String ERROR_PEOPLE_PATRONYMIC_ADD = "error.people.patronymic.add";
			public static final String ERROR_PEOPLE_LOGIN_ADD = "error.people.login.add";
			public static final String ERROR_PEOPLE_PASSWORD_ADD = "error.people.password.add";
			public static final String ERROR_AGE_ADD = "error.people.age.add";
			public static final String ERROR_PEOPLE_ADDRESS_ADD = "error.people.address.add";
						
			public static final String ERROR_EMAIL_ADD = "error.email.add";
			public static final String ERROR_PHONE_ADD = "error.phone.add";
			public static final String ERROR_EMPLOYMENT_ADD = "error.employment.add";
			
			public static final String ERROR_TASK_ADD = "error.task.add";
			
			public static final String ERROR_ALTERNATIVE_ADD = "error.alternative.add";
			public static final String ERROR_CRITERION_ADD = "error.criterion.add";
			
			public static final String ERROR_FORUM_NAME_ADD = "error.forum.name.add";
			public static final String ERROR_FORUM_PASSWORD_ADD = "error.forum.password.add";
			
			public static final String ERROR_MESSAGE_NEW_THEME_ADD = "error.messageNew.theme.add";
			
			public static final String ERROR_SOLVE = "error.solve";
			public static final String ERROR_INSERT = "error.insert";
		// }
			
		// Delete {
			public static final String ERROR_CODE_DELETE = "error.code.delete";
			
			public static final String ERROR_EMAIL_DELETE = "error.email.delete";
			public static final String ERROR_PHONE_DELETE = "error.phone.delete";
			public static final String ERROR_EMPLOYMENT_DELETE = "error.employment.delete";
			
			public static final String ERROR_ALTERNATIVE_DELETE = "error.alternative.delete";
			public static final String ERROR_CRITERION_DELETE = "error.criterion.delete";
			
			public static final String ERROR_DELETE = "error.delete";
			
		// }
			
		// Edit {
			public static final String ERROR_CODE_NO_VALUE_FOR_SHOW_EDIT = "error.code.noValueForShow.edit";
			public static final String ERROR_FILE_EDIT = "error.file.edit";
			
			public static final String ERROR_CODE_EDIT = "error.code.edit";			
			public static final String ERROR_PEOPLE_SURNAME_EDIT = "error.people.surname.edit";
			public static final String ERROR_PEOPLE_NAME_EDIT = "error.people.name.edit";
			public static final String ERROR_PEOPLE_PATRONYMIC_EDIT = "error.people.patronymic.edit";
			public static final String ERROR_PEOPLE_LOGIN_EDIT = "error.people.login.edit";
			public static final String ERROR_PEOPLE_PASSWORD_EDIT = "error.people.password.edit";
			public static final String ERROR_AGE_EDIT = "error.people.age.edit";
			public static final String ERROR_PEOPLE_ADDRESS_EDIT = "error.people.address.edit";
			
			public static final String ERROR_EDIT = "error.edit";
		// }		
	// ----------------------------------------------------------------------
}
