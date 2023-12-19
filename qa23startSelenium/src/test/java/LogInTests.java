import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogInTests {
 @BeforeClass
    public void preCondition(){
        //open
        //open site
    }
    @Test
    public void logInDone(){
        //open form(find+click)
        //fill email(find+click+clear+fill)
        //fill pass(find+click+clear+fill)
        //submit(find+click)
    }
   @Test
   public void logInWrongPass(){
       //open form(find+click)
       //fill email(find+click+clear+fill)
       //fill pass(find+click+clear+fill)
       //submit(find+click)
    }
    @Test
    public void logInWrongEmail(){
        //open form(find+click)
        //fill email(find+click+clear+fill)
        //fill pass(find+click+clear+fill)
        //submit(find+click)
    }
    @AfterClass
    public void postCond(){
        //close browser
    }
}
