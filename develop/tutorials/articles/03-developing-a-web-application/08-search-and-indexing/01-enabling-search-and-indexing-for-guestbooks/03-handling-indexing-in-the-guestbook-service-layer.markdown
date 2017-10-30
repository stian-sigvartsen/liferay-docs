# Handling Indexing in the Guestbook Service Layer [](id=handling-indexing-in-the-guestbook-service-layer)

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Guestbooks<br>Step 3 of 3</p>
</div>

Whenever a guestbook database entity is added, updated, or deleted, the search 
index must be updated accordingly. The @product@ annotations `@Indexable` and 
`@IndexableType` mark your service methods so documents can be updated or 
deleted. You must update the `addGuestbook`, `updateGuestbook`, and 
`deleteGuestbook` service methods with these annotations. 

1.  Open `GuestbookLocalServiceImpl` in the `guestbook-service` module's 
    `com.liferay.docs.guestbook.service.impl` package, and add the following 
    annotation above the method signature for the `addGuestbook` and 
    `updateGuestbook` methods:

        @Indexable(type = IndexableType.REINDEX)
        public Guestbook addGuestbook(...)

        @Indexable(type = IndexableType.REINDEX)
        public Guestbook updateGuestbook(...)

    The `@Indexable` annotation indicates that an index update is required
    following the method execution. The `GuestbookIndexer` controls exactly how 
    the indexing happens. Setting the `@Indexable` annotation type to 
    `IndexableType.REINDEX` updates the document in the index that corresponds
    to the updated guestbook. 

2.  Add the following annotation above the method signature for the 
    `deleteGuestbook` method: 

        @Indexable(type = IndexableType.DELETE)
        public Guestbook deleteGuestbook(...)

    When a guestbook is deleted from the database, its document shouldn't
    remain in the search index. This ensures that it is deleted.

3.  Add the necessary imports:

        import com.liferay.portal.kernel.search.Indexable;
        import com.liferay.portal.kernel.search.IndexableType;

    Save the file. 

4.  In the Gradle Tasks pane on the right-hand side of Liferay @ide@, 
    double-click `buildService` in `guestbook-service` &rarr; `build`. This 
    re-runs Service Builder to incorporate your changes to 
    `GuestbookLocalServiceImpl`. 

Great! Next, you'll enable search and indexing for guestbook entries. 
