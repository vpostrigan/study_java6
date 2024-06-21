
package database;

import beans.Alternative;
import beans.Comment;
import beans.Criterion;
import beans.Forum;
import beans.Message;
import beans.People;
import beans.Student;
import beans.Task;

/**
 * <p>Этот класс хранит запросы к БД</p>
 * @author Postrigan V.A.
 * @version 1.0 
 */
public final class DataBaseQuery{

	private final static String PEOPLE_1 = "People_1";
	private final static String PEOPLE_1__ID_PEOPLE = "People_1.Id_People";
	private final static String PEOPLE_1__SURNAME = "People_1.Surname";
	private final static String PEOPLE_1__NAME = "People_1.Name";
	private final static String PEOPLE_1__PATRONYMIC = "People_1.Patronymic";
	private final static String PEOPLE_1__LOGIN = "People_1.Login";
	private final static String PEOPLE_1__PASSWORD = "People_1.Password";
	private final static String PEOPLE_1__PEOPLE_TYPE = "People_1.People_type";
	private final static String PEOPLE_1__AGE = "People_1.Age";
	private final static String PEOPLE_1__ADDRESS = "People_1.Address";
	private final static String PEOPLE_1__AVATAR = "People_1.Avatar";
	
	
	private final static String PEOPLE_PHONE_2 = "People_Phone_2";
	private final static String PEOPLE_PHONE_2__ID_PEOPLE = "People_Phone_2.Id_People";
	private final static String PEOPLE_PHONE_2__PHONE = "People_Phone_2.Phone";
	
	
	private final static String ADMIN_3 = "Admin_3";
	private final static String ADMIN_3__ID_PEOPLE = "Admin_3.Id_People";
	
	
	private final static String STUDENT_4 = "Student_4";
	private final static String STUDENT_4__ID_PEOPLE = "Student_4.Id_People";
	private final static String STUDENT_4__RESUME = "Student_4.Resume";
	
	
	private final static String TEACHER_5 = "Teacher_5";
	private final static String TEACHER_5__ID_PEOPLE = "Teacher_5.Id_People";
	
	
	private final static String EMPLOYEE_6 = "Employee_6";
	private final static String EMPLOYEE_6__ID_PEOPLE = "Employee_6.Id_People";
	
	
	private final static String MESSAGE_7 = "Message_7";
	private final static String MESSAGE_7__ID_MESSAGE = "Message_7.Id_Message";
	private final static String MESSAGE_7__ID_PEOPLE_CREATOR = "Message_7.Id_People_Creator";
	private final static String MESSAGE_7__ID_PEOPLE_RECEIVER = "Message_7.Id_People_Receiver";
	private final static String MESSAGE_7__MESSAGE = "Message_7.Message";
	private final static String MESSAGE_7__THEME = "Message_7.Theme";
	private final static String MESSAGE_7__MESSAGE_DATE = "Message_7.Message_Date";
	
	
	private final static String FORUM_8 = "Forum_8";
	private final static String FORUM_8__ID_CHAPTER = "Forum_8.Id_Chapter";
	private final static String FORUM_8__CHAPTER_NAME = "Forum_8.Chapter_Name";
	private final static String FORUM_8__CHAPTER_PASSWORD = "Forum_8.Chapter_Password";
	private final static String FORUM_8__ID_PEOPLE = "Forum_8.Id_People";
	private final static String FORUM_8__CHAPTER_DATE = "Forum_8.Chapter_Date";
	
	
	private final static String COMMENT_9 = "Comment_9";
	private final static String COMMENT_9__ID_CHAPTER = "Comment_9.Id_Chapter";
	private final static String COMMENT_9__COMMENT_NUMBER = "Comment_9.Comment_number";
	private final static String COMMENT_9__ID_PEOPLE = "Comment_9.Id_People";
	private final static String COMMENT_9__COMMENT_VALUE = "Comment_9.Comment_value";
	private final static String COMMENT_9__COMMENT_DATE = "Comment_9.Comment_Date";
	
	
	private final static String TASK_13 = "Task_13";
	private final static String TASK_13__ID_TASK = "Task_13.Id_Task";
	private final static String TASK_13__NAME = "Task_13.Name";
	private final static String TASK_13__DATE = "Task_13.Date";
	private final static String TASK_13__ID_PEOPLE = "Task_13.Id_People";
	
	
	private final static String ALTERNATIVE_14 = "Alternative_14";
	private final static String ALTERNATIVE_14__ID_TASK = "Alternative_14.Id_Task";
	private final static String ALTERNATIVE_14__ALTERNATIVE_NAME = "Alternative_14.Alternative_Name";
	
	
	private final static String CRITERION_15 = "Criterion_15";
	private final static String CRITERION_15__ID_TASK = "Criterion_15.Id_Task";
	private final static String CRITERION_15__CRITERION_NAME = "Criterion_15.Criterion_Name";	
	
	
	private final static String COMMENT_OBJECT_17 = "Comment_Object_17";
	private final static String COMMENT_OBJECT_17__ID_CHAPTER = "Comment_Object_17.Id_Chapter";
	private final static String COMMENT_OBJECT_17__COMMENT_NUMBER = "Comment_Object_17.Comment_number";
	private final static String COMMENT_OBJECT_17__ID_OBJECT = "Comment_Object_17.Id_Object";	
	
	
	private final static String PEOPLE_EMAIL_19 = "People_Email_19";
	private final static String PEOPLE_EMAIL_19__ID_PEOPLE = "People_Email_19.Id_People";
	private final static String PEOPLE_EMAIL_19__EMAIL = "People_Email_19.E_mail";
	
	
	private final static String PEOPLE_EMPLOYMENT_20 = "People_Employment_20";
	private final static String PEOPLE_EMPLOYMENT_20__ID_PEOPLE = "People_Employment_20.Id_People";
	private final static String PEOPLE_EMPLOYMENT_20__EMPLOYMENT = "People_Employment_20.Employment";
	
	
	private final static String DATA_OBJECT_21 = "Data_Object_21";
	private final static String DATA_OBJECT_21__ID_OBJECT = "Data_Object_21.Id_Object";
	private final static String DATA_OBJECT_21__OBJECT = "Data_Object_21.Object";
	
	
	private final static String MESSAGE_OBJECT_23 = "Message_Object_23";
	private final static String MESSAGE_OBJECT_23__ID_MESSAGE = "Message_Object_23.Id_Message";
	private final static String MESSAGE_OBJECT_23__ID_OBJECT = "Message_Object_23.Id_Object";	
	
	///// Authorization
	public static String authorizationLogin(String login){ 
		return " SELECT " + PEOPLE_1__ID_PEOPLE + 
				" FROM " + PEOPLE_1 + 
				" WHERE " + PEOPLE_1__LOGIN + "='" + login + "'";
	}	
	public static String authorizationPassword(String login, String passwd){ 
		return " SELECT " + PEOPLE_1__ID_PEOPLE + 
				" FROM " + PEOPLE_1 + 
				" WHERE (" + PEOPLE_1__LOGIN + "='" + login + "') AND (" + PEOPLE_1__PASSWORD + "='" + passwd + "')";
	}
	
	///// People
	public static String getPeople1(String login, String passwd){
		return " SELECT " + PEOPLE_1__NAME + "," + PEOPLE_1__PEOPLE_TYPE +
				" FROM " + PEOPLE_1 + 
				" WHERE (" + PEOPLE_1__LOGIN + "='" + login + "') AND (" + PEOPLE_1__PASSWORD + "='" + passwd + "')";
	}
	public static String getPeopleAll(){
		return "SELECT " + PEOPLE_1__ID_PEOPLE + ", " + PEOPLE_1__SURNAME + ", " + PEOPLE_1__NAME + ", " + 
							PEOPLE_1__PATRONYMIC + ", " + PEOPLE_1__LOGIN + ", " + PEOPLE_1__PASSWORD + ", " +
							PEOPLE_1__PEOPLE_TYPE + ", " + PEOPLE_1__AGE + ", " + PEOPLE_1__ADDRESS + ", " + 
							PEOPLE_1__AVATAR +
				" FROM " + PEOPLE_1 +
				" ORDER BY " + PEOPLE_1__PEOPLE_TYPE + ", " + PEOPLE_1__SURNAME + ", " + PEOPLE_1__NAME; 
	}
	public static String getProductOneRecord(int code){
		return "SELECT " + PEOPLE_1__ID_PEOPLE + ", " + PEOPLE_1__SURNAME + ", " + PEOPLE_1__NAME + ", " + 
							PEOPLE_1__PATRONYMIC + ", " + PEOPLE_1__LOGIN + ", " + PEOPLE_1__PASSWORD + ", " +
							PEOPLE_1__PEOPLE_TYPE + ", " + PEOPLE_1__AGE + ", " + PEOPLE_1__ADDRESS + ", " + 
							PEOPLE_1__AVATAR + 
				" FROM " + PEOPLE_1 + 
				" WHERE " + PEOPLE_1__ID_PEOPLE + " = '"+ code + "'";
	}
	
	public static String getPeopleFreeID(){
		return "SELECT " + PEOPLE_1__ID_PEOPLE + " FROM " + PEOPLE_1 + " ORDER BY " + PEOPLE_1__ID_PEOPLE;
	}
	
	public static String insertPeople(People p){	
		return "INSERT INTO " + PEOPLE_1 + 
			"("+ PEOPLE_1__ID_PEOPLE + ", " + PEOPLE_1__SURNAME + ", " + PEOPLE_1__NAME + ", " + PEOPLE_1__PATRONYMIC + ", " 
				+ PEOPLE_1__LOGIN + ", " + PEOPLE_1__PASSWORD + ", " + PEOPLE_1__PEOPLE_TYPE + ", " + PEOPLE_1__AGE + ", " 
				+ PEOPLE_1__ADDRESS + ", " + PEOPLE_1__AVATAR + 
			") VALUES(" + "'" + p.getId_People() + "', '" + p.getSurname() + "', '" + p.getName() + "', '" + 
								p.getPatronymic() + "', '" + p.getLogin() + "', '" + p.getPassword() + "', '" +
								p.getPeople_type() + "', '" + p.getAge() + "', '" + p.getAddress() + "', '" +
								p.getAvatar() + "')";
	}
	
	public static String deletePeople(int code){		
		return "DELETE FROM " + PEOPLE_1 + " WHERE "+ PEOPLE_1__ID_PEOPLE + "=" + code;
	}
	
	public static String updatePeople(People p, int oldCode){
		return "UPDATE " + PEOPLE_1 +
					" SET " + PEOPLE_1__ID_PEOPLE + "=" + "'" + p.getId_People() + "', " +
							PEOPLE_1__SURNAME + "=" + "'" + p.getSurname() + "', " +
							PEOPLE_1__NAME + "=" + "'" + p.getName() + "', " +
							PEOPLE_1__PATRONYMIC + "=" + "'" + p.getPatronymic() + "', " +
							PEOPLE_1__LOGIN + "=" + "'" + p.getLogin() + "'," +
							PEOPLE_1__PASSWORD + "=" + "'" + p.getPassword() + "', " +
							PEOPLE_1__PEOPLE_TYPE + "=" + "'" + p.getPeople_type()+ "', " +
							PEOPLE_1__AGE + "=" + "'" + p.getAge() + "', " +
							PEOPLE_1__ADDRESS + "=" + "'" + p.getAddress() + "' " + 
					" WHERE " + PEOPLE_1__ID_PEOPLE + "=" + "'" + oldCode + "'";		
	}
	
	public static String updatePeopleAvatar(String s, int id){
		return "UPDATE " + PEOPLE_1 +
					" SET " + PEOPLE_1__AVATAR + "=" + "'" + s + "' " + 
					" WHERE " + PEOPLE_1__ID_PEOPLE + "=" + "'" + id + "'";		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///// Admin
	public static String insertAdmin(int i){	
		return "INSERT INTO " + ADMIN_3 + 
			"("+ ADMIN_3__ID_PEOPLE +  
			") VALUES('" + i + "')";
	}
	
	public static String deleteAdmin(int code){		
		return "DELETE FROM " + ADMIN_3 + " WHERE "+ ADMIN_3__ID_PEOPLE + "=" + code;
	}
	
	///// Student
	public static String getStudent_4(int id){	
		return "SELECT " + STUDENT_4__RESUME + 
				" FROM " + STUDENT_4 +
				" WHERE " + STUDENT_4__ID_PEOPLE + "='" + id + "'";
	}
	public static String getStudent_4_Resume(){
		return "SELECT " + PEOPLE_1__ID_PEOPLE + ", " + PEOPLE_1__SURNAME + ", " + PEOPLE_1__NAME + ", " + 
							PEOPLE_1__PATRONYMIC +
				" FROM " + PEOPLE_1 + " INNER JOIN " + STUDENT_4 + " ON " + 
									PEOPLE_1__ID_PEOPLE + "=" + STUDENT_4__ID_PEOPLE +
				" WHERE " + STUDENT_4__RESUME + " <> " + "''" + 
				" ORDER BY " + PEOPLE_1__SURNAME + ", " + PEOPLE_1__NAME + ", " + PEOPLE_1__PATRONYMIC; 
	}	
	
	public static String insertStudent(int i, String s){	
		return "INSERT INTO " + STUDENT_4 + 
			"("+ STUDENT_4__ID_PEOPLE + ", " + STUDENT_4__RESUME +
			") VALUES('" + i + "', '" + s + "')";
	}
	
	public static String deleteStudent(int code){		
		return "DELETE FROM " + STUDENT_4 + " WHERE "+ STUDENT_4__ID_PEOPLE + "=" + code;
	}
	
	public static String updateStudent(Student s){
		return "UPDATE " + STUDENT_4 +
					" SET " + STUDENT_4__RESUME + "=" + "'" + s.getResume() + "' " + 
					" WHERE " + STUDENT_4__ID_PEOPLE + "=" + "'" + s.getId_People() + "'";		
	}
		
	///// Teacher
	public static String insertTeacher(int i){	
		return "INSERT INTO " + TEACHER_5 + 
			"("+ TEACHER_5__ID_PEOPLE +  
			") VALUES('" + i + "')";
	}
	
	public static String deleteTeacher(int code){		
		return "DELETE FROM " + TEACHER_5 + " WHERE "+ TEACHER_5__ID_PEOPLE + "=" + code;
	}
	
	///// Employee
	public static String insertEmployee(int i){	
		return "INSERT INTO " + EMPLOYEE_6 + 
			"("+ EMPLOYEE_6__ID_PEOPLE +  
			") VALUES('" + i + "')";
	}
	
	public static String deleteEmployee(int code){
		return "DELETE FROM " + EMPLOYEE_6 + " WHERE "+ EMPLOYEE_6__ID_PEOPLE + "=" + code;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///// People_Phone_2
	public static String getPeople_Phone_2(int code){
		return "SELECT " + PEOPLE_PHONE_2__ID_PEOPLE + ", " + PEOPLE_PHONE_2__PHONE +
				" FROM " + PEOPLE_PHONE_2 +
				" WHERE " + PEOPLE_PHONE_2__ID_PEOPLE + "=" + code +
				" ORDER BY " + PEOPLE_PHONE_2__PHONE;
	}
	
	public static String insertPeople_Phone_2(int i, String s){	
		return "INSERT INTO " + PEOPLE_PHONE_2 + 
		"("+ PEOPLE_PHONE_2__ID_PEOPLE + ", " + PEOPLE_PHONE_2__PHONE +
		") VALUES('" + i + "', '" + s + "')";
	}
	
	public static String deletePeople_Phone_2(int code){		
		return "DELETE FROM " + PEOPLE_PHONE_2 + " WHERE "+ PEOPLE_PHONE_2__ID_PEOPLE + "=" + code;
	}	
	
	public static String deletePeople_Phone_2(int code, String value){		
		return "DELETE FROM " + PEOPLE_PHONE_2 + " WHERE "+ PEOPLE_PHONE_2__ID_PEOPLE + "='" + code + "'" + 
				" AND " + PEOPLE_PHONE_2__PHONE + "='" + value +"'";
	}
	
	///// People_Email_19
	public static String getPeople_Email_19(int code){	
		return "SELECT " + PEOPLE_EMAIL_19__ID_PEOPLE + ", " + PEOPLE_EMAIL_19__EMAIL +
				" FROM " + PEOPLE_EMAIL_19 +
				" WHERE " + PEOPLE_EMAIL_19__ID_PEOPLE + "=" + code +
				" ORDER BY " + PEOPLE_EMAIL_19__EMAIL;
	}
	
	public static String insertPeople_Email_19(int i, String s){	
		return "INSERT INTO " + PEOPLE_EMAIL_19 + 
		"("+ PEOPLE_EMAIL_19__ID_PEOPLE + ", " + PEOPLE_EMAIL_19__EMAIL +
		") VALUES('" + i + "', '" + s + "')";
	}
	
	public static String deletePeople_Email_19(int code){		
		return "DELETE FROM " + PEOPLE_EMAIL_19 + " WHERE "+ PEOPLE_EMAIL_19__ID_PEOPLE + "=" + code;
	}
	
	public static String deletePeople_Email_19(int code, String value){		
		return "DELETE FROM " + PEOPLE_EMAIL_19 + " WHERE "+ PEOPLE_EMAIL_19__ID_PEOPLE + "='" + code + "'" + 
				" AND " + PEOPLE_EMAIL_19__EMAIL + "='" + value +"'";
	}
	
	///// People_Employment_20
	public static String getPeople_Employment_20(int code){	
		return "SELECT " + PEOPLE_EMPLOYMENT_20__ID_PEOPLE + ", " + PEOPLE_EMPLOYMENT_20__EMPLOYMENT +
				" FROM " + PEOPLE_EMPLOYMENT_20 +
				" WHERE " + PEOPLE_EMPLOYMENT_20__ID_PEOPLE + "=" + code +
				" ORDER BY " + PEOPLE_EMPLOYMENT_20__EMPLOYMENT;
	}
	
	public static String insertPeople_Employment_20(int i, String s){	
		return "INSERT INTO " + PEOPLE_EMPLOYMENT_20 + 
		"("+ PEOPLE_EMPLOYMENT_20__ID_PEOPLE + ", " + PEOPLE_EMPLOYMENT_20__EMPLOYMENT +
		") VALUES('" + i + "', '" + s + "')";
	}
	
	public static String deletePeople_Employment_20(int code){		
		return "DELETE FROM " + PEOPLE_EMPLOYMENT_20 + " WHERE "+ PEOPLE_EMPLOYMENT_20__ID_PEOPLE + "=" + code;
	}
	
	public static String deletePeople_Employment_20(int code, String value){		
		return "DELETE FROM " + PEOPLE_EMPLOYMENT_20 + " WHERE "+ PEOPLE_EMPLOYMENT_20__ID_PEOPLE + "='" + code + "'" + 
				" AND " + PEOPLE_EMPLOYMENT_20__EMPLOYMENT + "='" + value +"'";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Message_7	
	public static String getMessage_7_Creator_Count(int creator){	
		return "SELECT COUNT(" + MESSAGE_7__ID_PEOPLE_CREATOR + ") " +
				" FROM " + MESSAGE_7 +
				" WHERE " + MESSAGE_7__ID_PEOPLE_CREATOR + "=" + creator;
	}
	
	public static String getMessage_7_Receiver_Count(int receiver){	
		return "SELECT COUNT(" + MESSAGE_7__ID_PEOPLE_RECEIVER + ") " +
				" FROM " + MESSAGE_7 +
				" WHERE " + MESSAGE_7__ID_PEOPLE_RECEIVER + "=" + receiver;
	}
	
	public static String getMessage_7(int id){
		return "SELECT " +MESSAGE_7__ID_MESSAGE + ", " + MESSAGE_7__MESSAGE_DATE + ", " + MESSAGE_7__THEME + ", " + 
								PEOPLE_1__LOGIN + ", " + PEOPLE_1__SURNAME + ", " + PEOPLE_1__NAME +
				" FROM " + PEOPLE_1 +" INNER JOIN " + MESSAGE_7 + " ON " 
															+ PEOPLE_1__ID_PEOPLE + "=" + MESSAGE_7__ID_PEOPLE_CREATOR + 
				" WHERE " + MESSAGE_7__ID_PEOPLE_RECEIVER + "=" + id +
				" ORDER BY " + MESSAGE_7__MESSAGE_DATE + " DESC ";
	}
	
	public static String getMessage_Message(int id){
		return "SELECT " + MESSAGE_7__MESSAGE_DATE + ", " + MESSAGE_7__THEME + ", " + MESSAGE_7__MESSAGE + 
				" FROM " + MESSAGE_7 + 
				" WHERE " + MESSAGE_7__ID_MESSAGE + "=" + id;
	}
	
	public static String getMessage_7_FreeID(){
		return "SELECT " + MESSAGE_7__ID_MESSAGE + " FROM " + MESSAGE_7;
	}
	
	public static String deleteMessage_7(int code){		
		return "DELETE FROM " + MESSAGE_7 + " WHERE "+ MESSAGE_7__ID_MESSAGE + "='" + code + "'";
	}
	
	public static String insertMessage_7(Message m){	
		return "INSERT INTO " + MESSAGE_7 + 
						"("+ MESSAGE_7__ID_MESSAGE + ", " + MESSAGE_7__ID_PEOPLE_CREATOR + ", " + 
							MESSAGE_7__ID_PEOPLE_RECEIVER + ", " + MESSAGE_7__MESSAGE + ", " + MESSAGE_7__THEME + ", " +
							MESSAGE_7__MESSAGE_DATE + 
						") VALUES('" + m.getId_Message() + "', '" + m.getId_People_Creator() + "', '" + 
									m.getId_People_Receiver() + "', '" +  m.getMessage() + "', '" + m.getTheme() + "', '" + 
									m.getMessage_Date() +
						"')";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Forum_8
	public static String getForum_8(){	
		return "SELECT " + FORUM_8__ID_CHAPTER + ", " + FORUM_8__CHAPTER_NAME + ", " + FORUM_8__CHAPTER_PASSWORD + ", " 
						 + FORUM_8__ID_PEOPLE + ", " + FORUM_8__CHAPTER_DATE + ", " + PEOPLE_1__LOGIN + 
				" FROM " + FORUM_8 + " INNER JOIN " + PEOPLE_1 + " ON " +
							FORUM_8__ID_PEOPLE + "=" + PEOPLE_1__ID_PEOPLE + 
				" ORDER BY " + FORUM_8__CHAPTER_DATE + " DESC ";
	}
	
	public static String getForum_8_pass(int chapId, String chapPass){	
		return "SELECT " + FORUM_8__ID_CHAPTER + ", " + FORUM_8__CHAPTER_PASSWORD +  
				" FROM " + FORUM_8 +
				" WHERE " + FORUM_8__ID_CHAPTER + "=" + chapId + " AND " +
							FORUM_8__CHAPTER_PASSWORD + "='" + chapPass + "'";
	}
	
	public static String getForum_8_name(int id){	
		return "SELECT " + FORUM_8__CHAPTER_NAME + 
				" FROM " + FORUM_8 +
				" WHERE " + FORUM_8__ID_CHAPTER + "=" + id;
	}
	
	public static String getForum_8_FreeID(){
		return "SELECT " + FORUM_8__ID_CHAPTER + " FROM " + FORUM_8;
	}
	
	public static String insertForum_8(Forum f){	
		return "INSERT INTO " + FORUM_8 + 
						"("+ FORUM_8__ID_CHAPTER + ", " + FORUM_8__CHAPTER_NAME + ", " + FORUM_8__CHAPTER_PASSWORD + ", " 
						 	+ FORUM_8__ID_PEOPLE + ", " + FORUM_8__CHAPTER_DATE + 
						") VALUES('" + f.getId_Chapter() + "', '" + f.getChapterName() + "', '" + 
									f.getChapterPassword() + "', '" + f.getId_People() + "', '" + f.getChapterDate() +
						"')";
	}
	
	public static String deleteForum_8(int code){		
		return "DELETE FROM " + FORUM_8 + " WHERE "+ FORUM_8__ID_CHAPTER + "='" + code + "'";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Comment_9
	public static String getComment_9(){	
		return "SELECT " + COMMENT_9__ID_CHAPTER + ", " + COMMENT_9__COMMENT_NUMBER + ", " + COMMENT_9__ID_PEOPLE + ", " 
						 + COMMENT_9__COMMENT_VALUE + ", " + COMMENT_9__COMMENT_DATE +
				" FROM " + COMMENT_9;
	}
	
	public static String getComment_9_oneChapter(int id){	
		return "SELECT " + COMMENT_9__ID_CHAPTER + ", " + COMMENT_9__COMMENT_NUMBER + ", " + COMMENT_9__ID_PEOPLE + ", " 
						 + COMMENT_9__COMMENT_VALUE + ", " + COMMENT_9__COMMENT_DATE + ", "
						 + PEOPLE_1__LOGIN + ", " + PEOPLE_1__AVATAR +
				" FROM " + PEOPLE_1 + " INNER JOIN " + COMMENT_9 + " ON " + 
																COMMENT_9__ID_PEOPLE + "=" + PEOPLE_1__ID_PEOPLE+
				" WHERE " + COMMENT_9__ID_CHAPTER + "=" + id +
				" ORDER BY " + COMMENT_9__COMMENT_NUMBER;
	}
	
	public static String insertComment_9(Comment c){	
		return "INSERT INTO " + COMMENT_9 + 
						"("+ COMMENT_9__ID_CHAPTER + ", " + COMMENT_9__COMMENT_NUMBER + ", " + COMMENT_9__ID_PEOPLE + ", " 
							+ COMMENT_9__COMMENT_VALUE + ", " + COMMENT_9__COMMENT_DATE + 
						") VALUES('" + c.getId_Chapter() + "', '" + c.getCommentNumber() + "', '" + 
								c.getId_People() + "', '" + c.getCommentValue() + "', '" + c.getCommentDate() + 
						"')";
	}
	
	public static String deleteComment_9(int code){		
		return "DELETE FROM " + COMMENT_9 + " WHERE "+ COMMENT_9__ID_CHAPTER + "='" + code + "'";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Task_13	
	public static String getTask_13(){	
		return "SELECT " + TASK_13__ID_TASK + ", " + TASK_13__NAME + ", " + TASK_13__DATE + ", " + TASK_13__ID_PEOPLE + 
				" FROM " + TASK_13;
	}
	
	public static String getTask_13_One_Id_people(int id){	
		return "SELECT " + TASK_13__ID_TASK + ", " + TASK_13__NAME + ", " + TASK_13__DATE + ", " + TASK_13__ID_PEOPLE + 
				" FROM " + TASK_13 +
				" WHERE " + TASK_13__ID_PEOPLE + "=" + id;
	}
	
	public static String getTask_13_One_Id_Task(int id){	
		return "SELECT " + TASK_13__ID_TASK + ", " + TASK_13__NAME + ", " + TASK_13__DATE + ", " + TASK_13__ID_PEOPLE + 
				" FROM " + TASK_13 +
				" WHERE " + TASK_13__ID_TASK + "=" + id;
	}
	
	public static String getTask_13_FreeID(){
		return "SELECT " + TASK_13__ID_TASK + " FROM " + TASK_13;
	}
	
	public static String insertTask_13(Task t){	
		return "INSERT INTO " + TASK_13 + 
						"("+ TASK_13__ID_TASK + ", " + TASK_13__NAME + ", " + TASK_13__DATE + ", " + TASK_13__ID_PEOPLE + 
						") VALUES('" + t.getId_Task() + "', '" + t.getName() + "', '" + 
									t.getDate() + "', '" + t.getId_People() +
						"')";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Alternative_14
	public static String getAlternative_14_One(int id_task){	
		return "SELECT " + ALTERNATIVE_14__ID_TASK + ", " + ALTERNATIVE_14__ALTERNATIVE_NAME +  
				" FROM " + ALTERNATIVE_14 +
				" WHERE " + ALTERNATIVE_14__ID_TASK + "=" + id_task;
	}
	
	public static String insertAlternative_14(Alternative a){	
		return "INSERT INTO " + ALTERNATIVE_14 + 
						"("+ ALTERNATIVE_14__ID_TASK + ", " + ALTERNATIVE_14__ALTERNATIVE_NAME + 
						") VALUES('" + a.getId_Task() + "', '" + a.getAlternativeName() +
						"')";
	}
	
	public static String deleteAlternative_14(int code, String value){		
		return "DELETE FROM " + ALTERNATIVE_14 + " WHERE "+ ALTERNATIVE_14__ID_TASK + "='" + code + "'" + 
				" AND " + ALTERNATIVE_14__ALTERNATIVE_NAME + "='" + value +"'";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Criterion_15
	public static String getCriterion_15_One(int id_task){	
		return "SELECT " + CRITERION_15__ID_TASK + ", " + CRITERION_15__CRITERION_NAME + 
				" FROM " + CRITERION_15 +
				" WHERE " + CRITERION_15__ID_TASK + "=" + id_task;
	}
	
	public static String insertCriterion_15(Criterion c){	
		return "INSERT INTO " + CRITERION_15 + 
						"("+ CRITERION_15__ID_TASK + ", " + CRITERION_15__CRITERION_NAME + 
						") VALUES('" + c.getId_Task() + "', '" + c.getCriterionName() + 
						"')";
	}
	
	public static String deleteCriterion_15(int code, String value){		
		return "DELETE FROM " + CRITERION_15 + " WHERE "+ CRITERION_15__ID_TASK + "='" + code + "'" + 
				" AND " + CRITERION_15__CRITERION_NAME + "='" + value +"'";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Comment_Object_17
	public static String getComment_Object_17(int id_Chapter, int number){	
		return "SELECT " + DATA_OBJECT_21__OBJECT + 
				" FROM " + DATA_OBJECT_21 +" INNER JOIN " + COMMENT_OBJECT_17 + " ON " +
							DATA_OBJECT_21__ID_OBJECT + "=" + COMMENT_OBJECT_17__ID_OBJECT + 
				" WHERE " + COMMENT_OBJECT_17__ID_CHAPTER + "=" + id_Chapter +
						" AND " + COMMENT_OBJECT_17__COMMENT_NUMBER + "=" + number;
	}
	
	public static String getComment_Object_17_FreeID(){
		return "SELECT " + COMMENT_OBJECT_17__ID_OBJECT + " FROM " + COMMENT_OBJECT_17;
	}
	
	public static String insertComment_Object_17(int id_Chapter, int number, int id_Object){	
		return "INSERT INTO " + COMMENT_OBJECT_17 + 
						"("+ COMMENT_OBJECT_17__ID_CHAPTER + ", " + COMMENT_OBJECT_17__COMMENT_NUMBER + ", " +
							COMMENT_OBJECT_17__ID_OBJECT +
						") VALUES('" + id_Chapter + "', '" + number + "', '" + id_Object + "')";
	}
	
	public static String deleteComment_Object_17(int code){		
		return "DELETE FROM " + COMMENT_OBJECT_17 + " WHERE "+ COMMENT_OBJECT_17__ID_CHAPTER + "='" + code + "'";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Data_Object_21
	public static String getData_Object_21(int id_Message){	
		return "SELECT " + DATA_OBJECT_21__OBJECT + 
				" FROM " + DATA_OBJECT_21 +" INNER JOIN " + MESSAGE_OBJECT_23 + " ON " +
							DATA_OBJECT_21__ID_OBJECT + "=" + MESSAGE_OBJECT_23__ID_OBJECT + 
				" WHERE " + MESSAGE_OBJECT_23__ID_MESSAGE + "=" + id_Message;
	}
	
	public static String getData_Object_21_One(String value){	
		return "SELECT " + DATA_OBJECT_21__ID_OBJECT + 
				" FROM " + DATA_OBJECT_21 + 
				" WHERE " + DATA_OBJECT_21__OBJECT + "='" + value +"'";
	}
	
	public static String getData_Object_21_FreeID(){
		return "SELECT " + DATA_OBJECT_21__ID_OBJECT + " FROM " + DATA_OBJECT_21;
	}
	
	public static String insertData_Object_21(int i, String s){	
		return "INSERT INTO " + DATA_OBJECT_21 + 
						"("+ DATA_OBJECT_21__ID_OBJECT + ", " + DATA_OBJECT_21__OBJECT +
						") VALUES('" + i + "', '" + s + "')";
	}	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Message_Object_23
	public static String insertMessage_Object_23(int id_Message, int id_Object){	
		return "INSERT INTO " + MESSAGE_OBJECT_23 + 
						"("+ MESSAGE_OBJECT_23__ID_MESSAGE + ", " + MESSAGE_OBJECT_23__ID_OBJECT +
						") VALUES('" +  id_Message + "', '" + id_Object + "')";
	}
	
	public static String deleteMessage_Object_23(int code){		
		return "DELETE FROM " + MESSAGE_OBJECT_23 + " WHERE "+ MESSAGE_OBJECT_23__ID_MESSAGE + "='" + code + "'";
	}
}

