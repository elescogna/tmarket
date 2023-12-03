package use_case.post;

import entities.Clothing;
import entities.Furniture;
import entities.SchoolItem;
import entities.Technology;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class PostInteractor implements PostInputBoundary {
    final PostOutputBoundary postPresenter;
    final ClothingPostDataAccessInterface clothingDataAccessObject;
    final FurniturePostDataAccessInterface furnitureDataAccessObject;
    final SchoolItemPostDataAccessInterface schoolItemDataAccessObject;
    final TechnologyPostDataAccessInterface technologyDataAccessObject;

    public PostInteractor(
            ClothingPostDataAccessInterface clothingDataAccessObject,
            FurniturePostDataAccessInterface furnitureDataAccessObject,
            SchoolItemPostDataAccessInterface schoolItemDataAccessObject,
            TechnologyPostDataAccessInterface technologyDataAccessObject,
            PostOutputBoundary postPresenter) {
        this.clothingDataAccessObject = clothingDataAccessObject;
        this.furnitureDataAccessObject = furnitureDataAccessObject;
        this.schoolItemDataAccessObject = schoolItemDataAccessObject;
        this.technologyDataAccessObject = technologyDataAccessObject;
        this.postPresenter = postPresenter;
            }

    private int getLastFileIndex() throws IOException {

        ArrayList<Integer> indices = new ArrayList<Integer>();

        indices.add(this.clothingDataAccessObject.getLastImageIndex());
        indices.add(this.furnitureDataAccessObject.getLastImageIndex());
        indices.add(this.schoolItemDataAccessObject.getLastImageIndex());
        indices.add(this.technologyDataAccessObject.getLastImageIndex());

        return Collections.max(indices);
    }

    private String uploadFile(String imagePath) throws IOException {
        if (imagePath.isEmpty() || !imagePath.endsWith(".png")) {
            throw new IOException("");
        } else {
            Region region = Region.US_EAST_2;
            String bucketName = System.getenv("AWS_S3_BUCKET_NAME");

            S3Client s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(
                        EnvironmentVariableCredentialsProvider.create())
                .build();

            String key = String.valueOf(getLastFileIndex() + 1);

            PutObjectRequest request =
                PutObjectRequest.builder().bucket(bucketName).key(key).build();

            try {
                s3.putObject(request, new java.io.File(imagePath).toPath());
            } finally {
                s3.close();
            }

            return key;
        }
    }

    @Override
    public void execute(PostInputData postInputData) {
        if (postInputData.getCategory().equals("Furniture")) {
            String nextImageId;
            try {
                nextImageId = uploadFile(postInputData.getImagePath());
            } catch (IOException e){
                postPresenter.prepareFailView("Failed to post the furniture item.");
                return;
            }
            LocalDateTime now = LocalDateTime.now();
            // ID and image
            Furniture newFurniture = new Furniture(
                    postInputData.getName(), postInputData.getDescription(),
                    postInputData.getConditionScore(), postInputData.getPrice(),
                    postInputData.getAge(), false, postInputData.getPickupAddress(),
                    postInputData.getStudent().getId(), postInputData.getType(), nextImageId, now,
                    postInputData.getLength(), postInputData.getWidth(),
                    postInputData.getHeight());
            try {
                furnitureDataAccessObject.addItemToFurnitureCollection(newFurniture);
                PostOutputData postOutputData =
                    new PostOutputData(postInputData.getStudent());
                postPresenter.prepareSuccessView(postOutputData);
            } catch (IOException e) {
                postPresenter.prepareFailView("Failed to post the furniture item.");
            }

        } else if (postInputData.getCategory().equals("Technology")) {
            String nextImageId;
            try {
                nextImageId = uploadFile(postInputData.getImagePath());
            } catch (IOException e){
                postPresenter.prepareFailView("Failed to post the technology item.");
                System.out.println(e);
                return;
            }
            LocalDateTime now = LocalDateTime.now();
            // ID and image
            Technology newTechnology = new Technology(
                    postInputData.getName(), postInputData.getDescription(),
                    postInputData.getConditionScore(), postInputData.getPrice(),
                    postInputData.getAge(), false, postInputData.getPickupAddress(),
                    postInputData.getStudent().getId(), postInputData.getType(), nextImageId, now,
                    postInputData.getBrand(), postInputData.getCapabilities(),
                    postInputData.getColour());
            try {
                technologyDataAccessObject.addItemToTechnologyCollection(newTechnology);
                PostOutputData postOutputData =
                    new PostOutputData(postInputData.getStudent());
                postPresenter.prepareSuccessView(postOutputData);
            } catch (IOException e) {
                postPresenter.prepareFailView("Failed to post the technology item.");
            }
        } else if (postInputData.getCategory().equals("Clothing")) {
            String nextImageId;
            try {
                nextImageId = uploadFile(postInputData.getImagePath());
            } catch (IOException e){
                postPresenter.prepareFailView("Failed to post the clothing item.");
                return;
            }
            LocalDateTime now = LocalDateTime.now();
            // ID and image
            Clothing newClothing = new Clothing(
                    postInputData.getName(), postInputData.getDescription(),
                    postInputData.getConditionScore(), postInputData.getPrice(),
                    postInputData.getAge(), false, postInputData.getPickupAddress(),
                    postInputData.getStudent().getId(), postInputData.getType(), nextImageId, now,
                    postInputData.getBrand(), postInputData.getColour(),
                    postInputData.getSize(), postInputData.getMaterial());
            try {
                clothingDataAccessObject.addItemToClothingCollection(newClothing);
                PostOutputData postOutputData =
                    new PostOutputData(postInputData.getStudent());
                postPresenter.prepareSuccessView(postOutputData);
            } catch (IOException e) {
                postPresenter.prepareFailView("Failed to post the clothing item.");
            }
        } else if (postInputData.getCategory().equals("SchoolItem")) {
            String nextImageId;
            try {
                nextImageId = uploadFile(postInputData.getImagePath());
            } catch (IOException e){
                postPresenter.prepareFailView("Failed to post the school item.");
                return;
            }
            LocalDateTime now = LocalDateTime.now();
            // ID and image
            SchoolItem newSchoolItem = new SchoolItem(
                    postInputData.getName(), postInputData.getDescription(),
                    postInputData.getConditionScore(), postInputData.getPrice(),
                    postInputData.getAge(), false, postInputData.getPickupAddress(),
                    postInputData.getStudent().getId(), postInputData.getType(), nextImageId, now,
                    postInputData.getBrand(), postInputData.getColour());
            try {
                schoolItemDataAccessObject.addItemToSchoolItemCollection(newSchoolItem);
                PostOutputData postOutputData =
                    new PostOutputData(postInputData.getStudent());
                postPresenter.prepareSuccessView(postOutputData);
            } catch (IOException e) {
                postPresenter.prepareFailView("Failed to post the school item.");
            }
        }
    }
}
