import org.junit.runner.RunWith;

import com.egen.management.service.model.User;
import com.egen.management.service.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class) 
@Transactional
public class UserServiceImplTest {

    @Autowired 
    UserService userService; 
    
    private static List<User> VALID_USERS;
    private static List<User> INVALID_USERS;
    
    @BeforeClass
    public static void setupTestClass(){
	VALID_USERS = new ArrayList<User>();
	INVALID_USERS = new ArrayList<User>();
	VALID_USERS.add(new User("13234492", "Karthik", "", "Selvaraj",(short)30, 'M', 1289019999L, "12345"));
	
	INVALID_USERS.add(new User("", "", "", "Selvaraj",(short)30, 'M', 1289019999L, "12345"));
    }

    
    @AfterClass
    public static void tearDownTestClass(){
	VALID_USERS = null;
    }
    
    @Test
    public void getAllUsersTest(){
	userService.createUser(VALID_USERS.get(0));
	List<User> returnedUsers = userService.getAllUsers();
	assertNotNull("Retrieved data is null. Expected One", returnedUsers);
	assertTrue("Expected One User. But size = " + returnedUsers.size(), returnedUsers.size() == 1);
	assertTrue("Retrieved user value is not as Expected", User.equals(VALID_USERS.get(0), returnedUsers);
    }
    
    @Test
    public void validateUsersTest(){
	
	List<String> inValidErrorMessages = userService.validateUserInfo(INVALID_USERS.get(0));
	assertNotNull("Should get atleast one error message in the list -" + inValidErrorMessages);
	assertTrue("Both id and first name are empty" +errorMessages.size==2);
	
	List<String> validErrorMessages = userService.validateUserInfo(VALID_USERS.get(0));
	assertNull("List should be empty - "+validErrorMessages);
	assertTrue("No error messages" +errorMessages.size==1);
	
    }
    
    @Test
    public void findUserbyIdTest(){
	User user = VALID_USERS.get(0);
	userService.createUser(VALID_USERS.get(0));
	User returnedUser = userService.findById(user.getId());
	assertNotNull("Retrieved data is null. Expected One", returnedUsers);
	assertTrue("Both users are equal " + returnedUsers.size(), returnedUser.equals(user));
	//assertTrue("Retrieved user value is not as Expected", User.isAllValueEquals(VALID_USERS.get(0), returnedUsers
    }
    
    @Test
    public void updateUserTest(){
	User user = VALID_USERS.get(0);
	userService.createUser(VALID_USERS.get(0));
	User returnedUser = userService.findById(user.getId());
	assertNotNull("Retrieved data is not null. Expected One", returnedUser);
	user.setFirstName(ModifiedKarthik);
	userService.updateUser(returnedUser);
	User updatedUser = userService.findById(user.getId());
	assertNotNull("Retrieved data is not null. Expected One", updatedUser);
	assertTrue("Both users are equal " + returnedUsers.size(), returnedUser.getFirstName().equals(updatedUser.getFirstName()));
	//assertTrue("Retrieved user value is not as Expected", User.isAllValueEquals(VALID_USERS.get(0), returnedUsers
    }
}
