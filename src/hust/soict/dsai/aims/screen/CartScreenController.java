package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.SwingUtilities;

public class CartScreenController {

    private Cart cart;
    private Store store;
    private CartScreen cartScreen;

    // ── FXML fields ──────────────────────────────────────────────
    @FXML private TableView<Media>            tblMedia;
    @FXML private TableColumn<Media, String>  colMediaTitle;
    @FXML private TableColumn<Media, String>  colMediaCategory;
    @FXML private TableColumn<Media, Float>   colMediaCost;

    @FXML private Button  btnPlay;
    @FXML private Button  btnRemove;
    @FXML private Label   labelTotalCost;

    @FXML private TextField   tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;

    // ── Constructor (called before FXML fields are injected) ──────
    public CartScreenController(Cart cart, Store store, CartScreen cartScreen) {
        this.cart       = cart;
        this.store      = store;
        this.cartScreen = cartScreen;
    }

    // ── Called by FXMLLoader after all @FXML fields are set ───────
    @FXML
    private void initialize() {

        // Wire columns to Media getters
        colMediaTitle   .setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost    .setCellValueFactory(new PropertyValueFactory<>("cost"));

        // Wrap items in a FilteredList so filter works without changing the cart
        FilteredList<Media> filteredItems = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredItems);

        // Buttons hidden until a row is selected
        btnPlay  .setVisible(false);
        btnRemove.setVisible(false);

        updateTotalCost();

        // Show/hide buttons based on selected row
        tblMedia.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Media>() {
                    @Override
                    public void changed(ObservableValue<? extends Media> obs,
                                        Media oldVal, Media newVal) {
                        if (newVal != null) updateButtonBar(newVal);
                    }
                });

        // Re-filter whenever the text field changes
        tfFilter.textProperty().addListener(
            (obs, oldVal, newVal) -> showFilteredMedia(newVal, filteredItems)
        );
    }

    // ── Filter ────────────────────────────────────────────────────
    private void showFilteredMedia(String keyword, FilteredList<Media> list) {
        list.setPredicate(media -> {
            if (keyword == null || keyword.isBlank()) return true;
            if (radioBtnFilterId.isSelected()) {
                return String.valueOf(media.getId()).contains(keyword.trim());
            } else {
                return media.getTitle().toLowerCase()
                            .contains(keyword.trim().toLowerCase());
            }
        });
    }

    // ── Button bar ────────────────────────────────────────────────
    private void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        btnPlay  .setVisible(media instanceof Playable);
    }

    private void updateTotalCost() {
        labelTotalCost.setText(String.format("%.2f $", cart.totalCost()));
    }

    // ── Action handlers ───────────────────────────────────────────
    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
                showInfo("Playing", "Playing: " + media.getTitle());
            } catch (Exception e) {
                showError("Player Error", e.getMessage());
            }
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            btnPlay  .setVisible(false);
            btnRemove.setVisible(false);
            updateTotalCost();
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            showWarning("Place Order", "Your cart is empty!");
        } else {
            showInfo("Order Confirmed",
                     "Order placed!\nTotal: "
                     + String.format("%.2f $", cart.totalCost()));
            cart.getItemsOrdered().clear();
            btnPlay  .setVisible(false);
            btnRemove.setVisible(false);
            updateTotalCost();
        }
    }

    // ── Menu navigation ───────────────────────────────────────────
    @FXML void viewStore(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            new StoreScreen(store, cart);
            cartScreen.dispose();
        });
    }

    @FXML void viewCart(ActionEvent e) { /* already here */ }

    @FXML void openAddBook(ActionEvent e) {
        SwingUtilities.invokeLater(() -> new AddBookToStoreScreen(store, cart));
    }

    @FXML void openAddCD(ActionEvent e) {
        SwingUtilities.invokeLater(() -> new AddCompactDiscToStoreScreen(store, cart));
    }

    @FXML void openAddDVD(ActionEvent e) {
        SwingUtilities.invokeLater(() -> new AddDigitalVideoDiscToStoreScreen(store, cart));
    }

    // ── Helpers ───────────────────────────────────────────────────
    private void showInfo(String title, String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK)
            {{ setTitle(title); }}.showAndWait();
    }

    private void showWarning(String title, String msg) {
        new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK)
            {{ setTitle(title); }}.showAndWait();
    }

    private void showError(String title, String msg) {
        new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK)
            {{ setTitle(title); }}.showAndWait();
    }
}
