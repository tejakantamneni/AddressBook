package com.jags.fxui;

import com.jags.Address;
import com.jags.utils.AddressBookUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.jags.utils.Wellknown.WINDOW_HEIGHT;
import static com.jags.utils.Wellknown.WINDOW_WIDTH;

/**
 * Created by Teja Kantamneni on 1/17/16.
 */
public class AddressBook extends Application {
    private static final Log LOG = LogFactory.getLog(AddressBookUtil.class);
    List<Address> addressList = new ArrayList<>();
    ObservableList<Address> addressObservableList = FXCollections.observableArrayList();
    private Pagination pagination;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Group root = new Group();

        initAddressBook();
        pagination = new Pagination((addressObservableList.size() / rowsPerPage()), 0);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                if (pageIndex > addressObservableList.size() / rowsPerPage() + 1) {
                    return null;
                } else {
                    try {
                        return createPage(pageIndex);
                    } catch (IOException e) {
                        LOG.error("Unable to create page, ", e);
                    }
                }
                return null;
            }
        });

        AnchorPane anchor = new AnchorPane();
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        anchor.getChildren().addAll(pagination);

        root.getChildren().addAll(anchor);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();

    }

    private void initAddressBook() {
        try {
            List<Address> addressListFromFile = AddressBookUtil.loadAddressListFromFile();
            if (addressListFromFile != null && !addressListFromFile.isEmpty()) {
                addressList.addAll(addressListFromFile);
                addressObservableList.addAll(addressListFromFile);
            }
        } catch (Exception e) {
            LOG.error("error loading saved address file", e);
        }
    }

    public int itemsPerPage() {
        return 1;
    }

    public int rowsPerPage() {
        return 1;
    }

    public VBox createPage(int pageIndex) throws IOException {
        FXMLLoader.load(getClass().getResource("/address.fxml"));
        VBox box = new VBox(5);
        Label firstNameLabel = new Label(addressObservableList.get(pageIndex).getFirstName());

        box.getChildren().add(firstNameLabel);
        return box;
    }
}
