package use_case.view_item;

import entities.Item;
import java.io.IOException;
import javax.swing.ImageIcon;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class ViewItemInteractor implements ViewItemInputBoundary {
    final ViewItemDataAccessInterface clothingDataAccessObject;
    final ViewItemDataAccessInterface furnitureDataAccessObject;
    final ViewItemDataAccessInterface schoolItemDataAccessObject;
    final ViewItemDataAccessInterface technologyDataAccessObject;
    final ViewItemOutputBoundary viewItemPresenter;

    public ViewItemInteractor(
            ViewItemDataAccessInterface clothingDataAccessObject,
            ViewItemDataAccessInterface furnitureDataAccessObject,
            ViewItemDataAccessInterface schoolItemDataAccessObject,
            ViewItemDataAccessInterface technologyDataAccessObject,
            ViewItemOutputBoundary viewItemOutputBoundary) {
        this.clothingDataAccessObject = clothingDataAccessObject;
        this.furnitureDataAccessObject = furnitureDataAccessObject;
        this.schoolItemDataAccessObject = schoolItemDataAccessObject;
        this.technologyDataAccessObject = technologyDataAccessObject;
        this.viewItemPresenter = viewItemOutputBoundary;
            }

    private ImageIcon retrieveImage(String key) throws IOException {
        Region region = Region.US_EAST_2;
        String bucketName = System.getenv("AWS_S3_BUCKET_NAME");

        S3Client s3 = S3Client.builder()
            .region(region)
            .credentialsProvider(
                    EnvironmentVariableCredentialsProvider.create())
            .build();
        ResponseInputStream<GetObjectResponse> image = s3.getObject(
                GetObjectRequest.builder().bucket(bucketName).key(key).build());

        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon(image.readAllBytes())
                .getImage()
                .getScaledInstance(399, 316, java.awt.Image.SCALE_SMOOTH));

        return imageIcon;
    }

    /**
     * Makes the required DAO calls with the given ViewItemInputData
     *
     * @param viewItemData the input data with which to call by the ViewItemPresenter
     */
    @Override
    public void execute(ViewItemInputData viewItemData) {
        try {
            Item itemToDisplay;

            // try to find the item in any of the collections
            String itemIdToGet = viewItemData.getItemId();

            if ((itemToDisplay = clothingDataAccessObject.getItem(itemIdToGet)) !=
                    null ||
                    (itemToDisplay = furnitureDataAccessObject.getItem(itemIdToGet)) !=
                    null ||
                    (itemToDisplay = schoolItemDataAccessObject.getItem(itemIdToGet)) !=
                    null ||
                    (itemToDisplay = technologyDataAccessObject.getItem(itemIdToGet)) !=
                    null) {
                viewItemPresenter.prepareSuccessView(new ViewItemOutputData(
                            itemToDisplay, retrieveImage(itemToDisplay.getPicture()),
                            viewItemData.getCurrentStudent()));
            } else { // if the item wasn't found in any collection
                throw new IOException("Item with the given ID not found in database.");
            }
        } catch (IOException e) {
            viewItemPresenter.prepareFailView(
                    "No such item was found in the database.");
        }
    }
}
