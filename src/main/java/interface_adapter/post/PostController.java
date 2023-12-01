package interface_adapter.post;

import entities.Student;
import use_case.post.PostInputBoundary;
import use_case.post.PostInputData;

public class PostController {
    final PostInputBoundary postUseCaseInteractor;
    public PostController(PostInputBoundary postUseCaseInteractor) {
        this.postUseCaseInteractor = postUseCaseInteractor;
    }

    // furniture
    public void execute(Student student, String category, String type,
            String name, String description, String pickupAddress,
            int conditionScore, int age, int price, double length,
            double width, double height) {
        PostInputData postInputData = new PostInputData(
                student, category, type, name, description, pickupAddress,
                conditionScore, age, price, length, width, height);
        postUseCaseInteractor.execute(postInputData);
    }

    // technology
    public void execute(Student student, String category, String type,
            String name, String description, String pickupAddress,
            int conditionScore, int age, int price, String brand,
            String capabilities, String colour) {
        PostInputData postInputData = new PostInputData(
                student, category, type, name, description, pickupAddress,
                conditionScore, age, price, brand, capabilities, colour);
        postUseCaseInteractor.execute(postInputData);
    }

    // school item
    public void execute(Student student, String category, String type,
            String name, String description, String pickupAddress,
            int conditionScore, int age, int price, String brand,
            String colour) {
        PostInputData postInputData = new PostInputData(
                student, category, type, name, description, pickupAddress,
                conditionScore, age, price, brand, colour);
        postUseCaseInteractor.execute(postInputData);
    }

    // clothing
    public void execute(Student student, String category, String type,
            String name, String description, String pickupAddress,
            int conditionScore, int age, int price, String brand,
            String colour, String size, String material) {
        PostInputData postInputData = new PostInputData(
                student, category, type, name, description, pickupAddress,
                conditionScore, age, price, brand, colour, size, material);
        postUseCaseInteractor.execute(postInputData);
    }
}
