package Test.com.company;

import com.company.controllers.CreateTask;
import com.company.controllers.Task;
import com.company.controllers.UpdateFile;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class isTaskUpdatedTest {

    @Test
    public void isMarkedAsDone() throws IOException {
        String fileName = "CDproject.txt";
        Task task = new Task("new test", "this file is to test mark as done", "2021-04-12");
        new CreateTask().writeTask(fileName, task);
        new UpdateFile(fileName).setState(7,"Done");
        var words = Files.lines(Paths.get(fileName))
                .map(s -> s.split("\\R"))
                .map(Arrays::asList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertEquals("Done", words.get(7));
    }

    @Test
    public void isTitleChanged() throws IOException {
        String fileName = "TrialProject.txt";
        Task task = new Task("titleBefore", "this file is to test changing title", "2021-05-12");
        new CreateTask().writeTask(fileName, task);
        new UpdateFile(fileName).setState(1,"titleAfter");
        var words = Files.lines(Paths.get(fileName))
                .map(s -> s.split("\\R"))
                .map(Arrays::asList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertEquals("titleAfter", words.get(1));
    }
}
