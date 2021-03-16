package Test.com.company;

import com.company.controllers.CreateTask;
import com.company.controllers.Task;
import com.company.controllers.UpdateFile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import java.io.File;
import java.io.IOException;

public class IsRemovingTest {
    @Test
    public void isFileRemoved() throws IOException {
        new CreateTask().writeTask("AJproject.txt", new Task("new task", "buying somethings", "2022-03-15"));
        new UpdateFile("AJproject.txt").deleteFile();
        assertFalse(new File("./AJproject.txt").exists());
    }
}
