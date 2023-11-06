package data_access;

public class AtlasDataAccessObject {
    protected static final String atlasDataSourceName =
        System.getenv("ATLAS_DATA_SOURCE_NAME");
    protected static final String atlasDatabaseName =
        System.getenv("ATLAS_DATABASE_NAME");
    protected static final String atlasApiEndpoint =
        System.getenv("ATLAS_API_ENDPOINT");
    protected static final String atlasApiKey = System.getenv("ATLAS_API_KEY");
}
