package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.view_order.ViewOrderViewModel;
import java.io.IOException;
import javax.swing.JOptionPane;
import view.ViewOrderView;

public class ViewOrderUseCaseFactory {

    /** Prevent instantiation. */
    private ViewOrderUseCaseFactory() {}

    public static ViewOrderView create(ViewManagerModel viewManagerModel,
            HomeViewModel homeViewModel,
            ViewOrderViewModel viewOrderViewModel) {

        return new ViewOrderView(viewOrderViewModel, homeViewModel,
                viewManagerModel);
    }
}
