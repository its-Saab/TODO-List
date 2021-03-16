package Test.com.company;

import com.company.controllers.CreateTask;
import com.company.controllers.Task;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.io.File;

public class IsSavingTest {

    @Test
    public void isSavingFile() {
        String fileName = "MNproject.txt";
        Task task = new Task("new task", "buying somethings", "2021-02-12");
        new CreateTask().writeTask(fileName, task);
        assertTrue(new File("./MNproject.txt").exists());
    }


}
