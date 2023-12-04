package interface_adapter.post;

import entities.Student;
import org.junit.jupiter.api.Test;
import use_case.post.PostInputBoundary;
import use_case.post.PostInputData;

import static org.junit.jupiter.api.Assertions.*;

class MockPostInteractor implements PostInputBoundary {
    private boolean isExecuteCalled = false;
    private PostInputData inputData;
    @Override
    public void execute(PostInputData inputData) {
        this.isExecuteCalled = true;
        this.inputData = inputData;
    }

    public PostInputData getInputData() {
        return inputData;
    }

    public boolean isExecuteCalled() {
        return isExecuteCalled;
    }
}

public class PostControllerTest {
    @Test
    void successTest() {
        MockPostInteractor interactor = new MockPostInteractor();
        PostController controller = new PostController(interactor);

        Student student = new Student("someId", "John", "Doe", "700 University", "john.doe@mail.utoronto.ca");
        PostInputData postInputData = new PostInputData(student, "", "", "", "", "",  3, 3, 3, "", "", "");

        controller.execute(student,"", "", "", "", "", 3, 3, 3, "", "", "");
        assertTrue(interactor.isExecuteCalled());
        assertEquals(postInputData.getStudent(), interactor.getInputData().getStudent());
        assertEquals(postInputData.getCategory(), interactor.getInputData().getCategory());
        assertEquals(postInputData.getType(), interactor.getInputData().getType());
        assertEquals(postInputData.getName(), interactor.getInputData().getName());
    }
}

