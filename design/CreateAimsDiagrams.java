import java.awt.geom.Point2D;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.*;
import com.change_vision.jude.api.inf.model.*;
import com.change_vision.jude.api.inf.presentation.INodePresentation;
import com.change_vision.jude.api.inf.project.ProjectAccessor;

public class CreateAimsDiagrams {

    static BasicModelEditor    bme;
    static UseCaseModelEditor  ume;
    static ProjectAccessor     prj;

    public static void main(String[] args) {
        try { run(); }
        catch (Throwable t) { t.printStackTrace(System.out); }
    }

    static void run() throws Exception {
        prj = AstahAPI.getAstahAPI().getProjectAccessor();
        prj.create("aims_diagrams.asta");
        IModel root = prj.getProject();
        bme = ModelEditorFactory.getBasicModelEditor();
        ume = ModelEditorFactory.getUseCaseModelEditor();
        System.out.println("Project created.");

        TransactionManager.beginTransaction();
        try {
            buildUseCaseDiagram(root);
            buildClassDiagram(root);
            TransactionManager.endTransaction();
        } catch (Exception e) {
            TransactionManager.abortTransaction();
            throw e;
        }
        prj.save();
        prj.close();
        System.out.println("Done.");
    }

    // ───────────────────────────────────────────────────────────────
    //  USE CASE DIAGRAM
    // ───────────────────────────────────────────────────────────────
    static void buildUseCaseDiagram(IModel root) throws Exception {
        IPackage pkg = bme.createPackage(root, "UseCaseModel");

        // Actor (IClass returned by createActor)
        IClass user = ume.createActor(pkg, "User");

        // Use cases
        IUseCase ucViewStore      = ume.createUseCase(pkg, "View Store");
        IUseCase ucViewCart       = ume.createUseCase(pkg, "View Cart");
        IUseCase ucAddToCart      = ume.createUseCase(pkg, "Add Media to Cart");
        IUseCase ucRemove         = ume.createUseCase(pkg, "Remove Media from Cart");
        IUseCase ucFilter         = ume.createUseCase(pkg, "Filter Items in Cart");
        IUseCase ucPlay           = ume.createUseCase(pkg, "Play Media");
        IUseCase ucOrder          = ume.createUseCase(pkg, "Place Order");
        IUseCase ucUpdate         = ume.createUseCase(pkg, "Update Store");
        IUseCase ucAddBook        = ume.createUseCase(pkg, "Add Book");
        IUseCase ucAddCD          = ume.createUseCase(pkg, "Add CD");
        IUseCase ucAddDVD         = ume.createUseCase(pkg, "Add DVD");

        // Associations: actor → use case
        IAssociation a1 = bme.createAssociation(user, ucViewStore,  "", "", "");
        IAssociation a2 = bme.createAssociation(user, ucViewCart,   "", "", "");
        IAssociation a3 = bme.createAssociation(user, ucAddToCart,  "", "", "");
        IAssociation a4 = bme.createAssociation(user, ucRemove,     "", "", "");
        IAssociation a5 = bme.createAssociation(user, ucFilter,     "", "", "");
        IAssociation a6 = bme.createAssociation(user, ucPlay,       "", "", "");
        IAssociation a7 = bme.createAssociation(user, ucOrder,      "", "", "");
        IAssociation a8 = bme.createAssociation(user, ucUpdate,     "", "", "");

        // <<include>>: Update Store → Add Book / Add CD / Add DVD
        IInclude inc1 = ume.createInclude(ucUpdate, ucAddBook, "");
        IInclude inc2 = ume.createInclude(ucUpdate, ucAddCD,   "");
        IInclude inc3 = ume.createInclude(ucUpdate, ucAddDVD,  "");

        // ── Diagram ──────────────────────────────────────────────────
        UseCaseDiagramEditor dgm =
            prj.getDiagramEditorFactory().getUseCaseDiagramEditor();
        dgm.createUseCaseDiagram(pkg, "AIMS Use Case Diagram");

        // Actor — left side
        INodePresentation userPs = dgm.createNodePresentation(user, new Point2D.Double(30, 340));

        // Main use cases — centre column
        double cx = 230;
        INodePresentation vsPs  = dgm.createNodePresentation(ucViewStore,  new Point2D.Double(cx, 30));
        INodePresentation vcPs  = dgm.createNodePresentation(ucViewCart,   new Point2D.Double(cx, 110));
        INodePresentation acPs  = dgm.createNodePresentation(ucAddToCart,  new Point2D.Double(cx, 190));
        INodePresentation rmPs  = dgm.createNodePresentation(ucRemove,     new Point2D.Double(cx, 270));
        INodePresentation fiPs  = dgm.createNodePresentation(ucFilter,     new Point2D.Double(cx, 350));
        INodePresentation pmPs  = dgm.createNodePresentation(ucPlay,       new Point2D.Double(cx, 430));
        INodePresentation poPs  = dgm.createNodePresentation(ucOrder,      new Point2D.Double(cx, 510));
        INodePresentation usPs  = dgm.createNodePresentation(ucUpdate,     new Point2D.Double(cx, 610));

        // Sub-use cases — right column
        double rx = 500;
        INodePresentation abPs  = dgm.createNodePresentation(ucAddBook, new Point2D.Double(rx, 560));
        INodePresentation acdPs = dgm.createNodePresentation(ucAddCD,   new Point2D.Double(rx, 640));
        INodePresentation advPs = dgm.createNodePresentation(ucAddDVD,  new Point2D.Double(rx, 720));

        // Associations
        dgm.createLinkPresentation(a1, userPs, vsPs);
        dgm.createLinkPresentation(a2, userPs, vcPs);
        dgm.createLinkPresentation(a3, userPs, acPs);
        dgm.createLinkPresentation(a4, userPs, rmPs);
        dgm.createLinkPresentation(a5, userPs, fiPs);
        dgm.createLinkPresentation(a6, userPs, pmPs);
        dgm.createLinkPresentation(a7, userPs, poPs);
        dgm.createLinkPresentation(a8, userPs, usPs);

        // Include links
        dgm.createLinkPresentation(inc1, usPs, abPs);
        dgm.createLinkPresentation(inc2, usPs, acdPs);
        dgm.createLinkPresentation(inc3, usPs, advPs);

        System.out.println("Use case diagram done.");
    }

    // ───────────────────────────────────────────────────────────────
    //  CLASS DIAGRAM
    // ───────────────────────────────────────────────────────────────
    static void buildClassDiagram(IModel root) throws Exception {
        IPackage pkg = bme.createPackage(root, "ClassModel");
        IPackage pkgMedia = bme.createPackage(pkg, "media");
        IPackage pkgCart  = bme.createPackage(pkg, "cart");
        IPackage pkgStore = bme.createPackage(pkg, "store");
        IPackage pkgExc   = bme.createPackage(pkg, "exception");
        IPackage pkgScr   = bme.createPackage(pkg, "screen");

        // Interface
        IClass playable = bme.createInterface(pkgMedia, "Playable");
        bme.createOperation(playable, "play", "void");

        // Abstract classes
        IClass media = bme.createClass(pkgMedia, "Media");
        media.setAbstract(true);
        bme.createAttribute(media, "id",       "int");
        bme.createAttribute(media, "title",    "String");
        bme.createAttribute(media, "category", "String");
        bme.createAttribute(media, "cost",     "float");
        bme.createOperation(media, "getTitle",    "String");
        bme.createOperation(media, "getCost",     "float");
        bme.createOperation(media, "getCategory", "String");
        bme.createOperation(media, "equals",      "boolean");

        IClass disc = bme.createClass(pkgMedia, "Disc");
        disc.setAbstract(true);
        bme.createAttribute(disc, "length",   "int");
        bme.createAttribute(disc, "director", "String");

        // Concrete media
        IClass book = bme.createClass(pkgMedia, "Book");
        bme.createAttribute(book, "authors", "ArrayList<String>");

        IClass dvd = bme.createClass(pkgMedia, "DigitalVideoDisc");
        bme.createOperation(dvd, "play", "void");

        IClass cd = bme.createClass(pkgMedia, "CompactDisc");
        bme.createAttribute(cd, "artist", "String");
        bme.createAttribute(cd, "tracks", "ArrayList<Track>");
        bme.createOperation(cd, "play",     "void");
        bme.createOperation(cd, "addTrack", "void");

        IClass track = bme.createClass(pkgMedia, "Track");
        bme.createAttribute(track, "title",  "String");
        bme.createAttribute(track, "length", "int");
        bme.createOperation(track, "play", "void");

        // Cart & Store
        IClass cart = bme.createClass(pkgCart, "Cart");
        bme.createAttribute(cart, "MAX_NUMBERS_ORDERED", "int");
        bme.createAttribute(cart, "itemsOrdered", "ObservableList<Media>");
        bme.createOperation(cart, "addMedia",    "void");
        bme.createOperation(cart, "removeMedia", "void");
        bme.createOperation(cart, "totalCost",   "float");

        IClass store = bme.createClass(pkgStore, "Store");
        bme.createAttribute(store, "itemsInStore", "ArrayList<Media>");
        bme.createOperation(store, "addMedia",    "void");
        bme.createOperation(store, "removeMedia", "void");

        // Exceptions
        IClass javaExc  = bme.createClass(pkgExc, "Exception");
        javaExc.addStereotype("java.lang");
        IClass limitEx  = bme.createClass(pkgExc, "LimitExceededException");
        IClass playerEx = bme.createClass(pkgExc, "PlayerException");

        // Screen classes
        IClass addParent = bme.createClass(pkgScr, "AddItemToStoreScreen");
        addParent.setAbstract(true);
        addParent.addStereotype("JFrame");
        IClass addBook = bme.createClass(pkgScr, "AddBookToStoreScreen");
        IClass addCD   = bme.createClass(pkgScr, "AddCompactDiscToStoreScreen");
        IClass addDVD  = bme.createClass(pkgScr, "AddDigitalVideoDiscToStoreScreen");

        // Generalizations
        IGeneralization gDiscMedia = bme.createGeneralization(disc,     media,    "");
        IGeneralization gBookMedia = bme.createGeneralization(book,     media,    "");
        IGeneralization gDvdDisc   = bme.createGeneralization(dvd,      disc,     "");
        IGeneralization gCdDisc    = bme.createGeneralization(cd,       disc,     "");
        IGeneralization gLimitEx   = bme.createGeneralization(limitEx,  javaExc,  "");
        IGeneralization gPlayerEx  = bme.createGeneralization(playerEx, javaExc,  "");
        IGeneralization gAddBook   = bme.createGeneralization(addBook,  addParent,"");
        IGeneralization gAddCD     = bme.createGeneralization(addCD,    addParent,"");
        IGeneralization gAddDVD    = bme.createGeneralization(addDVD,   addParent,"");

        // Realizations
        IRealization rDvd   = bme.createRealization(dvd,   playable, "");
        IRealization rCd    = bme.createRealization(cd,    playable, "");
        IRealization rTrack = bme.createRealization(track, playable, "");

        // Associations
        IAssociation aCartMedia  = bme.createAssociation(cart,  media, "orders",   "", "");
        IAssociation aStoreMedia = bme.createAssociation(store, media, "contains", "", "");
        IAssociation aCdTrack    = bme.createAssociation(cd,    track, "has",      "", "");

        // ── Diagram layout ────────────────────────────────────────────
        ClassDiagramEditor dgm =
            prj.getDiagramEditorFactory().getClassDiagramEditor();
        dgm.createClassDiagram(pkg, "AIMS Class Diagram");

        // Row 1: Playable | Media | Disc
        INodePresentation playPs  = dgm.createNodePresentation(playable, new Point2D.Double(10,  10));
        INodePresentation mediaPs = dgm.createNodePresentation(media,    new Point2D.Double(240, 10));
        INodePresentation discPs  = dgm.createNodePresentation(disc,     new Point2D.Double(480, 10));

        // Row 2: Book | DVD | CD | Track
        INodePresentation bookPs  = dgm.createNodePresentation(book,  new Point2D.Double(10,  260));
        INodePresentation dvdPs   = dgm.createNodePresentation(dvd,   new Point2D.Double(240, 260));
        INodePresentation cdPs    = dgm.createNodePresentation(cd,    new Point2D.Double(480, 260));
        INodePresentation trackPs = dgm.createNodePresentation(track, new Point2D.Double(720, 260));

        // Row 3: Cart | Store
        INodePresentation cartPs  = dgm.createNodePresentation(cart,  new Point2D.Double(10,  510));
        INodePresentation storePs = dgm.createNodePresentation(store, new Point2D.Double(300, 510));

        // Row 3 right: Exception hierarchy
        INodePresentation excPs   = dgm.createNodePresentation(javaExc,  new Point2D.Double(720, 480));
        INodePresentation limPs   = dgm.createNodePresentation(limitEx,  new Point2D.Double(620, 620));
        INodePresentation plyPs   = dgm.createNodePresentation(playerEx, new Point2D.Double(820, 620));

        // Row 4: Screen hierarchy
        INodePresentation apPs   = dgm.createNodePresentation(addParent, new Point2D.Double(380, 720));
        INodePresentation abPs   = dgm.createNodePresentation(addBook,   new Point2D.Double(200, 880));
        INodePresentation acdPs  = dgm.createNodePresentation(addCD,     new Point2D.Double(420, 880));
        INodePresentation advPs  = dgm.createNodePresentation(addDVD,    new Point2D.Double(640, 880));

        // Generalization links
        dgm.createLinkPresentation(gDiscMedia, discPs,  mediaPs);
        dgm.createLinkPresentation(gBookMedia, bookPs,  mediaPs);
        dgm.createLinkPresentation(gDvdDisc,   dvdPs,   discPs);
        dgm.createLinkPresentation(gCdDisc,    cdPs,    discPs);
        dgm.createLinkPresentation(gLimitEx,   limPs,   excPs);
        dgm.createLinkPresentation(gPlayerEx,  plyPs,   excPs);
        dgm.createLinkPresentation(gAddBook,   abPs,    apPs);
        dgm.createLinkPresentation(gAddCD,     acdPs,   apPs);
        dgm.createLinkPresentation(gAddDVD,    advPs,   apPs);

        // Realization links
        dgm.createLinkPresentation(rDvd,   dvdPs,   playPs);
        dgm.createLinkPresentation(rCd,    cdPs,    playPs);
        dgm.createLinkPresentation(rTrack, trackPs, playPs);

        // Association links
        dgm.createLinkPresentation(aCartMedia,  cartPs,  mediaPs);
        dgm.createLinkPresentation(aStoreMedia, storePs, mediaPs);
        dgm.createLinkPresentation(aCdTrack,    cdPs,    trackPs);

        System.out.println("Class diagram done.");
    }
}
