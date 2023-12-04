package interface_adapter.post;

import entities.Student;
import use_case.post.PostInputBoundary;
import use_case.post.PostInputData;

public class PostController {
    final PostInputBoundary postUseCaseInteractor;
    /**
     * Creates a new PostController with the PostInteractor given.
     *
     * @param postUseCaseInteractor the PostInteractor with which to call the PostController.
     */
    public PostController(PostInputBoundary postUseCaseInteractor) {
        this.postUseCaseInteractor = postUseCaseInteractor;
    }

    // furniture
    public void execute(Student student, String category, String type,
            String name, String description, String pickupAddress,
            int conditionScore, int age, int price, double length,
            double width, double height, String imagePath) {
        PostInputData postInputData = new PostInputData(
                student, category, type, name, description, pickupAddress,
                conditionScore, age, price, length, width, height, imagePath);
        postUseCaseInteractor.execute(postInputData);
    }

    // technology
    public void execute(Student student, String category, String type,
            String name, String description, String pickupAddress,
            int conditionScore, int age, int price, String brand,
            String capabilities, String colour, String imagePath) {
        PostInputData postInputData = new PostInputData(
                student, category, type, name, description, pickupAddress,
                conditionScore, age, price, brand, capabilities, colour, imagePath);
        postUseCaseInteractor.execute(postInputData);
    }

    // school item
    public void execute(Student student, String category, String type,
            String name, String description, String pickupAddress,
            int conditionScore, int age, int price, String brand,
            String colour, String imagePath) {
        PostInputData postInputData = new PostInputData(
                student, category, type, name, description, pickupAddress,
                conditionScore, age, price, brand, colour, imagePath);
        postUseCaseInteractor.execute(postInputData);
    }

    // clothing
    public void execute(Student student, String category, String type,
            String name, String description, String pickupAddress,
            int conditionScore, int age, int price, String brand,
            String colour, String size, String material,
            String imagePath) {
        PostInputData postInputData = new PostInputData(
                student, category, type, name, description, pickupAddress,
                conditionScore, age, price, brand, colour, size, material, imagePath);
        postUseCaseInteractor.execute(postInputData);
    }
}
