package use_case.post;

import entities.Clothing;
import entities.Furniture;
import entities.SchoolItem;
import entities.Technology;
import use_case.posting.PostingOutputBoundary;

import java.io.IOException;
import java.time.LocalDateTime;

public class PostInteractor implements PostInputBoundary{
    final PostOutputBoundary postPresenter;
    final ClothingPostDataAccessInterface clothingDataAccessObject;
    final FurniturePostDataAccessInterface furnitureDataAccessObject;
    final SchoolItemPostDataAccessInterface schoolItemDataAccessObject;
    final TechnologyPostDataAccessInterface technologyDataAccessObject;

    public PostInteractor(ClothingPostDataAccessInterface clothingDataAccessObject,
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



    @Override
    public void execute(PostInputData postInputData) {
        if (postInputData.getCategory().equals("Furniture")){
            LocalDateTime now = LocalDateTime.now();
            //ID and image
            Furniture newFurniture = new Furniture(postInputData.getName(), postInputData.getDescription(),postInputData.getConditionScore(),
                    postInputData.getPrice(), postInputData.getAge(), false, postInputData.getPickupAddress(), postInputData.getStudent(),
                    postInputData.getType(),now, postInputData.getLength(), postInputData.getWidth(), postInputData.getHeight());
            try {
                furnitureDataAccessObject.addItemToFurnitureCollection(newFurniture);
                // MAYBE MAYBE NOT OUTPUT DATA
                PostOutputData postOutputData = new PostOutputData(postInputData.getStudent());
                postPresenter.prepareSuccessView(postOutputData);
            } catch (IOException e) {
                postPresenter.prepareFailView("Failed to post the furniture item.");
            }

        }
        else if (postInputData.getCategory().equals("Technology")){
            LocalDateTime now = LocalDateTime.now();
            //ID and image
            Technology newTechnology = new Technology(postInputData.getName(), postInputData.getDescription(),postInputData.getConditionScore(),
                    postInputData.getPrice(), postInputData.getAge(), false, postInputData.getPickupAddress(), postInputData.getStudent(),
                    postInputData.getType(),now, postInputData.getBrand(), postInputData.getCapabilities(), postInputData.getColour());
            try {
                technologyDataAccessObject.addItemToTechnologyCollection(newTechnology);
                // MAYBE MAYBE NOT OUTPUT DATA
                PostOutputData postOutputData = new PostOutputData(postInputData.getStudent());
                postPresenter.prepareSuccessView(postOutputData);
            } catch (IOException e) {
                postPresenter.prepareFailView("Failed to post the technology item.");
            }
        }
        else if (postInputData.getCategory().equals("Clothing")){
            LocalDateTime now = LocalDateTime.now();
            //ID and image
            Clothing newClothing = new Clothing(postInputData.getName(), postInputData.getDescription(),postInputData.getConditionScore(),
                    postInputData.getPrice(), postInputData.getAge(), false, postInputData.getPickupAddress(), postInputData.getStudent(),
                    postInputData.getType(),now, postInputData.getBrand(), postInputData.getColour(), postInputData.getSize(), postInputData.getMaterial());
            try {
                clothingDataAccessObject.addItemToClothingCollection(newClothing);
                // MAYBE MAYBE NOT OUTPUT DATA
                PostOutputData postOutputData = new PostOutputData(postInputData.getStudent());
                postPresenter.prepareSuccessView(postOutputData);
            } catch (IOException e) {
                postPresenter.prepareFailView("Failed to post the clothing item.");
            }
        }
        else if (postInputData.getCategory().equals("SchoolItem")){
            LocalDateTime now = LocalDateTime.now();
            //ID and image
            SchoolItem newSchoolItem = new SchoolItem(postInputData.getName(), postInputData.getDescription(),postInputData.getConditionScore(),
                    postInputData.getPrice(), postInputData.getAge(), false, postInputData.getPickupAddress(), postInputData.getStudent(),
                    postInputData.getType(),now, postInputData.getBrand(), postInputData.getColour());
            try {
                schoolItemDataAccessObject.addItemToSchoolItemCollection(newSchoolItem);
                // MAYBE MAYBE NOT OUTPUT DATA
                PostOutputData postOutputData = new PostOutputData(postInputData.getStudent());
                postPresenter.prepareSuccessView(postOutputData);
            } catch (IOException e) {
                postPresenter.prepareFailView("Failed to post the school item.");
            }
        }

    }
}
