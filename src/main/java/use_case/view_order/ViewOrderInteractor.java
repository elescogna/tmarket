package use_case.view_order;

import entities.Order;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class ViewOrderInteractor implements ViewOrderInputBoundary {
    final ViewOrderDataAccessInterface orderDataAccessObject;
    final ViewOrderOutputBoundary viewOrderPresenter;

    public ViewOrderInteractor(ViewOrderDataAccessInterface orderDataAccessObject,
            ViewOrderOutputBoundary viewOrderPresenter) {
        this.orderDataAccessObject = orderDataAccessObject;
        this.viewOrderPresenter = viewOrderPresenter;
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
                .getScaledInstance(225, 164, java.awt.Image.SCALE_SMOOTH));

        return imageIcon;
    }

    @Override
    public void execute(ViewOrderInputData viewOrderInputData) {
        try {

            String orderIdToGet = viewOrderInputData.getOrderId();
            String currentStudentAddress = viewOrderInputData.getStudentAddress();

            Order ordertoDisplay = orderDataAccessObject.getOrder(orderIdToGet);

            if (ordertoDisplay != null) {
                ArrayList<String> directions = orderDataAccessObject.getDirections(
                        currentStudentAddress, ordertoDisplay.getPickupLocation());

                viewOrderPresenter.prepareSuccessView(new ViewOrderOutputData(
                        ordertoDisplay, ordertoDisplay.getItemName(),
                        retrieveImage(ordertoDisplay.getItemImageKey()), directions));
            } else {
                throw new IOException();
            }

        } catch (IOException e) {
            viewOrderPresenter.prepareFailView("Could not access Atlas database.");
        }
    }
}
